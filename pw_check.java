
import java.io.*;
import java.util.*;
//import java.ArrayList.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dgtc1_000
 */
public class pw_check {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     * @throws java.io.UnsupportedEncodingException
     */
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        // TODO code application logic here
        
		boolean argument;
        if(args.length == 0){
			argument = false;
		}
		else if(args[0].equals("-g")){
			argument = true;
		}
		else{
			argument = false;
		}
		
		
        if(argument){
            String word;
            //int count=0;
            Scanner kb = new Scanner(System.in);
            DLBtrie<String> dictionary = new DLBtrie();

            //ensures the file name exists
            File inputFile = new File("dictionary.txt"); 
            String fileName;
            while(!inputFile.exists()){
                System.out.print("The file could not be found, enter a new path or filename:\t");
                fileName = kb.next();
                inputFile = new File(fileName);
            }
            
            //reads in the file
            Scanner file = new Scanner(inputFile);

            PrintWriter writer = new PrintWriter("my_dictionary.txt", "UTF-8");
            
            //reads each line into the array fileLines
            while(file.hasNextLine()){
                word = file.nextLine();
                if(word.length() <= 5){
                    //System.out.print(word+"\n");
                    dictionary.insert(word.toLowerCase());
                    writer.println(word);
                    
                    //count = dictionary.findString(word.toLowerCase());
                    //System.out.println("\t"+count);
                }
            }
            file.close();
            writer.close();
            /*
            System.out.println("\n\n\n\n");
            
             file = new Scanner(inputFile);
             //reads each line into the array fileLines
            while(file.hasNextLine()){
                word = file.nextLine();
                if(word.length() <= 5){
                    System.out.print(word);
                    count = dictionary.findString(word.toLowerCase());
                    System.out.println("\t"+count);
                }
            }
            file.close();
            
            word = "stace";
            System.out.println(word);
            count = dictionary.findString(word.toLowerCase());
            System.out.println("\t"+count);
            */
            
        // start password generation
            
            DLBtrie<String> goodPwds = new DLBtrie();
            String alphabet = "bcdefghjklmnopqrstuvwxyz02356789$^_*!@";
            String pwd;
            String temp;
            int w1, w2, w3, w4;
            char temp1;
            char temp2;
            char temp3;
            char temp4;
            char temp5;
            boolean valid;
            writer = new PrintWriter("good_passwords.txt", "UTF-8");
            
            for(int i = 0; i<alphabet.length(); i++){
                temp1 = replace(alphabet.charAt(i));
                
                for(int j = 0; j<alphabet.length(); j++){
                    temp2 = replace(alphabet.charAt(j));
                    temp = ""+temp1+temp2;
                    if(!(dictionary.findString(temp)==1)){
                        
                        for(int k = 0; k<alphabet.length(); k++){
                            temp3 = replace(alphabet.charAt(k));
                            
                            temp = ""+temp1+temp2+temp3;
                            w1 = dictionary.findString(temp);
                            temp = ""+temp2+temp3;
                            w2 = dictionary.findString(temp);
                            if(w1 != 1 && w2 !=1){
                                
                                for(int x = 0; x<alphabet.length(); x++){
                                    temp4 = replace(alphabet.charAt(x));
                                    
                                    temp = ""+temp1+temp2+temp3+temp4;
                                    w1 = dictionary.findString(temp);
                                    temp = ""+temp2+temp3+temp4;
                                    w2 = dictionary.findString(temp);
                                    temp = ""+temp3+temp4;
                                    w3 = dictionary.findString(temp);
                                    if(w1 != 1 && w2 != 1 && w3 != 1){
                                        for(int y = 0; y<alphabet.length(); y++){
                                            temp5 = replace(alphabet.charAt(y));
                                            
                                            temp = ""+temp1+temp2+temp3+temp4+temp5;
                                            w1 = dictionary.findString(temp);
                                            temp = ""+temp2+temp3+temp4+temp5;
                                            w2 = dictionary.findString(temp);
                                            temp = ""+temp3+temp4+temp5;
                                            w3 = dictionary.findString(temp);
                                            temp = ""+temp4+temp5;
                                            w4 = dictionary.findString(temp);
                                            if(w1 != 1 && w2 != 1 && w3 != 1 && w4 != 1){
                                                valid = checkCharRequirements(alphabet.charAt(i), alphabet.charAt(j),alphabet.charAt(k),alphabet.charAt(x),alphabet.charAt(y));
                                                if(valid){
                                                    pwd = ""+alphabet.charAt(i)+alphabet.charAt(j)+alphabet.charAt(k)+alphabet.charAt(x)+alphabet.charAt(y);
                                                    goodPwds.insert(pwd);
                                                    //System.out.println(pwd);
                                                    writer.println(pwd);
                                                }
                                            }
                                            
                                        }   
                                    }
                                }
                            }
                        }
                    }
                }
            }
            writer.close();
            
        }
        else{
            
            DLBtrie<String> goodPWD = new DLBtrie();
            
            Scanner kb = new Scanner(System.in);
            //ensures the file name exists
            File inputFile = new File("good_passwords.txt"); 
            //reads in the file
            String fileName;
            while(!inputFile.exists()){
                System.out.print("The file could not be found, enter a new path or filename:\t");
                fileName = kb.next();
                inputFile = new File(fileName);
            }
            
            Scanner file = new Scanner(inputFile);
            
            String pwd;
            
            //reads each line into the array fileLines
            while(file.hasNextLine()){
                pwd = file.nextLine();
                //System.out.println(pwd);
                if(pwd.length() <= 5){
                    //System.out.print(word+"\n");
                    goodPWD.insert(pwd);
                    
                    //count = dictionary.findString(word.toLowerCase());
                    //System.out.println("\t"+count);
                }
            }
            file.close();
            String loopCont="y";
            String userPWD;
            String newPWD, newPWD1, newPWD2, newPWD3;
            int valid;
            int i=0, j=0, k=0, x=0, y=0;
            int count=0;
			ArrayList<String> goodOnes = new ArrayList<String>();
			
            String alphabet = "bcdefghjklmnopqrstuvwxyz02356789$^_*!@";
            
            while(loopCont.equalsIgnoreCase("y")){
                System.out.print("\n Please enter your five character password: \t");
                userPWD = kb.next();
				
                
                while(userPWD.length() != 5){
                    System.out.print("\n Your password must be five characters long, please try again: \t");
                    userPWD = kb.next();
                }
                
                valid = goodPWD.findString(userPWD);
                if(valid == 1){
                    System.out.println("This is a valid password, good job!");
                }
                else{
                    System.out.println("This password is not valid, but here are some similar ones that are: ");
                    
                    count = 0;
                    String s = userPWD.substring(0,4);
                    //System.out.println(s);
                    if(goodPWD.findString(s) == 0){
                        //System.out.println("if1");
                        while(count<10 && i<alphabet.length()){
                            newPWD = s+alphabet.charAt(i);
                            if(goodPWD.findString(newPWD)==1 && !goodOnes.contains(newPWD)){
                                System.out.println(newPWD);
								goodOnes.add(newPWD);
                                count++;
                            }
                            i++;
                        }
                        i=0;
                    }
                    
                    s = userPWD.substring(0,3);
                    //System.out.println(s);
                    if(count<10 && goodPWD.findString(s)==0){
                       // System.out.println("if2");
                        while(count<10 && i<alphabet.length()){
                            newPWD = s+alphabet.charAt(i);
                            
                            if(goodPWD.findString(newPWD)==0){
                                while(count<10 && j<alphabet.length()){
                                    newPWD1 = newPWD + alphabet.charAt(j);
                                    
                                    //System.out.print(newPWD1 + "\t");
                                    //System.out.println(goodPWD.findString(newPWD1));
                                    
                                    if (goodPWD.findString(newPWD1)==1 && !goodOnes.contains(newPWD1)){
                                        System.out.println(newPWD1);
										goodOnes.add(newPWD1);
                                        count++;
                                    }
                                    j++;
                                }
                                j=0;
                            }
                            i++;
                        }
                        i=0;
                    }
                    
                    s= userPWD.substring(0,2);
                    //System.out.println(s);
                    if(count<10 && goodPWD.findString(s)==0){
                        //System.out.println("if3");
                        while(count<10 && k<alphabet.length()){
                            newPWD2 = s+ alphabet.charAt(k);
                            if(count<10 && goodPWD.findString(newPWD2)==0){
                                while(count<10 && i<alphabet.length()){
                                    newPWD = newPWD2+alphabet.charAt(i);
                                    if(goodPWD.findString(newPWD)==0){
                                        while(count<10 && j<alphabet.length()){
                                            newPWD1 = newPWD + alphabet.charAt(j);
                                            if (goodPWD.findString(newPWD1)==1 && !goodOnes.contains(newPWD1)){
                                                System.out.println(newPWD1);
												goodOnes.add(newPWD1);
                                                count++;
                                            }
                                            j++;
                                        }
                                        j=0;
                                    }
                                    i++;
                                }
                                i=0;
                            }
                            k++;
                        }
                        k=0;
                    }
                    
                    newPWD3 =  userPWD.substring(0,1);
                    //System.out.println(newPWD3);
                    if(count<10 && goodPWD.findString(newPWD3)==0){
                        //System.out.println("if4");
                        while(count<10 && x<alphabet.length()){
                            s = newPWD3 + alphabet.charAt(x);
                            if(count<10 && goodPWD.findString(s)==0){
                                while(count<10 && k<alphabet.length()){
                                    newPWD2 = s+ alphabet.charAt(k);
                                    if(count<10 && goodPWD.findString(newPWD2)==0){
                                        while(count<10 && i<alphabet.length()){
                                            newPWD = newPWD2+alphabet.charAt(i);
                                            if(goodPWD.findString(newPWD)==0){
                                                while(count<10 && j<alphabet.length()){
                                                    newPWD1 = newPWD + alphabet.charAt(j);
                                                    if (goodPWD.findString(newPWD1)==1 && !goodOnes.contains(newPWD1)){
                                                        System.out.println(newPWD1);
														goodOnes.add(newPWD1);
                                                        count++;
                                                    }
                                                    j++;
                                                }
                                                j=0;
                                            }
                                            i++;
                                        }
                                        i=0;
                                    }
                                    k++;
                                }
                                k=0;
                            }
                            x++;
                        }
                        x=0;
                    }
                    
                    if(count<10){
                        //System.out.println("if5");
                        while(count<10 && y<alphabet.length()){
                            newPWD3 = ""+alphabet.charAt(y);
                            if(count<10 && goodPWD.findString(newPWD3)==0){
                                while(count<10 && x<alphabet.length()){
                                    s = newPWD3 + alphabet.charAt(x);
                                    if(count<10 && goodPWD.findString(s)==0){
                                        while(count<10 && k<alphabet.length()){
                                            newPWD2 = s+ alphabet.charAt(k);
                                            if(count<10 && goodPWD.findString(newPWD2)==0){
                                                while(count<10 && i<alphabet.length()){
                                                    newPWD = newPWD2+alphabet.charAt(i);
                                                    if(goodPWD.findString(newPWD)==0){
                                                        while(count<10 && j<alphabet.length()){
                                                            newPWD1 = newPWD + alphabet.charAt(j);
                                                            if (goodPWD.findString(newPWD1)==1 && !goodOnes.contains(newPWD1)){
                                                                System.out.println(newPWD1);
																goodOnes.add(newPWD1);
                                                                count++;
                                                            }
                                                            j++;
                                                        }
                                                        j=0;
                                                    }
                                                    i++;
                                                }
                                                i=0;
                                            }
                                            k++;
                                        }
                                        k=0;
                                    }
                                    x++;
                                }
                                x=0;
                            }
                            y++;
                        }
                        y=0;
                    }
                }
                
                System.out.println("Would you like to check another password? y or n:\t");
                loopCont = kb.next();
                
                while(!loopCont.equalsIgnoreCase("y") && !loopCont.equalsIgnoreCase("n")){
                    System.out.print("\nPlease enter y or n:\t");
                    loopCont = kb.next();
                }
            }
        }
        
    }
    
    
    public static char replace(char symbol){
        if(symbol == '7'){
            return 't';
        }
        else if(symbol == '4'){
            return 'a';
        }
        else if(symbol == '0'){
            return 'o';
        }
        else if(symbol == '3'){
            return 'e';
        }
        else if(symbol == '1'){
            return 'l';
        }
        //else if(symbol == '!'){
        //    return 'i';
        //}
        else if(symbol == '$'){
            return 's';
        }
        else{
            return symbol;
        }
    }
    
    public static boolean checkCharRequirements(char c1, char c2, char c3, char c4, char c5){
        
        //"bcdefghjklmnopqrstuvwxyz02356789$^_*!@"
        int letters=0;
        int numbers=0;
        int symbols=0;
        if(c1 == '$' || c1 == '^' || c1 == '_' || c1 == '*' || c1 == '!' || c1 == '@'){
            symbols++; 
        }
        if(c2 == '$' || c2 == '^' || c2 == '_' || c2 == '*' || c2 == '!' || c2 == '@'){
            symbols++; 
        }
        if(c3 == '$' || c3 == '^' || c3 == '_' || c3 == '*' || c3 == '!' || c3 == '@'){
            symbols++; 
        }
        if(c4 == '$' || c4 == '^' || c4 == '_' || c4 == '*' || c4 == '!' || c4 == '@'){
            symbols++; 
        }
        if(c5 == '$' || c5 == '^' || c5 == '_' || c5 == '*' || c5 == '!' || c5 == '@'){
            symbols++; 
        }
        if(symbols>2 || symbols < 1){
            return false;
        }
        
        if(c1 == '0' || c1 == '1' || c1 == '2' || c1 == '3' || c1 == '4' || c1 == '5' || c1 == '6' || c1 == '7' || c1 == '8' || c1 == '9'){
            numbers++; 
        }
        if(c2 == '0' || c2 == '1' || c2 == '2' || c2 == '3' || c2 == '4' || c2 == '5' || c2 == '6' || c2 == '7' || c2 == '8' || c2 == '9'){
            numbers++; 
        }
        if(c3 == '0' || c3 == '1' || c3 == '2' || c3 == '3' || c3 == '4' || c3 == '5' || c3 == '6' || c3 == '7' || c3 == '8' || c3 == '9'){
            numbers++; 
        }
        if(c4 == '0' || c4 == '1' || c4 == '2' || c4 == '3' || c4 == '4' || c4 == '5' || c4 == '6' || c4 == '7' || c4 == '8' || c4 == '9'){
            numbers++; 
        }
        if(c5 == '0' || c5 == '1' || c5 == '2' || c5 == '3' || c5 == '4' || c5 == '5' || c5 == '6' || c5 == '7' || c5 == '8' || c5 == '9'){
            numbers++; 
        }
        if(numbers>2 || numbers< 1){
            return false;
        }
        return true;
    }
    
}
