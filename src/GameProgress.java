import java.io.Serializable;

public class GameProgress implements Serializable {

    private static final long serialVersionUID = 1L;

    private int health; //здоровье
    private int weapons; // оружие
    private int lvl; //уровень
    private double distance; //расстояние

    public GameProgress(int health, int weapons, int lvl, double distance) {
        this.health = health;
        this.weapons = weapons;
        this.lvl = lvl;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "GameProgress{" +
                "health=" + health +
                ", weapons=" + weapons +
                ", lvl=" + lvl +
                ", distance=" + distance +
                '}';
    }

}