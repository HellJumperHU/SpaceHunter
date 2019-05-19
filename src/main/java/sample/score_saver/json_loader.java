package sample.score_saver;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * JSON FileReader
 */
public class json_loader {
    /**
     * Read the JSON file and required 2 LISTS as parameters
     * @param names its a String List
     * @param score Its a Long List
     */
    public static void load_json (List<String>names,List<Long> score){
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("./src/main/resources/score.json"));
            JSONObject jsonobject =(JSONObject)obj;
            score.add((Long) jsonobject.get("Score"));
            Long ink=(Long) jsonobject.get("Score");
            names.add((String)jsonobject.get("Pilot name"));
            String m =(String)jsonobject.get("Pilot name");
            //System.out.println(m+ink);
        }
        catch (FileNotFoundException e){e.printStackTrace();}
        catch (IOException e){e.printStackTrace();}
        catch (ParseException e){e.printStackTrace();}
    }


    /**
    * EXPERIMENTAL
    */
    /*public static void show()
    {
        JSONParser parser = new JSONParser();
        try
        {
            Object object = parser
                    .parse(new FileReader("./src/main/resources/score.json"));

            //convert Object to JSONObject
            JSONObject jsonObject = (JSONObject)object;

            //Reading the String
            String name = (String) jsonObject.get("Pilot name");
            //String age = (String) jsonObject.get("Score");


            //Printing all the values
            System.out.println("Name: " + name);
            //System.out.println("Age: " + age);

        }
        catch(FileNotFoundException fe)
        {
            fe.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }*/
}




