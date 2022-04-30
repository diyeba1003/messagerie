/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.fileReader;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author etud
 */
public class FileScan {
    /**
     * 
     * @param dir correspond au répertoire principal
     * @return la liste des fichiers contenue dans le répertoire
     */
   public static List<File> listAllFileFromDir(String dir) {
        File folder = new File(dir);
        return fileSearch(folder);
    }

    protected static List<File> fileSearch(File folder) {
        String completePath = null;
        List<File> fileList = new ArrayList<>();
        if (folder.isDirectory()) {
            List<File> files = Arrays.asList(folder.listFiles());
            Iterator<File> fileIt = files.iterator();
            while (fileIt.hasNext() && completePath == null) {
                fileList.addAll(fileSearch(fileIt.next()));
            }
        } else {
            fileList.add(folder);
        }
        return fileList;
    }

}
