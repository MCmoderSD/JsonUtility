package de.MCmoderSD.main;

import de.MCmoderSD.utilities.json.JsonNode;
import de.MCmoderSD.utilities.json.JsonUtility;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        JsonUtility jsonUtility = new JsonUtility();

        ArrayList<String> list = new ArrayList<>();

        JsonNode jsonNode = jsonUtility.load("/languages/en.json");
        list.add(jsonNode.get("title").asText());
        list.add(jsonNode.get("invalidMove").asText());
        list.add(jsonNode.get("invalidObstacle").asText());
        list.add(jsonNode.get("catWon").asText());
        list.add(jsonNode.get("catcherWon").asText());
        list.add(jsonNode.get("catIsNotOnMove").asText());
        list.add(jsonNode.get("catcherIsNotOnMove").asText());
        list.add(jsonNode.get("triesLeft").asText());
        list.add(jsonNode.get("catIsOnMove").asText());
        list.add(jsonNode.get("catcherIsOnMove").asText());
        list.add(jsonNode.get("host").asText());
        list.add(jsonNode.get("join").asText());
        list.add(jsonNode.get("roomID").asText());
        list.add(jsonNode.get("restart").asText());

        String l = "";

        for (String s : list) l = l + s;

        System.out.println(l);
    }
}