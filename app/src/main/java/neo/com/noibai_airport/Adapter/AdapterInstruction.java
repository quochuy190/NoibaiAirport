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
import neo.com.noibai_airport.Model.ObjWebview;
import neo.com.noibai_airport.R;
import neo.com.noibai_airport.untils.LanguageUtils;
import neo.com.noibai_airport.untils.setOnItemClickListener;

/**
 * Created by QQ on 7/7/2017.
 */

public class AdapterInstruction extends RecyclerView.Adapter<AdapterInstruction.FlightInfoViewHoder> {
    private List<ObjWebview> mLisString;
    private Context context;
    private setOnItemClickListener OnIListener;

    public setOnItemClickListener getOnIListener() {
        return OnIListener;
    }

    public void setOnIListener(setOnItemClickListener onIListener) {
        OnIListener = onIListener;
    }

    public AdapterInstruction(List<ObjWebview> mLisFeedback, Context context) {
        this.mLisString = mLisFeedback;
        this.context = context;
    }


    @NonNull
    @Override
    public FlightInfoViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_instruction, parent, false);
        return new FlightInfoViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlightInfoViewHoder holder, int position) {
        if (LanguageUtils.getCurrentLanguage().getCode().equals("en")) {
            holder.txt_name_instruction.setText(mLisString.get(position).getsId());
        } else
            holder.txt_name_instruction.setText(mLisString.get(position).getsName());
        Glide.with(context).load(mLisString.get(position).getsImage()).into(holder.img_icon_instruction);
    }


    @Override
    public int getItemCount() {
        return mLisString.size();
    }

    public class FlightInfoViewHoder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        @BindView(R.id.txt_name_instruction)
        TextView txt_name_instruction;
        @BindView(R.id.img_icon_instruction)
        ImageView img_icon_instruction;

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
}
