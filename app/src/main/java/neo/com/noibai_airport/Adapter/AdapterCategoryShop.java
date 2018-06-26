package neo.com.noibai_airport.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import neo.com.noibai_airport.Model.CategoryShops;
import neo.com.noibai_airport.R;
import neo.com.noibai_airport.untils.OnListenerItemClickObjService;

public class AdapterCategoryShop extends RecyclerView.Adapter<AdapterCategoryShop.CategoryServiceViewHolder>{
    public static Context mContext;
    private List<CategoryShops> mLisCateService;
    private static RecyclerView horizontalList;
    public static OnListenerItemClickObjService onListenerItemClickObjService;

    public AdapterCategoryShop(Context context, List<CategoryShops> mLisCateService,
                               OnListenerItemClickObjService onListenerItemClickObjService) {
        this.onListenerItemClickObjService = onListenerItemClickObjService;
        mContext = context;
        this.mLisCateService = mLisCateService;
    }

    @NonNull
    @Override
    public CategoryServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.item_category_service, parent, false);
        return new CategoryServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryServiceViewHolder holder, int position) {
        holder.title.setText(mLisCateService.get(position).getmName());
        holder.horizontalAdapter.setData(mLisCateService.get(position).getmLisObjSer()); // List of Strings
       // holder.horizontalAdapter.setRowIndex(position);
    }

    @Override
    public int getItemCount() {
        return mLisCateService.size();
    }


    public static class CategoryServiceViewHolder extends RecyclerView.ViewHolder {
        public final TextView title;
        private AdapterObjShop horizontalAdapter;

        public CategoryServiceViewHolder(View view) {
            super(view);
            Context context = itemView.getContext();
            title = (TextView) view.findViewById(R.id.txt_title_objservice);
            horizontalList = (RecyclerView) itemView.findViewById(R.id.recycle_lis_objservice);
            horizontalList.setLayoutManager(new GridLayoutManager(context,2, GridLayoutManager.HORIZONTAL,
                    false));
            horizontalAdapter = new AdapterObjShop(mContext,onListenerItemClickObjService );
            horizontalList.setAdapter(horizontalAdapter);
        }
    }



}
