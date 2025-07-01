package sell;

import java.io.File;
import java.io.FileNotFoundException;     
import java.util.InputMismatchException;
import java.util.NoSuchElementException;  
import java.util.Scanner;
import java.util.Formatter;               
import java.util.FormatterClosedException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.List;
public class MakeAndEdit {
    static String var;
    public static String total = "Total.txt";
    public static void main(String[] args) {
       
        boolean choice = true, Mistake = false;//choice true means continue making and editing
        Scanner input = new Scanner(System.in);
        File file = new File("Total.txt");
        try {
            file.createNewFile();//makes an empty text file named Total.txt
        }catch(IOException e){
            System.out.println("sorry an error has occured");
        }
        while(choice){
            System.out.println("Do you want to:");
            System.out.println("a) Make a new announcement");
            System.out.println("b) Edit an old announcement");
            System.out.println("c) Delete an old announcement");
            
            while (true) {
                String inp = input.nextLine();
                if (!inp.equals("a") && !inp.equals("b") && !inp.equals("c")){
                    System.out.println("invalid input,please renter.");
                }
                else {
                    
                    switch(inp) {
                        case "a":
                            File file1 = new File(ManageAccount.email + ".chat.txt");/////////////
                            if (!file1.exists()){
                                try{
                                    file1.createNewFile();
                                }catch(IOException e){
                                    System.out.println("sorry an error has occured.please try again.");
                                    System.exit(0);
                                }
                            }
                            CreateAnnouncement object = new CreateAnnouncement();
                            object.Create();
                            EditFile(ManageAccount.email + ".txt", Str.FileName, 1);//edits the email.txt
                            EditFile(total , Str.FileName, 1);//edits the Total.txt
                            System.out.println("Do you want to make ,edit or delete an announcement again?Enter 'yes' if yes and anything else if not");
                            String in = input.nextLine();
                            if(in.equals("yes")){
                                choice = true;
                            }
                            else {
                                choice = false;////////////////////////////////////////////
                            }
                            break;
                        case "b":
                            boolean f = false;
                            Scanner inpo = new Scanner(System.in);
                            showPeraonal();
                            System.out.println("please enter the exact name of the one to edit.");
                            //name of the file to be edited/////////////////////////////
                            while(true){
                                var = inpo.nextLine();
                                if(checkIfExists(var) == 1){
                                    break;
                                }
                                else{
                                    System.out.println("the entered name doesnt match any anouncement.");
                                    System.out.println("please try again.");
                                }
                            }
                            
                            SellInfo ob = new SellInfo();
                            
                            ob.EditOneAnnouncement(var);// you have to delete thevar named announcement in the files email.txt and total.txt
                            //and then you have to add the str.filename string to them
                            
                            EditFile(ManageAccount.email + ".txt",var, 2);
                            
                            EditFile(ManageAccount.email + ".txt", Str.FileName, 1);//edits the email.txt
                           
                            EditFile(total ,var, 2);
                           
                            EditFile(total , Str.FileName, 1);//edit the Total.txt
                      
                            DeleteOneAnnouncement(" Apple airpods pro 0electronic.txt");
                            EditFile(ManageAccount.email + ".txt"," Apple airpods pro 0electronic.txt", 2);
                            EditFile(total ," Apple airpods pro 0electronic.txt", 2);
                            System.out.println("Do you want to make ,edit or delete an announcement again?Enter 'yes' if yes and anything else if not");
                            in = input.nextLine();
                            if(in.equals("yes")){
                                choice = true;
                            }
                            else {
                                choice = false;/////////////////////////////////////////////
                            }
                            break;
                            
                            
                        case "c":
                            showPeraonal();
                            System.out.println("please enter the exact name of the one to delete.");
                            
                            while(true){
                                var = input.nextLine();
                                if(checkIfExists(var) == 1){
                                    break;
                                }
                                else{
                                    System.out.println("the entered name doesnt match any anouncement.");
                                    System.out.println("please try again.");
                                }
                            }
                            DeleteOneAnnouncement(var);
                            EditFile(ManageAccount.email + ".txt",var, 2);
                            EditFile(total ,var, 2);
                            System.out.println("Do you want to make ,edit or delete an announcement again?Enter 'yes' if yes and anything else if not");
                            in = input.nextLine();
                            if(in.equals("yes")){
                                choice = true;
                            }
                            else {
                                choice = false;/////////////////////////////////////////////
                            }
                            break;
                    }
                    break;
                }
            }
        }
    }
    public static void EditFile(String nameOfFile, String nameOfAnnouncement, int operation){
        Path pathToFile = Paths.get(nameOfFile);
        if(operation == 1){// means that an announcement has been created
            try{
                List<String> lines = Files.readAllLines(pathToFile);
                lines.add(nameOfAnnouncement);
                Files.write(pathToFile, lines);
            }catch(IOException e){
                System.out.println("sorry an error has occured,please try again");
            }
        }else{// means that an announcement has been deleted
            try{
                List<String> lines = Files.readAllLines(pathToFile);
                lines.removeIf(line -> line.contains(nameOfAnnouncement));
                Files.write(pathToFile, lines);
            }catch(IOException e){
                System.out.println("sorry an error has occured,please try again");
            }
        }
    }
    public static void DeleteOneAnnouncement(String v){
        Path path = Paths.get(v);
        try{
            Files.delete(path);
        }catch (IOException e){
            System.out.println("sorry an error has occured deleting the file.please try again");
        }
    }
    
