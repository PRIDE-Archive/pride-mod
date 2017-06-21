package uk.ac.ebi.pride.utilities.pridemod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.ac.ebi.pride.utilities.pridemod.controller.impl.PRIDEModDataAccessController;
import uk.ac.ebi.pride.utilities.pridemod.controller.impl.PSIModDataAccessController;
import uk.ac.ebi.pride.utilities.pridemod.exception.DataAccessException;
import uk.ac.ebi.pride.utilities.pridemod.model.*;
import uk.ac.ebi.pride.utilities.pridemod.controller.impl.UnimodDataAccessController;
import uk.ac.ebi.pride.utilities.pridemod.utils.Constants;
import uk.ac.ebi.pride.utilities.pridemod.utils.Utilities;

import java.io.InputStream;
import java.util.*;

/**
 * ModReader is a Helper Class that contains all the methods to interact with all the controllers. Because most of the
 * PTM database contains the same modifications we use this class to expose all the common methods amount all the controllers
 *
 * @author ypriverol
 */
public class ModReader {

    private static final Logger logger = LoggerFactory.getLogger(ModReader.class);

    private static InputStream unimodUrl    = ModReader.class.getClassLoader().getResourceAsStream("unimod.xml");
    private static InputStream psiModUrl    = ModReader.class.getClassLoader().getResourceAsStream("PSI-MOD.obo");
    private static InputStream prideModdUrl = ModReader.class.getClassLoader().getResourceAsStream("pride_mods.xml");

    private static UnimodDataAccessController   unimodController = null;
    private static PSIModDataAccessController   psiModController = null;
    private static PRIDEModDataAccessController prideModController = null;

    private volatile static ModReader instance = new ModReader();

    protected ModReader(){

        try {
            unimodController = new UnimodDataAccessController(unimodUrl);
            psiModController = new PSIModDataAccessController(psiModUrl);
            prideModController = new PRIDEModDataAccessController(prideModdUrl, unimodController, psiModController);
        } catch (Exception e) {
            String msg = "Exception while trying to read Ontology Files";
            logger.error(msg, e);
            throw new DataAccessException(msg, e);
        }
    }

    /**
     * The method to retrieve the ModReader Instance that can be use to retrieve all
     * PTMs in the major providers, PRIDE, PSIMod or UniMod.
     * @return
     */
    public static ModReader getInstance(){
        return instance;
    }

    /**
     * This function retrieve by Accession the PTM from UNIMOD, or PSIMod Ontologies. The
     * function also support search for Accessions in PSI-MS, such as
     * fragment losses.
     * @param accession
     * @return PTM
     */
    public PTM getPTMbyAccession(String accession){

        PTM ptm = null;
        if(Constants.getAccessionType(accession) == Constants.Database.MS){
            ptm = MSModification.getByAccession(accession);
        }
        if(Constants.getAccessionType(accession) == Constants.Database.UNIMOD){
            ptm = unimodController.getPTMbyAccession(accession);

        }else if(Constants.getAccessionType(accession) == Constants.Database.PSIMOD){
            ptm = psiModController.getPTMbyAccession(accession);
        }
        return ptm;
    }

    /**
     * This function retrieve by String pattern from Name in the the
     * PTM from UNIMOD, or PSIMod Ontologies. The function also support search
     * for Accessions in PSI-MS, such as fragment losses. The resulted List contains
     * the PTMs on all Ontologies including PSI and UniMod.
     * @param namePattern String Pattern.
     * @return List of PTMs
     */
    public List<PTM> getPTMListByPatternName(String namePattern){
        List<PTM> ptms = unimodController.getPTMListByPatternName(namePattern);
        ptms.addAll(psiModController.getPTMListByPatternName(namePattern));
        return ptms;
    }

    /**
     * This function retrieve by Specificity the PTM from UNIMOD, or PSIMod Ontologies. The
     * function also support search for Accessions in PSI-MS, such as
     * fragment losses. The resulted List contains the PTMs on all Ontologies including PSI and UniMod.
     * @param specificity . A site specific for the modification.
     * @return List of PTMs
     * @param specificity
     */
    public List<PTM> getPTMListBySpecificity(Specificity specificity){
        List<PTM> ptms = unimodController.getPTMListBySpecificity(specificity);
        ptms.addAll(psiModController.getPTMListBySpecificity(specificity));
        return ptms;
    }

