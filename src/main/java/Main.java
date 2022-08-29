/*DSV Scape And Post****************************************************************/
/**********************************************************************************/
import DSVdata.Load;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;

public class Main {

    //DSV Scape and Post
    private static final String LOAD_TABLE_CLASSNAME = "rf-dt-c";
    private static String[] searchCities;
    private static webDriver chromeDriver;
    private static Hashtable<String, Load> loadHashtable;
    private static loadGetter lg;
    static File allLoads = new File("/Users/nicholasburt/Desktop/allLoads.csv");

    /******************************************************************************/
    public static void main(String[] args) throws IOException, InterruptedException {
        /*should be searching a given query at a given rate and checking results against
         * already checked loads*/
        init();
        int i = 0;
//        for (int j = 0; j < 400; j++) {
//            searchUS();
//            Thread.sleep(10000);
//            System.out.println("Searched: " + i);
//            i++;
//        }
        searchUS();
        chromeDriver.killDriver();
        buildCSV(loadHashtable);


    }
    /******************************************************************************/
    private static void buildCSV(Hashtable<String, Load> loadHashtable) throws IOException {
        Enumeration<String> enumer = loadHashtable.keys();
        Load load;
        FileWriter writer = new FileWriter(allLoads);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("Pay number")
                .append(",")
                .append("Origin")
                .append(",")
                .append("Destination")
                .append(",")
                .append("Dead Head")
                .append(",")
                .append("Distance")
                .append(",")
                .append("Weight")
                .append(",")
                .append("Pickup Date")
                .append(",")
                .append("Equipment")
                .append(",")
                .append("Phone #")
                .append(",")
                .append("Date Pulled")
                .append("\n");
        while (enumer.hasMoreElements()){
            String key = enumer.nextElement();
            load = loadHashtable.get(key);
                        stringBuilder
                                .append(load.getPayNumber())
                                .append(",")
                                .append("\"")
                                .append(load.getOrigin())
                                .append("\"")
                                .append(",")
                                .append("\"")
                                .append(load.getDestination())
                                .append("\"")
                                .append(",")
                                .append(load.getDeadHead())
                                .append(",")
                                .append(load.getDistance())
                                .append(",")
                                .append(load.getWeight())
                                .append(",")
                                .append("\"")
                                .append(load.getPickupDate())
                                .append("\"")
                                .append(",")
                                .append(load.getLoadType())
                                .append(",")
                                .append(load.getPhoneNumber())
                                .append(",")
                                .append("\"")
                                .append(load.getPullDate())
                                .append("\"")
                                .append(",")
                                .append("\n");
                        // System.err.println(load.getDestination().replace(",","\",\""));
                    }
            writer.append(stringBuilder.toString());
        System.err.println("CSV file created...");
        writer.close();
    }

    /******************************************************************************/
    public static void init(){
        searchCities = new String[]{"Kennewick, WA", "Redding, CA", "Bakersfield, CA",
                "Show Low, AZ", "Salt Lake City, UT", "Lewistown, MT", "Jamestown, ND",
                "Crivitz, WI", "Somerset, PA", "Stoneham, CO", "Maysville, MO",
                "Georgetown, SC", "Floydada, TX", "Pandale, TX", "Houston, TX",
                "Shreveport, LA", "Holt, FL", "Miami, FL", "Dale, IN", "Scarborough, ME",
                "Bakersfield, CA"};
        //searchCities = new String[]{"San Francisco, CA","Holt, FL","San Francisco, CA"};
        //MapCoverageDisplay map = new MapCoverageDisplay(1);
        chromeDriver = new webDriver();
        loadHashtable = new Hashtable<String, Load>();
        lg = new loadGetter(LOAD_TABLE_CLASSNAME);
    }
    /******************************************************************************/
    public static void searchUS() throws IOException {
        for (String city:
                searchCities) {
            lg.getNewLoads(chromeDriver.getSource(city),loadHashtable);
        }

    }
}





