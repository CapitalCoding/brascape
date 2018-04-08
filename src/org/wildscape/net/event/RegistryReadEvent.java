package org.wildscape.net.event;

import java.nio.ByteBuffer;

import org.wildscape.game.system.SystemLogger;
import org.wildscape.game.world.GameWorld;
import org.wildscape.net.IoReadEvent;
import org.wildscape.net.IoSession;
import org.wildscape.net.amsc.ManagementServerState;
import org.wildscape.net.amsc.WorldCommunicator;
import org.wildscape.net.producer.MSEventProducer;

/**
 * Handles world registry read events.
 * @author Emperor
 */
public final class RegistryReadEvent extends IoReadEvent {

	/**
	 * The event producer.
	 */
	private static final MSEventProducer PRODUCER = new MSEventProducer();

	/**
	 * Constructs a new {@code RegistryReadEvent} {@code Object}.
	 * @param session The session.
	 * @param buffer The buffer to read.
	 */
	public RegistryReadEvent(IoSession session, ByteBuffer buffer) {
		super(session, buffer);
	}

	@Override
	public void read(IoSession session, ByteBuffer buffer) {
		int opcode = buffer.get() & 0xFF;
		switch (opcode) {
		case 0:
			WorldCommunicator.setState(ManagementServerState.NOT_AVAILABLE);
			SystemLogger.log("Failed registering world to AMS - [id=" + GameWorld.getSettings().getWorldId() + ", cause=World id out of bounds]!");
			break;
		case 1:
			session.setProducer(PRODUCER);
			WorldCommunicator.setState(ManagementServerState.AVAILABLE);
			SystemLogger.log("Successfully registered world to AMS - [id=" + GameWorld.getSettings().getWorldId() + "]!");
			break;
		case 2:
			WorldCommunicator.setState(ManagementServerState.NOT_AVAILABLE);
			SystemLogger.log("Failed registering world to AMS - [id=" + GameWorld.getSettings().getWorldId() + ", cause=World id already in use]!");
			break;
		case 3:
			WorldCommunicator.setState(ManagementServerState.NOT_AVAILABLE);
			SystemLogger.log("Failed registering world to AMS - [id=" + GameWorld.getSettings().getWorldId() + ", cause=Exception in AMS]!");
			break;
		default:
			System.out.println("??" + opcode);
			break;
		}
	}

}