package zw.co.micnice.web.misc;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;

/**
 *
 * @author Charles Chigoriwa
 */
public class CurrentMenuBehavior extends Behavior {
    private final boolean currentMenu;

    public CurrentMenuBehavior(boolean currentMenu) {
        this.currentMenu = currentMenu;
    }
    
    
    
    @Override
    public void onComponentTag(Component component, ComponentTag tag) {
        if (currentMenu) {
            String c1 = tag.getAttribute("class");
            if (c1 == null) {
                tag.put("class", "active");
            } else {
                tag.put("class", "active " + c1);
            }
        }
    }
}
