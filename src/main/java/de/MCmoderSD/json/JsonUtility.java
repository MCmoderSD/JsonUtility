package de.MCmoderSD.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Utility class for loading, caching, and managing JSON files.
 */
@SuppressWarnings("ALL")
public class JsonUtility {

    // Singleton instance
    private static JsonUtility instance;

    // Attributes
    private final ConcurrentHashMap<String, JsonNode> cache;
    private final ObjectMapper objectMapper;

    /**
     * Private constructor to initialize the cache and ObjectMapper.
     */
    private JsonUtility() {
        cache = new ConcurrentHashMap<>();
        objectMapper = new ObjectMapper();
    }

    /**
     * Returns the singleton instance of JsonUtility.
     * @return the singleton instance
     */
    public static JsonUtility getInstance() {
        if (instance == null) instance = new JsonUtility();
        return instance;
    }

    /**
     * Loads a JSON from the specified path.
     * @param path the path to the JSON
     * @return the JsonNode object
     * @throws IOException if an I/O error occurs
     * @throws URISyntaxException if the path is invalid
     */
    public JsonNode load(String path) throws IOException, URISyntaxException {
        return load(path, false);
    }

    /**
     * Loads a JSON from the specified path, with option to treat it as absolute.
     * @param path the path to the JSON
     * @param isAbsolute true if the path is absolute, false if resource path
     * @return the JsonNode object
     * @throws IOException if an I/O error occurs
     * @throws URISyntaxException if the path is invalid
     */
    public JsonNode load(String path, boolean isAbsolute) throws IOException, URISyntaxException {

        // Check Path
        if (path.isBlank()) throw new IllegalArgumentException("Path cannot be blank");

        // Check Cache
        if (cache.containsKey(path)) return cache.get(path);

        // Load JSON
        JsonNode json = reload(path, isAbsolute);

        // Check if JSON is null
        if (json == null) throw new RuntimeException("Failed to load JSON: " + path);
        else cache.put(path, json);

        // Return loaded JSON
        return json;
    }

    /**
     * Reloads a JSON from the specified path.
     * @param path the path to the JSON
     * @return the JsonNode object
     * @throws IOException if an I/O error occurs
     * @throws URISyntaxException if the path is invalid
     */
    public JsonNode reload(String path) throws IOException, URISyntaxException {
        return reload(path, false);
    }

    /**
     * Reloads a JSON from the specified path, with option to treat it as absolute.
     * @param path the path to the JSON
     * @param isAbsolute true if the path is absolute, false if resource path
     * @return the JsonNode object
     * @throws IOException if an I/O error occurs
     * @throws URISyntaxException if the path is invalid
     */
    public JsonNode reload(String path, boolean isAbsolute) throws IOException, URISyntaxException {

        // Check Path
        if (path.isBlank()) throw new IllegalArgumentException("Path cannot be blank");

        // Load JSON
        if (isAbsolute) return objectMapper.readTree(new File(path));
        else if (path.startsWith("http://") || path.startsWith("https://")) return objectMapper.readTree(new URI(path).toURL().openStream());
        else return objectMapper.readTree(JsonUtility.class.getResourceAsStream(path));
    }

    /**
     * Adds a new entry to the cache.
     * @param path the key
     * @param jsonNode the value
     * @return the previous value associated with the key
     */
    public JsonNode add(String path, JsonNode jsonNode) {
        return cache.put(path, jsonNode);
    }

    /**
     * Replaces an entry in the cache.
     * @param path the key
     * @param jsonNode the new value
     * @return the old value replaced
     */
    public JsonNode replace(String path, JsonNode jsonNode) {
        return cache.replace(path, jsonNode);
    }

    /**
     * Removes an entry from the cache by path.
     * @param path the key
     * @return the removed JsonNode
     */
    public JsonNode remove(String path) {
        return cache.remove(path);
    }

    /**
     * Removes an entry from the cache by JsonNode.
     * @param jsonNode the value to remove
     * @return the removed JsonNode
     */
    public JsonNode remove(JsonNode jsonNode) {
        return cache.remove(get(jsonNode));
    }

    /**
     * Clears all cached entries.
     */
    public void clear() {
        cache.clear();
    }

    /**
     * Returns the full cache.
     * @return the cache map
     */
    public ConcurrentHashMap<String, JsonNode> get() {
        return cache;
    }

    /**
     * Gets the JsonNode by path.
     * @param path the key
     * @return the associated JsonNode or null if not found
     */
    public JsonNode get(String path) {
        if (contains(path)) return cache.get(path);
        else return null;
    }

    /**
     * Gets the path associated with the JsonNode.
     * @param jsonNode the value
     * @return the associated path or null
     */
    public String get(JsonNode jsonNode) {
        if (contains(jsonNode)) for (String path : cache.keySet()) if (cache.get(path).equals(jsonNode)) return path;
        return null;
    }

    /**
     * Checks if the path exists in the cache.
     * @param path the key
     * @return true if exists, false otherwise
     */
    public boolean contains(String path) {
        return cache.containsKey(path);
    }

    /**
     * Checks if the JsonNode exists in the cache.
     * @param jsonNode the value
     * @return true if exists, false otherwise
     */
    public boolean contains(JsonNode jsonNode) {
        return cache.containsValue(jsonNode);
    }

    /**
     * Checks if the cache is empty.
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return cache.isEmpty();
    }

    /**
     * Returns the size of the cache.
     * @return the number of cached entries
     */
    public int size() {
        return cache.size();
    }
}