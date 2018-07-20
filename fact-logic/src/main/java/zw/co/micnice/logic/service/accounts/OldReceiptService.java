package zw.co.micnice.logic.service.accounts;

import java.util.Date;
import java.util.List;
import zw.co.micnice.logic.domain.Registrant;
import zw.co.micnice.logic.domain.User;
import zw.co.micnice.logic.domain.accounts.OldReceipt;
import zw.co.micnice.logic.domain.accounts.PaymentMethod;
import zw.co.micnice.logic.domain.accounts.PaymentType;
import zw.co.micnice.logic.domain.accounts.Product;
import zw.co.micnice.logic.service.IGenericService;

/**
 *
 * @author Michael Matiashe
 *
 */
public interface OldReceiptService extends IGenericService<OldReceipt> {

    public List<OldReceipt> getOldRenewalReceipts();

    public List<OldReceipt> getReceipts(Long receiptNumber, Product product, PaymentMethod paymentMethod, PaymentType paymentType, Date startDate, Date endDate, User user, Boolean cancel, Boolean bank);

    public List<OldReceipt> getReceipts(Product product, Date startDate, Date endDate, Boolean canceled);

    public List<OldReceipt> getReceipts(Product product, Registrant registrant);

    public Double getReceiptTotal(Long receiptNumber, Product product, PaymentMethod paymentMethod, PaymentType paymentType, Date startDate, Date endDate, User user, Boolean cancel, Boolean bank);

    public Double getReceiptTotal(Product product, Date startDate, Date endDate, Boolean cancel);
}
