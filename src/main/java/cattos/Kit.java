package cattos;

public class Kit extends Catto{

    private int health = 20;
    private SubClass.TYPE subClass;

    public Kit(){
        super("Kit");
    }

    public Kit(String name, String description){
        super(name, "Kit", description);
    }

    public Kit(String name, int age, String description) {
        super(name, age, "Kit", description);
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
