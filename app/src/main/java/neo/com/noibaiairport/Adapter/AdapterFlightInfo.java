package neo.com.noibaiairport.Adapter;

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
import neo.com.noibaiairport.Model.FlightInfo;
import neo.com.noibaiairport.R;
import neo.com.noibaiairport.untils.TimeUtils;
import neo.com.noibaiairport.untils.setOnItemClickListener;

/**
 * Created by QQ on 7/7/2017.
 */

public class AdapterFlightInfo extends RecyclerView.Adapter<AdapterFlightInfo.FlightInfoViewHoder> {
    private List<FlightInfo> mLisFlightInfo;
    private Context context;
    private setOnItemClickListener OnIListener;

    public setOnItemClickListener getOnIListener() {
        return OnIListener;
    }

    public void setOnIListener(setOnItemClickListener onIListener) {
        OnIListener = onIListener;
    }

    public AdapterFlightInfo(List<FlightInfo> mLisFlightInfo, Context context) {
        this.mLisFlightInfo = mLisFlightInfo;
        this.context = context;
    }


    @NonNull
    @Override
    public FlightInfoViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_flight_info, parent, false);
        return new FlightInfoViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlightInfoViewHoder holder, int position) {
        FlightInfo objFlight = mLisFlightInfo.get(position);
        if (objFlight.getmTime_star() != null) {
            holder.txt_time_start.setText(objFlight.getmTime_star());
        }
        if (objFlight.getmTime_end() != null) {
            holder.txt_time_end.setText(objFlight.getmTime_end());
        }
        if (objFlight.getmAirportArrivals() != null) {
            holder.txt_airport_arrivals.setText(objFlight.getmAirportArrivals());
        }
        if (objFlight.getmAirportDepartures() != null) {
            holder.txt_ariport_departures.setText(objFlight.getmAirportDepartures());
        }
        if (objFlight.getmDay_start() != null) {
            holder.txt_day_start.setText(TimeUtils.convent_date(objFlight.getmDay_start(),
                    "yyyy-MM-dd hh:mm:ss", "EEE, dd/MM/yyyy"));
        }
        if (objFlight.getmDay_end() != null) {
            holder.txt_day_end.setText(objFlight.getmDay_end());
        }
        if (objFlight.getmFlight_Number() != null) {
            holder.txt_flight_id.setText(objFlight.getmFlight_Number());
        }
        if (objFlight.getM_Notification() != null) {
            holder.txt_notifycation.setText(objFlight.getM_Notification());
        }

    }


    @Override
    public int getItemCount() {
        return mLisFlightInfo.size();
    }

    public class FlightInfoViewHoder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        @BindView(R.id.txt_time_end)
        TextView txt_time_end;
        @BindView(R.id.txt_time_start)
        TextView txt_time_start;
        @BindView(R.id.txt_airport_arrivals)
        TextView txt_airport_arrivals;
        @BindView(R.id.txt_ariport_departures)
        TextView txt_ariport_departures;
        @BindView(R.id.txt_day_start)
        TextView txt_day_start;
        @BindView(R.id.txt_day_end)
        TextView txt_day_end;
        @BindView(R.id.txt_flight_id)
        TextView txt_flight_id;
        @BindView(R.id.txt_notifycation)
        TextView txt_notifycation;


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
