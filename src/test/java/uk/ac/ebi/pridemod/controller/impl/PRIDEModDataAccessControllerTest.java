package uk.ac.ebi.pridemod.controller.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import uk.ac.ebi.pridemod.model.PTM;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.*;

public class PRIDEModDataAccessControllerTest {

    public PRIDEModDataAccessController prideModDataAccessController = null;

    @Before
    public void setUp() throws Exception {
        InputStream inputStream = PRIDEModDataAccessControllerTest.class.getClassLoader().getResourceAsStream("pride_mods.xml");
        if (inputStream == null) {
            throw new IllegalStateException("no file for input found!");
        }
        prideModDataAccessController = new PRIDEModDataAccessController(inputStream);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void TestGetPTms() {
        List<PTM> ptms = prideModDataAccessController.getPTMListByPatternDescription("Phospho");
        assertTrue("Number of PTMs with Term 'Phospho' in name:", ptms.size() == 2);
    }

    @Test
    public void TestGetMod(){
        PTM ptm = prideModDataAccessController.getPTMbyAccession("MOD:00394");
        assertTrue("Difference mass for Average mass is:", ptm.getMonoDeltaMass() == 42.010565);
    }

}
