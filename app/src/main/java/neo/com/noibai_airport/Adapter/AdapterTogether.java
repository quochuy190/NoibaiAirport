package neo.com.noibai_airport.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import neo.com.noibai_airport.Model.Together;
import neo.com.noibai_airport.R;
import neo.com.noibai_airport.untils.ItemTogetherClickListener;
import neo.com.noibai_airport.untils.TimeUtils;

public class AdapterTogether extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_ITEM = 0, VIEW_TYPE_LOADING = 1, VIEW_TYPE_HEADER = 2;
    private Context mContext;
    private List<Together> lisLanguage;
    private ItemTogetherClickListener itemClickListener;

    public AdapterTogether(Context mContext, List<Together> lisLanguage, ItemTogetherClickListener itemClickListener) {
        this.mContext = mContext;
        this.lisLanguage = lisLanguage;
        this.itemClickListener = itemClickListener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(mContext)
                    .inflate(R.layout.item_together, parent, false);
            return new LanguageViewHoder(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(mContext)
                    .inflate(R.layout.item_progressbar, parent, false);
            return new LoadingViewHolder(view);
        } else if (viewType == VIEW_TYPE_HEADER) {
            View view = LayoutInflater.from(mContext)
                    .inflate(R.layout.item_header, parent, false);
            return new HeaderViewHolder(view);
        }
        return null;
    }


    @Override
    public int getItemViewType(int position) {
        // So sánh nếu item được get tại vị trí này là null thì view đó là loading view , ngược lại là item
        if (lisLanguage.get(position) == null) {
            return VIEW_TYPE_LOADING;
        } else if (lisLanguage.get(position).isTitle()) {
            return VIEW_TYPE_HEADER;
        } else {
            return VIEW_TYPE_ITEM;
        }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHoder, final int position) {
        if (viewHoder instanceof LanguageViewHoder) {
            final Together language = lisLanguage.get(position);
            LanguageViewHoder holder = (LanguageViewHoder) viewHoder;
            if (language.isMyOrder()){
                holder.img_more.setVisibility(View.VISIBLE);
            }else
                holder.img_more.setVisibility(View.GONE);
            if (language.getmName() != null) {
                holder.txt_name.setText(language.getmName());
            }
            if (language.getsTime() != null) {
                if (!language.isMyOrder())
                holder.txt_time.setText(TimeUtils.convent_date(language.getsTime(),
                        "yyyy-MM-dd hh:mm:ss", "dd/MM/yyyy HH:mm:ss"));
                else holder.txt_time.setText(language.getsTime());
            }
            if (language.getsPhone() != null) {
                String htmlString = "<u><b>" + language.getsPhone() + "</b></u>";
                // mTextView.setText(Html.fromHtml(htmlString));
                holder.txt_phone.setText(Html.fromHtml(htmlString));
            }

            if (language.getsGender() != null) {
                switch (language.getsGender()) {
                    case "0":
                        holder.txt_gender.setText(mContext.getString(R.string.item_together_gender)
                                + " " + mContext.getString(R.string.sex_all));
                        break;
                    case "1":
                        holder.txt_gender.setText(mContext.getString(R.string.item_together_gender)
                                + " " + mContext.getString(R.string.sex_male));
                        break;
                    case "2":
                        holder.txt_gender.setText(mContext.getString(R.string.item_together_gender)
                                + " " + mContext.getString(R.string.sex_female));
                        break;
                }

            }
            if (language.getsTerminal() != null) {
                holder.txt_quantity.setText(mContext.getString(R.string.item_together_quantity) + " "
                        + language.getsQuantity());
            }
            if (language.getsDistrict() != null && language.getsWard() != null) {
                holder.txt_district.setText(mContext.getString(R.string.destination)+": "+language.getsPRECINCT_NAME() + ", "
                        + language.getsDISTRICT_NAME());
            }
            if (language.getsDESCRIPTION() != null) {
                holder.txt_description.setText(language.getsDESCRIPTION());
            }
            holder.ll_phone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onClickPhoneItem(position, language);
                }
            });
            if (language.getsPRICE_ESTIMATE()!=null)
                holder.txt_price.setText(language.getsPRICE_ESTIMATE()+" VNĐ");
        } else if (viewHoder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) viewHoder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        } else if (viewHoder instanceof HeaderViewHolder) {
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) viewHoder;
            headerViewHolder.txt_Header.setText(lisLanguage.get(position).getsTitle());
        }
    }



    @Override
    public int getItemCount() {
        return lisLanguage.size();
    }

    public class LanguageViewHoder extends RecyclerView.ViewHolder implements
            View.OnClickListener, View.OnLongClickListener {
        @BindView(R.id.txt_item_together_name)
        TextView txt_name;
        @BindView(R.id.txt_item_price_phone)
        TextView txt_price;
        @BindView(R.id.txt_item_together_time)
        TextView txt_time;
        @BindView(R.id.txt_item_together_phone)
        TextView txt_phone;
        @BindView(R.id.txt_item_together_quantity)
        TextView txt_quantity;
        @BindView(R.id.txt_item_together_gender)
        TextView txt_gender;
        @BindView(R.id.txt_item_together_district)
        TextView txt_district;
        @BindView(R.id.txt_item_together_description)
        TextView txt_description;
        @BindView(R.id.linear_phone)
        LinearLayout ll_phone;
        @BindView(R.id.img_more)
        ImageView img_more;

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

    public class LoadingViewHolder extends RecyclerView.ViewHolder {

        public ProgressBar progressBar;

        public LoadingViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
        }
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {

        public TextView txt_Header;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            txt_Header = (TextView) itemView.findViewById(R.id.txt_flight_header);
        }
    }

    public void update_list(List<Together> list) {
        lisLanguage.clear();
        lisLanguage.addAll(list);
        notifyDataSetChanged();
    }
}
