package it.dsergio.android.test.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import it.dsergio.android.test.R;
import it.dsergio.android.test.fragment.FirstAccessFragment;
import it.dsergio.android.test.model.UserModel;


public class FirstAccessActivity extends FragmentActivity implements FirstAccessFragment.FirstAccessListener{
    private static final String TAG_LOG = SplashActivity.class.getSimpleName();
    public static final int LOGIN_REQUEST_ID = 1;
    public static final int REGISTRATION_REQUEST_ID = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fragment);
        if (savedInstanceState == null) {
            final FirstAccessFragment fragment = new FirstAccessFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.anchor_point, fragment).commit();
        }
    }
    public void enterAsAnonymous() {
        Log.d(TAG_LOG, "Anonymous access");
        final Intent anonymousIntent = new Intent(this, MenuActivity.class);
        final UserModel userModel = UserModel.create(System.currentTimeMillis());
        anonymousIntent.putExtra(MenuActivity.USER_EXTRA, userModel);
        startActivity(anonymousIntent);
    }

    public void doLogin() {
        final Intent loginIntent = new Intent(LoginActivity.LOGIN_ACTION);
        startActivityForResult(loginIntent, LOGIN_REQUEST_ID);
    }
    public void doRegistration(){
        final Intent registrationIntent = new Intent(RegisterActivity.REGISTRATION_ACTION);
        startActivityForResult(registrationIntent,REGISTRATION_REQUEST_ID);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == LOGIN_REQUEST_ID) {
            switch (resultCode) {
                case RESULT_OK:
                    final UserModel userModel = (UserModel)
                            data.getParcelableExtra(LoginActivity.USER_DATA_EXTRA);
                    final Intent mainIntent = new Intent(this, MenuActivity.class);
                    mainIntent.putExtra(MenuActivity.USER_EXTRA, userModel);
                    startActivity(mainIntent);
                    finish();
                    break;
                case RESULT_CANCELED:
                    break;
            }
        } else if (requestCode == REGISTRATION_REQUEST_ID) {
            switch (resultCode) {
                case RESULT_OK:
                    final UserModel userModel = (UserModel) data.getParcelableExtra
                            (RegisterActivity.USER_DATA_EXTRA);
                    final Intent detailIntent = new Intent(ShowUserDataActivity.SHOW_USER_ACTION);
                    detailIntent.putExtra(ShowUserDataActivity.USER_EXTRA, userModel);
                    startActivity(detailIntent);
                    finish();
                    break;
                case RESULT_CANCELED:
                    break;
            }
        }
    }
}