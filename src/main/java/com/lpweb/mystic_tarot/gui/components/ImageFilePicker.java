package com.lpweb.mystic_tarot.gui.components;

import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * The ImageFilePicker class is a FilePicker that only allows to select image
 * files.
 * Are considered image files: jpg, jpeg, png.
 * @see FilePicker
 */
public class ImageFilePicker extends FilePicker {
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new ImageFilePicker.
     * @param currentDirectory the directory to open in the file chooser.
     * @see FilePicker#FilePicker(String) FilePicker
     */
    public ImageFilePicker(String currentDirectory) {
        super(currentDirectory);

        FileFilter filter = new FileNameExtensionFilter(
            // File types description
            "jpg, jpeg, png",
            // File types
            "jpg",
            "jpeg",
            "png"
        );

        fileChooser.setFileFilter(filter);
    }
}