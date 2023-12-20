import java.util.ArrayList;
import java.util.List;

public class Dungeon {
    private final char[][] map;
    private final int height;
    private final int width;
    private final TileManager tileManager;
    private Things[] thingsArray; // Utilisation d'un tableau dynamique
    public Things[] getThingsArray() {
        return thingsArray;
    }

    // Constructeur
    public Dungeon(int height, int width, TileManager tileManager) {
        this.height = height;
        this.width = width;
        this.tileManager = tileManager;
        this.map = new char[height][width];

        // Initialiser le tableau du donjon
        initializeDungeon();
        // Remplir le tableau des Things en fonction de la map
        fillThingsArray();

        // Créer un Hitbox pour le héros à la position (2, 2) en tuiles
        HitBox hero = new HitBox(2 * tileManager.getWidth(), 2 * tileManager.getHeight(),
                tileManager.getWidth(), tileManager.getHeight());

        // Afficher le donjon avec la position du héros
        displayDungeonInConsole(hero);

        // Afficher les Things dans le donjon
        displayThingsInConsole();
    }

    // Méthode pour initialiser le tableau du donjon
    private void initializeDungeon() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 || i == height - 1 || j == 0 || j == width - 1) {
                    // Sur les bords, initialiser avec 'W' (Wall)
                    map[i][j] = 'W';
                } else {
                    // À l'intérieur, initialiser avec ' ' (espace)
                    map[i][j] = ' ';
                }
            }
        }
    }

    // Méthode pour remplir le tableau des Things en fonction de la map
    private void fillThingsArray() {
        List<Things> thingsList = new ArrayList<>();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int xPixel = j * tileManager.getWidth();
                int yPixel = i * tileManager.getHeight();

                if (map[i][j] == ' ') {
                    // Pour chaque caractère ' ' (espace), ajouter une instance de Things
                    thingsList.add(new Things(xPixel, yPixel, tileManager.getWidth(), tileManager.getHeight()));
                } else if (map[i][j] == 'W') {
                    // Pour chaque caractère 'W' (wall), ajouter une instance de SolidThings
                    thingsList.add(new SolidThings(xPixel, yPixel, tileManager.getWidth(), tileManager.getHeight()));
                }
            }
        }

        // Convertir la liste en tableau
        thingsArray = thingsList.toArray(new Things[thingsList.size()]);
    }

    // Méthode pour afficher le tableau du donjon dans la console avec la position du héros
    public void displayDungeonInConsole(HitBox hero) {
        int heroX = (int) (hero.getX() / tileManager.getWidth());
        int heroY = (int) (hero.getY() / tileManager.getHeight());

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == heroY && j == heroX) {
                    // Position du héros
                    System.out.print("H ");
                } else {
                    System.out.print(map[i][j] + " ");
                }
            }
            System.out.println(); // Nouvelle ligne pour chaque ligne du donjon
        }
    }

    // Méthode pour afficher les Things dans le donjon
    public void displayThingsInConsole() {
        for (Things thing : thingsArray) {
            if (thing instanceof SolidThings) {
                SolidThings solidThing = (SolidThings) thing;
                System.out.println("SolidThing at (" + thing.getX() + ", " + thing.getY() + ") with size (" + thing.getWidth() + ", " + thing.getHeight() + ")");
            } else {
                System.out.println("Thing at (" + thing.getX() + ", " + thing.getY() + ") with size (" + thing.getWidth() + ", " + thing.getHeight() + ")");
            }
        }
    }

    // Méthode main pour exécuter l'application
    public static void main(String[] args) {
        // Créer un TileManager avec une taille de tuile de 32x32 pixels
        TileManager tileManager = new TileManager(32, 32);

        // Créer un Dungeon avec une hauteur et une largeur de 10x10 tuiles
        Dungeon dungeon = new Dungeon(10, 10, tileManager);

        Hero hero1 = Hero.getInstance(10.0, 20.0, 30, 40);
        Hero hero2 = Hero.getInstance(50.0, 60.0, 70, 80);

// hero1 et hero2 référencent la même instance de Hero
        System.out.println(hero1 == hero2); // Cela devrait imprimer "true"
    }
}
