package com.group15.shoppingwithfriends.MainWelcome;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.group15.shoppingwithfriends.Database.DataSource;
import com.group15.shoppingwithfriends.Database.User;
import com.group15.shoppingwithfriends.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by annamcabee on 4/21/15.
 */
public class WelcomeFragment extends Fragment {
    private TextView mTextDetails;
    private LoginButton loginButton;
    private CallbackManager callbackManager;

    private DataSource dataSource;
    private FacebookCallback<LoginResult> mCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {

            dataSource.open();
            AccessToken accessToken = AccessToken.getCurrentAccessToken();
            if (accessToken != null)  {
                startActivity(new Intent(getActivity(), MainActivity.class));
            }

            Profile profile =  Profile.getCurrentProfile();
            if (profile != null) {
                displayWelcomeMessage(profile);
                startActivity(new Intent(getActivity(), MainActivity.class));
                if (dataSource.userExists(profile.getFirstName() + profile.getLastName())) {

                } else {
                    GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                            new GraphRequest.GraphJSONObjectCallback() {
                                @Override
                                public void onCompleted(
                                        JSONObject object,
                                        GraphResponse response) {
                                    // Application code
                                    Log.v("LoginActivity", response.toString());
                                }
                            });
                    Bundle parameters = new Bundle();
                    parameters.putString("fields", "id,name,email");
                    request.setParameters(parameters);
                    request.executeAsync();
                    System.out.println(parameters);
                    String username = profile.getFirstName() + profile.getLastName();
                    String name = profile.getName();


                }
            }
        }

        @Override
        public void onCancel() {

            dataSource.close();
            Log.v("LoginActivity", "cancel");
        }

        @Override
        public void onError(FacebookException e) {

            dataSource.close();
            Log.v("LoginActivity", e.getCause().toString());

        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        System.out.println("annanananananannanan");

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {

                dataSource.open();
                AccessToken accessToken = AccessToken.getCurrentAccessToken();
                if (accessToken != null)  {
                    startActivity(new Intent(getActivity(), MainActivity.class));
                }

                Profile profile =  Profile.getCurrentProfile();
                if (profile != null) {
                    displayWelcomeMessage(profile);
                    startActivity(new Intent(getActivity(), MainActivity.class));
                    if (dataSource.userExists(profile.getFirstName() + profile.getLastName())) {

                    } else {
                        GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(
                                            JSONObject object,
                                            GraphResponse response) {
                                        // Application code
                                        Log.v("LoginActivity", response.toString());
                                    }
                                });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,name,email");
                        request.setParameters(parameters);
                        request.executeAsync();
                        System.out.println(parameters);
                        String username = profile.getFirstName() + profile.getLastName();
                        String name = profile.getName();


                    }
                }
            }

            @Override
            public void onCancel() {

                dataSource.close();
                Log.v("LoginActivity", "cancel");
            }

            @Override
            public void onError(FacebookException e) {

                dataSource.close();
                Log.v("LoginActivity", e.getCause().toString());

            }
        });

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_welcome, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loginButton = (LoginButton) view.findViewById(R.id.facebook_login_button);

        List<String> permissions = new ArrayList<>();
        permissions.add("user_friends");
        permissions.add("email");
        loginButton.setReadPermissions(permissions);
        // If using in a fragment
        loginButton.setFragment(this);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                dataSource.open();
                AccessToken accessToken = AccessToken.getCurrentAccessToken();
                if (accessToken != null)  {
                    startActivity(new Intent(getActivity(), MainActivity.class));
                }

                Profile profile =  Profile.getCurrentProfile();
                if (profile != null) {
                    displayWelcomeMessage(profile);
                    startActivity(new Intent(getActivity(), MainActivity.class));
                    if (dataSource.userExists(profile.getFirstName() + profile.getLastName())) {
                        User user = new User();
                        user.setName(profile.getName());
                        user.setUsername(profile.getFirstName() + profile.getLastName());
                        User.setCurrentUser(user);
                    } else {
                        GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(
                                            JSONObject object,
                                            GraphResponse response) {
                                        // Application code
                                        Log.v("LoginActivity", response.toString());
                                    }
                                });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,name,email");
                        request.setParameters(parameters);
                        request.executeAsync();
                        String username = profile.getFirstName() + profile.getLastName();
                        String name = profile.getName();
                        dataSource.createUser(username, name, "a@gmail.com");
                    }
                }
            }

            @Override
            public void onCancel() {

                dataSource.close();
                Log.v("LoginActivity", "cancel");
            }

            @Override
            public void onError(FacebookException e) {

                dataSource.close();
                Log.v("LoginActivity", e.getCause().toString());

            }
        });

        System.out.println(";laskdjfals;kdfja;slkdjf;alskdfj");
        System.out.println(loginButton.getLoginBehavior().name());
    }
    public void displayWelcomeMessage(Profile profile) {
        if (profile != null) {
            mTextDetails.setText("Welcome " + profile.getFirstName());
        }
    }

    public void onResume() {
        super.onResume();
        Profile profile = Profile.getCurrentProfile();
        displayWelcomeMessage(profile);
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);
        if (callbackManager.onActivityResult(requestCode, resultCode, data)) {

            startActivity(new Intent(getActivity(), MainActivity.class));

        }

    }
}
