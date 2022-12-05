package view;

import loaders.ResourceLoader;
import objects.Piece;
import objects.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Panel extends JPanel implements Runnable {

    private Player testPlayer1;
    private Player testPlayer2;
    private Player testPlayer3;
    private Player testPlayer4;

    private JButton dadoBoton = new JButton("Girar Dado");
    private JLabel dadoLabel = new JLabel();

    private int posicionesAMoverse = 0;

    private JLabel player1Jlb;
    private JLabel player2Jlb;
    private JLabel player3Jlb;
    private JLabel player4Jlb;

    private ArrayList<Player> jugadores;
    private int turno;
    private Player jugadorTurno;
    private JLabel playerTurno = new JLabel("", SwingConstants.CENTER);

    private int[] posicionesInmortales = {0, 8, 13, 21, 26, 34, 39, 47};

    public static final int WIDTH = 898;
    public static final int HEIGHT = 898;

    private ArrayList<Piece> piezas = new ArrayList<>();

    private ArrayList<Piece>[] almacenDeColisiones = new ArrayList[76];

    public Panel() {
        this.setLayout(null);
        jugadores = new ArrayList<>();
        cargarDado();
        addListeners();
        labels();
        init();
        addPiezasDeJugadores();
    }

    public Panel(String nombrePlayer, String colorPlayer) {
        this.setLayout(null);
        rellenarJugadores(nombrePlayer, colorPlayer);
        cargarDado();
        addListeners();
        labels();
        init();
        addPiezasDeJugadores();
    }

    private void rellenarJugadores(String nombre, String color) {
        jugadores = new ArrayList<>();

        if (color.equalsIgnoreCase("red")){
            testPlayer1 = new Player(nombre, color, this);
            jugadores.add(testPlayer1);
        } else if (color.equalsIgnoreCase("blue")) {
            testPlayer2 = new Player(nombre, color, this);
            jugadores.add(testPlayer2);
        } else if (color.equalsIgnoreCase("yellow")) {
            testPlayer3 = new Player(nombre, color, this);
            jugadores.add(testPlayer3);
        }else {
            testPlayer4 = new Player(nombre, color, this);
            jugadores.add(testPlayer4);
        }

        String[] nombres = {"Hugo", "Martin", "Lucas", "Mateo", "Leo", "Daniel", "Alejandro", "Pablo", "Manuel", "Adrián", "David", "Mario", "Enzo", "Diego", "Marcos", "Izan", "Javier", "Marco", "Alex", "Bruno", "Oliver", "Miguel", "Thiago", "Antonio", "Marc", "Carlos", "Angel", "Juan", "Gonzalo", "Gael", "Sergio", "Nicolas", "Dylan", "Gabriel", "Jorge", "Jose", "Adam", "Liam", "Eric", "Samuel", "Dario", "Hector", "Luca", "Iker", "Amir", "Rodrigo", "Saul", "Victor", "Francisco", "Iván", "Jesus", "Jaime", "Aaron", "Ruben", "Ian", "Guillermo", "Erik", "Mohamed", "Julen", "Luis", "Pau", "Unai", "Rafael", "Joel", "Alberto", "Pedro", "Raul", "Aitor", "Santiago", "Rayan", "Pol", "Nil", "Noah", "Jan", "Asier", "Fernando", "Alonso", "Matias", "Biel", "Andres", "Axel", "Ismael", "Marti", "Arnau", "Imran", "Luka", "Ignacio", "Aleix", "Alan", "Elias", "Omar", "Isaac", "Youssef", "Jon", "Teo", "Mauro", "Oscar", "Cristian", "Leonardo"};

        for (int i = 0; i < 3; i++) {
            int random = (int) (Math.random() * nombres.length);

            String nombreRandom = nombres[random];
            String colorRandom = getColorDisponible();

            if (colorRandom.equalsIgnoreCase("red")){
                testPlayer1 = new Player(nombreRandom, colorRandom, this);
                jugadores.add(testPlayer1);
            } else if (colorRandom.equalsIgnoreCase("blue")) {
                testPlayer2 = new Player(nombreRandom, colorRandom, this);
                jugadores.add(testPlayer2);
            } else if (colorRandom.equalsIgnoreCase("yellow")) {
                testPlayer3 = new Player(nombreRandom, colorRandom, this);
                jugadores.add(testPlayer3);
            }else {
                testPlayer4 = new Player(nombreRandom, colorRandom, this);
                jugadores.add(testPlayer4);
            }
        }

    }

    public void cargarDado() {
        dadoBoton.setBounds(1200, 600, 100, 40);
        dadoLabel.setBounds(998, 575, 100, 100);
        dadoLabel.setOpaque(true);
        ImageIcon imageIcon3 = new ImageIcon(ResourceLoader.loadGraphics("DiceAllStart.png"));
        Icon aux2 = new ImageIcon(imageIcon3.getImage().getScaledInstance(dadoLabel.getWidth(), dadoLabel.getHeight(), Image.SCALE_DEFAULT));
        dadoLabel.setIcon(aux2);
        this.add(dadoBoton);
        this.add(dadoLabel);
    }

    public void addListeners() {
        dadoBoton.addActionListener(e -> {
            ImageIcon imageIcon = new ImageIcon(ResourceLoader.loadGif("Dice.gif"));
            Icon aux = new ImageIcon(imageIcon.getImage().getScaledInstance(dadoLabel.getWidth(), dadoLabel.getHeight(), Image.SCALE_DEFAULT));
            dadoLabel.setIcon(aux);
            Thread hilo = new Thread(this);
            hilo.start();
        });
    }


    private void labels() {
        JLabel titulo = new JLabel("Ludo Game", SwingConstants.CENTER);
        titulo.setBounds(898, 25, 502, 50);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 42));

        playerTurno.setBounds(973, 750, 352, 50);
        playerTurno.setFont(new Font("SansSerif", Font.BOLD, 38));

        cargarNombres();

        this.add(titulo);
        this.add(playerTurno);
    }

    private void init() {
        turno = (int) (Math.random() * jugadores.size());
        jugadorTurno = jugadores.get(turno);
        playerTurno.setText("Turno de " + jugadorTurno.getNickName());
    }

    public void addPiezasDeJugadores() {
        for (Player player : jugadores) {
            Piece[] piezas = player.getPiecesList();
            for (int i = 0; i < piezas.length; i++) {
                this.piezas.add(piezas[i]);
            }
        }
        for (int i = 0; i < this.almacenDeColisiones.length; i++) {
            this.almacenDeColisiones[i] = new ArrayList<>();
        }
    }

    public String getColorDisponible(){
        ArrayList<String> coloresOcupados = new ArrayList<>();
        String[] colores = {"red","blue","green","yellow"};

        for (int i = 0; i < jugadores.size(); i++) {
            Player aux = jugadores.get(i);
            coloresOcupados.add(aux.getColor());
        }

        for (int i = 0; i < coloresOcupados.size(); i++) {
            for (int j = 0; j < colores.length; j++) {
                if (coloresOcupados.get(i).equalsIgnoreCase(colores[j])){
                    colores[j] = "";
                }
            }
        }

        for (int i = 0; i < colores.length; i++) {
            if (!colores[i].equalsIgnoreCase("")){
                return colores[i];
            }
        }

        return null;
    }

    public void cargarNombres() {

        if (testPlayer1 != null) {
            player1Jlb = new JLabel();
            player1Jlb.setText("1. " + testPlayer1.getNickName() + " - " + testPlayer1.getColor());
            player1Jlb.setBounds(948, 125, 352, 50);
            player1Jlb.setFont(new Font("SansSerif", Font.BOLD, 30));
            this.add(player1Jlb);
        }

        if (testPlayer2 != null) {
            player2Jlb = new JLabel();
            player2Jlb.setText("2. " + testPlayer2.getNickName() + " - " + testPlayer2.getColor());
            player2Jlb.setBounds(948, 225, 352, 50);
            player2Jlb.setFont(new Font("SansSerif", Font.BOLD, 30));
            this.add(player2Jlb);
        }

        if (testPlayer3 != null) {
            player3Jlb = new JLabel();
            player3Jlb.setText("3. " + testPlayer3.getNickName() + " - " + testPlayer3.getColor());
            player3Jlb.setBounds(948, 325, 352, 50);
            player3Jlb.setFont(new Font("SansSerif", Font.BOLD, 30));
            this.add(player3Jlb);
        }

        if (testPlayer4 != null) {
            player4Jlb = new JLabel();
            player4Jlb.setText("4. " + testPlayer4.getNickName() + " - " + testPlayer4.getColor());
            player4Jlb.setBounds(948, 425, 352, 50);
            player4Jlb.setFont(new Font("SansSerif", Font.BOLD, 30));
            this.add(player4Jlb);
        }
    }


    public static void main(String[] args) {
        Frame f = new Frame(WIDTH, HEIGHT);
    }


    @Override
    public void paint(Graphics g) {
        BufferedImage fondo = ResourceLoader.loadGraphics("FondoLudo.png");
        g.drawImage(fondo, 0, 0, 898, 898, null);
        setOpaque(false);
        super.paint(g);
    }


    @Override
    public void run() {
        dadoBoton.setEnabled(false);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }

        int numero = (int) (Math.random() * 6) + 1;
        String dadoPath = getPathDado(numero);

        ImageIcon imageIcon2 = new ImageIcon(ResourceLoader.loadGraphics(dadoPath));
        Icon aux3 = new ImageIcon(imageIcon2.getImage().getScaledInstance(dadoLabel.getWidth(), dadoLabel.getHeight(), Image.SCALE_DEFAULT));
        dadoLabel.setIcon(aux3);

        this.posicionesAMoverse = numero;

        Piece[] piezas = jugadorTurno.getPiecesList();
        int[] pos = new int[4];

        for (int i = 0; i < piezas.length; i++) {
            pos[i] = piezas[i].getPosicion();
        }

        if ((pos[0] == -1 && pos[1] == -1 && pos[2] == -1 && pos[3] == -1 && posicionesAMoverse != 6)) {
            jugadorTurno.siguienteTurno();
        } else {
            if (sePuedePasarTurno(pos, this.posicionesAMoverse) == false){
                jugadorTurno.siguienteTurno();
            }else {
                for (int i = 0; i < piezas.length; i++) {
                    if (pos[i] == -1 && posicionesAMoverse == 6) {
                        piezas[i].setMoverse(true);
                        piezas[i].setCasillasAMoverse(1);
                    } else {
                        if (pos[i] != -1) {
                            piezas[i].setMoverse(true);
                            piezas[i].setCasillasAMoverse(posicionesAMoverse);
                        }

                    }
                }
            }

        }
    }


    public void verificarResizeYColision(Piece p) {
        //Codigo resize
        System.out.println("Se ANADIO una piezan en la posicion: " + p.getPosGlobal());

        if (esPosicionInmortal(p.getPosGlobal())) {
            System.out.println("Entro a Posicion inmortal");
            anadirPieza(p);
        } else {
            if (almacenDeColisiones[p.getPosGlobal()].size() >= 1) {
                System.out.println("No es posion inmortal y si hay alguien");
                if (almacenDeColisiones[p.getPosGlobal()].get(0).getColor().equalsIgnoreCase(p.getColor())) {
                    System.out.println("No es posion inmortal y si hay alguien en esta Posicion y si es del mismo color");
                    anadirPieza(p);
                } else {
                    System.out.println("No es posion inmortal y si hay alguien en esta Posicion y si NO es del mismo color");
                    for (Piece piece : almacenDeColisiones[p.getPosGlobal()]) {
                        piece.setLocation(piece.getPointOrigen().x, piece.getPointOrigen().y);
                        piece.initPosGloba();
                        piece.setPosArreglo(0);
                        piece.setMoverse(false);
                        piece.setSalio(false);
                        // piece.setResize(true);
                        piece.setPoint(new Point(0, 0));
                        piece.setPosicion(-1);
                        piece.acomodarImagenNormal();
                    }
                    almacenDeColisiones[p.getPosGlobal()].clear();
                    this.repaint();
                    anadirPieza(p);

                }
            } else {
                System.out.println("No es posion inmortal y no hay nadie");
                anadirPieza(p);
            }
        }

        if (almacenDeColisiones[63].size() > 3) {
            JOptionPane.showMessageDialog(null,"Gano el jugador: " + testPlayer1.getNickName() + " con color: " + testPlayer1.getColor());
            dadoBoton.setEnabled(false);

        }
        if (almacenDeColisiones[69].size() > 3) {
            JOptionPane.showMessageDialog(null,"Gano el jugador: " + testPlayer4.getNickName() + " con color: " + testPlayer4.getColor());
            dadoBoton.setEnabled(false);
        }
        if (almacenDeColisiones[75].size() > 3) {
            JOptionPane.showMessageDialog(null,"Gano el jugador: " + testPlayer3.getNickName() + " con color: " + testPlayer3.getColor());
            dadoBoton.setEnabled(false);
        }
        if (almacenDeColisiones[57].size() > 3) {
            JOptionPane.showMessageDialog(null,"Gano el jugador: " + testPlayer2.getNickName() + " con color: " + testPlayer2.getColor());
            dadoBoton.setEnabled(false);
        }
    }

    public void anadirPieza(Piece p) {
        almacenDeColisiones[p.getPosGlobal()].add(p);
        p.setPoint(p.getPosicionActual(p.getPosicion()));
        actualizarPosicion(p.getPosGlobal(), p.getPosGlobalAnterior(), p);
        p.setPosArreglo(almacenDeColisiones[p.getPosGlobal()].indexOf(p));


        for (int j = 0; j < this.almacenDeColisiones.length; j++) {
            if (this.almacenDeColisiones[j].size() > 1) {
                reAcomodarPiezas(this.almacenDeColisiones[j]);
            }
        }
        String posiciones = "";
        for (int j = 0; j < this.almacenDeColisiones.length; j++) {
            if (this.almacenDeColisiones[j].size() > 0) {
                posiciones += this.almacenDeColisiones[j].size() + " (" + j + "),  ";
            }
        }
        System.out.println(posiciones);
        System.out.println("======================================");
    }

    public void reAcomodarPiezas(ArrayList<Piece> piezas) {
        for (Piece p : piezas) {
            p.acomodarImagenNormal();
        }

        if (piezas.size() == 1) {

            piezas.get(0).setLocation(piezas.get(0).getPoint().x, piezas.get(0).getPoint().y);
            piezas.get(0).setSize(60, 60);

        }

        if (piezas.size() == 2) {

            piezas.get(0).setLocation(piezas.get(0).getPoint().x, piezas.get(0).getPoint().y + 15);
            piezas.get(1).setLocation(piezas.get(1).getPoint().x + 30, piezas.get(1).getPoint().y + 15);

            piezas.get(0).setSize(30, 30);
            piezas.get(1).setSize(30, 30);

            piezas.get(0).acomodarImagen();
            piezas.get(1).acomodarImagen();

        }

        if (piezas.size() == 3) {

            piezas.get(0).setLocation(piezas.get(0).getPoint().x, piezas.get(0).getPoint().y);
            piezas.get(1).setLocation(piezas.get(1).getPoint().x + 30, piezas.get(1).getPoint().y);
            piezas.get(2).setLocation(piezas.get(2).getPoint().x + 15, piezas.get(2).getPoint().y + 30);

            piezas.get(0).setSize(30, 30);
            piezas.get(1).setSize(30, 30);
            piezas.get(2).setSize(30, 30);

            piezas.get(0).acomodarImagen();
            piezas.get(1).acomodarImagen();
            piezas.get(2).acomodarImagen();

        }

        if (piezas.size() == 4) {
            piezas.get(0).setLocation(piezas.get(0).getPoint().x, piezas.get(0).getPoint().y);
            piezas.get(1).setLocation(piezas.get(1).getPoint().x + 30, piezas.get(1).getPoint().y);
            piezas.get(2).setLocation(piezas.get(2).getPoint().x, piezas.get(2).getPoint().y + 30);
            piezas.get(3).setLocation(piezas.get(3).getPoint().x + 30, piezas.get(3).getPoint().y + 30);

            piezas.get(0).setSize(30, 30);
            piezas.get(1).setSize(30, 30);
            piezas.get(2).setSize(30, 30);
            piezas.get(3).setSize(30, 30);

            piezas.get(0).acomodarImagen();
            piezas.get(1).acomodarImagen();
            piezas.get(2).acomodarImagen();
            piezas.get(3).acomodarImagen();

        }
        this.repaint();
    }


    public void actualizarPosicion(int posGlobal, int posGlobalAnterior, Piece piece) {
        if (posGlobalAnterior != -1) {
            System.out.println("En el arreglo SE ELIMINO EN LA POS: " + posGlobalAnterior);
            System.out.println("En el arreglo  " + piece.getPosArreglo());
            this.almacenDeColisiones[posGlobalAnterior].remove(piece.getPosArreglo());

            if (this.almacenDeColisiones[posGlobalAnterior].size() >= 1) {
                for (Piece p : this.almacenDeColisiones[posGlobalAnterior]) {
                    p.setPosArreglo(this.almacenDeColisiones[posGlobalAnterior].indexOf(p));
                }
                reAcomodarPiezas(this.almacenDeColisiones[posGlobalAnterior]);
            }
            System.out.println("posicion global" + posGlobal);
            System.out.println("posicion global anterior" + posGlobalAnterior);
        }

        String posiciones = "";
        for (int j = 0; j < this.almacenDeColisiones.length; j++) {
            if (this.almacenDeColisiones[j].size() > 0) {
                posiciones += this.almacenDeColisiones[j].size() + " (" + j + "),  ";
            }
        }
        System.out.println(posiciones);


    }

    public boolean esPosicionInmortal(int posicion) {
        for (int i = 0; i < posicionesInmortales.length; i++) {
            if (posicion == posicionesInmortales[i]) {
                return true;
            }
        }
        return false;
    }


    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public Player getJugadorTurno() {
        return jugadorTurno;
    }

    public void setJugadorTurno(Player jugadorTurno) {
        this.jugadorTurno = jugadorTurno;
    }

    public ArrayList<Player> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Player> jugadores) {
        this.jugadores = jugadores;
    }


    public void setPlayerTurno(String texto) {
        this.playerTurno.setText(texto);
    }

    public void activarBoton() {
        dadoBoton.setEnabled(true);
    }

    public int cantidadJugadores() {
        return jugadores.size();
    }

    public String getPathDado(int numero) {
        String dadoPath = "";
        switch (numero) {
            case 1:
                dadoPath = "Dice1.png";
                break;
            case 2:
                dadoPath = "Dice2.png";
                break;
            case 3:
                dadoPath = "Dice3.png";
                break;
            case 4:
                dadoPath = "Dice4.png";
                break;
            case 5:
                dadoPath = "Dice5.png";
                break;
            case 6:
                dadoPath = "Dice6.png";
                break;
            default:
                dadoPath = "nada";
                break;
        }
        return dadoPath;
    }

    public boolean sePuedePasarTurno(int[] posiciones, int moverse) {
        boolean[] puedenMoverse = new boolean[4];
        int contador = 0;
        for (int pos : posiciones) {
            if (pos == -1) {
                if (moverse == 6) {
                    puedenMoverse[contador] = true;
                } else {
                    puedenMoverse[contador] = false;
                }
            } else {
                if (pos + moverse <= 56) {
                    puedenMoverse[contador] = true;
                } else {
                    puedenMoverse[contador] = false;
                }
            }
            contador++;
        }

        if (puedenMoverse[0] == false && puedenMoverse[1] == false && puedenMoverse[2] == false && puedenMoverse[3] == false)
        {
            return false;
        }
        return true;
    }
}
