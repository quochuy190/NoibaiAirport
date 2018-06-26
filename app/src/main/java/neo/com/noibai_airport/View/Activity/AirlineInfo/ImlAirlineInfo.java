package neo.com.noibai_airport.View.Activity.AirlineInfo;

import java.util.List;

import neo.com.noibai_airport.Model.AirlineInfo;
import neo.com.noibai_airport.Model.Airport;
import neo.com.noibai_airport.Model.ErrorApi;

/**
 * @author Quốc Huy
 * @version 1.0.0
 * @description
 * @desc Developer NEO Company.
 * @created 6/20/2018
 * @updated 6/20/2018
 * @modified by
 * @updated on 6/20/2018
 * @since 1.0
 */
public interface ImlAirlineInfo {
    interface Presenter{
        void get_list_airline(String sUserId);
        void get_airline_detail(String sUserId, String sIdFlight);
        void get_all_airport(String sUserId);
    }
    interface View{
        void show_list_airline(List<AirlineInfo> lisAirline);
        void show_list_airport(List<Airport> lisAirport);
        void show_airline_detail(List<Airport> lisAirport);
        void show_api_error(List<ErrorApi> lisError);
    }
}
