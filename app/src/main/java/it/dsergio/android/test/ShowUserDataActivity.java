package it.dsergio.android.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

import it.dsergio.android.test.model.UserModel;
import it.dsergio.android.test.service.LoginService;

public class ShowUserDataActivity extends Activity {

    private static final String TAG_LOG = ShowUserDataActivity.class.getName();
    public static final String SHOW_USER_ACTION = "it.dsergio.android.test.action.SHOW_USER_ACTION";
    public static final String USER_EXTRA = "it.dsergio.android.test.extra.USER_EXTRA";

    private UserModel mUserModel;
    private TextView mUsername;
    private TextView mEmail;
    private TextView mBirthDate;
    private TextView mLocation;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_user_data_activity);
        this.mUserModel = (UserModel) getIntent().getParcelableExtra(USER_EXTRA);
        if (mUserModel == null) {
            Log.w(TAG_LOG, USER_EXTRA + " is mandatory!");
            finish();
        }
        mUsername = (TextView) findViewById(R.id.username_value);
        mEmail = (TextView) findViewById(R.id.email_value);
        mBirthDate = (TextView) findViewById(R.id.birth_date_value);
        mLocation = (TextView) findViewById(R.id.location_value);
    }


    @Override
    protected void onResume() {
        super.onResume();
        final String userName = mUserModel.getUsername();
        mUsername.setText(userName);
        final String email = mUserModel.getEmail();
        if (!TextUtils.isEmpty(email)) {
            mEmail.setText(email);
        } else {
            mEmail.setText("-");
        }
        final DateFormat format = DateFormat.getDateInstance(DateFormat.MEDIUM);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(mUserModel.getBirthDate());
        final String birthDate = format.format(calendar.getTime());
        mBirthDate.setText(birthDate);
        final String location = mUserModel.getLocation();
        if (!TextUtils.isEmpty(location)) {
            mLocation.setText(location);
        } else {
            mLocation.setText("-");
        }
    }

    public void doConfirm(View loginButton) {
        final Intent mainIntent = new Intent(this, MenuActivity.class);
        mainIntent.putExtra(MenuActivity.USER_EXTRA, mUserModel);
        startActivity(mainIntent);
        finish();
    }
}
