package com.barber.shop.queue.system.views.activity;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.barber.shop.queue.system.views.fragment.LoginFragment;
import com.barber.shop.queue.system.views.fragment.QueueListFragment;
import com.barber.shop.queue.system.views.fragment.RegisterFragment;
import com.firebase.client.Firebase;
import com.queue.shop.barber.barbershopqueuesystem.R;

import service.DefaultServiceManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        QueueListFragment.OnFragmentInteractionListener, RegisterFragment.OnRegisterFragmentInteractionListener,
        LoginFragment.OnLoginFragmentInteractionListener {

    Button mViewQueueButton, mJoinQueueButton, mLeaveQueueButton, mRefreshQueueButton;
    Toolbar mToolbar;
    RecyclerView mQueueList;
    ViewGroup mRootView;
    LinearLayout mFragmentsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setSupportActionBar(mToolbar);
        setClickListeners();
        setInitalFragment(savedInstanceState);

        Firebase.setAndroidContext(this);
        DefaultServiceManager mConnectDB = new DefaultServiceManager();
        mConnectDB.getDBConnection();

    }
//TODO - IMPLEMENT ONVALUECHANGED IN ONSTART()
//    @Override
//    protected void onStart() {
//        super.onStart();
//        // add listener onStart
//        serviceManager.getDBChildReference("customer").addValueEventListener(new ValueEventListener() {
//            //This fires every time the condition branch changes
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // Do something with data received (dataSnapShot
//                // update recyclerview
//            }
//            // handle error events
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//        mConditionRef.addValueEventListener(new ValueEventListener() {
//            //This fires every time the condition branch changes
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // Do something with data received (dataSnapShot
//                // update recyclerview
//            }
//        // handle error events
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }

    private void initViews() {
        mJoinQueueButton = (Button) findViewById(R.id.button_add_customer);
        mLeaveQueueButton = (Button) findViewById(R.id.button_remove_customer);
        mRefreshQueueButton = (Button) findViewById(R.id.button_refresh_queue);
        mQueueList = (RecyclerView) findViewById(R.id.queue_listview);
        mRootView = (LinearLayout) findViewById(R.id.rootview);
        mFragmentsContainer = (LinearLayout) findViewById(R.id.fragment_container);
        mViewQueueButton = (Button) findViewById(R.id.button_view_queue);
    }

    private void setClickListeners() {
        for (int i = 0; i < (mRootView).getChildCount(); ++i) {
            View nextChild = (mRootView).getChildAt(i);
            if (nextChild instanceof Button) {
                nextChild.setOnClickListener(this);
            }
        }

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }

    private void setInitalFragment(Bundle savedInstanceState) {
        if (mFragmentsContainer != null) {
            QueueListFragment qListFragment = new QueueListFragment();
            if (savedInstanceState != null) {
                return;
            }
            qListFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, qListFragment).commit();
        }
    }

    public void switchFragment(Fragment fragment) {
        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(mFragmentsContainer.getId(), fragment);
        transaction.addToBackStack(null);
        transaction.commit();
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
        switch (view.getId()) {
            case R.id.button_view_queue:
                QueueListFragment listFragment = new QueueListFragment();
                switchFragment(listFragment);
                break;
            case R.id.button_add_customer:
                LoginFragment loginFragment = new LoginFragment();
                switchFragment(loginFragment);
                break;
            case R.id.button_remove_customer:
                // TODO - implement remove customer from queue and list
                break;
            case R.id.button_refresh_queue:
                // TODO - refresh recyclerview
                break;
        }
    }

    @Override
    public void onQueueListFragmentInteraction(Uri uri) {

    }

    @Override
    public void onRegisterFragmentInteraction(Uri uri) {

    }


    @Override
    public void onLoginFragmentInteraction(boolean response) {
        if (response) {
//            RegisterFragment registerFragment = new RegisterFragment();
//            FragmentTransaction transaction = getFragmentManager().beginTransaction();
//
//            transaction.replace(R.id.fragment_container, registerFragment);
//            transaction.addToBackStack(null);
//            transaction.commit();
//        }

        }
    }
}

