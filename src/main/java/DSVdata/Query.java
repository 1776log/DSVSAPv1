package DSVdata;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Stack;

public class Query {
    private ArrayList<Load> DSVloads;
    private Document doc;
    private static String tableClassName;
    private Load load;
    private String queryName;

    /******************************************************************************/
    public Query(File pageSource, String resultTableClassName) throws IOException {

        DSVloads = new ArrayList<>();
        tableClassName = resultTableClassName;
        queryName = pageSource.toString();
        /*parse HTML source-----------------------*/
        doc = Jsoup.parse(pageSource);
        /*parse HTML source-----------------------*/
        createDSVLoadList(doc);
        //map.addNewCircle(pageSource.toString());

        System.err.println("Completed: " + queryName);
    }

    public Query() {

    }

    /******************************************************************************/
    private void createDSVLoadList(Document doc){
        Stack<String> stack = new Stack<>();
        Elements resultsTable = doc.getElementsByClass(tableClassName);
        for (Element el:
                resultsTable) {
            stack.push(el.text());
        }
        while (!stack.isEmpty()){
            load = new Load();
            stack.pop();
            load.setPhoneNumber(stack.pop());
            load.setEquipmentType(stack.pop());
            load.setPickUpDate(stack.pop());
            load.setWeight(stack.pop());
            load.setDistance(stack.pop());
            load.setDeadHead(stack.pop());
            load.setDestination(stack.pop());
            load.setOrigin(stack.pop());
            load.setPayNumber(stack.pop());

            DSVloads.add(load);
        }
    }
    /******************************************************************************/
    public void getNewLoads(File pageSource, Hashtable<String, Load> loadHashtable) throws IOException {
        doc = Jsoup.parse(pageSource);
        Stack<String> stack = new Stack<>();
        Elements resultsTable = doc.getElementsByClass(tableClassName);
        for (Element el:
                resultsTable) {
            stack.push(el.text());
        }
        while (!stack.isEmpty()){
            load = new Load();
            stack.pop();
            load.setPhoneNumber(stack.pop());
            load.setEquipmentType(stack.pop());
            load.setPickUpDate(stack.pop());
            load.setWeight(stack.pop());
            load.setDistance(stack.pop());
            load.setDeadHead(stack.pop());
            load.setDestination(stack.pop());
            load.setOrigin(stack.pop());
            load.setPayNumber(stack.pop());
            if (!loadHashtable.containsKey(load.getPayNumber())){
                loadHashtable.put(load.getPayNumber(),load);
            }
            else{
                System.out.println("Duplicate load found: " + load.getPayNumber());
            }
        }
    }
    /******************************************************************************/

    public void print() {
        for (Load l :
             DSVloads) {
            l.print();
        }
    }
    /******************************************************************************/
    public void printOnlyUnder10K() {
        for (Load l :
                DSVloads) {
            if (l.getWeight() <10000)
            l.print();
        }
    }
    /******************************************************************************/
    public void printOnlyVanLoads() {
        for (Load l :
                DSVloads) {
            //returns lowercase load type
            if (l.getLoadType().contains("van"))
                l.print();
        }
    }
    /******************************************************************************/
    public ArrayList<Load> getloads() {
        return (DSVloads);
    }
    /******************************************************************************/
    public ArrayList<Load> underWeight(int x){
        ArrayList results = new ArrayList();
        for (Load l:
                DSVloads) {
            if (l.weightLessthan(x)){
                results.add(l);
            }
        }
        return  results;
    }
}
