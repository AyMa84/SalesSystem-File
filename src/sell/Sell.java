/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sell;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.List;

import java.util.Scanner;
public class Sell {
    public static String ending;
    public static String sellersEmail;
    public static int Range;

    public static void main(String[] args) {
        System.out.println("welcome to sales manager system!");
        Scanner scanner = new Scanner(System.in);
        String input;
        int cont = 1;
        boolean mis = true;
        boolean mistake = false;
        String[] s = new String[4];
        System.out.println("OK! to continue, you need to have an account in DIVAR.");
        System.out.println("a) I already have an account");
        System.out.println("b) I dont have any account");
        while (true) {
            input = scanner.nextLine();
            if (!input.equals("a") && !input.equals("b")) {
                System.out.println("invalid input, please enter only a or b.");
            }
            else {
                if (input.equals("a")){
                    ManageAccount.ch1 = 1;
                }//ch1 = 1 یعنی اکانت دارد
                else {
                   ManageAccount.ch1 = 2;//ch1 = 2 یعنی اکانت ندارد
                   ManageAccount.not(s);
                   break;
                }
            }
        } 
        File file = new File(ManageAccount.email + ".chat.txt");/////////////
        if (!file.exists()){
            try{
                file.createNewFile();
            }catch(IOException e){
                System.out.println("sorry an error has occured.chat couldnt be created,please try again.");
                System.exit(0);
            }
        }
        while (true) {
            System.out.println("What do you want to do?");
            System.out.println("a) Make,edit or delete an announcement");
            System.out.println("b) Buy something");
            System.out.println("c) go to Massages");
            System.out.println("d) quit the program");
            input = scanner.nextLine();
            if (!input.equals("a") && !input.equals("b") && !input.equals("c") && !input.equals("d")) {
                System.out.println("invalid input, please enter only a,b,c,d.");
            }
            else {
                
                if (input.equals("a")) {
                    MakeAndEdit.main(args);
                }
                else if(input.equals("b")){
                    String var;
                    GetFilter();
                    while(mis){//the while of scrowling in the announcements//////////////////////
                        if(ShowByFilter()) {
                            System.out.println("please enter the exact name of the one to open.");
                          
                            while (true) {
                                var = scanner.nextLine();
                                int a = CheckIfExists("Total.txt", var);
                                //System.out.println("1"+a);///////////////
                                if(a == 1) {
                                    break;
                                }else if(a == 0){
                                    System.out.println("the entered name doesnt match any anouncement.");
                                    System.out.println("please try again.");
                                }else if(a == 2){
                                    System.out.println("an error has occured.please try again");
                                    cont = 0;
                                    break;
                                }
                            }
                            
                            if (cont == 0){
                                cont = 1;
                                break;
                            }
                            //now you have to show the announcement named var
                            int a = ShowFile(var);//to get the email of the owner
                            System.out.println("2"+a);////////////////
                            if(a == 2){
                                break;
                            }
                            System.out.println("enter your choice:");
                            System.out.println("a)back");
                            System.out.println("b)Start chating with the owner");
                            while(true){
                                input = scanner.nextLine();
                                if(!input.equals("a") && !input.equals("b")){
                                    System.out.println("Invalid input,please try again");
                                }
                                else{
                                    if(input.equals("b")){//ManageAccount.email + ".chat.txt", ManageAccount.email +"***" + sellersEmail+".txt"
                                        a = CheckIfExists(ManageAccount.email + ".chat.txt", ManageAccount.email +" " + sellersEmail+".txt");
                                        
                                        if(a == 1){
                                            System.out.println("the chat already exists,you can have access to it by the option c in the main menu.");
                                        }
                                        else if(a == 0){                                            
                                            Massager object = new Massager();
                                            int b = object.ChatCreation(ManageAccount.email, sellersEmail);
                                            if (b == 2) {
                                                System.out.println("sorry chat not created");
                                                cont = 0;
                                                break;
                                            }
                                            MakeAndEdit.EditFile(ManageAccount.email + ".chat.txt", ManageAccount.email +" " + sellersEmail+".txt", 1);
                                            MakeAndEdit.EditFile(sellersEmail + ".chat.txt", ManageAccount.email +" " + sellersEmail+".txt", 1);
                                            System.out.println("Chat created,you now have access to it by the option c of the main menu.");
                                        }else {
                                            cont = 0;
                                            break;
                                        }
                                    /////////////////////////////
                                    }
                                    break;
                                }
                            }
                            
                            if (cont == 0){
                                cont = 1;
                                System.out.println("exited");
                                break;
                            }
                            System.out.println("Do you want to continue scrowling among announcements?");
                            System.out.println("a)yes");
                            System.out.println("b)no");
                            while(true){
                                input = scanner.nextLine();
                                if(!input.equals("a") && !input.equals("b")){
                                    System.out.println("Invalid input,please try again");
                                }
                                else{
                                    if(input.equals("b")){
                                        mis = false;
                                    }
                                    break;
                                }
                            }
                        }else{
                            break;
                        }
                    }
                   ////////////
                }else if(!input.equals("d")){
                    int a = ShowFile(ManageAccount.email + ".chat.txt");
                    if(a == 0){
                        System.out.println("you have no converstation yet.");
                    }
                    else if (a == 1){
                        mis = true;
                        String nameOfConversation;
                        while(mis){//the while of conversations scrowling
                            System.out.println("enter the name of the converstation you want to access.");
                            while(true){
                                nameOfConversation = scanner.nextLine();
                                int b = CheckIfExists(ManageAccount.email+".chat.txt", nameOfConversation);
                                if (b == 0){
                                    System.out.println("the entered name matches no converstation.please try again.");
                                }else if (b == 1){
                                    break;
                                }else{
                                    System.out.println("please try again");
                                    cont = 0;
                                    break;
                                }
                            }
                            if (cont == 0){
                                cont = 1;
                                break;
                            }
                            ShowFile(nameOfConversation);
                            Massager.EditConversation(nameOfConversation);
                            System.out.println("Do you want to open an other conversation?");
                            System.out.println("a)yes");
                            System.out.println("b)no");
                            while(true){
                                input = scanner.nextLine();
                                if(!input.equals("a") && !input.equals("b")){
                                    System.out.println("Invalid input,please try again");
                                }
                                else{
                                    if(input.equals("b")){
                                        mis = false;
                                        break;
                                    }
                                }
                            }
                        }
                    }//an exception has happened
                    
                }else {
                    System.out.println("Goodby!");
                    return;
                } 
                
            } 
        }
    }
    public static int ShowFile(String nameOfFileToShow){//returnng false means that the file is empty 
        Path personalPath = Paths.get(nameOfFileToShow);
        int t = 0;
        int n = 0;
        try {
            List<String> lines = Files.readAllLines(personalPath);
            System.out.println("\n");
            System.out.println("\n");
            for(String line : lines){
                if (n == 0){
                    System.out.println("\n");
                    System.out.println("\n");
                }
                if(!line.trim().isEmpty()){
                    t =1;
                    System.out.println(line);
                    if (n == 0){
                        System.out.println("\n");
                        System.out.println("\n");
                    }
                    n=1;
                }
                if(line.contains("Email:")){
                    String[] emailWithNewLine = line.split(":");
                    String temp = emailWithNewLine[1].trim();
                    sellersEmail = temp;
                }
            }
        }
        catch (IOException e){
            t = 2;
            System.out.println("Sorry an error has occured.please try again");
        }
        
        return t;
    }
    public static int CheckIfExists(String v, String s){
        Path personalPath = Paths.get(v);
        
        int t = 0;
        try {
            java.util.List<String> lines = Files.readAllLines(personalPath);
            for(String line : lines){
                if(line.contains(s)){
                    t = 1;
                }
            }
        }catch(IOException e){
            t = 2;
            System.out.println("Sorry an error has occured.please try again");
        }
        return t;
    }
    public static void GetFilter(){
        Scanner scanner = new Scanner(System.in);
        String option;
        double price;
       
        System.out.println("Please select the category of the thing you want.");
        System.out.println("a)Cars");
        System.out.println("b)Electronics");
        System.out.println("c)Home stuff");
        System.out.println("d)Toys");
        System.out.println("e)Other");
        while(true){
            option = scanner.nextLine();
            if(!option.equals("a") && !option.equals("b") && !option.equals("c") && !option.equals("d") && !option.equals("e")){
                System.out.println("invalid input,Please try again.");
            }
            else{
                break;
            }
        }
        switch (option){
            case "a" :
                ending = "car.txt";
                break;
            case "b":
                ending = "electronic.txt";
                break;
            case "c":
                ending = "Home.txt";
                break;
            case "d":
                ending = "toys.txt";
                break;
            default:
                ending = "other.txt";
                break;        
        }
        System.out.println(ending);
        if(option.equals("a")){
            System.out.println("please enter the maximum amount you would rather pay for it.");
            while(true){
                try{
                    price = scanner.nextDouble();
                    scanner.nextLine();
                    break;
                }catch(InputMismatchException e){
                    System.out.println("invalid input,please try again.");
                }
            }
            CarInfo ob = new CarInfo();
            ob.priceOfProduct = price;
            ob.RangeDetermination();
            Range = ob.Range;
           // ending = ob.Range + ending;
            //System.out.println(ending);/////////////////////////
        }
        if(option.equals("b")){
            System.out.println("please enter the maximum amount you would rather pay for it.");
            while(true){
                try{
                    price = scanner.nextDouble();
                    scanner.nextLine();
                    break;
                }catch(InputMismatchException e){
                    System.out.println("invalid input,please try again.");
                }
            }
            ElectronicsInfo ob = new ElectronicsInfo();
            ob.priceOfProduct = price;
            ob.RangeDetermination();
            Range = ob.Range;
           // ending = ob.Range + ending;
           // System.out.println(ending);//////////////////////////////
        }
        if(option.equals("c")){
            System.out.println("please enter the maximum amount you would rather pay for it.");
            while(true){
                try{
                    price = scanner.nextDouble();
                    scanner.nextLine();
                    break;
                }catch(InputMismatchException e){
                    System.out.println("invalid input,please try again.");
                }
            }
            HomeStuffInfo ob = new HomeStuffInfo();
            ob.priceOfProduct = price;
            ob.RangeDetermination();
            Range = ob.Range;
            //ending = ob.Range + ending;
            //System.out.println(ending);///////////////////////////
        }
        if(option.equals("d")){
            System.out.println("please enter the maximum amount you would rather pay for it.");
            while(true){
                try{
                    price = scanner.nextDouble();
                    scanner.nextLine();
                    break;
                }catch(InputMismatchException e){
                    System.out.println("invalid input,please try again.");
                }
            }
            ToysInfo ob = new ToysInfo();
            ob.priceOfProduct = price;
            ob.RangeDetermination();
            Range = ob.Range;
            //ending = ob.Range + ending;
            //System.out.println(ending);////////////////////
        }
    }
    public static boolean ShowByFilter(){

        Path path = Paths.get("Total.txt");
        boolean t = true;
        try{
           
            List<String> lines = Files.readAllLines(path);
            for(String line : lines){
                String last;
               
                for(int i = 1; i <= Range; i++){
                    last = i + ending;
                    if(line.contains(last)){
                        System.out.println(line);
                    }
                }
            }
        }catch(IOException e){
            System.out.println("sorry an error has occured,please try again");
            t = false;
        }
        System.out.println("show by filter is read");
        return t;
    }
    
}
