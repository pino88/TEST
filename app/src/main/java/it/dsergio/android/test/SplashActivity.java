package it.dsergio.android.test;

import it.dsergio.android.test.util.SystemUiHider;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import java.lang.ref.WeakReference;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class SplashActivity extends Activity {

    private static final String IS_DONE_KEY = "it.dsergio.adroid.ugho.IS_DONE_KEY";
    private static final String START_TIME_KEY = "it.dsergio.adroid.ugho.START_TIME_KEY";
    private static final String TAG_LOG = SplashActivity.class.getSimpleName();
    private static final long MIN_WAIT_INTERVAL = 2000L;

    private static final long MAX_WAIT_INTERVAL = 10000L;

    private static final int GO_AHEAD_WHAT = 1;

    private long mStartTime = -1L;
    private boolean mIsDone;
    private UiHandler mHandler;
    private static class UiHandler extends Handler{
        private WeakReference<SplashActivity> mActivityRef;
        public UiHandler(final SplashActivity srcActivity){
            this.mActivityRef = new WeakReference<SplashActivity>(srcActivity);
        }
        @Override
        public void handleMessage(Message msg){
            final SplashActivity srcActivity = this.mActivityRef.get();
            if(srcActivity == null){
                return;
            }
            switch (msg.what){
                case GO_AHEAD_WHAT:
                    long elapsedTime = SystemClock.uptimeMillis() - srcActivity.mStartTime;
                    if(elapsedTime >= MIN_WAIT_INTERVAL && !srcActivity.mIsDone){
                        srcActivity.mIsDone = true;
                        srcActivity.goAhead();

                    }
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG_LOG,"ON CREATE");
        setContentView(R.layout.splash_activity);
        if(savedInstanceState != null){
            this.mStartTime = savedInstanceState.getLong(START_TIME_KEY);

        }
        mHandler=new UiHandler(this);
        final ImageView logoImageView = (ImageView) findViewById(R.id.splash_imageview);
        logoImageView.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent){
                long elapsedTime = SystemClock.uptimeMillis() - mStartTime;
                if(elapsedTime>= MIN_WAIT_INTERVAL && !mIsDone){
                    mIsDone = true;
                    goAhead();
                    Log.d(TAG_LOG, "ImageView touched!!");
                }else{
                    Context context = getApplicationContext();
                    CharSequence text = "Devi aspettare ancora:"+(MIN_WAIT_INTERVAL-elapsedTime)+"ms";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    Log.d(TAG_LOG, "Too much early!!");
                    Log.e(TAG_LOG, "ERRORE");
                }
                return false;
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG_LOG,"ON START");
        if(mStartTime == -1L){
            mStartTime = SystemClock.uptimeMillis();
        }
        final Message goAheadMessage = mHandler.obtainMessage(GO_AHEAD_WHAT);
        mHandler.sendMessageAtTime(goAheadMessage,mStartTime + MAX_WAIT_INTERVAL);
    }

    @Override
    protected void onResume(){
        Log.d(TAG_LOG,"ON RESUME");
        super.onResume();
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d(TAG_LOG,"ON PAUSE");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d(TAG_LOG,"ON STOP");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG_LOG,"ON DESTROY");
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d(TAG_LOG,"ON RESUME");
    }
    private void goAhead(){
        final Intent intent = new Intent(this, FirstAccessActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putBoolean(IS_DONE_KEY, mIsDone);
        outState.putLong(START_TIME_KEY, mStartTime);
        Log.d(TAG_LOG,"ON SAVE INSTANCE STATE");
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        this.mIsDone = savedInstanceState.getBoolean(IS_DONE_KEY);
        Log.d(TAG_LOG,"ON RESTORE INSTANCE STATE");
    }
}