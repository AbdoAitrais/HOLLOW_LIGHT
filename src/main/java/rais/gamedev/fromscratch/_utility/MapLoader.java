package rais.gamedev.fromscratch._utility;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MapLoader {
    public int width,height;
    public int[] baseLayerTiles;
    public int[] secondLayerTiles;

    public MapLoader(String path) {
        loadJSON(path);
    }

    private void loadJSON(String path) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(path)));
            JSONObject jsonObject = new JSONObject(content);
            width = (int) jsonObject.get("width");
            height = (int) jsonObject.get("height");
            this.baseLayerTiles = new int[height * width];
            if (jsonObject.getJSONArray("layers").isEmpty()) {
                System.out.println("No layers detectd");
                return;
            }
            JSONArray baseLayer =  jsonObject.getJSONArray("layers").getJSONObject(0).getJSONArray("data");
            parseTiles(baseLayer,baseLayerTiles);
            if (jsonObject.getJSONArray("layers").length() < 2) {
                System.out.println("No object layer detected ");
                return;
            }
            secondLayerTiles = new int[width * height];
            JSONArray objectLayer = jsonObject.getJSONArray("layers").getJSONObject(1).getJSONArray("data");

            parseTiles(objectLayer, secondLayerTiles);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new MapLoader("src/main/resources/maps/SimpleMap.tmj");
    }

    private void parseTiles(JSONArray jsonArray, int[] layer) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                layer[x + y * width] = jsonArray.getInt(x + y * width);
            }
        }
    }
}
