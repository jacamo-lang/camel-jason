package jasonComponent;

import jason.ReceiverNotFoundException;
import jason.architecture.AgArch;
import jason.asSemantics.Message;

public class CamelAgArch extends AgArch {
	@Override
	public void sendMsg(Message m) throws Exception {
		try {
			super.sendMsg(m);
			return;
		} catch (ReceiverNotFoundException e) {
			JasonConsumer adr = null;

			if (JasonConsumer.getConsumers().containsKey(m.getReceiver())) {
				adr = JasonConsumer.getConsumers().get(m.getReceiver());
			}
			
//			else {
//				// try ZK
//				byte[] badr = zkClient.getData().forPath(JCMRest.JaCaMoZKAgNodeId+"/"+m.getReceiver());
//				if (badr != null)
//					adr = new String(badr);
//			}
			
			// try by camel to send the message
			if (adr != null) {
				adr.receiveMessage(m);
			} else {
				throw e;
			}
		}
		
	}
}
