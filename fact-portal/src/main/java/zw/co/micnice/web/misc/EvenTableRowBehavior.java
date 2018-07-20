package zw.co.micnice.web.misc;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;

/**
 *
 * @author charlesc
 */
public class EvenTableRowBehavior extends Behavior {

    @Override
    public void onComponentTag(Component component, ComponentTag tag) {

        String c1 = tag.getAttribute("class");
        if (c1 == null) {
            tag.put("class", "even");
        } else {
            tag.put("class", "even " + c1);
        }

    }
}
