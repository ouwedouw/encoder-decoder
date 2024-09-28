import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays; 
import java.util.Random;
import java.util.Scanner;

public class encoder {
    static String[] letters = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"," "};
    static String[] codekey = {"a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
    public static int[] solutionArray = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26};

    public static void main(String[] args) {
        genkey();
        encoder();
    }

    static void genkey(){
        Random rand = new Random();
		
		for (int i = 0; i < solutionArray.length; i++) {
			int randomIndexToSwap = rand.nextInt(solutionArray.length);
			int temp = solutionArray[randomIndexToSwap];
			solutionArray[randomIndexToSwap] = solutionArray[i];
			solutionArray[i] = temp;
		}
		System.out.println(Arrays.toString(solutionArray));

        for (int i = 0; i < letters.length; i++) {
            int x = (int)Array.get(solutionArray, i);
            String a = (String)Array.get(letters, i);
            System.out.println(x);
            System.out.println(a);
            codekey[x] = a;
        }
        System.out.println(Arrays.toString(codekey));
    }

    static void encoder(){
        String data = "lol";
        File myObj = new File("");
        String encodedData = "";
        try {
            myObj = new File("test.txt");
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
            for (int a = 0; (a < letters.length) && (index == -1); a++) {
                String letterind = (String)Array.get(letters, a);
                if (letterind.contains(Character.toString(c))) {
                    index = a;
                    String code = (String)Array.get(codekey, a); 
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

        try {
            File myObj2 = new File("key.txt");
            FileWriter myWriter = new FileWriter(myObj2);
            myWriter.write(Arrays.toString(solutionArray));
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
