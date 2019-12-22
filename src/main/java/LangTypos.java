import com.google.common.collect.ImmutableBiMap;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LangTypos {


    ImmutableBiMap<Object, Object> enToRus;
    ImmutableBiMap<Object, Object> rusToEn;

    Map<String, Object> dictRuToEn;
    Map<String, Object> dictEnToRu;



   public LangTypos(){
       enToRus = new ImmutableBiMap.Builder<>()
               .put('`', 'ё')
               .put('q', 'й')
               .put('w', 'ц')
               .put('e', 'у')
               .put('r', 'к')
               .put('t', 'е')
               .put('y', 'н')
               .put('u', 'г')
               .put('i', 'ш')
               .put('o', 'щ')
               .put('p', 'з')
               .put('[', 'х')
               .put(']', 'ъ')
               .put('a', 'ф')
               .put('s', 'ы')
               .put('d', 'в')
               .put('f', 'а')
               .put('g', 'п')
               .put('h', 'р')
               .put('j', 'о')
               .put('k', 'л')
               .put('l', 'д')
               .put(';', 'ж')
               .put('\'','э')
               .put('z', 'я')
               .put('x', 'ч')
               .put('c', 'с')
               .put('v', 'м')
               .put('b', 'и')
               .put('n', 'т')
               .put('m', 'ь')
               .put(',', 'б')
               .put('.', 'ю')
               .put('/', '.')
               .put('~', 'Ё')
               .put('@', '"')
               .put('#', '№')
               .put('$', ';')
               .put('^', ':')
               .put('&', '?')
               .put('|', '/')
               .put('Q', 'Й')
               .put('W', 'Ц')
               .put('E', 'У')
               .put('R', 'К')
               .put('T', 'Е')
               .put('Y', 'Н')
               .put('U', 'Г')
               .put('I', 'Ш')
               .put('O', 'Щ')
               .put('P', 'З')
               .put('{', 'Х')
               .put('}', 'Ъ')
               .put('A', 'Ф')
               .put('S', 'Ы')
               .put('D', 'В')
               .put('F', 'А')
               .put('G', 'П')
               .put('H', 'Р')
               .put('J', 'О')
               .put('K', 'Л')
               .put('L', 'Д')
               .put(':', 'Ж')
               .put('"', 'Э')
               .put('Z', 'Я')
               .put('X', 'Ч')
               .put('C', 'С')
               .put('V', 'М')
               .put('B', 'И')
               .put('N', 'Т')
               .put('M', 'Ь')
               .put('<', 'Б')
               .put('>', 'Ю')
               .put('?', ',')
               .put(' ', ' ')
               .build();

       rusToEn = enToRus.inverse();


      loadDictionaries();


   }

   private void loadDictionaries(){

       try (BufferedReader fileRuToEn = new BufferedReader(new InputStreamReader(
               Objects.requireNonNull(ClassLoader.getSystemClassLoader()
                       .getResourceAsStream("dictRuToEn.json"))))) {
           String response = fileRuToEn.lines().collect(Collectors.joining());
           JSONObject jObject = new JSONObject(response); // json
           dictRuToEn = jObject.toMap();
           //System.out.println(jObject.toMap().get("оренбургская"));
       } catch (IOException e) {
           e.printStackTrace();
       }
       
       try (BufferedReader fileEnToRu = new BufferedReader(new InputStreamReader(
               Objects.requireNonNull(ClassLoader.getSystemClassLoader()
                       .getResourceAsStream("dictEnToRu.json"))))) {
           String response = fileEnToRu.lines().collect(Collectors.joining());
           JSONObject jObject = new JSONObject(response); // json
           dictEnToRu = jObject.toMap();
       } catch (IOException e) {
           e.printStackTrace();
       }


   }

    public String convertLayout(String string, String input, String output) {

        StringBuilder result = new StringBuilder();

        if (input.equals("en") & output.equals("ru")) {
            for (int i = 0; i < string.length(); i++) {
                //result += enToRus.get(string.charAt(i));
                result.append(enToRus.get(string.charAt(i)));
            }
        }

        if (input.equals("ru") & output.equals("en")) {
            for (int i = 0; i < string.length(); i++) {
                //result += rusToEn.get(string.charAt(i));
                result.append(rusToEn.get(string.charAt(i)));
            }
        }

        return result.toString();
    }

    public String convertString(String message) {
        StringBuilder result = new StringBuilder();
        String  message_clone;
        Pattern enPattern = Pattern.compile("[a-zA-Z]");
        Matcher enMatcher = enPattern.matcher(message);
        Pattern ruPattern = Pattern.compile("[а-бА-Б]");
        Matcher ruMatcher = ruPattern.matcher(message);

        if (enMatcher.find()){
            message_clone = convertLayout(message,"en","ru");
        }

        if (ruMatcher.find()){
            message_clone = convertLayout(message,"en","ru");
        }


        for (String word:message.split(" ")) {
           if(dictRuToEn.containsKey(word.toLowerCase()) || dictEnToRu.containsKey(word.toLowerCase())){
               result.append(word);
               result.append(" ");
            }
           else if(dictRuToEn.containsValue(word.toLowerCase())){
               result.append(convertLayout(word,"en","ru"));
               result.append(" ");
           }
           else if(dictEnToRu.containsValue(word.toLowerCase())){
               result.append(convertLayout(word,"ru","en"));
               result.append(" ");
           }

           else {
               result.append(word);
               result.append(" ");
           }
        }

       return result.toString().trim();
    }

    public boolean containsWord(String word){
        if(dictRuToEn.containsValue(word.toLowerCase()))
            return true;
        if(dictEnToRu.containsValue(word.toLowerCase()))
            return true;
       if(dictRuToEn.containsKey(word.toLowerCase()))
           return true;
       if(dictEnToRu.containsKey(word.toLowerCase()))
           return true;
       return false;
    }
}
