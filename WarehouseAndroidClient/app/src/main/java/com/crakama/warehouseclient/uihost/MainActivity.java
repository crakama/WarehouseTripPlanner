package com.crakama.warehouseclient.uihost;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import com.crakama.warehouseclient.R;
import com.crakama.warehouseclient.controller.EventHandler;
import com.crakama.warehouseclient.ui.viewdisplay.HomeFragment;
import com.crakama.warehouseclient.ui.viewdisplay.ResultsDisplayFragment;
import com.crakama.warehouseclient.ui.viewdisplay.SearchItemFragment;

public class MainActivity extends AppCompatActivity implements HomeFragment.OnItemClickedListener,
        SearchItemFragment.OnItemClickedListener,ResultsDisplayFragment.OnItemClickedListener{
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
                if(code == 0){
                    HomeFragment.setConnectionInfo(connectionprogress);
                }else if(code == 1){
                    SearchItemFragment searchItemFragment = new SearchItemFragment();
                    changeFragment(searchItemFragment);
                }
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

    public void displayProductLocation(final String msg) {
        System.out.println("MESSAGE FROM SERVER" + msg);
        handler.post(new Runnable() {
            @Override
            public void run() {
               // SearchItemFragment.setSearchInfo(msg);
                changeFragment(new ResultsDisplayFragment());
            }
        });
    }
}
