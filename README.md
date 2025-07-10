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
    <version>1.3.1</version>
</dependency>
```

### Usage Example
```java
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

            // Load from absolute path
            JsonNode absolute = jsonUtility.load("C:/Users/username/Desktop/config.json", true);

            // Load from URL
            JsonNode url = jsonUtility.load("https://example.com/config.json");

        } catch (IOException | URISyntaxException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
```