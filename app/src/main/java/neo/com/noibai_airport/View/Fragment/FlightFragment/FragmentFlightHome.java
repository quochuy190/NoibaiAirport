package neo.com.noibai_airport.View.Fragment.FlightFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import neo.com.noibai_airport.Adapter.AdapterViewpager;
import neo.com.noibai_airport.R;
import neo.com.noibai_airport.untils.BaseFragment;

public class FragmentFlightHome extends BaseFragment {
    private static final String TAG = "FragmentFlightHome";
    public static FragmentFlightHome fragment;
    public static boolean isLoadViewDeparture = false;
    public static boolean isLoadViewArrivals = false;

    public static synchronized FragmentFlightHome getInstance() {
        if (fragment == null) {
            fragment = new FragmentFlightHome();
        }
        return (fragment);
    }

    public static ViewPager viewPager;
    public static AdapterViewpager adapter;
    public static TabLayout tabLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: ");
        isLoadViewDeparture = true;
        isLoadViewArrivals = true;
    }

    public static String departures;
    public static String arrivals;
    public static String other;
    public boolean isFollow;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flight_home, container, false);
        Log.i(TAG, "onCreateView: ");
        //  ButterKnife.bind(this, view);
        viewPager = view.findViewById(R.id.viewpager_flight_home);
        tabLayout = view.findViewById(R.id.tab_layout);
        init();
        setupViewPager(viewPager);
        return view;


    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");

    }

    private void init() {
        departures = getActivity().getString(R.string.txt_title_departures);
        arrivals = getActivity().getString(R.string.txt_title_arrivals);
        other = getActivity().getString(R.string.txt_title_other);
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new AdapterViewpager(getChildFragmentManager());
        adapter.addFragment(new FragmentFlightDepartures(), departures);
        adapter.addFragment(new FragmentFlightArrivals(), arrivals);
        adapter.addFragment(new FragmentFlight(), other);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }


}
