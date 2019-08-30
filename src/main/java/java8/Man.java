package java8;

/**
 * @author abs
 * @Date 2019/8/23 - 23:01
 */
public class Man {
    Godness godness;

    public Man(Godness godness) {
        this.godness = godness;
    }

    @Override
    public String toString() {
        return "Man{" +
                "godness=" + godness +
                '}';
    }

    public Godness getGodness() {
        return godness;
    }

    public void setGodness(Godness godness) {
        this.godness = godness;
    }
}
