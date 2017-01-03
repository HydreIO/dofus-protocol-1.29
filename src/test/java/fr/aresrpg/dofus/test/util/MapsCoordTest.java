package fr.aresrpg.dofus.test.util;

import static net.java.quickcheck.generator.PrimitiveGenerators.integers;

import fr.aresrpg.dofus.util.Maps;
import fr.aresrpg.test.GeneratorCombiner;

import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MapsCoordTest {

	@Test(dataProvider = "coord_data_provider")
	public void test_idempotent_coord(int id, int width, int height) {
		Assert.assertEquals(Maps.getIdRotated(Maps.getXRotated(id, width, height), Maps.getYRotated(id, width, height), width, height), id, "Id must be idempotent");
	}

	@DataProvider
	public Iterator<Object[]> coord_data_provider() {
		return GeneratorCombiner.combine(integers(0, Short.MAX_VALUE), integers(1, Short.MAX_VALUE), integers(1, Short.MAX_VALUE));
	}

}