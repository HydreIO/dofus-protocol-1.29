package fr.aresrpg.dofus.protocol;

public interface DofusStream {
	String read();

	String read(int index);

	default int readInt(){
		return Integer.parseInt(read());
	}

	default int readInt(int index){
		return Integer.parseInt(read(index));
	}

	DofusStream write(String value);

	DofusStream write(int index, String value);

	DofusStream allocate(int size);
}
