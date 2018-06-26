package neo.com.noibai_airport.View.Activity.Feedback;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import neo.com.noibai_airport.Config.Constants;
import neo.com.noibai_airport.R;
import neo.com.noibai_airport.untils.BaseActivity;
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
public class ActivityRegister extends BaseActivity {
    @Override
    public int setContentViewId() {
        return R.layout.activity_register;
    }

    @BindView(R.id.edt_email_register)
    EditText edt_email_register;
    @BindView(R.id.edt_user_register)
    EditText edt_user_register;
    @BindView(R.id.btn_comfirm_register)
    Button btn_comfirm_register;
    @BindView(R.id.btn_cancel_register)
    Button btn_cancel_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initAppbar();
        initData();
        initEvent();

    }
    public void initAppbar() {
        ImageView img_search = findViewById(R.id.img_search);
        ImageView img_back = findViewById(R.id.img_back);
        TextView txt_title = findViewById(R.id.txt_title_main);
        ImageView img_chatbox = findViewById(R.id.img_chatbox);

        txt_title.setText(R.string.title_register);
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
    String sType;

    private void initData() {
        sType = getIntent().getStringExtra(Constants.KEY_SEND_REGISTER);
    }

    private void initEvent() {
        btn_comfirm_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_email_register.getText().toString().length() > 0
                        && edt_user_register.getText().toString().length() > 0) {
                    SharedPrefs.getInstance().put(Constants.KEY_IS_LOGIN_FEEDBACK, true);
                    SharedPrefs.getInstance().put(Constants.KEY_USER_FEEDBACK, edt_user_register.getText().toString());
                    SharedPrefs.getInstance().put(Constants.KEY_EMAIL_FEEDBACK, edt_email_register.getText().toString());
                    if (sType.equals(Constants.FEEDBACK)) {
                        startActivity(new Intent(ActivityRegister.this, ActivityAddFeedback.class));
                        finish();
                    } else if (sType.equals(Constants.COMMENTS)) {
                        //startActivity(new Intent(ActivityRegister.this, ActivityComments.class));
                        finish();
                    } else finish();

                } else showAlertDialog("Thông báo", "Bạn chưa nhập vào đủ thông tin");
            }
        });
        btn_cancel_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
