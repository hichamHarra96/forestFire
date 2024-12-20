# Feu de Forêt - Simulation de Propagation d'Incendie
### **Auteur :** Hicham HARRA

Ce projet simule la propagation d'un incendie dans une forêt représentée sous forme de grille. Chaque case de la grille correspond à un arbre, et son état peut évoluer au fil du temps en fonction des règles définies.

## **Description du Projet**

L'objectif est de modéliser un incendie et d'observer sa propagation. L'application est équipée d'une interface graphique qui permet de visualiser l'état de la forêt et de progresser étape par étape dans la simulation.

### **Caractéristiques :**
- Modélisation d'une forêt sous forme d'une grille 2D.
- Chaque arbre peut être dans l'un des états suivants : **sain (Arbre)**, **en feu**, ou **en cendres**.
- Propagation du feu avec une probabilité configurable.
- Visualisation des arbres à risque (proches d'arbres en feu).
- Interface graphique avec une vue dynamique de l'évolution.
### **Structure du Projet :**
- **`Configuration`** : Gère les paramètres de simulation (dimensions, probabilité, positions initiales), **configration dans config.json.**
- **`Forest`** : Implémente la grille, la logique de propagation et les étapes de simulation.
- **`Tree`** : Représente chaque arbre et son état.
- **`Main`** : Initialise l'application, charge les paramètres et lance l'interface graphique.
- **`ForestPanel`** : Gère l'interface graphique pour visualiser l'état de la forêt.

## **Configuration**

Le comportement de la simulation est configuré via un fichier JSON (`config.json`) situé dans les ressources du projet. Voici un exemple de configuration :

```json
{
  "height": 10,
  "width": 10,
  "propagationProbability": 0.7,
  "firePositions": [
    [1, 1],
    [2, 2]
  ]
}
```

## **Prérequis**

- **Java 17** 
- **Maven** 
---
## **Installation**

2. Compilez le projet et générez un JAR exécutable :
   ```bash
   mvn clean package
   ```

---

## **Exécution**

Une fois le projet compilé, lancez l'application avec la commande suivante depuis la racine du projet :

```bash
java -jar target/forestFire-1.0-SNAPSHOT-jar-with-dependencies.jar
```
---
