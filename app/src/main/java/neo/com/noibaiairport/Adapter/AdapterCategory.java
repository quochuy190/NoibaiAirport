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
import neo.com.noibaiairport.Model.Category;
import neo.com.noibaiairport.Model.Language;
import neo.com.noibaiairport.R;
import neo.com.noibaiairport.untils.ItemClickListener;

public class AdapterCategory extends RecyclerView.Adapter<AdapterCategory.LanguageViewHoder> {
    private Context mContext;
    private List<Category> lisLanguage;
    private ItemClickListener itemClickListener;

    public AdapterCategory(Context mContext, List<Category> lisLanguage) {
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
                .inflate(R.layout.item_category_left, parent, false);
        return new LanguageViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LanguageViewHoder holder, int position) {
        Category language = lisLanguage.get(position);
        Glide.with(mContext).load(language.getmImage()).into(holder.img_category_left);
        holder.txt_name_category.setText(language.getmName());

    }

    @Override
    public int getItemCount() {
        return lisLanguage.size();
    }

    public class LanguageViewHoder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        @BindView(R.id.img_category_left)
        ImageView img_category_left;
        @BindView(R.id.txt_name_category_left)
        TextView txt_name_category;

        public LanguageViewHoder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClickItem(getLayoutPosition(),lisLanguage.get(getLayoutPosition()));
        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }

    public void update_list(List<Language> list) {
       /* lisLanguage.clear();
        lisLanguage.addAll(list);
        notifyDataSetChanged();*/
    }
}
