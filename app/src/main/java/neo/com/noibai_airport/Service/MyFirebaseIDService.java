package neo.com.noibai_airport.Service;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.util.List;

import neo.com.noibai_airport.BuildConfig;
import neo.com.noibai_airport.Config.Constants;
import neo.com.noibai_airport.Model.AirlineInfo;
import neo.com.noibai_airport.Model.Airport;
import neo.com.noibai_airport.View.Activity.MainActivity.MainInterface;
import neo.com.noibai_airport.View.Activity.MainActivity.MainPresenter;
import neo.com.noibai_airport.untils.SharedPrefs;

/**
 * Created by QQ on 8/29/2017.
 */

public class MyFirebaseIDService extends FirebaseInstanceIdService implements MainInterface.View {
    private static final String TAG = "MyFirebaseIDService";
    private MainPresenter mPresenter;
    String mToken;

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        mPresenter = new MainPresenter(this);
        mToken = FirebaseInstanceId.getInstance().getToken();

        if (mToken != null) {
            Log.i(TAG, "onTokenRefresh: "+mToken);
            mPresenter.getInit("app", BuildConfig.VERSION_NAME,
                    android.os.Build.BRAND + " " + android.os.Build.MODEL, "2",
                    android.os.Build.VERSION.RELEASE, mToken);
        }
    }


    public void getInfoDevice() {
        int versionCode = BuildConfig.VERSION_CODE;
        String VERSION = BuildConfig.VERSION_NAME;
        String _OSVERSION = System.getProperty("os.version");
        //phiên ban andoird
        String _RELEASE = android.os.Build.VERSION.RELEASE;
        String _DEVICE = android.os.Build.DEVICE;
        //đời máy
        String _MODEL = android.os.Build.MODEL;
        String _PRODUCT = android.os.Build.PRODUCT;
        //hãng máy
        String BRAND = android.os.Build.BRAND;

        String _DISPLAY = android.os.Build.DISPLAY;
        String _CPU_ABI = android.os.Build.CPU_ABI;
        String _CPU_ABI2 = android.os.Build.CPU_ABI2;
        String _UNKNOWN = android.os.Build.UNKNOWN;
        String _HARDWARE = android.os.Build.HARDWARE;
        String _ID = android.os.Build.ID;
        String _MANUFACTURER = android.os.Build.MANUFACTURER;
        String _SERIAL = android.os.Build.SERIAL;
        String _USER = android.os.Build.USER;
        String _HOST = android.os.Build.HOST;
        String MODEL = BRAND + " " + _MODEL;
        String VERSION_OS = _RELEASE;
        String ISMODEL = "2";
    }


    @Override
    public void show_init(List<String> mLisErorr) {
        SharedPrefs.getInstance().put(Constants.KEY_TOKEN, mToken);
        SharedPrefs.getInstance().put(Constants.KEY_VERSION, BuildConfig.VERSION_NAME);
    }

    @Override
    public void show_all_airline(List<AirlineInfo> lisAirline) {

    }

    @Override
    public void show_all_airport(List<Airport> lisAirport) {

    }

    @Override
    public void show_api_error() {

    }
}
