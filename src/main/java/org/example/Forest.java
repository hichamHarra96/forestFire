package org.example;

import java.util.Random;

public class Forest {
    private Tree[][] grid;
    private int height;
    private int width;
    private double propagationProbability;

    public Forest(Configuration config) {
        this.height = config.getHeight();
        this.width = config.getWidth();
        this.propagationProbability = config.getPropagationProbability();
        this.grid = new Tree[height][width];

        initializeGrid(config);
        markAtRiskTrees();
    }

    private void initializeGrid(Configuration config) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = new Tree();
            }
        }
        for (int[] fire : config.getFirePositions()) {
            grid[fire[0]][fire[1]].ignite();
        }
    }

    public void simulateFire() {
        Tree[][] newGrid = new Tree[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                newGrid[i][j] = grid[i][j].copy();
            }
        }

        Random random = new Random();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (grid[i][j].getState().equals(TreeState.BURNING)) {
                    newGrid[i][j].extinguish();
                    for (int[] dir : new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}) {
                        int nx = i + dir[0];
                        int ny = j + dir[1];
                        if (nx >= 0 && nx < height && ny >= 0 && ny < width && grid[nx][ny].getState().equals(TreeState.HEALTHY)) {
                            if (random.nextDouble() < propagationProbability) {
                                newGrid[nx][ny].ignite();
                            }
                        }
                    }
                }
            }
        }

        grid = newGrid;
        markAtRiskTrees();
    }

    /**
     * Marks adjacent trees to burning trees as "at risk".
     */
    public void markAtRiskTrees() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j].setAtRisk(false);
            }
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (grid[i][j].getState().equals(TreeState.BURNING)) {
                    for (int[] dir : new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}) {
                        int nx = i + dir[0];
                        int ny = j + dir[1];
                        if (nx >= 0 && nx < height && ny >= 0 && ny < width && grid[nx][ny].getState().equals(TreeState.HEALTHY)) {
                            grid[nx][ny].setAtRisk(true);
                        }
                    }
                }
            }
        }
    }

    public boolean hasBurningTrees() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (grid[i][j].getState().equals(TreeState.BURNING)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Tree[][] getGrid() {
        return grid;
    }
}
