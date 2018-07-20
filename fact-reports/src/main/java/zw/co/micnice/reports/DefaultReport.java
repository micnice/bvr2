package zw.co.micnice.reports;

/**
 *
 * @author Charles Chigoriwa
 */
public abstract class DefaultReport extends Report {
    @Override
    protected String getOutputReportName() {
        return this.getClass().getSimpleName();
    }

    @Override
    protected String getReportPath() {
        return "/" + this.getClass().getName().replace(".", "/");
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
