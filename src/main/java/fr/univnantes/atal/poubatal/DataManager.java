/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univnantes.atal.poubatal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.URL;
import java.net.MalformedURLException;

/**
 *
 * @author sildar
 */
public class DataManager {

    URL urlToFetch;
    List<CollectePoint> points;

    public DataManager() {

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
        } catch(MalformedURLException ex) {
            Logger.getLogger(DataManager.class.getName()).log(Level.WARNING, null, ex);
        }

        try {
            points = new ArrayList<CollectePoint>();

            String urlString = "http://data.nantes.fr/api/publication/"
                + "JOURS_COLLECTE_DECHETS_VDN/JOURS_COLLECTE_DECHETS_VDN_STBL/content/"
                + "?format=csv";

            urlToFetch = new URL(urlString);

            InputStreamReader in = new InputStreamReader(urlToFetch.openConnection().getInputStream());
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
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<CollectePoint> getPoints() {
        return points;
    }

    public void updateData() {
        try {
            InputStreamReader in = new InputStreamReader(urlToFetch.openConnection().getInputStream());
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
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
