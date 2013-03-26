package fr.univnantes.atal.poubatal.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;

public class Oauth {

    static private ObjectMapper mapper = new ObjectMapper();

    public static boolean verifyOauth(String token) {
        if (token == null) {
            return false;
        } else {
            try {
                URL url = new URL("https://www.googleapis.com/oauth2/v1/tokeninfo" + "?access_token=" + token);

                Map<String, String> userData = mapper.readValue(
                        new InputStreamReader(url.openStream(), "UTF-8"),
                        new TypeReference<Map<String, String>>() {
                });
                if (userData.containsKey("error") || !userData.get("audience").equals("385110343883.apps.googleusercontent.com")) {
                    return false;
                } else {
 //                   String email = userData.get("email");
                    return true;
                }
            } catch (Exception ex) {
                return false;
            }
        }
    }
}