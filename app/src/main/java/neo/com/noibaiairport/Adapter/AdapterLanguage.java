package neo.com.noibaiairport.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import neo.com.noibaiairport.Model.Language;
import neo.com.noibaiairport.R;
import neo.com.noibaiairport.untils.ItemClickListener;

public class AdapterLanguage extends RecyclerView.Adapter<AdapterLanguage.LanguageViewHoder> {
    private Context mContext;
    private List<Language> lisLanguage;
    private ItemClickListener itemClickListener;

    public AdapterLanguage(Context mContext, List<Language> lisLanguage) {
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
                .inflate(R.layout.item_language, parent, false);
        return new LanguageViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LanguageViewHoder holder, int position) {
        Language language = lisLanguage.get(position);
        holder.txt_name_language.setText(language.getName());

        holder.cb_language.setChecked(language.isChecked());

    }

    @Override
    public int getItemCount() {
        return lisLanguage.size();
    }

    public class LanguageViewHoder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        @BindView(R.id.txt_name_language)
        TextView txt_name_language;
        @BindView(R.id.cb_language)
        CheckBox cb_language;

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

    public void update_list(List<Language> list) {
        lisLanguage.clear();
        lisLanguage.addAll(list);
        notifyDataSetChanged();
    }
}
