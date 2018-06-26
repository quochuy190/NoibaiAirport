package neo.com.noibai_airport.View.Activity.AirlineInfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import neo.com.noibai_airport.Adapter.AdapterListAirline;
import neo.com.noibai_airport.App;
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
 * @created ${Date}
 * @updated ${Date}
 * @modified by
 * @updated on ${Date}
 * @since 1.0
 */
public class ActivityListAirline extends BaseActivity implements ImlAirlineInfo.View{
    @Override
    public int setContentViewId() {
        return R.layout.activity_airline;
    }

    AdapterListAirline adapter;
    RecyclerView.LayoutManager mLayoutManager;
    List<AirlineInfo> mLisFlight;
    @BindView(R.id.recycle_list_airline)
    RecyclerView recycle_list_airline;
    PresenterAirlineInfo mPresenter;
    String sUserId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new PresenterAirlineInfo(this);
        initAppbar();
        init();
        initData();


    }

    private void initData() {
        sUserId = SharedPrefs.getInstance().get(Constants.KEY_USERID, String.class);
        showDialogLoading();
        mPresenter.get_list_airline(sUserId);

    }

    public void initAppbar() {
        ImageView img_search = findViewById(R.id.img_search);
        ImageView img_back = findViewById(R.id.img_back);
        TextView txt_title = findViewById(R.id.txt_title_main);
        ImageView img_chatbox = findViewById(R.id.img_chatbox);

        txt_title.setText(R.string.title_search_airline);
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

    private void init() {
        mLisFlight = new ArrayList<>();
        adapter = new AdapterListAirline(this, mLisFlight);
        mLayoutManager = new GridLayoutManager(this, 1);
        //recycle_category.setNestedScrollingEnabled(false);

        recycle_list_airline.setLayoutManager(mLayoutManager);
        recycle_list_airline.setItemAnimator(new DefaultItemAnimator());
        recycle_list_airline.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        adapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClickItem(int position, Object item) {
                AirlineInfo obj = (AirlineInfo) item;
               // ObjWebview objWebview = new ObjWebview(obj.getsName(), obj.getsName(), 0, obj.getsUrl_Vi(),obj.getsUrl_En());
                Intent intent = new Intent(ActivityListAirline.this, ActivityAirlineDetail.class);
                intent.putExtra(Constants.KEY_SEND_DETAIL_AIRLINE, obj);
                startActivity(intent);
            }
        });
    }

    @Override
    public void show_list_airline(List<AirlineInfo> listAirline) {
        hideDialogLoading();
        if (listAirline!=null&&listAirline.size()>0){
            mLisFlight.clear();
            App.lisAirline.addAll(listAirline);
            mLisFlight.addAll(listAirline);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void show_list_airport(List<Airport> lisAirport) {

    }

    @Override
    public void show_airline_detail(List<Airport> lisAirport) {
        hideDialogLoading();
    }


    @Override
    public void show_api_error(List<ErrorApi> lisError) {
        hideDialogLoading();
    }
}
