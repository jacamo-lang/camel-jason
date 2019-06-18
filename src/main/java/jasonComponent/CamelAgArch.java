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
            try {
    			if (JasonConsumer.getConsumers().containsKey(m.getReceiver())) {
    				JasonConsumer.getConsumers().get(m.getReceiver()).receiveMessage(m);
                    return;
    			}
            } catch (Exception e2) { }
			throw e;
		}
	}
}
