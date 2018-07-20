package zw.co.micnice.reports;

//~--- non-JDK imports --------------------------------------------------------

import zw.co.micnice.reports.utils.ContentType;

//~--- JDK imports ------------------------------------------------------------

import java.io.Serializable;

/**
 *
 * @author Charles Chigoriwa
 */
public abstract class Report implements Serializable {
    public final static String    JRXML         = "jrxml";
    public final static String    JASPER        = "jasper";
    protected final static String PDF_EXTENSION = "pdf";

    protected abstract String getOutputReportName();

    protected abstract String getReportPath();

    public final String getOutputReportName(ContentType contentType) {
        if (contentType.equals(ContentType.PDF)) {
            return concat(getOutputReportName(), PDF_EXTENSION);
        }

        return getOutputReportName();
    }

    public final String getJRXMLReportPath() {
        return concat(getReportPath(), JRXML);
    }

    public final String getJasperReportPath() {
        return concat(getReportPath(), JASPER);
    }

    protected String concat(String prefix, String suffix) {
        return prefix + "." + suffix;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
