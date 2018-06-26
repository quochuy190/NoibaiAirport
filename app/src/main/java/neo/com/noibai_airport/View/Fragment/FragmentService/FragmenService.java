package neo.com.noibai_airport.View.Fragment.FragmentService;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import neo.com.noibai_airport.Adapter.AdapterCategoryService;
import neo.com.noibai_airport.App;
import neo.com.noibai_airport.Config.Constants;
import neo.com.noibai_airport.Model.CategoryService;
import neo.com.noibai_airport.Model.CategoryShops;
import neo.com.noibai_airport.Model.ObjMenu;
import neo.com.noibai_airport.Model.ObjService;
import neo.com.noibai_airport.Model.Product;
import neo.com.noibai_airport.R;
import neo.com.noibai_airport.untils.BaseFragment;
import neo.com.noibai_airport.untils.OnListenerItemClickObjService;
import neo.com.noibai_airport.untils.SharedPrefs;

public class FragmenService extends BaseFragment implements OnListenerItemClickObjService, ImlService.View {
    public static FragmenService fragment;
    AdapterCategoryService adapter;
    RecyclerView.LayoutManager mLayoutManager;
    List<CategoryService> mLisCateService;
    @BindView(R.id.recycle_service_main)
    RecyclerView recycle_service_main;
    PresenterService mPresenter;
    private String sUserId;

    public static synchronized FragmenService getInstance() {
        if (fragment == null) {
            fragment = new FragmenService();
        }
        return (fragment);
    }

    private boolean isLoadView = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLisCateService = new ArrayList<>();
        isLoadView = true;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_allservice, container, false);
        ButterKnife.bind(this, view);
        mPresenter = new PresenterService(this);
        init();
        initData();
        return view;
    }

    private void initData() {
        if (App.isLoadService) {
            if (isNetwork()) {
                sUserId = SharedPrefs.getInstance().get(Constants.KEY_USERID, String.class);
                showDialogLoading();
                mPresenter.get_service_list(sUserId);
            }
        } else {
            mLisCateService.clear();
            mLisCateService.addAll(App.lisCateService);
            adapter.notifyDataSetChanged();
        }


    }

    private void init() {
        adapter = new AdapterCategoryService(getContext(), mLisCateService, this);
        mLayoutManager = new GridLayoutManager(getContext(), 1);
        //recycle_category.setNestedScrollingEnabled(false);
        recycle_service_main.setHasFixedSize(true);
        recycle_service_main.setLayoutManager(mLayoutManager);
        recycle_service_main.setItemAnimator(new DefaultItemAnimator());
        recycle_service_main.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onClickListener(Object objService) {
        if (isNetwork()){
            ObjService obj = (ObjService) objService;
            if (obj.getsMENUID().equals("0")) {
                Intent intent = new Intent(getContext(), ActivityDetailService.class);
                intent.putExtra(Constants.KEY_SEND_DETAIL_SERVICE, obj);
                startActivity(intent);
            } else if (obj.getsMENUID().equals("1")) {
                Intent intent = new Intent(getContext(), ActivityListProduct.class);
                intent.putExtra(Constants.KEY_SEND_PRODUCT, obj);
                startActivity(intent);
            }
        }


    }

    @Override
    public void show_list_service(List<CategoryService> lisCaService) {
        hideDialogLoading();
        if (lisCaService.size() > 0) {
            mLisCateService.addAll(lisCaService);
            adapter.notifyDataSetChanged();
            App.lisCateService.clear();
            App.lisCateService.addAll(lisCaService);
            App.isLoadService = false;
        }
    }

    @Override
    public void show_list_shop(List<CategoryShops> lisCaService) {
        hideDialogLoading();
    }

    @Override
    public void show_error_api(String sMessage) {
        hideDialogLoading();

    }

    @Override
    public void show_list_menu_shop(List<ObjMenu> lisMenuShop) {
        hideDialogLoading();
    }

    @Override
    public void show_list_product(List<Product> lisProduct) {
        hideDialogLoading();
    }
}
