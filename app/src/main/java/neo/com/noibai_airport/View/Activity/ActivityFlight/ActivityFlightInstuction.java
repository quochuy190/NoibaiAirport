package neo.com.noibai_airport.View.Activity.ActivityFlight;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import neo.com.noibai_airport.Adapter.AdapterViewpager;
import neo.com.noibai_airport.R;
import neo.com.noibai_airport.View.Fragment.FlightFragment.Fragment_Flight_Procedures;
import neo.com.noibai_airport.View.Fragment.FlightFragment.Fragment_Guide_Luggage;
import neo.com.noibai_airport.untils.BaseActivity;

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
public class ActivityFlightInstuction extends BaseActivity {
    @Override
    public int setContentViewId() {
        return R.layout.activity_flight_instruction;
    }
    @BindView(R.id.viewpager_instruction)
    ViewPager viewPager;
    AdapterViewpager adapter;
    @BindView(R.id.tablayout_instruction)
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initAppbar();
        initData();
        setupViewPager(viewPager);
    }
    public void initAppbar() {
        ImageView img_search = findViewById(R.id.img_search);
        ImageView img_back = findViewById(R.id.img_back);
        TextView txt_title = findViewById(R.id.txt_title_main);
        ImageView img_chatbox = findViewById(R.id.img_chatbox);

        txt_title.setText(R.string.title_checkin);
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

    }

    private void setupViewPager(ViewPager viewPager) {
        String departures =  getString(R.string.tab_thutucbay);
        String arrivals =getString( R.string.tab_hanhly);

        adapter = new AdapterViewpager(getSupportFragmentManager());
        adapter.addFragment(new Fragment_Flight_Procedures(),departures);
        adapter.addFragment(new Fragment_Guide_Luggage(), arrivals);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
    }

}
