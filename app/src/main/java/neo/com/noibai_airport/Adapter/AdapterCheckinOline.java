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
import neo.com.noibai_airport.Model.AirlineInfo;
import neo.com.noibai_airport.Model.Language;
import neo.com.noibai_airport.R;
import neo.com.noibai_airport.untils.ItemClickListener;

public class AdapterCheckinOline extends RecyclerView.Adapter<AdapterCheckinOline.LanguageViewHoder> {
    private Context mContext;
    private List<AirlineInfo> lisAirline;
    private ItemClickListener itemClickListener;

    public AdapterCheckinOline(Context mContext, List<AirlineInfo> lisAirline) {
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
                .inflate(R.layout.item_airline_checkin, parent, false);
        return new LanguageViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LanguageViewHoder holder, int position) {
        AirlineInfo obj = lisAirline.get(position);
        holder.txt_name_language.setText(obj.getsName());
        holder.txt_phone_airline.setText(obj.getsPhone());
        holder.txt_address_airline.setText(obj.getsAddress());
        if (obj.getsImage().equals("vi_al"))
            Glide.with(mContext).load(R.drawable.ic_vietnamairline).into(holder.img_avata_airline);
        else  if (obj.getsImage().equals("jet"))
            Glide.with(mContext).load(R.drawable.ic_jetstar).into(holder.img_avata_airline);
        else  if (obj.getsImage().equals("vi_jet"))
            Glide.with(mContext).load(R.drawable.ic_vietjetair).into(holder.img_avata_airline);
    }

    @Override
    public int getItemCount() {
        return lisAirline.size();
    }

    public class LanguageViewHoder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        @BindView(R.id.txt_name_airline)
        TextView txt_name_language;
        @BindView(R.id.txt_address_airline)
        TextView txt_address_airline;
        @BindView(R.id.img_avata_airline)
        ImageView img_avata_airline;
        @BindView(R.id.txt_phone_airline)
        TextView txt_phone_airline;

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
