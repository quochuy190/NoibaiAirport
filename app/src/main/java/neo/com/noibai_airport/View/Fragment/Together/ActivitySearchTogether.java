package neo.com.noibai_airport.View.Fragment.Together;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import neo.com.noibai_airport.App;
import neo.com.noibai_airport.Config.Constants;
import neo.com.noibai_airport.Model.SearchToghther;
import neo.com.noibai_airport.R;
import neo.com.noibai_airport.View.Activity.SearchUntils.ActivityListDistrict;
import neo.com.noibai_airport.View.Activity.SearchUntils.ActivityListWard;
import neo.com.noibai_airport.untils.BaseActivity;

/**
 * @author Quốc Huy
 * @version 1.0.0
 * @description
 * @desc Developer NEO Company.
 * @created 7/13/2018
 * @updated 7/13/2018
 * @modified by
 * @updated on 7/13/2018
 * @since 1.0
 */
public class ActivitySearchTogether extends BaseActivity {
    private static final String TAG = "ActivitySearchTogether";
    @BindView(R.id.edt_district_add_togerther)
    EditText edtDistrict;
    @BindView(R.id.edt_ward_add_togerther)
    EditText edtWard;
    @BindView(R.id.edt_time_search_together)
    EditText edtTime;
    @BindView(R.id.btn_comfirm_add_together)
    Button btnSearch;
    @BindView(R.id.radio_sex_all)
    RadioButton radioAll;
    @BindView(R.id.radio_sex_male)
    RadioButton radioMale;
    @BindView(R.id.radio_sex_female)
    RadioButton radioFemale;
    @BindView(R.id.radio_group)
    RadioGroup radio_group;
    String mGender = "";
    String mDistrictId="",mPrecinctId="", mTime="";
    @Override
    public int setContentViewId() {
        return R.layout.activity_search_together;
    }
    public void initAppbar() {
        ImageView img_search = findViewById(R.id.img_search);
        ImageView img_back = findViewById(R.id.img_back);
        TextView txt_title = findViewById(R.id.txt_title_main);
        ImageView img_chatbox = findViewById(R.id.img_chatbox);

        txt_title.setText(R.string.title_search_new);
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGender="0";
        initAppbar();
        initEvent();

    }

    private void initEvent() {
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (edtDistrict.getText().toString().length()>0||edtWard.getText().toString().length()>0
                       ||edtTime.getText().length()>0||mGender.length()>0){
                   if (App.mDistrict!=null){
                       mDistrictId = App.mDistrict.getsID();
                   }
                   if (App.mWard!=null){
                       mPrecinctId = App.mWard.getsID();
                   }
                   if (edtTime.getText().toString().length()>0)
                       mTime = edtTime.getText().toString();
                   SearchToghther obj = new SearchToghther(mDistrictId, mPrecinctId,
                           mTime, mGender);
                   App.mSearchTogether=obj;
                   setResult(RESULT_OK, new Intent());
                   finish();
               }else showAlertDialog(getString(R.string.error),
                       getString(R.string.error_not_param));
            }
        });
        edtDistrict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                App.mDistrict = null;
                App.mWard = null;
                Intent intent = new Intent(ActivitySearchTogether.this, ActivityListDistrict.class);
                startActivityForResult(intent, Constants.RequestCode.GET_DISTRICT);
            }
        });
        edtWard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (App.mDistrict != null) {
                    App.mWard = null;
                    Intent intent = new Intent(ActivitySearchTogether.this, ActivityListWard.class);
                    startActivityForResult(intent, Constants.RequestCode.GET_WARD);
                } else
                    showAlertDialog(getString(R.string.error), getString(R.string.error_get_ward));
            }
        });
        edtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_date_picker(edtTime);
            }
        });
    }

    public void show_date_picker(final EditText editText) {
        final Date now = new Date();
        final Calendar calendar = Calendar.getInstance();
        final Calendar calendarMin = Calendar.getInstance();
        final Calendar calendarMax = Calendar.getInstance();
        final Date defaultDate = calendar.getTime();
        calendarMin.add(Calendar.DAY_OF_YEAR, -1);// Set min now
        calendarMax.add(Calendar.DAY_OF_YEAR, 1);

        final Date minDate = calendarMin.getTime();
        final Date maxDate = calendarMax.getTime();
        new SingleDateAndTimePickerDialog.Builder(ActivitySearchTogether.this)
                //.bottomSheet()
                .curved()
                .minutesStep(15)
                .mainColor(Color.BLUE)
                .title(getString(R.string.title_datetime))
                .listener(new SingleDateAndTimePickerDialog.Listener() {
                    @Override
                    public void onDateSelected(Date date) {
                        if (date.after(defaultDate) && date.before(maxDate)) {
                            SimpleDateFormat dft = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss",
                                    Locale.getDefault());
                            String strDate = dft.format(date);
                            //hiển thị lên giao diện
                            editText.setText(strDate);
                        } else {
                            showAlertDialog(getString(R.string.error),
                                    getString(R.string.error_time));
                        }

                    }
                })
                .display();
    }
    public void onRadioButtonClicked(View view) {
        //	Check to see if a button has been clicked.
        boolean checked = ((android.widget.RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.radio_sex_all:
                if (checked) {
                    mGender = "0";
                    //Toast.makeText(this, "sameday", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.radio_sex_male:
                if (checked) {
                    mGender = "1";
                    //Toast.makeText(this, "nextday", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.radio_sex_female:
                if (checked) {
                    mGender = "2";
                    //Toast.makeText(this, "pickup", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            //
            case Constants.RequestCode.GET_DISTRICT:
                if (App.mDistrict != null) {
                    edtDistrict.setText(App.mDistrict.getsDISTRICT_NAME());
                    edtWard.setText("");
                } else {
                    edtDistrict.setText("");
                    edtWard.setText("");
                }
                break;
            case Constants.RequestCode.GET_WARD:
                if (App.mWard != null)
                    edtWard.setText(App.mWard.getsCOMMUNE_NAME());
                else
                    edtWard.setText("");
                break;
        }
    }
}
