package neo.com.noibaiairport.Fragment.FragmentService;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import neo.com.noibaiairport.Adapter.AdapterCategoryService;
import neo.com.noibaiairport.Model.CategoryService;
import neo.com.noibaiairport.Model.ObjService;
import neo.com.noibaiairport.R;
import neo.com.noibaiairport.untils.BaseFragment;
import neo.com.noibaiairport.untils.OnListenerItemClickObjService;

public class FragmenService extends BaseFragment implements OnListenerItemClickObjService {
    public static FragmenService fragment;
    AdapterCategoryService adapter;
    RecyclerView.LayoutManager mLayoutManager;
    List<CategoryService> mLisCateService;
    @BindView(R.id.recycle_service_main)
    RecyclerView recycle_service_main;
    List<ObjService> lis1;
    List<ObjService> lis2;
    List<ObjService> lis3;
    List<ObjService> lis4;
    public static synchronized FragmenService getInstance() {
        if (fragment == null) {
            fragment = new FragmenService();
        }
        return (fragment);
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_allservice, container, false);
        ButterKnife.bind(this, view);
        init();
        initData();
        return view;
    }

    private void initData() {
        lis1 = new ArrayList<>();
        lis2 = new ArrayList<>();
        lis3 = new ArrayList<>();
        lis4 = new ArrayList<>();
        lis1.add(new ObjService("1", "QUẦY NUOC MIỄN PHÍ",
                "http://tranhsondaudepamia.com/wp-content/uploads/2017/02/phong-canh-viet-nam-tuyet-dep-720x380.jpg"));
        lis1.add(new ObjService("1", "SẠC ĐT MIỄN PHÍ",
                "http://tranhsondaudepamia.com/wp-content/uploads/2017/02/phong-canh-viet-nam-tuyet-dep-720x380.jpg"));
        lis1.add(new ObjService("1", "XE ĐẨY MIỄN PHÍ",
                "http://tranhsondaudepamia.com/wp-content/uploads/2017/02/phong-canh-viet-nam-tuyet-dep-720x380.jpg"));
        lis1.add(new ObjService("1", "CHẤM CHẤM MIỄN PHÍ",
                "http://tranhsondaudepamia.com/wp-content/uploads/2017/02/phong-canh-viet-nam-tuyet-dep-720x380.jpg"));
        lis2.add(new ObjService("1", "TAXI",
                "https://vietnambiz.vn/stores/news_dataimages/thanhnt/012017/03/14/tu-112017-honda-accord-giam-gia-80-trieu-dong-48-.5090.jpg"));
        lis2.add(new ObjService("1", "TẦU HOẢ",
                "https://vietnambiz.vn/stores/news_dataimages/thanhnt/012017/03/14/tu-112017-honda-accord-giam-gia-80-trieu-dong-48-.5090.jpg"));
        lis2.add(new ObjService("1", "MÁY BAY",
                "https://vietnambiz.vn/stores/news_dataimages/thanhnt/012017/03/14/tu-112017-honda-accord-giam-gia-80-trieu-dong-48-.5090.jpg"));
        lis2.add(new ObjService("1", "DUY THUYỀN",
                "https://vietnambiz.vn/stores/news_dataimages/thanhnt/012017/03/14/tu-112017-honda-accord-giam-gia-80-trieu-dong-48-.5090.jpg"));
        lis3.add(new ObjService("1", "HOTEL",
                "https://getdiskon.com/images/uploads/promo/1505623668hotel-santika-diskon-promo-hotel-santika-dis.jpg"));
        lis3.add(new ObjService("1", "HOTEL",
                "https://getdiskon.com/images/uploads/promo/1505623668hotel-santika-diskon-promo-hotel-santika-dis.jpg"));
        lis3.add(new ObjService("1", "KHÁCH SẠN",
                "https://getdiskon.com/images/uploads/promo/1505623668hotel-santika-diskon-promo-hotel-santika-dis.jpg"));
        lis4.add(new ObjService("1", "DUY THUYỀN",
                "https://vietnambiz.vn/stores/news_dataimages/thanhnt/012017/03/14/tu-112017-honda-accord-giam-gia-80-trieu-dong-48-.5090.jpg"));
        lis4.add(new ObjService("1", "DUY THUYỀN",
                "https://vietnambiz.vn/stores/news_dataimages/thanhnt/012017/03/14/tu-112017-honda-accord-giam-gia-80-trieu-dong-48-.5090.jpg"));
        lis4.add(new ObjService("1", "DUY THUYỀN",
                "https://vietnambiz.vn/stores/news_dataimages/thanhnt/012017/03/14/tu-112017-honda-accord-giam-gia-80-trieu-dong-48-.5090.jpg"));
        lis4.add(new ObjService("1", "DUY THUYỀN",
                "https://vietnambiz.vn/stores/news_dataimages/thanhnt/012017/03/14/tu-112017-honda-accord-giam-gia-80-trieu-dong-48-.5090.jpg"));
        mLisCateService.add(new CategoryService("1","DỊCH VỤ MIỄN PHÍ",lis1));
        mLisCateService.add(new CategoryService("2","DI CHUYỂN",lis2));
        mLisCateService.add(new CategoryService("3","DỊCH VỤ KHAC",lis3));
        mLisCateService.add(new CategoryService("4","DỊCH VỤ ĐÉO BIẾT",lis4));
        adapter.notifyDataSetChanged();
    }

    private void init() {
        mLisCateService = new ArrayList<>();
        adapter = new AdapterCategoryService( getContext(),mLisCateService, this);
        mLayoutManager = new GridLayoutManager(getContext(), 1);
        //recycle_category.setNestedScrollingEnabled(false);
        recycle_service_main.setHasFixedSize(true);
        recycle_service_main.setLayoutManager(mLayoutManager);
        recycle_service_main.setItemAnimator(new DefaultItemAnimator());
        recycle_service_main.setAdapter(adapter);
       // adapterCategory.notifyDataSetChanged();
    }


    @Override
    public void onClickListener(ObjService objService) {
        Toast.makeText(getContext(), ""+objService.getmName(), Toast.LENGTH_SHORT).show();
        //startActivity();
    }
}
