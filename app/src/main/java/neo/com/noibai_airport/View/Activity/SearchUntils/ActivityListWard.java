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
import neo.com.noibai_airport.Adapter.AdapterListWard;
import neo.com.noibai_airport.App;
import neo.com.noibai_airport.Config.Constants;
import neo.com.noibai_airport.Model.District;
import neo.com.noibai_airport.Model.ErrorApi;
import neo.com.noibai_airport.Model.Together;
import neo.com.noibai_airport.Model.Ward;
import neo.com.noibai_airport.R;
import neo.com.noibai_airport.View.Fragment.Together.ImlTogether;
import neo.com.noibai_airport.View.Fragment.Together.PresenterTogether;
import neo.com.noibai_airport.untils.BaseActivity;
import neo.com.noibai_airport.untils.KeyboardUtil;
import neo.com.noibai_airport.untils.SharedPrefs;
import neo.com.noibai_airport.untils.setOnItemClickListener;

/**
 * Created by QQ on 10/16/2017.
 */

public class ActivityListWard extends BaseActivity implements ImlTogether.View {

    private List<Ward> mLisAirport;
    private AdapterListWard adapterService;
    @BindView(R.id.recycle_list_service)
    RecyclerView recycle_service;
    RecyclerView.LayoutManager mLayoutManager;
    @BindView(R.id.edt_search_service)
    EditText edt_search_service;
    List<Ward> temp;
    String sUserId;
    PresenterTogether mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //   setContentView(R.layout.fragment_service);
        ButterKnife.bind(this);
        KeyboardUtil.hideSoftKeyboard(this);
        //  initData();
        App.mWard = null;
        mPresenter = new PresenterTogether(this);
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
        for (Ward d : mLisAirport) {
            if (d.getsDISTRICT_NAME().toLowerCase().contains(text.toLowerCase())) {
                //adding the element to filtered list
                temp.add(d);
            }
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if (d.getsDISTRICT_NAME().contains(text)) {

            }
        }
        //update recyclerview
        adapterService.updateList(temp);
        adapterService.setOnIListener(new setOnItemClickListener() {
            @Override
            public void OnItemClickListener(int position) {
                App.mWard = temp.get(position);
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
        adapterService = new AdapterListWard(temp, this);
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
                App.mWard = mLisAirport.get(position);
                finish();

            }

            @Override
            public void OnLongItemClickListener(int position) {

            }
        });

    }

    String mDistrictID;

    private void initData() {
        if (App.mDistrict != null) {
            mDistrictID = App.mDistrict.getsID();
            sUserId = SharedPrefs.getInstance().get(Constants.KEY_USERID, String.class);
            showDialogLoading();
            mPresenter.api_get_list_wards(sUserId, mDistrictID);

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
    public void show_get_api_error() {
        hideDialogLoading();
    }

    @Override
    public void show_get_list_district(List<District> mList) {
        hideDialogLoading();
        /*if (mList != null) {
            mLisAirport.addAll(mList);
            temp.addAll(mLisAirport);
            App.lisDistrict.clear();
            App.lisDistrict.addAll(mLisAirport);
            adapterService.notifyDataSetChanged();
        }*/
    }

    @Override
    public void show_get_list_ward(List<Ward> mList) {
        hideDialogLoading();
        if (mList != null) {
            mLisAirport.addAll(mList);
            temp.addAll(mLisAirport);
            App.lisWards.clear();
            App.lisWards.addAll(mLisAirport);
            adapterService.notifyDataSetChanged();
        }
    }

    @Override
    public void show_get_list_together(List<Together> mList) {
        hideDialogLoading();
    }

    @Override
    public void show_oder_together(ErrorApi errorApi) {
        hideDialogLoading();
    }
}
