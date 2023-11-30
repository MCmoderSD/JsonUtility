package de.MCmoderSD.main;

import de.MCmoderSD.utilities.json.JsonNode;
import de.MCmoderSD.utilities.json.JsonUtility;

import java.awt.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        JsonUtility jsonUtility = new JsonUtility();

        ArrayList<String> list = new ArrayList<>();

        JsonNode jsonNode = jsonUtility.load("/languages/en.json");

        jsonNode.set("red", new Color(255, 0, 0));


        System.out.println(jsonNode.get("red").asColor());

        String l = "";

        for (String s : list) l = l + s;

        System.out.println(l);
    }
}