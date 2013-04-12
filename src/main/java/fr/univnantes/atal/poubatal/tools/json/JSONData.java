package fr.univnantes.atal.poubatal.tools.json;

/**
 *
 * @author Noemi
 * @param <T>
 */
public class JSONData<T> {

    /**
     *
     */
    public T data;

    /**
     *
     * @param object
     */
    public JSONData(T object) {
        data = object;
    }
}