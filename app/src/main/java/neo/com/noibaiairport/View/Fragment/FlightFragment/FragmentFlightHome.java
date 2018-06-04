package neo.com.noibaiairport.View.Fragment.FlightFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import neo.com.noibaiairport.Adapter.AdapterViewpager;
import neo.com.noibaiairport.R;
import neo.com.noibaiairport.untils.BaseFragment;

public class FragmentFlightHome extends BaseFragment {
    public static FragmentFlightHome fragment;
    public static synchronized FragmentFlightHome getInstance() {
        if (fragment == null) {
            fragment = new FragmentFlightHome();
        }
        return (fragment);
    }
    @BindView(R.id.viewpager_flight_home)
    ViewPager viewPager;
    AdapterViewpager adapter;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flight_home, container, false);
        ButterKnife.bind(this, view);
        setupViewPager(viewPager);
        return view;
    }

    private void setupViewPager(ViewPager viewPager) {
        String departures =  getActivity().getString(R.string.txt_title_departures);
        String arrivals =getActivity().getString( R.string.txt_title_arrivals);
        String other =getActivity().getString(R.string.txt_title_other);
        adapter = new AdapterViewpager(getChildFragmentManager());
        adapter.addFragment(new FragmentFlightDepartures(),departures);
        adapter.addFragment(new FragmentFlightArrivals(), arrivals);
        adapter.addFragment(new FragmentFlight(), other);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }


}
