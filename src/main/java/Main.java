import javafx.scene.Node;
import javafx.util.Pair;
import org.checkerframework.checker.units.qual.A;
import org.json.JSONObject;

import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) {


        /*
        try (BufferedReader reader = new BufferedReader(new FileReader("rusDict-new.txt"));
        FileWriter file = new FileWriter("morfRu.txt",false)){

            String line;


            while ((line = reader.readLine()) != null) {

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
*/

     LangTypos_v2 converter = new LangTypos_v2();
     converter.loadDictionaries();
     //converter.viewDict();
        ArrayDeque<String> errorStrings = new ArrayDeque(10);


        Scanner input = new Scanner(System.in);



        while (true) {
            System.out.println("Введите строку");
            String message = input.nextLine();
            if(message.equals("q")){
                break;
            }


            //System.out.println(Porter.stem(message));
            //System.out.println(message.matches("[\\w|\\W]\\p{InCyrillic}"));
            //System.out.println(!message.matches("(\\p{L1})*"));
            //System.out.println(  message.replaceAll("\\p{P}",""));

            if(!message.equals(converter.convertString(message))){
                errorStrings.push(converter.convertString(message));
            }

            System.out.println(converter.convertString(message));
            System.out.println();




        }

        for (String s:errorStrings) {
            System.out.println(s);
        }


        //System.out.println(converter.convertString(inp));
        /*
        inp = "И Сережа тоже";
        System.out.println(converter.convertString(inp));
        inp = "Dvtcnt c k.,jq 'njq";
        System.out.println(converter.convertString(inp));
        inp = "<hfkb dc` r j,tle";
        System.out.println(converter.convertString(inp));
        inp = "И говорил руддщ цщкдв фтщерук";
        System.out.println(converter.convertString(inp));
        inp = "ьфтн нуфкы пщ";
        System.out.println(converter.convertString(inp));
        inp = "Vfvf мыла hfve Лфззф";
        System.out.println(converter.convertString(inp));



        //System.out.println(converter.containsWord(converter.convertLayout("<hfkb","en","ru")));


        /*
        Map<String,String> dict = new HashMap<>();




        try (BufferedReader reader = new BufferedReader(new FileReader("english.txt"))){

            String line;

            while ((line = reader.readLine()) != null) {
                dict.put(line,converter.convertLayout(line,"en","ru"));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject jsonDict = new JSONObject(dict); // json


        try (FileWriter file = new FileWriter("dictEnToRu.json",false)) {

            file.write(jsonDict.toString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

*/



/*TODO:Работает


        LangTypos converter = new LangTypos();

        Map<String,String> dict = new HashMap<>();




        try (BufferedReader br = new BufferedReader(new FileReader("rusDict-new.txt"))) {

            String line;

            while ((line = br.readLine()) != null) {
                dict.put(line,converter.convertLayout(line));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject jsonDict = new JSONObject(dict); // json


        try (FileWriter file = new FileWriter("dictRuToEn-mini-v2.json",false)) {

            file.write(jsonDict.toString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

/* TODO:Работает

        try (BufferedReader reader = new BufferedReader(new FileReader("dictRuToEn.json"))) {
            String response = reader.lines().collect(Collectors.joining());
            JSONObject jObject = new JSONObject(response); // json
            System.out.println(jObject.toMap().get("оренбургская"));
        } catch (IOException e) {
            e.printStackTrace();
        }
*/


/*
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(ClassLoader.getSystemClassLoader()
                        .getResourceAsStream("rusDict.txt"))));
             FileWriter writer = new FileWriter("rusDict-new.txt", false)) {

            String line;
            while ((line = br.readLine()) != null) {
              String newLine = line.substring(0,line.indexOf("#"));
                System.out.println(newLine);
                writer.write(newLine+'\n');
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

 */


    }


}
