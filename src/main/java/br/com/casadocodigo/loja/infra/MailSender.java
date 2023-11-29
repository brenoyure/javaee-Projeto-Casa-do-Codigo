package br.com.casadocodigo.loja.infra;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.mail.Message.RecipientType;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@ApplicationScoped
public class MailSender {

	@Resource(mappedName = "java:/jboss/mail")
	private Session session;

	public void send(String from, String to, String subject, String body) throws AddressException, MessagingException {

		MimeMessage message = new MimeMessage(session);

		message.setFrom(new InternetAddress(from));
		message.setRecipients(RecipientType.TO, InternetAddress.parse(to));
		message.setSubject(subject);
		message.setContent(body, "text/html");

		Transport.send(message);

	}

}
