package fr.univnantes.atal.poubatal.tools.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSON {
    
    /**
     * Encapsulates an object with data and display it in JSON format
     *
     * @param <T> Generic type of object
     * @param object Object to be parsed
     * @return The JSON representation of object
     * @throws JsonProcessingException
     */
    public static <T> String data(T object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(new JSONData<>(object));
    }

    /**
     * Encapsulates an object with an error and display it in JSON format
     *
     * @param <T> Generic type of object
     * @param object Object to be parsed
     * @return The JSON representation of object
     * @throws JsonProcessingException
     */
    public static <T> String error(T object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(new JSONError<>(object));
    }
}
