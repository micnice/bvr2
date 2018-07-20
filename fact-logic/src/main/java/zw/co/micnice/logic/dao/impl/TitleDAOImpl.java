package zw.co.micnice.logic.dao.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zw.co.micnice.logic.dao.TitleDAO;
import zw.co.micnice.logic.dao.repo.TitleRepository;
import zw.co.micnice.logic.domain.Title;

/**
 *
 * @author tidza
 */
@Repository
public class TitleDAOImpl implements TitleDAO {

    @Autowired
    private TitleRepository titleRepository;

    public Title save(Title t) {
        return titleRepository.save(t);
    }

    public List<Title> findAll() {
        return titleRepository.findAll();
    }

    public Title get(Long id) {
        return titleRepository.findOne(id);
    }

    public void setTitleRepository(TitleRepository titleRepository) {
        this.titleRepository = titleRepository;
    }
   
}
