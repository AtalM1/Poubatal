package fr.univnantes.atal.poubatal.tools;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univnantes.atal.poubatal.Constants;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;

/**
 *
 * @author Noemi
 */
public class Oauth {

    static private ObjectMapper mapper = new ObjectMapper();

    /**
     *
     * @param accessToken
     * @return
     */
    public static Map<String, String> getGoogleUser(String accessToken) {
        try {
            URL url = new URL("https://www.googleapis.com/oauth2/v1/tokeninfo" + "?access_token=" + accessToken);

            Map<String, String> userData = mapper.readValue(
                    new InputStreamReader(url.openStream(), "UTF-8"),
                    new TypeReference<Map<String, String>>() {
            });
            if (userData.containsKey("error") || !userData.get("audience").equals(Constants.APPLICATION_ID)) {
                return null;
            } else {
                return userData;
            }
        } catch (Exception ex) {
            return null;
        }
    }
}