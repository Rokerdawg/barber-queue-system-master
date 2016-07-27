package com.barber.shop.queue.system.views.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
    public static final String ARG_PAGE = "ARG_PAGE";

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance(int page, String title) {
        LoginFragment fragment = new LoginFragment();
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
