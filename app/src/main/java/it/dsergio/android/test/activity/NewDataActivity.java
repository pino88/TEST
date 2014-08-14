package it.dsergio.android.test.activity;

import it.dsergio.android.test.fragment.InputDataFragment;
import  it.dsergio.android.test.fragment.NewDataFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import it.dsergio.android.test.R;
import it.dsergio.android.test.model.UserModel;
import it.dsergio.android.test.conf.Const;

public class NewDataActivity extends FragmentActivity implements NewDataFragment.NewDataFragmentListener {


    /**
     * The Tag of the Log for this class
     */
    private static final String TAG_LOG = NewDataActivity.class.getName();

    /**
     * The key we use to store the currentCategory as the state of this Activity
     */
    public static final String CURRENT_CATEGORY_KEY = Const.PKG + ".key.CURRENT_CATEGORY_KEY";

    /**
     * Extra used to get the UserModel
     */
    public static final String USER_EXTRA = Const.PKG + ".extra.USER_EXTRA";

    /**
     * Id for the input data request
     */
    private static final int INPUT_DATA_REQUEST_ID = 1;

    /**
     * The current UserModel
     */
    private UserModel mUserModel;

    /**
     * This is true when the screen is split in two part when the large device is in
     * landscape position
     */
    private boolean mIsDouble;

    /**
     * The current category
     */
    private String mCurrentCategory;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        // The display is double when the right_anchor is present into the chosen layout
        mIsDouble = findViewById(R.id.right_anchor) != null;
        // We get the UserModel
        this.mUserModel = (UserModel) getIntent().getParcelableExtra(USER_EXTRA);
        if (mUserModel == null) {
            Log.w(TAG_LOG, USER_EXTRA + " is mandatory!");
            finish();
        }
        // We insert the Fragment only if it's the first time and the
        // bundle is null
        if (savedInstanceState == null) {
            final NewDataFragment fragment = new NewDataFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.anchor_point, fragment).commit();
            // The default category
            final String defaultCategory = getResources().getString(R.string.love_label);
            mCurrentCategory = defaultCategory;
        } else {
            mCurrentCategory = savedInstanceState.getString(CURRENT_CATEGORY_KEY);
        }
        // If the screen is double we have to check if anything is already present. If not
        // we insert the first
        if (mIsDouble) {
            Fragment rightFragment = InputDataFragment.getInputDataFragment(mCurrentCategory);
            getSupportFragmentManager().beginTransaction().replace(R.id.right_anchor, rightFragment).commit();
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // We save the current category
        outState.putString(CURRENT_CATEGORY_KEY, mCurrentCategory);
    }

    /**
     * This is invoked when we press the newDataButton
     *
     * @param category The chosen category
     */
    @Override
    public void newDataForCategory(final String category) {
        // We change the fragment on the right only if different
        if (mIsDouble && !category.equals(mCurrentCategory)) {
            // In this case we have to replace the fragment on the right
            InputDataFragment newFragment = InputDataFragment.getInputDataFragment(category);
            // We replace the Fragment on the right
            getSupportFragmentManager().beginTransaction().replace(R.id.right_anchor, newFragment).commit();
            // We update the current category
            mCurrentCategory = category;
        } else {
            // We update the current category
            mCurrentCategory = category;
            final Intent questionIntent = new Intent(this, InputDataActivity.class);
            questionIntent.putExtra(InputDataActivity.INPUT_TYPE_EXTRA, category);
            startActivityForResult(questionIntent, INPUT_DATA_REQUEST_ID);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == INPUT_DATA_REQUEST_ID && resultCode == RESULT_OK) {
            // Here we'll manage the data from the questions
            // TODO Manage data from the questions
        }
    }
}