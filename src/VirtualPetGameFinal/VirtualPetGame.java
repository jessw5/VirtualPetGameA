/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package VirtualPetGameFinal;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author jessi
 */
public class VirtualPetGame {
    private final HashMap<String, PetUser> users;
    private final String fileName;
    
    public VirtualPetGame(){
        this.fileName = "./test/PetUsers.txt";
        this.users = new HashMap();
        this.getPetUsers(fileName);
    }
    
    public final void getPetUsers(String fn){
        FileInputStream fin;
        try{
            fin = new FileInputStream(fn);
            Scanner fileScanner = new Scanner(fin);
            
            while(fileScanner.hasNextLine()){
                String line = fileScanner.nextLine();
                StringTokenizer st = new StringTokenizer(line);
                PetUser u = new PetUser(st.nextToken(), Integer.parseInt(st.nextToken()));
                this.users.put(u.getUsername(), u);
            }
            fin.close();
        }catch (FileNotFoundException ex){
            System.out.println(ex.getMessage());
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    //Main Method - runs the program
    public static void main(String[] args) throws IOException{
        VirtualPetGame game = new VirtualPetGame();
        Scanner scan = new Scanner(System.in);
        System.out.println("***Welcome to the Virtual Pet Game***\n");
        System.out.println("Please enter your name: ");
        String s = scan.nextLine();
        PetUser u = game.checkUser(s);

        //Prints the game's manual
        Manual.main();
 
        //start game with created pet
        Animal pet1 = game.choosePet();
        
        //User plays the game
        Game g = new Game(u, pet1);
       
        //Updates Users scores and records it on txt file
        game.updateScore(u);
        System.out.println(u.getUsername() + ", your score is: " + u.getScore());
        
        //Ask user if they like to replay the virtual pet game
        while(true){
                System.out.println("\nDo you want to retry? (Y/N)");
                String c = scan.nextLine();
                
                if (c.equalsIgnoreCase("y")) {
                    VirtualPetGame.main(args);
                }
                else if (c.equalsIgnoreCase("n"))
                {
                    System.out.println("Thank you for playing!");
                    System.exit(0);
                }
            }
    }
    
    public void updateScore(PetUser user) {
        this.users.put(user.getUsername(), user);
        //uses arraylist to output highest score in the collection
        List<Integer> list = new ArrayList<Integer>();
        try{
            FileOutputStream fOut = new FileOutputStream(this.fileName);
            PrintWriter pw = new PrintWriter(fOut);
            for(PetUser u : this.users.values()){
                list.add(u.getScore());
                pw.println(u.getUsername() + " " + u.getScore());
            }
            pw.close();
        }catch(FileNotFoundException ex){
            System.out.println(ex.getMessage());
        }
        System.out.println("Highest Score: "+Collections.max(list));
    }
    
    public PetUser checkUser(String un) {
        PetUser u;
        if(this.users.containsKey(un)){
            u = this.users.get(un);
            System.out.println("Your current score: " + u.getScore());
        }else{
            u = new PetUser(un, 0);
            this.users.put(un, u);
        } 
        return u;
    }
    
    //prompts user to create pet to play game with
    public Animal choosePet(){
        System.out.println("Please Select: \n"
                + "(1) Dog\n"
                + "(2) Cat\n"
                + "(3) Rabbit");
        
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
       
        while(true){
        try{
            int option = Integer.parseInt(s);
            switch(option){
                case 1:
                    System.out.println("Congratulations! You have adopted a Puppy!");
                    
                    System.out.println("              _=,_\n" +
                                       "           o_/6 /#\\\n" +
                                       "           \\__ |##/\n" +
                                       "            ='|--\\\n" +
                                       "              /   #'-.\n" +
                                       "              \\#|_   _'-. /\n" +
                                       "               |/ \\_( # |\" \n" +
                                       "              C/ ,--___/");
                    System.out.println("Please give your puppy a name: ");
                    String nameDog = scan.nextLine();
                    System.out.println("Please describe the colour of your puppy: ");
                    String colorDog = scan.nextLine();
                    Dog dog = new Dog(nameDog, colorDog);
                    System.out.println("Your puppy details: ");
                    System.out.println(dog.getName());
                    System.out.println(dog.getColor());
                    dog.printInfo();
                    return dog;
                case 2: 
                    System.out.println("Congratulations! You have adopted a Kitten!");
                    System.out.println(" _._     _,-'\"\"`-._\n" +
                                       "(,-.`._,'(       |\\`-/|\n" +
                                       "    `-.-' \\ )-`( , o o)\n" +
                                       "          `-    \\`_`\"'-");
                    System.out.println("Please give your kitten a name: ");
                    String nameCat = scan.nextLine();
                    System.out.println("Please describe the colour of your kitten: ");
                    String colorCat = scan.nextLine();
                    Cat cat = new Cat(nameCat,colorCat);
                    System.out.println("Your kitten details: ");
                    System.out.println(cat.getName());
                    System.out.println(cat.getColor());
                    cat.printInfo();
                    return cat;            
                case 3:
                    System.out.println("Congratulations! You have adopted a Rabbit!");
                    System.out.println("       _\n" +
                                       "      (\\\\\n" +
                                       "       \\||\n" +
                                       "     __(_\";\n" +
                                       "    /    \\\n" +
                                       "   {}___)\\)_");
                    System.out.println("Please give your rabbit a name: ");
                    String nameRabbit = scan.nextLine();
                    System.out.println("Please describe the colour of your rabbit: ");
                    String colorRabbit = scan.nextLine();
                    Rabbit rabbit = new Rabbit(nameRabbit, colorRabbit);
                    System.out.println("Your rabbit details: ");
                    System.out.println(rabbit.getName());
                    System.out.println(rabbit.getColor());
                    rabbit.printInfo();
                    return rabbit;
                default: 
                    System.out.println("Wrong input. Please enter a number between 1 - 3");
                    break;
            }
        }catch (NumberFormatException e){
            System.out.println("Invalid input. Not an integer. Please enter a number between 1 - 3");
        }
        s = scan.nextLine();
        }
    }
   
}

