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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import neo.com.noibai_airport.Adapter.AdapterFlightDeparture;
import neo.com.noibai_airport.Config.Constants;
import neo.com.noibai_airport.Model.ErrorApi;
import neo.com.noibai_airport.Model.FlightInfo;
import neo.com.noibai_airport.Model.ObjSearchFlight;
import neo.com.noibai_airport.R;
import neo.com.noibai_airport.RealmController.RealmController;
import neo.com.noibai_airport.untils.BaseFragment;
import neo.com.noibai_airport.untils.ItemClickListener;
import neo.com.noibai_airport.untils.SharedPrefs;
import neo.com.noibai_airport.untils.setOnItemClickListener;

import static neo.com.noibai_airport.Config.Constants.KEY_SENT_FLIGHT_TYPE;

public class FragmentSearchFlightDepartures extends BaseFragment implements ImlFlight.View, SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "FragmentFlightDeparture";
    public static FragmentSearchFlightDepartures fragment;
    private ObjSearchFlight mObjSearch;

    public static synchronized FragmentSearchFlightDepartures getInstance() {
        if (fragment == null) {
            fragment = new FragmentSearchFlightDepartures();
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
    private int iIndex =500;
    Realm realm;
    boolean isLoading, isLoading_earlier= false;
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
        initData();
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

    }

    private void initPulltoRefresh() {
        refesh_flight_info.setOnRefreshListener(this);
        refesh_flight_info.setColorSchemeColors(getResources().getColor(R.color.app_bar));
        refesh_flight_info.setDistanceToTriggerSync(20);// in dips
        refesh_flight_info.setSize(SwipeRefreshLayout.DEFAULT);// LARGE also can be used
    }
    public List<FlightInfo> mLisFollow = new ArrayList<>();
    private void init() {
        //   refesh_flight_info.setOnRefreshListener(this);

        adapterCategory = new AdapterFlightDeparture(mLisFlight, mLisFollow,getActivity());
        mLayoutManager = new GridLayoutManager(getContext(), 1);
        recycle_flight_info.setLayoutManager(mLayoutManager);
        recycle_flight_info.setItemAnimator(new DefaultItemAnimator());
        recycle_flight_info.setAdapter(adapterCategory);
        adapterCategory.notifyDataSetChanged();

        adapterCategory.setOnIListener(new setOnItemClickListener() {
            @Override
            public void OnItemClickListener(int position) {
                Intent intent = new Intent(getActivity(), ActivityFlightDetail.class);
                intent.putExtra(KEY_SENT_FLIGHT_TYPE, "D");
                if (mLisFollow.size()>0){
                    intent.putExtra(Constants.KEY_SENT_FLIGHT, mLisFlight.get(position-(mLisFollow.size())));
                }else {
                    intent.putExtra(Constants.KEY_SENT_FLIGHT, mLisFlight.get(position));
                }

                startActivity(intent);
            }

            @Override
            public void OnLongItemClickListener(int position) {

            }
        });
        adapterCategory.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClickItem(int position, Object item) {
                FlightInfo obj = (FlightInfo) item;
                Intent intent = new Intent(getActivity(), ActivityFlightDetail.class);
                intent.putExtra(KEY_SENT_FLIGHT_TYPE, "D");
                intent.putExtra(Constants.KEY_SENT_FLIGHT, obj);
                startActivity(intent);
            }
        });


    }

    private void initData() {
        Bundle bundle = getArguments();
        mObjSearch  = (ObjSearchFlight) bundle.getSerializable(Constants.KEY_SEND_FLIGHT_SEARCH);
        sUserId = SharedPrefs.getInstance().get(Constants.KEY_USERID, String.class);
        showDialogLoading();
        mPresenter.get_list_flight(sUserId, mObjSearch.getsFlightNumber(),
                mObjSearch.getsFlightAirport(), "D", mObjSearch.getsFlightDatetime(),
                mObjSearch.getsFlightAirline(),
                "asc", "" + iPage, "" + iIndex, "");
    }

    private void initEvent() {

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
                mLisFlight.clear();
                mLisFlight.addAll(lisFlight);
                recycle_flight_info.scrollToPosition(0);
                adapterCategory.notifyDataSetChanged();
            }
        } else {
                showAlertDialog(getString(R.string.error), getString(R.string.mesaage_error_search));

        }

    }

    @Override
    public void show_list_filghtinfo_earlier(List<FlightInfo> lisFlight) {
        hideDialogLoading();
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

                    iPage = 1;
                    iPage_earlier=1;
                    initData();
                } else
                    showAlertDialog(getString(R.string.error_network), getString(R.string.error_network_message));
                refesh_flight_info.setRefreshing(false);
            }
        }, 1000);
    }


}
