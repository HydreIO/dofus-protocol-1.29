package fr.aresrpg.dofus.protocol;

import java.util.Arrays;

public class StringDofusStream implements DofusStream{

	private String[][] buffer;
	private int packetIndex = 0;
	private int read = 0;
	private int write = 0;

	public StringDofusStream(String[][] buffer) {
		this.buffer = buffer;
	}

	public StringDofusStream() {
		this.buffer = new String[1][0];
	}

	@Override
	public String read() {
		return read(read++);
	}

	@Override
	public String read(int index) {
		return buffer[packetIndex][index];
	}

	@Override
	public int available() {
		return buffer[packetIndex].length - read;
	}

	@Override
	public DofusStream write(String value) {
		return write(write++ , value);
	}

	@Override
	public DofusStream write(int index, String value) {
		buffer[packetIndex][index] = value;
		return this;
	}

	@Override
	public DofusStream allocate(int size) {
		if(buffer[packetIndex] == null)
			buffer[packetIndex] = new String[size];
		else
			buffer[packetIndex] = Arrays.copyOf(buffer[packetIndex] , size);
		return this;
	}

	@Override
	public DofusStream allocatePacket(int size) {
		buffer = Arrays.copyOf(buffer , size);
		return this;
	}

	@Override
	public DofusStream nextPacket() {
		packetIndex++;
		write = 0;
		read = 0;
		return this;
	}

	@Override
	public void setWriteIndex(int index) {
		write = index;
	}

	@Override
	public void setReadIndex(int index) {
		read = index;
	}

	public String[][] getBuffer() {
		return buffer;
	}
}
