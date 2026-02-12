# Json Utility

## Description
A simple utility to read JSON files and convert them into a JsonNode.
It supports loading JSON files directly from:
- The resources folder
- A file path
- A URL

It also caches loaded JSON files to improve performance on subsequent loads.

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
    <version>1.5.0</version>
</dependency>
```

### Usage Example
```java
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
    System.out.println("Resource JSON: \n" + resourceJson.toPrettyString());
    System.out.println("\nURL JSON: \n" + urlJson.toPrettyString());
    System.out.println("\nFile JSON: \n" + fileJson.toPrettyString());
}
```