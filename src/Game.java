import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Game {
    
    public static void main(String[] args) {
        String savegamesPath = "C:/Games/savegames/";
        
        GameProgress progress1 = new GameProgress(100, 5, 10, 250.5);
        GameProgress progress2 = new GameProgress(85, 3, 7, 180.2);
        GameProgress progress3 = new GameProgress(120, 8, 15, 420.8);
        
        String save1 = savegamesPath + "save1.dat";
        String save2 = savegamesPath + "save2.dat";
        String save3 = savegamesPath + "save3.dat";
        
        saveGame(save1, progress1);
        saveGame(save2, progress2);
        saveGame(save3, progress3);
        
        // Создаем ZIP-архив
        String zipPath = savegamesPath + "zip.zip";
        String[] filesToZip = {save1, save2, save3};
        zipFiles(zipPath, filesToZip);
        
        // Удаляем файлы сохранений, лежащие вне архива
        for (String filePath : filesToZip) {
            File file = new File(filePath);
            if (file.exists()) {
                if (file.delete()) {
                    System.out.println("Файл удален: " + filePath);
                } else {
                    System.err.println("Не удалось удалить файл: " + filePath);
                }
            } else {
                System.out.println("Файл уже удален или не существует: " + filePath);
            }
        }       
    }
    
    public static void saveGame(String filePath, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(filePath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            
            oos.writeObject(gameProgress);
            System.out.println("Сохранение создано: " + filePath);
            
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении игры: " + e.getMessage());
        }
    }
    
    public static void zipFiles(String zipPath, String[] filesToZip) {
        try (FileOutputStream fos = new FileOutputStream(zipPath);
             ZipOutputStream zos = new ZipOutputStream(fos)) {
            
            byte[] buffer = new byte[1024];

            for (String filePath : filesToZip) {
                File fileToZip = new File(filePath);
                
                if (!fileToZip.exists()) {
                    System.err.println("Файл не найден: " + filePath);
                    continue;
                }
                
                // Создаем ZipEntry для файла
                ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
                zos.putNextEntry(zipEntry);
                
                // Читаем файл и записываем в архив
                try (FileInputStream fis = new FileInputStream(fileToZip)) {
                    int length;
                    while ((length = fis.read(buffer)) > 0) {
                        zos.write(buffer, 0, length);
                    }
                    System.out.println("Файл добавлен в архив: " + fileToZip.getName());
                } catch (IOException e) {
                    System.err.println("Ошибка при чтении файла: " + filePath + " - " + e.getMessage());
                }
                
                zos.closeEntry();
            }
            
            System.out.println("ZIP-архив создан: " + zipPath);
            
        } catch (IOException e) {
            System.err.println("Ошибка при создании ZIP-архива: " + e.getMessage());
        }
    }
}