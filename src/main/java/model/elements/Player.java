package model.elements;

import model.Matrix;
import model.Position;

import java.util.ArrayList;
import java.util.Arrays;

public class Player extends Element {
    private int lives;
    private int nCoins;

    public Player(Position position) {
        super(position, new Matrix<Character> (1, 1, 'P'));
        this.lives = 1;
        this.nCoins = 0;
    }


}
