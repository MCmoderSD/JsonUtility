import com.fasterxml.jackson.databind.JsonNode;
import de.MCmoderSD.json.JsonUtility;

import java.io.IOException;
import java.net.URISyntaxException;

@SuppressWarnings("ALL")
public class Main {

    public static void main(String[] args) {

        // Create an instance of JsonUtility
        JsonUtility jsonUtility = JsonUtility.getInstance();

        try {

            // Load from resources folder
            JsonNode resource = jsonUtility.load("/config.json");

            // Load from URL
            JsonNode url = jsonUtility.load("https://raw.githubusercontent.com/MCmoderSD/JsonUtility/refs/heads/master/src/test/resources/config.json");

            // Load from absolute path
            JsonNode absolute = jsonUtility.load("C:/Users/username/Desktop/config.json", true);

        } catch (IOException | URISyntaxException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}