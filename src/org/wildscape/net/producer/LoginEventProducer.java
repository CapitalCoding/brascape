package org.wildscape.net.producer;

import java.nio.ByteBuffer;

import org.wildscape.net.EventProducer;
import org.wildscape.net.IoReadEvent;
import org.wildscape.net.IoSession;
import org.wildscape.net.IoWriteEvent;
import org.wildscape.net.event.LoginReadEvent;
import org.wildscape.net.event.LoginWriteEvent;

/**
 * Produces login I/O events.
 * @author Emperor
 */
public final class LoginEventProducer implements EventProducer {

	@Override
	public IoReadEvent produceReader(IoSession session, ByteBuffer buffer) {
		return new LoginReadEvent(session, buffer);
	}

	@Override
	public IoWriteEvent produceWriter(IoSession session, Object context) {
		return new LoginWriteEvent(session, context);
	}

}