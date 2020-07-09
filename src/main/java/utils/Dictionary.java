package utils;

import exception.NotDictionaryElementExist;
import model.repository.DictionaryRepository;

public class Dictionary {
    public static final String DIRECTORY = "directory";
    private static final Dictionary DICTIONARY = new Dictionary();
    private DictionaryRepository repository = new DictionaryRepository();
    private Dictionary() {
    }

    public static Dictionary getInstance() {
        return DICTIONARY;
    }

    public String getValue(String name) throws NotDictionaryElementExist {
            return repository.findByName(name);
    }

    public void changeValue(String name, String value) {
        repository.update(value, name);
    }



}
