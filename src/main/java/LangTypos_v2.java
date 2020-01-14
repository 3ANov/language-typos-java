import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Objects;

/** Класс для исправления раскладки текста содержащего русские и английские слова.
 * Использует проверку по словарям и алгоритм стемминга Портера
 * @see <a href="https://github.com/vpominchuk/StemmerPorterRU">реализация алгоритма стеммера Портера на java</a>
 *
 *
 * @author 3ANov
 * Мой e-mail: <a href="mailto:3anovdev@gmail.com">3anovdev@gmail.com</a>
 */
public class LangTypos_v2 {


    BiMap<Character, Character> enToRusBiMap;
    BiMap<Character, Character> rusToEnBiMap;

    HashSet<String> russianDictionary;
    HashSet<String> englishDictionary;

    HashSet<String> morFormRussianDictionary;/** используется в {@link #convertString(String)} */

    /** Выделил отдельно StringBuilder'ы чтобы их не пересоздавали методы,
     * но возможно будет удобней оставить это всё в методах */
    StringBuilder resultMessage;/** используется в {@link #convertString(String)} */
    StringBuilder resultWord;/** используется в {@link #mirrorLayout(String)} */

    /** Основной конструктор класса - сопоставляет английские и русские символы
     *
     */
    public LangTypos_v2() {
        enToRusBiMap = HashBiMap.create();
        enToRusBiMap.put('`', 'ё');
        enToRusBiMap.put('q', 'й');
        enToRusBiMap.put('w', 'ц');
        enToRusBiMap.put('e', 'у');
        enToRusBiMap.put('r', 'к');
        enToRusBiMap.put('t', 'е');
        enToRusBiMap.put('y', 'н');
        enToRusBiMap.put('u', 'г');
        enToRusBiMap.put('i', 'ш');
        enToRusBiMap.put('o', 'щ');
        enToRusBiMap.put('p', 'з');
        enToRusBiMap.put('[', 'х');
        enToRusBiMap.put(']', 'ъ');
        enToRusBiMap.put('a', 'ф');
        enToRusBiMap.put('s', 'ы');
        enToRusBiMap.put('d', 'в');
        enToRusBiMap.put('f', 'а');
        enToRusBiMap.put('g', 'п');
        enToRusBiMap.put('h', 'р');
        enToRusBiMap.put('j', 'о');
        enToRusBiMap.put('k', 'л');
        enToRusBiMap.put('l', 'д');
        enToRusBiMap.put(';', 'ж');
        enToRusBiMap.put('\'', 'э');
        enToRusBiMap.put('z', 'я');
        enToRusBiMap.put('x', 'ч');
        enToRusBiMap.put('c', 'с');
        enToRusBiMap.put('v', 'м');
        enToRusBiMap.put('b', 'и');
        enToRusBiMap.put('n', 'т');
        enToRusBiMap.put('m', 'ь');
        enToRusBiMap.put(',', 'б');
        enToRusBiMap.put('.', 'ю');
        enToRusBiMap.put('/', '.');
        enToRusBiMap.put('~', 'Ё');
        enToRusBiMap.put('@', '"');
        enToRusBiMap.put('#', '№');
        enToRusBiMap.put('$', ';');
        enToRusBiMap.put('^', ':');
        enToRusBiMap.put('&', '?');
        enToRusBiMap.put('|', '/');
        enToRusBiMap.put('Q', 'Й');
        enToRusBiMap.put('W', 'Ц');
        enToRusBiMap.put('E', 'У');
        enToRusBiMap.put('R', 'К');
        enToRusBiMap.put('T', 'Е');
        enToRusBiMap.put('Y', 'Н');
        enToRusBiMap.put('U', 'Г');
        enToRusBiMap.put('I', 'Ш');
        enToRusBiMap.put('O', 'Щ');
        enToRusBiMap.put('P', 'З');
        enToRusBiMap.put('{', 'Х');
        enToRusBiMap.put('}', 'Ъ');
        enToRusBiMap.put('A', 'Ф');
        enToRusBiMap.put('S', 'Ы');
        enToRusBiMap.put('D', 'В');
        enToRusBiMap.put('F', 'А');
        enToRusBiMap.put('G', 'П');
        enToRusBiMap.put('H', 'Р');
        enToRusBiMap.put('J', 'О');
        enToRusBiMap.put('K', 'Л');
        enToRusBiMap.put('L', 'Д');
        enToRusBiMap.put(':', 'Ж');
        enToRusBiMap.put('"', 'Э');
        enToRusBiMap.put('Z', 'Я');
        enToRusBiMap.put('X', 'Ч');
        enToRusBiMap.put('C', 'С');
        enToRusBiMap.put('V', 'М');
        enToRusBiMap.put('B', 'И');
        enToRusBiMap.put('N', 'Т');
        enToRusBiMap.put('M', 'Ь');
        enToRusBiMap.put('<', 'Б');
        enToRusBiMap.put('>', 'Ю');
        enToRusBiMap.put('?', ',');
        enToRusBiMap.put(' ', ' ');

        rusToEnBiMap = enToRusBiMap.inverse();

        resultMessage = new StringBuilder();
        resultWord = new StringBuilder();


    }

