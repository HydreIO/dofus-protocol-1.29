package fr.aresrpg.dofus.protocol;

public interface DofusStream {
	String read();

	String read(int index);

	default int readInt() {
		return Integer.parseInt(read());
	}

	default int readInt(int index) {
		return Integer.parseInt(read(index));
	}

	int available();

	DofusStream write(String value);

	DofusStream write(int index, String value);

	default DofusStream writeInt(int value) {
		write(Integer.toString(value));
		return this;
	}

	default DofusStream writeInt(int index, int value) {
		write(index, Integer.toString(value));
		return this;
	}

	DofusStream allocate(int size);

	DofusStream allocatePacket(int size);

	DofusStream nextPacket();
}
