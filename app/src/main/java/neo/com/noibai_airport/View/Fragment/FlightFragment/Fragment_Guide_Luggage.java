package neo.com.noibai_airport.View.Fragment.FlightFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import neo.com.noibai_airport.Adapter.AdapterInstruction;
import neo.com.noibai_airport.Model.ObjWebview;
import neo.com.noibai_airport.R;
import neo.com.noibai_airport.View.Activity.ActivityBooking;
import neo.com.noibai_airport.untils.BaseFragment;
import neo.com.noibai_airport.untils.setOnItemClickListener;

import static neo.com.noibai_airport.Config.Constants.KEY_SEND_URL_WEBVIEW;

/**
 * @author Quốc Huy
 * @version 1.0.0
 * @description
 * @desc Developer NEO Company.
 * @created ${Date}
 * @updated ${Date}
 * @modified by
 * @updated on ${Date}
 * @since 1.0
 */
public class Fragment_Guide_Luggage extends BaseFragment {
    public static Fragment_Guide_Luggage fragment;

    public static synchronized Fragment_Guide_Luggage getInstance() {
        if (fragment == null) {
            fragment = new Fragment_Guide_Luggage();
        }
        return (fragment);
    }

    @BindView(R.id.rv_flight_procedures)
    RecyclerView rv_flight_procedures;
    RecyclerView.LayoutManager mLayoutManager;
    AdapterInstruction adapter;
    List<ObjWebview> mLisString;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flight_procedures, container, false);
        ButterKnife.bind(this, view);
        init();
        initData();
        return view;
    }

    private void init() {
        mLisString = new ArrayList<>();
        adapter = new AdapterInstruction(mLisString, getContext());
        mLayoutManager = new GridLayoutManager(getContext(), 1);
        //  rv_flight_procedures.setHasFixedSize(true);
        rv_flight_procedures.setLayoutManager(mLayoutManager);
        rv_flight_procedures.setItemAnimator(new DefaultItemAnimator());
        rv_flight_procedures.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        adapter.setOnIListener(new setOnItemClickListener() {
            @Override
            public void OnItemClickListener(int position) {
                Intent intent = new Intent(getActivity(), ActivityBooking.class);
                intent.putExtra(KEY_SEND_URL_WEBVIEW, mLisString.get(position));
                startActivity(intent);
            }

            @Override
            public void OnLongItemClickListener(int position) {

            }
        });

    }

    private void initData() {
        mLisString.add(new ObjWebview("Size and quantity of baggage", "Kích thước, khối lượng hành lý",
                R.drawable.icon_hl_kickthuoc,
                "http://noibai.online/nia/vi/klhanhly.html",
                ""));
        mLisString.add(new ObjWebview("Cabin baggage", "Hành lý xách tay",  R.drawable.icon_hl_xachtay,
                "http://noibai.online/nia/vi/hlxachtay.html",
                ""));
        mLisString.add(new ObjWebview("Hold baggage", "Hành lý ký gửi",  R.drawable.icon_hl_kygui,
                "http://noibai.online/nia/vi/hlkygui.html",
                ""));
        mLisString.add(new ObjWebview("Regulation for liquids",
                "Vật phẩm nguy hiểm được phép mang theo chuyến bay",  R.drawable.icon_hl_nguyhiem,
                "http://noibai.online/nia/vi/vatphamduocphep.html",
                ""));
        mLisString.add(new ObjWebview("Personal papers", "Giấy tờ tùy thân",  R.drawable.icon_hl_giaytotuythan,
                "http://noibai.online/nia/vi/giaytotuythan.html",
                ""));
        mLisString.add(new ObjWebview("Immigration and customs regulations",
                "Quy định về xuất nhập cảnh và hải quan",  R.drawable.icon_hl_haiquan,
                "http://noibai.online/nia/vi/quydinhhq.html",
                ""));
    }
}
