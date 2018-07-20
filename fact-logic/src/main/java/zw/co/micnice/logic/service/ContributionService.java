/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.logic.service;

import java.util.List;
import zw.co.micnice.logic.domain.Beneficiary;
import zw.co.micnice.logic.domain.Contribution;

public interface ContributionService extends IGenericService<Contribution> {
    public List<Contribution> getContributions(Beneficiary beneficiary);
    public Contribution getTopBid(Beneficiary beneficiary);
}
