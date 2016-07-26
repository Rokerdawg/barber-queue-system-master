package com.barber.shop.queue.system.views.fragment;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.queue.shop.barber.barbershopqueuesystem.R;

/**
 * Handles the register fragment Ui and user interaction
 */
public class RegisterFragment extends Fragment {

    ImageView mLogo;
    EditText mNameInput, mPhoneInput, mConfirmPhoneInput, mEmailInput, mPasswordInput;
    Button mSubmitButton;
    TextView mLoginLink;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnRegisterFragmentInteractionListener mListener;

    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_register, container, false);
        initViews(v);
        return v;

    }

    public void initViews(View v){
        mLogo = (ImageView)v.findViewById(R.id.logo);
        mNameInput = (EditText)v.findViewById(R.id.input_name);
        mPhoneInput = (EditText)v.findViewById(R.id.input_telephone);
        mConfirmPhoneInput = (EditText)v.findViewById(R.id.input_confirm_telephone);
        mEmailInput = (EditText)v.findViewById(R.id.input_email);
        mPasswordInput = (EditText)v.findViewById(R.id.input_password);
        mSubmitButton = (Button) v.findViewById(R.id.submit_register_button);
        mLoginLink = (TextView)v.findViewById(R.id.link_login);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onRegisterFragmentInteraction(uri);
        }
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
        void onRegisterFragmentInteraction(Uri uri);
    }
}
