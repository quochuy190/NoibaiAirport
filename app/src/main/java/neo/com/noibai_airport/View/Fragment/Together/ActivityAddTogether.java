package neo.com.noibai_airport.View.Fragment.Together;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import neo.com.noibai_airport.App;
import neo.com.noibai_airport.Config.Constants;
import neo.com.noibai_airport.Model.District;
import neo.com.noibai_airport.Model.ErrorApi;
import neo.com.noibai_airport.Model.Together;
import neo.com.noibai_airport.Model.Ward;
import neo.com.noibai_airport.R;
import neo.com.noibai_airport.View.Activity.SearchUntils.ActivityListDistrict;
import neo.com.noibai_airport.View.Activity.SearchUntils.ActivityListQuantity;
import neo.com.noibai_airport.View.Activity.SearchUntils.ActivityListWard;
import neo.com.noibai_airport.untils.BaseActivity;
import neo.com.noibai_airport.untils.DialogUtil;
import neo.com.noibai_airport.untils.PhoneNumber;
import neo.com.noibai_airport.untils.SharedPrefs;

/**
 * @author Quốc Huy
 * @version 1.0.0
 * @description
 * @desc Developer NEO Company.
 * @created 7/9/2018
 * @updated 7/9/2018
 * @modified by
 * @updated on 7/9/2018
 * @since 1.0
 */
public class ActivityAddTogether extends BaseActivity implements ImlTogether.View {
    private static final String TAG = "ActivityAddTogether";
    @BindView(R.id.edt_district_add_togerther)
    EditText edtDistrict;
    @BindView(R.id.edt_ward_add_togerther)
    EditText edtWard;
    @BindView(R.id.edt_time_search_together)
    EditText edtTime;
    @BindView(R.id.edt_quantity_add_togerther)
    EditText edtQuantity;
    @BindView(R.id.edt_name_add_togerther)
    EditText edtName;
    @BindView(R.id.edt_phone_add_togerther)
    EditText edtPhone;
    @BindView(R.id.edt_add_together_description)
    EditText edtDescription;
    @BindView(R.id.btn_comfirm_add_together)
    Button btnConfirm;
    @BindView(R.id.radio_sex_all)
    RadioButton radioAll;
    @BindView(R.id.radio_sex_male)
    RadioButton radioMale;
    @BindView(R.id.radio_sex_female)
    RadioButton radioFemale;
    @BindView(R.id.radio_group)
    RadioGroup radio_group;
    String mGender = "0";
    PresenterTogether mPresenter;
    private String sUserId;
    public Together mTogether;
    @BindView(R.id.edt_price_add_togerther)
    EditText edtPrice;

    @Override
    public int setContentViewId() {
        return R.layout.activity_add_together;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new PresenterTogether(this);
        mGender = "0";
        radioAll.setChecked(true);
        initAppbar();
        initEvent();
    }

