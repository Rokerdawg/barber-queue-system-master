package com.barber.shop.queue.system.LoginRegister.view;


import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.barber.shop.queue.system.LoginRegister.interfaces.ILoginView;
import com.barber.shop.queue.system.model.Customer;
import com.barber.shop.queue.system.views.activity.MainActivity;
import com.barber.shop.queue.system.views.fragment.SocialMediaFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.queue.shop.barber.barbershopqueuesystem.R;

/**
 * Handles the login fragment Ui and user interaction
 */
public class LoginView extends Fragment implements ILoginView.LoginViewStuff,
        SocialMediaFragment.SocialMediaListener, View.OnClickListener{

    ImageView mLogo;
    EditText mEmailLogin, mPasswordLogin;
    Button mSubmitLoginButton;
    Button mMoveToRegsiter;
    Communicator mListener;
    public static final String ARG_PAGE = "ARG_PAGE";
    String email = "";
    String password ="";
    ProgressDialog progressDialog;

    public LoginView() {
        // Required empty public constructor
    }

    public static LoginView newInstance(int page, String title) {
        LoginView fragment = new LoginView();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        args.putString("someTitle", title);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        initViews(v);
        clickListener();
        return v;
    }

    private void clickListener() {
        mSubmitLoginButton.setOnClickListener(this);
    }

//    private void insertSocialMediaFragment(View v) {
//        Fragment videoFragment = new SocialMediaFragment();
//        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//        transaction.add(R.id.loginSocialMediaFragment, videoFragment).commit();
//    }

    private void initViews(View v) {
//        mLogo = (ImageView)v.findViewById(R.id.login_logo);
        mEmailLogin = (AppCompatEditText)v.findViewById(R.id.login_email_input);
        mPasswordLogin = (AppCompatEditText)v.findViewById(R.id.login_password_input);
        mSubmitLoginButton = (Button)v.findViewById(R.id.button_login);
        mMoveToRegsiter = (Button) v.findViewById(R.id.button_register);
        mMoveToRegsiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterView registerFragment = new RegisterView();
                ((MainActivity)getActivity()).switchFragment(registerFragment);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof Communicator){
            mListener = (Communicator)context;
        }
        else{
            throw new RuntimeException(context.toString()
                    + " must implement Communicator");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_login){
            showProgressBar();
            email = mEmailLogin.getText().toString();
            password = mPasswordLogin.getText().toString();
            Customer user = new Customer();
            user.setEmailAddress(email);
            user.setPassword(password);
            mListener.onLoginFragmentInteraction(user);

        }
    }

    @Override
    public void onLoginBySocialMediaListener(Uri uri) {

    }

    @Override
    public FirebaseAuth getAuth() {
        return null;
    }

    @Override
    public void showProgressBar() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Logging in...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void hideProgressBar() {
        progressDialog.dismiss();
    }

    public interface Communicator {
        void onLoginFragmentInteraction(Customer customer);
    }
}
