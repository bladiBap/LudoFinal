package objects;

import loaders.ResourceLoader;

import javax.swing.*;
import java.awt.*;

public class Piece extends JButton {
    private static final int size = 60;
    private int posX;
    private int posY;
    private String path;
    private String color;
    private Sound audioPaso = new Sound(ResourceLoader.loadSounds("SonidoPaso.wav"));
    private Player jugador;

    private Point point = new Point();
    private Point pointOrigen = new Point();

    private int posGlobalRojo = -1;
    private int posGlobalAzul = -1;
    private int posGlobalVerde = -1;
    private int posGlobalAmarillo = -1;
    private int posGlobalAnterior = -1;

    private boolean salio = false;

    private int posArreglo = 0;

    private view.Panel panel;

    private boolean moverse;
    private int casillasAMoverse;

    private int posicion;

    private Point[] posicionesRojo = {new Point(60, 360), new Point(120, 360), new Point(180, 360), new Point(240, 360), new Point(300, 360),
            new Point(360, 300), new Point(360, 240), new Point(360, 180), new Point(360, 120), new Point(360, 60), new Point(360, 0),
            new Point(420, 0),
            new Point(480, 0), new Point(480, 60), new Point(480, 120), new Point(480, 180), new Point(480, 240), new Point(480, 300),
            new Point(540, 360), new Point(600, 360), new Point(660, 360), new Point(720, 360), new Point(780, 360), new Point(840, 360),
            new Point(840, 420),
            new Point(840, 480), new Point(780, 480), new Point(720, 480), new Point(660, 480), new Point(600, 480), new Point(540, 480),
            new Point(480, 540), new Point(480, 600), new Point(480, 660), new Point(480, 720), new Point(480, 780), new Point(480, 840),
            new Point(420, 840),
            new Point(360, 840), new Point(360, 780), new Point(360, 720), new Point(360, 660), new Point(360, 600), new Point(360, 540),
            new Point(300, 480), new Point(240, 480), new Point(180, 480), new Point(120, 480), new Point(60, 480), new Point(0, 480),
            new Point(0, 420), new Point(60, 420), new Point(120, 420), new Point(180, 420), new Point(240, 420), new Point(300, 420), new Point(360, 420),
            new Point(420, 420)};

    private Point[] posicionesVerde = {new Point(480, 60), new Point(480, 120), new Point(480, 180), new Point(480, 240), new Point(480, 300),
            new Point(540, 360), new Point(600, 360), new Point(660, 360), new Point(720, 360), new Point(780, 360), new Point(840, 360),
            new Point(840, 420),
            new Point(840, 480), new Point(780, 480), new Point(720, 480), new Point(660, 480), new Point(600, 480), new Point(540, 480),
            new Point(480, 540), new Point(480, 600), new Point(480, 660), new Point(480, 720), new Point(480, 780), new Point(480, 840),
            new Point(420, 840),
            new Point(360, 840), new Point(360, 780), new Point(360, 720), new Point(360, 660), new Point(360, 600), new Point(360, 540),
            new Point(300, 480), new Point(240, 480), new Point(180, 480), new Point(120, 480), new Point(60, 480), new Point(0, 480),
            new Point(0, 420),
            new Point(0, 360), new Point(60, 360), new Point(120, 360), new Point(180, 360), new Point(240, 360), new Point(300, 360),
            new Point(360, 300), new Point(360, 240), new Point(360, 180), new Point(360, 120), new Point(360, 60), new Point(360, 0),
            new Point(420, 0), new Point(420, 60), new Point(420, 120), new Point(420, 180), new Point(420, 240), new Point(420, 300),
            new Point(420, 360)};

    private Point[] posicionesAzul = {new Point(360, 780), new Point(360, 720), new Point(360, 660), new Point(360, 600), new Point(360, 540),
            new Point(300, 480), new Point(240, 480), new Point(180, 480), new Point(120, 480), new Point(60, 480), new Point(0, 480),
            new Point(0, 420),
            new Point(0, 360), new Point(60, 360), new Point(120, 360), new Point(180, 360), new Point(240, 360), new Point(300, 360),
            new Point(360, 300), new Point(360, 240), new Point(360, 180), new Point(360, 120), new Point(360, 60), new Point(360, 0),
            new Point(420, 0),
            new Point(480, 0), new Point(480, 60), new Point(480, 120), new Point(480, 180), new Point(480, 240), new Point(480, 300),
            new Point(540, 360), new Point(600, 360), new Point(660, 360), new Point(720, 360), new Point(780, 360), new Point(840, 360),
            new Point(840, 420),
            new Point(840, 480), new Point(780, 480), new Point(720, 480), new Point(660, 480), new Point(600, 480), new Point(540, 480),
            new Point(480, 540), new Point(480, 600), new Point(480, 660), new Point(480, 720), new Point(480, 780), new Point(480, 840),
            new Point(420, 840), new Point(420, 780), new Point(420, 720), new Point(420, 660), new Point(420, 600), new Point(420, 540), new Point(420, 480)};

