package project.capstone.sw.yahaja;

import org.json.JSONException;
import org.json.JSONObject;

public class LocationData {
    String firstname;
    double location_longitude;
    int i;
    double location_latitude;

    LocationData(JSONObject rank_json) {
        try {
            this.firstname = rank_json.getString("firstname");
            this.location_longitude = rank_json.getDouble("location_longitude");
            this.location_latitude = rank_json.getDouble("location_latitude");
        } catch(JSONException e){
            System.out.println("JSON parse error. /location");
        }
    }

}