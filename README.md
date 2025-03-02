# Json Utility

## Description
A simple utility to read json files and convert them into a JsonNode.


## Usage

### Maven
Make sure you have my Sonatype Nexus OSS repository added to your `pom.xml` file:
```xml
<repositories>
    <repository>
        <id>Nexus</id>
        <name>Sonatype Nexus</name>
        <url>https://mcmodersd.de/nexus/repository/maven-releases/</url>
    </repository>
</repositories>
```
Add the dependency to your `pom.xml` file:
```xml
<dependency>
    <groupId>de.MCmoderSD</groupId>
    <artifactId>JsonUtility</artifactId>
    <version>1.2.0</version>
</dependency>
```

### Usage Example
```java
import com.fasterxml.jackson.databind.JsonNode;
import de.MCmoderSD.json.JsonUtility;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {

    public static void main(String[] args) {

        // Create new instance of JsonUtility
        JsonUtility jsonUtility = new JsonUtility();

        try {

            // Load from resources folder
            JsonNode resource = jsonUtility.load("/config.json");

            // Load from absolute path
            JsonNode absolute = jsonUtility.load("C:/Users/username/Desktop/config.json", true);

            // Load from URL
            JsonNode url = jsonUtility.load("https://example.com/config.json");


            // If you don't to create a new instance of JsonUtility, you can use the static method
            // Then you cant use the cache and have to specify if the path is absolute


            // Load from resources folder
            JsonNode staticResource = JsonUtility.loadJson("/config.json", false);

            // Load from absolute path
            JsonNode staticAbsolute = JsonUtility.loadJson("C:/Users/username/Desktop/config.json", true);

            // Load from URL
            JsonNode staticUrl = JsonUtility.loadJson("https://example.com/config.json", false);
        } catch (IOException | URISyntaxException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
```