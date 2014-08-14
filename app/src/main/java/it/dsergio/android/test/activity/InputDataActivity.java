package it.dsergio.android.test.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
import it.dsergio.android.test.R;
import it.dsergio.android.test.conf.Const;
import it.dsergio.android.test.fragment.InputDataFragment;
import it.dsergio.android.test.conf.Const;

import android.support.v4.app.FragmentActivity;


public class InputDataActivity extends FragmentActivity implements InputDataFragment.InputDataFragmentListener {

    /**
     * The Tag of the Log for this class
     */
    private static final String TAG_LOG = InputDataActivity.class.getName();

    /**
     * The name of the extra that will contain the type of question to do
     */
    public static final String INPUT_TYPE_EXTRA = Const.PKG + ".extra.INPUT_TYPE_EXTRA";

    /**
     * The the value the user insert.
     */
    public static final String INPUT_VALUE_EXTRA = Const.PKG + ".extra.INPUT_VALUE_EXTRA";

    /**
     * The TextView with the question
     */
    private TextView mQuestionTextView;

    /**
     * The category or questionType of the question we want answer to.
     */
    private String mQuestionType;

    /**
     * If true the layout should be split for tablet
     */
    private boolean mIsDouble;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        // We understand that it's single if into the layout there's an anchor named
        // R.id.right_anchor or not. If yes it's double
        mIsDouble = findViewById(R.id.right_anchor) != null;
        if (mIsDouble) {
            // In this case we have to finish the Activity so we return to the layout
            // with this fragment on the right
            finish();
        }
        // We read the type of the question
        mQuestionType = getIntent().getStringExtra(INPUT_TYPE_EXTRA);
        if (TextUtils.isEmpty(mQuestionType)) {
            Log.w(TAG_LOG, "The value for the extra " + INPUT_TYPE_EXTRA + " is mandatory");
            finish();
        }
        // if it's double we have to finish() this activity because we have to come back
        // to the fragment

        // We add the Fragment if not already done
        if (savedInstanceState == null) {
            final InputDataFragment fragment = InputDataFragment.getInputDataFragment(mQuestionType);
            getSupportFragmentManager().beginTransaction().add(R.id.anchor_point, fragment).commit();
        }

    }

    @Override
    public void valueForCategory(String category, long vote) {
        // We create the Intent to return
        final Intent returnIntent = new Intent();
        returnIntent.putExtra(INPUT_TYPE_EXTRA, mQuestionType);
        returnIntent.putExtra(INPUT_VALUE_EXTRA, category);
        // So far we just return
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}