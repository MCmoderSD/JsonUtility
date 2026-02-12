package de.MCmoderSD.json;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings("unused")
public class JsonUtility {

    // Singleton instance
    private static JsonUtility instance;

    // Attributes
    private final ConcurrentHashMap<String, JsonNode> cache;
    private final ObjectMapper objectMapper;

    // Constructor
    private JsonUtility() {
        cache = new ConcurrentHashMap<>();
        objectMapper = new ObjectMapper();
    }

    // Get Singleton Instance
    public static JsonUtility getInstance() {
        if (instance == null) instance = new JsonUtility();
        return instance;
    }

    // Read Methods
    private JsonNode readResource(String resourcePath) {

        // Load JSON from Resource
        try (var resource = JsonUtility.class.getResourceAsStream(resourcePath)) {

            // Check if resource exists
            if (resource == null) throw new IOException("Resource not found: " + resourcePath);

            // Parse and return JSON
            return objectMapper.readTree(resource);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load JSON from resource: " + resourcePath, e);
        }
    }

    private JsonNode readURL(String url) {

        // Load JSON from URL
        try (var stream = new URI(url).toURL().openStream()) {
            return objectMapper.readTree(stream);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException("Failed to load JSON from URL: " + url, e);
        }
    }

    private JsonNode readFile(String filePath) {

        // Load JSON from File
        File file = new File(filePath);

        // Check if file exists
        if (!file.exists()) throw new RuntimeException("File not found: " + filePath);

        // Parse and return JSON
        return objectMapper.readTree(file);
    }

    // Load Methods
    public JsonNode loadResource(String resourcePath) {

        // Check Parameters
        if (resourcePath == null || resourcePath.isBlank()) throw new IllegalArgumentException("Resource path cannot be null or blank");

        // Check Cache
        if (cache.containsKey(resourcePath)) return cache.get(resourcePath);

        // Load JSON and cache it
        JsonNode jsonNode = readResource(resourcePath);

        // Check if JSON was loaded successfully
        if (jsonNode == null) throw new RuntimeException("Failed to load JSON from resource: " + resourcePath);

        // Cache
        cache.put(resourcePath, jsonNode);

        // Return JSON
        return jsonNode;
    }

    public JsonNode loadURL(String url) {

        // Check Parameters
        if (url == null || url.isBlank()) throw new IllegalArgumentException("URL cannot be null or blank");

        // Check Cache
        if (cache.containsKey(url)) return cache.get(url);

        // Load JSON and cache it
        JsonNode jsonNode = readURL(url);

        // Check if JSON was loaded successfully
        if (jsonNode == null) throw new RuntimeException("Failed to load JSON from URL: " + url);

        // Cache
        cache.put(url, jsonNode);

        // Return JSON
        return jsonNode;
    }

    public JsonNode loadFile(String filePath) {

        // Check Parameters
        if (filePath == null || filePath.isBlank()) throw new IllegalArgumentException("File path cannot be null or blank");

        // Check Cache
        if (cache.containsKey(filePath)) return cache.get(filePath);

        // Load JSON and cache it
        JsonNode jsonNode = readFile(filePath);

        // Check if JSON was loaded successfully
        if (jsonNode == null) throw new RuntimeException("Failed to load JSON from file: " + filePath);

        // Cache
        cache.put(filePath, jsonNode);

        // Return JSON
        return jsonNode;
    }

    // Reload Methods
    public JsonNode reloadResource(String resourcePath) {
        cache.remove(resourcePath);
        return loadResource(resourcePath);
    }

    public JsonNode reloadURL(String url) {
        cache.remove(url);
        return loadURL(url);
    }

    public JsonNode reloadFile(String filePath) {
        cache.remove(filePath);
        return loadFile(filePath);
    }

    // Setter
    public void clear() {
        cache.clear();
    }

    // Getter
    public HashMap<String, JsonNode> getCache() {
        return new HashMap<>(cache);
    }

    public int size() {
        return cache.size();
    }

    public boolean isEmpty() {
        return cache.isEmpty();
    }
}