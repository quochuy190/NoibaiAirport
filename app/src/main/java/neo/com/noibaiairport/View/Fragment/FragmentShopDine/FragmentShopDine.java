package neo.com.noibaiairport.Fragment.FragmentShopDine;

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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import neo.com.noibaiairport.Adapter.AdapterShopsDine;
import neo.com.noibaiairport.Config.Constants;
import neo.com.noibaiairport.Model.ShopsDine;
import neo.com.noibaiairport.R;
import neo.com.noibaiairport.View.Activity.ActivityShops.ActivityShops;
import neo.com.noibaiairport.untils.BaseFragment;
import neo.com.noibaiairport.untils.setOnItemClickListener;

public class FragmentShopDine extends BaseFragment {
    public static FragmentShopDine fragment;
    public static synchronized FragmentShopDine getInstance() {
        if (fragment == null) {
            fragment = new FragmentShopDine();
        }
        return (fragment);
    }
    private List<ShopsDine> mLisShops;
    AdapterShopsDine adapterShops;
    RecyclerView.LayoutManager mLayoutManager;
    @BindView(R.id.recycle_shops)
    RecyclerView recycle_shops;
    @BindView(R.id.recycle_dine)
    RecyclerView recycle_dine;
    private List<ShopsDine> mLisDine;
    AdapterShopsDine adapterDine;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopdine, container, false);
        ButterKnife.bind(this, view);
        initShops();
        initDine();
        initData();
        return view;
    }

    private void initDine() {
        mLisDine= new ArrayList<>();
        adapterDine = new AdapterShopsDine( mLisDine, getContext());
        // mLayoutManager = new GridLayoutManager(getContext(), 2);
        mLayoutManager = new GridLayoutManager(getContext(),2, GridLayoutManager.HORIZONTAL,false );
        //recycle_category.setNestedScrollingEnabled(false);
        recycle_dine.setHasFixedSize(true);
        recycle_dine.setLayoutManager(mLayoutManager);

        recycle_dine.setItemAnimator(new DefaultItemAnimator());
        recycle_dine.setAdapter(adapterDine);

        adapterDine.setOnIListener(new setOnItemClickListener() {
            @Override
            public void OnItemClickListener(int position) {
                Toast.makeText(getContext(), ""+mLisDine.get(position).getmName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnLongItemClickListener(int position) {

            }
        });
    }

    private void initData() {
        mLisShops.add(new ShopsDine("1","Shoppe",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTd_neK3XgynCl2SaUuA5G7grtcPjXFOOLt-BfejF-APIZAaVwV"));
        mLisShops.add(new ShopsDine("1","Tiki",
                "https://www.telegraph.co.uk/content/dam/business/2017/04/16/TELEMMGLPICT000041724939_trans_NvBQzQNjv4BqHaZ8JMGQ_nOZ1URPL9FaE8iZ_6TH--rKxBA8mEVjkI4.jpeg?imwidth=450"));
        mLisShops.add(new ShopsDine("1","Lazada",
                "https://az769197.vo.msecnd.net/media/534730/island-shop.jpg"));
        mLisShops.add(new ShopsDine("1","Neo",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS8ZLziv1xAmDRTqMf7yTQ6YxJZR6sLoMHhhi5ZiEXXKNRhQHh2"));
        mLisShops.add(new ShopsDine("1","Noibai",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS6gc2-FnvPdgcDhZ-Xg5ecCOuknranHED0OzyUXs27NdOmQu26"));
        adapterShops.notifyDataSetChanged();
        //Data Dine
        mLisDine.add(new ShopsDine("1","KPC",
                "http://amthuccuoituan.com/uploads/001amthuccuoituan.com/4-So-tay-dia-diem/5-Thuc-an-nhanh/KFC-The-gioi-ga-ran-tuyet-voi-khong-the-bo-qua/KFC-The-gioi-ga-ran-tuyet-voi-khong-the-bo-qua-1.jpg"));
        mLisDine.add(new ShopsDine("1","Bia Hải Xồm",
                "https://www.telegraph.co.uk/content/dam/business/2017/04/16/TELEMMGLPICT000041724939_trans_NvBQzQNjv4BqHaZ8JMGQ_nOZ1URPL9FaE8iZ_6TH--rKxBA8mEVjkI4.jpeg?imwidth=450"));
        mLisDine.add(new ShopsDine("1","BBQ Nội bài",
                "https://pastaxi-manager.onepas.vn/content/uploads/articles/2amthuc/nhahang/chiakibbq/chiaki-bbq-1.jpg"));
        mLisDine.add(new ShopsDine("1","Cộng Cafe",
                "https://images.foody.vn/res/g28/278318/prof/s576x330/foody-mobile-cong-hoang-dieu-jpg-573-636122965342863027.jpg"));
        mLisDine.add(new ShopsDine("1","Anh em Quán",
                "http://kientructhegioi.com/wp-content/uploads/2016/02/thiet-ke-quan-cafe-dep-tai-Ha-Noi-9.jpg"));
        mLisDine.add(new ShopsDine("1","Rum Restaurent",
                "https://pastaxi-manager.onepas.vn/content/uploads/articles/2amthuc/nhahang/rumquan/rum-quan-tay-bac-2.jpg"));
        adapterDine.notifyDataSetChanged();

    }

    private void initShops() {
        mLisShops = new ArrayList<>();
        adapterShops = new AdapterShopsDine( mLisShops, getContext());
       // mLayoutManager = new GridLayoutManager(getContext(), 2);
        mLayoutManager = new GridLayoutManager(getContext(),2, GridLayoutManager.HORIZONTAL,false );
        //recycle_category.setNestedScrollingEnabled(false);
        recycle_shops.setHasFixedSize(true);
        recycle_shops.setLayoutManager(mLayoutManager);

        recycle_shops.setItemAnimator(new DefaultItemAnimator());
        recycle_shops.setAdapter(adapterShops);

        adapterShops.setOnIListener(new setOnItemClickListener() {
            @Override
            public void OnItemClickListener(int position) {
                Intent intent = new Intent(getActivity(), ActivityShops.class);
                intent.putExtra(Constants.KEY_SENT_SHOPS, mLisShops.get(position));
                startActivity(intent);
                //Toast.makeText(getContext(), ""+mLisShops.get(position).getmName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnLongItemClickListener(int position) {

            }
        });
    }

}
