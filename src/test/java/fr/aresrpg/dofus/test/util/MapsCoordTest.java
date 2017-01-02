package fr.aresrpg.dofus.test.util;

import fr.aresrpg.dofus.util.Maps;
import fr.aresrpg.test.GeneratorCombiner;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;

import static net.java.quickcheck.generator.PrimitiveGenerators.integers;

public class MapsCoordTest {


    @Test(dataProvider = "coord_data_provider")
    public void test_idempotent_coord(int id, int width , int height){
        Assert.assertEquals(Maps.getIdRotated(Maps.getXRotated(id , width , height) , Maps.getYRotated(id , width , height) , width , height)
                , id , "Id must be idempotent");
    }

    @DataProvider
    public Iterator<Object[]> coord_data_provider() {
        //TODO: Better QuickCheck
        return GeneratorCombiner.combine(integers(0 , 400) , integers(15 , 19) , integers(17 , 22));
    }

}