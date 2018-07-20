package zw.co.micnice.web.utility;

import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.DynamicImageResource;
import org.apache.wicket.request.resource.IResource;
import org.jfree.chart.JFreeChart;

/**
 *
 * @author Charles
 */
public class JFreeChartImage extends Image{
    
    private int width;
    private int height;

    public JFreeChartImage(String id,JFreeChart chart, int width,int height) {
        super(id, new Model<JFreeChart>(chart));
        this.width=width;
        this.height=height;
    }

    @Override
    protected IResource getImageResource() {
        return new DynamicImageResource() {

            @Override
            protected byte[] getImageData(IResource.Attributes attributes) {
               JFreeChart chart=(JFreeChart)getDefaultModelObject();
               return toImageData(chart.createBufferedImage(width, height));
            }
        };
    }

    
    
    
}
