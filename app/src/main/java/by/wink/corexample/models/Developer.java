package by.wink.corexample.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zoid23 on 24/05/17.
 */

public class Developer implements Parcelable{

    private String name;

    public Developer (JSONObject object) throws JSONException {
        name = object.getString("name");
    }

    public String getName() {
        return name;
    }

    protected Developer(Parcel in) {
        name = in.readString();
    }

    public static final Creator<Developer> CREATOR = new Creator<Developer>() {
        @Override
        public Developer createFromParcel(Parcel in) {
            return new Developer(in);
        }

        @Override
        public Developer[] newArray(int size) {
            return new Developer[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }
}
