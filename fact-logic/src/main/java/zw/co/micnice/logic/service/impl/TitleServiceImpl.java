/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.logic.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.micnice.logic.dao.TitleDAO;
import zw.co.micnice.logic.domain.Title;
import zw.co.micnice.logic.service.TitleService;
/**
 *
 * @author tidza
 */
@Service
@Transactional(readOnly = true)
public class TitleServiceImpl implements TitleService {

    @Autowired
    private TitleDAO titleDao;

    public Title save(Title t) {
        return titleDao.save(t);
    }

    public List<Title> findAll() {
        return titleDao.findAll();
    }

    public Title get(Long id) {
        return titleDao.get(id);
    }

    public void setTitleDAO(TitleDAO titleDAO) {
        this.titleDao = titleDAO;
    }
}
