import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class endecoder {
    static String[] letters = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"," "};
    static String[] codekey = {"a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
    public static void main(String[] args) {
        decodekey();
        decoder();
    }

    static void decodekey(){
        File keyFile = new File("key.txt");
        int[] key = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        String keyData = "";
        try {
            Scanner file = new Scanner(keyFile);
            while (file.hasNextLine()) {
                keyData = file.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }

        String input = keyData;
        input = input.replaceAll("\\[|\\]", ""); // Remove brackets
        String[] n1 = input.split(","); // Split by comma
        
        List<Integer> numberList = new ArrayList<>();
        for (String num : n1) {
            num = num.trim(); // Trim spaces
            numberList.add(Integer.parseInt(num)); // Parse and add to list
        }
        // Convert list to array if needed
        Integer[] numberArray = numberList.toArray(new Integer[0]);
        System.out.println(Arrays.toString(numberArray));
        

        for (int i = 0; i < letters.length; i++) {
            int x = (int)Array.get(numberArray, i);
            String a = (String)Array.get(letters, i);
            codekey[x] = a;
        }
        System.out.println(Arrays.toString(codekey));
    }

    static void decoder(){
        String data = "lol";
        File myObj = new File("test.txt");
        String encodedData = "";
        try {
            Scanner file = new Scanner(myObj);
            while (file.hasNextLine()) {
                data = file.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }

        for (int i = 0; i < data.length(); i++) {
            char c = data.charAt(i);
            int index = -1;
            for (int a = 0; (a < codekey.length) && (index == -1); a++) {
                String letterind = (String)Array.get(codekey, a);
                if (letterind.contains(Character.toString(c))) {
                    index = a;
                    String code = (String)Array.get(letters, a); 
                    encodedData = encodedData + code;
                }
            }
        }

        try {
            System.out.println("writing to "+ myObj);
            FileWriter myWriter = new FileWriter(myObj);
            myWriter.write(encodedData);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }
}
//change