    public static void showPeraonal(){//shows in the email.txt after *****
        Path personalPath = Paths.get(ManageAccount.email+".txt");
        boolean start = false;
        try {
            List<String> lines = Files.readAllLines(personalPath);
            for(String line : lines){
                if (line.contains("*****")){
                    start = true;
                }
                if (start){
                    System.out.println(line);
                }
            }
        }catch(IOException e){
           
            System.out.println("Sorry an error has occured showing your anouncements please try again");
        }
    }
    public static int checkIfExists(String s){
        Path personalPath = Paths.get(ManageAccount.email+".txt");
        
        int t = 0;
        try {
            List<String> lines = Files.readAllLines(personalPath);
            for(String line : lines){
                if(line.contains(s)){
                    t = 1;
                }
            }
        }catch(IOException e){
            
            System.out.println("Sorry an error has occured.please try again");
        }
        return t;
    }
    
        
    
}
class CreateAnnouncement {
    public void Create() {
        Scanner input = new Scanner(System.in);
        boolean Mistake = false;
        System.out.println("please choose the category of the product you want to sell");
        System.out.println("a)Cars");
        System.out.println("b)Electronics");
        System.out.println("c)Home stuff");
        System.out.println("d)Toys");
        System.out.println("e)Other");
        while (!Mistake) {
            String inp = input.nextLine();
            if (!inp.equals("a") && !inp.equals("b") && !inp.equals("c") && !inp.equals("d") && !inp.equals("e") ){
                System.out.println("invalid input,please renter.");
            }
            else {
                switch(inp){
                    case "a":
                        CarInfo object = new CarInfo();
                        object.Info();
                        object.RangeDetermination();
                        object.SpecialInfo();
                        object.CreateTextFile();
                       
                        break;
                    case "b":
                        ElectronicsInfo object1 = new ElectronicsInfo();
                        object1.Info();
                        object1.RangeDetermination();
                        object1.SpecialInfo();
                        object1.CreateTextFile();
                       
                        break;
                    case "c":
                        HomeStuffInfo object2 = new HomeStuffInfo();
                        object2.Info();
                        object2.RangeDetermination();
                        object2.SpecialInfo();
                        object2.CreateTextFile();
                       
                        break;
                    case "d":
                       ToysInfo object3 = new ToysInfo();
                       object3.Info();
                       object3.RangeDetermination();
                       object3.SpecialInfo();
                       object3.CreateTextFile();
                      
                       break;
                    case "e":
                       SellInfo ob = new SellInfo();
                       ob.Info();
                       Str.FileName = ob.nameOfProduct + "other.txt";
                       ob.CreateTextFile();
                       
                       break;
                }
                break;
            }
        }
    }
 
}
class Str {
    public static String FileName;
    public static String Describtion;
    
}
class SellInfo {
    protected String nameOfProduct;
    protected double priceOfProduct;
    protected int Range;
    protected int phoneNumber;
    public void Info() {
        boolean Mistake = false;
        Scanner input = new Scanner(System.in);
        System.out.println("enter the name of your product.");
        this.nameOfProduct = input.nextLine();
        System.out.println("enter the price of your product.");
        while(!Mistake) {//قیمت را میگیرد
            try {
               priceOfProduct = input.nextDouble();
               Mistake = true;
               input.nextLine();
            }
            catch (InputMismatchException e){
              System.out.println("Invalid input.please enter a number as the price.");
              input.nextLine();
            }
        }
        Mistake = false;
        System.out.println("please enter your phone number.");
        while(!Mistake) {//شماره تماس را میگیرد
            try {
               phoneNumber = input.nextInt();
               Mistake = true;
               input.nextLine();
            }
            catch (InputMismatchException e){
              System.out.println("Invalid input.please enter a number as your phone number.");
              input.nextLine();
            }
        }
        
     
        System.out.println("Add any additional describtion if needed.If no describtion is needed just press enter.");
        Str.Describtion = "extra description :" +input.nextLine();//توضیحات را اضافه می کند
    }
    public void CreateTextFile() {
        String FileName = Str.FileName;
        
        try (Formatter output = new Formatter(FileName)) {
          
            try {
               
               output.format("%s%n %s %n%s %.2f %n%s %d%n %s ","Email:"+ManageAccount.email, nameOfProduct,"Price:",priceOfProduct,"Phone number of seller:",phoneNumber,"Explanations:"+ Str.Describtion);
            }
            catch (NoSuchElementException elementException) {
               System.err.println("Sorry!something went wrong!");
               System.exit(1);
            } 
         
        }
        catch (SecurityException | FileNotFoundException | 
           FormatterClosedException e) {
          
           System.exit(1); // terminate the program
        } 
    }
    public void EditOneAnnouncement(String v){//v is the name of the announcement file
        
        Path filePath = Paths.get(v);
        String ending = "other.txt";
        try(Scanner scan = new Scanner(filePath)){
            scan.nextLine();
            String temp;
            nameOfProduct = scan.nextLine();
            //System.out.println(nameOfProduct);////////////////////////////////////
            temp = scan.nextLine();
            Split(temp ,1);
            // System.out.println(priceOfProduct);////////////////////////////////////
            //priceOfProduct = scan.nextDouble();
            temp = scan.nextLine();
            Split(temp ,2);
           // System.out.println(phoneNumber);////////////////////////////////////
            //phoneNumber = scan.nextInt();
            temp = scan.nextLine();
            Split(temp ,3);
            //System.out.println(Str.Describtion);////////////////////////////////////
            //Str.Describtion = scan.nextLine();
            if(v.endsWith("car.txt")){
                //CarInfo object = new CarInfo();
                //object.RangeDetermination();
                
                ending = "car.txt";
                
            }else if(v.endsWith("Home.txt")){
                HomeStuffInfo object = new HomeStuffInfo();
                object.RangeDetermination();
                ending = "Home.txt";
                
            }else if(v.endsWith("electronic.txt")){
                ElectronicsInfo object = new ElectronicsInfo();
                object.RangeDetermination();
                ending = "electronic.txt";
                System.out.println("this is read first,range is" + Range);///////////////////////////////////////////////////
                
            }else if(v.endsWith("toys.txt")){
                ToysInfo object = new ToysInfo();
                object.RangeDetermination();
                ending = "toys.txt";
                
            }else{
                ending = "other.txt";
                
            }
           // System.out.println(ending);///////////////////////////////////////////////////
           // System.out.println("now range is"+Range);///////////////////////////////////////////////////
           // System.out.println(priceOfProduct);///////////////////////////////////////////////////
            //////////////////////////////////////////////dont forget to delet the former file
            
        }catch(IOException e){
            System.out.println("sorry an error has occured please try again");
        }
        
        while(true){
            
            System.out.println("which information do you want to edit?");
            System.out.println("a)name");
            System.out.println("b)price");
            System.out.println("c)phone number");
            System.out.println("d)description.");
            System.out.println("e)special information");
            Scanner scan = new Scanner(System.in);
            while (true){
                String s = scan.nextLine();
                if(!s.equals("a")&&  !s.equals("b")&& !s.equals("c")&& !s.equals("d")){
                    System.out.println("invalid input, please try again");
                }
                else {
                    switch (s) {
                        case "a":
                            System.out.println("please enter the new name");
                            while(true){
                                try{
                                    nameOfProduct = scan.nextLine();
                                    break;
                                }catch (InputMismatchException e){
                                    System.out.println("invalid input, please try again");
                                }
                            }
                            //System.out.println(nameOfProduct);///////////////////////////
                            break;
                            
                        case "b": 
                            System.out.println("please enter the new price");
                            while(true){
                                try{
                                    priceOfProduct = scan.nextDouble();
                                    scan.nextLine();
                                    break;
                                }catch (InputMismatchException e){
                                    System.out.println("invalid input, please try again");
                                }
                            }
                            switch(ending){
                                case"car.txt":
                                    CarInfo car = new CarInfo();
                                    car.RangeDetermination();
                                    break;
                                case"Home.txt":
                                    HomeStuffInfo home = new HomeStuffInfo();
                                    home.RangeDetermination();
                                    break;
                                case"electronic.txt":
                                    ElectronicsInfo elec = new ElectronicsInfo();
                                    elec.RangeDetermination();
                                    break;
                                case"toys.txt":
                                    ToysInfo toy = new ToysInfo();
                                    toy.RangeDetermination();
                                    break;
                                case"other.txt":
                                    System.out.println("the (others) category has no special information");
                                    break;
                            }
                            //System.out.println(priceOfProduct);///////////////////////////
                           // System.out.println(Range);/////////////////////
                            break;
                        case "c":
                            System.out.println("please enter the new phone number");
                            while(true){
                                try{
                                    phoneNumber = scan.nextInt();
                                    scan.nextLine();
                                    break;
                                }catch (InputMismatchException e){
                                    System.out.println("invalid input, please try again");
                                }
                            }
                            //System.out.println(phoneNumber);///////////////////////////
                            break;
                        case "d":
                            System.out.println("please enter the new description");
                            Str.Describtion = scan.nextLine();
                            //System.out.println(Str.Describtion);///////////////////////////
                            break;
                        case "e":
                            switch(ending){
                                case"car.txt":
                                    CarInfo car = new CarInfo();
                                    car.SpecialInfo();
                                    break;
                                case"Home.txt":
                                    HomeStuffInfo home = new HomeStuffInfo();
                                    home.SpecialInfo();
                                    break;
                                case"electronic.txt":
                                    ElectronicsInfo elec = new ElectronicsInfo();
                                    elec.SpecialInfo();
                                    break;
                                case"toys.txt":
                                    ToysInfo toy = new ToysInfo();
                                    toy.SpecialInfo();
                                    break;
                                case"other.txt":
                                    System.out.println("the (others) category has no special information");
                                    break;
                            }
                            //System.out.println(Str.Describtion);///////////////////////////
                            break; 
                    }
                    break;
                }
            }
            System.out.println("if you want to continue editing the announcement,enter a and anything else for no");
            String n = scan.nextLine();
            if(!n.equals("a")){
                break;
            }
        }
        
        ///////////////////here you have to delete the oldNamed announcement
        MakeAndEdit.DeleteOneAnnouncement(v);//deletes the old announcement file
        System.out.println(nameOfProduct);///////////////////////////
        System.out.println(priceOfProduct);///////////////////////////
        System.out.println(Range);/////////////////////
        System.out.println(phoneNumber);///////////////////////////
        System.out.println(Str.Describtion);///////////////////////////
        Str.FileName = nameOfProduct + Range+ ending;
        ///here you have to create the nameofproduct+range+ending file
        SellInfo ob = new SellInfo();
        ob.CreateTextFile();//creates the edited announcement
        System.out.println("editing done");///////////////////////////
    }
    public void Split(String t,int a){
        
        switch(a){
            case 1:
                String[] temp = t.split(": ");
                //System.out.println(temp[0]);/////////////////
                //System.out.println(temp[1]);/////////////////
                priceOfProduct = Double.parseDouble(temp[1]);
                //System.out.println("price"+priceOfProduct);/////////////////
                break;
            case 2:
                String[] temp1 = t.split(": ");
                phoneNumber = Integer.parseInt(temp1[1]);
                
                break;
            case 3:
                String[] temp2 = t.split(" :");
                Str.Describtion = temp2[1];
                break;
        }
    }
}
class CarInfo extends SellInfo {
    protected int year;
    protected int kilo;
    protected String crush;

    
    public void SpecialInfo() {
        boolean mis = false;
        RangeDetermination();
        Str.FileName = nameOfProduct + Range + "car.txt";
        System.out.println("enter the production year of your car");
        Scanner input = new Scanner(System.in);
        while(!mis){
            try{
                year = input.nextInt();
                input.nextLine();
                mis = true;
            }
            catch(InputMismatchException e){
                System.out.println("please enter an integer as the year of production.");
                input.nextLine();
            }
        }//mis is true here
        System.out.println("enter how many kilometers your car has traveled.(integer)");
        while(mis){
            try{
                kilo = input.nextInt();
                input.nextLine();
                mis = false;
            }
            catch(InputMismatchException e){
                System.out.println("please enter an integer as the count of kilometers.");
                input.nextLine();
            }
        }//mis is false here
        System.out.println("has your car ever had an accident?enter 'yes' or 'no' ");
        while(!mis){
            try {
                crush = input.nextLine();
                mis = true;
            }
            catch(InputMismatchException e){
                System.out.println("please enter only 'yes' or 'no'.");
            }
        }
        Str.Describtion += "\n year of production :" + year;
        Str.Describtion += "\n travaled kilometers :" + kilo;
        Str.Describtion += "\n accident :" + crush;
    }
    public void RangeDetermination() {
        if ((priceOfProduct <= 100000000)) {
            Range = 1;
        }
        else if((priceOfProduct <= 300000000)){
            Range = 2;
        }else if((priceOfProduct <= 500000000)){
            Range = 3;
        }else if ((priceOfProduct <= 700000000)){
            Range = 4;
        }else {
            Range = 5;
        }
    }
}
class HomeStuffInfo extends SellInfo {
    public void SpecialInfo() {
        RangeDetermination();
        Str.FileName = nameOfProduct + Range + "Home.txt";
    }
    public void RangeDetermination() {
        if ((priceOfProduct <= 500000)) {
            Range = 1;
        }
        else if((priceOfProduct <= 2000000)){
            Range = 2;
        }else if((priceOfProduct <= 10000000)){
            Range = 3;
        }else if ((priceOfProduct <= 100000000)){
            Range = 4;
        }else {
            Range = 5;
        }
    }
}
class ElectronicsInfo extends SellInfo {
    public void SpecialInfo() {
        RangeDetermination();
        Str.FileName = nameOfProduct + Range + "electronic.txt";
    }
    public void RangeDetermination() {
       // System.out.println("rprice is"+priceOfProduct);
        if ((priceOfProduct <= 500000)) {
            Range = 1;
        }
        else if((priceOfProduct <= 1000000)){
            Range = 2;
        }else if((priceOfProduct <= 3000000)){
            Range = 3;
        }else if ((priceOfProduct <= 10000000)){
            Range = 4;
        }else {
            Range = 5;
        }
        //System.out.println("rangedetermination is read an range is"+Range);
    }
    
}
class ToysInfo extends SellInfo{
    public void SpecialInfo(){
        RangeDetermination();
        Str.FileName = nameOfProduct + Range + "toys.txt";
    }
    public void RangeDetermination() {
        if ((priceOfProduct <= 500000)) {
            Range = 1;
        }
        else if((priceOfProduct <= 1000000)){
            Range = 2;
        }else if((priceOfProduct <= 3000000)){
            Range = 3;
        }else if ((priceOfProduct <= 10000000)){
            Range = 4;
        }else {
            Range = 5;
        }
    }
}




