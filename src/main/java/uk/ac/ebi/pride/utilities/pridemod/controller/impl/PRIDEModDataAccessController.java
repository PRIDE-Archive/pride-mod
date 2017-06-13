package uk.ac.ebi.pride.utilities.pridemod.controller.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.ac.ebi.pride.utilities.pridemod.io.pridemod.model.PsiModification;
import uk.ac.ebi.pride.utilities.pridemod.model.PSIModPTM;
import uk.ac.ebi.pride.utilities.pridemod.model.PTM;
import uk.ac.ebi.pride.utilities.pridemod.model.Specificity;
import uk.ac.ebi.pride.utilities.pridemod.controller.AbstractDataAccessController;
import uk.ac.ebi.pride.utilities.pridemod.io.pridemod.model.PrideMod;
import uk.ac.ebi.pride.utilities.pridemod.io.pridemod.model.PrideModification;
import uk.ac.ebi.pride.utilities.pridemod.io.pridemod.xml.PrideModReader;
import uk.ac.ebi.pride.utilities.pridemod.model.PRIDEModPTM;
import uk.ac.ebi.pride.utilities.pridemod.utils.Utilities;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * yperez
 */
public class PRIDEModDataAccessController extends AbstractDataAccessController {

    private static final Logger logger = LoggerFactory.getLogger(PRIDEModDataAccessController.class);

    /**
     * Default constructor for Controllers
     *
     * @param xml
     */
    public PRIDEModDataAccessController(InputStream xml) {
        super(xml);

        PrideModReader reader = new PrideModReader(xml);
        initPTMMap(reader.getPrideMod());
    }

    /**
     * Init the PTM map.
     * @param prideMod
     */
    private void initPTMMap(PrideMod prideMod) {
        if(prideMod != null) {
            ptmMap = new HashMap<>();
            for (PrideModification oldMod : prideMod.getPrideModifications().getPrideModification()) {
                String accession = oldMod.getPsiId();
                String name = oldMod.getPsiName();
                Double monoMass = oldMod.getDiffMono().doubleValue();
                List<Specificity> specicityList = oldMod.getSpecificityList();
                Comparable unimodReference = String.valueOf(oldMod.getUnimodMappings().getUnimodMapping().get(0).getId().intValue());
                Map<Comparable, PSIModPTM> children = new HashMap<>();
                if(oldMod.getPsiModifications() != null && !oldMod.getPsiModifications().getPsiModification().isEmpty()){
                    for(PsiModification psiModification: oldMod.getPsiModifications().getPsiModification()){
                        List<Specificity> specificities = new ArrayList<>();
                        Specificity spec = new Specificity(psiModification.getOrigin(),null);
                        specificities.add(spec);
                        PSIModPTM psiModPTM = new PSIModPTM(psiModification.getId(), psiModification.getTitle(), null,
                                oldMod.getDiffMono().doubleValue(),null, specificities,
                                null,null,psiModification.getObsolete().intValue() == 1,
                                psiModification.getTermSpec(),null, null,null);
                        children.put(psiModification.getId(), psiModPTM);
                    }
                }
                PRIDEModPTM ptm = new PRIDEModPTM(accession, name, name, monoMass, null, specicityList, unimodReference, null, oldMod.getShortname(), children);
                ptmMap.put(accession, ptm);
            }
        }
    }

    /**
     * Accession can be Unimod in this format: Unimod:35 or PSI-MOD in the form of MOD:00492
     * @param childAccession PSI MOD or Unimod ID
     * @return PrideModPTM
     */
    public PRIDEModPTM getPRIDEModByChildrenID(String childAccession){
        if(childAccession != null){
            for(PTM iPTM: ptmMap.values()){
                PRIDEModPTM ptm = (PRIDEModPTM) iPTM;
                if(Utilities.getIntegerForUnimodAccession(childAccession) != null && ptm.getUniModRef().equals(Utilities.getIntegerForUnimodAccession(childAccession).toString()))
                    return ptm;
                for(PSIModPTM psiMod: ptm.getPsiChildren().values())
                    if(psiMod.getAccession().equalsIgnoreCase(childAccession))
                        return ptm;
            }
        }
        return null;
    }

}
