package it.dsergio.android.test.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.dsergio.android.test.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FirstAccessListener.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FirstAccessListener#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class FirstAccessFragment extends android.support.v4.app.Fragment {
    private static final String TAG_LOG = FirstAccessFragment.class.getSimpleName();

    public interface FirstAccessListener {
        void enterAsAnonymous();
        void doLogin();
        void doRegistration();
    }

    private FirstAccessListener mListener;

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        if(activity instanceof  FirstAccessListener) {
            mListener = (FirstAccessListener) activity;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        Log.d(TAG_LOG, "onCreateView");
        final View firstAccessView = inflater.inflate(R.layout.fragment_first_access, null);
        firstAccessView.findViewById(R.id.anonimus_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.enterAsAnonymous();
                }
            }
        });
        firstAccessView.findViewById(R.id.login_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mListener != null){
                    mListener.doLogin();
                }
            }
        });

        firstAccessView.findViewById(R.id.register_button).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.doRegistration();
                }
            }
        });
        return firstAccessView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.mListener = null;
    }
}
