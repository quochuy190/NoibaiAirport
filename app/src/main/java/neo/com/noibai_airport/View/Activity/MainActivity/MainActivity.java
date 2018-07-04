package neo.com.noibai_airport.View.Activity.MainActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IdRes;
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

    /* @BindView(R.id.img_back)
     ImageView img_back;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: ");
        App.isLoadFlightArr = true;
        App.isLoadFlightDep = true;
        App.isLoadService = true;
        App.isLoadShops = true;
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
        initAppbar();
        initBottomBar();
        initEvent();
        mPresenter.getInit("", "", "", "", "", "");
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
        img_search = findViewById(R.id.img_search);
        txt_title = findViewById(R.id.txt_title_main);
        txt_title.setText(R.string.tab_home);

    }

    private void initEvent() {
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
                        break;
                    case R.id.tab_flight:

                        FragmentUtil.replaceFragmentMain(MainActivity.this, FragmenService.getInstance(),
                                R.id.frame_content);
                        txt_title.setText(R.string.tab_service);
                        img_search.setVisibility(View.GONE);
                        break;
                    case R.id.tab_booking:

                        FragmentUtil.replaceFragmentMain(MainActivity.this, FragmenShopAndDine.getInstance(),
                                R.id.frame_content);
                        txt_title.setText(R.string.tab_shopdine);
                        img_search.setVisibility(View.GONE);
                        break;
                  /*  case R.id.tab_service:

                        txt_title.setText(R.string.tab_tuongtac);
                        img_search.setVisibility(View.GONE);
                        break;*/
                    case R.id.tab_shopdine:

                        FragmentUtil.replaceFragmentMain(MainActivity.this, FragmentMenu.getInstance(),
                                R.id.frame_content);
                        txt_title.setText(R.string.tab_menu);
                        img_search.setVisibility(View.GONE);
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


}
