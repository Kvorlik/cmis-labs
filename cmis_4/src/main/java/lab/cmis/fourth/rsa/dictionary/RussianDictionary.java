package lab.cmis.fourth.rsa.dictionary;

import java.util.HashMap;
import java.util.Map;

public class RussianDictionary implements Dictionary {
    public Map<Character, Integer> getDictionary() {
        Map<Character, Integer> dictionary = new HashMap<>();
        dictionary.put('А', 10);
        dictionary.put('Б', 11);
        dictionary.put('В', 12);
        dictionary.put('Г', 13);
        dictionary.put('Д', 14);
        dictionary.put('Е', 15);
        dictionary.put('Ж', 16);
        dictionary.put('З', 17);
        dictionary.put('И', 18);
        dictionary.put('Й', 19);
        dictionary.put('К', 20);
        dictionary.put('Л', 21);
        dictionary.put('М', 22);
        dictionary.put('Н', 23);
        dictionary.put('О', 24);
        dictionary.put('П', 25);
        dictionary.put('Р', 26);
        dictionary.put('С', 27);
        dictionary.put('Т', 28);
        dictionary.put('У', 29);
        dictionary.put('Ф', 30);
        dictionary.put('Х', 31);
        dictionary.put('Ц', 32);
        dictionary.put('Ч', 33);
        dictionary.put('Ш', 34);
        dictionary.put('Щ', 35);
        dictionary.put('Ъ', 36);
        dictionary.put('Ы', 37);
        dictionary.put('Ь', 38);
        dictionary.put('Э', 39);
        dictionary.put('Ю', 40);
        dictionary.put('Я', 41);
        dictionary.put(' ', 99);
        return dictionary;
    }
}
