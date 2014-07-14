package it.dsergio.android.test.conf;

/**
 * This is a final class that we use to manage the constants of our application
 * <p/>
 * Created by Massimo Carli on 05/06/13.
 */
public final class Const {

    private Const() {
        throw new AssertionError("Never instantiate me! I'm a Const class!");
    }

    /**
     * The Package of the application. We use this to manage extra and other parameters.
     */
    public static final String PKG = "it.dsergio.android.test";

}
