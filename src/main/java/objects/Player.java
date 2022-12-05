package objects;

import view.Panel;

import javax.swing.*;
import java.util.ArrayList;

public class Player {

    private String nickName;
    private String color;
    private String path;
    private Panel panel;
    private  Piece [] piecesList = new Piece[4];

    public Player(String nickName, String color, Panel panel) {
        this.nickName = nickName;
        this.color = color;
        this.panel = panel;
        initPiece(panel);
    }

    public void initPiece (Panel panel){
        int x = 0;
        int y = 0;

        switch (color){
            case "red":
                x = 120;
                y = 120;
                this.path = "LudoRojo.png";
                break;
            case "blue":
                x = 120;
                y = 660;
                this.path = "LudoAzul.png";
                break;
            case "green":
                x = 660;
                y = 120;
                this.path = "LudoVerde.png";
                break;
            case "yellow":
                x = 660;
                y = 660;
                this.path = "LudoAmarillo.png";
                break;
        }

        int pos = 0;

        for ( int i = x; i <= x+60; i+=60) {
            for (int j = y; j <= y+60; j+=60) {
                piecesList[pos] = new Piece(i,j,path,color,panel,this);
                pos++;
            }
        }

    }
    public void siguienteTurno(){
        ArrayList<Player> jugadores = panel.getJugadores();

        if (panel.getTurno() == panel.cantidadJugadores()-1){
            panel.setTurno(0);
            panel.setJugadorTurno(jugadores.get(panel.getTurno()));
            panel.activarBoton();
            panel.setPlayerTurno("Turno de "+panel.getJugadorTurno().getNickName());

        }else {
            panel.setTurno(panel.getTurno()+1);
            panel.setJugadorTurno(jugadores.get(panel.getTurno()));
            panel.activarBoton();
            panel.setPlayerTurno("Turno de "+panel.getJugadorTurno().getNickName());
        }
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Piece[] getPiecesList() {
        return piecesList;
    }
}
