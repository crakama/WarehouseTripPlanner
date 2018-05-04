package com.crakama.warehouseclient;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements HomeFragment.OnItemClickedListener{
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
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, homeFragment).commit();
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
    public void setConnectionInfo(final String connectionprogress) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                HomeFragment.setConnectionInfo(connectionprogress);
            }
        });
    }
}
