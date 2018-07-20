/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.logic.dao.repo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import zw.co.micnice.logic.domain.Title;

/**
 *
 * @author tidza
 */
public interface TitleRepository extends CrudRepository<Title, Long> {

    public List<Title> findAll();
}
