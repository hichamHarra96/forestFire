package org.example;

import javax.swing.*;
import java.awt.*;

public class ForestPanel extends JPanel {
    private Forest forest;

    public ForestPanel(Forest forest) {
        this.forest = forest;
        setLayout(new GridLayout(forest.getGrid().length, forest.getGrid()[0].length));
    }

    /**
     * Paints the current state of the forest grid.
     * <p>
     * Trees are colored based on their state:
     * - Green: Healthy trees.
     * - Red: Burning trees.
     * - Gray: Ash (burnt trees).
     * - Semi-transparent yellow: Trees at risk of catching fire.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Tree[][] grille = forest.getGrid();

        int cellWidth = getWidth() / grille[0].length;
        int cellHeight = getHeight() / grille.length;

        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[i].length; j++) {
                Tree cellule = grille[i][j];

                if (cellule.isAtRisk()) {
                    g.setColor(new Color(255, 255, 0, 128));
                } else {
                    switch (cellule.getState()) {
                        case HEALTHY -> g.setColor(Color.GREEN);
                        case BURNING -> g.setColor(Color.RED);
                        case ASH -> g.setColor(Color.GRAY);
                    }
                }
                g.fillRect(j * cellWidth, i * cellHeight, cellWidth, cellHeight);
                g.setColor(Color.BLACK);
                g.drawRect(j * cellWidth, i * cellHeight, cellWidth, cellHeight);
            }
        }
    }
}
