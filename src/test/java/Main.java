import de.MCmoderSD.json.JsonUtility;
import tools.jackson.databind.JsonNode;

void main() {

    // Get JsonUtility instance
    JsonUtility jsonUtility = JsonUtility.getInstance();

    // Load JSON from Resource
    JsonNode resourceJson = jsonUtility.loadResource("/config.json");

    // Load JSON from URL
    JsonNode urlJson = jsonUtility.loadURL("https://raw.githubusercontent.com/MCmoderSD/JsonUtility/refs/heads/master/src/test/resources/config.json");

    // Load JSON from File
    JsonNode fileJson = jsonUtility.loadFile("src/test/resources/config.json");

    // Print loaded JSON
    IO.println("Resource JSON: \n" + resourceJson.toPrettyString());
    IO.println("\nURL JSON: \n" + urlJson.toPrettyString());
    IO.println("\nFile JSON: \n" + fileJson.toPrettyString());
}