package neo.com.noibai_airport.View.Activity.ActivityShops;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import neo.com.noibai_airport.Adapter.AdapterViewpager;
import neo.com.noibai_airport.Config.Constants;
import neo.com.noibai_airport.Model.ShopsDine;
import neo.com.noibai_airport.R;
import neo.com.noibai_airport.View.Activity.Feedback.ActivityRegister;
import neo.com.noibai_airport.View.Fragment.FragmentShopDine.FragmentShopsDescription;
import neo.com.noibai_airport.View.Fragment.FragmentShopDine.FragmentShopsMenu;
import neo.com.noibai_airport.untils.BaseActivity;
import neo.com.noibai_airport.untils.SharedPrefs;

/**
 * @author Quốc Huy
 * @version 1.0.0
 * @description
 * @desc Developer NEO Company.
 * @created ${30/5/2018}
 * @updated ${Date}
 * @modified by
 * @updated on ${Date}
 * @since 1.0
 */
public class ActivityShops extends BaseActivity {
    @Override
    public int setContentViewId() {
        return R.layout.activity_shop_detail;
    }

    @BindView(R.id.img_shop_main)
    ImageView img_shop_main;
    public static ShopsDine mObjShop;
    @BindView(R.id.viewpager_shop)
    ViewPager viewPager;
    AdapterViewpager adapter;
    @BindView(R.id.tablayout_shop)
    TabLayout tabLayout;
    @BindView(R.id.ll_comment)
    LinearLayout ll_comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initAppbar();
        initEvent();
        setupViewPager(viewPager);
    }

    private void initEvent() {
        ll_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isLoginFeedback = SharedPrefs.getInstance().get(Constants.KEY_IS_LOGIN_FEEDBACK, Boolean.class);
                if (isLoginFeedback) {
                    Intent intent = new Intent(ActivityShops.this, ActivityComments.class);
                    intent.putExtra(Constants.KEY_SEND_COMMENT, mObjShop.getmId());
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(ActivityShops.this, ActivityRegister.class);
                    intent.putExtra(Constants.KEY_SEND_REGISTER, Constants.COMMENTS);
                    startActivity(intent);
                }

            }
        });
    }

    public void initAppbar() {
        ImageView img_search = findViewById(R.id.img_search);
        ImageView img_back = findViewById(R.id.img_back);
        TextView txt_title = findViewById(R.id.txt_title_main);
        ImageView img_chatbox = findViewById(R.id.img_chatbox);

        txt_title.setText(mObjShop.getmName());
        img_chatbox.setVisibility(View.GONE);
        img_back.setVisibility(View.VISIBLE);
        img_search.setVisibility(View.GONE);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initData() {
        mObjShop = (ShopsDine) getIntent().getSerializableExtra(Constants.KEY_SENT_SHOPS);
        if (mObjShop != null) {
            Glide.with(this).load(mObjShop.getmImage()).into(img_shop_main);
        }

    }

    private void setupViewPager(ViewPager viewPager) {
        String departures = getString(R.string.viewpage_shop_general);
        String arrivals = getString(R.string.viewpage_shop_menu);

        adapter = new AdapterViewpager(getSupportFragmentManager());
        adapter.addFragment(new FragmentShopsDescription(), departures);
        adapter.addFragment(new FragmentShopsMenu(), arrivals);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

}
