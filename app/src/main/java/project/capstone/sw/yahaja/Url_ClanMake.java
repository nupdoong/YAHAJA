package project.capstone.sw.yahaja;

public class Url_ClanMake {
    public final String url = "http://13.59.95.38:3000/signup2?";
    //public final String url = "http://222.98.25.138:5001/signup?";

    private String clan_name;
    private String clan_type;
    private String clan_master;
    private String clan_introduction;


    Url_ClanMake(String clan_name, String clan_type, String clan_master, String clan_introduction){
        this.clan_name = clan_name;
        this.clan_type = clan_type;
        this.clan_master = clan_master;
        this.clan_introduction = clan_introduction;
    }

    public String getPostDataParameter(){
        String dataParameter = "";
        dataParameter += "clan_name=" + clan_name;
        dataParameter += "&clan_type=" + clan_type;
        dataParameter += "&clan_master=" + clan_master;
        dataParameter += "&clan_introduction=" + clan_introduction;

        return dataParameter;
    }

}
