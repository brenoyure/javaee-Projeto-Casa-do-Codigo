package br.com.casadocodigo.loja.services;

import br.com.casadocodigo.loja.daos.CompraDao;
import br.com.casadocodigo.loja.infra.MailSender;
import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.inject.Inject;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType",   propertyValue = "jakarta.jms.Topic"),
		@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/topics/CarrinhoComprasTopico") })
public class EnviaEmailCompra implements MessageListener {

	@Inject
	private MailSender mailSender;

	@Inject
	private CompraDao compraDao;

	@Override
	public void onMessage(Message message) {
		try {
			String uuid = message.getBody(String.class);
			mailSender.send("compras@casacodigo.com.br", compraDao.buscaPorUuid(uuid).getUsuario().getEmail(), "Nova Compra na CDC", "Sua compra foi realizada com sucesso, com o número de pedido " + uuid);

		} catch (JMSException e) { throw new RuntimeException(e); }

	}

}
