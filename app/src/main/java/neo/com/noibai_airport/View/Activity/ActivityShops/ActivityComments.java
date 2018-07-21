package neo.com.noibai_airport.View.Activity.ActivityShops;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import neo.com.noibai_airport.Adapter.AdapterComment;
import neo.com.noibai_airport.Config.Constants;
import neo.com.noibai_airport.Model.Comments;
import neo.com.noibai_airport.R;
import neo.com.noibai_airport.untils.BaseActivity;
import neo.com.noibai_airport.untils.ItemClickListener;
import neo.com.noibai_airport.untils.KeyboardUtil;
import neo.com.noibai_airport.untils.SharedPrefs;

public class ActivityComments extends BaseActivity implements ImlComment.View {
    @BindView(R.id.reyclerview_message_list)
    RecyclerView recycle_language;
    AdapterComment adapterLanguage;
    private List<Comments> lisLanguage;
    RecyclerView.LayoutManager mLayoutManager;
    PresenterComment mPresenter;
    @BindView(R.id.button_chatbox_send)
    ImageView button_chatbox_send;
    @BindView(R.id.edt_input_message)
    EditText edt_input_message;
    String sServiceId;
    private String sUserFeedback, sEmailFeedback, sUserId;

    @Override
    public int setContentViewId() {
        return R.layout.fragment_chatbot;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new PresenterComment(this);
        init();
        initData();
        initAppbar();
        initEvent();
    }

    private void initEvent() {
        button_chatbox_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_input_message.getText().toString().length() > 0) {
                    showDialogLoading();
                    mPresenter.add_comment(sUserId, sUserFeedback, sServiceId,
                            edt_input_message.getText().toString(), "", "");
                } else Toast.makeText(ActivityComments.this,
                        "Bạn chưa nhập vào thông tin", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {
        sServiceId = getIntent().getStringExtra(Constants.KEY_SEND_COMMENT);
        sUserFeedback = SharedPrefs.getInstance().get(Constants.KEY_USER_FEEDBACK, String.class);
        sEmailFeedback = SharedPrefs.getInstance().get(Constants.KEY_EMAIL_FEEDBACK, String.class);
        sUserId = SharedPrefs.getInstance().get(Constants.KEY_USERID, String.class);
        showDialogLoading();
        mPresenter.get_comment(sUserId, sServiceId, "");
    }

    public void initAppbar() {
        TextView txt_title = findViewById(R.id.txt_title_main);
        txt_title.setText(R.string.txt_comment);
        ImageView img_back = findViewById(R.id.img_back);
        img_back.setVisibility(View.VISIBLE);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void init() {
        lisLanguage = new ArrayList<>();
        adapterLanguage = new AdapterComment(this, lisLanguage);
        mLayoutManager = new GridLayoutManager(this, 1);
        // recycle_language.setNestedScrollingEnabled(false);
        recycle_language.setHasFixedSize(true);
        recycle_language.setLayoutManager(mLayoutManager);
        recycle_language.setItemAnimator(new DefaultItemAnimator());
        recycle_language.setAdapter(adapterLanguage);
        adapterLanguage.notifyDataSetChanged();

        adapterLanguage.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClickItem(int position, final Object item) {

            }
        });
    }

    @Override
    public void show_list_comment(List<Comments> mLisComments) {
        hideDialogLoading();
        if (mLisComments != null) {
            lisLanguage.clear();
            lisLanguage.addAll(mLisComments);
            adapterLanguage.notifyDataSetChanged();
            recycle_language.scrollToPosition(lisLanguage.size()-1);
        }
    }

    @Override
    public void show_result_addComments() {
       // hideDialogLoading();
        edt_input_message.setText("");
        KeyboardUtil.hideSoftKeyboard(this);
        mPresenter.get_comment(sUserId, sServiceId, "");
    }

    @Override
    public void show_error_addComments() {
        hideDialogLoading();
    }

    @Override
    public void show_error_api() {
        hideDialogLoading();
    }
}
