package uk.ac.ebi.pridemod;

import org.junit.After;
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
        assertEquals("Number of PTMs with Term 'Phospho' in name:", ptms.size(), 104);
    }

    @Test
    public void TestGetMod(){
        PTM ptm = modReader.getPTMbyAccession("MOD:00036");
        assertEquals("Difference mass for Average mass is:", ptm.getAveDeltaMass(), (Double) 16.0);
    }

    @Test
    public void TestGetMonoDeltaMass(){
        List<PTM> ptms = modReader.getPTMListByMonoDeltaMass(42.010565);
        assertEquals("The number of Proteins with Mono equal to 42.010565 is:", ptms.size(), 37);
    }

    @Test
    public void TestGetAveDeltaMass(){
        List<PTM> ptms = modReader.getPTMListByAvgDeltaMass(42.0367);
        assertEquals("The number of Proteins with Average equal to 42.010565 is:", ptms.size(), 2);
    }
}