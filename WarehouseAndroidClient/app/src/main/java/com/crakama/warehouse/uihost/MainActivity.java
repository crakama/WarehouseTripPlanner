package com.crakama.warehouse.uihost;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import com.crakama.warehouse.R;
import com.crakama.warehouse.common.MsgProtocol;
import com.crakama.warehouse.controller.EventHandler;
import com.crakama.warehouse.ui.viewdisplay.HomeFragment;
import com.crakama.warehouse.ui.viewdisplay.NotifyFragment;
import com.crakama.warehouse.ui.viewdisplay.ResultsDisplayFragment;
import com.crakama.warehouse.ui.viewdisplay.SearchItemFragment;

public class MainActivity extends AppCompatActivity implements HomeFragment.OnItemClickedListener,
        SearchItemFragment.OnItemClickedListener,ResultsDisplayFragment.OnItemClickedListener,
        NotifyFragment.OnUpdateDialogListener{
    private Handler handler;
    private EventHandler eventHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Handler();
        eventHandler = new EventHandler(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(findViewById(R.id.fragment_container) != null){
            if(savedInstanceState != null){
                return;}
            HomeFragment homeFragment = new HomeFragment();
            homeFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,
                    homeFragment).commit();
        }
    }

    @Override
    public void connectionBtnClicked(String name, String pass) {
        eventHandler.connectionAttempt(name,pass);
    }

    public void setConnectionButton(final boolean connectionButton) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                HomeFragment.setButtonState(connectionButton);
            }
        });
    }
    public void setConnectionInfo(final int code, final String connectionprogress) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                    HomeFragment.setConnectionInfo(connectionprogress);
            }
        });
    }
    public void changeFragment(Fragment newFragment) {
        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    @Override
    public void searchBtnClicked(String productid) {
        eventHandler.searchProduct(productid);
    }

    public void displayProductLocation(final MsgProtocol response) {
        System.out.println("MESSAGE FROM SERVER" + response);
        handler.post(new Runnable() {
            @Override
            public void run() {
                switch (response.getMsgType()) {
                    case RESPONSE:
                        //TODO connectionHandler.sendMessage(productid);
                        String msg = response.getMsgBody();
                        changeFragment(new ResultsDisplayFragment());
                        break;
                    case UPDATE:
                        triggerUpdateDialog(response.getMsgBody());
                        break;
                    case CONNECTION_OK:
                        SearchItemFragment searchItemFragment = new SearchItemFragment();
                        changeFragment(searchItemFragment);
                        break;
                }
            }
        });
    }
    private void triggerUpdateDialog(String reply) {
        NotifyFragment instructionsFragment = new NotifyFragment();
        Bundle args = new Bundle();
        args.putString("reply", reply);
        instructionsFragment.setArguments(args);
        instructionsFragment.show(getSupportFragmentManager(),"Update Dialog");
    }

    @Override
    public void btnOKClicked(String text) {
        //TODO Refresh the recycler view
    }
}
