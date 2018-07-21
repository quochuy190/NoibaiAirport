package neo.com.noibai_airport.View.Fragment.Together;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import neo.com.noibai_airport.Adapter.AdapterTogether;
import neo.com.noibai_airport.App;
import neo.com.noibai_airport.Config.Constants;
import neo.com.noibai_airport.Model.District;
import neo.com.noibai_airport.Model.ErrorApi;
import neo.com.noibai_airport.Model.Together;
import neo.com.noibai_airport.Model.Ward;
import neo.com.noibai_airport.R;
import neo.com.noibai_airport.View.Activity.MainActivity.MainActivity;
import neo.com.noibai_airport.untils.BaseFragment;
import neo.com.noibai_airport.untils.DialogUtil;
import neo.com.noibai_airport.untils.ItemTogetherClickListener;
import neo.com.noibai_airport.untils.SharedPrefs;

import static android.app.Activity.RESULT_OK;

public class FragmenTogether extends BaseFragment implements ImlTogether.View, SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "FragmenTogether";
    public static FragmenTogether fragment;
    AdapterTogether adapter;
    RecyclerView.LayoutManager mLayoutManager;
    List<Together> mLisTogether;
    @BindView(R.id.recycle_list_together)
    RecyclerView recycle_list_together;
    @BindView(R.id.txt_empty_together)
    TextView txt_empty_together;
    PresenterTogether mPresenter;
    private String sUserId;
    @BindView(R.id.btn_add_together)
    FloatingActionButton btn_add_together;
    private String mIndex = "50";
    private int mPage = 1;
    boolean isAddFilter = true;
    Together mMyOrderTogether;
    @BindView(R.id.swip_together)
    SwipeRefreshLayout refesh_together;
    private boolean isLoading;
    int pastVisiblesItems, visibleItemCount, totalItemCount;

    public static synchronized FragmenTogether getInstance() {
        if (fragment == null) {
            fragment = new FragmenTogether();
        }
        return (fragment);
    }

    private boolean isLoadView = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLisTogether = new ArrayList<>();

        isLoadView = true;
    }

    private void initPulltoRefresh() {
        refesh_together.setOnRefreshListener(this);
        refesh_together.setColorSchemeColors(getResources().getColor(R.color.app_bar));
        refesh_together.setDistanceToTriggerSync(20);// in dips
        refesh_together.setSize(SwipeRefreshLayout.DEFAULT);// LARGE also can be used
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_together, container, false);
        ButterKnife.bind(this, view);
        initPulltoRefresh();
        mPresenter = new PresenterTogether(this);
        mMyOrderTogether = SharedPrefs.getInstance().get(Constants.KEY_SAVE_TOGETHER_ORDER, Together.class);
        if (mMyOrderTogether != null) {
            btn_add_together.setVisibility(View.GONE);
            mLisTogether.add(new Together(getString(R.string.myorder), true));
            mLisTogether.add(mMyOrderTogether);
            mLisTogether.add(new Together(getString(R.string.all_order), true));
            Log.i(TAG, "onCreate: " + mMyOrderTogether.getsDISTRICT_NAME());
        } else {
            btn_add_together.setVisibility(View.VISIBLE);
        }
        init();
        if (App.isLoadToghther) {
            App.isLoadToghther = false;
            show_warning();
        } else {
            if (isNetwork()) {
                mPage = 1;
                showDialogLoading();
                if (mMyOrderTogether != null) {
                    txt_empty_together.setVisibility(View.GONE);
                    initData(mMyOrderTogether.getsDistrict());
                } else
                    initData("");
            }
        }


       /* if (App.isLoadToghther) {

        } else {
            mLisTogether.addAll(App.lisTogether);
            adapter.notifyDataSetChanged();
        }*/
        // initData();
        initEvent();
        return view;
    }

    private void initEvent() {
        MainActivity.img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                App.mSearchTogether = null;
                App.mDistrict = null;
                App.mWard = null;
                Intent intent = new Intent(getContext(), ActivitySearchTogether.class);
                startActivityForResult(intent, Constants.RequestCode.SEARCH_TOGETHER);
            }
        });
        btn_add_together.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ActivityAddTogether.class);
                startActivityForResult(intent, Constants.RequestCode.ADD_TOGETHER);
                //startActivity(new Intent(getActivity(), ActivityAddTogether.class));
            }
        });
    }

    private void initData(String mDictrictId) {
        sUserId = SharedPrefs.getInstance().get(Constants.KEY_USERID, String.class);
        mPresenter.api_get_list_together(sUserId, mDictrictId, "",
                "", "", mIndex, "" + mPage);

    }

    private void initDataSearch() {
        showDialogLoading();
        mPage = 1;
        sUserId = SharedPrefs.getInstance().get(Constants.KEY_USERID, String.class);
        mPresenter.api_get_list_together(sUserId, App.mSearchTogether.getmDistrict(), App.mSearchTogether.getmWard(),
                App.mSearchTogether.getmTime(), App.mSearchTogether.getmGender(), mIndex, "" + mPage);

    }

    private void init() {
        adapter = new AdapterTogether(getContext(), mLisTogether, new ItemTogetherClickListener() {
            @Override
            public void onClickItem(int position, Object item) {
                Together obj = (Together) item;
                if (obj.isMyOrder()) {
                    Intent intent = new Intent(getContext(), ActivityEditTogether.class);
                    startActivityForResult(intent, Constants.RequestCode.ADD_TOGETHER);
                }
            }

            @Override
            public void onClickPhoneItem(int position, Object item) {
                Together together = (Together) item;
                MainActivity.call_phone(getContext(), together.getsPhone());

            }
        });
        mLayoutManager = new GridLayoutManager(getContext(), 1);
        //recycle_category.setNestedScrollingEnabled(false);
        recycle_list_together.setHasFixedSize(true);
        recycle_list_together.setLayoutManager(mLayoutManager);
        recycle_list_together.setItemAnimator(new DefaultItemAnimator());
        recycle_list_together.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        recycle_list_together.setOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    GridLayoutManager layoutmanager = (GridLayoutManager) recyclerView.getLayoutManager();
                    visibleItemCount = layoutmanager.getChildCount();
                    totalItemCount = layoutmanager.getItemCount();
                    pastVisiblesItems = layoutmanager.findFirstVisibleItemPosition();
                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                        if (!isLoading) {
                            isLoading = true;
                            mLisTogether.add(null);
                            adapter.notifyDataSetChanged();
                            mPage++;
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if (isNetwork())

                                        initData("");
                                    else {
                                        mLisTogether.remove(mLisTogether.size() - 1);
                                        adapter.notifyDataSetChanged();
                                    }
                                }
                            }, 1000);
                        }
                    }
                }
            }
        });
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
        if (mList != null) {
            txt_empty_together.setVisibility(View.GONE);
            isLoading = false;
            if (mPage > 1) {
                mLisTogether.remove(mLisTogether.size() - 1);
                adapter.notifyDataSetChanged();
                mLisTogether.addAll(mList);
                adapter.notifyDataSetChanged();
            } else {
                App.isLoadToghther = false;
                App.lisTogether.clear();
                App.lisTogether.addAll(mList);
                mLisTogether.addAll(mList);
                adapter.notifyDataSetChanged();
            }

        } else {
            if (mPage > 1) {
                mLisTogether.remove(mLisTogether.size() - 1);
                adapter.notifyDataSetChanged();
            } else {
                if (mMyOrderTogether != null) {
                    txt_empty_together.setVisibility(View.GONE);
                } else
                    txt_empty_together.setVisibility(View.VISIBLE);
                adapter.notifyDataSetChanged();
            }
        }

    }

    @Override
    public void show_oder_together(ErrorApi errorApi) {
        hideDialogLoading();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Constants.RequestCode.ADD_TOGETHER:
                if (resultCode == RESULT_OK) {
                    mLisTogether.clear();
                    mMyOrderTogether = SharedPrefs.getInstance().get(Constants.KEY_SAVE_TOGETHER_ORDER, Together.class);
                    if (mMyOrderTogether != null) {
                        btn_add_together.setVisibility(View.GONE);
                        mLisTogether.add(new Together(getString(R.string.myorder), true));
                        mLisTogether.add(mMyOrderTogether);
                        mLisTogether.add(new Together(getString(R.string.all_order), true));
                        Log.i(TAG, "onCreate: " + mMyOrderTogether.getsDISTRICT_NAME());
                        adapter.notifyDataSetChanged();
                    } else {
                        btn_add_together.setVisibility(View.VISIBLE);
                    }
                    adapter.notifyDataSetChanged();
                    mPage = 1;
                    showDialogLoading();
                    if (mMyOrderTogether != null) {
                        initData(mMyOrderTogether.getsDistrict());
                    } else
                        initData("");
                }
                break;
            case Constants.RequestCode.SEARCH_TOGETHER:
                if (resultCode == RESULT_OK) {
                    mLisTogether.clear();
                    mMyOrderTogether = SharedPrefs.getInstance().
                            get(Constants.KEY_SAVE_TOGETHER_ORDER, Together.class);
                    if (mMyOrderTogether != null) {
                        btn_add_together.setVisibility(View.GONE);
                        mLisTogether.add(new Together(getString(R.string.myorder), true));
                        mLisTogether.add(mMyOrderTogether);
                        mLisTogether.add(new Together(getString(R.string.all_order), true));
                        Log.i(TAG, "onCreate: " + mMyOrderTogether.getsDISTRICT_NAME());
                        adapter.notifyDataSetChanged();
                    } else {
                        btn_add_together.setVisibility(View.VISIBLE);
                    }
                    adapter.notifyDataSetChanged();
                    initDataSearch();
                }
                break;
        }
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isNetwork()) {
                    mLisTogether.clear();
                    mMyOrderTogether = SharedPrefs.getInstance().
                            get(Constants.KEY_SAVE_TOGETHER_ORDER, Together.class);
                    if (mMyOrderTogether != null) {
                        btn_add_together.setVisibility(View.GONE);
                        mLisTogether.add(new Together(getString(R.string.myorder), true));
                        mLisTogether.add(mMyOrderTogether);
                        mLisTogether.add(new Together(getString(R.string.all_order), true));
                        Log.i(TAG, "onCreate: " + mMyOrderTogether.getsDISTRICT_NAME());
                        adapter.notifyDataSetChanged();
                    } else {
                        btn_add_together.setVisibility(View.VISIBLE);
                    }
                    adapter.notifyDataSetChanged();
                    isLoading = true;
                    mPage = 1;
                    if (mMyOrderTogether != null) {
                        initData(mMyOrderTogether.getsDistrict());
                    } else
                        initData("");
                }
                refesh_together.setRefreshing(false);
            }
        }, 1000);
    }

    public void show_warning() {
        showDialogWarning(getString(R.string.title_warning),
                getString(R.string.message_warning_together),
                true,
                new DialogUtil.ClickDialog() {
                    @Override
                    public void onClickYesDialog() {
                        if (isNetwork()) {
                            mPage = 1;
                            showDialogLoading();
                            if (mMyOrderTogether != null) {
                                initData(mMyOrderTogether.getsDistrict());
                            } else
                                initData("");
                        }
                    }

                    @Override
                    public void onClickNoDialog() {
                        if (isNetwork()) {
                            mPage = 1;
                            showDialogLoading();
                            if (mMyOrderTogether != null) {
                                initData(mMyOrderTogether.getsDistrict());
                            } else
                                initData("");
                        }
                    }
                }

        );
    }
}
