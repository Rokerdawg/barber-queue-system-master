package com.barber.shop.queue.system.LoginRegister.view;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.barber.shop.queue.system.BarberQueueApplication;
import com.barber.shop.queue.system.LoginRegister.interfaces.ILoginView;
import com.barber.shop.queue.system.model.Customer;
import com.barber.shop.queue.system.views.activity.MainActivity;
import com.barber.shop.queue.system.views.fragment.SocialMediaFragment;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.queue.shop.barber.barbershopqueuesystem.R;

import static android.support.v7.content.res.AppCompatResources.getDrawable;


/**
 * Handles the login fragment Ui and user interaction
 */
public class LoginView extends Fragment implements ILoginView.LoginViewStuff,
        SocialMediaFragment.SocialMediaListener, View.OnClickListener{

    private static final String TAG = LoginView.class.getSimpleName();
    ImageView mLogo;
    EditText mEmailLogin, mPasswordLogin;
    Button mSubmitLoginButton;
    Button mMoveToRegsiter;
    Communicator mListener;
    public static final String ARG_PAGE = "ARG_PAGE";
    String email = "";
    String password ="";
    ProgressDialog progressDialog;
    SignInButton googleSignIn;
    private final static int RC_SIGN_IN = 1;
    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth mAuth;

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
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        initViews(v);
        clickListener();
        googleSignIn();
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
        googleSignIn = (SignInButton)v.findViewById(R.id.btnGplus);
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

    void googleSignIn(){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(BarberQueueApplication.getInstance()).enableAutoManage(
                getActivity(), new GoogleApiClient.OnConnectionFailedListener(){

                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Toast.makeText(getActivity(), "You got an error", Toast.LENGTH_SHORT).show();
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        googleSignIn.setOnClickListener(this);
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
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
        switch (v.getId()){
            case R.id.button_login:
            showProgressBar();
            email = mEmailLogin.getText().toString();
            password = mPasswordLogin.getText().toString();
            Customer user = new Customer();
            user.setEmailAddress(email);
            user.setPassword(password);
            mListener.onLoginFragmentInteraction(user);
                break;
            case R.id.btnGplus:
                signIn();

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
                // Google Sign In failed, update UI appropriately
                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());
                        getActivity().startActivity(new Intent(getActivity(), UserLoggedIn.class));

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());
//                            Toast.makeText(GoogleSignInActivity.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
                        }
                        // ...
                    }
                });
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

        // Set the custom drawable for progress bar
        progressDialog.setProgressDrawable(getDrawable(getContext(),R.drawable.progress_bar_states));

        // Change the background color of progress dialog
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        progressDialog.show();

        TextView tv1 = (TextView) progressDialog.findViewById(android.R.id.message);
        tv1.setTextSize(20);
//        tv1.setTypeface(yourCustomTF);
        tv1.setTextColor(Color.WHITE);
        tv1.setText("your msg");
    }

    @Override
    public void hideProgressBar() {
        progressDialog.dismiss();
    }

    public interface Communicator {
        void onLoginFragmentInteraction(Customer customer);
    }
}
