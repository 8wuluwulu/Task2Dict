import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileRepository {
    private final String filePath;

    public FileRepository(String filePath) {
        this.filePath = filePath;
    }

    public Map<String, String> load() {
        Map<String, String> data = new HashMap<>();
        File file = new File(filePath);
        if (!file.exists()) return data;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 2) data.put(parts[0], parts[1]);
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
        return data;
    }

    public void save(Map<String, String> data) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, String> entry : data.entrySet()) {
                writer.println(entry.getKey() + "=" + entry.getValue());
            }
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении: " + e.getMessage());
        }
    }
}