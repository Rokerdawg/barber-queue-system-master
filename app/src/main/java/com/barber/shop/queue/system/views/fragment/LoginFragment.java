package com.barber.shop.queue.system.views.fragment;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.barber.shop.queue.system.views.activity.MainActivity;
import com.queue.shop.barber.barbershopqueuesystem.R;

/**
 * Handles the login fragment Ui and user interaction
 */
public class LoginFragment extends Fragment {

    ImageView mLogo;
    EditText mEmailLogin, mPasswordLogin;
    Button mSubmitLoginButton;
    Button mMoveToRegsiter;
    private OnLoginFragmentInteractionListener mListener;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        initViews(v);
        return v;
    }

    private void initViews(View v) {
        mLogo = (ImageView)v.findViewById(R.id.login_logo);
        mEmailLogin = (EditText)v.findViewById(R.id.login_email_input);
        mPasswordLogin = (EditText)v.findViewById(R.id.login_password_input);
        mSubmitLoginButton = (Button)v.findViewById(R.id.button_login);
        mMoveToRegsiter = (Button) v.findViewById(R.id.button_register);
        mMoveToRegsiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterFragment registerFragment = new RegisterFragment();
                ((MainActivity)getActivity()).switchFragment(registerFragment);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnLoginFragmentInteractionListener){
            mListener = (OnLoginFragmentInteractionListener)context;
        }
        else{
            throw new RuntimeException(context.toString()
                    + " must implement OnLoginFragmentInteractionListener");
        }
    }

    public interface OnLoginFragmentInteractionListener {
        void onLoginFragmentInteraction(boolean response);
    }
}
