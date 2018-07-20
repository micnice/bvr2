package zw.co.micnice.web.pages.accounts;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Check;
import org.apache.wicket.markup.html.form.CheckGroup;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import zw.co.micnice.logic.domain.Beneficiary;
import zw.co.micnice.logic.domain.accounts.Account;
import zw.co.micnice.logic.domain.accounts.DebtComponent;
import zw.co.micnice.logic.service.BeneficiaryService;
import zw.co.micnice.logic.service.accounts.AccountService;
import zw.co.micnice.logic.service.accounts.DebtComponentService;
import zw.co.micnice.web.pages.BeneficiaryViewPage;
import zw.co.micnice.web.pages.TemplatePage;
import zw.co.micnice.web.util.DetachableBeneficiaryModel;
import zw.co.micnice.web.utility.EvenTableRowBehavior;
import zw.co.micnice.web.utility.GeneralUtils;

/**
 *
 * @author Michael Matiashe
 */
public class DebtComponentsPage extends TemplatePage {

    @SpringBean
    private BeneficiaryService beneficiaryService;
    @SpringBean
    AccountService accountService;
    @SpringBean
    private BeneficiaryService beneficiaryAccountService;
    @SpringBean
    DebtComponentService debtComponentService;
    private List<DebtComponent> selectedDebtComponents = new ArrayList<DebtComponent>();
    private List<DebtComponent> fetchedDebtComponents = new ArrayList<DebtComponent>();

    public DebtComponentsPage(PageParameters parameters) {
        this(parameters.get("beneficiaryId").toLong());
    }

    public DebtComponentsPage(Long id) {

        final CompoundPropertyModel<Beneficiary> beneficiaryModel = new CompoundPropertyModel<Beneficiary>(new DetachableBeneficiaryModel(id, beneficiaryAccountService));
        setDefaultModel(beneficiaryModel);
        add(new Label("beneficiaryName"));

        IModel<List<DebtComponent>> debtComponentsModel = new LoadableDetachableModel<List<DebtComponent>>() {
            @Override
            protected List<DebtComponent> load() {
                fetchedDebtComponents = debtComponentService.getDebtComponents(beneficiaryModel.getObject());
                return fetchedDebtComponents;
            }
        };

        Form<Void> form = new Form<Void>("form") {
            @Override
            protected void onSubmit() {
                if (!selectedDebtComponents.isEmpty()) {
                    setResponsePage(new PaymentPage(beneficiaryModel.getObject(), selectedDebtComponents));
                }
            }
        };

        CheckGroup<DebtComponent> selectedDebtComponentsGroup = new CheckGroup<DebtComponent>("selectedDebtComponents", this.selectedDebtComponents);
        form.add(selectedDebtComponentsGroup);
        form.add(new Link<Void>("beneficiaryViewPage") {
            @Override
            public void onClick() {
                setResponsePage(new BeneficiaryViewPage(beneficiaryModel.getObject().getId()));
            }
        });
        selectedDebtComponentsGroup.add(new Label("total", GeneralUtils.formatDecimalAsCurrency(new BigDecimal(getTotalDues(debtComponentService.getDebtComponents(beneficiaryModel.getObject()))))));


        add(form);
        add(new Link<Void>("all") {
            @Override
            public void onClick() {
                setResponsePage(new DebtComponentsPage(beneficiaryModel.getObject().getId()));
            }
        });
        add(new Link<Void>("current") {
            @Override
            public void onClick() {
                setResponsePage(new DebtComponentsPage(beneficiaryModel.getObject().getId(), 0));
            }
        });
        add(new Link<Void>("oneMonth") {
            @Override
            public void onClick() {
                setResponsePage(new DebtComponentsPage(beneficiaryModel.getObject().getId(), 30));
            }
        });
        add(new Link<Void>("twoMonths") {
            @Override
            public void onClick() {
                setResponsePage(new DebtComponentsPage(beneficiaryModel.getObject().getId(), 60));
            }
        });
        add(new Link<Void>("threeOrMore") {
            @Override
            public void onClick() {
                setResponsePage(new DebtComponentsPage(beneficiaryModel.getObject().getId(), 90));
            }
        });
        PropertyListView<DebtComponent> eachItem = new PropertyListView<DebtComponent>("eachItem", debtComponentsModel) {
            @Override
            protected void populateItem(final ListItem<DebtComponent> item) {

                if (item.getIndex() % 2 == 0) {
                    item.add(new EvenTableRowBehavior());
                }

                item.add(new Check("selected", item.getModel()));
                item.add(new Label("transactionComponent.account.name"));
                item.add(new Label("remainingBalance"));
            }
        };

        selectedDebtComponentsGroup.add(eachItem);


    }

