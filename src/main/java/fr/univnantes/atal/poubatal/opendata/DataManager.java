package fr.univnantes.atal.poubatal.opendata;

import fr.univnantes.atal.poubatal.entity.Address;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DataManager is a Singleton
 */
public class DataManager {

    private static final Logger log = Logger.getLogger(DataManager.class.getName());
    private static DataManager instance = null;
    private URL urlToFetch;
    private List<Address> points;

    /**
     *
     */
    protected DataManager() {
        points = new ArrayList<>();
        try {
            urlToFetch = new URL("http://data.nantes.fr/api/publication/"
                    + "JOURS_COLLECTE_DECHETS_VDN/JOURS_COLLECTE_DECHETS_VDN_STBL/content/"
                    + "?format=csv");
        } catch (MalformedURLException ex) {
            log.warning(ex.toString());
        }
    }
    
    /**
     *
     * @param addressId
     * @return
     */
    public static Address getAddressById(String addressId) {
        Address address = null;
        LoopAddresses:
        for (Address current : DataManager.getInstance().getPoints()) {
            if (current.getId().equals(addressId)) {
                address = current;
                break LoopAddresses;
            }
        }
        return address;
    }

    /**
     *
     * @return
     */
    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
            instance.updateData();
        }
        return instance;
    }
    
    /**
     *
     * @return
     */
    public List<Address> getPoints() {
        if (instance.points.isEmpty()) {
            // something goes wrong during the initialization
            instance.updateData();
        }
        return instance.points;
    }

    /**
     *
     */
    public void updateData() {
        String http_proxy = System.getenv().get("http_proxy");
        try {
            if (http_proxy != null) {
                URL proxy_url = new URL(http_proxy);

                System.getProperties().put("proxySet", "true");
                System.getProperties().put("proxyHost", proxy_url.getHost());

                if (proxy_url.getPort() > 0) {
                    System.getProperties().put("proxyPort", String.valueOf(proxy_url.getPort()));
                }
                System.out.println("Now using http_proxy=" + proxy_url);
            }
            InputStreamReader in = new InputStreamReader(urlToFetch.openConnection().getInputStream(), "UTF-8");
            BufferedReader buffer = new BufferedReader(in);

            //reads the header
            String nextLine = buffer.readLine();
            //reads first line
            nextLine = buffer.readLine();

            while (nextLine != null) {
                Address address = new Address(nextLine);
                if (!address.isMixte()) {
                    points.add(address);
                }                
                nextLine = buffer.readLine();
            }
        } catch (IOException ex) {
            log.severe(ex.toString());
        }
    }
}
