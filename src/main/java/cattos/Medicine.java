package cattos;

/**
 * Subclass of Catto, for Medicine
 */
public class Medicine extends Catto {
    // Health of Medicine cats
    private int health = 100;
    // Subclasses of Medicine cats
    private SubClass.TYPE subClass;

    /**
     * Creates an Medicine cat
     */
    public Medicine(){
        super("Medicine");
    }

    /**
     * Creates an Medicine cat with name and description provided
     * @param name name of the cat
     * @param description description of the cat
     */
    public Medicine(String name, String description){
        super(name, "Medicine", description);
    }

    /**
     * Creates an Medicine cat with name, age, and description provided
     * @param name name of the cat
     * @param age age of the cat
     * @param description description of the cat
     */
    public Medicine(String name, int age, String description) {
        super(name, age, "Medicine", description);
    }

    /**
     * Sets the subclass of the cat given the subclass type integer
     * @param subClass string representation of subclass
     * @return boolean
     */
    public boolean setSubClass(String subClass) {
        switch(subClass.toLowerCase()){
            case "full":
            case "1":
                this.subClass = SubClass.TYPE.FULL;
                break;
            case "apprentice":
            case "2":
                this.subClass = SubClass.TYPE.APPRENTICE;
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
        if (this.subClass.equals(SubClass.TYPE.FULL)) {
            return "Full";
        } else if (this.subClass.equals(SubClass.TYPE.APPRENTICE)) {
            return "Apprentice";
        } else {
            return "";
        }
    }

}
