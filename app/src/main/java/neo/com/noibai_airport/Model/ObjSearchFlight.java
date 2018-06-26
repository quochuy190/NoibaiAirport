package neo.com.noibai_airport.Model;

import java.io.Serializable;

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
public class ObjSearchFlight implements Serializable {
    private String sFlightNumber;
    private String sFlightAirport;
    private String sFlightDatetime;
    private String sFlightAirline;
    private String sFlightType;


    public ObjSearchFlight(String sFlightNumber, String sFlightAirport, String sFlightDatetime, String sFlightAirline) {
        this.sFlightNumber = sFlightNumber;
        this.sFlightAirport = sFlightAirport;
        this.sFlightDatetime = sFlightDatetime;
        this.sFlightAirline = sFlightAirline;
    }

    public ObjSearchFlight() {
    }

    public String getsFlightType() {
        return sFlightType;
    }

    public void setsFlightType(String sFlightType) {
        this.sFlightType = sFlightType;
    }

    public String getsFlightNumber() {
        return sFlightNumber;
    }

    public void setsFlightNumber(String sFlightNumber) {
        this.sFlightNumber = sFlightNumber;
    }

    public String getsFlightAirport() {
        return sFlightAirport;
    }

    public void setsFlightAirport(String sFlightAirport) {
        this.sFlightAirport = sFlightAirport;
    }

    public String getsFlightDatetime() {
        return sFlightDatetime;
    }

    public void setsFlightDatetime(String sFlightDatetime) {
        this.sFlightDatetime = sFlightDatetime;
    }

    public String getsFlightAirline() {
        return sFlightAirline;
    }

    public void setsFlightAirline(String sFlightAirline) {
        this.sFlightAirline = sFlightAirline;
    }
}

