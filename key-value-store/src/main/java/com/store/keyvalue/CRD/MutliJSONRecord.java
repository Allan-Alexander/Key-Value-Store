package com.store.keyvalue.CRD;

import ch.qos.logback.core.BasicStatusManager;
import com.store.keyvalue.Model.Employee;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MutliJSONRecord {
    public static HashSet<String> hashset = new HashSet<>();
    private static JSONArray empList = new JSONArray();
    public MutliJSONRecord() throws IOException {

        new File("C:\\Store").mkdir();
        File f = new File("C:\\Store\\employee.json");

            if(f.length()==0){
                System.out.println("File is Empty");
                return;
            }

        if((int)getFileSizeMegaBytes(f)>(1024*1024)){
            System.out.println("Memory exceeded 1GB");
            return;
        }

        long fileSize = f.length();
        JSONParser jsonParser = new JSONParser();
        FileReader reader;

        {
            try {
                reader = new FileReader("C:\\Store\\employee.json");
                Object obj = jsonParser.parse(reader);
                JSONArray empList = (JSONArray) obj;
                // System.out.println(empList);

                //Iterate over array
                empList.forEach(emp->readEmpObj((JSONObject) emp));

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Key: "+this.hashset);
    }

    public static void readEmpObj(JSONObject emp){
        emp.keySet().forEach(keyStr->
        {
            hashset.add((String)keyStr);
            empList.add(emp);

        });
    }

    public void Create() {
        Employee E = new Employee();
        Scanner sc = new Scanner(System.in);

        System.out.println("Unique Id: ");
        String id = sc.nextLine();
            if(hashset.contains(id)){
                System.out.println("Id Exists");
                return;
            }else{
            this.hashset.add(id);
            E.setId(id);
            }


        System.out.println("Name: ");
        E.setName(sc.nextLine());

        System.out.println("Address: ");
        E.setAddress(sc.nextLine());

        System.out.println("Email: ");
        E.setEmail(sc.nextLine());

        System.out.println("Designation: ");
        E.setDesignation(sc.nextLine());

        JSONObject emps = new JSONObject();
        emps.put("name",E.getName());
        emps.put("address",E.getAddress());
        emps.put("email",E.getEmail());
        emps.put("designation",E.getDesignation());

        JSONObject empObj = new JSONObject();
        empObj.put(E.getId(),emps);


        empList.add(empObj);


        try(FileWriter file = new FileWriter("C:\\Store\\employee.json")){
            file.write(empList.toJSONString());
            file.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private static int getFileSizeMegaBytes(File file) {
        return (int) file.length() / (1024 * 1024) ;
    }
}
