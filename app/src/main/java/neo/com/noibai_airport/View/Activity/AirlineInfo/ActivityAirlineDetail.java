package neo.com.noibai_airport.View.Activity.AirlineInfo;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import neo.com.noibai_airport.Adapter.AdapterListRoute;
import neo.com.noibai_airport.Config.Constants;
import neo.com.noibai_airport.Model.AirlineInfo;
import neo.com.noibai_airport.Model.Airport;
import neo.com.noibai_airport.Model.ErrorApi;
import neo.com.noibai_airport.R;
import neo.com.noibai_airport.untils.BaseActivity;
import neo.com.noibai_airport.untils.ItemClickListener;
import neo.com.noibai_airport.untils.SharedPrefs;

/**
 * @author Quốc Huy
 * @version 1.0.0
 * @description
 * @desc Developer NEO Company.
 * @created 6/20/2018
 * @updated 6/20/2018
 * @modified by
 * @updated on 6/20/2018
 * @since 1.0
 */
public class ActivityAirlineDetail extends BaseActivity implements ImlAirlineInfo.View {
    private String sUserId;

    @Override
    public int setContentViewId() {
        return R.layout.activity_airline_detail;
    }

    PresenterAirlineInfo mPresenter;
    AirlineInfo objAirline;
    @BindView(R.id.img_banner_airline_detail)
    ImageView img_banner_airline_detail;
    @BindView(R.id.txt_location_airlinedetail)
    TextView txt_location_airlinedetail;
    @BindView(R.id.txt_hotline_airlinedetail)
    TextView txt_hotline_airlinedetail;
    @BindView(R.id.btn_checkin_airlinedetail)
    TextView btn_checkin_airlinedetail;
    @BindView(R.id.btn_boking_airlinedetail)
    TextView btn_boking_airlinedetail;
    @BindView(R.id.rv_destination_list)
    RecyclerView rv_destination_list;
    AdapterListRoute adapter;
    RecyclerView.LayoutManager mLayoutManager;
    List<Airport> mLisFlight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new PresenterAirlineInfo(this);
        initAppbar();
        init();
        initData();

    }
    private void init() {
        mLisFlight = new ArrayList<>();
        adapter = new AdapterListRoute(this, mLisFlight);
        mLayoutManager = new GridLayoutManager(this, 1);
        rv_destination_list.setNestedScrollingEnabled(false);

        rv_destination_list.setLayoutManager(mLayoutManager);
        rv_destination_list.setItemAnimator(new DefaultItemAnimator());
        rv_destination_list.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        adapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClickItem(int position, Object item) {

            }
        });
    }

    private void initData() {
        objAirline = (AirlineInfo) getIntent().getSerializableExtra(Constants.KEY_SEND_DETAIL_AIRLINE);
        if (objAirline.getsAddress()!=null){
            txt_title.setText(objAirline.getsName());
            txt_location_airlinedetail.setText(objAirline.getsAddress());
        }
        if (objAirline.getsPhone()!=null){
            txt_hotline_airlinedetail.setText(objAirline.getsPhone());
        }
        if (objAirline.getsImage()!=null){
         //   txt_location_airlinedetail.setText(objAirline.getsAddress());
            Glide.with(this)
                    .load(objAirline.getsImage())
                    .error(R.drawable.icon_may_bay)
                    .into(img_banner_airline_detail);
        }
        if (objAirline.getsAIRLINEID()!=null){
            sUserId = SharedPrefs.getInstance().get(Constants.KEY_USERID, String.class);
            showDialogLoading();
            mPresenter.get_airline_detail(sUserId, objAirline.getsAIRLINEID());
        }else   txt_title.setText(R.string.title_airline_detail);


    }
    TextView txt_title;
    public void initAppbar() {
        ImageView img_search = findViewById(R.id.img_search);
        ImageView img_back = findViewById(R.id.img_back);
        txt_title = findViewById(R.id.txt_title_main);
        ImageView img_chatbox = findViewById(R.id.img_chatbox);


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


    @Override
    public void show_list_airline(List<AirlineInfo> lisAirline) {
        hideDialogLoading();
    }

    @Override
    public void show_list_airport(List<Airport> lisAirport) {

    }

    @Override
    public void show_airline_detail(List<Airport> lisAirport) {
        hideDialogLoading();
        if (lisAirport!=null&&lisAirport.size()>0){
            mLisFlight.clear();
            mLisFlight.addAll(lisAirport);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void show_api_error(List<ErrorApi> lisError) {
        hideDialogLoading();
    }
}
