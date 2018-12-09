package project.capstone.sw.yahaja;

import org.json.JSONException;
import org.json.JSONObject;

public class ClanRankData {
    int clan_rank_num;
    String clan_name;
    int clan_point;

    ClanRankData(JSONObject rank_json) {
        try {
            this.clan_name = rank_json.getString("clan_name");
            this.clan_point = rank_json.getInt("points");
            this.clan_rank_num = 0;
        } catch(JSONException e){
            System.out.println("JSON parse error. /rank");
        }
    }

    public int getClan_rank_num() {
        return clan_rank_num;
    }

    public String getClan_name() {
        return clan_name;
    }

    public int getClan_point() {
        return clan_point;
    }

    public void setClan_rank_num(int num){
        this.clan_rank_num = num;
    }
}
