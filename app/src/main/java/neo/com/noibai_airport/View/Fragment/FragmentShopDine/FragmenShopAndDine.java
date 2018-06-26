package neo.com.noibai_airport.View.Fragment.FragmentShopDine;

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
import neo.com.noibai_airport.Adapter.AdapterCategoryShop;
import neo.com.noibai_airport.App;
import neo.com.noibai_airport.Config.Constants;
import neo.com.noibai_airport.Model.CategoryService;
import neo.com.noibai_airport.Model.CategoryShops;
import neo.com.noibai_airport.Model.ObjMenu;
import neo.com.noibai_airport.Model.Product;
import neo.com.noibai_airport.Model.ShopsDine;
import neo.com.noibai_airport.R;
import neo.com.noibai_airport.View.Activity.ActivityShops.ActivityShops;
import neo.com.noibai_airport.View.Fragment.FragmentService.ImlService;
import neo.com.noibai_airport.View.Fragment.FragmentService.PresenterService;
import neo.com.noibai_airport.untils.BaseFragment;
import neo.com.noibai_airport.untils.OnListenerItemClickObjService;
import neo.com.noibai_airport.untils.SharedPrefs;

public class FragmenShopAndDine extends BaseFragment implements OnListenerItemClickObjService, ImlService.View {
    public static FragmenShopAndDine fragment;
    AdapterCategoryShop adapter;
    RecyclerView.LayoutManager mLayoutManager;
    List<CategoryShops> mLisShop;
    @BindView(R.id.recycle_service_main)
    RecyclerView recycle_service_main;
    PresenterService mPresenter;
    private String sUserId;

    public static synchronized FragmenShopAndDine getInstance() {
        if (fragment == null) {
            fragment = new FragmenShopAndDine();
        }
        return (fragment);
    }

    private boolean isLoadView = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLisShop = new ArrayList<>();
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
        if (App.isLoadShops) {
            showDialogLoading();
            sUserId = SharedPrefs.getInstance().get(Constants.KEY_USERID, String.class);
            mPresenter.get_service_shops(sUserId);
        }else {
            mLisShop.clear();
            mLisShop.addAll(App.lisCateShops);
            adapter.notifyDataSetChanged();
        }


    }

    private void init() {
        adapter = new AdapterCategoryShop(getContext(), mLisShop, this);
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
        ShopsDine obj = (ShopsDine) objService;
        Intent intent = new Intent(getActivity(), ActivityShops.class);
        intent.putExtra(Constants.KEY_SENT_SHOPS, obj);
        startActivity(intent);
    }

    @Override
    public void show_list_service(List<CategoryService> lisCaService) {
        hideDialogLoading();
    }

    @Override
    public void show_list_shop(List<CategoryShops> lisCaShopsDine) {
        hideDialogLoading();
        if (lisCaShopsDine.size() > 0) {
            App.isLoadShops = false;
            App.lisCateShops.clear();
            App.lisCateShops.addAll(lisCaShopsDine);
            mLisShop.clear();
            mLisShop.addAll(lisCaShopsDine);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void show_error_api(String sMessage) {
        hideDialogLoading();
/*        if (sMessage != null && sMessage.length() > 0) {
            showAlertDialog(getString(R.string.error), sMessage);
        } else
            showAlertDialog(getString(R.string.error_network), getString(R.string.error_network_message));*/
    }

    @Override
    public void show_list_menu_shop(List<ObjMenu> lisMenuShop) {

    }

    @Override
    public void show_list_product(List<Product> lisProduct) {

    }
}
