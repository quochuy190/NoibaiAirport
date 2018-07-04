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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

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
    @BindView(R.id.spn_teminal)
    Spinner spn_teminal;
    @BindView(R.id.spn_floor)
    Spinner spn_floor;
    @BindView(R.id.spn_area)
    Spinner spn_area;
    String sTerminal, sFloor, sArea;
    boolean isAddFilter = true;
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
        initSpiner();
        initData();
        return view;
    }

    private void initSpiner() {
        ArrayAdapter<CharSequence> adapter_terminal = ArrayAdapter.createFromResource(getContext(),
                R.array.arr_terminal, R.layout.item_spinner);
        adapter_terminal.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_teminal.setAdapter(adapter_terminal);
        spn_teminal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        sTerminal = "";
                        filter_terminal(sTerminal, sFloor, sArea, App.lisCateService);
                        break;
                    case 1:
                        sTerminal = "T1";
                        filter_terminal(sTerminal, sFloor, sArea, App.lisCateService);
                        break;
                    case 2:
                        sTerminal = "T2";
                        filter_terminal(sTerminal, sFloor, sArea, App.lisCateService);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // lọc theo tầng
        ArrayAdapter<CharSequence> adapter_floor = ArrayAdapter.createFromResource(getContext(),
                R.array.arr_floor,  R.layout.item_spinner);
        adapter_floor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_floor.setAdapter(adapter_floor);
        spn_floor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        sFloor = "";
                        filter_terminal(sTerminal, sFloor, sArea, App.lisCateService);
                        break;
                    case 1:
                        sFloor = "1";
                        filter_terminal(sTerminal, sFloor, sArea, App.lisCateService);
                        break;
                    case 2:
                        sFloor = "2";
                        filter_terminal(sTerminal, sFloor, sArea, App.lisCateService);
                        break;
                    case 3:
                        sFloor = "3";
                        filter_terminal(sTerminal, sFloor, sArea, App.lisCateService);
                        break;

                    case 4:
                        sFloor = "4";
                        filter_terminal(sTerminal, sFloor, sArea, App.lisCateService);
                        break;


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter<CharSequence> adapter_area = ArrayAdapter.createFromResource(getContext(),
                R.array.arr_area,  R.layout.item_spinner);
        adapter_area.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_area.setAdapter(adapter_area);
        spn_area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        sArea = "";
                        filter_terminal(sTerminal, sFloor, sArea, App.lisCateService);
                        break;
                    case 1:
                        sArea = "1";
                        filter_terminal(sTerminal, sFloor, sArea, App.lisCateService);
                        break;
                    case 2:
                        sArea = "0";
                        filter_terminal(sTerminal, sFloor, sArea, App.lisCateService);
                        break;

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
        if (isNetwork()) {
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
        if (lisCaService != null && lisCaService.size() > 0) {
            if (lisCaService.get(0).getsERROR().equals("0000")) {
                mLisCateService.addAll(lisCaService);
                adapter.notifyDataSetChanged();
                App.lisCateService.clear();
                App.lisCateService.addAll(lisCaService);
                App.isLoadService = false;
            } else {
                showAlertDialog(getString(R.string.error), lisCaService.get(0).getsRESULT());
            }
        }
    }

    @Override
    public void show_list_shop(List<CategoryShops> lisCaService) {
        hideDialogLoading();
    }

    @Override
    public void show_error_api(String sMessage) {
        hideDialogLoading();
        showAlertErrorNetwork();
    }

    @Override
    public void show_list_menu_shop(List<ObjMenu> lisMenuShop) {
        hideDialogLoading();
    }

    @Override
    public void show_list_product(List<Product> lisProduct) {
        hideDialogLoading();
    }
    // Lọc danh sách dịch vụ theo nhà ga
    private void filter_terminal(String sTerminal, String sFloor, String sArea, List<CategoryService> lisCategory) {
        isAddFilter = true;
        mLisCateService.clear();
        for (int i = 0; i < lisCategory.size(); i++) {
            CategoryService objCateShop = new CategoryService();
            if (lisCategory.get(i).getmLisObjSer() != null && lisCategory.get(i).getmLisObjSer().size() > 0) {
                List<ObjService> lisShopDinne = new ArrayList<>();
                for (int j = 0; j < lisCategory.get(i).getmLisObjSer().size(); j++) {
                    isAddFilter = true;
                    if (sTerminal.length() > 0 &&
                            lisCategory.get(i).getmLisObjSer().get(j).getsTERMINAL().indexOf(sTerminal) < 0) {
                        isAddFilter = false;
                    }
                    if (sFloor.length() > 0 &&
                            lisCategory.get(i).getmLisObjSer().get(j).getsFLOOR().indexOf(sFloor) < 0) {
                        isAddFilter = false;
                    }
                    if (sArea.length() > 0 &&
                            lisCategory.get(i).getmLisObjSer().get(j).getsAREA().indexOf(sArea) < 0) {
                        isAddFilter = false;
                    }
                    if (isAddFilter){
                        lisShopDinne.add(lisCategory.get(i).getmLisObjSer().get(j));
                    }
                }
                if (lisShopDinne.size() > 0) {
                    objCateShop.setmName(lisCategory.get(i).getmName());
                    objCateShop.setmLisObjSer(lisShopDinne);
                    mLisCateService.add(objCateShop);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }
}
