package neo.com.noibai_airport.View.Fragment.FragmentShopDine;

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
import neo.com.noibai_airport.Adapter.AdapterListMenu;
import neo.com.noibai_airport.Config.Constants;
import neo.com.noibai_airport.Model.CategoryService;
import neo.com.noibai_airport.Model.CategoryShops;
import neo.com.noibai_airport.Model.ObjMenu;
import neo.com.noibai_airport.Model.Product;
import neo.com.noibai_airport.R;
import neo.com.noibai_airport.View.Activity.ActivityShops.ActivityShops;
import neo.com.noibai_airport.View.Fragment.FragmentService.ImlService;
import neo.com.noibai_airport.View.Fragment.FragmentService.PresenterService;
import neo.com.noibai_airport.untils.BaseFragment;
import neo.com.noibai_airport.untils.ItemClickListener;
import neo.com.noibai_airport.untils.SharedPrefs;

public class FragmentShopsMenu extends BaseFragment implements ImlService.View {
    public static FragmentShopsMenu fragment;

    public static synchronized FragmentShopsMenu getInstance() {
        if (fragment == null) {
            fragment = new FragmentShopsMenu();
        }
        return (fragment);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    PresenterService mPresenter;
    AdapterListMenu adapter;
    RecyclerView.LayoutManager mLayoutManager;
    List<ObjMenu> mLisMenu;
    @BindView(R.id.rv_list_menu)
    RecyclerView rv_list_menu;
    private String sUserId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_shop, container, false);
        ButterKnife.bind(this, view);
        mPresenter = new PresenterService(this);
        init();
        initData();

        return view;
    }

    private void init() {
        mLisMenu = new ArrayList<>();
        adapter = new AdapterListMenu(getContext(), mLisMenu);
        mLayoutManager = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
        rv_list_menu.setHasFixedSize(true);
        rv_list_menu.setLayoutManager(mLayoutManager);
        rv_list_menu.setItemAnimator(new DefaultItemAnimator());
        rv_list_menu.setAdapter(adapter);

       adapter.setItemClickListener(new ItemClickListener() {
           @Override
           public void onClickItem(int position, Object item) {

           }
       });

    }

    private void initData() {
        sUserId = SharedPrefs.getInstance().get(Constants.KEY_USERID, String.class);
        if (ActivityShops.mObjShop != null) {
            mPresenter.get_shop_menu(sUserId, ActivityShops.mObjShop.getmId());
        }

    }


    @Override
    public void show_list_service(List<CategoryService> lisCaService) {

    }

    @Override
    public void show_list_shop(List<CategoryShops> lisCaShop) {

    }

    @Override
    public void show_error_api(String sError) {

    }

    @Override
    public void show_list_menu_shop(List<ObjMenu> lisMenuShop) {
        if (lisMenuShop.size() > 0) {
            mLisMenu.clear();
            mLisMenu.addAll(lisMenuShop);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void show_list_product(List<Product> lisProduct) {

    }
}
