package fr.univnantes.atal.poubatal.tools.json;

/**
 *
 * @author Noemi
 * @param <T>
 */
public class JSONError<T> {

    /**
     *
     */
    public T error;

    /**
     *
     * @param object
     */
    public JSONError(T object) {
        error = object;
    }
}