    /**
     * This function retrieve by String pattern from Description in the the
     * PTM from UNIMOD, or PSIMod Ontologies. The function also support search
     * for Accessions in PSI-MS, such as fragment losses. The resulted List contains
     * the PTMs on all Ontologies including PSI and UniMod.
     *
     * @author descriptionPattern pattern on description
     * @return List of PTMs
     **/
     public List<PTM> getPTMListByPatternDescription(String descriptionPattern){
        List<PTM> ptms = unimodController.getPTMListByPatternDescription(descriptionPattern);
        ptms.addAll(psiModController.getPTMListByPatternDescription(descriptionPattern));
        return ptms;
    }

    /**
     * Return all PTMs with the exact name. In case of PSI-Mod modifications different
     * mofifications will be returns.
     * can have the same name.
     * @param name Name of the modification
     * @return List of PTMs
     */
    public List<PTM> getPTMListByEqualName(String name){
        List<PTM> ptms = unimodController.getPTMListByEqualName(name);
        ptms.addAll(psiModController.getPTMListByEqualName(name));
        return ptms;
    }

    /**
     * Get the PTMs using the MonoDelta Mass of the modification. This will
     * use the mono isotopic mass for the comparison of each modification.
     * @param delta the delta mass to be search
     * @return List of modifications.
     */
    public List<PTM> getPTMListByMonoDeltaMass(Double delta) {
        if(delta != null){
            List<PTM> ptms = unimodController.getPTMListByMonoDeltaMass(delta);
            ptms.addAll(psiModController.getPTMListByMonoDeltaMass(delta));
            return ptms;
        }
        return Collections.emptyList();
    }

    /**
     * Get the PTMs using the AvgDelta Mass of the modification. This will
     * use the average isotopic mass for the comparison of each modification.
     * @param delta the delta mass to be search
     * @return List of modifications.
     */
    public List<PTM> getPTMListByAvgDeltaMass(Double delta) {
        List<PTM> ptms = unimodController.getPTMListByAvgDeltaMass(delta);
        ptms.addAll(psiModController.getPTMListByAvgDeltaMass(delta));
        return ptms;

    }

    /**
     * Retrieve PRIDE Annotation PTM using the Accession of the corresponding
     * PTM in the group. The PRIDE Annotation PTM aggregate a set of PTMs in a
     * generic PTM with some important annotations on top such as:
     *   - Biologically relevant
     *   - ShortName
     *
     * @param accession
     * @return
     */
    public PRIDEModPTM getPRIDEModByAccession(String accession){
        String newAccession = getUniquePRIDEModAccessionFromCheMod(accession);
        if(newAccession != null)
            accession = newAccession;
        PRIDEModPTM prideMod = prideModController.getPRIDEModByChildrenID(accession);
        if(prideMod != null)
            return prideMod;
        return null;
    }

    /**
     * Retrieve PRIDE Annotation PTM using the Accession of the corresponding
     * AminoAcid where the modification occur. The PRIDE Annotation PTM aggregate a set of PTMs in a
     * generic PTM with some important annotations on top such as:
     *   - Biologically relevant
     *   - ShortName
     *
     * @param accession
     * @return
     */
    public PRIDEModPTM getPRIDEModByAccessionAndAmminoAcid(String accession, String aminoAcid){
        String newAccession = getUniquePRIDEModAccessionFromCheMod(accession, aminoAcid);
        if(newAccession != null)
            accession = newAccession;
        PRIDEModPTM prideMod = prideModController.getPRIDEModByChildrenID(accession);
        if(prideMod != null)
            return prideMod;
        return null;
    }


    /**
     * This function retrieve a UniMod accession if the accession is Unique, if the
     * PTM is not unique to one accession, it can't be assigned to a PTM without
     * manual curation. The accession should have the following format:
     *  - CHEMOD:34.560056
     *  - CHEMOD:-345.8999
     *
     * @param accession CHEMOD Accession.
     * @return A {@link UniModPTM} accession
     */
    private String getUniquePRIDEModAccessionFromCheMod(String accession){
        if(Utilities.isChemodAccession(accession)){
            Double mass = Utilities.getChemodMass(accession);
            if(mass != null){
                List<PTM> unimodList = unimodController.getPTMListByMonoDeltaMass(mass);
                UniModPTM generalModification = prideModController.getGeneralModificationUNIMOD(unimodList);
                if(generalModification != null)
                    return generalModification.getAccession();
            }
        }
        return null;
    }

