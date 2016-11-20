package fr.aresrpg.dofus.protocol;

public interface DofusStream {
	String read();

	String read(int index);

	DofusStream write(String value);

	DofusStream write(int index, String value);

	DofusStream allocate(int size);
}
