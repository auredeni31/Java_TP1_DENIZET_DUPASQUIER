public class Dungeon {
    private final char[][] dungeonMap;
    private final int height;
    private final int width;
    private final TileManager tileManager;

    public Dungeon(int height, int width, TileManager tileManager) {
        this.height = height;
        this.width = width;
        this.tileManager = tileManager;
        this.dungeonMap = new char[height][width];

        initializeDungeon();
    }

    private void initializeDungeon() {
        // Initialize dungeon with walls on borders and empty spaces inside
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 || i == height - 1 || j == 0 || j == width - 1) {
                    dungeonMap[i][j] = 'W'; // Walls on borders
                } else {
                    dungeonMap[i][j] = ' '; // Empty spaces inside
                }
            }
        }
    }

    public void displayDungeonInConsole(HitBox hero) {
        // Display dungeon with hero position marked as 'H'
        int heroX = hero.getX() / tileManager.getWidth();
        int heroY = hero.getY() / tileManager.getHeight();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == heroY && j == heroX) {
                    System.out.print("H ");
                } else {
                    System.out.print(dungeonMap[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        TileManager tileManager = new TileManager(20, 20); // Adjust with your tile sizes
        Dungeon dungeon = new Dungeon(10, 10, tileManager); // Adjust with your dungeon dimensions

        HitBox hero = new HitBox(40, 40, 20, 20); // Adjust hero position and size
        dungeon.displayDungeonInConsole(hero);
    }
}
