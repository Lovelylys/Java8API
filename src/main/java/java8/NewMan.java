package java8;

import java.util.Optional;

/**
 * @author abs
 * @Date 2019/8/23 - 23:05
 */
public class NewMan {
    Optional<Godness> godness;

    public NewMan() {
    }

    public NewMan(Optional<Godness> godness) {
        this.godness = godness;
    }

    public Optional<Godness> getGodness() {
        return godness;
    }

    public void setGodness(Optional<Godness> godness) {
        this.godness = godness;
    }
}
