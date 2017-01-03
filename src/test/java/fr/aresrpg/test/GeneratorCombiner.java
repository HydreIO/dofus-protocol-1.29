package fr.aresrpg.test;

import java.util.*;
import java.util.stream.Collectors;

import net.java.quickcheck.Generator;
import net.java.quickcheck.generator.iterable.Iterables;

public class GeneratorCombiner {
	private GeneratorCombiner() {
	}

	public static Iterator<Object[]> combine(Generator<?>... generators) {
		List<Iterator<?>> it = Arrays.stream(generators).map(i -> Iterables.toIterable(i)).map(i -> i.iterator()).collect(Collectors.toList());
		return new Iterator<Object[]>() {
			@Override
			public boolean hasNext() {
				return it.stream().map(Iterator::hasNext).reduce(true, Boolean::logicalAnd);
			}

			@Override
			public Object[] next() {
				if (!hasNext())
					throw new ArrayIndexOutOfBoundsException();
				return it.stream().map(Iterator::next).toArray(Object[]::new);
			}
		};
	}
}
