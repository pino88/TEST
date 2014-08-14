package it.dsergio.android.test.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import it.dsergio.android.test.R;
import it.dsergio.android.test.conf.Const;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InputDataFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InputDataFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class InputDataFragment extends Fragment {
    /**
     * The tag for the log
     */
    private static final String TAG_LOG = InputDataFragment.class.getName();

    /**
     * The key we use for the category as argument
     */
    private static final String CATEGORY_ARG_KEY = Const.PKG + ".key.CATEGORY_ARG_KEY";


    /**
     * Create a InputDataFragment for the given category
     *
     * @param category The category for this Fragment
     * @return The Fragment for input data about a category
     */
    public static InputDataFragment getInputDataFragment(final String category) {
        // We create the InputDataFragment saving the category as arguments
        final InputDataFragment fragment = new InputDataFragment();
        Bundle args = new Bundle();
        args.putString(CATEGORY_ARG_KEY, category);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Interface that should be implemented by the activity that uses this Fragment
     */
    public interface InputDataFragmentListener {

        /**
         * This is invoked to notify the choice on this menu.
         *
         * @param category The category related to the vote
         * @param vote     The vote for a given category
         */
        void valueForCategory(String category, long vote);

    }

    /**
     * The reference to the listener of this Fragment
     */
    private InputDataFragmentListener mListener;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof InputDataFragmentListener) {
            mListener = (InputDataFragmentListener) activity;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // We get the category value we're voting for
        final String category = getArguments().getString(CATEGORY_ARG_KEY);
        // We create the layout for this fragment
        final View inputDataView = inflater.inflate(R.layout.fragment_input_data, null);
        // We note that we don't care about the Button type because the onClick is an event
        // of all the View
        inputDataView.findViewById(R.id.send_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    Log.d(TAG_LOG, "Send value for category " + category);
                    // So far we return always the same value. TODO Make it dynamic
                    mListener.valueForCategory(category, 10);
                }
            }
        });
        // We set the question
        final TextView questionTextView = (TextView) inputDataView.findViewById(R.id.data_question);
        final String question = getResources().getString(R.string.data_question_format, category);
        questionTextView.setText(question);
        // We return the View for the fragment
        return inputDataView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.mListener = null;
    }
}