    /**Загрузка словарей
     * Сделано отдельно - потому что занимает достаточно много памяти
     * rusDict-new.txt - русский словарь (загрузка произодится из папки ресурсов)
     * english.txt - словарь английских слов (загрузка произодится из папки ресурсов)
     *
     * загрузка словарей производится из папки ресурсов (resources)
     *
     */

    public void loadDictionaries() {
        russianDictionary = new HashSet<>();
        englishDictionary = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(ClassLoader.getSystemClassLoader()
                        .getResourceAsStream("rusDict-new.txt"))))) {

            String line;
            while ((line = br.readLine()) != null) {

               russianDictionary.add(line);

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

                englishDictionary.add(line);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        morFormRussianDictionary = new HashSet<>();
        for (String key: russianDictionary) {
            morFormRussianDictionary.add(Porter.stem(key));
        }

    }

    /**
     *
     * @param string входная строка которую нужно "отразить" в другой расскладке
     * @return возвращается строка в "зеркальной" раскладке
     * example: "многоточие" -> "vyjujnjxbt"
     */
    public String mirrorLayout(String string) {
        
        resultWord.delete(0, resultWord.length());
        for (int i = 0; i < string.length(); i++) {
            if (enToRusBiMap.containsKey(string.charAt(i))) {
                resultWord.append(enToRusBiMap.get(string.charAt(i)));
            } else if (rusToEnBiMap.containsKey(string.charAt(i))) {
                resultWord.append(rusToEnBiMap.get(string.charAt(i)));
            } else {
                resultWord.append(string.charAt(i));
            }
        }

        return resultWord.toString();
    }

    /**Метод который исправляет расскладку (если текст содержит русские или английские слова)
     * В основе проверка по словарям английских и русских слов + проверка слов через алгоритм стемминга Портера
     *
     * @param message Входная строка раскладку которой нужно исправить
     * @return Выходная строка с "исправленной" раскладкой
     * example: "многоточие это знак препинания" -> "многоточие это знак препинания"
     * example: "многоточие это pyfr ghtgbyfybz" -> "многоточие это знак препинания"
     */

    public String convertString(String message) {

        resultMessage.delete(0, resultMessage.length());

        for (String word : message.split(" ")) {

            String revLayout = mirrorLayout(word);
            //если слово в правильной расскладке найдено в словарях по ключу
            if (russianDictionary.contains(word.toLowerCase()) || englishDictionary.contains(word.toLowerCase()) || englishDictionary.contains(word)) {
                resultMessage.append(word);//то возвращаем его без изменений
            }
            else if(russianDictionary.contains(revLayout.toLowerCase()) || englishDictionary.contains(revLayout.toLowerCase()) || englishDictionary.contains(revLayout)){
                resultMessage.append(revLayout);
            }

            else {
                //проверка для слов которые запсаны вместе со знаками препинания
                if(!revLayout.matches("(\\p{L1})*")){//отдельная проверка для русских символов е
                    // (пришлось использовать инвертированное условие проверки
                    if(morFormRussianDictionary.contains(Porter.stem(revLayout.replaceAll("(\\p{P}*)","")))){ //проверка слова без знаков
                        // с использование алгорима стемминга Портера
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
