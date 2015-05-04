package com.group15.shoppingwithfriends.LoginRegistration;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.group15.shoppingwithfriends.R;


/**
 * Created by annamcabee on 2/23/15.
 */
@SuppressLint("NewApi")
public class MainFragment extends Fragment {
    /**
     * main fragment on create view
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_login, container, false);
        return view;
    }
}
