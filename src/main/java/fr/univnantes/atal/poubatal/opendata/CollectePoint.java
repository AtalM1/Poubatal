package fr.univnantes.atal.poubatal.opendata;

import com.googlecode.objectify.annotation.Embed;
import com.googlecode.objectify.annotation.Serialize;
import fr.univnantes.atal.poubatal.Tools;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Embed
public class CollectePoint implements Comparable<CollectePoint> {

    private String id;
    private String streetName;
    private String numberDescription;
    private String zoneName;
    private String city;
    @Serialize
    private List<String> yellowBin;
    @Serialize
    private List<String> blueBin;

    private CollectePoint() {
    }

    public CollectePoint(String csvRow) {

        String[] columns = csvRow.split(",");

        id = columns[0].replaceAll("\"", "");
        streetName = columns[1].replaceAll("\"", "");
        city = columns[2].replaceAll("\"", "");
        numberDescription = columns[9].replaceAll("\"", "");
        zoneName = columns[13].replaceAll("\"", "");

        yellowBin = parseCollectableDays(columns[11].replaceAll("\"", ""));
        blueBin = parseCollectableDays(columns[10].replaceAll("\"", ""));

    }
    
    private List<String> parseCollectableDays(String dayString) {
        List<String> collectableDays = new ArrayList<>();
        dayString = dayString.replaceAll("et", " ");
        dayString = dayString.replaceAll("-", " ");
        dayString = dayString.replaceAll("\\s+", " ");

        if (!dayString.contains("mixte")) {
            collectableDays.addAll(Arrays.asList(dayString.split(" ")));
        } else {
            //handle mixtes ?
        }
        return collectableDays;
    }

    public boolean filter(String address) {
        String concat = streetName + " " + city + " " + zoneName;
        concat = Tools.normalizeString(concat);
        address = Tools.normalizeString(address);
        String[] filters = address.split(" ");
        for (String filter : filters) {
            if (!concat.contains(filter)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Renvoi le CollectePoint correspondant à l'ID
     *
     * @param addressId L'ID du CollectePoint dans l'OpenData
     * @return Le CollectePoint correspondant, ou null
     */
    public static CollectePoint getById(String addressId) {
        CollectePoint point = null;
        LoopCollectePoint:
        for (CollectePoint current : DataManager.getInstance().getPoints()) {
            if (current.getId().equals(addressId)) {
                point = current;
                break LoopCollectePoint;
            }
        }
        return point;
    }

    @Override
    public String toString() {
        String res = "";

        res += getStreetName() + " -> ";
        res += getBlueBin().toString();

        return res;
    }

    /**
     * @return the streetName
     */
    public String getStreetName() {
        return streetName;
    }

    /**
     * @return the numberDescription
     */
    public String getNumberDescription() {
        return numberDescription;
    }

    /**
     * @return the zoneName
     */
    public String getZoneName() {
        return zoneName;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @return the yellowBin
     */
    public List<String> getYellowBin() {
        return Collections.unmodifiableList(yellowBin);
    }

    /**
     * @return the blueBin
     */
    public List<String> getBlueBin() {
        return Collections.unmodifiableList(blueBin);
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    @Override
    public int compareTo(CollectePoint t) {
        return id.compareTo(t.id);
    }
}
