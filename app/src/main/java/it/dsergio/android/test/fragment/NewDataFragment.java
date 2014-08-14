package it.dsergio.android.test.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.dsergio.android.test.R;
import android.util.Log;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewDataFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewDataFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class NewDataFragment extends Fragment {

    /**
     * The tag for the log
     */
    private static final String TAG_LOG = NewDataFragment.class.getName();

    /**
     * Interface that should be implemented by the activity that uses this Fragment
     */
    public interface NewDataFragmentListener {

        /**
         * This is invoked to notify the choice on this menu.
         *
         * @param category The category choosen
         */
        void newDataForCategory(String category);

    }

    /**
     * The reference to the listener of this Fragment
     */
    private NewDataFragmentListener mListener;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof NewDataFragmentListener) {
            mListener = (NewDataFragmentListener) activity;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // We create the layout for this fragment
        final View menuView = inflater.inflate(R.layout.fragment_new_data, null);
        // We note that we don't care about the Button type because the onClick is an event
        // of all the View
        menuView.findViewById(R.id.new_love_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    Log.d(TAG_LOG, "Data for Love!");
                    mListener.newDataForCategory(getResources().getString(R.string.love_label));
                }
            }
        });
        // Health
        menuView.findViewById(R.id.new_health_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    Log.d(TAG_LOG, "Data for Health!");
                    mListener.newDataForCategory(getResources().getString(R.string.health_label));
                }
            }
        });
        // Work
        menuView.findViewById(R.id.new_work_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    Log.d(TAG_LOG, "Data for Work!");
                    mListener.newDataForCategory(getResources().getString(R.string.work_label));
                }
            }
        });
        // Luck
        menuView.findViewById(R.id.new_luck_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    Log.d(TAG_LOG, "Data for Luck!");
                    mListener.newDataForCategory(getResources().getString(R.string.luck_label));
                }
            }
        });
        // We return the View for the fragment
        return menuView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.mListener = null;
    }

}
