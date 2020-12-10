/**
 * Read Class
 *
 * This class reads based on the key provided by the user
 * and display the value corresponding to the key
 *
 */
package com.store.keyvalue.CRD;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MultiJSONRead {
    public static String Id;
    public void Read(String id) {

        /**
         * To check if file exists . And if it exists
         * check the size of the file.
         * if empty exit the class, else proceed
         */

        new File("C:\\Store").mkdir();
        File f = new File("C:\\Store\\employee.json");
        if(f.length()==0){
            System.out.println("File is Empty");
            return;
        }
        this.Id = id;

    JSONParser jsonParser = new JSONParser();

    FileReader reader;

    {
        try {
            reader = new FileReader("C:\\Store\\employee.json");
            Object obj = jsonParser.parse(reader);
            JSONArray empList = (JSONArray) obj;
           // System.out.println(empList);


            //Iterate over array
            empList.forEach(emp->parseEmpObj((JSONObject) emp));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
         }
      }
   }

   private static void parseEmpObj(JSONObject emp){


        emp.keySet().forEach(keyStr->
        {
            if(keyStr.equals(Id)) {
                Object keyValue = emp.get(keyStr);
                System.out.println(keyStr + " : " + keyValue);
            }
        });
    }
}
