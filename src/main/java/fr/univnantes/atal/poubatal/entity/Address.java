package fr.univnantes.atal.poubatal.entity;

import com.google.appengine.api.datastore.Key;
import fr.univnantes.atal.poubatal.opendata.DataManager;
import fr.univnantes.atal.poubatal.tools.Tools;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Address {

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
    private boolean mixte;
    @Persistent
    private Set<String> yellowBin;
    @Persistent
    private Set<String> blueBin;

    private Address() {
    }

    public Address(String csvRow) {
        String[] columns = csvRow.split(",");
        streetName = columns[1].replaceAll("\"", "");
        city = columns[2].replaceAll("\"", "");
        numberDescription = columns[9].replaceAll("\"", "");
        zoneName = columns[13].replaceAll("\"", "");
        id = streetName + " " + numberDescription;
        id = Tools.fullNormalizeString(id);
        yellowBin = parseCollectableDays(columns[11].replaceAll("\"", ""));
        blueBin = parseCollectableDays(columns[10].replaceAll("\"", ""));
    }

    private Set<String> parseCollectableDays(String dayString) {
        Set<String> collectableDays = new TreeSet<>();
        dayString = dayString.replaceAll("et", " ");
        dayString = dayString.replaceAll("-", " ");
        dayString = dayString.replaceAll("\\s+", " ");

        if (!dayString.contains("mixte")) {
            mixte = false;
            collectableDays.addAll(Arrays.asList(dayString.split(" ")));
        } else {
            mixte = true;
        }
        return collectableDays;
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
    public Set<String> getYellowBin() {
        return Collections.unmodifiableSet(yellowBin);
    }

    /**
     * @return the blueBin
     */
    public Set<String> getBlueBin() {
        return Collections.unmodifiableSet(blueBin);
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the mixte
     */
    public boolean isMixte() {
        return mixte;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (getClass() != obj.getClass()) {
            return false;
        } else {
            Address other = (Address) obj;
            return id.equals(other.id);
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.id);
        return hash;
    }
}
