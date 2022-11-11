import java.io.Serializable;

public class mClase implements Serializable {

    protected String name;
//    protected int num1;
    transient protected int num1;
    protected double num2;

    public mClase() {
        this.name = "";
        this.num1 = 0;
        this.num2 = 0;
    }

    public mClase(String name, int num1, double num2) {
        this.name = name;
        this.num1 = num1;
        this.num2 = num2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum1() {
        return num1;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public double getNum2() {
        return num2;
    }

    public void setNum2(double num2) {
        this.num2 = num2;
    }

    @Override
    public String toString() {
        return "mClase{" +
                "name='" + name + '\'' +
                ", num1=" + num1 +
                ", num2=" + num2 +
                '}';
    }

}
