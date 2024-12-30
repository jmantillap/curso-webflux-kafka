package init;

import components.Consumidor;

public class LanzadorConsumidor {

	public static void main(String[] args) {
		Consumidor consumidor=new Consumidor();
		consumidor.suscribir("topicTest");

	}

}
