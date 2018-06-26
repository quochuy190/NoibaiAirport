package neo.com.noibai_airport.View.Fragment.MenuFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import neo.com.noibai_airport.Adapter.AdapterInstruction;
import neo.com.noibai_airport.Config.Constants;
import neo.com.noibai_airport.Model.ObjWebview;
import neo.com.noibai_airport.R;
import neo.com.noibai_airport.View.Activity.ActivityBooking;
import neo.com.noibai_airport.untils.BaseActivity;
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
public class ActivityMap extends BaseActivity {
    private static final String TAG = "ActivityMap";
    @BindView(R.id.recycle_flight_info)
    RecyclerView recycle_flight_info;
    private String sUserId;


    AdapterInstruction adapterCategory;
    RecyclerView.LayoutManager mLayoutManager;
    List<ObjWebview> mLisFlight;


    @Override
    public int setContentViewId() {
        return R.layout.activity_list_result_flight;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initAppbar();
        init();
        initData();

    }

    public void initAppbar() {
        ImageView img_search = findViewById(R.id.img_search);
        ImageView img_back = findViewById(R.id.img_back);
        TextView txt_title = findViewById(R.id.txt_title_main);
        ImageView img_chatbox = findViewById(R.id.img_chatbox);
        txt_title.setText(R.string.txt_map);
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
        mLisFlight = new ArrayList<>();
        adapterCategory = new AdapterInstruction(mLisFlight, this);
        mLayoutManager = new GridLayoutManager(this, 1);
        //recycle_category.setNestedScrollingEnabled(false);

        recycle_flight_info.setLayoutManager(mLayoutManager);
        recycle_flight_info.setItemAnimator(new DefaultItemAnimator());
        recycle_flight_info.setAdapter(adapterCategory);
        adapterCategory.notifyDataSetChanged();

        adapterCategory.setOnIListener(new setOnItemClickListener() {
            @Override
            public void OnItemClickListener(int position) {
                Intent intent = new Intent(ActivityMap.this, ActivityBooking.class);
                intent.putExtra(Constants.KEY_SEND_URL_WEBVIEW, mLisFlight.get(position));
                startActivity(intent);
            }

            @Override
            public void OnLongItemClickListener(int position) {

            }
        });

    }

    private void initData() {
        mLisFlight.add(new ObjWebview("International Terminal (T2)", "Nhà ga hành khách quốc tế (T2)",
                0,
                "http://noibai.online/nia/vi/t2.html",
                "http://noibai.online/nia/en/t2.html"));
        mLisFlight.add(new ObjWebview("Domestic Terminal (T1):", "Nhà ga hành khách quốc nội (T1)", 0,
                "http://noibai.online/nia/vi/t1.html",
                "http://noibai.online/nia/en/t1.html"));
        adapterCategory.notifyDataSetChanged();
    }


}
