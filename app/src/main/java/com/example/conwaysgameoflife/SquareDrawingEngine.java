package com.example.conwaysgameoflife;

import android.graphics.Canvas;

import java.util.Set;

public class SquareDrawingEngine {
    public Set<Square> squares;
    public boolean[][] gameBoard;

    public boolean hasSquare;

    public boolean hasTooFewToLive;
    public boolean hasEnoughToLive;
    public boolean hasTooManyToLive;
    public boolean hasEnoughToBeBorn;

    public void drawGameBoard(){

        gameBoard = new boolean[][]{
                {false, false, true, false, false, false, true, false, false, false},
                {false, true, false, true, false, false, false, true, true, false},
                {false, true, false, false, true, false, false, false, false, false},
                {false, false, true, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, true, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false},
                {false, true, true, false, false, false, true, false, true, true},
                {false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, true},
        };

        for(int row = 0; row < gameBoard.length; row++) {
            for (int col = 0; col < gameBoard[row].length; col++) {
                if (gameBoard[row][col] == true) {

                }
            }
        }
    }

    private static boolean getCellValue(boolean[][] aa, int row, int col) {

        if (row < 0 || col < 0 || row >= aa.length || col >= aa[row].length) {
            return false;
        }

        return aa[row][col];
    }

    private int checkNeighbor(boolean[][] aa, int row, int col) {
        boolean cell = aa[row][col];
        int count = 0;

        hasSquare = getCellValue(aa, row, col + 1);
        System.out.println(cell + " x " + getCellValue(aa, row, col + 1) + " equals " + hasSquare);

        if (hasSquare) {
            count++;
        }

        hasSquare = getCellValue(aa, row + 1, col + 1);
        System.out.println(cell + " x " + getCellValue(aa, row + 1, col + 1) + " equals " + hasSquare);

        if (hasSquare) {
            count++;
        }

        hasSquare = getCellValue(aa, row + 1, col);
        System.out.println(cell + " x " + getCellValue(aa, row + 1, col) + " equals " + hasSquare);

        if (hasSquare) {
            count++;
        }

        hasSquare = getCellValue(aa, row + 1, col - 1);
        System.out.println(cell + " x " + getCellValue(aa, row + 1, col - 1) + " equals " + hasSquare);

        if (hasSquare) {
            count++;
        }

        System.out.println();
        return count;
    }

    public void doesHaveNeighbors(boolean[][] aa) {
        for (int row = 0; row < aa.length; row++) {
            for (int col = 0; col < aa[row].length; col++) {
                int neighbors = checkNeighbor(aa, row, col);
                if (neighbors < 2) {
                    hasTooFewToLive = true;
                } else if (neighbors == 2 || neighbors == 3) {
                    hasEnoughToLive = true;
                } else if (neighbors > 3) {
                    hasTooManyToLive = true;
                } else if (neighbors == 3) {
                    hasEnoughToBeBorn = true;
                }
                System.out.println(neighbors);
            }
        }
    }

    public void drawAll(Canvas canvas) {
        for (Square square : squares) {
            square.draw(canvas);
        }
    }

    public void add(Square square) {
        squares.add(square);
    }
}
