package de.MCmoderSD.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.HashMap;

/**
 * A utility class for loading and caching JSON data.
 * This class provides methods to load JSON from various sources,
 * including local files and URLs. It also caches loaded JSON to
 * improve performance on subsequent requests.
 */
@SuppressWarnings("ALL")
public class JsonUtility {

    /**
     * A cache to store loaded JSON nodes mapped by their path.
     */
    private final HashMap<String, JsonNode> cache = new HashMap<>();

    /**
     * The ObjectMapper instance used for reading JSON data.
     */
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Loads a JSON node from the specified path.
     * This method first checks if the JSON is already cached.
     * If not, it attempts to load it from the specified path.
     *
     * @param path the path to the JSON file or URL
     * @return the loaded JsonNode
     * @throws IOException if there is an error reading the JSON
     * @throws URISyntaxException if the path is not a valid URI
     */
    public JsonNode load(String path) throws IOException, URISyntaxException {
        return load(path, false);
    }

    /**
     * Loads a JSON node from the specified path, with an option to specify
     * whether the path is absolute.
     *
     * @param path the path to the JSON file or URL
     * @param isAbsolute indicates if the path is an absolute path
     * @return the loaded JsonNode
     * @throws IOException if there is an error reading the JSON
     * @throws URISyntaxException if the path is not a valid URI
     */
    public JsonNode load(String path, boolean isAbsolute) throws IOException, URISyntaxException {

        // Check path
        if (path == null || path.isEmpty() || path.isBlank()) throw new RuntimeException("Path is empty!");

        // Check if JSON is already in cache
        if (cache.containsKey(path)) return cache.get(path);

        // Load JSON
        JsonNode json = loadJson(path, isAbsolute);

        // Check if JSON is null
        if (json == null) throw new RuntimeException("Failed to load JSON: " + path);

        // Cache JSON
        cache.put(path, json);
        return json;
    }

    /**
     * Loads a JSON node from the specified path, with an option to specify
     * whether the path is absolute.
     *
     * @param path the path to the JSON file or URL
     * @param isAbsolute indicates if the path is an absolute path
     * @return the loaded JsonNode
     * @throws IOException if there is an error reading the JSON
     * @throws URISyntaxException if the path is not a valid URI
     */
    public static JsonNode loadJson(String path, boolean isAbsolute) throws IOException, URISyntaxException {

        // Check path
        if (path == null || path.isEmpty() || path.isBlank()) throw new RuntimeException("Path is empty!");

        // Load JSON
        if (isAbsolute) return objectMapper.readTree(new File(path));
        else if (path.startsWith("http://") || path.startsWith("https://")) return objectMapper.readTree(new URI(path).toURL().openStream());
        else return objectMapper.readTree(JsonUtility.class.getResourceAsStream(path));
    }

    /**
     * Adds a JSON node to the cache with the specified path.
     *
     * @param path the path to associate with the JSON node
     * @param jsonNode the JSON node to cache
     */
    public void add(String path, JsonNode jsonNode) {
        cache.put(path, jsonNode);
    }

    /**
     * Replaces the JSON node associated with the specified path in the cache.
     *
     * @param path the path whose associated JSON node will be replaced
     * @param jsonNode the new JSON node to associate with the path
     */
    public void replace(String path, JsonNode jsonNode) {
        cache.replace(path, jsonNode);
    }

    /**
     * Removes the JSON node associated with the specified path from the cache.
     *
     * @param path the path whose associated JSON node will be removed
     */
    public void remove(String path) {
        cache.remove(path);
    }

    /**
     * Removes the JSON node from the cache that is equal to the specified JSON node.
     *
     * @param jsonNode the JSON node to remove from the cache
     */
    public void remove(JsonNode jsonNode) {
        cache.remove(get(jsonNode));
    }

    /**
     * Clears all entries from the cache.
     */
    public void clear() {
        cache.clear();
    }

    /**
     * Retrieves the cache of JSON nodes.
     *
     * @return the cache containing the loaded JSON nodes
     */
    public HashMap<String, JsonNode> get() {
        return cache;
    }

    /**
     * Retrieves the JSON node associated with the specified path from the cache.
     *
     * @param path the path to look up in the cache
     * @return the associated JsonNode, or null if not found
     */
    public JsonNode get(String path) {
        if (contains(path)) return cache.get(path);
        else return null;
    }

    /**
     * Retrieves the path associated with the specified JSON node.
     *
     * @param jsonNode the JSON node to look up in the cache
     * @return the path associated with the JSON node, or null if not found
     */
    public String get(JsonNode jsonNode) {
        if (contains(jsonNode)) for (String path : cache.keySet()) if (cache.get(path).equals(jsonNode)) return path;
        return null;
    }

    /**
     * Checks if the cache contains a JSON node associated with the specified path.
     *
     * @param path the path to check in the cache
     * @return true if the cache contains the specified path, false otherwise
     */
    public boolean contains(String path) {
        return cache.containsKey(path);
    }

    /**
     * Checks if the cache contains the specified JSON node.
     *
     * @param jsonNode the JSON node to check in the cache
     * @return true if the cache contains the specified JSON node, false otherwise
     */
    public boolean contains(JsonNode jsonNode) {
        return cache.containsValue(jsonNode);
    }

    /**
     * Checks if the cache is empty.
     *
     * @return true if the cache is empty, false otherwise
     */
    public boolean isEmpty() {
        return cache.isEmpty();
    }

    /**
     * Returns the number of JSON nodes currently in the cache.
     *
     * @return the size of the cache
     */
    public int size() {
        return cache.size();
    }
}