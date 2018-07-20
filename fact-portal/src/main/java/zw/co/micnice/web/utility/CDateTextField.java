package zw.co.micnice.web.utility;

//~--- non-JDK imports --------------------------------------------------------

import org.apache.wicket.Application;
import org.apache.wicket.datetime.DateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.WebSession;

//~--- JDK imports ------------------------------------------------------------

import java.util.Date;
import java.util.Locale;

/**
 *
 * @author Charles Chigoriwa
 */
public class CDateTextField extends DateTextField {
    public CDateTextField(String id) {
        this(id, WebSession.get().getLocale());
    }

    public CDateTextField(String id, DateConverter converter) {
        super(id, converter);
    }

    public CDateTextField(String id, Locale locale) {
        super(id, DateTimeUtils.getConverter(locale));
    }

    public CDateTextField(String id, IModel<Date> model, DateConverter converter) {
        super(id, model, converter);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
