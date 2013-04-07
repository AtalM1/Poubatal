package fr.univnantes.atal.poubatal.tools.json;

public class JSONError<T> {

    public T error;

    public JSONError(T object) {
        error = object;
    }
}
