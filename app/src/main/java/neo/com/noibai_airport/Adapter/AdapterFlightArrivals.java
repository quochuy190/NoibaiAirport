package neo.com.noibai_airport.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import neo.com.noibai_airport.Model.FlightInfo;
import neo.com.noibai_airport.R;
import neo.com.noibai_airport.untils.ItemClickListener;
import neo.com.noibai_airport.untils.TimeUtils;
import neo.com.noibai_airport.untils.setOnItemClickListener;

/**
 * Created by QQ on 7/7/2017.
 */

public class AdapterFlightArrivals extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_ITEM = 0, VIEW_TYPE_LOADING = 1, VIEW_TYPE_HEADER = 2, VIEW_TYPE_ITEM_FOLLOW = 3;
    private List<FlightInfo> mLisFlightInfo;
    private List<FlightInfo> mLisFollow;
    private Context context;
    private setOnItemClickListener OnIListener;
    private ItemClickListener itemClickListener;

    public setOnItemClickListener getOnIListener() {
        return OnIListener;
    }

    public void setOnIListener(setOnItemClickListener onIListener) {
        OnIListener = onIListener;
    }

    public ItemClickListener getItemClickListener() {
        return itemClickListener;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public AdapterFlightArrivals(List<FlightInfo> mLisFlightInfo, List<FlightInfo> mLisFollow, Context context) {
        this.mLisFlightInfo = mLisFlightInfo;
        this.mLisFollow = mLisFollow;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        // So sánh nếu item được get tại vị trí này là null thì view đó là loading view , ngược lại là item
        if (mLisFollow.size() > 0) {
            if (position == 0 || (position == (mLisFollow.size() - 1))) {
                return VIEW_TYPE_HEADER;
            } else if (position > 0 && position < (mLisFollow.size() - 1)) {
                return VIEW_TYPE_ITEM_FOLLOW;
            } else {
                if (mLisFlightInfo.get(position-mLisFollow.size()) == null) {
                    return VIEW_TYPE_LOADING;
                } else {
                    return VIEW_TYPE_ITEM;
                }
            }
        } else {
            if (mLisFlightInfo.get(position) == null) {
                return VIEW_TYPE_LOADING;
            } else {
                return VIEW_TYPE_ITEM;
            }
        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(context)
                    .inflate(R.layout.item_flight_arrivals, parent, false);
            return new FlightInfoViewHoder(view);
        } else if (viewType == VIEW_TYPE_ITEM_FOLLOW) {
            View view = LayoutInflater.from(context)
                    .inflate(R.layout.item_flight_arrivals, parent, false);
            return new FlightInfoFollowViewHoder(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(context)
                    .inflate(R.layout.item_progressbar, parent, false);
            return new LoadingViewHolder(view);
        } else if (viewType == VIEW_TYPE_HEADER) {
            View view = LayoutInflater.from(context)
                    .inflate(R.layout.item_header, parent, false);
            return new HeaderViewHolder(view);
        }
        return null;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FlightInfoViewHoder) {
            FlightInfo objFlight = null;
            // int posi = position - (mLisFollow.size());
            if (mLisFollow.size() > 0) {
                objFlight = mLisFlightInfo.get(position - (mLisFollow.size()));
            } else {
                objFlight = mLisFlightInfo.get(position);
            }
            FlightInfoViewHoder viewHolder = (FlightInfoViewHoder) holder;
            //check là chuyến bay đi
            Glide.with(context)
                    .load(objFlight.getmAVATAR())
                    .error(R.drawable.icon_may_bay)
                    .into(((FlightInfoViewHoder) holder).img_avata_flight);
            if (objFlight.getmTime_arrival() != null && objFlight.getmTime_arrival().length() > 0) {
                viewHolder.txt_time_start_flight.setText(objFlight.getmTime_arrival());
            } else {
                viewHolder.txt_time_start_flight.setText("-");
            }
            // set ngày đi
            if (objFlight.getmDay_start() != null && objFlight.getmDay_start().length() > 0) {
                viewHolder.txt_date_start_flight.setText(TimeUtils.convent_date(objFlight.getmDay_start(),
                        "yyyy-MM-dd hh:mm:ss", "EEE, dd/MM/yyyy"));
            }
            // set ngày đến h đến dự kiến
            if (objFlight.getmTime_arrival_Estimation() != null && objFlight.getmTime_arrival_Estimation().length() > 0) {
                viewHolder.txt_time_estimate.setText(objFlight.getmTime_arrival_Estimation());
            } else {
                viewHolder.txt_time_estimate.setText("-");
            }

            if (objFlight.getmNAME() != null) {
                viewHolder.txt_airport_flight.setText(objFlight.getmNAME());
            }
            if (objFlight.getmTERMINAL() != null) {
                viewHolder.txt_teminal_flight.setText(objFlight.getmTERMINAL());
            }

            if (objFlight.getM_Notification() != null) {
                viewHolder.txt_flight_checkin.setText(objFlight.getM_Notification());
            }
            if (objFlight.getmFlight_Number() != null) {
                viewHolder.txt_flight_number.setText(objFlight.getmFlight_Number());
            }
        } else if (holder instanceof FlightInfoFollowViewHoder) {
            FlightInfo objFlight = mLisFollow.get(position);
            FlightInfoFollowViewHoder viewHolder = (FlightInfoFollowViewHoder) holder;
            //check là chuyến bay đi
            Glide.with(context)
                    .load(objFlight.getmAVATAR())
                    .error(R.drawable.icon_may_bay)
                    .into(((FlightInfoFollowViewHoder) holder).img_avata_flight);
            if (objFlight.getmTime_arrival() != null && objFlight.getmTime_arrival().length() > 0) {
                viewHolder.txt_time_start_flight.setText(objFlight.getmTime_arrival());
            } else {
                viewHolder.txt_time_start_flight.setText("-");
            }
            // set ngày đi
            if (objFlight.getmDay_start() != null && objFlight.getmDay_start().length() > 0) {
                viewHolder.txt_date_start_flight.setText(TimeUtils.convent_date(objFlight.getmDay_start(),
                        "yyyy-MM-dd hh:mm:ss", "EEE, dd/MM/yyyy"));
            }
            // set ngày đến h đến dự kiến
            if (objFlight.getmTime_departure_Estimation() != null && objFlight.getmTime_departure_Estimation().length() > 0) {
                viewHolder.txt_time_estimate.setText(objFlight.getmTime_departure_Estimation());
            } else {
                viewHolder.txt_time_estimate.setText("-");
            }

            if (objFlight.getmNAME() != null) {
                viewHolder.txt_airport_flight.setText(objFlight.getmNAME());
            }
            if (objFlight.getmTERMINAL() != null) {
                viewHolder.txt_teminal_flight.setText(objFlight.getmTERMINAL());
            }

            if (objFlight.getM_Notification() != null) {
                viewHolder.txt_flight_checkin.setText(objFlight.getM_Notification());
            }
            if (objFlight.getmFlight_Number() != null) {
                viewHolder.txt_flight_number.setText(objFlight.getmFlight_Number());
            }
        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        } else if (holder instanceof HeaderViewHolder) {
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
            headerViewHolder.txt_Header.setText(mLisFollow.get(position).getsHeader());
        }
    }

    @Override
    public int getItemCount() {
        if (mLisFollow != null && mLisFollow.size() > 0) {
            return (mLisFlightInfo.size()+mLisFollow.size());
        } else
            return mLisFlightInfo.size();
    }

    public class FlightInfoViewHoder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {
        @BindView(R.id.txt_day_flightariivals)
        TextView txt_date_start_flight;
        @BindView(R.id.txt_time_start_flight)
        TextView txt_time_start_flight;
        @BindView(R.id.txt_time_estimate)
        TextView txt_time_estimate;
        @BindView(R.id.txt_flight_number)
        TextView txt_flight_number;
        @BindView(R.id.txt_airport_flight)
        TextView txt_airport_flight;
        @BindView(R.id.txt_teminal_flight)
        TextView txt_teminal_flight;
        @BindView(R.id.txt_flight_checkin)
        TextView txt_flight_checkin;
        @BindView(R.id.img_avata_flight)
        ImageView img_avata_flight;

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

    public void updateList(List<FlightInfo> list) {
        mLisFlightInfo = list;
        notifyDataSetChanged();
    }

    // Item Follow
    public class FlightInfoFollowViewHoder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {
        @BindView(R.id.txt_day_flightariivals)
        TextView txt_date_start_flight;
        @BindView(R.id.txt_time_start_flight)
        TextView txt_time_start_flight;
        @BindView(R.id.txt_time_estimate)
        TextView txt_time_estimate;
        @BindView(R.id.txt_flight_number)
        TextView txt_flight_number;
        @BindView(R.id.txt_airport_flight)
        TextView txt_airport_flight;
        @BindView(R.id.txt_teminal_flight)
        TextView txt_teminal_flight;
        @BindView(R.id.txt_flight_checkin)
        TextView txt_flight_checkin;
        @BindView(R.id.img_avata_flight)
        ImageView img_avata_flight;

        public FlightInfoFollowViewHoder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            // OnIListener.OnItemClickListener(getLayoutPosition());
            itemClickListener.onClickItem(getAdapterPosition(), mLisFollow.get(getLayoutPosition()));
        }

        @Override
        public boolean onLongClick(View v) {
            //  OnIListener.OnLongItemClickListener(getLayoutPosition());
            return false;
        }
    }

}
