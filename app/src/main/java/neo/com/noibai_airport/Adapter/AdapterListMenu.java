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
import neo.com.noibai_airport.Model.ObjMenu;
import neo.com.noibai_airport.R;
import neo.com.noibai_airport.untils.ItemClickListener;

public class AdapterListMenu extends RecyclerView.Adapter<AdapterListMenu.LanguageViewHoder> {
    private Context mContext;
    private List<ObjMenu> lisAirline;
    private ItemClickListener itemClickListener;

    public AdapterListMenu(Context mContext, List<ObjMenu> lisAirline) {
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
                .inflate(R.layout.item_menu_shop, parent, false);
        return new LanguageViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LanguageViewHoder holder, int position) {
        ObjMenu obj = lisAirline.get(position);
        Glide.with(mContext).load(obj.getsPRODUCTAVARTAR()).into(holder.img_flag);
        if (obj.getsPRODUCTNAME()!=null){
            holder.txt_name_menu.setText(obj.getsPRODUCTNAME());
        }
        if (obj.getsPRICE()!=null){
            holder.txt_price_menu.setText("Giá: "+obj.getsPRICE());
        }
        if (obj.getsUNIT()!=null){
            holder.txt_unit_menu.setText("Đơn vị: "+ obj.getsUNIT());
        }
    }

    @Override
    public int getItemCount() {
        return lisAirline.size();
    }

    public class LanguageViewHoder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        @BindView(R.id.txt_name_menu)
        TextView txt_name_menu;
        @BindView(R.id.txt_price_menu)
        TextView txt_price_menu;
        @BindView(R.id.txt_unit_menu)
        TextView txt_unit_menu;
        @BindView(R.id.img_flag)
        ImageView img_flag;

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
