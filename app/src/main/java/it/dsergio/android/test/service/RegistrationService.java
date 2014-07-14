package it.dsergio.android.test.service;

import it.dsergio.android.test.model.UserModel;

public class RegistrationService {
    public static final String DUMMY_USERNAME="root";
    public static final String DUMMY_PASSWORD="admin";
    private static RegistrationService instance=null;

    private RegistrationService(){};

    public static RegistrationService get(){
        if(instance == null){
            instance=new RegistrationService();
        }
        return instance;
    }
    public UserModel register(final String username, final String password, final String email,
                              final long birthDate, final String location) {
             UserModel userModel = UserModel.create(birthDate).withUsername(username)
                    .withLocation(location).withEmail(email);
            return userModel;
    }

}