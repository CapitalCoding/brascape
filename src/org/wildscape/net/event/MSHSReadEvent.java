package org.wildscape.net.event;

import java.nio.ByteBuffer;

import org.wildscape.net.EventProducer;
import org.wildscape.net.IoReadEvent;
import org.wildscape.net.IoSession;
import org.wildscape.net.producer.RegistryEventProducer;

/**
 * Handles the management server handshake read events.
 * @author Emperor
 */
public final class MSHSReadEvent extends IoReadEvent {

	/**
	 * The event producer.
	 */
	private static final EventProducer REGISTRY_PRODUCER = new RegistryEventProducer();

	/**
	 * Constructs a new {@code MSHSReadEvent} {@Code Object}
	 * @param session The session.
	 * @param buffer The buffer to read.
	 */
	public MSHSReadEvent(IoSession session, ByteBuffer buffer) {
		super(session, buffer);
	}

	@Override
	public void read(IoSession session, ByteBuffer buffer) {
		int opcode = buffer.get() & 0xFF;
		if (opcode == 14) {
			session.setProducer(REGISTRY_PRODUCER);
			session.write(true);
		}
	}

}