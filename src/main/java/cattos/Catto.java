package cattos;

public abstract class Catto {
    int age;
    boolean isAgeFrozen = false;
    private String name;
    private String cattoClass;
    private String imageUrl;
    private String description;

    public Catto(String cattoClass){
        this.cattoClass = cattoClass;
    }

    public Catto(String name, String cattoClass, String description){
        this.name = name;
        this.cattoClass = cattoClass;
        this.description = description;
    }

    public Catto(String name, int age, String cattoClass, String description){
        this.name = name;
        this.age = age;
        this.cattoClass = cattoClass;
        this.description = description;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getImageUrl() { return imageUrl; }

    public void setAgeFrozen(boolean isAgeFrozen) { this.isAgeFrozen = isAgeFrozen; }

    public boolean getAgeFrozen() { return isAgeFrozen; }

    public void setName(String name) {
        this.name = name;
    }

    public String getCattoClass(){
        return cattoClass;
    }

    public void setCattoClass(String cattoClass){
        this.cattoClass = cattoClass;
    }

    public void increaseAge(){
        if(!this.isAgeFrozen){
            this.age++;
        }
    }

    abstract boolean setSubClass(int subclass);
    abstract int getHealth();
    abstract void setHealth(int health);

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
