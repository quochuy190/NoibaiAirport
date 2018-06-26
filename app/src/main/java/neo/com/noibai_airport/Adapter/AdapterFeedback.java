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
import neo.com.noibai_airport.Model.Feedback;
import neo.com.noibai_airport.R;
import neo.com.noibai_airport.untils.setOnItemClickListener;

/**
 * Created by QQ on 7/7/2017.
 */

public class AdapterFeedback extends RecyclerView.Adapter<AdapterFeedback.FlightInfoViewHoder> {
    private List<Feedback> mLisFeedback;
    private Context context;
    private setOnItemClickListener OnIListener;

    public setOnItemClickListener getOnIListener() {
        return OnIListener;
    }

    public void setOnIListener(setOnItemClickListener onIListener) {
        OnIListener = onIListener;
    }

    public AdapterFeedback(List<Feedback> mLisFeedback, Context context) {
        this.mLisFeedback = mLisFeedback;
        this.context = context;
    }


    @NonNull
    @Override
    public FlightInfoViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_feedback, parent, false);
        return new FlightInfoViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlightInfoViewHoder holder, int position) {
        Feedback objFeedback = mLisFeedback.get(position);
        if (objFeedback.getsNICKNAME() != null) {
            holder.txt_name_sent_fb.setText(objFeedback.getsNICKNAME());
        }
        if (objFeedback.getsCOMMENTS() != null) {
            holder.txt_question_fb.setText(objFeedback.getsCOMMENTS());
        }
        if (objFeedback.getsCOMMENTTIME()!=null){
            holder.txt_time_sent_fb.setText(objFeedback.getsCOMMENTTIME());
        }
    }


    @Override
    public int getItemCount() {
        return mLisFeedback.size();
    }

    public class FlightInfoViewHoder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        @BindView(R.id.txt_name_sent_fb)
        TextView txt_name_sent_fb;
        @BindView(R.id.txt_question_fb)
        TextView txt_question_fb;
@BindView(R.id.txt_time_sent_fb)
TextView txt_time_sent_fb;

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
