package cattos;

public class Kit extends Catto{

    private int health = 20;
    private SubClass.TYPE subClass;

    public Kit(){
        super("Kit");
    }

    public Kit(String name){
        super(name, "Kit");
    }

    public Kit(String name, int age) {
        super(name, age, "Kit");
    }

    public boolean setSubClass(int subClass) {
        if (subClass == 1) {
            this.subClass = SubClass.TYPE.REGULAR_K;
            return true;
        }
        return false;
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
