package neo.com.noibai_airport.View.Activity.MainActivity;

import java.util.List;

import neo.com.noibai_airport.Model.AirlineInfo;
import neo.com.noibai_airport.Model.Airport;

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
public interface MainInterface {
    interface Presenter{
        void getInit(String sUsertype, String sAppVersion, String sDeviceModel, String sDeviceType,
                     String sVersionHDH, String sTokenKey);
        void get_All_Airline(String sUserId);
        void get_All_Airport(String sUserId);
    }
    interface View{
        void show_init(List<String> mLisErorr);
        void show_all_airline(List<AirlineInfo> lisAirline);
        void show_all_airport(List<Airport> lisAirport);
        void show_api_error();
    }
}
