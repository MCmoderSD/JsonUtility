# Json Utility

## Description

A simple utility to read json files and convert them into a JsonNode.

## Usage

### Maven
```xml
<dependencies>
    <dependency>
        <groupId>de.MCmoderSD</groupId>
        <artifactId>json</artifactId>
        <version>1.0.0</version>
    </dependency>
</dependencies>
```

### Usage Example
```java
import com.fasterxml.jackson.databind.JsonNode;

import de.MCmoderSD.json.JsonUtility;

public class Main {
    public static void main(String[] args) {
        
        // Create new instance of JsonUtility
        JsonUtility jsonUtility = new JsonUtility();
        
        // Load from resources folder
        JsonNode resource = jsonUtility.load("/config.json");
        
        // Load from absolute path
        JsonNode absolute = jsonUtility.load("C:/Users/username/Desktop/config.json");
    }
}
```