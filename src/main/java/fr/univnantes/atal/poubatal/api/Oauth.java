package fr.univnantes.atal.poubatal.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univnantes.atal.poubatal.Constants;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;

public class Oauth {

    static private ObjectMapper mapper = new ObjectMapper();

    public static GoogleUser getGoogleUser(String token) {
        try {
            URL url = new URL("https://www.googleapis.com/oauth2/v1/tokeninfo" + "?access_token=" + token);

            Map<String, String> userData = mapper.readValue(
                    new InputStreamReader(url.openStream(), "UTF-8"),
                    new TypeReference<Map<String, String>>() {
            });
            if (userData.containsKey("error") || !userData.get("audience").equals(Constants.APPLICATION_ID)) {
                return null;
            } else {
                GoogleUser user = new GoogleUser();
                user.setEmail(userData.get("email"));
                user.setId(userData.get("user_id"));
                user.setName(userData.get("name"));
                return user;
            }
        } catch (Exception ex) {
            return null;
        }
    }
}