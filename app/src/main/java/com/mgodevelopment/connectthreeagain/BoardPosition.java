package com.mgodevelopment.connectthreeagain;

import android.widget.ImageView;

/**
 * Created by Martin on 10/16/2016.
 */

public class BoardPosition {

    ImageView gamePiece;
    boolean isTaken;
    int position;
    String player;

    public BoardPosition() {
    }

    public BoardPosition(ImageView gamePiece, boolean isTaken, String player) {
        this.gamePiece = gamePiece;
        this.isTaken = isTaken;
        this.position = position;
        this.player = player;
    }

    public BoardPosition(ImageView gamePiece, boolean isTaken, int position, String player) {
        this.gamePiece = gamePiece;
        this.isTaken = isTaken;
        this.position = position;
        this.player = player;
    }

    public ImageView getGamePiece() {
        return gamePiece;
    }

    public void setGamePiece(ImageView gamePiece) {
        this.gamePiece = gamePiece;
    }

    public boolean isTaken() {
        return isTaken;
    }

    public void setTaken(boolean taken) {
        isTaken = taken;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }


}
