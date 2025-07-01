/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sell;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class Massager {
    public Massager(){
        System.out.println("Welcome to Massager!");
    }
    
    public static void main(String[] args){
        
    }
    
    public int ChatCreation(String email1, String email2){
        int a = 1;
        String s = email1 + " " + email2 + ".txt";
        File chat = new File(s);
        try{
           
            if(chat.createNewFile()){
                System.out.println("chat created succesfully");///////////////////////////////
            }
            else{
                System.out.println("file already exists");
            }
        }catch(IOException e){
            a = 2;
            System.out.println("sorry an error has occured,chat couldnt be created");
        }
        return a;
    }
    public static void EditConversation(String conversation){
        Path path = Paths.get(conversation);
        File filedPath = path.toFile();
        String massage;
        System.out.println("please enter your massage:");
        Scanner scan = new Scanner(System.in);
        massage = ManageAccount.email + ": " + scan.nextLine() + "\n ***************************************";
        try(FileWriter writer = new FileWriter(filedPath, true)){
            writer.write(massage);
        }catch(IOException e){
            System.out.println("sorry an error has occured,massage not sent,please try again");
        }
        
    }
}



