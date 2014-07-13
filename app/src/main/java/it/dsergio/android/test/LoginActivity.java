package it.dsergio.android.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import it.dsergio.android.test.model.UserModel;
import it.dsergio.android.test.service.LoginService;

public class LoginActivity extends Activity {

    public static final String LOGIN_ACTION = "it.dsergio.android.test.action.LOGIN_ACTION";
    public static final String USER_DATA_EXTRA="userLogged";
    private TextView mErrorTextView;
    private EditText mUsernameEditText;
    private EditText mPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        this.mErrorTextView = (TextView) findViewById(R.id.error_message_label);
        this.mUsernameEditText =(EditText) findViewById(R.id.username_edittext);
        this.mPasswordEditText = (EditText) findViewById(R.id.password_edittext);
    }

    public void doLogin(View loginButton) {
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
        final String username = usernameEdit.toString();
        final String password = passwordEdit.toString();
        final UserModel userModel = LoginService.get().login(username, password);
        if (userModel != null) {
            Intent resultIntent = new Intent();
            resultIntent.putExtra(USER_DATA_EXTRA, userModel);
            setResult(RESULT_OK, resultIntent);
            finish();
        } else {
            this.mErrorTextView.setText(R.string.wrong_credential_error);
            this.mErrorTextView.setVisibility(View.VISIBLE);
        }
    }


}