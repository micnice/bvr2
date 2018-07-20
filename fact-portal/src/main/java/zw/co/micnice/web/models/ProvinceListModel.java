/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.web.models;

import java.util.List;
import org.apache.wicket.model.LoadableDetachableModel;
import zw.co.micnice.logic.domain.Province;
import zw.co.micnice.logic.service.ProvinceService;

/**
 *
 * @author Michael Matiashe
 */
public class ProvinceListModel extends LoadableDetachableModel<List<Province>> {
    private final ProvinceService provinceService;

    public ProvinceListModel(ProvinceService provinceService) {
        this.provinceService = provinceService;
    }

    @Override
    protected List<Province> load() {
       return provinceService.findAll();
    }
    
}
