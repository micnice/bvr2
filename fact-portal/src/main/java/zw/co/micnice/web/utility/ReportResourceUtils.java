package zw.co.micnice.web.utility;

//~--- non-JDK imports --------------------------------------------------------

import net.sf.jasperreports.engine.JRException;

import org.apache.wicket.request.resource.ByteArrayResource;


//~--- JDK imports ------------------------------------------------------------

import java.io.Serializable;
import java.sql.Connection;

import java.util.Collection;
import java.util.Map;
import zw.co.micnice.reports.Report;
import zw.co.micnice.reports.utils.ContentType;
import zw.co.micnice.reports.utils.IReportManager;
import zw.co.micnice.reports.utils.ReportManager;

/**
 *
 * @author Charles Chigoriwa
 */
public class ReportResourceUtils implements Serializable {
    public static ByteArrayResource getReportResource(Report report, ContentType contentType, Collection<?> collection,
            Map<String, Object> parameters)
            throws JRException {
        IReportManager jasperReportManager = new ReportManager();

        return new ByteArrayResource(contentType.getContentTypeString(),
                                     jasperReportManager.getPdfReportBytes(parameters, report.getJRXMLReportPath(),
                                         collection), report.getOutputReportName(contentType));

        // return new ByteArrayResource(contentType.getContentTypeString(), jasperReportManager.getPdfReportBytes(parameters, /*report.getJRXMLReportPath()*/report.getJasperReportPath(), collection), report.getOutputReportName(contentType));
    }
    
     public static ByteArrayResource getReportXlsResource(Report report, ContentType contentType, Collection<?> collection,
            Map<String, Object> parameters)
            throws JRException {
        IReportManager jasperReportManager = new ReportManager();

        return new ByteArrayResource(contentType.getContentTypeString(),
                                     jasperReportManager.getReportXls(parameters, report.getJRXMLReportPath(),
                                         collection), report.getOutputReportName(contentType));

        // return new ByteArrayResource(contentType.getContentTypeString(), jasperReportManager.getPdfReportBytes(parameters, /*report.getJRXMLReportPath()*/report.getJasperReportPath(), collection), report.getOutputReportName(contentType));
    }
     
     public static ByteArrayResource getReportResource(Report report, ContentType contentType,  Connection connection,
            Map<String, Object> parameters)
            throws JRException {
        IReportManager jasperReportManager = new ReportManager();

        return new ByteArrayResource(contentType.getContentTypeString(),
                                     jasperReportManager.getPdfReportBytes(parameters, report.getJRXMLReportPath(),
                                         connection), report.getOutputReportName(contentType));

        // return new ByteArrayResource(contentType.getContentTypeString(), jasperReportManager.getPdfReportBytes(parameters, /*report.getJRXMLReportPath()*/report.getJasperReportPath(), collection), report.getOutputReportName(contentType));
    }
    
     public static ByteArrayResource getReportXlsResource(Report report, ContentType contentType, Connection connection,
            Map<String, Object> parameters)
            throws JRException {
        IReportManager jasperReportManager = new ReportManager();

        return new ByteArrayResource(contentType.getContentTypeString(),
                                     jasperReportManager.getReportXls(parameters, report.getJRXMLReportPath(),
                                         connection), report.getOutputReportName(contentType));

        // return new ByteArrayResource(contentType.getContentTypeString(), jasperReportManager.getPdfReportBytes(parameters, /*report.getJRXMLReportPath()*/report.getJasperReportPath(), collection), report.getOutputReportName(contentType));
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