    private Point[] posicionesAmarillo = {new Point(780, 480), new Point(720, 480), new Point(660, 480), new Point(600, 480), new Point(540, 480),
            new Point(480, 540), new Point(480, 600), new Point(480, 660), new Point(480, 720), new Point(480, 780), new Point(480, 840),
            new Point(420, 840),
            new Point(360, 840), new Point(360, 780), new Point(360, 720), new Point(360, 660), new Point(360, 600), new Point(360, 540),
            new Point(300, 480), new Point(240, 480), new Point(180, 480), new Point(120, 480), new Point(60, 480), new Point(0, 480),
            new Point(0, 420),
            new Point(0, 360), new Point(60, 360), new Point(120, 360), new Point(180, 360), new Point(240, 360), new Point(300, 360),
            new Point(360, 300), new Point(360, 240), new Point(360, 180), new Point(360, 120), new Point(360, 60), new Point(360, 0),
            new Point(420, 0),
            new Point(480, 0), new Point(480, 60), new Point(480, 120), new Point(480, 180), new Point(480, 240), new Point(480, 300),
            new Point(540, 360), new Point(600, 360), new Point(660, 360), new Point(720, 360), new Point(780, 360), new Point(840, 360),
            new Point(840, 420), new Point(780, 420), new Point(720, 420), new Point(660, 420), new Point(600, 420), new Point(540, 420), new Point(480, 420)};


    public Piece(int posX, int posY, String path, String color, view.Panel panel, Player jugador) {
        this.posX = posX;
        this.posY = posY;
        this.path = path;
        this.color = color;
        this.posicion = -1;
        this.moverse = false;
        this.casillasAMoverse = 0;
        this.jugador = jugador;
        this.panel = panel;
        this.point.x = 0;
        this.point.y = 0;
        this.addListener();
        this.pointOrigen.x = posX;
        this.pointOrigen.y = posY;
        drawPiece(this.panel);
        initPosGloba();
    }

    public void drawPiece(view.Panel panel) {
        this.setBounds(posX, posY, size, size);
        this.setContentAreaFilled(false);
        this.setBorder(null);
        this.setFocusPainted(false);
        panel.add(this);
        ImageIcon imageIcon = new ImageIcon(ResourceLoader.loadGraphics(path));
        Icon aux = new ImageIcon(imageIcon.getImage().getScaledInstance(size - 20, size - 20, Image.SCALE_DEFAULT));
        this.setIcon(aux);
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setVerticalAlignment(SwingConstants.CENTER);
    }

    private void addListener() {
        this.addActionListener(e -> {
            if (moverse) {
                this.setSize(size, size);

                if (posicion + casillasAMoverse > 56){

                    System.out.println("No puedes moverte mas");

                }else {
                    mover(casillasAMoverse);
                    if (salio) {
                        this.setPosGlobalAnterior(this.getPosGlobal());
                    } else {
                        this.setPosGlobalAnterior(-1);
                    }
                    actualizarPosGlobal(casillasAMoverse);
                    this.panel.verificarResizeYColision(this);
                    this.salio = true;
                }

            }
        });
    }

