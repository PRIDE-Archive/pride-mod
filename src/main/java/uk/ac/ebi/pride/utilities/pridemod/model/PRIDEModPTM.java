package uk.ac.ebi.pride.utilities.pridemod.model;

import uk.ac.ebi.pride.utilities.pridemod.exception.DataAccessException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The PRIDEModPTM contains the information for specific modifications in PRIDE annotation system.
 * Each PRIDE Modification contains a reference to a {@link UniModPTM} Reference, a biological Relevant field,
 * a correspong shortName and a list of {@link PSIModPTM}.
 */

public class PRIDEModPTM extends AbstractPTM{

    private Map<Comparable, Map.Entry<UniModPTM,Boolean>> uniModRef = new HashMap<>();
    private String shortName;

    private Map<Comparable, Map.Entry<PSIModPTM, Boolean>> psiChildren = new HashMap<>();
    private boolean biologicalRelevant;

    private String title;

    /**
     * Contructor with all the fields for a general modification.
     * @param accession
     * @param title
     * @param unimodReference
     * @param shortName
     * @param biologicalRelevant
     * @param children
     */
    public PRIDEModPTM(String accession,
                       String title,
                       Map<Comparable, Map.Entry<UniModPTM,Boolean>> unimodReference,
                       String shortName, boolean biologicalRelevant, Map<Comparable, Map.Entry<PSIModPTM, Boolean>> children) {
        super(accession);
        this.uniModRef = unimodReference;
        this.shortName = shortName;
        this.psiChildren = children;
        this.biologicalRelevant = biologicalRelevant;
        this.title = title;
    }

    public String getShortName() {
        return shortName;
    }

    public String getTitle(){
        return this.title;
    }

    public boolean isBiologicalRelevant() {
        return biologicalRelevant;
    }

    /**
     * This function return check if the UniMod Reference PTM is
     * the same that the accession.
     * @param accession UNIMOD accession.
     * @return true if the UNIMOD modification reference is the same that the accession
     */
    public boolean isUniModRef(String accession) {
        return (uniModRef != null && !uniModRef.isEmpty() && uniModRef.containsKey(accession.toUpperCase()));
    }

    /**
     * This function return true if is able to find a PSI MOD modifications in the modifications list
     * @param accession
     * @return true if psiChildren contains the accession.
     */
    public boolean containsPSIMod(String accession) {
        return (psiChildren != null && !psiChildren.isEmpty() && psiChildren.containsKey(accession.toUpperCase()));
    }

    @Override
    public String getAccession() {
        UniModPTM uniModPTM = getUnimodGeneralModification();
        if(uniModPTM != null)
            return uniModPTM.getAccession();
        PSIModPTM psiModPTM = getPSIGeneralModification();
        if(psiModPTM != null)
            return psiModPTM.getAccession();
        throw new DataAccessException("The PRIDE Modifications is wrongly defined");
    }

    @Override
    public String getName() {
        UniModPTM uniModPTM = getUnimodGeneralModification();
        if(uniModPTM != null)
            return uniModPTM.getName();
        PSIModPTM psiModPTM = getPSIGeneralModification();
        if(psiModPTM != null)
            return psiModPTM.getName();
        throw new DataAccessException("The PRIDE Modifications is wrongly defined");
    }

    @Override
    public Double getMonoDeltaMass() {
        UniModPTM uniModPTM = getUnimodGeneralModification();
        if(uniModPTM != null)
            return uniModPTM.getMonoDeltaMass();
        PSIModPTM psiModPTM = getPSIGeneralModification();
        if(psiModPTM != null)
            return psiModPTM.getMonoDeltaMass();
        throw new DataAccessException("The PRIDE Modifications is wrongly defined");
    }

    @Override
    public Double getAveDeltaMass() {
        UniModPTM uniModPTM = getUnimodGeneralModification();
        if(uniModPTM != null)
            return uniModPTM.getAveDeltaMass();
        PSIModPTM psiModPTM = getPSIGeneralModification();
        if(psiModPTM != null)
            return psiModPTM.getAveDeltaMass();
        throw new DataAccessException("The PRIDE Modifications is wrongly defined");
    }

    @Override
    public String getDescription() {
        UniModPTM uniModPTM = getUnimodGeneralModification();
        if(uniModPTM != null)
            return uniModPTM.getDescription();
        PSIModPTM psiModPTM = getPSIGeneralModification();
        if(psiModPTM != null)
            return psiModPTM.getDescription();
        throw new DataAccessException("The PRIDE Modifications is wrongly defined");
    }

    @Override
    public List<Specificity> getSpecificityCollection() {
        List<Specificity> specificities = new ArrayList<>();
        if(uniModRef != null)
            for(Map.Entry uniModValue: uniModRef.values())
                specificities.addAll(((UniModPTM)uniModValue).getSpecificityCollection());
        if(psiChildren != null){
            for(Map.Entry psiModValue: psiChildren.values()){
                specificities.addAll(((PSIModPTM)psiModValue.getKey()).getSpecificityCollection());
            }
        }
        return specificities;
    }

    @Override
    public String toString() {
        return "PRIDEModPTM{" +
                "uniModRef=" + uniModRef +
                '}';
    }

    private PSIModPTM getPSIGeneralModification(){
        for(Map.Entry psiModPTM: psiChildren.values()){
            if((Boolean) psiModPTM.getValue())
                return (PSIModPTM) psiModPTM.getKey();
        }
        return null;

    }

    private UniModPTM getUnimodGeneralModification(){
        for(Map.Entry unimodPTM: uniModRef.values()){
            if((Boolean) unimodPTM.getValue())
                return (UniModPTM) unimodPTM.getKey();
        }
        return null;

    }

}
