package fr.univnantes.atal.poubatal.opendata;

/**
 *
 * @author sildar
 */
public class CollectePoint {

    private String id;
    private String streetName;
    private String numberDescription;
    private String zoneName;
    private String city;
    private CollectableDays yellowBin;
    private CollectableDays blueBin;

    public CollectePoint(String csvRow) {

        String[] columns = csvRow.split(",");

        id = columns[0].replaceAll("\"", "");
        streetName = columns[1].replaceAll("\"", "");
        city = columns[2].replaceAll("\"", "");
        numberDescription = columns[9].replaceAll("\"", "");
        zoneName = columns[13].replaceAll("\"", "");

        yellowBin = new CollectableDays(columns[11].replaceAll("\"", ""));
        blueBin = new CollectableDays(columns[10].replaceAll("\"", ""));

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
    public CollectableDays getYellowBin() {
        return yellowBin;
    }

    /**
     * @return the blueBin
     */
    public CollectableDays getBlueBin() {
        return blueBin;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }
}
