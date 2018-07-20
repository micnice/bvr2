package zw.co.micnice.web.utility;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;

public class EvenTableRowBehavior extends Behavior {

    @Override
    public void onComponentTag(Component component, ComponentTag tag) {

        String c1 = tag.getAttribute("class");
        if (c1 == null) {
            tag.put("class", "evenz");
        } else {
            tag.put("class", "even " + c1);
        }

    }
}
