package neo.com.noibai_airport.View.Activity.MainActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.List;

import butterknife.ButterKnife;
import neo.com.noibai_airport.App;
import neo.com.noibai_airport.BuildConfig;
import neo.com.noibai_airport.Config.Constants;
import neo.com.noibai_airport.Model.AirlineInfo;
import neo.com.noibai_airport.Model.Airport;
import neo.com.noibai_airport.R;
import neo.com.noibai_airport.View.Fragment.FlightFragment.FragmentFlightHome;
import neo.com.noibai_airport.View.Fragment.FragmentService.FragmenService;
import neo.com.noibai_airport.View.Fragment.FragmentShopDine.FragmenShopAndDine;
import neo.com.noibai_airport.View.Fragment.MenuFragment.FragmentMenu;
import neo.com.noibai_airport.View.Fragment.Together.FragmenTogether;
import neo.com.noibai_airport.untils.BaseActivity;
import neo.com.noibai_airport.untils.FragmentUtil;
import neo.com.noibai_airport.untils.LanguageUtils;
import neo.com.noibai_airport.untils.SharedPrefs;

public class MainActivity extends BaseActivity implements MainInterface.View {
    private static final String TAG = "MainActivity";
    public static MainActivity activity;

    public static synchronized MainActivity getInstance() {
        if (activity == null) {
            activity = new MainActivity();
        }
        return (activity);
    }

    public static BottomBar bottomBar;
    public static RelativeLayout relative_appbar_main;
    public static Context mContext;
    private TextView txt_title;
    private MainPresenter mPresenter;
    public static ImageView img_search;
    public static ImageView img_chatbox;
    /* @BindView(R.id.img_back)
     ImageView img_back;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: ");
        App.isLoadFlightArr = true;
        App.isLoadFlightDep = true;
        App.isLoadToghther = true;
        App.isLoadService = true;
        App.isLoadShops = true;
        App.isFlightBoking = true;
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
        initAppbar();
        initBottomBar();
        initEvent();
       // mPresenter.getInit("", "", "", "", "", "");
        // initData();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
        if (SharedPrefs.getInstance().get(Constants.KEY_CHANGE_LANGUAGE, Boolean.class)) {
            SharedPrefs.getInstance().put(Constants.KEY_CHANGE_LANGUAGE, false);
            updateViewByLanguage();
        }
        LanguageUtils.loadLocale();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }
    //TextView txt_title;

    public void initAppbar() {
        img_chatbox = findViewById(R.id.img_chatbox);
        img_search = findViewById(R.id.img_search);
        txt_title = findViewById(R.id.txt_title_main);
        txt_title.setText(R.string.tab_home);

    }

    private void initEvent() {
        MainActivity.img_chatbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.messenger.com/t/218839388662019");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }

    @Override
    public int setContentViewId() {
        return R.layout.activity_main;
    }

    private void init() {
        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        relative_appbar_main = findViewById(R.id.relative_appbar_main);
        mPresenter = new MainPresenter(this);
        String sToken = SharedPrefs.getInstance().get(Constants.KEY_TOKEN, String.class);
        Log.i(TAG, "init: " + sToken);
        String sUserId = SharedPrefs.getInstance().get(Constants.KEY_USERID, String.class);
        String username = SharedPrefs.getInstance().get(Constants.KEY_USER_FEEDBACK, String.class);
        String ver_app = SharedPrefs.getInstance().get(Constants.KEY_VERSION, String.class);
        String email = SharedPrefs.getInstance().get(Constants.KEY_EMAIL_FEEDBACK, String.class);
        if (!BuildConfig.VERSION_NAME.equals(ver_app)) {
            mPresenter.get_update_info(sUserId, "app", username, email, BuildConfig.VERSION_NAME,
                    android.os.Build.BRAND + " " + android.os.Build.MODEL, sToken, "2",
                    android.os.Build.VERSION.RELEASE);
        }
    }


    private void initBottomBar() {
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.tab_home:
                        FragmentUtil.replaceFragmentMain(MainActivity.this, FragmentFlightHome.getInstance(),
                                R.id.frame_content);
                        txt_title.setText(R.string.tab_flight);
                        img_search.setVisibility(View.VISIBLE);
                        img_chatbox.setVisibility(View.VISIBLE);
                        break;
                    case R.id.tab_flight:
                        FragmentUtil.replaceFragmentMain(MainActivity.this, FragmenService.getInstance(),
                                R.id.frame_content);
                        txt_title.setText(R.string.tab_service);
                        img_search.setVisibility(View.GONE);
                        img_chatbox.setVisibility(View.VISIBLE);
                        break;
                    case R.id.tab_booking:

                        FragmentUtil.replaceFragmentMain(MainActivity.this, FragmenShopAndDine.getInstance(),
                                R.id.frame_content);
                        txt_title.setText(R.string.tab_shopdine);
                        img_search.setVisibility(View.GONE);
                        img_chatbox.setVisibility(View.VISIBLE);
                        break;
                    case R.id.tab_together:
                        FragmentUtil.replaceFragmentMain(MainActivity.this, FragmenTogether.getInstance(),
                                R.id.frame_content);
                        txt_title.setText(R.string.tab_together);
                        img_search.setVisibility(View.VISIBLE);
                        img_chatbox.setVisibility(View.VISIBLE);
                        break;
                    case R.id.tab_shopdine:

                        FragmentUtil.replaceFragmentMain(MainActivity.this, FragmentMenu.getInstance(),
                                R.id.frame_content);
                        txt_title.setText(R.string.tab_menu);
                        img_search.setVisibility(View.GONE);
                        img_chatbox.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });

    }

    boolean isDoubleClick;

    @Override
    public void onBackPressed() {

        fragmentBackTack();
    }

    public void fragmentBackTack() {
        if (isDoubleClick) {
            finish();
            return;
        }
        this.isDoubleClick = true;
        Toast.makeText(this, R.string.back_notification, Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                isDoubleClick = false;
            }
        }, 2000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Constants.RequestCode.CHANGE_LANGUAGE:
                if (resultCode == RESULT_OK) {
                    updateViewByLanguage();
                }
                break;
        }

    }

    private void updateViewByLanguage() {
        finish();
        startActivity(new Intent(MainActivity.this, MainActivity.class));
    }

    @Override
    public void show_init(List<String> mLisErorr) {

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
    public static void call_phone(Context mContext,String phone){
        sPhone = phone;
        if (Build.VERSION.SDK_INT < 23) {
            phoneCall(mContext, phone);
        } else {
            if (ActivityCompat.checkSelfPermission(mContext,
                    Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                phoneCall(mContext, phone);
            } else {
                final String[] PERMISSIONS_STORAGE = {Manifest.permission.CALL_PHONE};
                //Asking request Permissions
                ActivityCompat.requestPermissions((Activity) mContext, PERMISSIONS_STORAGE, 9);
            }
        }
    }
    public static String sPhone;
    private static void phoneCall(Context mContext,String phone) {
        if (ActivityCompat.checkSelfPermission(mContext,
                Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + phone));
            mContext.startActivity(callIntent);
        } else {
            Toast.makeText(mContext, "You don't assign permission.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        boolean permissionGranted = false;
        switch (requestCode) {
            case 9:
                permissionGranted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                break;
        }
        if (permissionGranted) {
           phoneCall(this, sPhone);
        } else {
            Toast.makeText(this, "You don't assign permission.", Toast.LENGTH_SHORT).show();
        }
    }

}
