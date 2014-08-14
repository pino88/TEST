package it.dsergio.android.test.activity;

import it.dsergio.android.test.fragment.MenuFragment;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import it.dsergio.android.test.conf.Const;

import it.dsergio.android.test.R;
import it.dsergio.android.test.model.UserModel;


public class MenuActivity extends FragmentActivity implements MenuFragment.MenuFragmentListener  {
    private static final String MENU_FRAGMENT_TAG = Const.PKG + ".tag.MENU_FRAGMENT_TAG";
    public static final String USER_EXTRA = "it.dsergio.android.test.extra.USER_EXTRA";
    private static final String TAG_LOG = MenuActivity.class.getName();
    private UserModel mUserModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fragment);
        // We get the UserModel
        this.mUserModel = (UserModel) getIntent().getParcelableExtra(USER_EXTRA);
        if (mUserModel == null) {
            Log.w(TAG_LOG, USER_EXTRA + " is mandatory!");
            finish();
        }
        if (savedInstanceState == null) {
            final MenuFragment fragment = new MenuFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.anchor_point, fragment, MENU_FRAGMENT_TAG).commit();
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

    @Override
    public void insertNewData() {
        Log.d(TAG_LOG, "We choose to insert new data");
        final Intent newDataIntent = new Intent(this, NewDataActivity.class);
        newDataIntent.putExtra(NewDataActivity.USER_EXTRA, mUserModel);
        startActivity(newDataIntent);
    }

    @Override
    public void viewOldData() {
        Log.d(TAG_LOG, "We choose to view our old data");
        final Intent localDataIntent = new Intent(this, LocalDataActivity.class);
        startActivity(localDataIntent);
    }

    @Override
    public void viewRemoteData() {
        Log.d(TAG_LOG, "We choose to view remote data");
        final Intent remoteDataIntent = new Intent(this, RemoteDataActivity.class);
        startActivity(remoteDataIntent);
    }
    @Override
    public String pritInfo(){
        if (mUserModel != null){
            return mUserModel.toString();
        }
        return "user non definito";
    }
}
