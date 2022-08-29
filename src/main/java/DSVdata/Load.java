package DSVdata;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Load {
    private String payNumber;
    private String origin;
    private String distance;
    private String destination;
    private double weight;
    private String pickupDate;
    private String loadType;
    private String phoneNumber;
    private String deadHead;
    private String searchCity;
    private static Date pullDate;
    private static LocalDate PUdate;
    /******************************************************************************/
    public Load() {
        pullDate = new Date();

    }
    /******************************************************************************/
    public boolean weightLessthan(int x){
        return(weight < x);
    }
    /******************************************************************************/
    public void setPayNumber(String loadPayNumber) {
        payNumber = loadPayNumber;
    }
    /******************************************************************************/
    public String getPayNumber() {
        return payNumber;
    }
    /******************************************************************************/
    public void setPhoneNumber(String phone) {
        phoneNumber=phone;
    }
    /******************************************************************************/
    public void setEquipmentType(String equipType) {
        loadType = equipType;
    }
    /******************************************************************************/
    public void setPickUpDate(String pickDate) {
        try {
            Date date1Out = new SimpleDateFormat("MMM dd, yyy").parse(pickDate);
            PUdate = date1Out.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        pickupDate=pickDate;

    }
    /******************************************************************************/
    public void setWeight(String weight) {

        this.weight= Integer.valueOf(weight);
    }
    /******************************************************************************/
    public void setDistance(String dist) {
        distance = dist;
    }
    /******************************************************************************/
    public void setDeadHead(String dHead) {
        deadHead=dHead;
    }
    /******************************************************************************/
    public void setDestination(String dest) {
        destination=dest;
    }
    /******************************************************************************/
    public void setOrigin(String orig) {
        origin=orig;
    }
    /******************************************************************************/
    public void print(){
        System.out.println(
                "Pay number: " + payNumber+ "\n" +
                "Origin: " + origin + "\n" +
                "Destination: " + destination+ "\n" +

                "Dist: " + distance+ "\n" +
                "Weight: " + weight+ "\n" +
                "Pickup date: "+pickupDate+ "\n" +
                "Equipment: " + loadType +"\n"
                );
    }
    /******************************************************************************/
    public String type() {
        return (loadType.toLowerCase());
    }
    /******************************************************************************/
    public double getWeight(){
        return (weight);
    }
    /******************************************************************************/
    public String getLoadType(){
        return(loadType.toLowerCase());
    }
    /******************************************************************************/
    public String getOrigin() {
        return origin;
    }
    /******************************************************************************/
    public String getDistance() {
        return distance;
    }
    /******************************************************************************/
    public String getDestination() {
        return destination;
    }
    /******************************************************************************/
    public String getPickupDate() {
        return pickupDate;
    }
    /******************************************************************************/
    public String getPhoneNumber() {
        return phoneNumber;
    }
    /******************************************************************************/
    public String getDeadHead() {
        return deadHead;
    }
    /******************************************************************************/
    public String getSearchCity() {
        return searchCity;
    }
    /******************************************************************************/
    public void setSearchCity(String searchCity) {
        this.searchCity = searchCity;
    }
    /******************************************************************************/
    public static Date getPullDate() {
        return pullDate;
    }
    /******************************************************************************/
    public static LocalDate getPUdate() {
        return PUdate;
    }
}
