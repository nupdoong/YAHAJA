package project.capstone.sw.yahaja;

import org.json.JSONException;
import org.json.JSONObject;

public class ClanData {

    String clan_name;
    String clan_master;
    String type;
    int points;

    ClanData(JSONObject rank_json) {
        try {
            this.clan_name = rank_json.getString("clan_name");
            this.clan_master = rank_json.getString("clan_master");
            this.type = rank_json.getString("type");
            this.points = rank_json.getInt("points");
        } catch(JSONException e){
            System.out.println("JSON parse error. /clans");
        }
    }

    public String get_clan_name() { return clan_name; }
    public String get_clan_master() { return clan_master; }
    public String get_type() {
        return type;
    }
    public int get_points() {
        return points;
    }
}
