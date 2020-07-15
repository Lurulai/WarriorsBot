package cattos;

public abstract class Catto {
    int age;
    boolean isAgeFrozen = false;
    private String name;
    private String cattoClass;

    public Catto(String cattoClass){
        this.cattoClass = cattoClass;
    }

    public Catto(String name, String cattoClass){
        this.name = name;
        this.cattoClass = cattoClass;
    }

    public Catto(String name, int age, String cattoClass){
        this.name = name;
        this.age = age;
        this.cattoClass = cattoClass;
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

    abstract boolean setSubClass(String subclass);
}
