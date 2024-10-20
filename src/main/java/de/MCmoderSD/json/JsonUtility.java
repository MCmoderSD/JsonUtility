package de.MCmoderSD.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.util.HashMap;

@SuppressWarnings("ALL")
public class JsonUtility {

    // Attributes
    private final ObjectMapper objectMapper;
    private final HashMap<String, JsonNode> jsonCache;

    // Default constructor
    public JsonUtility() {

        // Initialize JSON cache
        objectMapper = new ObjectMapper();
        
        // Initialize JSON cache
        jsonCache = new HashMap<>();
    }
    
    public JsonUtility(ObjectMapper objectMapper) {

        // Set ObjectMapper
        this.objectMapper = objectMapper;

        // Initialize JSON cache
        jsonCache = new HashMap<>();
    }
    
    public JsonUtility(HashMap<String, JsonNode> jsonCache) {

        // Initialize JSON cache
        objectMapper = new ObjectMapper();

        // Set JSON cache
        this.jsonCache = jsonCache;
    }
    
    public JsonUtility(ObjectMapper objectMapper, HashMap<String, JsonNode> jsonCache) {

        // Set ObjectMapper
        this.objectMapper = objectMapper;

        // Set JSON cache
        this.jsonCache = jsonCache;
    }

    // Methods
    public JsonNode load(String path) {
        if (jsonCache.containsKey(path)) return jsonCache.get(path);
        try {

            // Load JSON from resources folder in JAR
            InputStream inputStream = getClass().getResourceAsStream(path);
            JsonNode jsonNode = objectMapper.readTree(inputStream);

            // Cache JSON
            jsonCache.put(path, jsonNode);
            return jsonNode; // Return JSON
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public JsonNode load(String path, boolean isAbsolute) {
        if (jsonCache.containsKey(path)) return jsonCache.get(path);
        if (!isAbsolute) return load(path);
        else {
            try {
                
                // Load JSON from absolute path
                File file = new File(path);
                JsonNode jsonNode = objectMapper.readTree(file);

                // Cache JSON
                jsonCache.put(path, jsonNode);
                return jsonNode; // Return JSON
            } catch (IOException e) {
                throw new RuntimeException("Error loading JSON from absolute path: " + e.getMessage());
            }
        }
    }

    // Clear
    public void clear() {
        jsonCache.clear();
    }
    
    // Remove
    public void remove(String path) {
        jsonCache.remove(path);
    }
    
    // Contains
    public boolean contains(String path) {
        return jsonCache.containsKey(path);
    }
    
    // Get
    public JsonNode get(String path) {
        return jsonCache.get(path);
    }
    
    // Size
    public int size() {
        return jsonCache.size();
    }
    
    // Is Empty
    public boolean isEmpty() {
        return jsonCache.isEmpty();
    }
    
    // Get JSON Cache
    public HashMap<String, JsonNode> getJsonCache() {
        return jsonCache;
    }
    
    // Get Object Mapper
    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}