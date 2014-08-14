package it.dsergio.android.test.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.dsergio.android.test.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RemoteDataFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RemoteDataFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class RemoteDataFragment extends Fragment {

    /**
     * The tag for the log
     */
    private static final String TAG_LOG = RemoteDataFragment.class.getName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // We create the layout for this fragment
        final View remoteDataView = inflater.inflate(R.layout.fragment_remote_data, null);
        // We return the View for the fragment
        return remoteDataView;
    }

}
