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
public class Fragment_Flight_Procedures extends BaseFragment {
    public static Fragment_Flight_Procedures fragment;

    public static synchronized Fragment_Flight_Procedures getInstance() {
        if (fragment == null) {
            fragment = new Fragment_Flight_Procedures();
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
        mLisString.add(new ObjWebview("International Arrival Passenger", "Hành khách quốc tế đến", R.drawable.icon_quoc_te_den,
                "http://noibai.online/nia/vi/qtden.html",
                "http://noibai.online/nia/en/qtden.html"));
        mLisString.add(new ObjWebview("International Departure Passenger", "Hành khách quốc tế đi",  R.drawable.icon_quoc_te_di,
                "http://noibai.online/nia/vi/qtdi.html",
                "http://noibai.online/nia/en/qtdi.html"));
        mLisString.add(new ObjWebview("Domestic Arrival Passenger", "Hành khách quốc nội đến",  R.drawable.icon_noi_dia_den,
                "http://noibai.online/nia/vi/qnoiden.html",
                ""));
        mLisString.add(new ObjWebview("Domestic Departure Passenger", "Hành khách quốc nội đi",  R.drawable.icon_noi_dia_di,
                "http://noibai.online/nia/vi/qnoidi.html",
                ""));
        mLisString.add(new ObjWebview("Connecting Passenger", "Hành khách nối chuyến",  R.drawable.icon_noi_tuyen,
                "http://noibai.online/nia/vi/noichuyen.html",
                ""));
        mLisString.add(new ObjWebview("Passenger Requested For Special Assistance", "Trợ giúp hành khách đặc biệt",  R.drawable.icon_trogiupdacbiet,
                "http://noibai.online/nia/vi/trogiupkhdb.html",
                ""));
        mLisString.add(new ObjWebview("Baggage Lost & Found", "Tìm hành lý thất lạc",  R.drawable.icon_hanh_ly,
                "http://noibai.online/nia/vi/thatlac.html",
                ""));

    }
}
