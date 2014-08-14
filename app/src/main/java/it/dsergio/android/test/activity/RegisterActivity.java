package it.dsergio.android.test.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Calendar;

import it.dsergio.android.test.R;
import it.dsergio.android.test.model.UserModel;
import it.dsergio.android.test.service.RegistrationService;

public class RegisterActivity extends ActionBarActivity {
    public static final String REGISTRATION_ACTION = "it.dsergio.android.test.action.REGISTRATION_ACTION";
    public static final String USER_DATA_EXTRA="it.dsergio.android.test.extra.USER_DATA_EXTRA";
    private TextView mErrorTextView;
    private EditText mUsernameEditText;
    private EditText mPasswordEditText;
    private EditText mLocationEditText;
    private EditText mEmailEditText;
    private DatePicker mBirthdateDatePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        this.mErrorTextView = (TextView) findViewById(R.id.error_message_label);
        this.mUsernameEditText =(EditText) findViewById(R.id.username_edittext);
        this.mPasswordEditText = (EditText) findViewById(R.id.password_edittext);
        this.mBirthdateDatePicker = (DatePicker) findViewById(R.id.birthdate_datapicker);
        this.mLocationEditText =(EditText) findViewById(R.id.location_edittext);
        this.mEmailEditText = (EditText) findViewById(R.id.email_edittext);
    }

    public void doRegistration(View loginButton) {
        this.mErrorTextView.setVisibility(View.INVISIBLE);
        final Editable usernameEdit = mUsernameEditText.getText();
        if (TextUtils.isEmpty(usernameEdit)) {
            final String usernameMandatory = getResources()
                    .getString(R.string.mandatory_field_error, "username");
            this.mErrorTextView.setText(usernameMandatory);
            this.mErrorTextView.setVisibility(View.VISIBLE);
            return;
        }
        final Editable passwordEdit = mPasswordEditText.getText();
        if (TextUtils.isEmpty(passwordEdit)) {
            final String passwordMandatory = getResources()
                    .getString(R.string.mandatory_field_error, "password");
            this.mErrorTextView.setText(passwordMandatory);
            this.mErrorTextView.setVisibility(View.VISIBLE);
            return;
        }
        final Editable locationEdit = mLocationEditText.getText();
        if (TextUtils.isEmpty(locationEdit)) {
            final String usernameMandatory = getResources()
                    .getString(R.string.mandatory_field_error, "locationEdit");
            this.mErrorTextView.setText(usernameMandatory);
            this.mErrorTextView.setVisibility(View.VISIBLE);
            return;
        }
        final Editable emailEdit = mEmailEditText.getText();
        if (TextUtils.isEmpty(emailEdit)) {
            final String usernameMandatory = getResources()
                    .getString(R.string.mandatory_field_error, "email");
            this.mErrorTextView.setText(usernameMandatory);
            this.mErrorTextView.setVisibility(View.VISIBLE);
            return;
        }

        final String username = usernameEdit.toString();
        final String password = passwordEdit.toString();
        final String email = emailEdit.toString();
        final String location = locationEdit.toString();

        int dayOfMonth = mBirthdateDatePicker.getDayOfMonth();
        int month = mBirthdateDatePicker.getMonth();
        int year = mBirthdateDatePicker.getYear();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        final long birthDate = cal.getTimeInMillis();

        final UserModel userModel = RegistrationService.get().register(username,password,email,birthDate,location);
        Intent resultIntent = new Intent();
        resultIntent.putExtra(USER_DATA_EXTRA, userModel);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
