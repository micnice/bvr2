
/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
 */
package zw.co.micnice.web.utility;

//~--- non-JDK imports --------------------------------------------------------

import org.apache.wicket.datetime.StyleDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DateField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.extensions.yui.calendar.DateTimeField;
import org.apache.wicket.model.PropertyModel;

//~--- JDK imports ------------------------------------------------------------

import java.util.Date;

/**
 *
 * @author tdhlakama
 */
public class DatePickerUtil {
    public static DatePicker getDatePicker() {
        DatePicker datePicker = new DatePicker() {
            @Override
            protected String getAdditionalJavaScript() {
                return "${calendar}.cfg.setProperty(\"navigator\",true,false); ${calendar}.render();";
            }
        };

        datePicker.setShowOnFieldClick(true);
        datePicker.setAutoHide(true);

        return datePicker;
    }

    public static DateTimeField getDateTimeField() {
        DateTimeField dateTimeField = new DateTimeField("examDate") {
            @Override
            protected DatePicker newDatePicker() {
                return getDatePicker();
            }
        };

        return dateTimeField;
    }

    public static DateTimeField getDateTimeField(String p) {
        DateTimeField dateTimeField = new DateTimeField(p) {
            @Override
            protected DatePicker newDatePicker() {
                return getDatePicker();
            }
        };

        return dateTimeField;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
