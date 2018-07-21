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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

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
    private static final String TAG = "FragmenShopAndDine";
    public static FragmenShopAndDine fragment;
    AdapterCategoryShop adapter;
    RecyclerView.LayoutManager mLayoutManager;
    List<CategoryShops> mLisShop;
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
    List<String> lis_Terminal = new ArrayList<>();
    List<CategoryShops> lisTempCategoryShop = new ArrayList<>();
    String sTerminal, sFloor, sArea;
    boolean isAddFilter = true;

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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_allservice, container, false);
        ButterKnife.bind(this, view);
        mPresenter = new PresenterService(this);
        init();
        initDataSpinner();
        //reloadspiner();
        initSpiner();
        initData();
        return view;
    }

    private void initDataSpinner() {
        lis_Terminal.add("All");
        lis_Terminal.add("T1");
        lis_Terminal.add("T2");
    }

    private void reloadspiner() {
        sTerminal = "";
        sFloor = "";
        sArea = "";
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
                        filter_terminal(sTerminal, sFloor, sArea, App.lisCateShops);
                        break;
                    case 1:
                        sTerminal = "T1";
                        filter_terminal(sTerminal, sFloor, sArea, App.lisCateShops);
                        break;
                    case 2:
                        sTerminal = "T2";
                        filter_terminal(sTerminal, sFloor, sArea, App.lisCateShops);
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
                        filter_terminal(sTerminal, sFloor, sArea, App.lisCateShops);
                        break;
                    case 1:
                        sFloor = "1";
                        filter_terminal(sTerminal, sFloor, sArea, App.lisCateShops);
                        break;
                    case 2:
                        sFloor = "2";
                        filter_terminal(sTerminal, sFloor, sArea, App.lisCateShops);
                        break;
                    case 3:
                        sFloor = "3";
                        filter_terminal(sTerminal, sFloor, sArea, App.lisCateShops);
                        break;

                    case 4:
                        sFloor = "4";
                        filter_terminal(sTerminal, sFloor, sArea, App.lisCateShops);
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
                        filter_terminal(sTerminal, sFloor, sArea, App.lisCateShops);
                        break;
                    case 1:
                        sArea = "1";
                        filter_terminal(sTerminal, sFloor, sArea, App.lisCateShops);
                        break;
                    case 2:
                        sArea = "0";
                        filter_terminal(sTerminal, sFloor, sArea, App.lisCateShops);
                        break;

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void initData() {
        if (App.isLoadShops) {
            showDialogLoading();
            sUserId = SharedPrefs.getInstance().get(Constants.KEY_USERID, String.class);
            mPresenter.get_service_shops(sUserId);
        } else {
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
        mPresenter.api_access_log(sUserId, "getshoplist|"+sUserId,obj.getmId(), "shop" );
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

    // Lọc danh sách dịch vụ theo nhà ga
    private void filter_terminal(String sTerminal, String sFloor, String sArea, List<CategoryShops> lisCategory) {
        isAddFilter = true;
        mLisShop.clear();
        for (int i = 0; i < lisCategory.size(); i++) {
            CategoryShops objCateShop = new CategoryShops();
            if (lisCategory.get(i).getmLisObjSer() != null && lisCategory.get(i).getmLisObjSer().size() > 0) {
                List<ShopsDine> lisShopDinne = new ArrayList<>();
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
                    mLisShop.add(objCateShop);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }
}
