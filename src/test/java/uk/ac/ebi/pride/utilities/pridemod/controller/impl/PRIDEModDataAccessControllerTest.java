package uk.ac.ebi.pride.utilities.pridemod.controller.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import uk.ac.ebi.pride.utilities.pridemod.controller.DataAccessController;
import uk.ac.ebi.pride.utilities.pridemod.model.PTM;

import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.*;

public class PRIDEModDataAccessControllerTest {

    public PRIDEModDataAccessController prideModDataAccessController = null;

    @Before
    public void setUp() throws Exception {
        InputStream inputStream = PRIDEModDataAccessController.class.getClassLoader().getResourceAsStream("pride_mods.xml");
        InputStream psiStream = PSIModDataAccessController.class.getClassLoader().getResourceAsStream("PSI-MOD.obo");
        InputStream uniModStream = PSIModDataAccessController.class.getClassLoader().getResourceAsStream("unimod.xml");

        if (psiStream == null || inputStream == null || uniModStream == null) {
            throw new IllegalStateException("Modification file not found!");
        }
        DataAccessController psiModDataAccessController = new PSIModDataAccessController(psiStream);
        DataAccessController unimodDataAccessController = new UnimodDataAccessController(uniModStream);
        prideModDataAccessController = new PRIDEModDataAccessController(inputStream,unimodDataAccessController,psiModDataAccessController);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void TestGetPTms() {
        List<PTM> ptms = prideModDataAccessController.getPTMListByPatternDescription("Phospho");
        assertTrue("Number of PTMs with Term 'Phospho' in name:", ptms.size() == 3);
    }

    @Test
    public void TestGetMod(){
        PTM ptm = prideModDataAccessController.getPTMbyAccession("UNIMOD:1");
        assertTrue("Difference mass for Average mass is:", ptm.getMonoDeltaMass() == 42.010565);
    }

}
