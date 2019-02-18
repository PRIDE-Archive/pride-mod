package uk.ac.ebi.pride.utilities.pridemod.controller.impl;

import uk.ac.ebi.pride.utilities.pridemod.exception.DataAccessException;
import uk.ac.ebi.pride.utilities.pridemod.io.unimod.model.UnimodModification;
import uk.ac.ebi.pride.utilities.pridemod.model.PTM;
import uk.ac.ebi.pride.utilities.pridemod.model.UniModPTM;
import uk.ac.ebi.pride.utilities.pridemod.controller.AbstractDataAccessController;
import uk.ac.ebi.pride.utilities.pridemod.io.unimod.model.Unimod;
import uk.ac.ebi.pride.utilities.pridemod.io.unimod.xml.UnimodReader;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Class to retrieve all Modifications from Unimod database. All modifications are store in
 * memory using the ptmMap. The current version of UniMod is June 2017.
 *
 * @author ypriverol
 */
public class UnimodDataAccessController extends AbstractDataAccessController{

    private static final Logger logger = LoggerFactory.getLogger(UnimodDataAccessController.class);

    public UnimodDataAccessController(InputStream xml) {
        super(xml);
        try {
            UnimodReader reader = new UnimodReader(xml);
            initPTMMap(reader.getUnimodObject());

        } catch (JAXBException e) {
            String msg = "Exception while trying to read the Unimod file";
            logger.error(msg, e);
            throw new DataAccessException(msg, e);
        }
    }

    /**
     * Init the Map of the PTMs
     * @param unimodObject
     */
    private void initPTMMap(Unimod unimodObject) {

        ptmMap = new HashMap<>(unimodObject.getModifications().getMod().size());

        for(UnimodModification unimodMod: unimodObject.getModifications().getMod()){

             // We will add the UNIMOD to the accession in order to have the same style than PSI-MOD the mzIdentML files
            String accession   = "UNIMOD:" + (unimodMod.getRecordId()).intValue();
            String name        = unimodMod.getTitle();
            String description = unimodMod.getFullName();
            String formula     = (unimodMod.getDelta()!=null)?unimodMod.getDelta().getComposition():null;
            Double avgMass     = (unimodMod.getDelta()!=null)? (unimodMod.getDelta().getAvgeMass()).doubleValue():null;
            Double monoMass    = (unimodMod.getDelta()!=null)? (unimodMod.getDelta().getMonoMass()).doubleValue():null;
            List<uk.ac.ebi.pride.utilities.pridemod.model.Specificity> specificityList = null;

            Set<String> classifications = new HashSet<>();

            if(unimodMod.getSpecificity() != null && unimodMod.getSpecificity().size() > 0){
                specificityList = new ArrayList<>(unimodMod.getSpecificity().size());
                for(uk.ac.ebi.pride.utilities.pridemod.io.unimod.model.Specificity oldSpecificty: unimodMod.getSpecificity()){
                    uk.ac.ebi.pride.utilities.pridemod.model.Specificity specificity = new uk.ac.ebi.pride.utilities.pridemod.model.Specificity(oldSpecificty.getSite(), oldSpecificty.getPosition());
                    classifications.add(oldSpecificty.getClassification().toLowerCase());
                    specificityList.add(specificity);
                }

            }
            PTM uniModPTM = new UniModPTM(accession, name,description,monoMass,avgMass,specificityList,formula, new ArrayList<>(classifications));
            ptmMap.put(accession,uniModPTM);
        }
    }

    /**
     * We decided to put the UNIOMD: prefix in order to be compatible with the same PSI-MOD style and
     * the mzIdentML.
     * @param accession an accession in a way of UNIMOD:(number) can be also a number in the case of a number we added the prefix UNIMOD at the beginning.
     * @return
     */
    @Override
    public PTM getPTMbyAccession(String accession) {
        if(!accession.contains("UNIMOD:"))
            accession = "UNIMOD:" + accession;
        return super.getPTMbyAccession(accession);
    }
}
