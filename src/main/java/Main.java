import org.json.JSONObject;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
/*
        LangTypos converter = new LangTypos();

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




        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(ClassLoader.getSystemClassLoader()
                        .getResourceAsStream("russian.txt"))))) {

            String line;

            while ((line = br.readLine()) != null) {
                dict.put(line,converter.convertLayout(line,"ru","en"));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject jsonDict = new JSONObject(dict); // json


        try (FileWriter file = new FileWriter("dictRuToEn.json",false)) {

            file.write(jsonDict.toString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
*/
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
