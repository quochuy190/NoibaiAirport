package neo.com.noibai_airport.View.Fragment.FlightFragment;

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
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import neo.com.noibai_airport.Adapter.AdapterFlightArrivals;
import neo.com.noibai_airport.Config.Constants;
import neo.com.noibai_airport.Model.ErrorApi;
import neo.com.noibai_airport.Model.FlightInfo;
import neo.com.noibai_airport.R;
import neo.com.noibai_airport.RealmController.RealmController;
import neo.com.noibai_airport.View.Activity.MainActivity.MainActivity;
import neo.com.noibai_airport.untils.BaseFragment;
import neo.com.noibai_airport.untils.ItemClickListener;
import neo.com.noibai_airport.untils.SharedPrefs;
import neo.com.noibai_airport.untils.setOnItemClickListener;

import static neo.com.noibai_airport.Config.Constants.KEY_SENT_FLIGHT_TYPE;

public class FragmentFlightArrivals extends BaseFragment implements ImlFlight.View, SwipeRefreshLayout.OnRefreshListener {

    public static FragmentFlightArrivals fragment;

    public static synchronized FragmentFlightArrivals getInstance() {
        if (fragment == null) {
            fragment = new FragmentFlightArrivals();
        }
        return (fragment);
    }

    AdapterFlightArrivals adapterCategory;
    RecyclerView.LayoutManager mLayoutManager;
    @BindView(R.id.recycle_flight_info)
    RecyclerView recycle_flight_info;
    List<FlightInfo> mLisFlight;
    PresenterFlight mPresenter;
    private String sUserId;
    Date mCalendar = Calendar.getInstance().getTime();
    @BindView(R.id.refesh_flight_info)
    SwipeRefreshLayout refesh_flight_info;
    private int iPage = 1;
    private int iIndex = 50;
    Realm realm;
    boolean isLoading, isLoading_earlier = false;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    @BindView(R.id.btn_load_earlier)
    Button btn_load_earlier;
    private int iPage_earlier = 1;

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
        realm = RealmController.with(this).getRealm();
        initPulltoRefresh();
        init();
        initData();
        initEvent();
        get_list_flight_follow();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void initPulltoRefresh() {
        refesh_flight_info.setOnRefreshListener(this);
        refesh_flight_info.setColorSchemeColors(getResources().getColor(R.color.app_bar));
        refesh_flight_info.setDistanceToTriggerSync(20);// in dips
        refesh_flight_info.setSize(SwipeRefreshLayout.DEFAULT);// LARGE also can be used
    }

    public List<FlightInfo> mLisFollow = new ArrayList<>();

