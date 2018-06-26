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
import neo.com.noibai_airport.Model.ObjService;
import neo.com.noibai_airport.R;
import neo.com.noibai_airport.untils.OnListenerItemClickObjService;
import neo.com.noibai_airport.untils.setOnItemClickListener;

/**
 * Created by QQ on 7/7/2017.
 */

public class AdapterObjService extends RecyclerView.Adapter<AdapterObjService.FlightInfoViewHoder> {
    private List<ObjService> mLisObjService;
    private Context context;
    private setOnItemClickListener OnIListener;
    private OnListenerItemClickObjService onListenerItemClickObjService;

    public setOnItemClickListener getOnIListener() {
        return OnIListener;
    }

    public void setOnIListener(setOnItemClickListener onIListener) {
        OnIListener = onIListener;
    }

    public AdapterObjService( Context context, OnListenerItemClickObjService onListenerItemClickObjService) {
        this.context = context;
        this.onListenerItemClickObjService =  onListenerItemClickObjService;
    }


    @NonNull
    @Override
    public FlightInfoViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_objservice, parent, false);
        return new FlightInfoViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlightInfoViewHoder holder, final int position) {
        ObjService objService = mLisObjService.get(position);
        Glide.with(context).load(objService.getmImage()).into(holder.img_item_objservice);
        holder.txt_item_objservice_name.setText(objService.getmName());
        holder.img_item_objservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onListenerItemClickObjService.onClickListener(mLisObjService.get(position));
            }
        });
    }


    @Override
    public int getItemCount() {
        return mLisObjService.size();
    }

    public class FlightInfoViewHoder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        @BindView(R.id.txt_item_objservice_name)
        TextView txt_item_objservice_name;
        @BindView(R.id.img_item_objservice)
        ImageView img_item_objservice;


        public FlightInfoViewHoder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
          //  itemView.setOnClickListener(this);

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

    public void setData(List<ObjService> data) {
        if (mLisObjService != data) {
            mLisObjService = data;
            notifyDataSetChanged();
        }
    }
    /*public void updateList(List<BaoCao_Nam> list) {
        lis_Baocao_nam = list;
        notifyDataSetChanged();
    }*/
}
