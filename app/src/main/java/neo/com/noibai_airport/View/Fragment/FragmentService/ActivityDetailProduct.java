package neo.com.noibai_airport.View.Fragment.FragmentService;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import neo.com.noibai_airport.Adapter.AdapterComment;
import neo.com.noibai_airport.Config.Constants;
import neo.com.noibai_airport.Model.Comments;
import neo.com.noibai_airport.Model.Product;
import neo.com.noibai_airport.R;
import neo.com.noibai_airport.View.Activity.ActivityShops.ActivityComments;
import neo.com.noibai_airport.View.Activity.ActivityShops.ImlComment;
import neo.com.noibai_airport.View.Activity.ActivityShops.PresenterComment;
import neo.com.noibai_airport.View.Activity.Feedback.ActivityRegister;
import neo.com.noibai_airport.untils.BaseActivity;
import neo.com.noibai_airport.untils.ItemClickListener;
import neo.com.noibai_airport.untils.KeyboardUtil;
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
public class ActivityDetailProduct extends BaseActivity implements ImlComment.View {
    @BindView(R.id.img_shop_main)
    ImageView img_shop_main;
    @BindView(R.id.txt_suppliers_detail_product)
    TextView txt_suppliers_detail_product;
    @BindView(R.id.txt_location_detail_product)
    TextView txt_location_detail_product;
    @BindView(R.id.txt_price_detail_product)
    TextView txt_price_detail_product;
    @BindView(R.id.txt_phone_detail_product)
    TextView txt_phone_detail_product;
    @BindView(R.id.txt_description_detail_product)
    TextView txt_description_detail_service;
    @BindView(R.id.txt_schedule_time_product)
    TextView txt_schedule_time_product;
    private Product mObjProduct;
    @BindView(R.id.ll_suppliers)
    LinearLayout ll_suppliers;
    @BindView(R.id.ll_location)
    LinearLayout ll_location;
    @BindView(R.id.ll_price)
    LinearLayout ll_price;
    @BindView(R.id.ll_phone)
    LinearLayout ll_phone;
    @BindView(R.id.ll_time)
    LinearLayout ll_time;
    @BindView(R.id.ll_like_comment)
    LinearLayout ll_like_comment;
    @BindView(R.id.ll_comment)
    LinearLayout ll_comment;
   /* Comment*/
   @BindView(R.id.reyclerview_comment_service)
   RecyclerView recycle_comment;
    AdapterComment adapterLanguage;
    private List<Comments> mLisComment;
    RecyclerView.LayoutManager mLayoutManager;
    PresenterComment mPresenter;
    @BindView(R.id.button_chatbox_send)
    ImageView button_chatbox_send;
    @BindView(R.id.edt_input_message)
    EditText edt_input_message;
    private String sUserFeedback, sEmailFeedback, sUserId, sServiceId;

