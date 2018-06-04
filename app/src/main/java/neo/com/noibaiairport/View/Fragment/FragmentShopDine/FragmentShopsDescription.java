package neo.com.noibaiairport.View.Fragment.FragmentShopDine;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import neo.com.noibaiairport.R;
import neo.com.noibaiairport.untils.BaseFragment;

public class FragmentShopsDescription extends BaseFragment {
    public static FragmentShopsDescription fragment;
    public static synchronized FragmentShopsDescription getInstance() {
        if (fragment == null) {
            fragment = new FragmentShopsDescription();
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
        View view = inflater.inflate(R.layout.fragment_shop_description, container, false);
        ButterKnife.bind(this, view);

        return view;
    }


}
