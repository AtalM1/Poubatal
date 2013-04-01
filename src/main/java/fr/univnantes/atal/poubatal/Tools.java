package fr.univnantes.atal.poubatal;

import java.text.Normalizer;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Tools {

    /**
     *
     * Normalisation d'une String (Suppression des accents)
     */
    public static String stringWithoutAccents(String string) {
        return Normalizer.normalize(string, Normalizer.Form.NFD).replaceAll("[\u0300-\u036F]", "");
    }

    /**
     *
     * Normalisation d'une String (Suppression des espaces en trop)
     */
    public static String normalizeStringSpaces(String string) {
        return string.trim().replaceAll("\\s+", " ");
    }

    /**
     *
     * Normalisation d'une String (Suppression des accents, des espaces en trop
     * et de la casse)
     */
    public static String normalizeString(String string) {
        return normalizeStringSpaces(stringWithoutAccents(string)).toLowerCase();
    }
}
