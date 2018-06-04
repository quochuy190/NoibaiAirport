package neo.com.noibaiairport.View.Activity.Feedback;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import neo.com.noibaiairport.Adapter.AdapterFeedback;
import neo.com.noibaiairport.Config.Constants;
import neo.com.noibaiairport.Model.Feedback;
import neo.com.noibaiairport.R;
import neo.com.noibaiairport.untils.BaseActivity;
import neo.com.noibaiairport.untils.SharedPrefs;
import neo.com.noibaiairport.untils.setOnItemClickListener;

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
        init();
        initData();
    }

    private void initData() {
        sUserId = SharedPrefs.getInstance().get(Constants.KEY_USERID, String.class);
        mPresenter.get_feedback(sUserId, "toanbom", "a");
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

            }

            @Override
            public void OnLongItemClickListener(int position) {

            }
        });
    }

    @Override
    public void show_feedback(List<Feedback> lisFeedback) {
        if (lisFeedback.size()>0){
            mLisFeedback.addAll(lisFeedback);
            adapterFeedback.notifyDataSetChanged();
        }
    }
}
