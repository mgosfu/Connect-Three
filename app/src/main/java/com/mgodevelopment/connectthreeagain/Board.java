package com.mgodevelopment.connectthreeagain;

import java.util.ArrayList;

/**
 * Created by Martin on 10/17/2016.
 */

public class Board {
    ArrayList<BoardPosition> positions;

    public Board() {
    }

    public ArrayList<BoardPosition> getPositions() {
        return positions;
    }

    public void setPositions(ArrayList<BoardPosition> positions) {
        this.positions = positions;
    }

}
