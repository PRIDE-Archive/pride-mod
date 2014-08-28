package uk.ac.ebi.pridemod;

import ch.qos.logback.core.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.ac.ebi.pridemod.controller.impl.PRIDEModDataAccessController;
import uk.ac.ebi.pridemod.controller.impl.PSIModDataAccessController;
import uk.ac.ebi.pridemod.controller.impl.UnimodDataAccessController;
import uk.ac.ebi.pridemod.exception.DataAccessException;
import uk.ac.ebi.pridemod.model.PTM;
import uk.ac.ebi.pridemod.model.Specificity;
import uk.ac.ebi.pridemod.utils.PRIDEModUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

/**
 * yperez
 */
public class ModReader {

    private static final Logger logger = LoggerFactory.getLogger(ModReader.class);

    /**
     * Local definition of Unimod
     */
    private static InputStream unimodUrl    = ModReader.class.getClassLoader().getResourceAsStream("unimod.xml");

    /**
     * Local definition of psiMod
     */
    private static InputStream psiModUrl    = ModReader.class.getClassLoader().getResourceAsStream("PSI-MOD.obo");

    /**
     * Local definition of pride mod
     */
    private static InputStream prideModdUrl = ModReader.class.getClassLoader().getResourceAsStream("pride_mods.xml");

    private UnimodDataAccessController unimodController;

    private PSIModDataAccessController psiModController;

    private PRIDEModDataAccessController prideModController;

    private static ModReader instance = null;

    protected ModReader(){
        try {
            unimodController = new UnimodDataAccessController(unimodUrl);
            psiModController = new PSIModDataAccessController(psiModUrl);
            prideModController = new PRIDEModDataAccessController(prideModdUrl);
        } catch (Exception e) {
            String msg = "Exception while trying to read Database files..";
            logger.error(msg, e);
            throw new DataAccessException(msg, e);
        } finally {
            try {
                if(unimodUrl!= null){
                    unimodUrl.close();
                }
                if(psiModUrl!= null){
                    psiModUrl.close();
                }
                if(psiModUrl!= null){
                    psiModUrl.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static ModReader getInstance(){
        if(instance == null){
            instance = new ModReader();
        }
        return instance;
    }

    /**
     * PTM accession
     * @param accession
     * @return
     */
    public PTM getPTMbyAccession(String accession){
        PTM ptm = null;
        if(PRIDEModUtils.getAccessionType(accession) == PRIDEModUtils.Database.UNIMOD){
            ptm = unimodController.getPTMbyAccession(accession);
        }else if(PRIDEModUtils.getAccessionType(accession) == PRIDEModUtils.Database.PSIMOD){
            ptm = psiModController.getPTMbyAccession(accession);
        }
        return ptm;
    }

    /**
     * String pattern present in the name.
     * @param namePattern
     * @return
     */
    public List<PTM> getPTMListByPatternName(String namePattern){
        List<PTM> ptms = unimodController.getPTMListByPatternName(namePattern);
        ptms.addAll(psiModController.getPTMListByPatternName(namePattern));
        return ptms;
    }

    /**
     * Specificity to filter all the identifications in the
     * @param specificity
     * @return
     */
    public List<PTM> getPTMListBySpecificity(Specificity specificity){
        List<PTM> ptms = unimodController.getPTMListBySpecificity(specificity);
        ptms.addAll(psiModController.getPTMListBySpecificity(specificity));
        return ptms;
    }

    /**
     * Description pattern to found PTMs with the pattern
     * @param descriptionPattern
     * @return
     */
    public List<PTM> getPTMListByPatternDescription(String descriptionPattern){
        List<PTM> ptms = unimodController.getPTMListByPatternDescription(descriptionPattern);
        ptms.addAll(psiModController.getPTMListByPatternDescription(descriptionPattern));
        return ptms;
    }

    /**
     * Return all PTMs with the same name. In case of PSI-Mod modifications different mofifications
     * can have the same name.
     * @param name
     * @return
     */
    public List<PTM> getPTMListByEqualName(String name){
        List<PTM> ptms = unimodController.getPTMListByEqualName(name);
        ptms.addAll(psiModController.getPTMListByEqualName(name));
        return ptms;
    }

    public List<PTM> getPTMListByMonoDeltaMass(Double delta) {
        List<PTM> ptms = unimodController.getPTMListByMonoDeltaMass(delta);
        ptms.addAll(psiModController.getPTMListByMonoDeltaMass(delta));
        return ptms;
    }


    public List<PTM> getPTMListByAvgDeltaMass(Double delta) {
        List<PTM> ptms = unimodController.getPTMListByAvgDeltaMass(delta);
        ptms.addAll(psiModController.getPTMListByAvgDeltaMass(delta));
        return ptms;

    }
}
