package neo.com.noibai_airport.View.Activity.SearchUntils;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import neo.com.noibai_airport.Adapter.AdapterListAirline_Search;
import neo.com.noibai_airport.App;
import neo.com.noibai_airport.Config.Constants;
import neo.com.noibai_airport.Model.AirlineInfo;
import neo.com.noibai_airport.Model.Airport;
import neo.com.noibai_airport.Model.ErrorApi;
import neo.com.noibai_airport.R;
import neo.com.noibai_airport.View.Activity.AirlineInfo.ImlAirlineInfo;
import neo.com.noibai_airport.View.Activity.AirlineInfo.PresenterAirlineInfo;
import neo.com.noibai_airport.untils.BaseActivity;
import neo.com.noibai_airport.untils.KeyboardUtil;
import neo.com.noibai_airport.untils.SharedPrefs;
import neo.com.noibai_airport.untils.setOnItemClickListener;

/**
 * Created by QQ on 10/16/2017.
 */

public class ActivityListAirline_Search extends BaseActivity implements ImlAirlineInfo.View {

    private List<AirlineInfo> mLisAirport;
    private AdapterListAirline_Search adapterService;
    @BindView(R.id.recycle_list_service)
    RecyclerView recycle_service;
    RecyclerView.LayoutManager mLayoutManager;
    @BindView(R.id.edt_search_service)
    EditText edt_search_service;
    List<AirlineInfo> temp;
    String sUserId;
    PresenterAirlineInfo mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //   setContentView(R.layout.fragment_service);
        ButterKnife.bind(this);
        KeyboardUtil.hideSoftKeyboard(this);
        //  initData();
        App.mAirportSearch = null;
        mPresenter = new PresenterAirlineInfo(this);
        edt_search_service.setVisibility(View.VISIBLE);
        initAppbar();
        init();
        initData();
        initEvent();

    }

    TextView txt_title;

    public void initAppbar() {
        ImageView img_search = findViewById(R.id.img_search);
        ImageView img_back = findViewById(R.id.img_back);
        txt_title = findViewById(R.id.txt_title_main);
        ImageView img_chatbox = findViewById(R.id.img_chatbox);
        txt_title.setVisibility(View.GONE);
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

    @Override
    public int setContentViewId() {
        return R.layout.activity_recycleview;
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    /*  @Nullable
          @Override
          public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
              View view= inflater.inflate(R.layout.fragment_service, container, false);


              view.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {

                  }
              });
              return view;
          }
      */
    private void initEvent() {
        edt_search_service.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

                // filter your list from your input
                filter(s.toString());
                //you can use runnable postDelayed like 500 ms to delay search text
            }
        });


    }

    void filter(String text) {
        temp.clear();
        for (AirlineInfo d : mLisAirport) {
            if (d.getsName().toLowerCase().contains(text.toLowerCase())) {
                //adding the element to filtered list
                temp.add(d);
            }
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if (d.getsName().contains(text)) {

            }
        }
        //update recyclerview
        adapterService.updateList(temp);
        adapterService.setOnIListener(new setOnItemClickListener() {
            @Override
            public void OnItemClickListener(int position) {
                App.mAirlineSearch = temp.get(position);
                finish();
            }

            @Override
            public void OnLongItemClickListener(int position) {

            }
        });
    }

    private void init() {
        mLisAirport = new ArrayList<>();
        temp = new ArrayList<>();
        adapterService = new AdapterListAirline_Search(temp, this);
        mLayoutManager = new GridLayoutManager(this, 1);
        recycle_service.setNestedScrollingEnabled(false);
        recycle_service.setHasFixedSize(true);
        recycle_service.setLayoutManager(mLayoutManager);
        recycle_service.setItemAnimator(new DefaultItemAnimator());
        recycle_service.setAdapter(adapterService);
        adapterService.updateList(temp);
        adapterService.setOnIListener(new setOnItemClickListener() {
            @Override
            public void OnItemClickListener(int position) {
                App.mAirlineSearch = mLisAirport.get(position);
                finish();

            }

            @Override
            public void OnLongItemClickListener(int position) {

            }
        });

    }

    private void initData() {
        if (App.lisAirline != null && App.lisAirline.size() > 0) {
            mLisAirport.addAll(App.lisAirline);
            temp.addAll(mLisAirport);
            adapterService.notifyDataSetChanged();
        } else {
            sUserId = SharedPrefs.getInstance().get(Constants.KEY_USERID, String.class);
            showDialogLoading();
            mPresenter.get_list_airline(sUserId);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void show_list_airline(List<AirlineInfo> lisAirline) {
        hideDialogLoading();
        if (lisAirline != null) {
            mLisAirport.addAll(lisAirline);
            temp.addAll(mLisAirport);
            App.lisAirline.clear();
            App.lisAirline.addAll(mLisAirport);
            adapterService.notifyDataSetChanged();
        }
    }

    @Override
    public void show_list_airport(List<Airport> lisAirport) {

    }

    @Override
    public void show_airline_detail(List<Airport> lisAirport) {

    }

    @Override
    public void show_api_error(List<ErrorApi> lisError) {

    }
}
