package uk.ac.ebi.pridemod;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uk.ac.ebi.pridemod.model.PTM;

import java.util.List;

import static org.junit.Assert.*;

public class ModReaderTest {

    public ModReader modReader = null;


    @Before
    public void setUp() throws Exception {
        modReader = ModReader.getInstance();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void TestGetPTms() {
        List<PTM> ptms = modReader.getPTMListByPatternDescription("Phospho");
        assertTrue("Number of PTMs with Term 'Phospho' in name:", ptms.size() == 106);
    }

    @Test
    public void TestGetMod(){
        PTM ptm = modReader.getPTMbyAccession("MOD:00036");
        assertTrue("Difference mass for Average mass is:", ptm.getAveDeltaMass() == 16.0);
    }

    @Test
    public void TestGetMonoDeltaMass(){
        List<PTM> ptms = modReader.getPTMListByMonoDeltaMass(42.010565);
        assertTrue("The number of Proteins with Mono equal to 42.010565 is:", ptms.size() == 36);
    }

    @Test
    public void TestGetAveDeltaMass(){
        List<PTM> ptms = modReader.getPTMListByAvgDeltaMass(42.0367);
        assertTrue("The number of Proteins with Average equal to 42.010565 is:", ptms.size() == 2);
    }

    @Test
    public void testRetrieveAnchorPTM() throws Exception {

        //Todo: Solution for MOD:00907 should be provided

        List<PTM> ptms = modReader.getAnchorModification("MOD:01709");
        Assert.assertTrue(ptms.size() == 1);
        System.out.println(ptms);

        //Modification Oxidation example
        ptms = modReader.getAnchorModification("MOD:00412", "M");
        Assert.assertTrue(ptms.size() == 1);
        System.out.println(ptms);

        // Silac
        ptms = modReader.getAnchorModification("MOD:00942");
        Assert.assertTrue(ptms.size() == 1);
        System.out.println(ptms);

        // Silac
        ptms = modReader.getAnchorModification("MOD:01812");
        Assert.assertTrue(ptms.size() == 1);
        System.out.println(ptms);





    }
}