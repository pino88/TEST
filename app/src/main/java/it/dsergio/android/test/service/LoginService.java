package it.dsergio.android.test.service;

import it.dsergio.android.test.model.UserModel;

/**
 * Created by fen on 13/07/14.
 */
public class LoginService {
    public static final String DUMMY_USERNAME="root";
    public static final String DUMMY_PASSWORD="admin";
    private static LoginService instance=null;

    private LoginService(){};

    public static LoginService get(){
        if(instance == null){
            instance=new LoginService();
        }
        return instance;
    }
    public UserModel login(final String username, final String password) {
        UserModel userModel = null;
        if (DUMMY_USERNAME.equalsIgnoreCase(username) && DUMMY_PASSWORD.equalsIgnoreCase(password)) {
            userModel = UserModel.create(System.currentTimeMillis()).withEmail("dummy@daisy.com").withUsername(username);
        }
        return userModel;
    }
}
