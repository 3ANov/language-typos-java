# language-typos-java
Класс исправляющий раскладку текста который содержит английские и русские слова.
_Идеи были заимствованы из [github.com/rin-nas/language-typos](https://github.com/rin-nas/language-typos)_

_Использовалась реализаци алгоритма стемминга Портера отсюда [github.com/vpominchuk/StemmerPorterRU](https://github.com/vpominchuk/StemmerPorterRU)_

### Примеры входных и выходных данных:
*  _"многоточие это pyfr ghtgbyfybz" -> "многоточие это знак препинания"_
*  _"многоточие это знак препинания" -> "многоточие это знак препинания"_
*  _"руддщ цщкдв ьн дшееду гыук" -> "hello world my little user"_
*  _"hello world my little user" -> "hello world my little user"_

