package neo.com.noibaiairport;

import android.app.Application;

import com.google.gson.Gson;

import neo.com.noibaiairport.Model.User;

public class App extends Application {

    private static App sInstance;
    private Gson mGSon;
    public static User myUser;

    public static App self() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        mGSon = new Gson();
        myUser = new User();
    }

    public Gson getGSon() {
        return mGSon;
    }
}
