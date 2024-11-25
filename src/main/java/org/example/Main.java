package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                InputStream input = Main.class.getClassLoader().getResourceAsStream("config.json");
                if (input == null) {
                    throw new IOException("Configuration file not found in resources.");
                }
                ObjectMapper objectMapper = new ObjectMapper();
                Configuration config = objectMapper.readValue(input, Configuration.class);
                Forest forest = new Forest(config);
                ForestPanel forestPanel = new ForestPanel(forest);

                creerInterface(config, forest, forestPanel);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erreur : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    /**
     * Creates the graphical user interface for the simulation.
     *
     * @param config      The configuration of the simulation.
     * @param forest      The forest being simulated.
     * @param forestPanel The panel that displays the forest.
     */
    private static void creerInterface(Configuration config, Forest forest, ForestPanel forestPanel) {
        JFrame frame = new JFrame("Forest Fire Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Add the forest panel
        frame.add(forestPanel, BorderLayout.CENTER);

        // Display the configuration at the top
        JLabel labelConfig = new JLabel(config.toString());
        frame.add(labelConfig, BorderLayout.NORTH);

        // Add a button to advance to the next step
        JButton boutonEtape = new JButton("Next Step");
        boutonEtape.addActionListener(e -> {
            forest.simulateFire();
            forestPanel.repaint();

            if (!forest.hasBurningTrees()) {
                JOptionPane.showMessageDialog(frame, "The simulation is complete!");
            }
        });
        frame.add(boutonEtape, BorderLayout.SOUTH);

        frame.setSize(800, 800);
        frame.setVisible(true);
    }
}
