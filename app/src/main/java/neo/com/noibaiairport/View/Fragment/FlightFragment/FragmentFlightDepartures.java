package neo.com.noibaiairport.View.Fragment.FlightFragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import neo.com.noibaiairport.Adapter.AdapterFlightInfo;
import neo.com.noibaiairport.Config.Constants;
import neo.com.noibaiairport.Model.FlightInfo;
import neo.com.noibaiairport.R;
import neo.com.noibaiairport.View.Activity.MainActivity.MainActivity;
import neo.com.noibaiairport.untils.BaseFragment;
import neo.com.noibaiairport.untils.SharedPrefs;
import neo.com.noibaiairport.untils.setOnItemClickListener;

import static neo.com.noibaiairport.Config.Constants.KEY_SENT_FLIGHT_TYPE;

public class FragmentFlightDepartures extends BaseFragment implements ImlFlight.View, SwipeRefreshLayout.OnRefreshListener {

    public static FragmentFlightDepartures fragment;

    public static synchronized FragmentFlightDepartures getInstance() {
        if (fragment == null) {
            fragment = new FragmentFlightDepartures();
        }
        return (fragment);
    }

    AdapterFlightInfo adapterCategory;
    RecyclerView.LayoutManager mLayoutManager;
    @BindView(R.id.recycle_flight_info)
    RecyclerView recycle_flight_info;
    List<FlightInfo> mLisFlight;
    @BindView(R.id.linear_visiblity)
    LinearLayout linear_visiblity;
    @BindView(R.id.refesh_flight_info)
            SwipeRefreshLayout refesh_flight_info;
    PresenterFlight mPresenter;
    private String sUserId;
    Date mCalendar = Calendar.getInstance().getTime();
    private int iPage = 1;
    private int iIndex = 20;

    boolean isLoading;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flight_info, container, false);
        ButterKnife.bind(this, view);
        mPresenter = new PresenterFlight(this);
        init();
        initPulltoRefresh();
        initData();
        initEvent();
        return view;
    }
    private void initPulltoRefresh() {
        refesh_flight_info.setOnRefreshListener(this);
        refesh_flight_info.setColorSchemeColors(getResources().getColor(R.color.app_bar));
        refesh_flight_info.setDistanceToTriggerSync(20);// in dips
        refesh_flight_info.setSize(SwipeRefreshLayout.DEFAULT);// LARGE also can be used
    }
    private void initEvent() {
        MainActivity.img_search.setVisibility(View.VISIBLE);
        MainActivity.img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void init() {
     //   refesh_flight_info.setOnRefreshListener(this);
        mLisFlight = new ArrayList<>();
        adapterCategory = new AdapterFlightInfo(mLisFlight, getActivity());
        mLayoutManager = new GridLayoutManager(getContext(), 1);
        //recycle_category.setNestedScrollingEnabled(false);

        recycle_flight_info.setLayoutManager(mLayoutManager);
        recycle_flight_info.setItemAnimator(new DefaultItemAnimator());
        recycle_flight_info.setAdapter(adapterCategory);
        adapterCategory.notifyDataSetChanged();

        adapterCategory.setOnIListener(new setOnItemClickListener() {
            @Override
            public void OnItemClickListener(int position) {
                Intent intent = new Intent(getActivity(), ActivityFlightDetail.class);
                intent.putExtra(KEY_SENT_FLIGHT_TYPE, "D");
                intent.putExtra(Constants.KEY_SENT_FLIGHT, mLisFlight.get(position));
                startActivity(intent);
            }

            @Override
            public void OnLongItemClickListener(int position) {

            }
        });
        recycle_flight_info.setOnScrollListener(new RecyclerView.OnScrollListener() {

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
                            mLisFlight.add(null);
                            adapterCategory.notifyDataSetChanged();
                            iPage++;
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    initData();
                                }
                            }, 1000);
                        }
                    }
                }
            }
        });


    }

    private void initData() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String currentDateandTime = sdf.format(mCalendar);
        sUserId = SharedPrefs.getInstance().get(Constants.KEY_USERID, String.class);
        mPresenter.get_list_flight(sUserId, "", "", "D", currentDateandTime, "",
                "asc", ""+iPage, ""+iIndex);
    }


    @Override
    public void show_list_filghtinfo(List<FlightInfo> lisFlight) {
        if (lisFlight!=null&&lisFlight.size()>0){
            isLoading=false;
            if (iPage>1){
                mLisFlight.remove(mLisFlight.size()-1);
                adapterCategory.notifyDataSetChanged();
                mLisFlight.addAll(lisFlight);
                adapterCategory.notifyDataSetChanged();
            }else {
                mLisFlight.addAll(lisFlight);
                adapterCategory.notifyDataSetChanged();
            }

        }else {
            if (mLisFlight!=null&&mLisFlight.size()>0){
                mLisFlight.remove(mLisFlight.size()-1);
                adapterCategory.notifyDataSetChanged();
            }else {
                adapterCategory.notifyDataSetChanged();
            }
        }

    }

    @Override
    public void show_detail_flight(List<FlightInfo> lisFlight) {

    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mLisFlight.clear();
                iPage=1;
                initData();
                refesh_flight_info.setRefreshing(false);
            }
        }, 500);
    }
}
