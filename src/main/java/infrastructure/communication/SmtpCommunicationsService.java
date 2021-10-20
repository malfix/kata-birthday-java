package infrastructure.communication;

import domain.User;
import infrastructure.CommunicationsService;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

import static java.lang.String.format;

public class SmtpCommunicationsService implements CommunicationsService {
    @Override
    public void sendGreetingsTo(User aUser) {
        String smtpHostServer = "localhost";
        String emailID = aUser.getEmail();

        Properties props = System.getProperties();

        props.put("mail.smtp.host", smtpHostServer);

        Session session = Session.getInstance(props, null);

        sendEmail(session, emailID,format("Happy birthday %s", aUser.getName()));
    }

    private void sendEmail(Session session, String toEmail, String subject){
        try
        {
            MimeMessage msg = new MimeMessage(session);
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress("no_reply@malfix.com", "NoReply-JD"));

            msg.setReplyTo(InternetAddress.parse("no_reply@malfix.com", false));

            msg.setSubject(subject, "UTF-8");

            msg.setText("Today is your birthday!!! ", "UTF-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            System.out.println("Message is ready");
            Transport.send(msg);

            System.out.println("EMail Sent Successfully!!");
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new SmtpCommunicationException(e);
        }
    }
}
