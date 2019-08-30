package java8;

/**
 * @author abs
 * @Date 2019/8/23 - 23:02
 */
public class Godness {
    String name;

    public Godness(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Godness{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
