public class Hero extends DynamicThings {
    private static volatile Hero instance = null;

    private Hero(double x, double y, int width, int height) {
        super(x, y, width, height);
    }

    public static Hero getInstance(double x, double y, int width, int height) {
        if (instance == null) {
            synchronized (Hero.class) {
                if (instance == null) {
                    instance = new Hero(x, y, width, height);
                }
            }
        }
        return instance;
    }
}
