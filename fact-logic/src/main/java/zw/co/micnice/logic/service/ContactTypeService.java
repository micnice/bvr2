package zw.co.micnice.logic.service;

import java.util.List;
import zw.co.micnice.logic.domain.Beneficiary;
import zw.co.micnice.logic.domain.ContactType;

public interface ContactTypeService extends IGenericService<ContactType> {
    public List<ContactType> getTopBid(Beneficiary beneficiary);
}
