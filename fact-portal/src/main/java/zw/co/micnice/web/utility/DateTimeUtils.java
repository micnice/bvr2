package zw.co.micnice.web.utility;

//~--- non-JDK imports --------------------------------------------------------

import org.apache.wicket.datetime.DateConverter;
import org.apache.wicket.datetime.PatternDateConverter;

//~--- JDK imports ------------------------------------------------------------

import java.io.Serializable;

import java.util.Locale;

/**
 *
 * @author Charles Chigoriwa
 */
public class DateTimeUtils implements Serializable {
    private static String US_DATE_PATTERN = "MM/dd/yyyy";
    private static String UK_DATE_PATTERN = "dd/MM/yyyy";

    public static DateConverter getConverter(Locale locale) {
        if (Locale.US.equals(locale)) {
            return new PatternDateConverter(US_DATE_PATTERN, true);
        } else if (Locale.UK.equals(locale)) {
            return new PatternDateConverter(UK_DATE_PATTERN, true);
        }

        return new PatternDateConverter(UK_DATE_PATTERN, true);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
