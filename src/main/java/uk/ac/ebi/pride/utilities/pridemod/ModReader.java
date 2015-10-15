package uk.ac.ebi.pride.utilities.pridemod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.ac.ebi.pride.utilities.pridemod.controller.impl.PRIDEModDataAccessController;
import uk.ac.ebi.pride.utilities.pridemod.controller.impl.PSIModDataAccessController;
import uk.ac.ebi.pride.utilities.pridemod.exception.DataAccessException;
import uk.ac.ebi.pride.utilities.pridemod.model.PTM;
import uk.ac.ebi.pride.utilities.pridemod.model.Specificity;
import uk.ac.ebi.pride.utilities.pridemod.controller.impl.UnimodDataAccessController;
import uk.ac.ebi.pride.utilities.pridemod.model.PSIModPTM;
import uk.ac.ebi.pride.utilities.pridemod.utils.PRIDEModUtils;
import uk.ac.ebi.pride.utilities.pridemod.utils.Utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

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

    private static UnimodDataAccessController unimodController;

    private static PSIModDataAccessController psiModController;

    private static PRIDEModDataAccessController prideModController;

    private volatile static ModReader instance = new ModReader();

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

    /**
     * Get the PTMs using the MonoDelta Mass of the modification
     * @param delta the delta mass to be search
     * @return
     */
    public List<PTM> getPTMListByMonoDeltaMass(Double delta) {
        if(delta != null){
            List<PTM> ptms = unimodController.getPTMListByMonoDeltaMass(delta);
            ptms.addAll(psiModController.getPTMListByMonoDeltaMass(delta));
            return ptms;
        }
        return Collections.emptyList();
    }

    public List<PTM> getPTMListByAvgDeltaMass(Double delta) {
        List<PTM> ptms = unimodController.getPTMListByAvgDeltaMass(delta);
        ptms.addAll(psiModController.getPTMListByAvgDeltaMass(delta));
        return ptms;

    }

    /**
     * List the anchor synonyms for a given modification.
     * 1- The method started by adding getting all the modifications with the same
     * monoisotopic mass.
     * 2- Remap all of the to the new terms avoiding to include obsolete terms.
     * 3- Remap all of them to the UniMod modifications if is possible.
     * 4- If the Modification has a parent with the UniMod reference, we will try to use this as the reference.
     * 5- Filter the results by know amino-acid position
     * @param accession
     * @param aa
     * @return
     */
    public List<PTM> getAnchorModification(String accession, String aa, boolean delta){
        PTM currentPTM = getPTMbyAccession(accession);
        Double monoDelta = currentPTM.getMonoDeltaMass();
        List<PTM> ptms = getPTMListByMonoDeltaMass(monoDelta);
        if(ptms.isEmpty()){
            ptms = new ArrayList<PTM>();
            ptms.add(currentPTM);
        }
        ptms = remapPTMs(ptms);
        ptms  = Utilities.filterPTMsByAminoAcidSpecificity(ptms, aa);
        return ptms;
    }


    public List<PTM> getAnchorModification(String accession, boolean delta) {
        PTM currentPTM = getPTMbyAccession(accession);
        Double monoDelta = currentPTM.getMonoDeltaMass();
        List<PTM> ptms = getPTMListByMonoDeltaMass(monoDelta);
        if(ptms.isEmpty()){
            ptms = new ArrayList<PTM>();
            ptms.add(currentPTM);
        }
        ptms = remapPTMs(ptms);
        return ptms;
    }

    public List<PTM> getAnchorModification(String accession){
        List<PTM> ptms = new ArrayList<PTM>();
        ptms.add(getPTMbyAccession(accession));
        List<PTM> resultMaps = remapPTMs(ptms);
        return resultMaps;
    }

    public List<PTM> getAnchorModification(String accession, String aa) {
        List<PTM> ptms = new ArrayList<PTM>();
        ptms.add(getPTMbyAccession(accession));
        List<PTM> resultMaps = remapPTMs(ptms);
        ptms  = Utilities.filterPTMsByAminoAcidSpecificity(resultMaps, aa);
        return ptms;
    }



    /**
     * This function allows to remap all possible modifications for a list of modifications, it try to map all modifications from PSI and UniMod into
     * a UniMod list of modifications.
     * @param ptms the list of the current modifications
     * @return a List of mapped modifications
     */
    private List<PTM> remapPTMs(List<PTM> ptms){
        List<PTM> resutList = new ArrayList<PTM>();
        for(PTM ptm: ptms){
            if(ptm instanceof PSIModPTM){
                PSIModPTM psiPTM = (PSIModPTM) ptm;

                if(psiPTM.isObsolete() && psiPTM.getRemapID() != null && !psiPTM.getRemapID().isEmpty()){
                    psiPTM = remapPTM((PSIModPTM) psiModController.getPTMbyAccession(psiPTM.getRemapID()));
                }

                if(psiPTM.getUnimodId() != null && !psiPTM.getUnimodId().isEmpty()){
                    resutList.addAll(remapToUniMod(psiPTM));
                }else if(psiPTM.getParentPTMList() != null && !psiPTM.getParentPTMList().isEmpty()){
                    List<PTM> parents = remapParentPtms(psiPTM);
                    if(!parents.isEmpty())
                        resutList.addAll(parents);
                    else
                        resutList.add(psiPTM);
                }else
                    resutList.add(psiPTM);
            }else
                resutList.add(ptm);
        }
        Set<PTM> hashPTMs = new HashSet<>(resutList);
        return new ArrayList<>(hashPTMs);
    }

    /**
     * Try to remap the parents PTMs to UniMod, if the parent do not contains the UniMod,
     * we don't do anything with it.
     * @param currentPTM the PSI Mod to be mapped
     * @return A list of Unimod modifications were the modification map.
     */
    private List<PTM> remapParentPtms(PTM currentPTM) {
        List<PTM> resultPTMs = new ArrayList<PTM>();
        for(Comparable parent: ((PSIModPTM)currentPTM).getParentPTMList()){
            PSIModPTM psiModPTM = (PSIModPTM) psiModController.getPTMbyAccession((String) parent);
            if(psiModPTM.getUnimodId() != null && !psiModPTM.getUnimodId().isEmpty()){
                resultPTMs.addAll(remapToUniMod(psiModPTM));
            }else if(psiModPTM.getParentPTMList() != null && !psiModPTM.getParentPTMList().isEmpty()){
                resultPTMs.addAll(remapParentPtms(psiModPTM));
            }
        }
        return resultPTMs;
    }

    /**
     * This function map a PSIMod modification to UniMod by looking inside the
     * UniMod and map the mapUniMod id to it.
     *
     * @param ptm the PSIMod modification
     * @return the list of Unimod modifications.
     */
    private List<PTM> remapToUniMod(PSIModPTM ptm){
        List<PTM> resultList = new ArrayList<PTM>();
        for(String ptmAccesion: ptm.getUnimodId()){
            PTM unimodPTM = unimodController.getPTMbyAccession(Utilities.removePrefixUniMod(ptmAccesion));
            if( unimodPTM != null)
                resultList.add(unimodPTM);
        }
        return resultList;
    }

    /**
     * This function allow to trace the obsolete modifications until the updated and new version of modification.
     * The recursive method allow to remap obsolete modifications to the new versions.
     * @param psiPTM an obsolete modification
     * @return the new Term
     */
    private PSIModPTM remapPTM(PSIModPTM psiPTM){
        if(psiPTM.isObsolete() && psiPTM.getRemapID() != null){
            PSIModPTM ptmResult = (PSIModPTM) psiModController.getPTMbyAccession(psiPTM.getRemapID());
            return remapPTM(ptmResult);
        }
        return psiPTM;
    }
}
