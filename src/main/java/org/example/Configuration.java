package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Configuration {
    private int height;
    private int width;
    private double propagationProbability;
    private List<int[]> firePositions;

    // Constructor
    public Configuration() {
        // Default constructor required for Jackson
    }

    // Getters and Setters
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public double getPropagationProbability() {
        return propagationProbability;
    }

    public void setPropagationProbability(double propagationProbability) {
        this.propagationProbability = propagationProbability;
    }

    public List<int[]> getFirePositions() {
        return firePositions;
    }

    public void setFirePositions(List<int[]> firePositions) {
        this.firePositions = firePositions;
    }

    // Load Configuration from JSON file
    public static Configuration loadFromFile(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(filePath), Configuration.class);
    }

    @Override
    public String toString() {
        return String.format("<html>Dimensions: %dx%d<br>Propagation Probability: %.2f</html>",
                height, width, propagationProbability);
    }
}
