package com.lpweb.mystic_tarot.gui.components;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The class FilePicker allows to select a file.
 * It is made of a button that opens a JFileChooser and a label to display the
 * name of the choosen file.
 * Any file is accepted by the FileChooser class.
 */
public class FilePicker extends JPanel implements Validable {
    private static final long serialVersionUID = 1L;

    /**
     * The file selected by the user.
     */
    protected File selectedFile;

    /**
     * The file chooser used by the FilePicker to pick file.
     */
    protected JFileChooser fileChooser;

    /**
     * The label where to display the selected file name.
     */
    protected JLabel selectedFileNameLabel;

    /**
     * Constructs a new FilePicker.
     * @param currentDirectory the directory to open in the file chooser.
     * @see JFileChooser#JFileChooser(String) JFileChooser
     */
    public FilePicker(String currentDirectory) {
        super();

        fileChooser = new JFileChooser(currentDirectory);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        selectedFileNameLabel = new JLabel("No file selected.");
        add(selectedFileNameLabel);

        Button selectFileButton = new Button("Select file");
        add(selectFileButton);

        selectFileButton.addActionListener(new ChooseFile(this));
    }

    public JFileChooser getFileChooser() {
        return fileChooser;
    }

    public JLabel getSelectedFileNameLabel() {
        return selectedFileNameLabel;
    }

    public File getSelectedFile() {
        return selectedFile;
    }

    @Override
    public void setError() {
        selectedFileNameLabel.setForeground(Color.RED);
    }

    @Override
    public void removeError() {
        selectedFileNameLabel.setForeground(null);
    }
}

/**
 * Listener for opening a FilePicker file chooser.
 */
class ChooseFile implements ActionListener {
    /**
     * File picker that triggered the event.
     */
    private FilePicker filePicker;

    /**
     * Constructs a ChooseFile listener.
     * @param filePicker the file picker that triggered the event.
     */
    ChooseFile(FilePicker filePicker) {
        this.filePicker = filePicker;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JFileChooser fileChooser            = filePicker.getFileChooser();
        JLabel       selectedImagePathLabel = filePicker.getSelectedFileNameLabel();

        File file;
        int response;

        // Open file chooser dialog frame
        response = fileChooser.showOpenDialog(null);

        // Wait for the user to choose a file
        if (response == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
            if (file.isFile()) {
                filePicker.selectedFile = file;
                selectedImagePathLabel.setText(file.getName());
            }
        }
    }
}
