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
import android.util.Log;
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
import neo.com.noibai_airport.Adapter.AdapterFlightDeparture;
import neo.com.noibai_airport.App;
import neo.com.noibai_airport.Config.Constants;
import neo.com.noibai_airport.Model.ErrorApi;
import neo.com.noibai_airport.Model.FlightInfo;
import neo.com.noibai_airport.R;
import neo.com.noibai_airport.RealmController.RealmController;
import neo.com.noibai_airport.View.Activity.MainActivity.MainActivity;
import neo.com.noibai_airport.untils.BaseFragment;
import neo.com.noibai_airport.untils.ItemClickListener;
import neo.com.noibai_airport.untils.SharedPrefs;
import neo.com.noibai_airport.untils.TimeUtils;
import neo.com.noibai_airport.untils.setOnItemClickListener;

import static neo.com.noibai_airport.Config.Constants.KEY_SENT_FLIGHT_TYPE;

public class FragmentFlightDepartures_T1 extends BaseFragment implements ImlFlight.View, SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "FragmentFlightDeparture";
    public static FragmentFlightDepartures_T1 fragment;

    public static synchronized FragmentFlightDepartures_T1 getInstance() {
        if (fragment == null) {
            fragment = new FragmentFlightDepartures_T1();
        }
        return (fragment);
    }

    AdapterFlightDeparture adapterCategory;
    RecyclerView.LayoutManager mLayoutManager;
    @BindView(R.id.recycle_flight_info)
    RecyclerView recycle_flight_info;
    public static List<FlightInfo> mLisFlight;
    @BindView(R.id.refesh_flight_info)
    SwipeRefreshLayout refesh_flight_info;
    PresenterFlight mPresenter;
    private String sUserId;
    Date mCalendar = Calendar.getInstance().getTime();
    private int iPage = 1;
    private int iPage_earlier = 1;
    private int iIndex = 50;
    Realm realm;
    boolean isLoading, isLoading_earlier = false;
    public static boolean isFollow;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    @BindView(R.id.btn_load_earlier)
    Button btn_load_earlier;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: ");
        mLisFlight = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flight_info, container, false);
        Log.i(TAG, "onCreateView: ");
        ButterKnife.bind(this, view);
        mPresenter = new PresenterFlight(this);
        realm = RealmController.with(this).getRealm();
        init();
        initPulltoRefresh();
        if (App.isLoadFlightDep) {
            if (isNetwork()) {
                showDialogLoading();
                initData();
            }
        } else {
            mLisFlight.clear();
            mLisFlight.addAll(App.lisFlightDep);
            adapterCategory.notifyDataSetChanged();
        }
        get_list_flight_follow();
        initEvent();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
       /* isFollow = SharedPrefs.getInstance().get(Constants.KEY_CHANGE_VIEWFOLLOW, Boolean.class);
        if (isFollow) {
            recycle_flight_info.scrollToPosition(0);
            get_list_flight_follow();
            SharedPrefs.getInstance().put(Constants.KEY_CHANGE_VIEWFOLLOW, false);
        }else {

        }*/

    }

    private void initPulltoRefresh() {
        refesh_flight_info.setOnRefreshListener(this);
        refesh_flight_info.setColorSchemeColors(getResources().getColor(R.color.app_bar));
        refesh_flight_info.setDistanceToTriggerSync(20);// in dips
        refesh_flight_info.setSize(SwipeRefreshLayout.DEFAULT);// LARGE also can be used
    }

    public List<FlightInfo> mLisFollow = new ArrayList<>();

    private void init() {
        adapterCategory = new AdapterFlightDeparture(mLisFlight, mLisFollow, getActivity());
        mLayoutManager = new GridLayoutManager(getContext(), 1);
        recycle_flight_info.setLayoutManager(mLayoutManager);
        recycle_flight_info.setItemAnimator(new DefaultItemAnimator());
        recycle_flight_info.setAdapter(adapterCategory);
        adapterCategory.notifyDataSetChanged();

        adapterCategory.setOnIListener(new setOnItemClickListener() {
            @Override
            public void OnItemClickListener(int position) {
                if (isNetwork()) {
                    Intent intent = new Intent(getActivity(), ActivityFlightDetail.class);
                    intent.putExtra(KEY_SENT_FLIGHT_TYPE, "D");
                    if (mLisFollow.size() > 0) {
                        intent.putExtra(Constants.KEY_SENT_FLIGHT, mLisFlight.get(position - (mLisFollow.size())));
                    } else {
                        intent.putExtra(Constants.KEY_SENT_FLIGHT, mLisFlight.get(position));
                    }
                    startActivityForResult(intent, Constants.RequestCode.LOAD_FLIGHT_DEPARTURE);
                  //  startActivity(intent);
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
                    intent.putExtra(KEY_SENT_FLIGHT_TYPE, "D");
                    intent.putExtra(Constants.KEY_SENT_FLIGHT, obj);
                    startActivityForResult(intent, Constants.RequestCode.LOAD_FLIGHT_DEPARTURE);
                   // startActivity(intent);
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
                } else {

                }
            }
        });


    }

    private void initData() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String currentDateandTime = sdf.format(mCalendar);
        sUserId = SharedPrefs.getInstance().get(Constants.KEY_USERID, String.class);

        mPresenter.get_list_flight(sUserId, "", "", "D", currentDateandTime, "",
                "asc", "" + iPage, "" + iIndex, "T1");
    }

    private void initEvent() {
        MainActivity.img_search.setVisibility(View.VISIBLE);
        MainActivity.img_chatbox.setVisibility(View.VISIBLE);

        MainActivity.img_search.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ActivitySearchFlight.class));
            }
        });
        /*btn_load_earlier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isLoading_earlier) {
                    if (isNetwork()) {
                        btn_load_earlier.setVisibility(View.GONE);
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        String currentDateandTime = sdf.format(mCalendar);
                        sUserId = SharedPrefs.getInstance().get(Constants.KEY_USERID, String.class);
                        showDialogLoading();
                        mPresenter.get_list_flight_earlier(sUserId, "", "", "D",
                                currentDateandTime, "",
                                "desc", "" + iPage_earlier, "" + iIndex, "T1");
                        isLoading_earlier = true;
                    }
                }


            }
        });*/
    }


    @Override
    public void show_list_filghtinfo(List<FlightInfo> lisFlight) {
        hideDialogLoading();
        if (lisFlight != null && lisFlight.size() > 0) {
            for (int i = 0; i < lisFlight.size(); i++) {
                lisFlight.get(i).setsFlightType("D");
            }
            isLoading = false;
            if (iPage > 1) {
                mLisFlight.remove(mLisFlight.size() - 1);
                adapterCategory.notifyDataSetChanged();
                mLisFlight.addAll(lisFlight);
                adapterCategory.notifyDataSetChanged();
            } else {
                App.isLoadFlightDep = false;
                App.lisFlightDep.clear();
                App.lisFlightDep.addAll(lisFlight);
                mLisFlight.clear();
                mLisFlight.addAll(lisFlight);
                recycle_flight_info.scrollToPosition(0);
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
            isLoading_earlier = false;
            iPage_earlier = iPage_earlier + 1;
            for (int i = 0; i < lisFlight.size(); i++) {
                lisFlight.get(i).setsFlightType("D");
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
        //Toast.makeText(getActivity(), getString(R.string.show_error_follow_flight), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void show_result_flightfollow_error(List<ErrorApi> lisError) {
        hideDialogLoading();
       /* if (lisError != null && lisError.get(0).getsRESULT() != null) {
            showAlertDialog(getString(R.string.error), lisError.get(0).getsRESULT());
        } else
            showAlertDialog(getString(R.string.error), getString(R.string.show_failed_follw_flight));*/
    }

    @Override
    public void show_get_api_error() {
        hideDialogLoading();
        showAlertDialog(getString(R.string.error), getString(R.string.error_network_message));
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
              /*  if (isNetwork()){
                    mLisFlight.clear();
                    adapterCategory.notifyDataSetChanged();
                    isLoading = true;
                    btn_load_earlier.setVisibility(View.VISIBLE);
                    iPage = 1;
                    iPage_earlier = 1;
                    showDialogLoading();
                    initData();
                }*/
                if (isNetwork()) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    String currentDateandTime = sdf.format(mCalendar);
                    sUserId = SharedPrefs.getInstance().get(Constants.KEY_USERID, String.class);
                    showDialogLoading();
                    mPresenter.get_list_flight_earlier(sUserId, "", "", "D",
                            currentDateandTime, "",
                            "desc", "" + iPage_earlier, "" + iIndex,
                            "T1");
                    isLoading_earlier = true;
                }
                refesh_flight_info.setRefreshing(false);
            }
        }, 1000);
    }

    public void get_list_flight_follow() {
        mLisFollow.clear();
        List<FlightInfo> lisFlightRealm = realm.where(FlightInfo.class).findAll();
        if (lisFlightRealm != null && lisFlightRealm.size() > 0) {
            int iCountFlightRealm = 0;
            for (int i = 0; i < lisFlightRealm.size(); i++) {
                boolean is_delete_follow= TimeUtils.compare_date_time(lisFlightRealm.get(i).getmDay_start(),
                        "yyyy-MM-dd hh:mm:ss");
                if (is_delete_follow){
                    RealmController.getInstance().remove_flight(lisFlightRealm.get(i));
                }else {
                    if (lisFlightRealm.get(i).getsFlightType() != null && lisFlightRealm.get(i).getsFlightType().equals("D")) {
                        iCountFlightRealm++;
                        mLisFollow.add(0, new FlightInfo(lisFlightRealm.get(i).getsERROR(), lisFlightRealm.get(i).getsMESSAGE(),
                                lisFlightRealm.get(i).getsRESULT(), lisFlightRealm.get(i).getmFlight_Number(), lisFlightRealm.get(i).getmTime_departure(),
                                lisFlightRealm.get(i).getmTime_departure_Estimation(), lisFlightRealm.get(i).getmTime_arrival(),
                                lisFlightRealm.get(i).getmTime_arrival_Estimation(), lisFlightRealm.get(i).getmDay_start(), lisFlightRealm.get(i).getmDuration_time(),
                                lisFlightRealm.get(i).getM_Notification(), lisFlightRealm.get(i).getmAirportDepartures(), lisFlightRealm.get(i).getmAirportArrivals(),
                                lisFlightRealm.get(i).getmNameAriline(), lisFlightRealm.get(i).getmCHECKINCOUTER(), lisFlightRealm.get(i).getmBOARDINGGATE(),
                                lisFlightRealm.get(i).getmBELT(), lisFlightRealm.get(i).getmTERMINAL(), lisFlightRealm.get(i).getsFlightType(),
                                false, "", lisFlightRealm.get(i).getmLOBBY(), lisFlightRealm.get(i)
                                .getmAVATAR(), lisFlightRealm.get(i).getmNAME()));
                    }
                }

            }
            if (iCountFlightRealm > 0) {
                mLisFollow.add(iCountFlightRealm, new FlightInfo("", "", "", "",
                        "", "", "", "", "", "",
                        "", "", "", "", "", "",
                        "", "", "", true, getString(R.string.txt_flight_departure), "", "", ""));

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
            case Constants.RequestCode.LOAD_FLIGHT_DEPARTURE:
                recycle_flight_info.scrollToPosition(0);
                get_list_flight_follow();
                break;
        }
    }
}
