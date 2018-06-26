package neo.com.noibai_airport.View.Activity.Feedback;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import neo.com.noibai_airport.Adapter.AdapterFeedback;
import neo.com.noibai_airport.Config.Constants;
import neo.com.noibai_airport.Model.Feedback;
import neo.com.noibai_airport.R;
import neo.com.noibai_airport.untils.BaseActivity;
import neo.com.noibai_airport.untils.SharedPrefs;
import neo.com.noibai_airport.untils.setOnItemClickListener;

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
public class ActivityFeedback extends BaseActivity implements ImlFeedback.View{
    private List<Feedback> mLisFeedback;
    private RecyclerView.LayoutManager mLayoutManager;
    private AdapterFeedback adapterFeedback;
    private PresenterFeedback mPresenter;
    @BindView(R.id.recycle_list_feedback)
    RecyclerView recycle_list_feedback;
    private String sUserId;
    @Override
    public int setContentViewId() {
        return R.layout.activity_feedback;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new PresenterFeedback(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        initAppbar();
        init();
        initData();
    }

    private void initData() {
        sUserId = SharedPrefs.getInstance().get(Constants.KEY_USERID, String.class);
        showDialogLoading();
        mPresenter.get_feedback(sUserId, "", "");
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
    private void init() {
        mLisFeedback = new ArrayList<>();
        adapterFeedback = new AdapterFeedback(mLisFeedback, this);
        mLayoutManager = new GridLayoutManager(this, 1);
        //recycle_category.setNestedScrollingEnabled(false);
        recycle_list_feedback.setHasFixedSize(true);
        recycle_list_feedback.setLayoutManager(mLayoutManager);
        recycle_list_feedback.setItemAnimator(new DefaultItemAnimator());
        recycle_list_feedback.setAdapter(adapterFeedback);
        adapterFeedback.notifyDataSetChanged();
        adapterFeedback.setOnIListener(new setOnItemClickListener() {
            @Override
            public void OnItemClickListener(int position) {
                Intent intent = new Intent(ActivityFeedback.this, ActivityDetailFeedback.class);
                intent.putExtra(Constants.KEY_SEND_FEEDBACK, mLisFeedback.get(position));
                startActivity(intent);
            }

            @Override
            public void OnLongItemClickListener(int position) {

            }
        });
    }

    @Override
    public void show_feedback(List<Feedback> lisFeedback) {
        hideDialogLoading();
        if (lisFeedback.size()>0){
            mLisFeedback.addAll(lisFeedback);
            adapterFeedback.notifyDataSetChanged();
        }
    }

    @Override
    public void show_result_addfeedback() {
        hideDialogLoading();
    }

    @Override
    public void show_error_addfeedback() {
        hideDialogLoading();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_feedback:
                boolean isLoginFeedback = SharedPrefs.getInstance().get(Constants.KEY_IS_LOGIN_FEEDBACK, Boolean.class);
                if (isLoginFeedback){
                    Intent intent = new Intent(ActivityFeedback.this, ActivityAddFeedback.class);
                    startActivityForResult(intent, Constants.RequestCode.CHANGE_FEEDBACK);
                   // startActivity(new Intent(ActivityFeedback.this, ActivityAddFeedback.class));
                }else {
                    Intent intent = new Intent(ActivityFeedback.this, ActivityRegister.class);
                    intent.putExtra(Constants.KEY_SEND_REGISTER, Constants.FEEDBACK);
                    startActivity(intent);
                }

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Constants.RequestCode.CHANGE_FEEDBACK:
                if (resultCode == RESULT_OK) {
                    mLisFeedback.clear();
                    mPresenter.get_feedback(sUserId, "", "");
                }
                break;
        }
    }
}
