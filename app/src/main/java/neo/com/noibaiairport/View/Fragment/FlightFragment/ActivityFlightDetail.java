package neo.com.noibaiairport.View.Fragment.FlightFragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import neo.com.noibaiairport.Config.Constants;
import neo.com.noibaiairport.Model.FlightInfo;
import neo.com.noibaiairport.R;
import neo.com.noibaiairport.untils.BaseActivity;
import neo.com.noibaiairport.untils.SharedPrefs;
import neo.com.noibaiairport.untils.TimeUtils;

import static neo.com.noibaiairport.Config.Constants.KEY_SENT_FLIGHT_TYPE;

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
public class ActivityFlightDetail extends BaseActivity implements ImlFlight.View {
    @BindView(R.id.img_back)
    ImageView img_back;
    @BindView(R.id.txt_title_main)
    TextView txt_title_main;
    @BindView(R.id.img_chatbox)
    ImageView img_chatbox;
    @BindView(R.id.toolbar_main)
    Toolbar toolbar_main;
    @BindView(R.id.txt_status)
    TextView txt_status;
    @BindView(R.id.txt_datetime_start)
    TextView txt_datetime_start;
    @BindView(R.id.txt_datetime_end)
    TextView txt_datetime_end;
    @BindView(R.id.txt_time_start)
    TextView txt_time_start;
    @BindView(R.id.txt_time_end)
    TextView txt_time_end;
    @BindView(R.id.txt_address_start)
    TextView txt_address_start;
    @BindView(R.id.txt_address_end)
    TextView txt_address_end;
    @BindView(R.id.txt_id_flight)
    TextView txt_id_flight;
    @BindView(R.id.txt_address_detail1)
    TextView txt_address_detail1;
    @BindView(R.id.txt_checkin_number)
    TextView txt_checkin_number;
    @BindView(R.id.txt_gate_boaring)
    TextView txt_gate_boaring;
    @BindView(R.id.txt_airline)
    TextView txt_airline;


    PresenterFlight mPresenter;
    private String sUserId;
    Date mCalendar = Calendar.getInstance().getTime();
    private FlightInfo mObjFlight;

    @Override
    public int setContentViewId() {
        return R.layout.fragment_flight_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new PresenterFlight(this);
        initAppbar();
        initData();
    }

    private void initData() {
        FlightInfo objFlight = (FlightInfo) getIntent().getSerializableExtra(Constants.KEY_SENT_FLIGHT);
        String sType = getIntent().getStringExtra(KEY_SENT_FLIGHT_TYPE);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String currentDateandTime = sdf.format(mCalendar);
        sUserId = SharedPrefs.getInstance().get(Constants.KEY_USERID, String.class);
        if (objFlight != null && objFlight.getmFlight_Number() != null)
            mPresenter.get_detail_flight(sUserId, sType, currentDateandTime, objFlight.getmFlight_Number());
    }

    private void initAppbar() {
        toolbar_main.setTitle("");
        setSupportActionBar(toolbar_main);

        img_chatbox.setVisibility(View.GONE);
        img_back.setVisibility(View.VISIBLE);
        txt_title_main.setText(R.string.appbar_flightdetail);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    public void show_list_filghtinfo(List<FlightInfo> lisFlight) {

    }

    @Override
    public void show_detail_flight(List<FlightInfo> lisFlight) {
        if (lisFlight.size() > 0)
           // showAlertDialog("Thông báo", lisFlight.size()+"");
            mObjFlight = lisFlight.get(0);
        updateDataFlight();
    }

    private void updateDataFlight() {
        if (mObjFlight != null) {
            if (mObjFlight.getM_Notification() != null) {
                txt_status.setText(mObjFlight.getM_Notification());
            }
            if (mObjFlight.getmFlight_Number() != null) {
                txt_id_flight.setText(mObjFlight.getmFlight_Number());
            }
            if (mObjFlight.getmDay_start() != null) {
                txt_datetime_start.setText(TimeUtils.convent_date(mObjFlight.getmDay_start(),
                        "yyyy-MM-dd hh:mm:ss", "EEE, dd/MM/yyyy"));
            }
            if (mObjFlight.getmTime_star() != null) {
                txt_time_start.setText(mObjFlight.getmTime_star());
            }
            if (mObjFlight.getmDay_end() != null) {
                txt_datetime_end.setText(mObjFlight.getmDay_end());
            }
            if (mObjFlight.getmTime_end() != null) {
                txt_time_end.setText(mObjFlight.getmTime_end());
            }

            if (mObjFlight.getmAirportDepartures() != null) {
                txt_address_start.setText(mObjFlight.getmAirportDepartures());
            }
            if (mObjFlight.getmAirportArrivals() != null) {
                txt_address_end.setText(mObjFlight.getmAirportArrivals());
            }
            if (mObjFlight.getmTERMINAL() != null) {
                txt_address_detail1.setText(mObjFlight.getmTERMINAL());
            }
            if (mObjFlight.getmBOARDINGGATE() != null&&mObjFlight.getmBOARDINGGATE().length()>0) {
                txt_gate_boaring.setText(mObjFlight.getmBOARDINGGATE());
            }else
                txt_gate_boaring.setText("-");
            if (mObjFlight.getmCHECKINCOUTER() != null && mObjFlight.getmCHECKINCOUTER().length() > 0) {
                txt_checkin_number.setText(mObjFlight.getmCHECKINCOUTER());
            } else {
                txt_checkin_number.setText("-");
            }
        }
    }
}
