package cattos;

import java.util.HashMap;

public class SubClass {
    public enum TYPE {
        LEADER, SENIOR_W, REGULAR_W, YOUNG_W,
        SENIOR_A, REGULAR_A, YOUNG_A,
        ELDER, QUEEN, FULL, APPRENTICE,
        REGULAR_K
    }

    private static HashMap<TYPE, Integer> warriorSubclasses = new HashMap<>();
    private static HashMap<TYPE, Integer> apprenticeSubclasses = new HashMap<>();
    private static HashMap<TYPE, Integer> miscSubclasses = new HashMap<>();
    private static HashMap<TYPE, Integer> medicineSubclasses = new HashMap<>();
    private static HashMap<TYPE, Integer> kitSubclasses = new HashMap<>();

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

    public static HashMap<TYPE, Integer> getWarriorSubclasses() {
        return warriorSubclasses;
    }

    public static HashMap<TYPE, Integer> getApprenticeSubclasses() {
        return apprenticeSubclasses;
    }

    public static HashMap<TYPE, Integer> getMiscSubclasses() {
        return miscSubclasses;
    }

    public static HashMap<TYPE, Integer> getMedicineSubclasses() {
        return medicineSubclasses;
    }

    public static HashMap<TYPE, Integer> getKitSubclasses() {
        return kitSubclasses;
    }
}
