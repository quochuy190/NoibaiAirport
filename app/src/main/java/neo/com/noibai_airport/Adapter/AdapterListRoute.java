package neo.com.noibai_airport.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import neo.com.noibai_airport.Model.Airport;
import neo.com.noibai_airport.R;
import neo.com.noibai_airport.untils.ItemClickListener;

public class AdapterListRoute extends RecyclerView.Adapter<AdapterListRoute.LanguageViewHoder> {
    private Context mContext;
    private List<Airport> lisLanguage;
    private ItemClickListener itemClickListener;

    public AdapterListRoute(Context mContext, List<Airport> lisLanguage) {
        this.mContext = mContext;
        this.lisLanguage = lisLanguage;
    }

    public ItemClickListener getItemClickListener() {
        return itemClickListener;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public LanguageViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_route, parent, false);
        return new LanguageViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LanguageViewHoder holder, int position) {
        Airport language = lisLanguage.get(position);
        holder.txt_name_route.setText(language.getsName());

    }

    @Override
    public int getItemCount() {
        return lisLanguage.size();
    }

    public class LanguageViewHoder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        @BindView(R.id.txt_name_route)
        TextView txt_name_route;


        public LanguageViewHoder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClickItem(getLayoutPosition(), lisLanguage.get(getLayoutPosition()));
        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }

    public void update_list(List<Airport> list) {
        lisLanguage.clear();
        lisLanguage.addAll(list);
        notifyDataSetChanged();
    }
}
