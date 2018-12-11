package project.capstone.sw.yahaja;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by JINO on 2018-12-11.
 */

public class MatchData {

    int match_id;
    String match_type;
    String match_status;
    String member1;
    String member2;
    String match_date;
    String matchPlace;

    MatchData(JSONObject match_json) {
        try {
            this.match_id = match_json.getInt("match_id");
            this.match_type = match_json.getString("match_type");
            this.match_status = match_json.getString("match_status");
            this.member1 = match_json.getString("match_member1");
            this.member2 = match_json.getString("match_member2");
            this.match_date = match_json.getString("match_date");
            this.matchPlace = match_json.getString("match_place");
        } catch(JSONException e){
            System.out.println("JSON parse error. /match");
        }
    }

    public String getMember1() {
        return member1;
    }

    public String getMember2() {
        return member2;
    }

    public String getMatchPlace() {
        return matchPlace;
    }
}
