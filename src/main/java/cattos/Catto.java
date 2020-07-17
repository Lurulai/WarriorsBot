package cattos;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * An abstract Catto class with common methods and abstract ones that can be overridden.
 */
@JsonTypeInfo(use = Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "cattoClass")
@JsonSubTypes({
        @JsonSubTypes.Type(name = "Warrior", value = Warrior.class),
        @JsonSubTypes.Type(name = "Apprentice", value = Apprentice.class),
        @JsonSubTypes.Type(name = "Kit", value = Kit.class),
        @JsonSubTypes.Type(name = "Medicine", value = Medicine.class),
        @JsonSubTypes.Type(name = "Misc", value = Misc.class),
})
public abstract class Catto {
    // Age of catto
    private int age;
    // If the age of catto is frozen
    private boolean isAgeFrozen = false;
    // Name of catto
    private String name;
    // Main Class of catto
    private String cattoClass;
    // Picture of catto (if there is one)
    private String imageUrl;
    // Description of catto (if there is one)
    private String description;

    /**
     * Creates a cat given the class type of cat.
     * @param cattoClass string representation of cat's class
     */
    Catto(String cattoClass){
        this.cattoClass = cattoClass;
    }

    /**
     * Creates a cat given the name of the cat, the class type of the cat, and the description of the cat.
     * @param name name of the cat
     * @param cattoClass string representation of cat's class
     * @param description description of the cat
     */
    Catto(String name, String cattoClass, String description){
        this.name = name;
        this.cattoClass = cattoClass;
        this.description = description;
    }

    /**
     * Creates a cat given the name of the cat, the age of the cat, the class type of the cat, and the description of the cat.
     * @param name name of the cat
     * @param age age of the cat
     * @param cattoClass string representation of cat's class
     * @param description description of the cat
     */
    Catto(String name, int age, String cattoClass, String description){
        this.name = name;
        this.age = age;
        this.cattoClass = cattoClass;
        this.description = description;
    }

    /**
     * Gets the integer representation/value of the age of the cat.
     * @return int
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the cat given an age in integer.
     * @param age age of the cat
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Gets the name of the cat
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the image url of the cat
     * @param imageUrl string representation of the image url
     */
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    /**
     * Gets the image url of the cat
     * @return String
     */
    public String getImageUrl() { return imageUrl; }

    /**
     * Sets if the age of the cat is frozen or not
     * @param isAgeFrozen boolean
     */
    public void setAgeFrozen(boolean isAgeFrozen) { this.isAgeFrozen = isAgeFrozen; }

    /**
     * Gets whether the age of the cat is frozen or not
     * @return boolean
     */
    public boolean getAgeFrozen() { return isAgeFrozen; }

    /**
     * Sets the name of the cat
     * @param name name of the cat
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the class of the cat
     * @return String
     */
    public String getCattoClass(){
        return cattoClass;
    }

    /**
     * Sets the class of the cat
     * @param cattoClass string representation of cat's class
     */
    public void setCattoClass(String cattoClass){
        this.cattoClass = cattoClass;
    }

    /**
     * Increases the age of cats by one unless frozen
     */
    public void increaseAge(){
        if(!this.isAgeFrozen){
            this.age++;
        }
    }

    /**
     * Sets the subclass given an integer representation of a subclass, overridden by subclasses
     * @param subclass integer representation of a subclass
     * @return boolean
     */
    abstract boolean setSubClass(String subclass);

    /**
     * Gets the health of the cat, overridden by subclasses
     * @return int
     */
    abstract int getHealth();

    /**
     * Sets the health of the cat, overridden by subclasses
     * @param health health of the cat
     */
    abstract void setHealth(int health);

    /**
     * Gets the description of the cat
     * @return String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the cat
     * @param description description of the cat
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
