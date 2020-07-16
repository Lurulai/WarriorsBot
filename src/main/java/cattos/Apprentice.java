package cattos;

public class Apprentice extends Catto{
    private int health = 100;
    private SubClass.TYPE subClass;

    public Apprentice(){
        super("Apprentice");
    }

    public Apprentice(String name){
        super(name, "Apprentice");
    }

    public Apprentice(String name, int age) {
        super(name, age, "Apprentice");
    }

    public boolean setSubClass(int subClass) {
        switch(subClass){
            case 1:
                this.subClass = SubClass.TYPE.SENIOR_A;
                break;
            case 2:
                this.subClass = SubClass.TYPE.REGULAR_A;
                break;
            case 3:
                this.subClass = SubClass.TYPE.YOUNG_A;
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
        if (this.subClass.equals(SubClass.TYPE.SENIOR_A)) {
            return "Senior";
        } else if (this.subClass.equals(SubClass.TYPE.REGULAR_A)) {
            return "Regular";
        } else if (this.subClass.equals(SubClass.TYPE.YOUNG_A)) {
            return "Young";
        } else {
            return "";
        }
    }

    public void setSubClass(SubClass.TYPE subClass) {
        this.subClass = subClass;
    }
}
