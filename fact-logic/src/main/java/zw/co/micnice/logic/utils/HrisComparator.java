package zw.co.micnice.logic.utils;

import java.io.Serializable;
import java.util.List;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

/**
 *
 * @author charlesc
 */
public class HrisComparator implements Serializable {

    public List<?> sort(List<?> list) {
        if (list != null && !list.isEmpty()) {
            PropertyComparator.sort(list, new MutableSortDefinition("name", true, true));
        }
        return list;
    }

    public List<?> sortExamPassRates(List<?> list) {
        if (list != null && !list.isEmpty()) {
            PropertyComparator.sort(list, new MutableSortDefinition("percentagePassed", true, false));
        }
        return list;
    }

    public List<?> sortExamCandidateMarks(List<?> list) {
        if (list != null && !list.isEmpty()) {
            PropertyComparator.sort(list, new MutableSortDefinition("finalMark", true, false));
        }
        return list;
    }

    public List<?> sortExamMarks(List<?> list) {
        if (list != null && !list.isEmpty()) {
            PropertyComparator.sort(list, new MutableSortDefinition("mark", true, false));
        }
        return list;
    }

    public List<?> sortExaminationCards(List<?> list) {
        if (list != null && !list.isEmpty()) {
            PropertyComparator.sort(list, new MutableSortDefinition("examPaper", true, true));
        }
        return list;
    }

    public List<?> receiptItems(List<?> list) {
        if (list != null && !list.isEmpty()) {
            PropertyComparator.sort(list, new MutableSortDefinition("codeValue", true, true));
        }
        return list;
    }

    public List<?> sortRegistrants(List<?> list) {
        if (list != null && !list.isEmpty()) {
            PropertyComparator.sort(list, new MutableSortDefinition("lastname", true, true));
        }
        return list;
    }

    public List<?> sortExamRegistrationBySurname(List<?> list) {
        if (list != null && !list.isEmpty()) {
            PropertyComparator.sort(list, new MutableSortDefinition("registration.registrant.lastname", true, true));
        }
        return list;
    }
}
