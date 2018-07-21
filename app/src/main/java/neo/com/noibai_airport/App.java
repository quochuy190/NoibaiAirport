package neo.com.noibai_airport;

import android.app.Application;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import neo.com.noibai_airport.Model.AirlineInfo;
import neo.com.noibai_airport.Model.Airport;
import neo.com.noibai_airport.Model.CategoryService;
import neo.com.noibai_airport.Model.CategoryShops;
import neo.com.noibai_airport.Model.District;
import neo.com.noibai_airport.Model.FlightInfo;
import neo.com.noibai_airport.Model.SearchToghther;
import neo.com.noibai_airport.Model.Together;
import neo.com.noibai_airport.Model.User;
import neo.com.noibai_airport.Model.Ward;

public class App extends Application {

    private static App sInstance;
    private Gson mGSon;
    public static User myUser;
    public static boolean isFollow =false;
    public static App self() {
        return sInstance;
    }
    public static List<CategoryService> lisCateService;
    public static List<CategoryShops> lisCateShops;
    public static List<FlightInfo> lisFlightDep;
    public static List<FlightInfo> lisFlightArr;
    public static boolean isLoadFlightDep = false;
    public static boolean isLoadToghther = false;
    public static boolean isLoadFlightArr = false;
    public static boolean isLoadService = false;
    public static boolean isLoadShops = false;
    public static List<Together> lisTogether;
    public static List<AirlineInfo> lisAirline;
    public static List<Airport> lisAirport;
    public static List<District> lisDistrict;
    public static List<Ward> lisWards;
    public static AirlineInfo mAirlineSearch;
    public static Airport mAirportSearch;
    public static District mDistrict;
    public static String mQuantity;
    public static Ward mWard;
    public static SearchToghther mSearchTogether;
    public static boolean isFlightBoking = false;
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

        sInstance = this;
        mGSon = new Gson();
        myUser = new User();
        lisWards = new ArrayList<>();
        lisTogether = new ArrayList<>();
        lisDistrict = new ArrayList<>();
        lisCateService = new ArrayList<>();
        lisCateShops = new ArrayList<>();
        lisFlightDep = new ArrayList<>();
        lisFlightArr = new ArrayList<>();
        lisAirline = new ArrayList<>();
        lisAirport = new ArrayList<>();
    }

    public Gson getGSon() {
        return mGSon;
    }
}
