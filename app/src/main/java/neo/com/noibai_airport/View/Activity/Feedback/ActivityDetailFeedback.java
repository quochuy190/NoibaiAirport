package neo.com.noibai_airport.View.Activity.Feedback;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import neo.com.noibai_airport.Config.Constants;
import neo.com.noibai_airport.Model.Feedback;
import neo.com.noibai_airport.R;
import neo.com.noibai_airport.untils.BaseActivity;

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
public class ActivityDetailFeedback extends BaseActivity {
    @Override
    public int setContentViewId() {
        return R.layout.activity_detail_feedback;
    }

    @BindView(R.id.txt_name_detailfeddback)
    TextView txt_name_detailfeddback;
    @BindView(R.id.txt_email_detailfeddback)
    TextView txt_email_detailfeddback;
    @BindView(R.id.txt_question)
    TextView txt_question;
    @BindView(R.id.txt_content_answer)
    TextView txt_content_answer;
    Feedback objFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initAppbar();
        initData();
        initUpdateData();
    }

    public void initAppbar() {
        ImageView img_search = findViewById(R.id.img_search);
        ImageView img_back = findViewById(R.id.img_back);
        TextView txt_title = findViewById(R.id.txt_title_main);
        ImageView img_chatbox = findViewById(R.id.img_chatbox);

        txt_title.setText(R.string.title_feedback);
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
    private void initData() {
        objFeedback = (Feedback) getIntent().getSerializableExtra(Constants.KEY_SEND_FEEDBACK);

    }

    private void initUpdateData() {
        if (objFeedback != null) {
            if (objFeedback.getsNICKNAME() != null)
                txt_name_detailfeddback.setText(objFeedback.getsNICKNAME());
            if (objFeedback.getsCOMMENTTIME() != null)
                txt_email_detailfeddback.setText(objFeedback.getsCOMMENTTIME());
            if (objFeedback.getsCOMMENTS() != null)
                txt_question.setText(objFeedback.getsCOMMENTS());
            if (objFeedback.getsFEEDBACK() != null&&objFeedback.getsFEEDBACK().length()>0)
                txt_content_answer.setText(objFeedback.getsFEEDBACK());
            else
                txt_content_answer.setText(getString(R.string.hello)+": "
                        +objFeedback.getsNICKNAME()
                        +"\n\n"+getString(R.string.feedback_content));
        }
    }
}
