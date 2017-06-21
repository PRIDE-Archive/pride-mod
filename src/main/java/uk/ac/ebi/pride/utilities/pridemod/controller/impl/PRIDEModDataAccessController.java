package uk.ac.ebi.pride.utilities.pridemod.controller.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.ac.ebi.pride.utilities.pridemod.controller.DataAccessController;
import uk.ac.ebi.pride.utilities.pridemod.io.pridemod.model.PsiModification;
import uk.ac.ebi.pride.utilities.pridemod.io.pridemod.model.UnimodMapping;
import uk.ac.ebi.pride.utilities.pridemod.model.*;
import uk.ac.ebi.pride.utilities.pridemod.controller.AbstractDataAccessController;
import uk.ac.ebi.pride.utilities.pridemod.io.pridemod.model.PrideMod;
import uk.ac.ebi.pride.utilities.pridemod.io.pridemod.model.PrideModification;
import uk.ac.ebi.pride.utilities.pridemod.io.pridemod.xml.PrideModReader;
import uk.ac.ebi.pride.utilities.pridemod.utils.Utilities;

import java.io.InputStream;
import java.util.*;

/**
 * PRIDEMod Controller only provide metadata ang grouping options for each Modification.
 * Three main features are provided:
 *   - Relevance of the modification, if is biologically relevant taking into account PRIDE criteria.
 *   - Redundancy amount PSI-Mod modifications, for such modifications redundant it returns a unique Identifier
 *   - A shortname that can be use in Proteogenomics conversion and annotations.
 */
public class PRIDEModDataAccessController extends AbstractDataAccessController {

    private static final Logger logger = LoggerFactory.getLogger(PRIDEModDataAccessController.class);
    private static DataAccessController unimodController = null;
    private static DataAccessController psimodController = null;

    /**
     * This Constructor populates the information of each PTM in the Map from including all the information.
     * @param xml the PRIDE XML containing all the information about the PTMs
     * @param unimodController Unimod Controller.
     * @param psimodController PSI Controller.
     */
    public PRIDEModDataAccessController(InputStream xml, DataAccessController unimodController, DataAccessController psimodController){
        super(xml);

        this.unimodController = unimodController;
        this.psimodController = psimodController;

        PrideModReader reader = new PrideModReader(xml);
        initPTMMap(reader.getPrideMod());
    }

    /**
     * An initial Map containing all the information for the modification. In the PRIDE Case it contains
     * contains information about the each Modification.
     *
     * @param prideMod
     */
    private void initPTMMap(PrideMod prideMod) {

        if(prideMod != null) {
            ptmMap = new HashMap<>();
            for (PrideModification oldMod : prideMod.getPrideModifications().getPrideModification()) {
                logger.debug("Accession -> " + oldMod.getId());
                String accession = oldMod.getAccession();
                String shortName = oldMod.getShortname();
                String title     = oldMod.getTitle();
                UniModPTM uniModPTM = null;
                if(oldMod.getUnimodMapping() != null){
                    uniModPTM = (UniModPTM) unimodController.getPTMbyAccession(oldMod.getUnimodMapping().getAccession());
                }
                Map<Comparable, Map.Entry<PSIModPTM, Boolean>> children = new HashMap<>();
                if(oldMod.getPsiModifications() != null && !oldMod.getPsiModifications().getPsiModification().isEmpty()){
                    for(PsiModification psiModification: oldMod.getPsiModifications().getPsiModification()){
                        PSIModPTM psiModPTM = (PSIModPTM) psimodController.getPTMbyAccession(psiModification.getAccession());
                        children.put(psiModification.getAccession(), new AbstractMap.SimpleEntry<>(psiModPTM, psiModification.getGeneralModification().intValue() ==1));
                    }
                }
                PRIDEModPTM ptm = new PRIDEModPTM(accession, title,uniModPTM,shortName,(oldMod.getBiologicalSignificance().intValue() == 1),children);
                ptmMap.put(accession, ptm);
            }
        }
    }

    /**
     * This function retrieve the PRIDE PTM annotations if the accession can be found in the UNIMOD
     * Modifications of the PSIMOD references.
     * @param accession accession to be found
     * @return PRIDEModPTM
     */
    public PRIDEModPTM getPRIDEModByChildrenID(String accession) {
        for(PTM prideModPTMValue: ptmMap.values()){
            PRIDEModPTM prideModPTM = (PRIDEModPTM) prideModPTMValue;
            if(Utilities.isUniModAccession(accession) && prideModPTM.isUniModRef(accession) || Utilities.isPSIModAccession(accession) && prideModPTM.containsPSIMod(accession))
                return prideModPTM;
        }
        return null;
    }
}
