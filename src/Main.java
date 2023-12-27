import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.*;

public class Main {
    public static void main(String[] args) {

        GameProgress gameProgress1 = new GameProgress(1, 2, 3, 13.3);
        GameProgress gameProgress2 = new GameProgress(111, 3, 300, 113.3);
        GameProgress gameProgress3 = new GameProgress(10, 1, 53, 3313.3);


        List<String> files = new ArrayList<>();
        files.add("C:/Users/Виталий/netology/GameProgressConservation/src/savegames/save1.dat");
        files.add("C:/Users/Виталий/netology/GameProgressConservation/src/savegames/save2.dat");
        files.add("C:/Users/Виталий/netology/GameProgressConservation/src/savegames/save3.dat");


//        saveGame(files.get(0), gameProgress1);
//        saveGame(files.get(1), gameProgress2);
//        saveGame(files.get(2), gameProgress3);
//
//        zipFiles("C:/Users/Виталий/netology/GameProgressConservation/src/savegames/zip.zip", files);
//
//        deleted(files);
//Задача 3: Загрузка
        File dir1 = new File("C:/Users/Виталий/netology/GameProgressConservation/src/savegames/zip.zip");
        File dir2 = new File("C:/Users/Виталий/netology/GameProgressConservation/src/savegames");
        if (dir1.canExecute()) System.out.println("Доступ есть к zip.zip");
        if (dir2.canExecute()) System.out.println("Доступ есть к папке savegames");
        openZip(dir1, dir2);

    }

    public static void saveGame(String file, GameProgress gameProgress) {

        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void zipFiles(String archive, List<String> files) {
        try (FileOutputStream fout = new FileOutputStream(archive);
             ZipOutputStream zout = new ZipOutputStream(fout)) {
            String[] prt = {"save1.dat", "save2.dat", "save3.dat"};
            byte[] buffer = new byte[1024];

            for (int i = 0; i < files.size(); i++) {
                FileInputStream fin = new FileInputStream(files.get(i));
                zout.putNextEntry(new ZipEntry(prt[i]));
                int length;
                while ((length = fin.read(buffer)) > 0) {
                    zout.write(buffer, 0, length);
                }
                zout.closeEntry();
                fin.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleted(List<String> files) {
        for (int i = 0; i < files.size(); i++) {
            File newDir = new File(files.get(i));
            boolean deleted = newDir.delete();
        }
    }


    public static void openZip(File dir1, File dir2) {
        try (ZipInputStream zin = new ZipInputStream(
                new FileInputStream(dir1))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();
                System.out.printf("File name: %s \n", name);

                FileOutputStream fout = new FileOutputStream(dir2);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
