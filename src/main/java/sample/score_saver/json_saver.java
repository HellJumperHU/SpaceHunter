package sample.score_saver;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class json_saver {
    public static void write_json(String name, Integer score) {
        JSONObject obj = new JSONObject();
        obj.put("Pilot name", name);
        obj.put("Score", score);

        try (FileWriter file = new FileWriter("scores/")) {
                file.write(obj.toString());
                file.flush();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        System.out.println(obj);


    }

    public static void writeJsonSimpleDemo(String filename) throws Exception {
        JSONObject sampleObject = new JSONObject();
        sampleObject.put("name", "Stackabuser");
        sampleObject.put("age", 35);

        JSONArray messages = new JSONArray();
        messages.add("Hey!");
        messages.add("What's up?!");

        sampleObject.put("messages", messages);
        Files.write(Paths.get(filename), sampleObject.toJSONString().getBytes());
    }

}
