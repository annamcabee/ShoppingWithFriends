package com.group15.shoppingwithfriends.MainWelcome;

/**
 * Created by Kasey on 2/15/2015.
 */

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import com.group15.shoppingwithfriends.R;


public class MusicService extends Service implements MediaPlayer.OnErrorListener {

    private final IBinder mBinder = new ServiceBinder();
    MediaPlayer mPlayer;
    private int length = 0;

    /**
     * constructor
     */
    public MusicService() {
    }
    public class ServiceBinder extends Binder {
        MusicService getService() {
            return MusicService.this;
        }
    }

    /**
     * gets music binder
     *
     * @param arg0
     * @return music binder
     */
    @Override
    public IBinder onBind(Intent arg0) {
        return mBinder;
    }

    @Override
    /**
     * handles what all happens when you create music service
     */
    public void onCreate() {
        super.onCreate();

        mPlayer = MediaPlayer.create(this, R.raw.background);
        mPlayer.setOnErrorListener(this);

        if (mPlayer != null) {
            mPlayer.setLooping(true);
            mPlayer.setVolume(10, 10);
        }
        //TODO change volume back to 100,100


//        mPlayer.setOnErrorListener(new OnErrorListener() {
//
//            public boolean onError(MediaPlayer mp, int what, int
//                    extra) {
//
//                onError(mPlayer, what, extra);
//                return true;
//            }
//        });
    }

    @Override
    /** handles when you start music player
     * @param intent
     * @param flags
     * @param startId
     * @return int
     */
    public int onStartCommand(Intent intent, int flags, int startId) {
        mPlayer.start();
        return START_STICKY;
    }

    /**
     * pauses music
     */
    public void pauseMusic() {
        if (mPlayer.isPlaying()) {
            mPlayer.pause();
            length = mPlayer.getCurrentPosition();

        }
    }

    /**
     * resumes music
     */
    public void resumeMusic() {
        if (mPlayer.isPlaying() == false) {
            mPlayer.seekTo(length);
            mPlayer.start();
        }
    }

    /**
     * stops music
     */
    public void stopMusic() {
        mPlayer.stop();
        mPlayer.release();
        mPlayer = null;
    }

    @Override
    /**
     * destroys music player
     */
    public void onDestroy() {
        super.onDestroy();
        if (mPlayer != null) {
            try {
                mPlayer.stop();
                mPlayer.release();
            } finally {
                mPlayer = null;
            }
        }
    }

    /**
     * handles when the music player failed
     *
     * @param mp
     * @param what
     * @param extra
     * @return
     */
    public boolean onError(MediaPlayer mp, int what, int extra) {

        Toast.makeText(this, "music player failed", Toast.LENGTH_SHORT).show();
        if (mPlayer != null) {
            try {
                mPlayer.stop();
                mPlayer.release();
            } finally {
                mPlayer = null;
            }
        }
        return false;
    }
}