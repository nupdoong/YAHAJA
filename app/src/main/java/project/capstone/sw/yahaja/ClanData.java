package project.capstone.sw.yahaja;

import org.json.JSONException;
import org.json.JSONObject;

public class ClanData {

    String clan_name;
    String type;
    int points;

    ClanData(JSONObject rank_json) {
        try {
            this.clan_name = rank_json.getString("clan_name");
            this.type = rank_json.getString("type");
            this.points = rank_json.getInt("rank");
        } catch(JSONException e){
            System.out.println("JSON parse error. /clan");
        }
    }

    public String get_clan_name() { return clan_name; }
    public String get_type() {
        return type;
    }
    public int get_points() {
        return points;
    }
}
