package it.dsergio.android.test;

import android.app.Activity;
import android.os.Bundle;

public class RemoteDataActivity extends Activity {

    private static final String TAG_LOG = RemoteDataActivity.class.getName();

    public static final String INPUT_TYPE_EXTRA = "it.dsergio.android.test.extra.INPUT_TYPE_EXTRA";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_data);
    }


}