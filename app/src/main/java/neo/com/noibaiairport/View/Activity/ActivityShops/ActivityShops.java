package neo.com.noibaiairport.View.Activity.ActivityShops;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import neo.com.noibaiairport.Adapter.AdapterViewpager;
import neo.com.noibaiairport.Config.Constants;
import neo.com.noibaiairport.Model.ShopsDine;
import neo.com.noibaiairport.R;
import neo.com.noibaiairport.View.Fragment.FragmentShopDine.FragmentShopsDescription;
import neo.com.noibaiairport.View.Fragment.FragmentShopDine.FragmentShopsMenu;
import neo.com.noibaiairport.untils.BaseActivity;

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
    private ShopsDine mObjShop;
    @BindView(R.id.viewpager_shop)
    ViewPager viewPager;
    AdapterViewpager adapter;
    @BindView(R.id.tablayout_shop)
    TabLayout tabLayout;
    @BindView(R.id.txt_shops_name)
    TextView txt_shops_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        setupViewPager(viewPager);
    }

    private void initData() {
        mObjShop = (ShopsDine) getIntent().getSerializableExtra(Constants.KEY_SENT_SHOPS);
        if(mObjShop!=null){
            Glide.with(this).load(mObjShop.getmImage()).into(img_shop_main);
        }
        txt_shops_name.setText(mObjShop.getmName());
    }

    private void setupViewPager(ViewPager viewPager) {
        String departures =  getString(R.string.txt_title_departures);
        String arrivals =getString( R.string.txt_title_arrivals);

        adapter = new AdapterViewpager(getSupportFragmentManager());
        adapter.addFragment(new FragmentShopsDescription(),departures);
        adapter.addFragment(new FragmentShopsMenu(), arrivals);

        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

}
