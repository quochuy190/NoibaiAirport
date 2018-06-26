package neo.com.noibai_airport.View.Fragment.FragmentService;

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
import neo.com.noibai_airport.Adapter.AdapterListService;
import neo.com.noibai_airport.Config.Constants;
import neo.com.noibai_airport.Model.CategoryService;
import neo.com.noibai_airport.Model.CategoryShops;
import neo.com.noibai_airport.Model.ObjMenu;
import neo.com.noibai_airport.Model.ObjService;
import neo.com.noibai_airport.Model.Product;
import neo.com.noibai_airport.R;
import neo.com.noibai_airport.untils.BaseActivity;
import neo.com.noibai_airport.untils.ItemClickListener;
import neo.com.noibai_airport.untils.SharedPrefs;

public class ActivityListProduct extends BaseActivity implements ImlService.View {
    @BindView(R.id.recycle_language)
    RecyclerView recycle_language;
    AdapterListService adapterLanguage;
    private List<Product> lisLanguage;
    RecyclerView.LayoutManager mLayoutManager;
    PresenterService mPresenter;
    private String sUserId;
    ObjService objService;

    @Override
    public int setContentViewId() {
        return R.layout.activity_change_language;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new PresenterService(this);
        initAppbar();
        init();
        initData();

    }

    private void initData() {
        objService = (ObjService) getIntent().getSerializableExtra(Constants.KEY_SEND_PRODUCT);
        txt_title.setText(objService.getmName());
        sUserId = SharedPrefs.getInstance().get(Constants.KEY_USERID, String.class);
        if (objService != null) {
            showDialogLoading();
            mPresenter.get_list_product(sUserId, objService.getmId());
        }
    }
    TextView txt_title;
    public void initAppbar() {
        txt_title = findViewById(R.id.txt_title_main);
      //  txt_title.setText(R.string.txt_language);
        ImageView img_back = findViewById(R.id.img_back);
        img_back.setVisibility(View.VISIBLE);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void init() {
        lisLanguage = new ArrayList<>();
        adapterLanguage = new AdapterListService(this, lisLanguage);
        mLayoutManager = new GridLayoutManager(this, 2);
        // recycle_language.setNestedScrollingEnabled(false);
        recycle_language.setHasFixedSize(true);
        recycle_language.setLayoutManager(mLayoutManager);
        recycle_language.setItemAnimator(new DefaultItemAnimator());
        recycle_language.setAdapter(adapterLanguage);
        adapterLanguage.notifyDataSetChanged();

        adapterLanguage.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClickItem(int position, final Object item) {
                if (isNetwork()){
                    Product obj = (Product) item;
                    Intent intent = new Intent(ActivityListProduct.this, ActivityDetailProduct.class);
                    intent.putExtra(Constants.KEY_SEND_DETAIL_PRODUCT, obj);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void show_list_service(List<CategoryService> lisCaService) {
        hideDialogLoading();
    }

    @Override
    public void show_list_shop(List<CategoryShops> lisCaShop) {
        hideDialogLoading();
    }

    @Override
    public void show_error_api(String sError) {
        hideDialogLoading();
    }

    @Override
    public void show_list_menu_shop(List<ObjMenu> lisMenuShop) {
        hideDialogLoading();
    }

    @Override
    public void show_list_product(List<Product> lisProduct) {
        hideDialogLoading();
        if (lisProduct != null && lisProduct.size() > 0) {
            lisLanguage.clear();
            lisLanguage.addAll(lisProduct);
            adapterLanguage.notifyDataSetChanged();
        }
    }
}
