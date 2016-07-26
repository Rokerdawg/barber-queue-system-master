package com.barber.shop.queue.system;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.queue.shop.barber.barbershopqueuesystem.R;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button mJoinQueueButton, mLeaveQueueButton, mRefreshQueueButton;
    RecyclerView mQueueList;
    ViewGroup mRootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initViews();
        setClickListeners();

    }

    private void initViews() {
        mJoinQueueButton = (Button)findViewById(R.id.add_customer_button);
        mLeaveQueueButton = (Button)findViewById(R.id.remove_customer_button);
        mRefreshQueueButton = (Button)findViewById(R.id.refresh_queue_button);
        mQueueList = (RecyclerView)findViewById(R.id.queue_listview);
        mRootView = (LinearLayout)findViewById(R.id.rootview);
    }

    private void setClickListeners() {
        for(int i = 0; i<(mRootView).getChildCount(); ++i) {
            View nextChild = (mRootView).getChildAt(i);
            if(nextChild instanceof Button){
                nextChild.setOnClickListener(this);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.add_customer_button:
                // TODO - implement add customer to queue and list
                break;
            case R.id.remove_customer_button:
                // TODO - implement remove customer from queue and list
                break;
            case R.id.refresh_queue_button:
                // TODO - refresh recyclerview
        }
    }
}
