package cattos;

public class Misc extends Catto {

    private int health = 80;
    private SubClass.TYPE subClass;

    public Misc(){
        super("Misc");
    }

    public Misc(String name){
        super(name, "Misc");
    }

    public Misc(String name, int age) {
        super(name, age, "Misc");
    }

    public boolean setSubClass(int subClass) {
        switch(subClass){
            case 1:
                this.subClass = SubClass.TYPE.ELDER;
                break;
            case 2:
                this.subClass = SubClass.TYPE.QUEEN;
                break;
            default : return false;
        }
        return true;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public SubClass.TYPE getSubClass() {
        return subClass;
    }

}
