package com.store.keyvalue.CRD;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class MultiJSONDelete {
    private static JSONArray empList = new JSONArray();
    private static String Id ;
    public static void Delete(String id) {

        new File("C:\\Store").mkdir();
        File f = new File("C:\\Store\\employee.json");
        if(f.length()==0){
            System.out.println("File is Empty");
            return;
        }

        Id = id;

        JSONParser jsonParser = new JSONParser();

        FileReader reader;

        {
            try {
                reader = new FileReader("C:\\Store\\employee.json");
                Object obj = jsonParser.parse(reader);
                JSONArray empList = (JSONArray) obj;
                // System.out.println(empList);


                //Iterate over array
                empList.forEach(emp->new MultiJSONDelete().deleteEmpObj((JSONObject) emp));

                new MultiJSONDelete().save();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    private void save() {
        try(FileWriter file = new FileWriter("C:\\Store\\employee.json")){
            file.write(this.empList.toString());
            file.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void deleteEmpObj(JSONObject emp){


        emp.keySet().forEach(keyStr->
        {
            if(keyStr.equals(Id)) {
                Object keyValue = emp.get(keyStr);
                System.out.println(keyStr + " deleted !" );
            }else{
            this.empList.add(emp);
        }
        });

    }

}
