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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import neo.com.noibai_airport.Adapter.AdapterShopsDine;
import neo.com.noibai_airport.Config.Constants;
import neo.com.noibai_airport.Model.ShopsDine;
import neo.com.noibai_airport.R;
import neo.com.noibai_airport.View.Activity.ActivityShops.ActivityShops;
import neo.com.noibai_airport.untils.BaseFragment;
import neo.com.noibai_airport.untils.setOnItemClickListener;

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
        mLisShops = new ArrayList<>();
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

        adapterDine.notifyDataSetChanged();

    }

    private void initShops() {

        adapterShops = new AdapterShopsDine( mLisShops, getContext());
        mLayoutManager = new GridLayoutManager(getContext(),2, GridLayoutManager.HORIZONTAL,false );
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
