package it.dsergio.android.test;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import it.dsergio.android.test.R;
import it.dsergio.android.test.model.UserModel;
import it.dsergio.android.test.service.LoginService;

public class LoginActivity extends ActionBarActivity {

    //public static final Actio LOGIN_ACTION = 1;
    public static final String USER_DATA_EXTRA="userLogged";
    private TextView mErrorTextView;
    private EditText mUsernameEditText;
    private EditText mPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        this.mErrorTextView=(TextView) findViewById(R.id.error_message_label);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
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
