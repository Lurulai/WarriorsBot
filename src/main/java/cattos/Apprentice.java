package cattos;

/**
 * Subclass of Catto, for Apprentice
 */
public class Apprentice extends Catto{
    // Health of Apprentice cats
    private int health = 100;
    // Subclasses of Apprentice cats
    private SubClass.TYPE subClass;

    /**
     * Creates an Apprentice cat
     */
    public Apprentice(){
        super("Apprentice");
    }

    /**
     * Creates an Apprentice cat with name and description provided
     * @param name name of the cat
     * @param description description of the cat
     */
    public Apprentice(String name, String description){
        super(name, "Apprentice", description);
    }

    /**
     * Creates an Apprentice cat with name, age, and description provided
     * @param name name of the cat
     * @param age age of the cat
     * @param description description of the cat
     */
    public Apprentice(String name, int age, String description) {
        super(name, age, "Apprentice", description);
    }

    /**
     * Sets the subclass of the cat given the subclass type integer
     * @param subClass string representation of subclass
     * @return boolean
     */
    public boolean setSubClass(String subClass) {
        switch(subClass.toLowerCase()){
            case "senior":
            case "1":
                this.subClass = SubClass.TYPE.SENIOR_A;
                break;
            case "regular":
            case "2":
                this.subClass = SubClass.TYPE.REGULAR_A;
                break;
            case "young":
            case "3":
                this.subClass = SubClass.TYPE.YOUNG_A;
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
}
