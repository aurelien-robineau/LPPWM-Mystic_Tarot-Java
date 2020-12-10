package com.lpweb.mystic_tarot.gui.frames;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.lpweb.mystic_tarot.MysticTarot;
import com.lpweb.mystic_tarot.card.CardSerializer;
import com.lpweb.mystic_tarot.gui.GuiManager;
import com.lpweb.mystic_tarot.gui.components.Button;
import com.lpweb.mystic_tarot.gui.components.TextInput;
import com.lpweb.mystic_tarot.gui.listeners.OpenCreateForm;
import com.lpweb.mystic_tarot.gui.listeners.ShowCards;

/**
 * MainFrame is the window openned when lauching the application.
 * It allows users to create a new card, search for cards and edit or delete
 * them.
 */
public class MainFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    /**
     * Name of the window.
     */
    private static final String NAME = "Mystic tarot";

    /**
     * Default window's dimensions.
     */
    private static final Dimension DEFAULT_DIMENSION = new Dimension(960, 520);

    public MainFrame() {
        super();

        setTitle(NAME);
        setSize(DEFAULT_DIMENSION);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel      menuPanel   = new JPanel();
        JPanel      cardsPanel  = new JPanel();
        JScrollPane cardsScroll = new JScrollPane(cardsPanel);

        cardsPanel.setLayout(new BoxLayout(cardsPanel, BoxLayout.Y_AXIS));
        cardsScroll.setViewportView(cardsPanel);

        Button    createCardButton = new Button("Create card");
        TextInput searchCardInput  = new TextInput();

        menuPanel.add(createCardButton);
        menuPanel.add(searchCardInput);

        add(panel);

        panel.add(menuPanel);
        panel.add(cardsScroll);

        GuiManager.getInstance().setCardContainer(cardsPanel);

        KeyListener searchCardListener = new ShowCards(searchCardInput);
        searchCardInput.addKeyListener(searchCardListener);
        createCardButton.addActionListener(new OpenCreateForm());

        // Show all cards on init
        searchCardListener.keyTyped(null);
    }

    @Override
    public void processWindowEvent(WindowEvent e) {
        // Clear application before closing it.
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            MysticTarot.getScanner().close();
            CardSerializer.cleanFiles();
        }

        super.processWindowEvent(e);
    }
}
