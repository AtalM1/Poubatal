package fr.univnantes.atal.poubatal;

import static fr.univnantes.atal.poubatal.datastore.OfyService.ofy;
import fr.univnantes.atal.poubatal.entity.User;
import java.util.Map;

public class UserLoader {

    public static User load(String accessToken) {
        // Récupération du GoogleUser avec l'accessToken
        Map<String, String> userData = Oauth.getGoogleUser(accessToken);
        if (userData == null) {
            return null;
        }
        String id = userData.get("user_id");
        String email = userData.get("email");

        // Récupération du User avec l'ID Google
        User user = ofy().load().type(User.class).id(id).get();
        if (user == null) {
            user = new User(id, email);
            user.save();
        }
        return user;
    }
}
