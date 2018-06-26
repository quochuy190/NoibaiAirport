package neo.com.noibai_airport.View.Fragment.FragmentShopDine;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import neo.com.noibai_airport.R;
import neo.com.noibai_airport.View.Activity.ActivityShops.ActivityShops;
import neo.com.noibai_airport.untils.BaseFragment;

public class FragmentShopsDescription extends BaseFragment {
    public static FragmentShopsDescription fragment;
    public static synchronized FragmentShopsDescription getInstance() {
        if (fragment == null) {
            fragment = new FragmentShopsDescription();
        }
        return (fragment);
    }
    @BindView(R.id.txt_shop_des_location)
    TextView txt_shop_des_location;
    @BindView(R.id.txt_shop_des_timetable)
    TextView txt_shop_des_timetable;
    @BindView(R.id.txt_shop_des_phone)
    TextView txt_shop_des_phone;
    @BindView(R.id.txt_shop_des_description)
    TextView txt_shop_des_description;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop_description, container, false);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        if (ActivityShops.mObjShop!=null){
            txt_shop_des_location.setText(getString(R.string.terminal)
                    +" "+ActivityShops.mObjShop.getsTERMINAL()
                    +" "+getString(R.string.floor)+" "+ActivityShops.mObjShop.getsFLOOR()
                    +" "+getString(R.string.area)+" "+ActivityShops.mObjShop.getsAREA()
            );
            txt_shop_des_phone.setText(getString(R.string.phone)+" "+ActivityShops.mObjShop.getsHOTLINE());
            txt_shop_des_timetable.setText(getString(R.string.price)+" "+ActivityShops.mObjShop.getsPRICE());
            txt_shop_des_description.setText(ActivityShops.mObjShop.getsDESCRIPTION());
        }
    }


}
