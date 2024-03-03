package edu.psu.sweng888.sweng888practice4;

import android.credentials.CreateCredentialException;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Products implements Parcelable, Serializable {
    private String mPrice;
    private String mDescription;
    private String mName;
    private String mSeller;

    public Products(String name, String seller, String price, String description) {
        this.mName = name;
        this.mSeller = seller;
        this.mPrice = price;
        this.mDescription = description;
    }

    public static final Creator<Products> CREATOR = new Creator<Products>() {
        @Override
        public Products createFromParcel(Parcel parcel) {
            return new Products(parcel);
        }

        @Override
        public Products[] newArray(int i) {
            return new Products[i];
        }
    };

    protected Products(Parcel in) {
        mName = in.readString();
        mSeller = in.readString();
        mPrice = in.readString();
        mDescription = in.readString();
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags){
        dest.writeString(mName);
        dest.writeString(mSeller);
        dest.writeString(mDescription);
        dest.writeString(mPrice);
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getSeller() {
        return mSeller;
    }

    public void setSeller(String seller) {
        mSeller = seller;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String price) {
        mPrice = price;
    }
}
