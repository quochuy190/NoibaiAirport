package neo.com.noibai_airport.View.Activity.Feedback;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import neo.com.noibai_airport.Config.Constants;
import neo.com.noibai_airport.Model.Feedback;
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
public class ActivityAddFeedback extends BaseActivity implements ImlFeedback.View{
    @Override
    public int setContentViewId() {
        return R.layout.activity_add_feedback;
    }
    @BindView(R.id.txt_name_addfeddback)
    TextView txt_name_addfeddback;
    @BindView(R.id.txt_email_addfeddback)
    TextView txt_email_addfeddback;
    @BindView(R.id.btn_add_feedback)
    Button btn_add_feedback;
    @BindView(R.id.edt_content_addfeedback)
    EditText edt_content_addfeedback;
    @BindView(R.id.switch_public)
    Switch switch_public;
    private String sUserFeedback, sEmailFeedback, sUserId;
    private PresenterFeedback sPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sPresenter = new PresenterFeedback(this);
        initAppbar();
        initData();
        initEvent();
    }
    public void initAppbar() {
        ImageView img_search = findViewById(R.id.img_search);
        ImageView img_back = findViewById(R.id.img_back);
        TextView txt_title = findViewById(R.id.txt_title_main);
        ImageView img_chatbox = findViewById(R.id.img_chatbox);

        txt_title.setText(R.string.title_add_feedback);
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
        btn_add_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sPublic="";
                if (switch_public.isChecked()){
                    sPublic="1";
                }else sPublic="0";
                if (edt_content_addfeedback.getText().toString().length()>0){
                    sPresenter.add_new_feedback(sUserId,sUserFeedback, sEmailFeedback, edt_content_addfeedback.getText().toString()
                    ,sPublic);
                }else showAlertDialog("Thông báo", "Bạn chưa nhập vào đủ thông tin");
            }
        });
    }

    private void initData() {
        sUserId = SharedPrefs.getInstance().get(Constants.KEY_USERID, String.class);
        sUserFeedback = SharedPrefs.getInstance().get(Constants.KEY_USER_FEEDBACK, String.class);
        sEmailFeedback = SharedPrefs.getInstance().get(Constants.KEY_EMAIL_FEEDBACK, String.class);
        txt_email_addfeddback.setText("User: "+sEmailFeedback);
        txt_name_addfeddback.setText("Email: "+sUserFeedback);
    }

    @Override
    public void show_feedback(List<Feedback> mLisFeedback) {

    }

    @Override
    public void show_result_addfeedback() {
        edt_content_addfeedback.setText("");
        edt_content_addfeedback.setHint("- Nhập vào thông tin");
        Toast.makeText(this, "Thành công", Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK, new Intent());
        finish();
       /* showAlertDialog("Thông báo", "Thêm feedback thành công");*/

    }

    @Override
    public void show_error_addfeedback() {
        showAlertDialog("Lỗi", "Lỗi");
    }
}
