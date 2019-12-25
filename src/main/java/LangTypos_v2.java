import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;

public class LangTypos_v2 {


    BiMap<Character, Character> enToRus;
    BiMap<Character, Character> rusToEn;

    HashSet<String> dictRu;
    HashSet<String> dictEn;

    HashSet<String> morfRuDict;

    StringBuilder resultMessage;
    StringBuilder resultWord;


    public LangTypos_v2() {
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


    }

    public void loadDictionaries() {
        dictRu = new HashSet<>();
        dictEn = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(ClassLoader.getSystemClassLoader()
                        .getResourceAsStream("rusDict-new.txt"))))) {

            String line;
            while ((line = br.readLine()) != null) {

               dictRu.add(line);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(ClassLoader.getSystemClassLoader()
                        .getResourceAsStream("english.txt"))))) {

            String line;
            while ((line = br.readLine()) != null) {

                dictEn.add(line);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        morfRuDict = new HashSet<>();
        for (String key:dictRu) {
            morfRuDict.add(Porter.stem(key));
        }

    }

    public String convertLayout(String string) {


        resultWord.delete(0, resultWord.length());
        for (int i = 0; i < string.length(); i++) {
            if (enToRus.containsKey(string.charAt(i))) {
                resultWord.append(enToRus.get(string.charAt(i)));
            } else if (rusToEn.containsKey(string.charAt(i))) {
                resultWord.append(rusToEn.get(string.charAt(i)));
            } else {
                resultWord.append(string.charAt(i));
            }
        }

        return resultWord.toString();
    }

    public String convertString(String message) {

        resultMessage.delete(0, resultMessage.length());

        for (String word : message.split(" ")) {

            String revLayout = convertLayout(word);
            //если слово в правильной расскладке найдено в словарях по ключу
            if (dictRu.contains(word.toLowerCase()) || dictEn.contains(word.toLowerCase())) {
                resultMessage.append(word);//то возвращаем его без изменений
            }
            else if(dictRu.contains(revLayout.toLowerCase()) || dictEn.contains(revLayout.toLowerCase())){
                resultMessage.append(revLayout);
            }

            else {
                if(!revLayout.matches("(\\p{L1})*")){
                    if(morfRuDict.contains(Porter.stem(revLayout.replaceAll("(\\p{P}*)","")))){
                        resultMessage.append(revLayout);
                    }
                    else {
                        resultMessage.append(word);
                    }

                }
                else {
                    resultMessage.append(word);
                }

            }

            resultMessage.append(" ");
        }

        return resultMessage.toString().trim();
    }





}
