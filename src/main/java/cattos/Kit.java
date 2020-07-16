package cattos;

/**
 * Subclass of Catto, for Kit
 */
public class Kit extends Catto{
    // Health of Kit cats
    private int health = 20;
    // Subclasses of Kit cats
    private SubClass.TYPE subClass;

    /**
     * Creates an Kit cat
     */
    public Kit(){
        super("Kit");
    }

    /**
     * Creates an Kit cat with name and description provided
     * @param name name of the cat
     * @param description description of the cat
     */
    public Kit(String name, String description){
        super(name, "Kit", description);
    }

    /**
     * Creates an Kit cat with name, age, and description provided
     * @param name name of the cat
     * @param age age of the cat
     * @param description description of the cat
     */
    public Kit(String name, int age, String description) {
        super(name, age, "Kit", description);
    }

    /**
     * Sets the subclass of the cat given the subclass type integer
     * @param subClass string representation of subclass
     * @return boolean
     */
    public boolean setSubClass(String subClass) {
        if (subClass.equalsIgnoreCase("1") || subClass.equalsIgnoreCase("regular")) {
            this.subClass = SubClass.TYPE.REGULAR_K;
            return true;
        }
        return false;
    }

    /**
     * Gets the health of the cat
     * @return int
     */
    public int getHealth() {
        return health;
    }

    /**
     * Sets the health of the cat
     * @param health health of the cat
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Gets a string representation of the subclass of the cat
     * @return String
     */
    public String getSubClass() {
        if (this.subClass.equals(SubClass.TYPE.REGULAR_K)) {
            return "Regular";
        } else {
            return "";
        }
    }

}
