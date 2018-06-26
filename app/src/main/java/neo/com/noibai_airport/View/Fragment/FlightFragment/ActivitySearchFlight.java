package neo.com.noibai_airport.View.Fragment.FlightFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import neo.com.noibai_airport.App;
import neo.com.noibai_airport.Config.Constants;
import neo.com.noibai_airport.Model.ObjSearchFlight;
import neo.com.noibai_airport.R;
import neo.com.noibai_airport.View.Activity.SearchUntils.ActivityListAirline_Search;
import neo.com.noibai_airport.View.Activity.SearchUntils.ActivityListAirport_Search;
import neo.com.noibai_airport.untils.BaseActivity;

import static neo.com.noibai_airport.Config.Constants.KEY_SENT_SEARCH_FLIGHT;

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
public class ActivitySearchFlight extends BaseActivity {
    private static final String TAG = "ActivitySearchFlight";
    @BindView(R.id.edt_search_flightnumber)
    EditText edt_search_flightnumber;
    @BindView(R.id.edt_search_airport)
    EditText edt_search_airport;
    @BindView(R.id.edt_search_date)
    EditText edt_search_date;
    @BindView(R.id.relative_search_date)
    RelativeLayout relative_search_date;
    @BindView(R.id.edt_search_airline)
    EditText edt_search_airline;
    @BindView(R.id.btn_search)
    Button btn_search;
    ObjSearchFlight mObjSearch;
    Calendar cal;
    Date date;
    SimpleDateFormat dft = null;
    private boolean isDepartures = false;
    @BindView(R.id.txt_search_departure)
    TextView txt_search_departure;
    @BindView(R.id.txt_search_arrivals)
    TextView txt_search_arrivals;

    @Override
    public int setContentViewId() {
        return R.layout.activity_search_flight;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.mAirportSearch =null;
        App.mAirlineSearch = null;
        initAppbar();
        init();
        initEvent();

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

    private void initEvent() {
        txt_search_arrivals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isDepartures) {
                    txt_search_arrivals.setTextColor(getResources().getColor(R.color.red));
                    txt_search_departure.setTextColor(getResources().getColor(R.color.text_flight));
                    isDepartures = !isDepartures;
                    App.mAirportSearch = null;
                    edt_search_airport.setText("");
                    edt_search_airport.setHint(R.string.edt_search_airport_arrivals);
                    App.mAirlineSearch = null;
                    edt_search_airline.setText("");
                    edt_search_airline.setHint(R.string.edt_search_airline_departures);
                }
            }
        });
        txt_search_departure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isDepartures) {
                    txt_search_departure.setTextColor(getResources().getColor(R.color.red));
                    txt_search_arrivals.setTextColor(getResources().getColor(R.color.text_flight));
                    isDepartures = !isDepartures;
                    App.mAirportSearch = null;
                    edt_search_airport.setText("");
                    edt_search_airport.setHint(R.string.edt_search_airport_departures);
                    App.mAirlineSearch = null;
                    edt_search_airline.setText("");
                    edt_search_airline.setHint(R.string.edt_search_airline_departures);
                }
            }
        });
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_search_flightnumber.getText().toString().length() > 0)
                    mObjSearch.setsFlightNumber(edt_search_flightnumber.getText().toString());
                else
                    mObjSearch.setsFlightNumber("");
                if (App.mAirportSearch!=null&&App.mAirportSearch.getsDESTINATIONCODE().length()>0)
                    mObjSearch.setsFlightAirport(App.mAirportSearch.getsDESTINATIONCODE());
                else
                    mObjSearch.setsFlightAirport("");
                mObjSearch.setsFlightDatetime(edt_search_date.getText().toString());
                if (App.mAirlineSearch!=null&&App.mAirlineSearch.getsAIRLINEID().length()>0)
                    mObjSearch.setsFlightAirline(App.mAirlineSearch.getsAIRLINEID());
                else
                    mObjSearch.setsFlightAirline("");
                if (!isDepartures) {
                    mObjSearch.setsFlightType("D");
                } else
                    mObjSearch.setsFlightType("A");
                if (mObjSearch.getsFlightDatetime() != null && mObjSearch.getsFlightDatetime().length() > 0) {
                    Intent intent = new Intent(ActivitySearchFlight.this, ActivityListResultSearchFlight.class);
                    intent.putExtra(KEY_SENT_SEARCH_FLIGHT, mObjSearch);
                    startActivity(intent);
                } else
                    showAlertDialog("Thông báo", "Bạn phải nhập vào ngày tháng để tìm kiếm");
            }
        });
        edt_search_airline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                App.mAirlineSearch = null;
                Intent intent = new Intent(ActivitySearchFlight.this, ActivityListAirline_Search.class);
                startActivityForResult(intent, Constants.RequestCode.GET_AIRLINE);
            }
        });
        edt_search_airport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                App.mAirportSearch = null;
                Intent intent = new Intent(ActivitySearchFlight.this, ActivityListAirport_Search.class);
                startActivityForResult(intent, Constants.RequestCode.GET_AIRPORT);
            }
        });
        relative_search_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        edt_search_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
    }

    private void init() {
        //Set ngày giờ hiện tại khi mới chạy lần đầu
        cal = Calendar.getInstance();
        //Định dạng kiểu ngày / tháng /năm
        dft = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String strDate = dft.format(cal.getTime());
        //hiển thị lên giao diện
        edt_search_date.setText(strDate);
        mObjSearch = new ObjSearchFlight();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
    private void showDatePickerDialog() {
        DatePickerDialog.OnDateSetListener callback = new DatePickerDialog.OnDateSetListener() {
            //Sự kiện khi click vào nút Done trên Dialog
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // Set text cho textView
                edt_search_date.setText(day + "/" + (month + 1) + "/" + year);
                //Lưu vết lại ngày mới cập nhật
                //cal.set(year, month, day);
                //date = cal.getTime();
            }
        };


        String s = edt_search_date.getText() + "";
        //Lấy ra chuỗi của textView Date
        String strArrtmp[] = s.split("/");
        int ngay = Integer.parseInt(strArrtmp[0]);
        int thang = Integer.parseInt(strArrtmp[1]) - 1;
        int nam = Integer.parseInt(strArrtmp[2]);
        //Hiển thị ra Dialog
        DatePickerDialog pic = new DatePickerDialog(
                ActivitySearchFlight.this, callback, nam, thang, ngay);
        pic.setTitle("Chọn ngày hoàn thành");
        pic.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Constants.RequestCode.GET_AIRLINE:
                if (App.mAirlineSearch != null)
                    edt_search_airline.setText(App.mAirlineSearch.getsName());
                else
                    edt_search_airline.setText("");
                break;
            case Constants.RequestCode.GET_AIRPORT:
                if (App.mAirportSearch != null)
                    edt_search_airport.setText(App.mAirportSearch.getsName());
                else
                    edt_search_airport.setText("");
                break;
        }
    }
}
