package zw.co.micnice.logic.utils;

/**
 *
 * @author Takunda Dhlakama
 */
public enum Gender {

    MALE("M"),
    FEMALE("F")
    ;

      private Gender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return gender;
    }

    @Override
    public String toString() {
        return gender;
    }
    
    

    private final String gender;
}
