package zw.co.micnice.web.util;

//~--- non-JDK imports --------------------------------------------------------
import org.apache.wicket.model.LoadableDetachableModel;
import zw.co.micnice.logic.domain.Beneficiary;
import zw.co.micnice.logic.service.BeneficiaryService;



/**
 *
 * @author Michael Matiashe
 */
public class DetachableBeneficiaryModel extends LoadableDetachableModel<Beneficiary> {

    private static final long serialVersionUID = 1L;
    /**
     * database identity of the beneficiary
     */
    private final long id;
    /**
     * service dao reference - must be a wicket-wrapped proxy, holding onto a
     * reference to the real service dao will cause its serialization into
     * session or a not-serializable exception when the servlet container
     * serializes the session.
     */
    private final BeneficiaryService service;

    /**
     * Constructor
     *
     * @param id
     * @param service
     */
    public DetachableBeneficiaryModel(Long id, BeneficiaryService service) {
        this.id = id;
        this.service = service;
    }

    /**
     * Constructor
     *
     * @param beneficiary
     * @param service
     */
    public DetachableBeneficiaryModel(Beneficiary beneficiary, BeneficiaryService service) {
        id = beneficiary.getId();
        this.service = service;
    }

    /**
     * Loads the beneficiary from the database
     *
     * @see LoadableDetachableModel#load()
     */
    @Override
    protected Beneficiary load() {
        Beneficiary r = service.get(id);

        if (r == null) {
            return new Beneficiary();
        } else {
            return r;
        }
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
