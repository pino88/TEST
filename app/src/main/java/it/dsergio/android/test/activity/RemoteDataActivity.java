package it.dsergio.android.test.activity;

import android.app.Activity;
import android.os.Bundle;

import it.dsergio.android.test.R;
import android.support.v4.app.FragmentActivity;
import it.dsergio.android.test.fragment.RemoteDataFragment;
public class RemoteDataActivity extends FragmentActivity {

    /**
     * The Tag of the Log for this class
     */
    private static final String TAG_LOG = RemoteDataActivity.class.getName();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fragment);
        // We add the Fragment if not already done
        if (savedInstanceState == null) {
            final RemoteDataFragment fragment = new RemoteDataFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.anchor_point, fragment).commit();
        }
    }


}