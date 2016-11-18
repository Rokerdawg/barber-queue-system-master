package com.barber.shop.queue.system.LoginRegister.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.barber.shop.queue.system.LoginRegister.interfaces.ILoginView;
import com.queue.shop.barber.barbershopqueuesystem.R;

import com.barber.shop.queue.system.model.Customer;

/**
 * Handles the register fragment Ui and user interaction
 */
public class RegisterView extends Fragment implements ILoginView.RegisterViewStuff, View.OnClickListener {

    ImageView mLogo;
    EditText mNameInput, mPhoneInput, mConfirmPhoneInput, mEmailInput, mPasswordInput, mAgeInput;
    Button mSubmitButton;
    TextView mLoginLink;
    public static final String ARG_PAGE = "ARG_PAGE";

    OnRegisterFragmentInteractionListener mListener;

    public RegisterView() {
        // Required empty public constructor
    }

    public static RegisterView newInstance(int page, String title) {
        RegisterView fragment = new RegisterView();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        args.putString("someTitle", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_register, container, false);
        initViews(v);
        return v;

    }

    public void initViews(View v) {
        mLogo = (ImageView) v.findViewById(R.id.login_logo);
        mNameInput = (EditText) v.findViewById(R.id.input_name);
        mAgeInput = (EditText) v.findViewById(R.id.confirm_email);
        mPhoneInput = (EditText) v.findViewById(R.id.input_telephone);
        mConfirmPhoneInput = (EditText) v.findViewById(R.id.input_confirm_telephone);
        mEmailInput = (EditText) v.findViewById(R.id.input_email);
        mPasswordInput = (EditText) v.findViewById(R.id.input_password);
        mSubmitButton = (Button) v.findViewById(R.id.submit_register_button);
        mLoginLink = (TextView) v.findViewById(R.id.button_switch_to_login);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnRegisterFragmentInteractionListener) {
            mListener = (OnRegisterFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnRegisterFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnRegisterFragmentInteractionListener {
        void onRegisterFragmentInteraction(Customer userRegistrationDetails);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.submit_register_button) {
//            ((LoginRegisterHost)getActivity()).sendUserRegistration(getUserData());

        }
    }

}
