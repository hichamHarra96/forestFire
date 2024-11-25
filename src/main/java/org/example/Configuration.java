import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Configuration {
    private int height;
    private int width;
    private double propagationProbability;
    private List<int[]> firePositions;

    public Configuration(int height, int width, double propagationProbability, List<int[]> firePositions) {
        this.height = height;
        this.width = width;
        this.propagationProbability = propagationProbability;
        this.firePositions = firePositions;
    }

    public static Configuration chargerDepuisFichier(String cheminFichier) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(cheminFichier))) {
            int hauteur = 0, largeur = 0;
            double probabilite = 0.0;
            List<int[]> positionsFeu = new ArrayList<>();

            String ligne;
            while ((ligne = br.readLine()) != null) {
                ligne = ligne.trim();
                if (ligne.isEmpty() || ligne.startsWith("#")) continue;

                if (hauteur == 0 && largeur == 0) {
                    String[] dimensions = ligne.split(" ");
                    hauteur = Integer.parseInt(dimensions[0]);
                    largeur = Integer.parseInt(dimensions[1]);
                } else if (probabilite == 0.0) {
                    probabilite = Double.parseDouble(ligne);
                } else {
                    String[] position = ligne.split(" ");
                    positionsFeu.add(new int[]{Integer.parseInt(position[0]), Integer.parseInt(position[1])});
                }
            }

            return new Configuration(hauteur, largeur, probabilite, positionsFeu);
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public double getPropagationProbability() {
        return propagationProbability;
    }

    public List<int[]> getFirePositions() {
        return firePositions;
    }

    @Override
    public String toString() {
        return String.format("<html>Dimensions : %dx%d<br>Probabilité de propagation : %.2f</html>",
                height, width, propagationProbability);
    }
}
