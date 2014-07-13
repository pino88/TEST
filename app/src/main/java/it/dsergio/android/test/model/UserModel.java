package it.dsergio.android.test.model;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

public class UserModel implements Parcelable {


    private static final byte PRESENT = 1;

    private static final byte NOT_PRESENT = 0;

    private long mBirthDate;
    private String mUsername;
    private String mEmail;
    private String mLocation;

    public static final Parcelable.Creator<UserModel> CREATOR =
            new Parcelable.Creator<UserModel>() {
                public UserModel createFromParcel(Parcel in) {
                    return new UserModel(in);
                }

                public UserModel[] newArray(int size) {
                    return new UserModel[size];
                }
            };

    public UserModel(Parcel in) {
        this.mBirthDate = in.readLong();
        if (in.readByte() == PRESENT) {
            this.mUsername = in.readString();
        }
        if (in.readByte() == PRESENT) {
            this.mEmail = in.readString();
        }
        if (in.readByte() == PRESENT) {
            this.mLocation = in.readString();
          //  this.mLocation = (Location)in.readParcelable(getClass().getClassLoader());
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mBirthDate);
        if (!TextUtils.isEmpty(mUsername)) {
            dest.writeByte(PRESENT);
            dest.writeString(mUsername);
        } else {
            dest.writeByte(NOT_PRESENT);
        }
        if (!TextUtils.isEmpty(mEmail)) {
            dest.writeByte(PRESENT);
            dest.writeString(mEmail);
        } else {
            dest.writeByte(NOT_PRESENT);
        }
        if (!TextUtils.isEmpty(mLocation)) {
            dest.writeByte(PRESENT);
            dest.writeString(mLocation);
        } else {
            dest.writeByte(NOT_PRESENT);
        }
    }

    private UserModel(final long birthDate) {
        this.mBirthDate = birthDate;
    }
    public static UserModel create(final long birthDate) {
        final UserModel userModel = new UserModel(birthDate);
        return userModel;
    }

    public UserModel withEmail(final String email){
        mEmail=email;
        return this;
    };
    public UserModel withUsername(final String username){
        mUsername=username;
        return this;
    };
    public UserModel withLocation(final String location){
        mLocation=location;
        return this;
    };

    public boolean isAnonymous() {
        return TextUtils.isEmpty(this.mUsername);
    }
    public boolean isLogged() {
        return !TextUtils.isEmpty(this.mUsername);
    }
}