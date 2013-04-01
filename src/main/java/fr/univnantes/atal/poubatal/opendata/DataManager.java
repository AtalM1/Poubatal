package fr.univnantes.atal.poubatal.opendata;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

/**
 * DataManager is a Singleton
 */
public class DataManager {

    private static final Logger log = Logger.getLogger(DataManager.class.getName());
    private static DataManager instance = null;
    private URL urlToFetch;
    private List<CollectePoint> points;

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

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
            instance.updateData();
        }
        return instance;
    }
    
    public List<CollectePoint> getPoints(String filter) {
        // Création d'une liste de couple
        List<Map.Entry<Double,CollectePoint>> scores = new ArrayList<>();
        // Pour chaque adresse, on calcule son score par rapport au filtre
        for (CollectePoint current : getPoints()) {
            double score = current.filter(filter);
            if (score > 0.4) {
                scores.add(new AbstractMap.SimpleEntry<>(score, current));
            }
        }
        // On trie la collection par ordre de score
        Collections.sort(scores, new Comparator<Map.Entry<Double, CollectePoint>>() {
            @Override
            public int compare(Entry<Double, CollectePoint> o1, Entry<Double, CollectePoint> o2) {
                return o2.getKey().compareTo(o1.getKey());
            }
        });
        List<CollectePoint> filteredPoints = new ArrayList<>();
        // On recopie la liste triée dans la liste de retour
        for (Map.Entry<Double,CollectePoint> entry : scores) {
            filteredPoints.add(entry.getValue());
        }
        // On récupère au maximum 10 enregistrements
        int range = Math.min(filteredPoints.size(), 10);
        return filteredPoints.subList(0, range);
    }

    public List<CollectePoint> getPoints() {
        if (instance.points.isEmpty()) {
            // something goes wrong during the initialization
            instance.updateData();
        }
        return instance.points;
    }

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
                //System.out.println(nextLine);
                points.add(new CollectePoint(nextLine));
                nextLine = buffer.readLine();
            }
        } catch (IOException ex) {
            log.severe(ex.toString());
        }
    }
}
