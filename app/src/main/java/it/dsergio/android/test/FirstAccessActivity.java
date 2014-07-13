package it.dsergio.android.test;

        import android.app.Activity;
        import android.content.Context;
        import android.content.Intent;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.Toast;


public class FirstAccessActivity extends Activity {
    private static final String TAG_LOG = SplashActivity.class.getSimpleName();
    private static final int LOGIN_REQUEST_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_access_activity);
        final Button anonymousButton = (Button) findViewById(R.id.anonimus_button);
        anonymousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enterAsAnonymous();
            }
        });
        final Button registrationButton = (Button) findViewById(R.id.register_button);
        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // doRegistration();
                Context context = getApplicationContext();
                CharSequence text = "Registrazione non ancora implementata";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
        final Button loginButton = (Button) findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doLogin();
            }
        });
    }
    private void enterAsAnonymous() {
        Log.d(TAG_LOG, "Anonymous access");
        final Intent anonymousIntent = new Intent(this, MainActivity.class);
        startActivity(anonymousIntent);
    }

    private void doLogin() {
        final Intent loginIntent = new Intent(LoginActivity.LOGIN_ACTION);
        startActivityForResult(loginIntent, LOGIN_REQUEST_ID);
    }
}