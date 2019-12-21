import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableBiMap;

public class LangTypos {


    ImmutableBiMap<Object, Object> enToRus;
    ImmutableBiMap<Object, Object> rusToEn;

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
   }

    public String convertLayout(String string, String input, String output) {

        String result = "";

        if (input.equals("en") & output.equals("ru")) {
            for (int i = 0; i < string.length(); i++) {
                result += enToRus.get(string.charAt(i));
            }
        }

        if (input.equals("ru") & output.equals("en")) {
            for (int i = 0; i < string.length(); i++) {
                result += rusToEn.get(string.charAt(i));
            }
        }

        return result;
    }
}
