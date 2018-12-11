package project.capstone.sw.yahaja;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by JINO on 2018-12-11.
 */

public class FacilityData {
    String contact;
    String latitude;
    String longitude;

    FacilityData(JSONObject facility_json) {
        try {
            this.contact = facility_json.getString("contact");
            this.latitude = facility_json.getString("lat");
            this.longitude = facility_json.getString("lon");
        } catch(JSONException e){
            System.out.println("JSON parse error. /facility");
        }
    }

    public String getContact() {
        return contact;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

}