    private void initEvent() {
        edtDistrict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                App.mDistrict = null;
                App.mWard = null;
                Intent intent = new Intent(ActivityAddTogether.this, ActivityListDistrict.class);
                startActivityForResult(intent, Constants.RequestCode.GET_DISTRICT);
            }
        });
        edtWard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (App.mDistrict != null) {
                    App.mWard = null;
                    Intent intent = new Intent(ActivityAddTogether.this, ActivityListWard.class);
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
        edtQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                App.mQuantity = null;
                Intent intent = new Intent(ActivityAddTogether.this, ActivityListQuantity.class);
                startActivityForResult(intent, Constants.RequestCode.GET_QUANTITY);
            }
        });
        radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

            }
        });
        edtPrice.addTextChangedListener(new TextWatcher() {
            private String current = "";

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!s.toString().equals(current)) {
                    edtPrice.removeTextChangedListener(this);
                    String cleanString = s.toString().replaceAll(",", "");
                    Log.i(TAG, "onTextChanged: " + s);
                    Log.i(TAG, "onTextChanged: " + s.toString());
                    edtPrice.setText(formatDecimal(cleanString));
                    edtPrice.setSelection(edtPrice.getText().toString().length());
                    edtPrice.addTextChangedListener(this);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sUserId = SharedPrefs.getInstance().get(Constants.KEY_USERID, String.class);
                if (edtName.getText().toString().length() > 0) {
                    if (edtPhone.getText().toString().length() > 0) {
                        if (edtTime.getText().toString().length() > 0) {
                            if (edtQuantity.getText().toString().length() > 0) {
                                if (edtDistrict.getText().toString().length() > 0) {
                                    if (edtWard.getText().toString().length() > 0) {
                                        if (mGender.length() > 0) {
                                            if (edtDescription.getText().toString().length() > 0) {
                                                if (PhoneNumber.isPhoneNumber(edtPhone.getText().toString())) {
                                                    showDialogComfirm(getString(R.string.title_notify),
                                                            getString(R.string.message_comfirm_order),
                                                            false,
                                                            new DialogUtil.ClickDialog() {
                                                        @Override
                                                        public void onClickYesDialog() {
                                                            mTogether = new Together("", "", "", "", edtName.getText().toString(),
                                                                    edtPhone.getText().toString(), edtTime.getText().toString(), App.mQuantity, "1",
                                                                    App.mDistrict.getsID(), App.mWard.getsID(), mGender,
                                                                    "", edtDescription.getText().toString(), "",
                                                                    edtPrice.getText().toString().replace(",", "."),
                                                                    App.mDistrict.getsDISTRICT_NAME(), App.mWard.getsCOMMUNE_NAME());
                                                            showDialogLoading();
                                                            mPresenter.api_get_oder_together("", sUserId, edtName.getText().toString(),
                                                                    edtPhone.getText().toString(), edtTime.getText().toString(), App.mQuantity, "1",
                                                                    App.mDistrict.getsID(), App.mWard.getsID(), mGender, "",
                                                                    edtDescription.getText().toString(), edtPrice.getText().toString().
                                                                            replace(",", "."), "I", "0");

                                                        }

                                                        @Override
                                                        public void onClickNoDialog() {

                                                        }
                                                    });
                                                } else {
                                                    showAlertDialog(getString(R.string.error), getString(R.string.error_phone));
                                                    edtPhone.forceLayout();
                                                }
                                            } else {
                                                //gender empty
                                                showAlertDialog(getString(R.string.error), getString(R.string.error_name_description));
                                            }
                                        } else {
                                            //gender empty
                                            showAlertDialog(getString(R.string.error), getString(R.string.error_name_gender));
                                        }
                                    } else {
                                        //ward empty
                                        showAlertDialog(getString(R.string.error), getString(R.string.error_name_ward));
                                    }
                                } else {
                                    //district empty
                                    showAlertDialog(getString(R.string.error), getString(R.string.error_name_district));
                                }
                            } else {
                                //quantity empty
                                showAlertDialog(getString(R.string.error), getString(R.string.error_name_quantity));
                            }
                        } else {
                            //time empty
                            showAlertDialog(getString(R.string.error), getString(R.string.error_name_time));
                        }
                    } else {
                        //phone empty
                        showAlertDialog(getString(R.string.error), getString(R.string.error_name_phone));
                    }
                } else {
                    //name empty
                    showAlertDialog(getString(R.string.error), getString(R.string.error_name_empty));
                }
            }
        });
    }

    private String formatDecimal(String str) {
        BigDecimal parsed = new BigDecimal(str);
        // example pattern VND #,###.00
        DecimalFormat formatter = new DecimalFormat("###,###,###",
                new DecimalFormatSymbols(Locale.US));
        formatter.setRoundingMode(RoundingMode.UP);
        return formatter.format(parsed);
    }

    public void initAppbar() {
        ImageView img_search = findViewById(R.id.img_search);
        ImageView img_back = findViewById(R.id.img_back);
        TextView txt_title = findViewById(R.id.txt_title_main);
        ImageView img_chatbox = findViewById(R.id.img_chatbox);

        txt_title.setText(R.string.title_add_together);
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
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
            case Constants.RequestCode.GET_QUANTITY:
                if (App.mQuantity != null)
                    edtQuantity.setText(App.mQuantity);
                else
                    edtQuantity.setText("");
                break;
        }
    }

    SingleDateAndTimePickerDialog.Builder datePciekr;

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
        new SingleDateAndTimePickerDialog.Builder(ActivityAddTogether.this)
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
                            showAlertDialog(getString(R.string.error), getString(R.string.error_time));
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
    public void show_get_api_error() {
        hideDialogLoading();
    }

    @Override
    public void show_get_list_district(List<District> mList) {
        hideDialogLoading();
    }

    @Override
    public void show_get_list_ward(List<Ward> mList) {
        hideDialogLoading();
    }

    @Override
    public void show_get_list_together(List<Together> mList) {
        hideDialogLoading();
    }

    @Override
    public void show_oder_together(ErrorApi errorApi) {
        hideDialogLoading();
        if (errorApi.getsERROR().equals("0000")) {
            mTogether.setmId(errorApi.getsDORDER());
            mTogether.setMyOrder(true);
            SharedPrefs.getInstance().put(Constants.KEY_SAVE_TOGETHER_ORDER, mTogether);
            edtQuantity.setText("");
            edtTime.setText("");
            edtName.setText("");
            edtPhone.setText("");
            edtDistrict.setText("");
            edtWard.setText("");
            edtDescription.setText("");
            DialogUtil.showDialogComfirm(this,
                    getString(R.string.title_notify),
                    getString(R.string.message_notify),
                    getString(R.string.btn_ok),
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setResult(RESULT_OK, new Intent());
                            finish();
                        }
                    });
        } else {
            showAlertDialog(getString(R.string.error), errorApi.getsRESULT());
        }
    }

}
