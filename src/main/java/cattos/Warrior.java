package cattos;

/**
 * Subclass of Catto, for Warrior
 */
public class Warrior extends Catto{
    // Health of Warrior cats
    private int health = 100;
    // Subclasses of Warrior cats
    private SubClass.TYPE subClass;

    /**
     * Creates an Warrior cat
     */
    public Warrior(){
        super("Warrior");
    }

    /**
     * Creates an Warrior cat with name and description provided
     * @param name name of the cat
     * @param description description of the cat
     */
    public Warrior(String name, String description){
        super(name, "Warrior", description);
    }

    /**
     * Creates an Warrior cat with name, age, and description provided
     * @param name name of the cat
     * @param age age of the cat
     * @param description description of the cat
     */
    public Warrior(String name, int age, String description) {
        super(name, age, "Warrior", description);
    }

    /**
     * Sets the subclass of the cat given the subclass type integer
     * @param subClass string representation of subclass
     * @return boolean
     */
    public boolean setSubClass(String subClass) {
        switch(subClass.toLowerCase()){
            case "leader":
            case "1":
                this.subClass = SubClass.TYPE.LEADER;
                break;
            case "senior":
            case "2":
                this.subClass = SubClass.TYPE.SENIOR_W;
                break;
            case "regular":
            case "3":
                this.subClass = SubClass.TYPE.REGULAR_W;
                break;
            case "young":
            case "4":
                this.subClass = SubClass.TYPE.YOUNG_W;
                break;
            default : return false;
        }
        return true;
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
        if (this.subClass.equals(SubClass.TYPE.LEADER)) {
            return "Leader";
        } else if (this.subClass.equals(SubClass.TYPE.SENIOR_W)) {
            return "Senior";
        } else if (this.subClass.equals(SubClass.TYPE.REGULAR_W)) {
            return "Regular";
        } else if (this.subClass.equals(SubClass.TYPE.YOUNG_W)) {
            return "Young";
        } else {
            return "";
        }
    }

}
