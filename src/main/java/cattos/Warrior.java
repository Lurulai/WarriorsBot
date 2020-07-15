package cattos;

public class Warrior extends Catto{
    private int health = 100;
    private SubClass.TYPE subClass;

    public Warrior(){
        super("Warrior");
    }

    public Warrior(String name){
        super(name, "Warrior");
    }

    public Warrior(String name, int age) {
        super(name, age, "Warrior");
    }

    public boolean setSubClass(int subClass) {
        switch(subClass){
            case 1:
                this.subClass = SubClass.TYPE.LEADER;
                break;
            case 2:
                this.subClass = SubClass.TYPE.SENIOR_W;
                break;
            case 3:
                this.subClass = SubClass.TYPE.REGULAR_W;
                break;
            case 4:
                this.subClass = SubClass.TYPE.YOUNG_W;
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
