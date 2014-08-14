package it.dsergio.android.test.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import it.dsergio.android.test.fragment.LocalDataFragment;
import it.dsergio.android.test.R;


public class LocalDataActivity extends FragmentActivity {

    /**
     * The Tag of the Log for this class
     */
    private static final String TAG_LOG = LocalDataActivity.class.getName();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fragment);
        // We add the Fragment if not already done
        if (savedInstanceState == null) {
            final LocalDataFragment fragment = new LocalDataFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.anchor_point, fragment).commit();
        }
    }


}