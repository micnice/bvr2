package zw.co.micnice.reports.utils;

//~--- non-JDK imports --------------------------------------------------------

import zw.co.micnice.reports.Report;

//~--- JDK imports ------------------------------------------------------------

import java.io.Serializable;

import java.util.Collection;
import java.util.Map;

/**
 *
 * @author Charles Chigoriwa
 */
public class ReportExecutionConfig implements Serializable {
    private Report              report;
    private ContentType         contentType;
    private Collection<?>       collection;
    private Map<String, Object> parameters;

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    public Collection<?> getCollection() {
        return collection;
    }

    public void setCollection(Collection<?> collection) {
        this.collection = collection;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
