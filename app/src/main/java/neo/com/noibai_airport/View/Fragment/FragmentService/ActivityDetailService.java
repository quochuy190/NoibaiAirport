package neo.com.noibai_airport.View.Fragment.FragmentService;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import neo.com.noibai_airport.Config.Constants;
import neo.com.noibai_airport.Model.ObjService;
import neo.com.noibai_airport.R;
import neo.com.noibai_airport.View.Activity.ActivityShops.ActivityComments;
import neo.com.noibai_airport.View.Activity.Feedback.ActivityRegister;
import neo.com.noibai_airport.untils.BaseActivity;
import neo.com.noibai_airport.untils.SharedPrefs;

/**
 * @author Quốc Huy
 * @version 1.0.0
 * @description
 * @desc Developer NEO Company.
 * @created 6/13/2018
 * @updated 6/13/2018
 * @modified by
 * @updated on 6/13/2018
 * @since 1.0
 */
public class ActivityDetailService extends BaseActivity {
    @BindView(R.id.img_shop_main)
    ImageView img_shop_main;
    @BindView(R.id.txt_terminal_detail_service)
    TextView txt_terminal_detail_service;
    @BindView(R.id.txt_floor_detail_service)
    TextView txt_floor_detail_service;
    @BindView(R.id.txt_area_detail_service)
    TextView txt_area_detail_service;
    @BindView(R.id.txt_phone_detail_service)
    TextView txt_phone_detail_service;
    @BindView(R.id.txt_description_detail_service)
    TextView txt_description_detail_service;
    @BindView(R.id.txt_price_detail_service)
    TextView txt_price_detail_service;
    private ObjService mObjService;
    @BindView(R.id.ll_teminal)
    LinearLayout ll_teminal;
    @BindView(R.id.ll_floor)
    LinearLayout ll_floor;
    @BindView(R.id.ll_area)
    LinearLayout ll_area;
    @BindView(R.id.ll_phone)
    LinearLayout ll_phone;
    @BindView(R.id.ll_price)
    LinearLayout ll_price;
    @BindView(R.id.ll_like_comment)
    LinearLayout ll_like_comment;
    @BindView(R.id.ll_comment)
    LinearLayout ll_comment;


    @Override
    public int setContentViewId() {
        return R.layout.activity_service_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initAppbar();
        initData();
        initEvent();
    }

    private void initEvent() {
        ll_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetwork()){
                    boolean isLoginFeedback = SharedPrefs.getInstance().get(Constants.KEY_IS_LOGIN_FEEDBACK, Boolean.class);
                    if (isLoginFeedback) {
                        Intent intent = new Intent(ActivityDetailService.this, ActivityComments.class);
                        intent.putExtra(Constants.KEY_SEND_COMMENT, mObjService.getmId());
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(ActivityDetailService.this, ActivityRegister.class);
                        intent.putExtra(Constants.KEY_SEND_REGISTER, Constants.COMMENTS);
                        startActivity(intent);
                    }
                }
            }
        });
    }

    TextView txt_title;

    public void initAppbar() {
        ImageView img_search = findViewById(R.id.img_search);
        ImageView img_back = findViewById(R.id.img_back);
        txt_title = findViewById(R.id.txt_title_main);
        ImageView img_chatbox = findViewById(R.id.img_chatbox);


        img_chatbox.setVisibility(View.INVISIBLE);
        img_back.setVisibility(View.VISIBLE);
        img_search.setVisibility(View.GONE);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initData() {
        mObjService = (ObjService) getIntent().getSerializableExtra(Constants.KEY_SEND_DETAIL_SERVICE);
        if (mObjService != null) {
            if (mObjService.getmImage() != null && mObjService.getmImage().length() > 0) {
                Glide.with(this).load(mObjService.getmImage()).into(img_shop_main);
            } else
                Glide.with(this).load(R.drawable.image).into(img_shop_main);
            if (mObjService.getmName() != null && mObjService.getmName().length() > 0) {
                txt_title.setText(mObjService.getmName());
            } else
                txt_title.setText(R.string.title_service_detail);
            //set terminal
            if (mObjService.getsTERMINAL() != null && mObjService.getsTERMINAL().length() > 0) {
                ll_teminal.setVisibility(View.VISIBLE);
                txt_terminal_detail_service.setText(mObjService.getsTERMINAL());
            } else
                ll_teminal.setVisibility(View.GONE);
            //set terminal
            if (mObjService.getsFLOOR() != null && mObjService.getsFLOOR().length() > 0) {
                ll_floor.setVisibility(View.VISIBLE);
                txt_floor_detail_service.setText(mObjService.getsFLOOR());
            } else
                ll_floor.setVisibility(View.GONE);
            //set terminal
            if (mObjService.getsAREA() != null && mObjService.getsAREA().length() > 0) {
                ll_area.setVisibility(View.VISIBLE);
                txt_area_detail_service.setText(mObjService.getsAREA());
            } else
                ll_area.setVisibility(View.GONE);
            //set terminal
            if (mObjService.getsHOTLINE() != null && mObjService.getsHOTLINE().length() > 0) {
                ll_phone.setVisibility(View.VISIBLE);
                txt_phone_detail_service.setText(mObjService.getsHOTLINE());
            } else
                ll_phone.setVisibility(View.GONE);
            //set terminal
            if (mObjService.getsDESCRIPTION() != null && mObjService.getsDESCRIPTION().length() > 0) {
                txt_description_detail_service.setVisibility(View.VISIBLE);
                txt_description_detail_service.setText(mObjService.getsDESCRIPTION());
            } else
                txt_description_detail_service.setVisibility(View.GONE);
            //set price
            if (mObjService.getsPRICE() != null && mObjService.getsPRICE().length() > 0) {
                ll_price.setVisibility(View.VISIBLE);
                txt_price_detail_service.setText(mObjService.getsPRICE());
            } else
                ll_price.setVisibility(View.GONE);
        }
    }
}
