package com.e.praca;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;


public class JsonHelper {

    public String getJSON(String turl)
    {
        String str = new String();
        try {
            URL url = new URL(turl);
            Scanner scan = new Scanner(url.openStream());
            while (scan.hasNext())
                str += scan.nextLine();
            scan.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return str;
    }

    public JSONArray getJSONArray(String str)
    {
        JSONParser parser = new JSONParser();
        JSONArray a = null;
        try{
            a = (JSONArray) parser.parse(str);
        }
        catch (ParseException e){
            e.printStackTrace();
        }
        return a;
    }


    public ArrayList<Label> getLabelsFromJSONArray(String jsonString)
    {
        JSONArray array = getJSONArray(jsonString);
        ArrayList<Label> al = new ArrayList();
        for (Object o : array) {
            JSONObject obj = (JSONObject) o;
            String name = obj.get("name").toString();
            long id = (long) obj.get("id");
            Label tLabel = new Label(name, id);
            al.add(tLabel);
        }
        return al;
    }

    public ArrayList<Task> getTasksFromJSONArray(String jsonString)
    {
        JSONArray array = getJSONArray(jsonString);
        ArrayList<Task> al = new ArrayList();
        for (Object o : array) {
            JSONObject obj = (JSONObject) o;
            String name = obj.get("name").toString();
            long id = (long) obj.get("id");
            Task tTask = new Task(name, id);
            al.add(tTask);
        }
        return al;
    }

    public ArrayList<LabelTask> getLabelTaskFromJSONArray(String jsonString)
    {
        JSONArray array = getJSONArray(jsonString);
        ArrayList<LabelTask> al = new ArrayList();
        for (Object o : array) {
            JSONObject obj = (JSONObject) o;
            long taskId = (long) obj.get("id");
            JSONArray jsonArray = (JSONArray) obj.get("label_ids");
            Iterator<Long> iterator = jsonArray.iterator();
            while (iterator.hasNext())
            {
                LabelTask tmp = new LabelTask(iterator.next(),taskId);
                al.add(tmp);
            }
        }
        return al;
    }




}
