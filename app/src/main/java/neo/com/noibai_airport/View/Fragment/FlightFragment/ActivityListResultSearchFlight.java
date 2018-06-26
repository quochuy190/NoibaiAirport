package neo.com.noibai_airport.View.Fragment.FlightFragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import neo.com.noibai_airport.Config.Constants;
import neo.com.noibai_airport.Model.ErrorApi;
import neo.com.noibai_airport.Model.FlightInfo;
import neo.com.noibai_airport.Model.ObjSearchFlight;
import neo.com.noibai_airport.R;
import neo.com.noibai_airport.untils.BaseActivity;
import neo.com.noibai_airport.untils.FragmentUtil;

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
public class ActivityListResultSearchFlight extends BaseActivity implements ImlFlight.View {
    private static final String TAG = "ActivityListResultSearc";

    private String sUserId;
    Date mCalendar = Calendar.getInstance().getTime();
    private int iPage = 1;
    private int iIndex = 500;
    PresenterFlight mPresenter;

    RecyclerView.LayoutManager mLayoutManager;
    List<FlightInfo> mLisFlight;
    private ObjSearchFlight mObjSearch;
    private String sFlightType;

    @Override
    public int setContentViewId() {
        return R.layout.activity_list_result_flight;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new PresenterFlight(this);
        initAppbar();
     //   init();
        initData();

    }

    public void initAppbar() {
        ImageView img_search = findViewById(R.id.img_search);
        ImageView img_back = findViewById(R.id.img_back);
        TextView txt_title = findViewById(R.id.txt_title_main);
        ImageView img_chatbox = findViewById(R.id.img_chatbox);

        txt_title.setText(R.string.title_search);
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

    public List<FlightInfo> mLisFollow = new ArrayList<>();


    private void initData() {
        mObjSearch = (ObjSearchFlight) getIntent().getSerializableExtra(Constants.KEY_SENT_SEARCH_FLIGHT);
        Bundle objBundle = new Bundle();
        objBundle.putSerializable(Constants.KEY_SEND_FLIGHT_SEARCH, mObjSearch);
        if (mObjSearch.getsFlightType().equals("D"))
            FragmentUtil.replaceFragmentData(ActivityListResultSearchFlight.this, R.id.frame_search_flight,
                    FragmentSearchFlightDepartures.getInstance()
                    , true, objBundle);
        else {
            FragmentUtil.replaceFragmentData(ActivityListResultSearchFlight.this, R.id.frame_search_flight,
                    FragmentSearchFlightArrivals.getInstance()
                    , true, objBundle);
        }
    }

    @Override
    public void show_list_filghtinfo(List<FlightInfo> lisFlight) {
        hideDialogLoading();
    }

    @Override
    public void show_list_filghtinfo_earlier(List<FlightInfo> lisFlight) {
        hideDialogLoading();
    }

    @Override
    public void show_detail_flight(List<FlightInfo> lisFlight) {
        hideDialogLoading();
    }

    @Override
    public void show_result_flightfollow(List<ErrorApi> lisError) {
        hideDialogLoading();
    }

    @Override
    public void show_result_flightfollow_error(List<ErrorApi> lisError) {
        hideDialogLoading();
    }

    @Override
    public void show_get_api_error() {
        hideDialogLoading();
    }

    @Override
    public void onBackPressed() {
       finish();

    }
}
