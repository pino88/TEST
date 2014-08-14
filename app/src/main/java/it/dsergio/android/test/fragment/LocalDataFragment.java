package it.dsergio.android.test.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import it.dsergio.android.test.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LocalDataFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LocalDataFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class LocalDataFragment extends Fragment {

    /**
     * The tag for the log
     */
    private static final String TAG_LOG = LocalDataFragment.class.getName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // We create the layout for this fragment
        final View localDataView = inflater.inflate(R.layout.fragment_local_data, null);
        // We return the View for the fragment
        return localDataView;
    }

}

