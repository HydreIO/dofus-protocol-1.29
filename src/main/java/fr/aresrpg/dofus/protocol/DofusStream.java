package fr.aresrpg.dofus.protocol;

import java.util.function.Consumer;

public interface DofusStream {
	String peek();

	String read();

	String read(int index);

	default int peekInt() {
		return Integer.parseInt(peek());
	}

	default int readInt() {
		return Integer.parseInt(read());
	}

	default int readIntRadix(int radix) {
		return Integer.parseInt(read(), radix);
	}

	default long readLong() {
		return Long.parseLong(read());
	}

	default int readInt(int index) {
		return Integer.parseInt(read(index).trim());
	}

	default void forEach(Consumer<String> cons) {
		while (available() > 0)
			cons.accept(read());
	}

	default int readIntRadix(int index, int radix) {
		return Integer.parseInt(read(index), radix);
	}

	int available();

	DofusStream write(String value);

	DofusStream write(int index, String value);

	default DofusStream writeInt(int value) {
		write(Integer.toString(value));
		return this;
	}

	default DofusStream writeIntRadix(int value, int radix) {
		write(Integer.toString(value, radix));
		return this;
	}

	default DofusStream writeInt(int index, int value) {
		write(index, Integer.toString(value));
		return this;
	}

	default DofusStream writeIntRadix(int index, int value, int radix) {
		write(index, Integer.toString(value, radix));
		return this;
	}

	default DofusStream writeLong(long value) {
		write(Long.toString(value));
		return this;
	}

	default DofusStream writeLong(int index, long value) {
		write(index, Long.toString(value));
		return this;
	}

	default char readChar() {
		return read().charAt(0);
	}

	default DofusStream writeChar(char c) {
		return write(String.valueOf(c));
	}

	DofusStream allocate(int size);

	DofusStream allocatePacket(int size);

	DofusStream nextPacket();

	void setWriteIndex(int index);

	void setReadIndex(int index);
}
