package view;
import loaders.ResourceLoader;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    private ImageIcon icono = new ImageIcon(ResourceLoader.loadGraphics("IconoLudo.jpg"));
    private PanelCreate panelCreate;
    private PanelMenu panelMenu;

    public Frame(int width, int height) {
        panelCreate = new PanelCreate(this);
        panelMenu = new PanelMenu(this);

        this.setTitle("Ludo | Proyecto");
        this.pack();
        this.setSize(width + this.getInsets().left + this.getInsets().right, height + this.getInsets().top + this.getInsets().bottom);
        this.setLocationRelativeTo(null);
        this.setMinimumSize(new Dimension(width, height));
        this.setIconImage(icono.getImage());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.add(panelCreate);
    }

    public void cambiarPanel(JPanel panel) {
        this.getContentPane().removeAll();
        this.getContentPane().add(panel);
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }


}