    public void mover(int casillasAMoverse) {
        int posInicial = getPosicion();
        int pos = getPosicion() + casillasAMoverse;
        System.out.println(casillasAMoverse);
        System.out.println("posicion en el propio ARRAY        : " + pos);
        if (pos <= 56) {
            setPosicion(pos);
            Point nuevoPunto = getSiguientePosicion(pos);
            this.setLocation(nuevoPunto.x, nuevoPunto.y);
            acomodarImagenNormal();
            audioPaso.play();
            repaint();

            Piece[] piezas = jugador.getPiecesList();
            for (int i = 0; i < 4; i++) {
                piezas[i].setMoverse(false);
            }
            if ((casillasAMoverse==6) || (casillasAMoverse==1 && posInicial == -1)){
                panel.activarBoton();
                System.out.println("Deberia seguir jugando");
            }else{
                jugador.siguienteTurno();
            }

            if (posicion == 56) {
                this.moverse = false;
            }
        }
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public Point getSiguientePosicion(int posicion) {
        if (color.equalsIgnoreCase("red")) {
            return posicionesRojo[posicion];
        } else if (color.equalsIgnoreCase("blue")) {
            return posicionesAzul[posicion];
        } else if (color.equalsIgnoreCase("green")) {
            return posicionesVerde[posicion];
        } else {
            return posicionesAmarillo[posicion];
        }
    }

    public Point getPosicionActual(int posicion) {
        if (color.equalsIgnoreCase("red")) {
            return posicionesRojo[posicion];
        } else if (color.equalsIgnoreCase("blue")) {
            return posicionesAzul[posicion];
        } else if (color.equalsIgnoreCase("green")) {
            return posicionesVerde[posicion];
        } else {
            return posicionesAmarillo[posicion];
        }
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public boolean isMoverse() {
        return moverse;
    }

    public void setMoverse(boolean moverse) {
        this.moverse = moverse;
    }

    public int getCasillasAMoverse() {
        return casillasAMoverse;
    }

    public void setCasillasAMoverse(int casillasAMoverse) {
        this.casillasAMoverse = casillasAMoverse;
    }

    public void initPosGloba() {
        if (color.equalsIgnoreCase("red")) {
            this.posGlobalRojo = 12;
        } else if (color.equalsIgnoreCase("blue")) {
            this.posGlobalAzul = -1;
        } else if (color.equalsIgnoreCase("green")) {
            this.posGlobalVerde = 25;
        } else {
            this.posGlobalAmarillo = 38;
        }
    }

    public void actualizarPosGlobal(int casillasAMoverse) {
        if (color.equalsIgnoreCase("red") && (posGlobalRojo + casillasAMoverse) > 51 && posGlobalRojo <= 51) {
            posGlobalRojo = (posGlobalRojo + casillasAMoverse) - 52;
        } else {
            if ((posGlobalRojo + casillasAMoverse) > 11 && posGlobalRojo <= 11) {
                posGlobalRojo = ((posGlobalRojo + casillasAMoverse) - 11) + 57;
            } else {
                posGlobalRojo += casillasAMoverse;
            }
        }

        if (color.equalsIgnoreCase("blue") && (posGlobalAzul + casillasAMoverse) > 50 && posGlobalAzul <= 50) {
            posGlobalAzul = (posGlobalAzul + casillasAMoverse) + 1;

        } else {
            posGlobalAzul += casillasAMoverse;
        }

        if (color.equalsIgnoreCase("green") && (posGlobalVerde + casillasAMoverse) > 51 && posGlobalVerde <= 51) {
            posGlobalVerde = (posGlobalVerde + casillasAMoverse) - 52;
        } else {
            if ((posGlobalVerde+ casillasAMoverse) > 24 && posGlobalVerde <=24) {
                posGlobalVerde = ((posGlobalVerde + casillasAMoverse) - 24) + 63;
            } else {
                posGlobalVerde += casillasAMoverse;
            }
        }

        if (color.equalsIgnoreCase("yellow") && (posGlobalAmarillo + casillasAMoverse) > 51 && posGlobalAmarillo <= 51) {
            posGlobalAmarillo = (posGlobalAmarillo + casillasAMoverse) - 52;

        } else {
            if ((posGlobalAmarillo + casillasAMoverse) > 37 && posGlobalAmarillo <= 37) {
                posGlobalAmarillo = ((posGlobalAmarillo + casillasAMoverse) - 37) + 69;
            } else {
                posGlobalAmarillo += casillasAMoverse;
            }
        }
    }

    public int getPosGlobal() {
        if (color.equalsIgnoreCase("red")) {
            return posGlobalRojo;
        } else if (color.equalsIgnoreCase("blue")) {
            return posGlobalAzul;
        } else if (color.equalsIgnoreCase("green")) {
            return posGlobalVerde;
        } else {
            return posGlobalAmarillo;
        }
    }

    public void acomodarImagen() {
        ImageIcon imageIcon = new ImageIcon(ResourceLoader.loadGraphics(path));
        Icon aux = new ImageIcon(imageIcon.getImage().getScaledInstance(size - 30, size - 30, Image.SCALE_DEFAULT));
        this.setIcon(aux);
    }

    public void acomodarImagenNormal() {
        this.setSize(60, 60);
        ImageIcon imageIcon = new ImageIcon(ResourceLoader.loadGraphics(path));
        Icon aux = new ImageIcon(imageIcon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        this.setIcon(aux);
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public int getPosGlobalAnterior() {
        return posGlobalAnterior;
    }

    public void setPosGlobalAnterior(int posGlobalAnterior) {
        this.posGlobalAnterior = posGlobalAnterior;
    }

    public int getPosArreglo() {
        return posArreglo;
    }

    public void setPosArreglo(int posArreglo) {
        this.posArreglo = posArreglo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Point getPointOrigen() {
        return pointOrigen;
    }

    public void setPointOrigen(Point pointOrigen) {
        this.pointOrigen = pointOrigen;
    }

    public boolean isSalio() {
        return salio;
    }

    public void setSalio(boolean salio) {
        this.salio = salio;
    }


}