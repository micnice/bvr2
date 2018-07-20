package zw.co.micnice.reports.utils;

//~--- non-JDK imports --------------------------------------------------------
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import zw.co.micnice.reports.Report;

//~--- JDK imports ------------------------------------------------------------

import java.io.ByteArrayOutputStream;
import java.sql.Connection;

import java.util.Collection;
import java.util.Map;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

/**
 *
 * @author Charles Chigoriwa Consider "Spring" this class in future
 */
public class ReportManager implements IReportManager {

    // Make it more intelligent to generate other file formats
    public byte[] getPdfReportBytes(Map<String, Object> parameters, String reportPath, Collection<?> beanCollection)
            throws JRException {
        JasperPrint jasperPrint;

        if (reportPath.endsWith(Report.JASPER)) {
            jasperPrint = getJasperPrint(parameters, reportPath, beanCollection);
        } else {
            jasperPrint = getJasperPrintAfterCompile(parameters, reportPath, beanCollection);
        }

        JRPdfExporter exporter = new JRPdfExporter();
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, os);
        exporter.exportReport();

        return os.toByteArray();
    }

    // Make it more intelligent to generate other file formats
    public byte[] getReportXls(Map<String, Object> parameters, String reportPath, Collection<?> beanCollection)
            throws JRException {
        JasperPrint jasperPrint;

        if (reportPath.endsWith(Report.JASPER)) {
            jasperPrint = getJasperPrint(parameters, reportPath, beanCollection);
        } else {
            jasperPrint = getJasperPrintAfterCompile(parameters, reportPath, beanCollection);
        }

        JRXlsxExporter docxExporter = new JRXlsxExporter();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, os);
        docxExporter.exportReport();
        return os.toByteArray();
    }

    public JasperPrint getJasperPrint(Map<String, Object> parameters, String reportPath, Collection<?> beanCollection)
            throws JRException {
        JasperPrint jasperPrint;
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(beanCollection);

        jasperPrint = JasperFillManager.fillReport(CouncilReportApplication.class.getResourceAsStream(reportPath),
                parameters, beanCollectionDataSource);

        return jasperPrint;
    }

    public JasperPrint getJasperPrint(Map<String, Object> parameters, String reportPath, Connection connection)
            throws JRException {
        JasperPrint jasperPrint;

        jasperPrint = JasperFillManager.fillReport(CouncilReportApplication.class.getResourceAsStream(reportPath),
                parameters, connection);

        return jasperPrint;
    }

    public JasperPrint getJasperPrintAfterCompile(Map<String, Object> parameters, String reportPath,
            Collection<?> beanCollection)
            throws JRException {
        JasperDesign design;
        JasperReport report;
        JasperPrint jasperPrint;
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(beanCollection);

        design = JRXmlLoader.load(CouncilReportApplication.class.getResourceAsStream(reportPath));
        report = JasperCompileManager.compileReport(design);
        jasperPrint = JasperFillManager.fillReport(report, parameters, beanCollectionDataSource);

        return jasperPrint;
    }

    public JasperPrint getJasperPrintAfterCompile(Map<String, Object> parameters, String reportPath,
            Connection connection)
            throws JRException {
        JasperDesign design;
        JasperReport report;
        JasperPrint jasperPrint;

        design = JRXmlLoader.load(CouncilReportApplication.class.getResourceAsStream(reportPath));
        report = JasperCompileManager.compileReport(design);
        jasperPrint = JasperFillManager.fillReport(report, parameters, connection);

        return jasperPrint;
    }

    public byte[] getPdfReportBytes(Map<String, Object> parameters, String reportPath, Connection connection) throws JRException {
        JasperPrint jasperPrint;

        if (reportPath.endsWith(Report.JASPER)) {
            jasperPrint = getJasperPrint(parameters, reportPath, connection);
        } else {
            jasperPrint = getJasperPrintAfterCompile(parameters, reportPath, connection);
        }

        JRPdfExporter exporter = new JRPdfExporter();
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, os);
        exporter.exportReport();

        return os.toByteArray();
    }

    public byte[] getReportXls(Map<String, Object> parameters, String reportPath, Connection connection) throws JRException {
        JasperPrint jasperPrint;

        if (reportPath.endsWith(Report.JASPER)) {
            jasperPrint = getJasperPrint(parameters, reportPath, connection);
        } else {
            jasperPrint = getJasperPrintAfterCompile(parameters, reportPath, connection);
        }
        JRXlsxExporter docxExporter = new JRXlsxExporter();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, os);
        docxExporter.exportReport();
        return os.toByteArray();
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
