package neo.com.noibai_airport.View.Activity.ActivityFlight;

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
import neo.com.noibai_airport.Adapter.AdapterCheckinOline;
import neo.com.noibai_airport.Config.Constants;
import neo.com.noibai_airport.Model.AirlineInfo;
import neo.com.noibai_airport.Model.ObjWebview;
import neo.com.noibai_airport.R;
import neo.com.noibai_airport.View.Activity.ActivityBooking;
import neo.com.noibai_airport.untils.BaseActivity;
import neo.com.noibai_airport.untils.ItemClickListener;

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
public class ActivityCheckinOnline extends BaseActivity {
    @Override
    public int setContentViewId() {
        return R.layout.activity_recycleview;
    }

    AdapterCheckinOline adapter;
    RecyclerView.LayoutManager mLayoutManager;
    List<AirlineInfo> mLisFlight;
    @BindView(R.id.recycle_list_service)
    RecyclerView recycle_flight_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initAppbar();
        initData();
        init();

    }

    private void initData() {
        mLisFlight = new ArrayList<>();
        mLisFlight.add(new AirlineInfo("VietNam Airline", "vi_al",
                "Tổng công ty hàng không Việt Nam - CTCP. Số 200 Nguyễn Sơn, Phường Bồ Đề, Q.Long Biên, Hà Nội",
                "Điện thoại:(+84-24) 38272289. Fax: (+84-24) 38272289",
                "https://www.vietnamairlines.com/vn/vi/travel-information/check-in",
                "https://www.vietnamairlines.com/vn/en/travel-information/check-in"));
        mLisFlight.add(new AirlineInfo("Jetstar Pacific", "jet",
                "Jetstar Pacific, là hãng hàng không giá rẻ đầu tiên của Việt Nam, có trụ sở tại Sân bay Quốc tế Tân Sơn Nhất, Thành phố Hồ Chí Minh, Việt Nam",
                "Điện thoại: 1900 1550",
                "https://www.jetstar.com/vn/vi/online-checkin",
                ""));
        mLisFlight.add(new AirlineInfo("VietjetAir", "vi_jet",
                "CÔNG TY CỔ PHẦN HÀNG KHÔNG VIETJET. 302/3 Phố Kim Mã, Phường Ngọc Khánh, Quận Ba Đình, Thành phố Hà Nội. ",
                "Điện thoại:(84-28) 35471866 Fax: (84-28) 35471865",
                "https://booking.vietjetair.com/SearchResCheckin.aspx?lang=vi",
                "https://booking.vietjetair.com/SearchResCheckin.aspx?lang=en"));

    }

    public void initAppbar() {
        ImageView img_search = findViewById(R.id.img_search);
        ImageView img_back = findViewById(R.id.img_back);
        TextView txt_title = findViewById(R.id.txt_title_main);
        ImageView img_chatbox = findViewById(R.id.img_chatbox);

        txt_title.setText(R.string.title_checkin);
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
        adapter = new AdapterCheckinOline(this, mLisFlight);
        mLayoutManager = new GridLayoutManager(this, 1);
        //recycle_category.setNestedScrollingEnabled(false);

        recycle_flight_info.setLayoutManager(mLayoutManager);
        recycle_flight_info.setItemAnimator(new DefaultItemAnimator());
        recycle_flight_info.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        adapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClickItem(int position, Object item) {
                AirlineInfo obj = (AirlineInfo) item;
                ObjWebview objWebview = new ObjWebview(obj.getsName(), obj.getsName(), 0, obj.getsUrl_Vi(),obj.getsUrl_En());
                Intent intent = new Intent(ActivityCheckinOnline.this, ActivityBooking.class);
                intent.putExtra(Constants.KEY_SEND_URL_WEBVIEW, objWebview);
                startActivity(intent);
            }
        });
    }
}
