import java.util.ArrayList;

public class Elevator {

    protected boolean bussy;
    protected int weith;
    protected int actualPsoition;
    protected ArrayList desttinys;

    public Elevator() {
        this.bussy = false;
        this.weith = 0;
        this.actualPsoition = 0;
        this.desttinys = new ArrayList();
    }

    public Elevator(boolean bussy, int weith, int actualPsoition, ArrayList desttinys) {
        this.bussy = bussy;
        this.weith = weith;
        this.actualPsoition = actualPsoition;
        this.desttinys = desttinys;
    }

    public boolean isBussy() {
        return bussy;
    }

    public void setBussy(boolean bussy) {
        this.bussy = bussy;
    }

    public int getWeith() {
        return weith;
    }

    public void setWeith(int weith) {
        this.weith = weith;
    }

    public int getActualPsoition() {
        return actualPsoition;
    }

    public void setActualPsoition(int actualPsoition) {
        this.actualPsoition = actualPsoition;
    }

    public ArrayList getDesttinys() {
        return desttinys;
    }

    public void setDesttinys(ArrayList desttinys) {
        this.desttinys = desttinys;
    }
}
