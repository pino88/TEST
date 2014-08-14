package it.dsergio.android.test.utils;

import android.app.Activity;
import android.view.View;

/**
 * Created by dsergio on 14/08/2014.
 */
public class ViewUtility extends Activity{
    @SuppressWarnings("unchecked")
    public static <T extends View> T findViewById(Activity act, int viewId){
        View foundView = act.getWindow().getDecorView();
        return  (T) foundView;
    }
}
