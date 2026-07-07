import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String gamesPath = "C:/Games";
        
        // Создаем StringBuilder для лога
        StringBuilder log = new StringBuilder();
        log.append("Путь к Games: ").append(gamesPath).append("\n\n");
        
        File gamesDir = new File(gamesPath);
        if (!gamesDir.exists()) {
            System.out.println("Ошибка: папка Games не существует!");
            return;
        }
        
        // Создаем поддиректории в Games: src, res, savegames, temp
        String[] subDirs = {"src", "res", "savegames", "temp"};
        for (String dirName : subDirs) {
            File dir = new File(gamesDir, dirName);
            if (dir.mkdir()) {
                log.append("Создана директория: ").append(dir.getAbsolutePath()).append("\n");
            } else if (dir.exists()) {
                log.append("Директория уже существует: ").append(dir.getAbsolutePath()).append("\n");
            } else {
                log.append("Ошибка создания директории: ").append(dir.getAbsolutePath()).append("\n");
            }
        }
        
        // Создаем поддиректории в src: main, test
        File srcDir = new File(gamesDir, "src");
        String[] srcSubDirs = {"main", "test"};
        for (String dirName : srcSubDirs) {
            File dir = new File(srcDir, dirName);
            if (dir.mkdir()) {
                log.append("Создана директория: ").append(dir.getAbsolutePath()).append("\n");
            } else if (dir.exists()) {
                log.append("Директория уже существует: ").append(dir.getAbsolutePath()).append("\n");
            } else {
                log.append("Ошибка создания директории: ").append(dir.getAbsolutePath()).append("\n");
            }
        }
        
        // Создаем файлы в src/main: Main.java, Utils.java
        File mainDir = new File(srcDir, "main");
        String[] mainFiles = {"Main.java", "Utils.java"};
        for (String fileName : mainFiles) {
            File file = new File(mainDir, fileName);
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
        
        // Создаем поддиректории в res: drawables, vectors, icons
        File resDir = new File(gamesDir, "res");
        String[] resSubDirs = {"drawables", "vectors", "icons"};
        for (String dirName : resSubDirs) {
            File dir = new File(resDir, dirName);
            if (dir.mkdir()) {
                log.append("Создана директория: ").append(dir.getAbsolutePath()).append("\n");
            } else if (dir.exists()) {
                log.append("Директория уже существует: ").append(dir.getAbsolutePath()).append("\n");
            } else {
                log.append("Ошибка создания директории: ").append(dir.getAbsolutePath()).append("\n");
            }
        }
        
        // Создаем файл temp.txt в директории temp
        File tempDir = new File(gamesDir, "temp");
        File tempFile = new File(tempDir, "temp.txt");
        try {
            if (tempFile.createNewFile()) {
                log.append("Создан файл: ").append(tempFile.getAbsolutePath()).append("\n");
            } else if (tempFile.exists()) {
                log.append("Файл уже существует: ").append(tempFile.getAbsolutePath()).append("\n");
            } else {
                log.append("Ошибка создания файла: ").append(tempFile.getAbsolutePath()).append("\n");
            }
        } catch (IOException e) {
            log.append("Исключение при создании файла ").append(tempFile.getAbsolutePath())
               .append(": ").append(e.getMessage()).append("\n");
        }
        
        // Записываем лог в файл temp.txt
        try (FileWriter writer = new FileWriter("temp.txt", false)) {
            writer.write(log.toString());
            System.out.println("Лог успешно записан в temp.txt");
        } catch (IOException e) {
            System.err.println("Ошибка при записи лога в файл: " + e.getMessage());
        }
    }
}