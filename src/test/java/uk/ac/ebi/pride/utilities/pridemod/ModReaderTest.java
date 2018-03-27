package uk.ac.ebi.pride.utilities.pridemod;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uk.ac.ebi.pride.utilities.pridemod.controller.impl.UnimodDataAccessController;
import uk.ac.ebi.pride.utilities.pridemod.model.PRIDEModPTM;
import uk.ac.ebi.pride.utilities.pridemod.model.PTM;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
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

        PTM ptm2 = modReader.getPTMbyAccession("MS:1001524");
        assertEquals("Name of the MS:1001524 should be fragment neutral loss", ptm2.getShortName().toLowerCase(), "fragment neutral loss");
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

        List<PTM> ptms = modReader.getAnchorModification("MOD:00696");
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

        // Silac
        ptms = modReader.getAnchorModification("MOD:00417");
        Assert.assertTrue(ptms.size() == 1);
        System.out.println(ptms);

    }

    @Test
    public void TestGetShortNamePRIDEMod(){
        Assert.assertTrue(modReader.getPRIDEModByAccession("MOD:00394").getShortName().equalsIgnoreCase("acetyl"));

        // Unimod works with Unimod Prefix and Without it, this run is with Prefix
        Assert.assertTrue(modReader.getPRIDEModByAccession("UNIMOD:1").getShortName().equalsIgnoreCase("acetyl"));

        Assert.assertTrue(modReader.getPRIDEModByAccession("CHEMMOD:59.049690") != null );

        Assert.assertTrue(modReader.getPRIDEModByAccession("CHEMMOD:0.984016") != null);

        Assert.assertTrue(modReader.getPRIDEModByAccessionAndAmminoAcid("CHEMMOD:0.984016", "R") != null );

    }


    @Test
    public void TestGetShortNamePRIDEModFromFile(){
        try {
            File file = new File(ModReaderTest.class.getClassLoader().getResource("mods_not_found.txt").toURI());
            BufferedReader br = new BufferedReader(new FileReader(file));
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                PRIDEModPTM prideModPTM = modReader.getPRIDEModByAccession(sCurrentLine);
                if(prideModPTM == null){
                    System.out.println("Accession-> " + sCurrentLine + " not found shortname");
                    List<PTM> ptm = modReader.getAnchorModification(sCurrentLine);
                    if(ptm.size() == 1)
                        System.out.println("Accession-> " + sCurrentLine + " Short name -> " + ptm.get(0).getShortName());

                } else{
                    System.out.println("Accession-> " + sCurrentLine + " Short name -> " + prideModPTM.getShortName());
                }
            }
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestShortNameUNIMOD() throws IOException {
        InputStream file = ModReaderTest.class.getClassLoader().getResourceAsStream("unimod.xml");
        UnimodDataAccessController unimodDataAccessController = new UnimodDataAccessController(file);
        if(unimodDataAccessController != null && unimodDataAccessController.ptmMap.size() > 0){
            for(PTM ptm: unimodDataAccessController.ptmMap.values()){
                System.out.println("Accession-> " + ptm.getAccession() + " Short name -> " + ptm.getShortName());
                if(ptm.getShortName().contains("\t") || ptm.getShortName().contains(" "))
                    System.out.println("ERROR");
            }
        }

    }
}