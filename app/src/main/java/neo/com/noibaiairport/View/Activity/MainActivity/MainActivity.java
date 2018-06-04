package neo.com.noibaiairport.View.Activity.MainActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.List;

import butterknife.ButterKnife;
import neo.com.noibaiairport.BuildConfig;
import neo.com.noibaiairport.Config.Constants;
import neo.com.noibaiairport.Fragment.FragmentService.FragmenService;
import neo.com.noibaiairport.Fragment.FragmentShopDine.FragmentShopDine;
import neo.com.noibaiairport.R;
import neo.com.noibaiairport.View.Fragment.FlightFragment.FragmentFlightHome;
import neo.com.noibaiairport.View.Fragment.MenuFragment.FragmentMenu;
import neo.com.noibaiairport.untils.BaseActivity;
import neo.com.noibaiairport.untils.FragmentUtil;

public class MainActivity extends BaseActivity implements MainInterface.View {

    //public static ActionBar ab;
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

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
        initAppbar();
        initBottomBar();
        initEvent();
        // initData();

    }

    private void initData() {
        // mPresenter.getInit("", "", "", "", "", "");
        mPresenter.getInit("app", BuildConfig.VERSION_NAME,
                android.os.Build.BRAND + " " + android.os.Build.MODEL, "2",
                android.os.Build.VERSION.RELEASE, "abc");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("language", "onResume_main");

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
    }

    private void initBottomBar() {
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.tab_home:
                        FragmentUtil.replaceFragmentMain(MainActivity.this, FragmentFlightHome.getInstance(), R.id.frame_content);
                        txt_title.setText(R.string.tab_flight);
                        break;
                    case R.id.tab_flight:
                        FragmentUtil.replaceFragmentMain(MainActivity.this, FragmenService.getInstance(), R.id.frame_content);
                        txt_title.setText(R.string.tab_service);
                        break;
                    case R.id.tab_booking:
                        FragmentUtil.replaceFragmentMain(MainActivity.this, FragmentShopDine.getInstance(), R.id.frame_content);
                        txt_title.setText(R.string.tab_shopdine);
                        break;
                    case R.id.tab_service:
                        txt_title.setText(R.string.tab_tuongtac);
                        break;
                    case R.id.tab_shopdine:
                        //relative_appbar_main.setVisibility(View.GONE);
                        FragmentUtil.replaceFragmentMain(MainActivity.this, FragmentMenu.getInstance(), R.id.frame_content);
                        txt_title.setText(R.string.tab_menu);
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
        init();
        initBottomBar();
        startActivity(new Intent(MainActivity.this, MainActivity.class));
        finish();


    }

    @Override
    public void show_init(List<String> mLisErorr) {

    }

    @Override
    public void show_api_error() {

    }
}
