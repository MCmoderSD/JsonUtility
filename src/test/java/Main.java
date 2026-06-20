import de.MCmoderSD.json.JsonUtility;

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
    IO.println("Resource JSON: \n" + resourceJson.toPrettyString());
    IO.println("\nURL JSON: \n" + urlJson.toPrettyString());
    IO.println("\nFile JSON: \n" + fileJson.toPrettyString());
}