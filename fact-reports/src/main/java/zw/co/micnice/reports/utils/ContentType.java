package zw.co.micnice.reports.utils;

/**
 *
 * @author Charles Chigoriwa
 */
public enum ContentType {
    PDF("application/pdf"), EXCEL("application/xls"), CSV("application/csv"), DOC("application/doc");

    final private String contentTypeString;

    private ContentType(String contentTypeString) {
        this.contentTypeString = contentTypeString;
    }

    public String getContentTypeString() {
        return contentTypeString;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
