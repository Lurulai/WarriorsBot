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

    public boolean setSubClass(String subClass) {
        switch(subClass.toLowerCase()){
            case "senior":
                this.subClass = SubClass.TYPE.SENIOR_A;
                break;
            case "regular":
                this.subClass = SubClass.TYPE.REGULAR_A;
                break;
            case "young":
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

    public SubClass.TYPE getSubClass() {
        return subClass;
    }

    public void setSubClass(SubClass.TYPE subClass) {
        this.subClass = subClass;
    }
}
