# language-typos-java - исправление раскладки текста (реализация на Java)

[Класс](https://github.com/3ANov/language-typos-java/blob/master/src/main/java/LangTypos_v2.java) исправляющий раскладку текста который содержит английские и русские слова.

_Идеи были заимствованы из:_
* _[github.com/rin-nas/language-typos](https://github.com/rin-nas/language-typos)_

* _[Mahou (魔法) - Волшебный переключатель раскладок.](https://github.com/BladeMight/Mahou)_

* _Использовалась реализаци алгоритма стемминга Портера отсюда: [github.com/vpominchuk/StemmerPorterRU](https://github.com/vpominchuk/StemmerPorterRU)_

_**Используются словари**:_
* _Орфографический словарь п/р проф. Лопатина (2000 год) (русский язык),[словари русского языка для скачивания](http://www.speakrus.ru/dict/)_
* _Список английских слов (более чем 58000 слов) [ссылка](http://www.mieliestronk.com/wordlist.html)_

## Примеры входных и выходных данных:
*  _"многоточие это pyfr ghtgbyfybz" -> "многоточие это знак препинания"_
*  _"многоточие это знак препинания" -> "многоточие это знак препинания"_
*  _"руддщ цщкдв ьн дшееду гыук" -> "hello world my little user"_
*  _"hello world my little user" -> "hello world my little user"_

## Использование 

```java 
LangTypos_v2 converter = new LangTypos_v2();
converter.loadDictionaries();
converter.convertString("многоточие это pyfr ghtgbyfybz") // return: "многоточие это знак препинания"
```

Для использование необходимо добавить в зависимости класс [BiMap из пакета Google Guava ](https://mvnrepository.com/artifact/com.google.guava/guava/28.1-jre)


>_Если вы решились использовать класс **LangTypos** то в зависимости также необходимо добавить [Jackson Databind](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind/2.10.1) для работы с **json**-файлами (или любую другую библиотеку для работы с json-файлами которая может отобразить **json**-файл в **HashMap** (см метод **loadDictionaries** в классе **LangTypos**)_



## Что есть ещё 
По мере создания программы были сгенерированы словари в формате json содержащие слова в русской и английской расскладке. Словарь **dictRuToEn-mini.json** содержит пары типа: _"песочить":"gtcjxbnm", "рукаводержатель":"herfdjlth;fntkm"_. Словарь **dictEnToRu.json** содержит пары типа: _"frowning":"акщцтштп","undermining":"гтвукьштштп"_. Словарь **dictRuToEn.json** содержит более чем 2 376 434 словоформ в паре с версией в английской расскладке[ссылка](https://dikmax.name/post/russian-dictionary/).

### По всяким вопросам, можете писать на мою почту: 3anovdev@gmail.com
