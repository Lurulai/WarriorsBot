package cattos;

public class Misc extends Catto {

    private int health = 80;
    private SubClass.TYPE subClass;

    public Misc(){
        super("Misc");
    }

    public Misc(String name, String description){
        super(name, "Misc", description);
    }

    public Misc(String name, int age, String description) {
        super(name, age, "Misc", description);
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

    public String getSubClass() {
        if (this.subClass.equals(SubClass.TYPE.ELDER)) {
            return "Elder";
        } else if (this.subClass.equals(SubClass.TYPE.QUEEN)) {
            return "Queen";
        } else {
            return "";
        }
    }

}
