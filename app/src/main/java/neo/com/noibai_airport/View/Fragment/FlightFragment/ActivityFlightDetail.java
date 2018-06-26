package neo.com.noibai_airport.View.Fragment.FlightFragment;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import io.realm.Realm;
import neo.com.noibai_airport.Config.Constants;
import neo.com.noibai_airport.Model.ErrorApi;
import neo.com.noibai_airport.Model.FlightInfo;
import neo.com.noibai_airport.R;
import neo.com.noibai_airport.RealmController.RealmController;
import neo.com.noibai_airport.untils.BaseActivity;
import neo.com.noibai_airport.untils.SharedPrefs;
import neo.com.noibai_airport.untils.TimeUtils;

import static neo.com.noibai_airport.Config.Constants.KEY_SENT_FLIGHT_TYPE;

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
    @BindView(R.id.txt_address_detail1)
    TextView txt_address_detail1;
    @BindView(R.id.txt_checkin_number)
    TextView txt_checkin_number;
    @BindView(R.id.txt_lable_checkin)
    TextView txt_lable_checkin;
    @BindView(R.id.txt_gate_boaring)
    TextView txt_gate_boaring;
    @BindView(R.id.bnt_track_light)
    Button bnt_track_light;
    @BindView(R.id.img_avata_airline_detailflight)
    ImageView img_avata_airline_detailflight;
    PresenterFlight mPresenter;
    private String sUserId;
    Date mCalendar = Calendar.getInstance().getTime();
    private FlightInfo mObjFlight;
    String sType;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String currentDateandTime = sdf.format(mCalendar);
    public Realm realm;
    public boolean isTrack = false;
    @BindView(R.id.txt_address_detail2)
    TextView txt_Terminal2;

    @Override
    public int setContentViewId() {
        return R.layout.fragment_flight_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new PresenterFlight(this);
        realm = RealmController.with(this).getRealm();
        initAppbar();
        initData();
        initEvent();
    }

    private void initEvent() {
        bnt_track_light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isTrack) {
                    showDialogLoading();
                    mPresenter.set_follow_flight(sUserId, sType, currentDateandTime, "1", mObjFlight.getmFlight_Number());
                } else {
                    showDialogLoading();
                    mPresenter.set_follow_flight(sUserId, sType, currentDateandTime, "0", mObjFlight.getmFlight_Number());
                }

            }
        });
    }

    private void initData() {
        FlightInfo objFlight = (FlightInfo) getIntent().getSerializableExtra(Constants.KEY_SENT_FLIGHT);
        sType = getIntent().getStringExtra(KEY_SENT_FLIGHT_TYPE);

        sUserId = SharedPrefs.getInstance().get(Constants.KEY_USERID, String.class);
        if (objFlight != null && objFlight.getmFlight_Number() != null) {
            showDialogLoading();
            mPresenter.get_detail_flight(sUserId, objFlight.getsFlightType(),
                    currentDateandTime, objFlight.getmFlight_Number());
        }
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
    public void show_list_filghtinfo_earlier(List<FlightInfo> lisFlight) {

    }

    @Override
    public void show_detail_flight(List<FlightInfo> lisFlight) {
        hideDialogLoading();
        if (lisFlight.size() > 0) {
            mObjFlight = lisFlight.get(0);
        }
        updateDataFlight();
    }

    @Override
    public void show_result_flightfollow(List<ErrorApi> lisError) {
        hideDialogLoading();
        if (!isTrack) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                bnt_track_light.setBackground(getResources().getDrawable(R.drawable.spr_button_tracked));
                bnt_track_light.setText(getString(R.string.btn_unfollow_flight));
            } else {

            }
            if (mObjFlight != null) {
                mObjFlight.setsFlightType(sType);
                mObjFlight.setFollow(true);
                add_Flight_toRealm(mObjFlight);
            }
          /*  if (sType.equals("D"))
                SharedPrefs.getInstance().put(Constants.KEY_CHANGE_VIEWFOLLOW, true);
            else if (sType.equals("A"))
                SharedPrefs.getInstance().put(Constants.KEY_ISFOLLOW_ARRIVALS, true);*/
            finish();
            Toast.makeText(this, getString(R.string.show_error_follow_flight), Toast.LENGTH_SHORT).show();
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                bnt_track_light.setBackground(getResources().getDrawable(R.drawable.spr_button_track));
                bnt_track_light.setText(getString(R.string.btn_track_flight));
            } else {

            }
            if (mObjFlight != null) {
                RealmController.getInstance().remove_flight(mObjFlight);
            }
   /*         if (sType.equals("D"))
                SharedPrefs.getInstance().put(Constants.KEY_CHANGE_VIEWFOLLOW, true);
            else if (sType.equals("A"))
                SharedPrefs.getInstance().put(Constants.KEY_ISFOLLOW_ARRIVALS, true);*/
            finish();
            Toast.makeText(this, "UnFollow", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void show_result_flightfollow_error(List<ErrorApi> lisError) {
        hideDialogLoading();
        if (lisError != null && lisError.get(0).getsRESULT() != null) {
            showAlertDialog(getString(R.string.error), lisError.get(0).getsRESULT());
        } else
            showAlertDialog(getString(R.string.error), getString(R.string.show_failed_follw_flight));
    }

    @Override
    public void show_get_api_error() {
        hideDialogLoading();
        showAlertDialog(getString(R.string.error), getString(R.string.error_network_message));
    }

    private void updateDataFlight() {
        if (mObjFlight != null) {
            List<FlightInfo> lisFlightRealm = realm.where(FlightInfo.class).findAll();
            if (lisFlightRealm != null && lisFlightRealm.size() > 0) {
                for (int i = 0; i < lisFlightRealm.size(); i++) {
                    if (lisFlightRealm.get(i).getmFlight_Number() != null &&
                            lisFlightRealm.get(i).getmFlight_Number().equals(mObjFlight.getmFlight_Number())) {
                        isTrack = true;
                        if (mObjFlight != null) {
                            mObjFlight.setsFlightType(sType);
                            mObjFlight.setFollow(true);
                            add_Flight_toRealm(mObjFlight);
                        }
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            bnt_track_light.setBackground(getResources().getDrawable(R.drawable.spr_button_tracked));
                            bnt_track_light.setText(getString(R.string.btn_unfollow_flight));
                        } else {

                        }
                    }
                }
            }
            //Check là chuyến bay đi
            if (sType != null && sType.length() > 0 && sType.equals("D")) {
                if (mObjFlight.getmDay_start() != null && mObjFlight.getmDay_start().length() > 0) {
                    txt_datetime_start.setText(TimeUtils.convent_date(mObjFlight.getmDay_start(),
                            "yyyy-MM-dd hh:mm:ss", "EEE, dd/MM/yyyy"));
                }
                if (mObjFlight.getmTime_departure() != null &&
                        mObjFlight.getmTime_departure().length() > 0) {
                    txt_time_start.setText(mObjFlight.getmTime_departure());
                } else
                    txt_time_start.setText("-");

                if (mObjFlight.getmFlight_Number() != null && mObjFlight.getmFlight_Number().length() > 0) {
                    txt_time_end.setText(mObjFlight.getmFlight_Number());
                } else {
                    txt_time_end.setText("-");
                }
                if (mObjFlight.getmNAME() != null) {
                    txt_datetime_end.setText(mObjFlight.getmNAME());
                }
                txt_lable_checkin.setText(R.string.check_in_counte);
                if (mObjFlight.getmBOARDINGGATE() != null && mObjFlight.getmBOARDINGGATE().length() > 0) {
                    txt_gate_boaring.setText(mObjFlight.getmBOARDINGGATE());
                } else
                    txt_gate_boaring.setText("-");
                if (mObjFlight.getmTERMINAL() != null && mObjFlight.getmTERMINAL().length() > 0) {
                    txt_address_detail1.setText(getString(R.string.terminal) + ": " + mObjFlight.getmTERMINAL());
                } else {
                    txt_address_detail1.setText(getString(R.string.terminal) + ": -");
                }
            } else
                //Check là chuyến bay đến
                if (sType != null && sType.length() > 0 && sType.equals("A")) {
                    if (mObjFlight.getmDay_start() != null && mObjFlight.getmDay_start().length() > 0) {
                        txt_datetime_end.setText(TimeUtils.convent_date(mObjFlight.getmDay_start(),
                                "yyyy-MM-dd hh:mm:ss", "EEE, dd/MM/yyyy"));
                    } else {
                        txt_datetime_end.setText("-");
                    }
                    if (mObjFlight.getmTime_arrival() != null && mObjFlight.getmTime_arrival().length() > 0) {
                        txt_time_end.setText(mObjFlight.getmTime_arrival());
                    } else
                        txt_time_end.setText("-");

                    if (mObjFlight.getmFlight_Number() != null && mObjFlight.getmFlight_Number().length() > 0) {
                        txt_time_start.setText(mObjFlight.getmFlight_Number());
                    } else {
                        txt_time_start.setText("-");
                    }
                    if (mObjFlight.getmNAME() != null) {
                        txt_datetime_start.setText(mObjFlight.getmNAME());
                    } else {
                        txt_datetime_start.setText("-");
                    }
                    txt_lable_checkin.setText(R.string.lobby);
                    if (mObjFlight.getmLOBBY() != null && mObjFlight.getmLOBBY().length() > 0) {
                        txt_gate_boaring.setText(mObjFlight.getmLOBBY());
                    } else
                        txt_gate_boaring.setText("-");
                    if (mObjFlight.getmTERMINAL() != null && mObjFlight.getmTERMINAL().length() > 0) {
                        txt_Terminal2.setText(getString(R.string.terminal) + ": " + mObjFlight.getmTERMINAL());
                    } else {
                        txt_Terminal2.setText(getString(R.string.terminal) + ": -");
                    }
                }
            if (mObjFlight.getM_Notification() != null && mObjFlight.getM_Notification().length() > 0) {
                txt_status.setText(mObjFlight.getM_Notification());
            } else {
                txt_status.setText("-");
            }

            if (mObjFlight.getmAirportDepartures() != null) {
                txt_address_start.setText(mObjFlight.getmAirportDepartures());
            }
            if (mObjFlight.getmAirportArrivals() != null) {
                txt_address_end.setText(mObjFlight.getmAirportArrivals());
            }
            if (mObjFlight.getmCHECKINCOUTER() != null && mObjFlight.getmCHECKINCOUTER().length() > 0) {
                txt_checkin_number.setText(mObjFlight.getmCHECKINCOUTER());
            } else {
                txt_checkin_number.setText("-");
            }
            Glide.with(this).load(mObjFlight.getmAVATAR())
                    .error(R.drawable.icon_may_bay)
                    .into(img_avata_airline_detailflight);
        }
    }

    public void add_Flight_toRealm(FlightInfo objFlight) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(objFlight);
        realm.commitTransaction();
    }
}
