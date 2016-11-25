package book.java8InAction.domain;

import com.google.common.base.Objects;

/**
 * Created by ahernandez on 10/28/16.
 */
public class Apple {

    private String color;
    private String origin;
    private int weight;

    public Apple() {
    }

    public Apple(String color) {
        this.color = color;
        this.origin = "MEXICO";
        this.weight = 10;
    }

    public Apple(String color, String origin) {
        this.color = color;
        this.origin = origin;
        this.weight = 20;
    }

    public Apple(String color, String origin, int weight) {
        this.color = color;
        this.origin = origin;
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }

        final Apple that = (Apple) o;
        return Objects.equal(this.getColor(), that.getColor())
                && Objects.equal(this.getWeight(), that.getWeight())
                && Objects.equal(this.getOrigin(), that.getOrigin());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getColor(), this.getWeight(), this.getOrigin());
    }

    @Override
    public String toString() {
        return "Apple{" +
                "color='" + color + '\'' +
                ", origin='" + origin + '\'' +
                ", weigth=" + weight +
                "}\n";
    }
}
