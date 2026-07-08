import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String gamesPath = "C:/Games";
    private static final StringBuilder log = new StringBuilder();
    public static void main(String[] args) {
        log.append("Путь к Games: ").append(gamesPath).append("\n\n");
        
        File gamesDir = new File(gamesPath);
        if (!gamesDir.exists()) {
            System.out.println("Ошибка: папка Games не существует!");
            return;
        }
        
        // Создаем список директорий
        List<String> dirs = new ArrayList<>();
        dirs.add(gamesPath + "/src");
        dirs.add(gamesPath + "/res");
        dirs.add(gamesPath + "/savegames");
        dirs.add(gamesPath + "/temp");
        
        dirs.add(gamesPath + "/src/main");
        dirs.add(gamesPath + "/src/test");
        
        dirs.add(gamesPath + "/res/drawables");
        dirs.add(gamesPath + "/res/vectors");
        dirs.add(gamesPath + "/res/icons");

        List<String> files = new ArrayList<>();

        files.add(gamesPath + "/src/main/Main.java");
        files.add(gamesPath +  "/src/main/Utils.java");
        files.add(gamesPath + "/temp/temp.txt");
        
        
        for (String dirName : dirs) {
            makedir(dirName); 
        }
     
        for (String fileName : files) {
            makedfile(fileName);
        }
        
        // Записываем лог в файл temp.txt
        String logFilePath = gamesPath + "/temp/temp.txt";
        try (FileWriter writer = new FileWriter(logFilePath, false)) {
            writer.write(log.toString());
            System.out.println("Лог успешно записан в temp.txt");
        } catch (IOException e) {
            System.err.println("Ошибка при записи лога в файл: " + e.getMessage());
        }
    }

    public static void makedir(String dirName){
        File dir = new File(dirName);
        if (dir.mkdir()) {
            log.append("Создана директория: ").append(dir.getAbsolutePath()).append("\n");
        } else if (dir.exists()) {
            log.append("Директория уже существует: ").append(dir.getAbsolutePath()).append("\n");
        } else {
            log.append("Ошибка создания директории: ").append(dir.getAbsolutePath()).append("\n");
        }
    }

    public static void makedfile(String fileName){
        File file = new File(fileName);
            try {
                if (file.createNewFile()) {
                    log.append("Создан файл: ").append(file.getAbsolutePath()).append("\n");
                } else if (file.exists()) {
                    log.append("Файл уже существует: ").append(file.getAbsolutePath()).append("\n");
                } else {
                    log.append("Ошибка создания файла: ").append(file.getAbsolutePath()).append("\n");
                }
            } catch (IOException e) {
                log.append("Исключение при создании файла ").append(file.getAbsolutePath())
                   .append(": ").append(e.getMessage()).append("\n");
            }
    }
}