    private void init() {
        mLisFlight = new ArrayList<>();
        adapterCategory = new AdapterFlightArrivals(mLisFlight, mLisFollow, getContext());
        mLayoutManager = new GridLayoutManager(getContext(), 1);
        recycle_flight_info.setHasFixedSize(true);
        recycle_flight_info.setLayoutManager(mLayoutManager);
        recycle_flight_info.setItemAnimator(new DefaultItemAnimator());
        recycle_flight_info.setAdapter(adapterCategory);
        adapterCategory.notifyDataSetChanged();

        adapterCategory.setOnIListener(new setOnItemClickListener() {
            @Override
            public void OnItemClickListener(int position) {
                if (isNetwork()) {
                    Intent intent = new Intent(getActivity(), ActivityFlightDetail.class);
                    intent.putExtra(KEY_SENT_FLIGHT_TYPE, "A");
                    if (mLisFollow.size() > 0) {
                        intent.putExtra(Constants.KEY_SENT_FLIGHT, mLisFlight.get(position - (mLisFollow.size())));
                    } else {
                        intent.putExtra(Constants.KEY_SENT_FLIGHT, mLisFlight.get(position));
                    }
                    // intent.putExtra(Constants.KEY_SENT_FLIGHT, mLisFlight.get(position));
                    startActivityForResult(intent, Constants.RequestCode.LOAD_FLIGHT_ARRIVALS);
                    //startActivity(intent);
                }

            }

            @Override
            public void OnLongItemClickListener(int position) {

            }
        });

        adapterCategory.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClickItem(int position, Object item) {
                if (isNetwork()) {
                    FlightInfo obj = (FlightInfo) item;
                    Intent intent = new Intent(getActivity(), ActivityFlightDetail.class);
                    intent.putExtra(KEY_SENT_FLIGHT_TYPE, "A");
                    intent.putExtra(Constants.KEY_SENT_FLIGHT, obj);
                    //startActivity(intent);
                    startActivityForResult(intent, Constants.RequestCode.LOAD_FLIGHT_ARRIVALS);
                }

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
                                    if (isNetwork())
                                        initData();
                                    else {
                                        mLisFlight.remove(mLisFlight.size() - 1);
                                        adapterCategory.notifyDataSetChanged();
                                    }
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
        mPresenter.get_list_flight(sUserId, "", "", "A", currentDateandTime, "",
                "asc", "" + iPage, "" + iIndex);
    }

    private void initEvent() {
        MainActivity.img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ActivitySearchFlight.class));
            }
        });
        btn_load_earlier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetwork()) {
                    if (!isLoading_earlier) {

                        btn_load_earlier.setVisibility(View.GONE);
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        String currentDateandTime = sdf.format(mCalendar);
                        sUserId = SharedPrefs.getInstance().get(Constants.KEY_USERID, String.class);
                        showDialogLoading();
                        mPresenter.get_list_flight_earlier(sUserId, "", "", "A",
                                currentDateandTime, "",
                                "desc", "" + iPage_earlier, "" + 20);
                        isLoading_earlier = true;
                    }
                }
            }
        });
    }

    int iCountFlightRealm = 0;

    @Override
    public void show_list_filghtinfo(List<FlightInfo> lisFlight) {
        hideDialogLoading();

        if (lisFlight != null && lisFlight.size() > 0) {
            for (int i = 0; i < lisFlight.size(); i++) {
                lisFlight.get(i).setsFlightType("A");
            }
            isLoading = false;
            if (iPage > 1) {
                mLisFlight.remove(mLisFlight.size() - 1);
                adapterCategory.notifyDataSetChanged();
                mLisFlight.addAll(lisFlight);
                adapterCategory.notifyDataSetChanged();
            } else {

                mLisFlight.clear();

                mLisFlight.addAll(lisFlight);
                adapterCategory.notifyDataSetChanged();
            }

        } else {
            if (mLisFlight != null && mLisFlight.size() > 0) {
                mLisFlight.remove(mLisFlight.size() - 1);
                adapterCategory.notifyDataSetChanged();
            } else {
                adapterCategory.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void show_list_filghtinfo_earlier(List<FlightInfo> lisFlight) {
        hideDialogLoading();
        if (lisFlight != null && lisFlight.size() > 0) {
            btn_load_earlier.setVisibility(View.VISIBLE);
            isLoading_earlier = false;
            iPage_earlier = iPage_earlier + 1;
            for (int i = 0; i < lisFlight.size(); i++) {
                lisFlight.get(i).setsFlightType("A");
                mLisFlight.add(0, lisFlight.get(i));
            }
        }

        recycle_flight_info.scrollToPosition(0);
        adapterCategory.updateList(mLisFlight);
    }

    @Override
    public void show_detail_flight(List<FlightInfo> lisFlight) {
        hideDialogLoading();
    }

    @Override
    public void show_result_flightfollow(List<ErrorApi> lisError) {
        hideDialogLoading();
    }

    @Override
    public void show_result_flightfollow_error(List<ErrorApi> lisError) {
        hideDialogLoading();
    }

    @Override
    public void show_get_api_error() {
        hideDialogLoading();
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isNetwork()) {
                    mLisFlight.clear();
                    adapterCategory.notifyDataSetChanged();
                    isLoading = true;
                    btn_load_earlier.setVisibility(View.VISIBLE);
                    iPage = 1;
                    initData();
                }
                refesh_flight_info.setRefreshing(false);
            }
        }, 1000);
    }

    public void get_list_flight_follow() {
        iCountFlightRealm= 0;
        List<FlightInfo> lisFlightRealm = realm.where(FlightInfo.class).findAll();
        mLisFollow.clear();
        if (lisFlightRealm != null && lisFlightRealm.size() > 0) {
            for (int i = 0; i < lisFlightRealm.size(); i++) {
                if (lisFlightRealm.get(i).getsFlightType() != null && lisFlightRealm.get(i).getsFlightType().equals("A")) {
                    iCountFlightRealm++;
                    mLisFollow.add(0, new FlightInfo(lisFlightRealm.get(i).getsERROR(), lisFlightRealm.get(i).getsMESSAGE(),
                            lisFlightRealm.get(i).getsRESULT(), lisFlightRealm.get(i).getmFlight_Number(), lisFlightRealm.get(i).getmTime_departure(),
                            lisFlightRealm.get(i).getmTime_departure_Estimation(), lisFlightRealm.get(i).getmTime_arrival(),
                            lisFlightRealm.get(i).getmTime_arrival_Estimation(), lisFlightRealm.get(i).getmDay_start(), lisFlightRealm.get(i).getmDuration_time(),
                            lisFlightRealm.get(i).getM_Notification(), lisFlightRealm.get(i).getmAirportDepartures(), lisFlightRealm.get(i).getmAirportArrivals(),
                            lisFlightRealm.get(i).getmNameAriline(), lisFlightRealm.get(i).getmCHECKINCOUTER(), lisFlightRealm.get(i).getmBOARDINGGATE(),
                            lisFlightRealm.get(i).getmBELT(), lisFlightRealm.get(i).getmTERMINAL(), lisFlightRealm.get(i).getsFlightType(),
                            true, "", lisFlightRealm.get(i).getmLOBBY(), lisFlightRealm.get(i).getmAVATAR(),
                            lisFlightRealm.get(i).getmNAME()));
                }
            }
            if (iCountFlightRealm > 0) {
                mLisFollow.add(iCountFlightRealm, new FlightInfo("", "", "", "",
                        "", "", "", "", "", "",
                        "", "", "", "", "", "",
                        "", "", "", true, getString(R.string.txt_flight_arrivals), "", "", ""));

                mLisFollow.add(0, new FlightInfo("", "", "", "",
                        "", "", "", "", "", "",
                        "", "", "", "", "", "",
                        "", "", "", true, getString(R.string.txt_flight_listfollow), "", "", ""));
            }
            adapterCategory.notifyDataSetChanged();
        }
        adapterCategory.notifyDataSetChanged();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Constants.RequestCode.LOAD_FLIGHT_ARRIVALS:
                recycle_flight_info.scrollToPosition(0);
                get_list_flight_follow();
                break;
        }
    }
}
