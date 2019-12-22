import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class LangTypos {


    BiMap<Character, Character> enToRus;
    BiMap<Character, Character> rusToEn;

    Map<String, Object> dictRuToEn;
    Map<String, Object> dictEnToRu;

    StringBuilder resultMessage;
    StringBuilder resultWord;


    public LangTypos() {
        enToRus = HashBiMap.create();
        enToRus.put('`', 'ё');
        enToRus.put('q', 'й');
        enToRus.put('w', 'ц');
        enToRus.put('e', 'у');
        enToRus.put('r', 'к');
        enToRus.put('t', 'е');
        enToRus.put('y', 'н');
        enToRus.put('u', 'г');
        enToRus.put('i', 'ш');
        enToRus.put('o', 'щ');
        enToRus.put('p', 'з');
        enToRus.put('[', 'х');
        enToRus.put(']', 'ъ');
        enToRus.put('a', 'ф');
        enToRus.put('s', 'ы');
        enToRus.put('d', 'в');
        enToRus.put('f', 'а');
        enToRus.put('g', 'п');
        enToRus.put('h', 'р');
        enToRus.put('j', 'о');
        enToRus.put('k', 'л');
        enToRus.put('l', 'д');
        enToRus.put(';', 'ж');
        enToRus.put('\'', 'э');
        enToRus.put('z', 'я');
        enToRus.put('x', 'ч');
        enToRus.put('c', 'с');
        enToRus.put('v', 'м');
        enToRus.put('b', 'и');
        enToRus.put('n', 'т');
        enToRus.put('m', 'ь');
        enToRus.put(',', 'б');
        enToRus.put('.', 'ю');
        enToRus.put('/', '.');
        enToRus.put('~', 'Ё');
        enToRus.put('@', '"');
        enToRus.put('#', '№');
        enToRus.put('$', ';');
        enToRus.put('^', ':');
        enToRus.put('&', '?');
        enToRus.put('|', '/');
        enToRus.put('Q', 'Й');
        enToRus.put('W', 'Ц');
        enToRus.put('E', 'У');
        enToRus.put('R', 'К');
        enToRus.put('T', 'Е');
        enToRus.put('Y', 'Н');
        enToRus.put('U', 'Г');
        enToRus.put('I', 'Ш');
        enToRus.put('O', 'Щ');
        enToRus.put('P', 'З');
        enToRus.put('{', 'Х');
        enToRus.put('}', 'Ъ');
        enToRus.put('A', 'Ф');
        enToRus.put('S', 'Ы');
        enToRus.put('D', 'В');
        enToRus.put('F', 'А');
        enToRus.put('G', 'П');
        enToRus.put('H', 'Р');
        enToRus.put('J', 'О');
        enToRus.put('K', 'Л');
        enToRus.put('L', 'Д');
        enToRus.put(':', 'Ж');
        enToRus.put('"', 'Э');
        enToRus.put('Z', 'Я');
        enToRus.put('X', 'Ч');
        enToRus.put('C', 'С');
        enToRus.put('V', 'М');
        enToRus.put('B', 'И');
        enToRus.put('N', 'Т');
        enToRus.put('M', 'Ь');
        enToRus.put('<', 'Б');
        enToRus.put('>', 'Ю');
        enToRus.put('?', ',');
        enToRus.put(' ', ' ');

        rusToEn = enToRus.inverse();

        resultMessage = new StringBuilder();
        resultWord = new StringBuilder();

        loadDictionaries();


    }

    private void loadDictionaries() {

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

       resultWord.delete(0,resultWord.length());

        if (input.equals("en") & output.equals("ru")) {
            for (int i = 0; i < string.length(); i++) {
                //result += enToRus.get(string.charAt(i));
                resultWord.append(enToRus.get(string.charAt(i)));
            }
        }

        if (input.equals("ru") & output.equals("en")) {
            for (int i = 0; i < string.length(); i++) {
                //result += rusToEn.get(string.charAt(i));
                resultWord.append(rusToEn.get(string.charAt(i)));
            }
        }

        return  resultWord.toString();
    }

    public String convertString(String message) {


        resultMessage.delete(0, resultMessage.length());
        /*
        String  message_clone;
        Pattern enPattern = Pattern.compile("[a-zA-Z]");
        Matcher enMatcher = enPattern.matcher(message);
        Pattern ruPattern = Pattern.compile("[а-бА-Б]");
        Matcher ruMatcher = ruPattern.matcher(message);
*/


        for (String word : message.split(" ")) {

            if (dictRuToEn.containsKey(word.toLowerCase()) || dictEnToRu.containsKey(word.toLowerCase())) {
                resultMessage.append(word);
                resultMessage.append(" ");
            } else if (dictRuToEn.containsValue(word.toLowerCase()) ||
                    dictRuToEn.containsKey(convertLayout(word, "en", "ru").toLowerCase())) {
                resultMessage.append(convertLayout(word, "en", "ru"));
                resultMessage.append(" ");
            } else if (dictEnToRu.containsValue(word.toLowerCase()) ||
                    dictRuToEn.containsKey(convertLayout(word, "ru", "en").toLowerCase())) {
                resultMessage.append(convertLayout(word, "ru", "en"));
                resultMessage.append(" ");
            } else {
                resultMessage.append(word);
                resultMessage.append(" ");
            }
        }

        return resultMessage.toString().trim();
    }

    public boolean containsWord(String word) {
        if (dictRuToEn.containsValue(word.toLowerCase()))
            return true;
        if (dictEnToRu.containsValue(word.toLowerCase()))
            return true;
        if (dictRuToEn.containsKey(word.toLowerCase()))
            return true;
        if (dictEnToRu.containsKey(word.toLowerCase()))
            return true;
        return false;
    }
}
