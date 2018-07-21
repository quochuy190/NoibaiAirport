package neo.com.noibai_airport.View.Fragment.FlightFragment;

import java.util.List;

import neo.com.noibai_airport.Model.ErrorApi;
import neo.com.noibai_airport.Model.FlightInfo;

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
                             String sDatetime, String sAirline, String sLoad, String sPage, String sIndex, String sTerminal);
        void get_list_flight_earlier(String sUserId, String sFlightNo, String sLocation, String sDA_type,
                             String sDatetime, String sAirline, String sLoad, String sPage, String sIndex, String sTerminal);
        void get_detail_flight(String sUserId, String sDAtype, String sDatetime, String sFlightNo);
        void set_follow_flight(String sUserId, String sDAtype, String sDatetime, String sStatus, String sFlightNo);
    }
    interface View{
        void show_list_filghtinfo(List<FlightInfo> lisFlight);
        void show_list_filghtinfo_earlier(List<FlightInfo> lisFlight);
        void show_detail_flight(List<FlightInfo> lisFlight);
        void show_result_flightfollow(List<ErrorApi> lisError);
        void show_result_flightfollow_error(List<ErrorApi> lisError);
        void show_get_api_error();
    }
}
