package br.com.casadocodigo.loja.infra;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;

@ApplicationScoped
public class MailSender {

	private Session session;

	public void send(String from, String to, String subject, String body) {
		
		MimeMessage message = new MimeMessage(session);
		
		
	}

}
