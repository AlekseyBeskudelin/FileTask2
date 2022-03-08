package com.saving;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) throws IOException {
        GameProgress onePlayer = new GameProgress(5, 3, 2, 20.5);
        GameProgress twoPlayer = new GameProgress(6, 2, 3, 21.5);
        GameProgress threePlayer = new GameProgress(7, 5, 5, 25.5);
        saveGame("C:\\Users\\ПК\\Desktop\\Games\\savegames\\save1.dat", onePlayer);
        saveGame("C:\\Users\\ПК\\Desktop\\Games\\savegames\\save2.dat", twoPlayer);
        saveGame("C:\\Users\\ПК\\Desktop\\Games\\savegames\\save3.dat", threePlayer);
        List<String> filesForZiped = new ArrayList<>();
        filesForZiped.add("C:\\Users\\ПК\\Desktop\\Games\\savegames\\save1.dat");
        filesForZiped.add("C:\\Users\\ПК\\Desktop\\Games\\savegames\\save2.dat");
        filesForZiped.add("C:\\Users\\ПК\\Desktop\\Games\\savegames\\save3.dat");
        zipFiles("C:\\Users\\ПК\\Desktop\\Games\\savegames\\zipFile.zip", filesForZiped);
        System.out.println(filesForZiped);


    }

    public static void saveGame(String pathName, GameProgress player) {

        try (FileOutputStream fale = new FileOutputStream(pathName);
             ObjectOutputStream faleOos = new ObjectOutputStream(fale)) {
            faleOos.writeObject(player);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        //finally{}
    }

    public static void zipFiles(String pathZip, List<String> pathFileTozip) {
        try (ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(pathZip));) {
            for (int i = 0; i < pathFileTozip.size(); i++) {
                File fale = new File(pathFileTozip.get(i));
                fale.createNewFile();
                FileInputStream curentFile = new FileInputStream(fale);
                ZipEntry entry = new ZipEntry(fale.getName());
                zip.putNextEntry(entry);
                byte[] buffer = new byte[curentFile.available()];
                curentFile.read(buffer);
                curentFile.close();
                zip.write(buffer);
                zip.closeEntry();
                fale.delete();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
