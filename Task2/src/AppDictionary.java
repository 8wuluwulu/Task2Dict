import java.util.Map;

public class AppDictionary implements Dictionary {
    private final String name;
    private final BaseValidator validator; // Используем абстрактный класс
    private final FileRepository repository;
    private final Map<String, String> data;

    public AppDictionary(String name, BaseValidator validator, FileRepository repository) {
        this.name = name;
        this.validator = validator;
        this.repository = repository;
        this.data = repository.load();
    }

    @Override
    public void add(String key, String value) {
        if (validator.isValid(key)) {
            data.put(key, value);
            repository.save(data);
            System.out.println("Запись добавлена.");
        } else {
            System.out.println("Ключ не подходит для словаря " + name);
        }
    }

    @Override public void delete(String key) { data.remove(key); repository.save(data); }
    @Override public String find(String key) { return data.get(key); }
    @Override public Map<String, String> getAll() { return data; }
    @Override public String getName() { return name; }
}