package zw.co.micnice.logic.domain;

/**
 *
 * @author tdhlakama
 * @author Michael Matiashe
 */
public enum TypeOfService {

    REGISTRATION_FEES("Registration Fees"),
    RE_REGISTRATION_FEES("Re-Registration Fees"),
    EXAMS_FEES("Exam Fees"), 
    ANNUAL_FEES("Annual Fees"), 
    PENALTY_FEES("Penalty Fees"),
    APPLICATION_FEES("Application Fees"),
    APPLICATION_FOR_CGS_FEES("Application for CGS"),
    APPLICATION_FOR_UNRESTRICTED_PRACTICE("Application For Unrestricted Prastice Fees"),
    APPLICATION_FOR_INSTITUTION_REGISTRATION("Application For Institution Registration Fees"),
    INSTITUTION__REGISTRATION_FEES("Institution Registration Fees"),
    OTHER_FEES("Other Fees"),
    TRANSFER_FEES("Transfer"),
    CHANGE_OF_NAME_FEES("Change Of Name Fees"),
    QUALIFYING_FEES("Qualifying Fees"),
    PRACTISING_CERTIFICATE_FEES("Practising Certificate Fee"),
    PROVISIONAL_QUALIFICATION_FEES("Provisional Qualification Fee"),
    TRANSCRIPT_OF_TRAINING_FEES("Transcript Of Training Fee");
    
    private final String name;

    private TypeOfService(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
