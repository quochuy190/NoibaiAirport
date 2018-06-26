package neo.com.noibai_airport.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import neo.com.noibai_airport.Model.Language;
import neo.com.noibai_airport.Model.Product;
import neo.com.noibai_airport.R;
import neo.com.noibai_airport.untils.ItemClickListener;

public class AdapterListService extends RecyclerView.Adapter<AdapterListService.LanguageViewHoder> {
    private Context mContext;
    private List<Product> lisAirline;
    private ItemClickListener itemClickListener;

    public AdapterListService(Context mContext, List<Product> lisAirline) {
        this.mContext = mContext;
        this.lisAirline = lisAirline;
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
                .inflate(R.layout.item_service, parent, false);
        return new LanguageViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LanguageViewHoder holder, int position) {
        Product obj = lisAirline.get(position);
        holder.txt_name_service.setText(obj.getsPRODUCTNAME());
        holder.txt_price_service.setText(mContext.getString(R.string.price) + " " + obj.getsPRICE());
        if (obj.getsPRODUCTAVATAR() != null)
            Glide.with(mContext).load(obj.getsPRODUCTAVATAR()).into(holder.img_avata_service);
    }

    @Override
    public int getItemCount() {
        return lisAirline.size();
    }

    public class LanguageViewHoder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        @BindView(R.id.img_avata_service)
        ImageView img_avata_service;
        @BindView(R.id.txt_name_service)
        TextView txt_name_service;
        @BindView(R.id.txt_price_service)
        TextView txt_price_service;


        public LanguageViewHoder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClickItem(getLayoutPosition(), lisAirline.get(getLayoutPosition()));
        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }

    public void update_list(List<Language> list) {

    }
}