    @Override
    public int setContentViewId() {
        return R.layout.activity_product_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new PresenterComment(this);
        initAppbar();
        init();
        initData();
        initEvent();
    }
    String phone;
    private void initEvent() {
        ll_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetwork()) {
                    boolean isLoginFeedback = SharedPrefs.getInstance().get(Constants.KEY_IS_LOGIN_FEEDBACK, Boolean.class);
                    if (isLoginFeedback) {
                        Intent intent = new Intent(ActivityDetailProduct.this, ActivityComments.class);
                        intent.putExtra(Constants.KEY_SEND_COMMENT, mObjProduct.getmId());
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(ActivityDetailProduct.this, ActivityRegister.class);
                        intent.putExtra(Constants.KEY_SEND_REGISTER, Constants.COMMENTS);
                        startActivity(intent);
                    }
                }
            }
        });
        ll_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone = PhoneNumberUtils.stripSeparators(mObjProduct.getsPHONE());
                if (Build.VERSION.SDK_INT < 23) {
                    phoneCall(phone);

                } else {
                    if (ActivityCompat.checkSelfPermission(ActivityDetailProduct.this,
                            Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                        phoneCall(phone);
                    } else {
                        final String[] PERMISSIONS_STORAGE = {Manifest.permission.CALL_PHONE};
                        //Asking request Permissions
                        ActivityCompat.requestPermissions(ActivityDetailProduct.this, PERMISSIONS_STORAGE, 9);
                    }
                }
            }
        });
        button_chatbox_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_input_message.getText().toString().length() > 0) {
                    sUserFeedback = SharedPrefs.getInstance().get(Constants.KEY_USER_FEEDBACK, String.class);
                    sEmailFeedback = SharedPrefs.getInstance().get(Constants.KEY_EMAIL_FEEDBACK, String.class);
                    if (sUserFeedback != null && sUserFeedback.length() > 0
                            && sEmailFeedback != null && sEmailFeedback.length() > 0) {
                        showDialogLoading();
                        mPresenter.add_comment(sUserId, sUserFeedback, mObjProduct.getmId(),
                                edt_input_message.getText().toString(), "", sServiceId);
                    } else {
                        Intent intent = new Intent(ActivityDetailProduct.this,
                                ActivityRegister.class);
                        intent.putExtra(Constants.KEY_SEND_REGISTER, Constants.COMMENTS);
                        startActivity(intent);
                    }
                } else Toast.makeText(ActivityDetailProduct.this,
                        "Bạn chưa nhập vào thông tin", Toast.LENGTH_SHORT).show();
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
        mObjProduct = (Product) getIntent().getSerializableExtra(Constants.KEY_SEND_DETAIL_PRODUCT);
        txt_title.setText(mObjProduct.getsPRODUCTNAME());
        if (mObjProduct != null) {
            sServiceId = mObjProduct.getmIdProduct();
            sUserId = SharedPrefs.getInstance().get(Constants.KEY_USERID, String.class);
            showDialogLoading();
            mPresenter.get_comment(sUserId, mObjProduct.getmId(), sServiceId);
            if (mObjProduct.getsPRODUCTAVATAR() != null && mObjProduct.getsPRODUCTAVATAR().length() > 0) {
                Glide.with(this)
                        .load(mObjProduct.getsPRODUCTAVATAR())
                        .error(R.drawable.img_default)
                        .into(img_shop_main);
            } else
                Glide.with(this).load(R.drawable.img_default).into(img_shop_main);
            if (mObjProduct.getsSUPPLIERS() != null) {
                txt_suppliers_detail_product.setText(mObjProduct.getsSUPPLIERS());
            } else
                ll_suppliers.setVisibility(View.GONE);
            if (mObjProduct.getsLOCATION() != null) {
                txt_location_detail_product.setText(mObjProduct.getsLOCATION());
            } else
                ll_location.setVisibility(View.GONE);
            if (mObjProduct.getsPRICE() != null&&mObjProduct.getsPRICE().length()>0) {
                txt_price_detail_product.setText(mObjProduct.getsPRICE());
            } else
                ll_price.setVisibility(View.GONE);
            if (mObjProduct.getsPHONE() != null&&mObjProduct.getsPHONE().length()>0) {
                txt_phone_detail_product.setText(mObjProduct.getsPHONE());
            } else
                ll_phone.setVisibility(View.GONE);
            if (mObjProduct.getsSCHEDULE_TIME() != null&&mObjProduct.getsSCHEDULE_TIME().length()>0) {
                txt_schedule_time_product.setText(mObjProduct.getsSCHEDULE_TIME());
            } else
                ll_time.setVisibility(View.GONE);
            if (mObjProduct.getsDESCRIPTION() != null) {
                txt_description_detail_service.setText(mObjProduct.getsDESCRIPTION());
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        boolean permissionGranted = false;
        switch (requestCode) {
            case 9:
                permissionGranted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                break;
        }
        if (permissionGranted) {
            phoneCall(phone);
        } else {
            Toast.makeText(this, "You don't assign permission.", Toast.LENGTH_SHORT).show();
        }
    }

    private void phoneCall(String phone) {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + phone));
            this.startActivity(callIntent);
        } else {
            Toast.makeText(this, "You don't assign permission.", Toast.LENGTH_SHORT).show();
        }
    }


    private void init() {
        mLisComment = new ArrayList<>();
        adapterLanguage = new AdapterComment(this, mLisComment);
        mLayoutManager = new GridLayoutManager(this, 1);
        recycle_comment.setNestedScrollingEnabled(false);
        recycle_comment.setHasFixedSize(true);
        recycle_comment.setLayoutManager(mLayoutManager);
        recycle_comment.setItemAnimator(new DefaultItemAnimator());
        recycle_comment.setAdapter(adapterLanguage);
        adapterLanguage.notifyDataSetChanged();

        adapterLanguage.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClickItem(int position, final Object item) {

            }
        });
    }

    @Override
    public void show_list_comment(List<Comments> mLisComments) {
        hideDialogLoading();
        if (mLisComments != null) {
            mLisComment.clear();
            mLisComment.addAll(mLisComments);
            adapterLanguage.notifyDataSetChanged();
            recycle_comment.scrollToPosition(mLisComment.size() - 1);
        }
    }

    @Override
    public void show_result_addComments() {
        hideDialogLoading();
        edt_input_message.setText("");
        KeyboardUtil.hideSoftKeyboard(this);
        mPresenter.get_comment(sUserId,mObjProduct.getmId(), sServiceId);
    }

    @Override
    public void show_error_addComments() {
        hideDialogLoading();
    }

    @Override
    public void show_error_api() {
        hideDialogLoading();
    }
}
