package project.capstone.sw.yahaja;

import org.json.JSONException;
import org.json.JSONObject;

public class Url_RandomFc {

    class RandomFc{
        String name;
        String location;
        String contact;
        RandomFc(){}
    }

    public final String url = "http://18.220.15.129:3000/random_fc";

    Url_RandomFc(){
    }

    public String getApiUrl(){
        String apiUrl = url + "?";

        return apiUrl;
    }

    public RandomFc parseJSON(String response){
        RandomFc randomFc = new RandomFc();

        try {
            JSONObject res = new JSONObject(response);
            String name = res.getString("name");
            String location = res.getString("location");
            String contact = res.getString("contact");
            randomFc.name = name;
            randomFc.location = location;
            randomFc.contact = contact;
        } catch (JSONException e){
            System.out.println("JSON parse error. / random_fc");
        }

        return randomFc;
    }
}
