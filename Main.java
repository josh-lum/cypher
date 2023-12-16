// okay so probably my 7th attempt at this
// will be doing the caesar cipher(shifts)
// vignere cipher ("shuffle")
// random cipher just use dcode opr something for inpiration

import java.io.FileReader;
import java.io.*;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws IOException{

        // get inital password
        Scanner scan = new Scanner(System.in);
        System.out.println("enter a password");
        String password = scan.next();





        // set up password to change or not

        System.out.println("Please enter what operation you would like to do: ");
        System.out.println("Enter 1 for caesar cipher "  + '\n' + "Enter 2 for vigenere cipher" + '\n' + "Enter 3 for pokedex cipher " + '\n' + "Enter 0 to quit");
        int passwordChoice = scan.nextInt();


        if(passwordChoice == 0){
            System.out.println("You have chosen not to change your password");

        }else if(passwordChoice == 1){
            System.out.println("You have chosen caesar cipher: ");
            System.out.println(caesarCipher(password, 19));

        }else if(passwordChoice == 2){
            System.out.println("You have chosen vigeneres cipher: ");
            System.out.println("Please enter a key to be used: ");
            String key_encrypt = scan.next();
            key_encrypt = genVigenereCipherKey(password, key_encrypt);
            System.out.println(vigenereCipherText(password, key_encrypt));

        }else if(passwordChoice == 3){
            System.out.println("work in progress still");
            nationaldexCipher(password);
        }else{
            System.out.println("not a valid input, exiting");

        }

        if(passwordChoice > 0 && passwordChoice < 4) {
            System.out.println("This doesn't save passwords so write it down or something " + '\n' + "Goodbye");
        }
    }

    public static String caesarCipher(String password, int shift){
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < password.length(); i++){
            if(Character.isUpperCase(password.charAt(i))){
                char character = (char) (((int) password.charAt(i) + shift - 65) % 26 + 65);
                sb.append(character);
            }else{
                char character = (char) (((int) password.charAt(i) + shift - 97) % 26 + 97);
                sb.append(character);
            }
        }
        return sb.toString();
    }

    // methods for vigenere cipher bc of key I overlooked this at first

    public static String genVigenereCipherKey(String password, String key_encrypt){

        for(int i = 0; ; i++){
            if(i == password.length() - 1)
                i = 0;

            if(key_encrypt.length() == password.length())
                break;
                key_encrypt += (key_encrypt.charAt(i));

        }
        return key_encrypt;
    }

    public static String vigenereCipherText(String password, String key_encyrpt){
        String str = "";
        password = toUpper(password);
        key_encyrpt = toUpper(key_encyrpt);
        for(int i = 0; i < password.length(); i++){
            //in alphabet range
            int k = (password.charAt(i) + key_encyrpt.charAt(i)) % 26;
            //to ascii
            k += 'A';
            str += (char)(k);
        }
        return str;
    }

    public static String toUpper(String password){
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < password.length(); i++){
            if(Character.isLowerCase(password.charAt(i))){
                sb.append(Character.toUpperCase(password.charAt(i)));
            }
        }
        return sb.toString();
    }

    public static String nationaldexCipher(String s) throws IOException {
        System.out.println("This cipher will sum the ASCII of your password characters and return the Pokémon Pokédex entry number and name from the National Dex as of 11/8/2023");
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            sum += s.charAt(i);
        }

        if (sum > 1017) {
            return "Sorry, the National Dex only contains 1017 entries at the time of this program. Cannot use this cipher.";
        }

        // Binary search for it (you need to complete this part)
        RandomAccessFile filename = new RandomAccessFile("NationalDex.txt", "r");
        String target = ""; // You need to set the actual target value
        int linesize = 1017;
        int lineNums = (int) (filename.length() / linesize);
        byte[] line = new byte[linesize];
        int start = 0;
        int stop = lineNums;
        int mid;

        while (start <= stop) {
            mid = (start + stop) / 2;
            filename.seek(mid * linesize);
            filename.read(line);
            String lines = new String(line);
            int compare = lines.compareTo(target);
            if (compare == 0) {
                // Found the target, do something with it
                // Example: return lines;
                return lines;
            } else if (compare < 0) {
                start = mid + 1;
            } else {
                stop = mid - 1;
            }
        }

        
        return "Target not found in the National Dex.";
    }

}