    public DebtComponentsPage(Long id,final int days) {

        final CompoundPropertyModel<Beneficiary> beneficiaryModel = new CompoundPropertyModel<Beneficiary>(new DetachableBeneficiaryModel(id, beneficiaryAccountService));
        setDefaultModel(beneficiaryModel);
        add(new Label("beneficiaryName"));

        IModel<List<DebtComponent>> debtComponentsModel = new LoadableDetachableModel<List<DebtComponent>>() {
            @Override
            protected List<DebtComponent> load() {
//                if (days == 0){
//
//
//                return debtComponentService.getCurrentDebtComponents(beneficiaryModel.getObject());
//
//                }else if(days == 30){
//
//                return debtComponentService.getOneMonthDebtComponents(new Account());
//
//                }else if(days == 60){
////
//                return debtComponentService.getTwoMonthDebtComponents(beneficiaryModel.getObject().getAccount());
//
//                }else if(days == 90){
//
//                return debtComponentService.getThreeMonthsOrMoreDebtComponents(beneficiaryModel.getObject().getAccount());

//                }else {

                return debtComponentService.getDebtComponents(beneficiaryModel.getObject());

                }

        };

        Form<Void> form = new Form<Void>("form") {
            @Override
            protected void onSubmit() {
                if (!selectedDebtComponents.isEmpty()) {
                    setResponsePage(new PaymentPage(beneficiaryModel.getObject(), selectedDebtComponents));
                }
            }
        };
        CheckGroup<DebtComponent> selectedDebtComponentsGroup = new CheckGroup<DebtComponent>("selectedDebtComponents", this.selectedDebtComponents);
        selectedDebtComponentsGroup.add(new Label("total", GeneralUtils.formatDecimalAsCurrency(new BigDecimal(getTotalDues(debtComponentsModel.getObject())))));
        form.add(selectedDebtComponentsGroup);
        form.add(new Link<Void>("beneficiaryViewPage") {
            @Override
            public void onClick() {
                setResponsePage(new BeneficiaryViewPage(beneficiaryModel.getObject().getId()));
            }
        });


        add(form);
        add(new Link<Void>("all") {
            @Override
            public void onClick() {
                setResponsePage(new DebtComponentsPage(beneficiaryModel.getObject().getId()));
            }
        });
        add(new Link<Void>("current") {
            @Override
            public void onClick() {
                setResponsePage(new DebtComponentsPage(beneficiaryModel.getObject().getId(), 0));
            }
        });
        add(new Link<Void>("oneMonth") {
            @Override
            public void onClick() {
                setResponsePage(new DebtComponentsPage(beneficiaryModel.getObject().getId(), 30));
            }
        });
        add(new Link<Void>("twoMonths") {
            @Override
            public void onClick() {
                setResponsePage(new DebtComponentsPage(beneficiaryModel.getObject().getId(), 60));
            }
        });
        add(new Link<Void>("threeOrMore") {
            @Override
            public void onClick() {
                setResponsePage(new DebtComponentsPage(beneficiaryModel.getObject().getId(), 90));
            }
        });
        PropertyListView<DebtComponent> eachItem = new PropertyListView<DebtComponent>("eachItem", debtComponentsModel) {
            @Override
            protected void populateItem(final ListItem<DebtComponent> item) {

                if (item.getIndex() % 2 == 0) {
                    item.add(new EvenTableRowBehavior());
                }

                item.add(new Check("selected", item.getModel()));
                item.add(new Label("transactionComponent.account.name"));
                item.add(new Label("remainingBalance"));
            }
        };

        selectedDebtComponentsGroup.add(eachItem);


    }

    private double getTotalDues(List<DebtComponent> debtComponents){
     double total =0;
     System.out.println("==============" +debtComponents.size() +"==========");
        for (DebtComponent debtComponent : debtComponents) {
            DebtComponent debtComponent1 = debtComponentService.get(debtComponent.getId());
            System.out.println("==============" +debtComponent1.getRemainingBalance() +"==========");
            total += debtComponent1.getRemainingBalance().doubleValue();
        }
        return total;
    }
}
