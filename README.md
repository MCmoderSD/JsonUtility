# Json Utility

## Description
A simple utility to read JSON files and convert them into a JsonNode.

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
    <version>1.4.1</version>
</dependency>
```

### Usage Example
```java
import de.MCmoderSD.json.JsonUtility;

import tools.jackson.databind.JsonNode;

import java.io.IOException;
import java.net.URISyntaxException;

@SuppressWarnings("ALL")
void main() {

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
        throw new RuntimeException("Failed to load JSON: " + e.getMessage(), e);
    }
}
```