package cattos;

/**
 * Subclass of Catto, for Misc
 */
public class Misc extends Catto {
    // Health of Misc cats
    private int health = 80;
    // Subclasses of Misc cats
    private SubClass.TYPE subClass;

    /**
     * Creates an Misc cat
     */
    public Misc(){
        super("Misc");
    }

    /**
     * Creates an Misc cat with name and description provided
     * @param name name of the cat
     * @param description description of the cat
     */
    public Misc(String name, String description){
        super(name, "Misc", description);
    }

    /**
     * Creates an Misc cat with name, age, and description provided
     * @param name name of the cat
     * @param age age of the cat
     * @param description description of the cat
     */
    public Misc(String name, int age, String description) {
        super(name, age, "Misc", description);
    }

    /**
     * Sets the subclass of the cat given the subclass type integer
     * @param subClass string representation of subclass
     * @return boolean
     */
    public boolean setSubClass(String subClass) {
        switch(subClass.toLowerCase()){
            case "elder":
            case "1":
                this.subClass = SubClass.TYPE.ELDER;
                break;
            case "queen":
            case "2":
                this.subClass = SubClass.TYPE.QUEEN;
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
        if (this.subClass.equals(SubClass.TYPE.ELDER)) {
            return "Elder";
        } else if (this.subClass.equals(SubClass.TYPE.QUEEN)) {
            return "Queen";
        } else {
            return "";
        }
    }

}
