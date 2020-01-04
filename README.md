# language-typos-java
Класс исправляющий раскладку текста который содержит английские и русские слова.

_Идеи были заимствованы из [github.com/rin-nas/language-typos](https://github.com/rin-nas/language-typos)_

_Использовалась реализаци алгоритма стемминга Портера отсюда [github.com/vpominchuk/StemmerPorterRU](https://github.com/vpominchuk/StemmerPorterRU)_

### Примеры входных и выходных данных:
*  _"многоточие это pyfr ghtgbyfybz" -> "многоточие это знак препинания"_
*  _"многоточие это знак препинания" -> "многоточие это знак препинания"_
*  _"руддщ цщкдв ьн дшееду гыук" -> "hello world my little user"_
*  _"hello world my little user" -> "hello world my little user"_

### Использование 
Для использование необходимо добавить в зависимости класс [BiMap из пакета Google Guava ](https://mvnrepository.com/artifact/com.google.guava/guava/28.1-jre)


>_Если вы решились использовать класс **LangTypos** то в зависимости также необходимо добавить [Jackson Databind](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind/2.10.1) для работы с **json**-файлами (или любую другую библиотеку для работы с json-файлами которая может отобразить **json**-файл в **HashMap** (см метод **loadDictionaries** в классе **LangTypos**)_

```java 
LangTypos_v2 converter = new LangTypos_v2();
converter.loadDictionaries();
converter.convertString("многоточие это pyfr ghtgbyfybz") // return: "многоточие это знак препинания"
```
### Что есть ещё 
По мере создания программы были сгенерированы словари в формате json содержащие слова в русской и английской расскладке. Словарь 
