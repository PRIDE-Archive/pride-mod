package uk.ac.ebi.pridemod.controller.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import uk.ac.ebi.pridemod.model.PTM;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.assertTrue;


public class UnimodDataAccessControllerTest {

    public UnimodDataAccessController unimodDataAccessController = null;

    @Before
    public void setUp() throws Exception {
        InputStream inputStream = UnimodDataAccessControllerTest.class.getClassLoader().getResourceAsStream("unimod.xml");
        if (inputStream == null) {
            throw new IllegalStateException("no file for input found!");
        }
        unimodDataAccessController = new UnimodDataAccessController(inputStream);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void TestGetPTms() {
        List<PTM> ptms = unimodDataAccessController.getPTMListByPatternDescription("Phospho");
        assertTrue("Number of PTMs with Term 'Phospho' in name:", ptms.size() == 30);
    }

    @Test
    public void TestGetMod(){
        PTM ptm = unimodDataAccessController.getPTMbyAccession("UNIMOD:1");
        assertTrue("Difference mass for Average mass is:", ptm.getAveDeltaMass() == 42.0367);
    }
}
