package subai.trak2;

/**
 * Created by - on 30/10/2017.
 */

public class Bus {
    static class Coordinates {
        public String lat, lng;

        Coordinates(String lat, String lon){
            this.lat = lat;
            this.lng = lon;
        }
    }

    public Coordinates position, destination;
    public String busNumber, busCompany, route, accommodation, status;

    Bus(String x2, String y2){
        destination = new Coordinates(String.valueOf(10.298237), String.valueOf(123.893133));
        position = new Coordinates(x2, y2);
        busCompany = "Ceres";
        route = "Bato";
        accommodation = "Aircon";
        status = "In-transit";
        busNumber = "ABC1234";
    }

    Bus(String x2, String y2, String busNumber, String route, String accommodation, String busCompany) {
        destination = new Coordinates(String.valueOf(10.298237), String.valueOf(123.893133));
        position = new Coordinates(x2, y2);
        this.route = route;
        status = "In-transit";
        this.busNumber = busNumber;
//        this.busCompany= "Ceres";
//        this.accommodation = "Ordinary";
        this.busCompany = busCompany;
        this.accommodation = accommodation;
    }

    Bus(){
        //dummy values
        destination = new Coordinates(String.valueOf(10.298237), String.valueOf(123.893133));
        position = new Coordinates("", "");
        busCompany = "Ceres";
        route = "Bato";
        accommodation = "Aircon";
        status = "In-transit";
        busNumber = "ABC1234";
    }


    public void setBusDetails(String lat, String lng, String busNumber, String route, String acc, String busComp){
        destination = new Coordinates(String.valueOf(10.298237), String.valueOf(123.893133));
        position = new Coordinates(lat,lng);
        this.busNumber = busNumber;
        this.route = route;
        this.accommodation = acc;
        this.busCompany = busComp;
    }

    //lat, lng, busNumber, route, accommodation, busCompany

    public void setStatus(String st) {
        status = st;
    }

}

