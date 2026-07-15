import de.MCmoderSD.json.JsonUtility;

import static java.lang.IO.println;

void main() {

    // Get JsonUtility instance
    var jsonUtility = JsonUtility.getInstance();

    // Load JSON from Resource
    var resourceJson = jsonUtility.loadResource("/config.json");

    // Load JSON from URL
    var urlJson = jsonUtility.loadURL("https://raw.githubusercontent.com/MCmoderSD/JsonUtility/refs/heads/master/src/test/resources/config.json");

    // Load JSON from File
    var fileJson = jsonUtility.loadFile("src/test/resources/config.json");

    // Print loaded JSON
    println("Resource JSON: \n" + resourceJson.toPrettyString());
    println("\nURL JSON: \n" + urlJson.toPrettyString());
    println("\nFile JSON: \n" + fileJson.toPrettyString());
}