    /**
     * This function retrieve a UniMod accession if the accession is Unique, if the
     * PTM is not unique to one accession, it can't be assigned to a PTM without
     * manual curation. The accession should have the following format:
     *  - CHEMOD:34.560056
     *  - CHEMOD:-345.8999
     *
     * @param accession CHEMOD Accession.
     * @return A {@link UniModPTM} accession
     */
    private String getUniquePRIDEModAccessionFromCheMod(String accession, String aminoAcid){
        if(Utilities.isChemodAccession(accession)){
            Double mass = Utilities.getChemodMass(accession);
            if(mass != null){
                List<PTM> unimodList = unimodController.getPTMListByMonoDeltaMassSpecificity(mass, aminoAcid);
                UniModPTM generalModification = prideModController.getGeneralModificationUNIMOD(unimodList);
                if(generalModification != null)
                    return generalModification.getAccession();
            }
        }
        return null;
    }

    private String getUniqueUnimodAccessionFromCheMod(String accession, String aa) {
        if(Utilities.isChemodAccession(accession)){
            Double mass = Utilities.getChemodMass(accession);
            if(mass != null){
                List<PTM> unimodList = unimodController.getPTMListByMonoDeltaMassSpecificity(mass,aa);
                if(unimodList != null && unimodList.size() == 1)
                    return unimodList.get(0).getAccession();
            }
        }
        return null;
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
            ptms = new ArrayList<>();
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
            ptms = new ArrayList<>();
            ptms.add(currentPTM);
        }
        ptms = remapPTMs(ptms);
        return ptms;
    }

    public List<PTM> getAnchorModification(String accession){
        List<PTM> ptms = new ArrayList<>();
        PTM ptm = getPTMbyAccession(accession);
        if(ptm != null)
            ptms.add(ptm);
        return remapPTMs(ptms);
    }

    public List<PTM> getAnchorModification(String accession, String aa) {
        List<PTM> ptms = new ArrayList<>();
        PTM ptm = getPTMbyAccession(accession);
        if(ptm != null)
            ptms.add(ptm);
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
        List<PTM> resutList = new ArrayList<>();
        for(PTM ptm: ptms){
            if(ptm instanceof PSIModPTM){
                PSIModPTM psiPTM = (PSIModPTM) ptm;
                if(psiPTM.getUnimodId() != null && !psiPTM.getUnimodId().isEmpty())
                    resutList.addAll(remapToUniMod(psiPTM));
                else if(psiPTM.isObsolete() && psiPTM.getRemapID() != null && !psiPTM.getRemapID().isEmpty()){
                    psiPTM = remapPTM((PSIModPTM) psiModController.getPTMbyAccession(psiPTM.getRemapID()));
                    if(psiPTM.getUnimodId() != null && !psiPTM.getUnimodId().isEmpty()){
                        resutList.addAll(remapToUniMod(psiPTM));
                    }
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
        List<PTM> resultPTMs = new ArrayList<>();
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
     * UniMod and map the mapUniMod accession to it.
     *
     * @param ptm the PSIMod modification
     * @return the list of Unimod modifications.
     */
    private List<PTM> remapToUniMod(PSIModPTM ptm){
        List<PTM> resultList = new ArrayList<>();
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

    public List<PTM> getAnchorModificationPosition(String accession, String aa) {
        List<PTM> ptms = new ArrayList<>();
        PTM ptm = getPTMbyAccession(accession);
        if(ptm != null)
            ptms.add(ptm);
        List<PTM> resultMaps = remapPTMs(ptms);
        ptms  = Utilities.filterPTMsByAminoAcidSpecificityPosition(resultMaps, aa);
        return ptms;
    }

    public List<PTM> getAnchorMassModification(Double mass, String aa) {
        List<PTM> ptms = getPTMListByMonoDeltaMass(mass);
        List<PTM> resultMaps = remapPTMs(ptms);
        ptms = Utilities.filterPTMsByAminoAcidSpecificity(resultMaps, aa);
        return ptms;
    }

    public List<PTM> getAnchorMassModificationPosition(Double mass, String position) {
        List<PTM> ptms = getPTMListByMonoDeltaMass(mass);
        List<PTM> resultMaps = remapPTMs(ptms);
        ptms = Utilities.filterPTMsByAminoAcidSpecificityPosition(resultMaps, position);
        return ptms;
    }

    /**
     * This modifica
     * @param accession
     * @param aa
     * @return
     */
    public boolean isWrongAnnotated(String accession, String aa){
        List<PTM> ptms  = getAnchorModificationPosition(accession, aa);
        return ptms == null || ptms.isEmpty();
    }
}
