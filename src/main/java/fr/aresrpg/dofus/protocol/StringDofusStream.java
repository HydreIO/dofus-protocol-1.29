package fr.aresrpg.dofus.protocol;

import java.util.Arrays;

public class StringDofusStream implements DofusStream{

	private String[] in;
	private String[][] out;
	private int packetIndex = 0;
	private int read = 0;
	private int write = 0;

	public StringDofusStream(String[] in) {
		this.in = in;
	}

	public StringDofusStream() {
		this.out = new String[1][0];
	}

	@Override
	public String read() {
		return read(read++);
	}

	@Override
	public String read(int index) {
		return in[index];
	}

	@Override
	public int available() {
		return in.length - read;
	}

	@Override
	public DofusStream write(String value) {
		return write(write++ , value);
	}

	@Override
	public DofusStream write(int index, String value) {
		out[packetIndex][index] = value;
		return this;
	}

	@Override
	public DofusStream allocate(int size) {
		if(out[packetIndex] == null)
			out[packetIndex] = new String[size];
		else
			out[packetIndex] = Arrays.copyOf(out[packetIndex] , size);
		return this;
	}

	@Override
	public DofusStream allocatePacket(int size) {
		out = Arrays.copyOf(out , size);
		return this;
	}

	@Override
	public DofusStream nextPacket() {
		packetIndex++;
		write = 0;
		read = 0;
		return this;
	}

	public String[][] getOut() {
		return out;
	}
}
