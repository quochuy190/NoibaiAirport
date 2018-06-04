package neo.com.noibaiairport.View.Fragment.FlightFragment;

import java.util.List;

import neo.com.noibaiairport.Model.FlightInfo;

/**
 * @author Quốc Huy
 * @version 1.0.0
 * @description
 * @desc Developer NEO Company.
 * @created ${Date}
 * @updated ${Date}
 * @modified by
 * @updated on ${Date}
 * @since 1.0
 */
public interface ImlFlight {
    interface Presenter{
        void get_list_flight(String sUserId, String sFlightNo, String sLocation, String sDA_type,
                             String sDatetime, String sAirline, String sLoad, String sPage, String sIndex);
        void get_detail_flight(String sUserId, String sDAtype, String sDatetime, String sFlightNo);
    }
    interface View{
        void show_list_filghtinfo(List<FlightInfo> lisFlight);
        void show_detail_flight(List<FlightInfo> lisFlight);
    }
}
