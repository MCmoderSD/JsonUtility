package de.MCmoderSD.main;

import de.MCmoderSD.utilities.json.JsonNode;
import de.MCmoderSD.utilities.json.JsonUtility;

public class Main {
    public static void main(String[] args) {

        JsonUtility jsonUtility = new JsonUtility();

        JsonNode jsonNode = jsonUtility.load("/languages/en.json");

        System.out.println(jsonNode.get("title").asText());
        System.out.println(jsonNode.get("join").asText());
    }
}