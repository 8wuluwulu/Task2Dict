import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileRepository {
    private final String path;

    public FileRepository(String path) { this.path = path; }

    public Map<String, String> load() {
        Map<String, String> data = new HashMap<>();
        File file = new File(path);
        if (!file.exists()) return data;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 2) data.put(parts[0], parts[1]);
            }
        } catch (IOException e) { System.out.println("Ошибка чтения"); }
        return data;
    }

    public void save(Map<String, String> data) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path))) {
            data.forEach((k, v) -> pw.println(k + "=" + v));
        } catch (IOException e) { System.out.println("Ошибка записи"); }
    }
}