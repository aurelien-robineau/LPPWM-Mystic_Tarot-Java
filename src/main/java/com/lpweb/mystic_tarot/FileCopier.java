package com.lpweb.mystic_tarot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * The class FileCopier allows to copy files.
 */
public class FileCopier {
    /**
     * The file to copy.
     */
    private File file;

    /**
     * Constucts a file copier.
     * @param file the file to copy.
     */
    public FileCopier(File file) {
        this.file = file;
    }

    /**
     * Copies the file to a new destination.
     * @param filename the destination file.
     * @return the new file.
     */
    public File copyTo(String filename) throws Exception {
        try {
            // Stream to read to file
            FileInputStream  fis = new FileInputStream(file);
            // Stream to write the new file
            FileOutputStream fos = new FileOutputStream(filename);

            int length;
            byte[] buffer = new byte[1024];
            while ((length = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }

            fis.close();
            fos.close();

            return new File(filename);
        } catch (Exception e) {
            throw new Exception("Could not copy file " + file.getName());
        }
    }
}
