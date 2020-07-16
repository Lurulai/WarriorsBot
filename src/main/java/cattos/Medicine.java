package cattos;

public class Medicine extends Catto {

    private int health = 100;
    private SubClass.TYPE subClass;

    public Medicine(){
        super("Medicine");
    }

    public Medicine(String name, String description){
        super(name, "Medicine", description);
    }

    public Medicine(String name, int age, String description) {
        super(name, age, "Medicine", description);
    }

    public boolean setSubClass(int subClass) {
        switch(subClass){
            case 1:
                this.subClass = SubClass.TYPE.FULL;
                break;
            case 2:
                this.subClass = SubClass.TYPE.APPRENTICE;
                break;
            default : return true;
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
        if (this.subClass.equals(SubClass.TYPE.FULL)) {
            return "Full";
        } else if (this.subClass.equals(SubClass.TYPE.APPRENTICE)) {
            return "Apprentice";
        } else {
            return "";
        }
    }

}
