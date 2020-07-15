package cattos;

public class Medicine extends Catto {

    private int health = 100;
    private SubClass.TYPE subClass;

    public Medicine(){
        super("Medicine");
    }

    public Medicine(String name){
        super(name, "Medicine");
    }

    public Medicine(String name, int age) {
        super(name, age, "Medicine");
    }

    public boolean setSubClass(String subClass) {
        switch(subClass.toLowerCase()){
            case "full":
                this.subClass = SubClass.TYPE.FULL;
                break;
            case "apprentice":
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

    public SubClass.TYPE getSubClass() {
        return subClass;
    }

}
