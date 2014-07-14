package it.dsergio.android.test;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


public class LocalDataActivity extends Activity {


    private static final String TAG_LOG = LocalDataActivity.class.getName();

    public static final String INPUT_TYPE_EXTRA = "it.dsergio.android.test.extra.INPUT_TYPE_EXTRA";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_data);
    }


}