package cattos;

import java.util.HashMap;

/**
 * A class dedicated to keeping track of all the subclasses and what type has certain bonuses
 */
public class SubClass {
    /**
     * Enumeration called TYPE of all type of subclasses
     */
    public enum TYPE {
        LEADER, SENIOR_W, REGULAR_W, YOUNG_W,
        SENIOR_A, REGULAR_A, YOUNG_A,
        ELDER, QUEEN, FULL, APPRENTICE,
        REGULAR_K
    }

    // Variables that are used for different types of classes and their subclasses
    private static HashMap<TYPE, Integer> warriorSubclasses = new HashMap<>();
    private static HashMap<TYPE, Integer> apprenticeSubclasses = new HashMap<>();
    private static HashMap<TYPE, Integer> miscSubclasses = new HashMap<>();
    private static HashMap<TYPE, Integer> medicineSubclasses = new HashMap<>();
    private static HashMap<TYPE, Integer> kitSubclasses = new HashMap<>();

    /**
     * Initializes all the subclasses, their types and bonus effects into their respective class maps
     */
    public static void initializeAllSubclasses(){
        warriorSubclasses.put(TYPE.LEADER, 5);
        warriorSubclasses.put(TYPE.SENIOR_W, 4);
        warriorSubclasses.put(TYPE.REGULAR_W, 3);
        warriorSubclasses.put(TYPE.YOUNG_W, 2);
        apprenticeSubclasses.put(TYPE.SENIOR_A, 1);
        apprenticeSubclasses.put(TYPE.REGULAR_A, 0);
        apprenticeSubclasses.put(TYPE.YOUNG_A, -1);
        miscSubclasses.put(TYPE.ELDER, -2);
        miscSubclasses.put(TYPE.QUEEN, -3);
        medicineSubclasses.put(TYPE.FULL, 2);
        medicineSubclasses.put(TYPE.APPRENTICE, 0);
        kitSubclasses.put(TYPE.REGULAR_K, -4);
    }

    /**
     * Gets the subclasses of Warrior class
     * @return HashMap\<TYPE, Integer\>
     */
    public static HashMap<TYPE, Integer> getWarriorSubclasses() {
        return warriorSubclasses;
    }

    /**
     * Gets the subclasses of Apprentice class
     * @return HashMap\<TYPE, Integer\>
     */
    public static HashMap<TYPE, Integer> getApprenticeSubclasses() {
        return apprenticeSubclasses;
    }

    /**
     * Gets the subclasses of Misc class
     * @return HashMap\<TYPE, Integer\>
     */
    public static HashMap<TYPE, Integer> getMiscSubclasses() {
        return miscSubclasses;
    }

    /**
     * Gets the subclasses of Medicine class
     * @return HashMap\<TYPE, Integer\>
     */
    public static HashMap<TYPE, Integer> getMedicineSubclasses() {
        return medicineSubclasses;
    }

    /**
     * Gets the subclasses of Kit class
     * @return HashMap\<TYPE, Integer\>
     */
    public static HashMap<TYPE, Integer> getKitSubclasses() {
        return kitSubclasses;
    }
}
