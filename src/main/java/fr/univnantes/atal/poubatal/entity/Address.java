package fr.univnantes.atal.poubatal.entity;

import com.google.appengine.api.datastore.Key;
import fr.univnantes.atal.poubatal.opendata.DataManager;
import fr.univnantes.atal.poubatal.tools.Tools;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Address implements Comparable<Address> {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key; // Unused key
    @Persistent
    private String id;
    @Persistent
    private String streetName;
    @Persistent
    private String numberDescription;
    @Persistent
    private String zoneName;
    @Persistent
    private String city;
    @Persistent
    private List<String> yellowBin;
    @Persistent
    private List<String> blueBin;

    private Address() {
    }

    public Address(String csvRow) {

        String[] columns = csvRow.split(",");
        streetName = columns[1].replaceAll("\"", "");
        city = columns[2].replaceAll("\"", "");
        numberDescription = columns[9].replaceAll("\"", "");
        zoneName = columns[13].replaceAll("\"", "");
        id = Tools.fullNormalizeString(streetName) + " " + Tools.fullNormalizeString(numberDescription);

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

    /**
     * Renvoi le Address correspondant à l'ID
     *
     * @param addressId L'ID du Address dans l'OpenData
     * @return Le Address correspondant, ou null
     */
    public static Address getById(String addressId) {
        Address point = null;
        LoopCollectePoint:
        for (Address current : DataManager.getInstance().getPoints()) {
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

    @Override
    public int compareTo(Address t) {
        return id.compareTo(t.id);
    }
    
    public String getId() {
        return id;
    }
}
