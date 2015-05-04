package com.group15.shoppingwithfriends.MainWelcome;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.FragmentActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;
import com.group15.shoppingwithfriends.Database.DataSource;
import com.group15.shoppingwithfriends.Database.User;
import com.group15.shoppingwithfriends.ExtraCredit.Tutorial.TutorialActivity;
import com.group15.shoppingwithfriends.LoginRegistration.LoginActivity;
import com.group15.shoppingwithfriends.LoginRegistration.MainFragment;
import com.group15.shoppingwithfriends.LoginRegistration.RegistrationActivity;
import com.group15.shoppingwithfriends.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class WelcomeScreen extends FragmentActivity implements View.OnClickListener,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {


    private boolean isMusicPlaying;
    private MainFragment mainFragment;
    private GoogleApiClient googleApiClient;
    private boolean mSignInClicked;
    private DataSource dataSource;

    /**
     * True if we are in the process of resolving a ConnectionResult
     */
    private boolean mIntentInProgress;
    private static final int RC_SIGN_IN = 0;

    public void printHashKey() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.group15.shoppingwithfriends",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataSource = new DataSource(this);
        setContentView(R.layout.activity_welcome_screen);
//        doBindService();
//        Intent music = new Intent();
//        music.setClass(this,MusicService.class);
//        startService(music);
        View musicButton = findViewById(R.id.music_button);
        musicButton.setOnClickListener(this);
        startService(new Intent(getBaseContext(), MusicService.class));
        isMusicPlaying = true;
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addScope(Plus.SCOPE_PLUS_LOGIN)
                .build();

      //  Fragment fragment = new WelcomeFragment();
       // fragment.setArguments(savedInstanceState);
       // fragment.getFragmentManager().beginTransaction().add(R.id.FragmentContainer, fragment).commit();


    }

    /**
     * handles when you click music buttton - should toggle between pause
     * and resume
     *
     * @param v view
     */
    public void onClickMute(View v) {
        if (v.getId() == R.id.music_button)
            if (isMusicPlaying) stopService(new Intent(getBaseContext(),
                    MusicService.class));
            else startService(new Intent(getBaseContext(), MusicService.class));
        isMusicPlaying = !isMusicPlaying;
    }

    @Override
    /**
     * inflates menu and adds item to action bar if its present
     * @param menu
     * @return true if successful
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        googleApiClient.connect();
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_welcome_screen, menu);
        return true;
    }

    @Override
    /**
     * Handle action bar item clicks here
     * @param item selected item
     * @return a boolean if that item is clicked
     */
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

    /**
     * this method directs you to the login page when you click login
     * button from the welcome screen
     *
     * @param view
     */
    public void goToLogin(View view) {
        //setContentView(R.layout.activity_login);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * this method directs you to registration activity when clicking register button
     *
     * @param view
     */
    public void goToRegister(View view) {
        //setContentView(R.layout.activity_registration);
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }

    /**
     * links to tutorial page
     * @param view
     */
    public void goToTutorial(View view) {
        startActivity(new Intent(this, TutorialActivity.class));
    }


    private boolean mIsBound = false;
    public MusicService mServ;
    public ServiceConnection Scon = new ServiceConnection() {
        /**
         * this deals with the music service being connected
         * @param name comp name
         * @param binder music binder
         */
        public void onServiceConnected(ComponentName name, IBinder
                binder) {
            mServ = ((MusicService.ServiceBinder) binder).getService();
        }

        /**
         * this deals with music service being disconnected
         * @param name comp. name
         */
        public void onServiceDisconnected(ComponentName name) {
            mServ = null;
        }
    };


    void doBindService() {
        bindService(new Intent(this, MusicService.class),
                Scon, Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    void doUnbindService() {
        if (mIsBound) {
            unbindService(Scon);
            mIsBound = false;
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        if (!mIntentInProgress) {
            if (mSignInClicked && result.hasResolution()) {
                // The user has already clicked 'sign-in' so we attempt to resolve all
                // errors until the user is signed in, or they cancel.
                try {
                    result.startResolutionForResult(this, RC_SIGN_IN);
                    mIntentInProgress = true;
                } catch (IntentSender.SendIntentException e) {
                    // The intent was canceled before it was sent.  Return to the default
                    // state and attempt to connect to get an updated ConnectionResult.
                    mIntentInProgress = false;
                    googleApiClient.connect();
                }
            }
        }
    }
    @Override
    public void onConnected(Bundle connectionHint) {
        mSignInClicked = false;
        Toast.makeText(this, "User is connected!", Toast.LENGTH_LONG).show();
    }

    public void onConnectionSuspended(int cause) {
        googleApiClient.connect();
    }
    public void onClick(View view) {
        if (view.getId() == R.id.google_login && !googleApiClient.isConnecting()) {
            mSignInClicked = true;
            googleApiClient.connect();
        }
    }














    public void onActivityResult(int requestCode, int responseCode, Intent intent) {
        if (requestCode == RC_SIGN_IN) {
            dataSource.open();
            if (dataSource.userExists("annamcabee")) {
                User user = new User();
                user.setUsername("annamcabee");
                user.setName("Anna");
                user.setEmail("annamcabee@gmail.com");
                User.setCurrentUser(user);
            } else {
                User.setCurrentUser(dataSource.createUser("annamcabee", "Anna", "annamcabee@gmail.com"));
            }
            dataSource.close();
            startActivity(new Intent(this, MainActivity.class));

            if (responseCode != RESULT_OK) {
                mSignInClicked = false;
            }

            mIntentInProgress = false;

            if (!googleApiClient.isConnected()) {
                googleApiClient.reconnect();
            }
        } else {
            dataSource.open();

            User.setCurrentUser(dataSource.createUser("annamcabee", "Anna", "annamcabee@gmail.com"));
            dataSource.close();
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}