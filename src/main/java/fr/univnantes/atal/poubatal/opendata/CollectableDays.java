package fr.univnantes.atal.poubatal.opendata;

import java.util.ArrayList;
import java.util.Arrays;

@Deprecated
public class CollectableDays extends ArrayList<String> {

    private CollectableDays() {
    }

    public CollectableDays(String dayString) {

        dayString = dayString.replaceAll("et", " ");
        dayString = dayString.replaceAll("-", " ");
        dayString = dayString.replaceAll("\\s+", " ");

        if (!dayString.contains("mixte")) {
            this.addAll(Arrays.asList(dayString.split(" ")));
        } else {
            //handle mixtes ?
        }

    }
}
