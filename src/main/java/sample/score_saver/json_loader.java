package sample.score_saver;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class json_loader {
    public static void load_json (List<String>names,List<Integer> score){
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("src/main/resources/score.json"));
            JSONObject jsonobject =(JSONObject)obj;
            score.add((Integer) jsonobject.get("score"));
            Integer ink=(Integer) jsonobject.get("score");
            names.add((String)jsonobject.get("name"));
            String m =(String)jsonobject.get("name");
            System.out.println(m+ink);
        }
        catch (FileNotFoundException e){e.printStackTrace();}
        catch (IOException e){e.printStackTrace();}
        catch (ParseException e){e.printStackTrace();}
    }


}

