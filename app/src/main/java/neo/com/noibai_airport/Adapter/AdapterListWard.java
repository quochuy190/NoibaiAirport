package neo.com.noibai_airport.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import neo.com.noibai_airport.Model.Ward;
import neo.com.noibai_airport.R;
import neo.com.noibai_airport.untils.setOnItemClickListener;

/**
 * Created by QQ on 7/7/2017.
 */

public class AdapterListWard extends RecyclerView.Adapter<AdapterListWard.TopicViewHoder>{
    private List<Ward> listAirport;
    private Context context;
    private setOnItemClickListener OnIListener;


    public setOnItemClickListener getOnIListener() {
        return OnIListener;
    }

    public void setOnIListener(setOnItemClickListener onIListener) {
        OnIListener = onIListener;
    }

    public AdapterListWard(List<Ward> listAirport, Context context) {
        this.listAirport = listAirport;
        this.context = context;
    }

    @Override
    public TopicViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_listview,parent,false);
        return new TopicViewHoder(view);
    }

    @Override
    public void onBindViewHolder(TopicViewHoder holder, int position) {

        Ward airport = listAirport.get(position);
        holder.txt_name.setText(airport.getsCOMMUNE_NAME());

    }

    @Override
    public int getItemCount() {
        return listAirport.size();
    }

    public class TopicViewHoder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        @BindView(R.id.txt_name)
        TextView txt_name;




        public TopicViewHoder(View itemView) {
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

    public void updateList(List<Ward> list){
        listAirport = list;
        notifyDataSetChanged();
    }
}
