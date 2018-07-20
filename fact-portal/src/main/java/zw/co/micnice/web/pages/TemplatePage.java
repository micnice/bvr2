package zw.co.micnice.web.pages;

import com.googlecode.wicket.jquery.core.JQueryBehavior;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.spring.injection.annot.SpringBean;
import zw.co.micnice.logic.domain.Beneficiary;
import zw.co.micnice.logic.domain.Contribution;
import zw.co.micnice.logic.service.BeneficiaryService;
import zw.co.micnice.logic.service.ContactTypeService;
import zw.co.micnice.logic.service.ContributionService;
import zw.co.micnice.web.config.CouncilSession;
import zw.co.micnice.web.pages.admin.AdministerDatabasePage;
import zw.co.micnice.web.pages.admin.ContactTypeListPage;
import zw.co.micnice.web.search.SearchBeneficiaryPage;

/**
 * Template Page for all the system pages.
 *
 */
public abstract class TemplatePage extends WebPage {

    private String userName = CouncilSession.get().getUser().getUsername().trim();
    @SpringBean
    private BeneficiaryService beneficiaryService;
    @SpringBean
    private ContributionService contributionService;
    @SpringBean
    private ContactTypeService contactTypeService;

    public TemplatePage() {
        this.add(new JQueryBehavior("#tabs", "tabs"));
        add(new Label("loggedInUserNameLabel", userName));
        add(new BookmarkablePageLink("addBeneficiaryLink", BeneficiaryEditPage.class));
        add(new BookmarkablePageLink("admin", AdministerDatabasePage.class));
        add(new BookmarkablePageLink("logout", SignOutPage.class));
        add(new BookmarkablePageLink("searchLink", SearchBeneficiaryPage.class));
        add(new BookmarkablePageLink("companiesListLink", BeneficiaryListPage.class));
        add(new BookmarkablePageLink("topBids", ContactTypeListPage.class));
        add(new Link("auct") {

            @Override
            public void onClick() {
                auct();
            }
        });
        add(new Link("send") {

            @Override
            public void onClick() {
                sendEmail();
            }
        });

    }

    public void auct() {

        List<Beneficiary> list = new ArrayList<Beneficiary>();
        list = beneficiaryService.getCompanies();
        System.out.println("==============" + list.size());
        if (!list.isEmpty()) {
            for (Beneficiary beneficiary : list) {
                System.out.println("++++++++++++++" + beneficiary.getFirstName());
                cont(beneficiary);
//                beneficiary.setSold(Boolean.TRUE);
                beneficiaryService.save(beneficiary);
            }
        }
    }

    public void cont(Beneficiary beneficiary) {

        List<Contribution> contributions = new ArrayList<Contribution>();
        contributions = contributionService.getContributions(beneficiary);
        System.out.println("============== contri" + contributions.size());
        for (Contribution contribution : contributions) {
            System.out.println("============== contri" + contribution.getAmount());
            if (contributions.size() > 1) {
                if (contribution.getBalance() > 0) {
                    Integer value = contribution.getAmount();
                    Integer balance = contribution.getBalance();
                    System.out.println("============== value 1 " + value);
                    if (contribution.getBalance() > contribution.getInc()) {
                        value = value + contribution.getInc();
                        balance -= contribution.getInc();
                        System.out.println("============== value 2 " + value);
                    } else if (contribution.getBalance() == contribution.getInc()) {
                        value = value + contribution.getInc();
                        balance = 0;
                        System.out.println("============== value 3 " + value);
                        contribution.setRemove(Boolean.TRUE);
                    } else {
                        value = value + contribution.getBalance();
                        balance = 0;
                        System.out.println("============== value 1 " + value);
                        contribution.setRemove(Boolean.TRUE);
                    }
                    contribution.setAmount(value);
                    contribution.setBalance(balance);
                    contributionService.save(contribution);

                }

            } else {
//                beneficiary.setSold(Boolean.TRUE);
                beneficiaryService.save(beneficiary);
                List<Contribution> contrib = new ArrayList<Contribution>();
                contrib = contributionService.getContributions(beneficiary);
                for (Contribution contribution1 : contrib) {
                    contribution1.setRemove(Boolean.TRUE);
                    contributionService.save(contribution1);
                }

            }

        }

        List<Contribution> contrib = new ArrayList<Contribution>();
        contrib = contributionService.getContributions(beneficiary);
        if (!contrib.isEmpty() && contrib.size() > 1) {
            cont(beneficiary);

        } else {
//            beneficiary.setSold(Boolean.TRUE);
            beneficiaryService.save(beneficiary);
            for (Contribution contribution1 : contrib) {
                contribution1.setRemove(Boolean.TRUE);
                contributionService.save(contribution1);
            }
        }

    }

    public void sendEmail() {
        List<Beneficiary> beneficiarys = beneficiaryService.getClosedItems();
        List<Contribution> topBids = new ArrayList<Contribution>();
        for (Beneficiary beneficiary : beneficiarys) {
            Contribution contribution = contributionService.getTopBid(beneficiary);
            if (contribution != null) {
                topBids.add(contribution);
            }
        }
        if (!topBids.isEmpty()) {

        }
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.stmp.user", "ever.mic25@gmail.com");
        //If you want you use TLS 
        props.put("mail.smtp.auth", "true");

        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.password", "3v3rn1c3m1c");
        // If you want to use SSL
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                String username = "ever.mic25@gmail.com";
                String password = "3v3rn1c3m1c";
                return new PasswordAuthentication(username, password);
            }
        };
        Session session = Session.getDefaultInstance(props, auth);
        try {
            for (Contribution contribution : topBids) {

                MimeMessage msg = new MimeMessage(session);
                msg.setFrom(new InternetAddress("ever.mic25@gmail.com"));
                InternetAddress recipientAddress = new InternetAddress(contribution.getEmail());
                int counter = 0;
                for (Contribution rd : topBids) {

                    String email = rd.getEmail();

                }
                msg.setRecipient(Message.RecipientType.TO, recipientAddress);
                msg.setSubject("Congratulations: Bit Results Anouncement for  " + contribution.getBeneficiary().getFirstName());
                // Create the message part 
                BodyPart messageBodyPart = new MimeBodyPart();

                //paragraph one
                messageBodyPart.setText("We are happy to announce that Bidder No: " + contribution.getNumber() + " you have won the bid for " + contribution.getBeneficiary().getFirstName());

                // Create a multipar message
                Multipart multipart = new MimeMultipart();

                //paragraph two
                multipart.addBodyPart(messageBodyPart);

                //signature
                messageBodyPart = new MimeBodyPart();
                messageBodyPart.setText("Regards");
                messageBodyPart.setText("Auction Manager \n Clever");
                messageBodyPart.setText("AUTO AUCTION");
                multipart.addBodyPart(messageBodyPart);

                // Send the complete message parts
                msg.setContent(multipart);

                Transport transport = session.getTransport("smtp");
                transport.send(msg);
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
