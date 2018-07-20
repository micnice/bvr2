package zw.co.micnice.web.utility;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.request.Response;


public class ErrorBehavior extends Behavior {

    @Override
    public void beforeRender(Component component) {
        FormComponent<?> fc = (FormComponent<?>) component;
        Response r = component.getResponse();
        
        StringBuilder sb=new StringBuilder();
        
         for (FeedbackMessage message : fc.getFeedbackMessages()) { 
               sb.append(message.getMessage()).append(" ");
         }

        if (fc.isValid() == false) {
            r.write("<span class=\"error\">"+sb.toString()+"</span>");
        }

        super.beforeRender(component);
    }

    @Override
    public void onComponentTag(Component component, ComponentTag tag) {
        if (((FormComponent<?>) component).isValid() == false) {
            String c1 = tag.getAttribute("class");
            if (c1 == null) {
                tag.put("class", "error");
            } else {
                tag.put("class", "error " + c1);
            }
        }
    }
}
