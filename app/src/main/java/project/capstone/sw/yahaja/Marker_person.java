package project.capstone.sw.yahaja;

public class Marker_person {

    double lat;
    double lon;
    String firstname;

    public Marker_person(double lat, double lon, String firstname) {
        this.lat = lat;
        this.lon = lon;
        this.firstname = firstname;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getCustom_id() {
        return firstname;
    }

    public void setCustom_id(int price) {
        this.firstname = firstname;
    }
}
