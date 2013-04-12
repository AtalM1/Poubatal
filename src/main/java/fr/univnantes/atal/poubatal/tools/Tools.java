package fr.univnantes.atal.poubatal.tools;

import java.text.Normalizer;

/**
 *
 * @author Noemi
 */
public class Tools {

    /**
     * Normalizes a string by removing accents
     *
     * @param string The string to be normalize
     * @return The normalized string
     */
    public static String removeStringAccents(String string) {
        return Normalizer.normalize(string, Normalizer.Form.NFD).replaceAll("[\u0300-\u036F]", "");
    }

    /**
     * Normalizes a string by removing spaces
     *
     * @param string The string to be normalize
     * @return The normalized string
     */
    public static String normalizeStringSpaces(String string) {
        return string.trim().replaceAll("\\s+", " ");
    }

    /**
     * Normalizes a string by removing spaces, accents and case
     *
     * @param string The string to be normalize
     * @return The normalized string
     */
    public static String fullNormalizeString(String string) {
        return normalizeStringSpaces(removeStringAccents(string)).toLowerCase();
    }   
}
