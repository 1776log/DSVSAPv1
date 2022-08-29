import DSVdata.Load;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Hashtable;
import java.util.Stack;

public class loadGetter {
    private static String tableClassName;
    private static Load load;
    private static SlackBot slackBot;
    private static int total;
    private static int todaysDay;

    public loadGetter(String tableClassName) {
        this.tableClassName = tableClassName;
        slackBot = new SlackBot();
        total = 0;
        Date today = new Date();
        todaysDay = Integer.parseInt(today.toString().substring(8, 10));
    }
    /******************************************************************************/
    public void getNewLoads(File pageSource, Hashtable<String, Load> loadHashtable)
            throws IOException {
        Stack<String> loadDataStack = new Stack<>();
        Document doc = Jsoup.parse(pageSource);
        Elements resultsTable = doc.getElementsByClass(tableClassName);
        for (Element el :
                resultsTable) {
            loadDataStack.push(el.text());
        }
        while (!loadDataStack.isEmpty()) {
            load = new Load();
            load.setSearchCity(pageSource.toString());
            loadDataStack.pop();
            load.setPhoneNumber(loadDataStack.pop());
            load.setEquipmentType(loadDataStack.pop());
            load.setPickUpDate(loadDataStack.pop());
            System.out.println(pageSource.toString());
            load.setWeight(loadDataStack.pop());
            load.setDistance(loadDataStack.pop());
            load.setDeadHead(loadDataStack.pop());
            load.setDestination(loadDataStack.pop());
            load.setOrigin(loadDataStack.pop());
            load.setPayNumber(loadDataStack.pop());
            /*===================================================================*/
            /*Add the load to the hash table only if it is not already there====*/
            /*===================================================================*/
            if (!loadHashtable.containsKey(load.getPayNumber())) {
                total++;
                loadHashtable.put(load.getPayNumber(), load);
                System.out.println("new load found. Total: " + total);
                /*send a slack notification to expidite main if weights under
                * 10000 pounds AND pickupdate is today or later*/
                if (load.getWeight() < 10000
                        && load.getWeight() > 0
                        && !pickupDateInPast(load)
                        && (Integer.parseInt(load.getPayNumber()) != 15478816)
                        && (Integer.parseInt(load.getPayNumber()) != 15478820)
                        && (Integer.parseInt(load.getPayNumber()) != 15478826)
                        ) {
                    System.out.println("New Under 10k found in region: "
                            + load.getSearchCity() + " " + load.getPayNumber());
                    slackBot.sendLoadInfo(load);
                }
            }
            /*===================================================================*/
            /*===================================================================*/
        }
    }
    /******************************************************************************/
    private static boolean pickupDateInPast(Load l) {
        int pickupDay = l.getPUdate().getDayOfMonth();
        return (pickupDay < todaysDay);
    }
    /******************************************************************************/
}
