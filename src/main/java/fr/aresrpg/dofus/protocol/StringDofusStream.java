package fr.aresrpg.dofus.protocol;

import java.util.Arrays;

public class StringDofusStream implements DofusStream{

	private String[] in;
	private String[] out;
	private int read = 0;
	private int write = 0;

	public StringDofusStream(String[] in) {
		this.in = in;
	}

	public StringDofusStream() {}

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
		return in.length;
	}

	@Override
	public DofusStream write(String value) {
		return write(write++ , value);
	}

	@Override
	public DofusStream write(int index, String value) {
		out[index] = value;
		return this;
	}

	@Override
	public DofusStream allocate(int size) {
		if(out == null)
			out = new String[size];
		else
			out = Arrays.copyOf(out , size);
		return this;
	}

	public String[] getOut() {
		return out;
	}
}
