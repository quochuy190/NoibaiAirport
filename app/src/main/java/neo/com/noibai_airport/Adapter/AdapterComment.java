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
import de.hdodenhof.circleimageview.CircleImageView;
import neo.com.noibai_airport.Model.Comments;
import neo.com.noibai_airport.R;
import neo.com.noibai_airport.untils.ItemClickListener;
import neo.com.noibai_airport.untils.TimeUtils;

public class AdapterComment extends RecyclerView.Adapter<AdapterComment.LanguageViewHoder> {
    private Context mContext;
    private List<Comments> lisLanguage;
    private ItemClickListener itemClickListener;

    public AdapterComment(Context mContext, List<Comments> lisLanguage) {
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
                .inflate(R.layout.item_comment, parent, false);
        return new LanguageViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LanguageViewHoder holder, int position) {
        Comments language = lisLanguage.get(position);
        if (language.getsCOMMENTS()!=null){
            holder.txt_content_comment.setText(language.getsCOMMENTS());
        }
        if (language.getsCOMMENTTIME()!=null){
            holder.txt_time_comment.setText(TimeUtils.convent_date(language.getsCOMMENTTIME(),
                    "yyyy-MM-dd hh:mm:ss", "EEE, dd/MM/yyyy"));
        }
        if (language.getsMEMBERID()!=null){
            holder.txt_UserName.setText(language.getsMEMBERID());
        }

    }

    @Override
    public int getItemCount() {
        return lisLanguage.size();
    }

    public class LanguageViewHoder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        @BindView(R.id.txt_content_comment)
        TextView txt_content_comment;
        @BindView(R.id.txt_time_comment)
        TextView txt_time_comment;
        @BindView(R.id.txt_UserName)
        TextView txt_UserName;
        @BindView(R.id.img_avata_comment)
        CircleImageView img_avata_comment;

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

    public void update_list(List<Comments> list) {
        lisLanguage.clear();
        lisLanguage.addAll(list);
        notifyDataSetChanged();
    }
}
