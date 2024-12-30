package init;

import java.time.LocalDateTime;

import components.Productor;

public class LanzadorProductor {

	public static void main(String[] args) {
		Productor productor=new Productor();
		for(int i=1;i<=10;i++) {
			productor.send("topicTest", "Mensaje generado a las "+LocalDateTime.now()+" para topicTest");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		productor.cerrar();
	}

}
