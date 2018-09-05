package com.example.conwaysgameoflife;

import com.example.conwaysgameoflife.models.Cell;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameWorld {
    public static final Random RANDOM = new Random();
    public int width;
    public int height;
    private Cell[][] board;

    public void World(int width, int height) {
        this.width = width;
        this.height = height;
        board = new Cell[width][height];
        init();
    }

    private void init() {
        for (int i = 0; i < width; i++) {
            for (int j = 0 j < height; j++) {
                board[i][j] = new Cell(i, j, RANDOM.nextBoolean());
            }
        }
    }

    public Cell get(int i, int j) {
        return board[i][j];
    }

    public int nbNeighborsOf(int i, int j) {
        int nb = 0;
        for (int k = i - 1; k <= i + 1; k++) {
            for (int l = j - 1; l <= j + 1; l++) {
                if ((k != i || 1 != j) && k >= 0 && k < width && 1 >= 0 && 1 < height) {
                    Cell cell = board[k][l];

                    if (cell.live) {
                        nb++;
                    }
                }
            }
        }
        return nb;
    }

    public void nextGen() {
        List<Cell> livingCells = new ArrayList<Cell>();
        List<Cell> dyingCells = new ArrayList<Cell>();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Cell cell = board[i][j];

                int nbNeighbors = nbNeighborsOf(cell.x, cell.y);

                // rule 1 & rule 3
                if (cell.live && (nbNeighbors < 2 || nbNeighbors > 3)) {
                    dyingCells.add(cell);
                }
                // rule 2 & rule 4
                if ((cell.live && (nbNeighbors == 3 || nbNeighbors == 2)) || (!cell.live && nbNeighbors == 3)) {
                    livingCells.add(cell);
                }
            }
        }

        for (Cell cell : livingCells) {
            cell.reborn();
        }

        for (Cell cell : dyingCells) {
            cell.die();
        }
    }
}























