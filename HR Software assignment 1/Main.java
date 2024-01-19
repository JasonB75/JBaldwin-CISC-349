import java.util.Scanner;  // Import the Scanner class
import java.util.ArrayList;


class Main {

  static Scanner inputScan = new Scanner(System.in);  // Create a Scanner object
  static int inputInt = 0; // The base string for input

  static ArrayList part_time_employee_list = new ArrayList(); // Array lists used to hold the different employee types
  static ArrayList full_time_employee_list = new ArrayList();
  static ArrayList contract_employee_list = new ArrayList();
  
  public static void main(String[] args) {
    inputScan.useDelimiter(",|\\n");  // Split input using commas;

    //testPeoples(); // uncomment to automatically add in 2 employyees in each type
    
    while (inputInt != -1){ // Keep looping through the UI till -1 is entered
      
    System.out.println("\n\n Would you like to: \n1. add an employee \n2. view employees \n3. delete an employee? \n (Enter a number to continue, -1 to exit)\n");

    inputInt = inputScan.nextInt();  // Read user input for the above prompt and make an action
    if (inputInt == 1){
      new_employee();
      inputInt = 0;
    }
    else if (inputInt == 2){
      view_employee();
      inputInt = 0;
    }
    else if (inputInt == 3){
      delete_employee();
      inputInt = 0;
    }
    }
  }
  
  public static void new_employee(){
    System.out.println("\n Their name:");
    String name_string = inputScan.next();

    System.out.println("\n Their ssn:");
    int ssn_int = inputScan.nextInt();

    System.out.println("\n Their job title:");
    String title_string = inputScan.next();
    
    System.out.println("\n Are they, 1. salaried full time 2. salaried part time 3. a contractor? (enter number)");
    int type_int = inputScan.nextInt();

    if (type_int == 1){
      System.out.println("\n Their salary:");
      int salary_int = inputScan.nextInt();
      FT_Salary temp_employee = new FT_Salary(name_string, title_string, ssn_int, salary_int);
      full_time_employee_list.add(temp_employee);
    }
    else if (type_int == 2){
      System.out.println("\n Their salary:");
      int salary_int = inputScan.nextInt();
      System.out.println("\n How many hours do they work a week:");
      int hours_int = inputScan.nextInt();
      PT_Salary temp_employee = new PT_Salary(name_string, title_string, ssn_int, salary_int, hours_int);
      part_time_employee_list.add(temp_employee);
    }
    else if (type_int == 3){
      System.out.println("\n Their hourly rate:");
      int hourly_int = inputScan.nextInt();
      Contractor temp_employee = new Contractor(name_string, title_string, ssn_int, hourly_int);
      contract_employee_list.add(temp_employee);
    }
    System.out.println("\n Done! \n");
  } 
  

  public static void view_employee(){
    System.out.println("\n View: 1. salaried full time 2. salaried part time 3. a contractor (enter number)");
    int type_int = inputScan.nextInt();

    if (type_int == 1){
      for (int i = 0; i < full_time_employee_list.size(); i++){
        System.out.println(i + ". " + full_time_employee_list.get(i).toString());
      }
    }
    if (type_int == 2){
      for (int i = 0; i < part_time_employee_list.size(); i++){
        System.out.println(i + ". " + part_time_employee_list.get(i).toString());
      }
    }
    if (type_int == 3){
      for (int i = 0; i < contract_employee_list.size(); i++){
        System.out.println(i + ". " + contract_employee_list.get(i).toString());
      }
    }
  }

  public static void delete_employee(){
      System.out.println("\n View to delete: 1. salaried full time 2. salaried part time 3. a contractor (enter number)");
      int type_int = inputScan.nextInt();

      if (type_int == 1){
        for (int i = 0; i < full_time_employee_list.size(); i++){
          System.out.println(i + ". " + full_time_employee_list.get(i).toString());
        }
        System.out.println("\n Which employee would you like to delete? (enter number)");
        int delete_int = inputScan.nextInt();
        full_time_employee_list.remove(delete_int);
      }
      if (type_int == 2){
        for (int i = 0; i < part_time_employee_list.size(); i++){
          System.out.println(i + ". " + part_time_employee_list.get(i).toString());
        }
        System.out.println("\n Which employee would you like to delete? (enter number)");
        int delete_int = inputScan.nextInt();
        part_time_employee_list.remove(delete_int);
      }
      if (type_int == 3){
        for (int i = 0; i < contract_employee_list.size(); i++){
          System.out.println(i + ". " + contract_employee_list.get(i).toString());
        }
        System.out.println("\n Which employee would you like to delete? (enter number)");
        int delete_int = inputScan.nextInt();
        contract_employee_list.remove(delete_int);
      }
    }


    
  public static void testPeoples(){

    FT_Salary FT_employee = new FT_Salary("Bob", "Janitor", 1234567890, 100);
    full_time_employee_list.add(FT_employee);
    FT_employee = new FT_Salary("BIll", "Janitor", 1234567890, 130);
    full_time_employee_list.add(FT_employee);

    PT_Salary pt_employee = new PT_Salary("B0ob", "Janitor", 1234567890, 100, 25);
    part_time_employee_list.add(pt_employee);
    pt_employee = new PT_Salary("Bill", "Janitor", 1234567890, 100, 30);
    part_time_employee_list.add(pt_employee);

    Contractor temp_employee = new Contractor("Baob", "Janitor", 1234567890, 100);
    contract_employee_list.add(temp_employee);
    temp_employee = new Contractor("Bill", "Janitor", 1234567890, 10);
    contract_employee_list.add(temp_employee);
  }
    
  }
