package neo.com.noibaiairport.View.Fragment.FlightFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import neo.com.noibaiairport.R;
import neo.com.noibaiairport.untils.BaseFragment;

public class FragmentFlight extends BaseFragment {
    public static FragmentFlight fragment;
    public static synchronized FragmentFlight getInstance() {
        if (fragment == null) {
            fragment = new FragmentFlight();
        }
        return (fragment);
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flight, container, false);
        ButterKnife.bind(this, view);

        return view;
    }


}
