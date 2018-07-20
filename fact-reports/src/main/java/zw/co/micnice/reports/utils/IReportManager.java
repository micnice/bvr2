package zw.co.micnice.reports.utils;

//~--- non-JDK imports --------------------------------------------------------
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

//~--- JDK imports ------------------------------------------------------------

import java.io.Serializable;
import java.sql.Connection;

import java.util.Collection;
import java.util.Map;

/**
 *
 * @author Charles Chigoriwa
 */
public interface IReportManager extends Serializable {

    public byte[] getPdfReportBytes(Map<String, Object> parameters, String reportPath, Collection<?> beanCollection)
            throws JRException;

    public byte[] getReportXls(Map<String, Object> parameters, String reportPath, Collection<?> beanCollection)
            throws JRException;

    public byte[] getPdfReportBytes(Map<String, Object> parameters, String reportPath, Connection connection)
            throws JRException;

    public byte[] getReportXls(Map<String, Object> parameters, String reportPath, Connection connection)
            throws JRException;

    
    public JasperPrint getJasperPrint(Map<String, Object> parameters, String reportPath, Collection<?> beanCollection)
            throws JRException;

    public JasperPrint getJasperPrintAfterCompile(Map<String, Object> parameters, String reportPath,
            Collection<?> beanCollection)
            throws JRException;
}


//~ Formatted by Jindent --- http://www.jindent.com
