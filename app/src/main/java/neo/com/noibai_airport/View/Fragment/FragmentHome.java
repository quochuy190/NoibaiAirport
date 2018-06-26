package neo.com.noibai_airport.View.Fragment;

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
import neo.com.noibai_airport.View.Activity.ActivityBooking;
import neo.com.noibai_airport.Adapter.AdapterCategory;
import neo.com.noibai_airport.Model.Category;
import neo.com.noibai_airport.R;
import neo.com.noibai_airport.untils.BaseFragment;
import neo.com.noibai_airport.untils.ItemClickListener;

public class FragmentHome extends BaseFragment {
    public static FragmentHome fragment;

    public static synchronized FragmentHome getInstance() {
        if (fragment == null) {
            fragment = new FragmentHome();
        }
        return (fragment);
    }

    @BindView(R.id.recycle_category)
    RecyclerView recycle_category;
    public AdapterCategory adapterCategory;
    private List<Category> listCate;
    RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        initData();
        init();
        return view;
    }

    private void initData() {
        listCate = new ArrayList<>();
        listCate.add(new Category("0", "Exchange Money", "",
                "http://im.hunt.in/cg/Solapur/City-Guide/Currency1.jpg"));
        listCate.add(new Category("0", "Exchange Money", "",
                "http://www.bestofcurrency.com/wp-content/uploads/2015/09/exchange-money.jpg"));
        listCate.add(new Category("0", "Exchange Money", "",
                "http://www.bestofcurrency.com/wp-content/uploads/2015/09/exchange-money.jpg"));
        listCate.add(new Category("0", "Exchange Money", "",
                "http://im.hunt.in/cg/Solapur/City-Guide/Currency1.jpg"));
        listCate.add(new Category("0", "Exchange Money", "",
                "http://www.bestofcurrency.com/wp-content/uploads/2015/09/exchange-money.jpg"));
        listCate.add(new Category("0", "Exchange Money", "",
                "http://im.hunt.in/cg/Solapur/City-Guide/Currency1.jpg"));
        listCate.add(new Category("0", "Exchange Money", "",
                "http://www.bestofcurrency.com/wp-content/uploads/2015/09/exchange-money.jpg"));
        listCate.add(new Category("0", "Exchange Money", "",
                "http://www.bestofcurrency.com/wp-content/uploads/2015/09/exchange-money.jpg"));

    }

    private void init() {
        adapterCategory = new AdapterCategory(getContext(), listCate);
        mLayoutManager = new GridLayoutManager(getContext(), 2);
        //recycle_category.setNestedScrollingEnabled(false);
        recycle_category.setHasFixedSize(true);
        recycle_category.setLayoutManager(mLayoutManager);
        recycle_category.setItemAnimator(new DefaultItemAnimator());
        recycle_category.setAdapter(adapterCategory);
        adapterCategory.notifyDataSetChanged();

       adapterCategory.setItemClickListener(new ItemClickListener() {
           @Override
           public void onClickItem(int position, Object item) {
               startActivity(new Intent(getActivity(), ActivityBooking.class));
           }
       });
    }
}
