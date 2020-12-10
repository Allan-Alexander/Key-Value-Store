/**
 * By Allan Saji Alexander
 * Key-Value Store
 */

package com.store.keyvalue;
import com.store.keyvalue.CRD.MultiJSONDelete;
import com.store.keyvalue.CRD.MultiJSONRead;
import com.store.keyvalue.CRD.MutliJSONRecord;
import java.io.IOException;
import java.util.Scanner;

public class Store {


    public static void main(String[] args) throws IOException {
        /**
         * 3 file are used
         * Create
         * Read
         * Delete
         */
        Scanner sc = new Scanner(System.in);
        MutliJSONRecord C = new MutliJSONRecord();
        MultiJSONRead   R = new MultiJSONRead();
        MultiJSONDelete D = new MultiJSONDelete();

        /**
         * Option to select CRD.
         */
        char conti = 'y';
        int choice = 1;
        do{
            System.out.println("1. CREATE a JSON OBJECT");
            System.out.println("2. READ a JSON OBJECT");
            System.out.println("3. DELETE a JSON OBJECT");
            System.out.println("-1 to exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1: {
                    C.Create();
                    break;
                }
                case 2: {
                    System.out.println("Enter the ID of the Employee: ");
                    sc.nextLine();
                    String ID = sc.nextLine();
                    R.Read(ID);
                    System.out.println();
                    break;
                }
                case 3:{
                    System.out.println("Enter the ID of the Employee to delete: ");
                    sc.nextLine();
                    String deleteID = sc.nextLine();
                    D.Delete(deleteID);
                    break;
                }
                case -1:{
                    System.out.println("JSON file saved in \"C:\\Store\\employee.json\" directory");
                    System.out.println("Thank You");
                    break;
                }
            }
            System.out.print("Do you wish to continue (y/n)? -> ");
            conti = sc.next().charAt(0);
            System.out.println();
            }while(conti=='y'||conti=='Y');
    }
}
