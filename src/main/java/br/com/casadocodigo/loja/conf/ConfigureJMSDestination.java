package br.com.casadocodigo.loja.conf;

import jakarta.jms.JMSDestinationDefinition;

@JMSDestinationDefinition(
	name = "java:/jms/CarrinhoComprasTopico",
	interfaceName = "jakarta.jms.Topic",
	destinationName = ""
)
public class ConfigureJMSDestination {

}
