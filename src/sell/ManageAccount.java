
package sell;

import java.io.FileNotFoundException;     
import java.util.Formatter;               
import java.util.FormatterClosedException;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.NoSuchElementException;

public class ManageAccount {
    public static int ch1;
    public static String email;
    public static String password;
    public static void main(String[] args){
       
       Scanner E = new Scanner(System.in);
       System.out.println("Enter your email please");
       email = E.nextLine();
       if (ch1 == 1){
           Read.Read();
           
           System.out.println("Enter your password please");
           String input = E.nextLine();
           input = input + " ";
           
           if (input.equals(password)) {
               System.out.println("welcome!");
           }
           else {
               System.out.println("password is incorrect.Sorry but we cant let you use the program!");
               System.exit(0);
           }
       }
       else {
           CreateTextFile.Create();
           System.out.println("Welcome to your DIVAR! Please remember your password for next times.");
       }
       
    }
    public static void not(String[] args){
        Scanner E = new Scanner(System.in);
       System.out.println("Enter your email please");
       email = E.nextLine();
       if (ch1 == 1){
           Read.Read();
           
           System.out.println("Enter your password please");
           String input = E.nextLine();
           input = input + " ";
          
           if (input.contains(password)) {
               System.out.println("welcome!");
           }
           else {
               System.out.println("password is incorrect.Sorry but we cant let you use the program!");
               System.exit(0);
           }
       }
       if (ch1 == 2) {
           System.out.println("hello");
           CreateTextFile.Create();
           System.out.println("Welcome to your DIVAR! Please remember your password for next times.");
       }
    }
}
class CreateTextFile {
    public static void Create(){
        Scanner input = new Scanner(System.in);
        boolean per = true;
        System.out.printf("%s%n%s%n","enter your name and last name and the password you choose","Enter end-of-file indicator to end input.");
        String FileName = ManageAccount.email + ".txt";
        try (Formatter output = new Formatter(FileName)) {
         while (input.equals("0") || per) { // loop until end-of-file indicator
            try {
               // output new record to file; assumes valid input
               String s1, s2, s3;
               s1 = input.nextLine();
               s2 = input.nextLine();
               s3 = input.nextLine();//the password
               ManageAccount.password = s3;
               output.format("%s %s%n%s %n%s %n",s1,  
                  s2, s3, "*****");//stores the password in the second line
               System.out.println("done");
               
               per = false;
                       
            }
            catch (NoSuchElementException elementException) {
               System.err.println("Invalid input. Please try again.");
            
            } 
         }
        }
        catch (SecurityException | FileNotFoundException | 
           FormatterClosedException e) {
           
           System.exit(1); // terminate the program
        } 
    }
    
}
class Read{
    public static void Read() {
      String FileName = ManageAccount.email + ".txt";
      boolean mis = false;
      String[] args = new String[1];
      Scanner scan = new Scanner(System.in);
      try(Scanner input = new Scanner(Paths.get(FileName))) {
         String line1=input.nextLine();
         ManageAccount.password = input.nextLine();
         
      }
      catch (IOException e){// اگر ایمیل وارد شده با نام هیچ فایلی مطابقت نداشته باشد
          System.out.println("no account exists with that email.");
          System.out.println("you have two options now:");
          System.out.println("a)create an account.");
          System.out.println("b)try again.");
          while (!mis) {
            String input; 
            input = scan.nextLine();
            if (!input.equals("a") && !input.equals("b")) {
                System.out.println("invalid input, please enter only a or b.");
            }
            else {
                mis = true;
                if(input.equals("a")){
                    ManageAccount.ch1 = 2;
                    ManageAccount.main(args);
                }
                else{
                    ManageAccount.main(args);
                    
                }
            }
         }     
      }
      catch (NoSuchElementException | IllegalStateException e) {
         System.out.println("sorry an error has occured.");
         
         ManageAccount.main(args);
      } 
   } 
}

