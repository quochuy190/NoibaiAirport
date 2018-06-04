package neo.com.noibaiairport.Adapter;

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
import neo.com.noibaiairport.Model.ShopsDine;
import neo.com.noibaiairport.R;
import neo.com.noibaiairport.untils.setOnItemClickListener;

/**
 * Created by QQ on 7/7/2017.
 */

public class AdapterShopsDine extends RecyclerView.Adapter<AdapterShopsDine.FlightInfoViewHoder> {
    private List<ShopsDine> mLisShopsDine;
    private Context context;
    private setOnItemClickListener OnIListener;

    public setOnItemClickListener getOnIListener() {
        return OnIListener;
    }

    public void setOnIListener(setOnItemClickListener onIListener) {
        OnIListener = onIListener;
    }

    public AdapterShopsDine(List<ShopsDine> mLisShopsDine, Context context) {
        this.mLisShopsDine = mLisShopsDine;
        this.context = context;
    }


    @NonNull
    @Override
    public FlightInfoViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_shopsdine, parent, false);
        return new FlightInfoViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlightInfoViewHoder holder, int position) {
        ShopsDine objShops = mLisShopsDine.get(position);
        if (objShops.getmImage() != null)
            Glide.with(context).load(objShops.getmImage()).into(holder.img_Background);
        if (objShops.getmName() != null)
            holder.txt_item_nem.setText(objShops.getmName());

    }


    @Override
    public int getItemCount() {
        return mLisShopsDine.size();
    }

    public class FlightInfoViewHoder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        @BindView(R.id.txt_item_shops_name)
        TextView txt_item_nem;
        @BindView(R.id.img_item_shops)
        ImageView img_Background;


        public FlightInfoViewHoder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            OnIListener.OnItemClickListener(getLayoutPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            OnIListener.OnLongItemClickListener(getLayoutPosition());
            return false;
        }
    }


    /*public void updateList(List<BaoCao_Nam> list) {
        lis_Baocao_nam = list;
        notifyDataSetChanged();
    }*/
}
