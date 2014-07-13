package it.dsergio.android.test;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import it.dsergio.android.test.model.UserModel;


public class MenuActivity extends ActionBarActivity {
    public static final String USER_EXTRA = "it.dsergio.android.test.extra.USER_EXTRA";
    private static final String TAG_LOG = MenuActivity.class.getName();
    private UserModel mUserModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);
        final TextView textView = (TextView) findViewById(R.id.welcome_user);
        this.mUserModel = (UserModel) getIntent().getParcelableExtra(USER_EXTRA);
        if (mUserModel == null) {
            Log.w(TAG_LOG, USER_EXTRA + " is mandatory!");
            finish();
        }else{
            textView.setText(mUserModel.toString());
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
