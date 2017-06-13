package uk.ac.ebi.pride.utilities.pridemod.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * yperez
 */
public class PRIDEModPTM extends AbstractPTM{

    // Unimod Reference
    private Comparable uniModRef;

    // ShortName for the biological significance of the modification
    private String shortName;

    // List of PSIModPTM
    private Map<Comparable, PSIModPTM> psiChildren = new HashMap<>();


    /**
     * Contructor with all the fields for a general modification
     *
     * @param accession        PTM accession
     * @param name             Name
     * @param description      Description
     * @param monoDeltaMass    MonoIsotopic Delta Mass
     * @param averageDeltaMass Average Delta Mass
     * @param specificityList  List of Specificity
     * @param formula          Chemical Formula
     */
    public PRIDEModPTM(String accession,
                       String name,
                       String description,
                       Double monoDeltaMass,
                       Double averageDeltaMass,
                       List<Specificity> specificityList,
                       Comparable unimodReference,
                       String formula, String shortName, Map<Comparable, PSIModPTM> children) {
        super(accession, name, description, monoDeltaMass, averageDeltaMass, specificityList, formula);
        this.uniModRef = unimodReference;
        this.shortName = shortName;
        this.psiChildren = children;
    }

    public Comparable getUniModRef() {
        return uniModRef;
    }

    public void setUniModRef(Comparable uniModRef) {
        this.uniModRef = uniModRef;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Map<Comparable, PSIModPTM> getPsiChildren() {
        return psiChildren;
    }

    public void setPsiChildren(Map<Comparable, PSIModPTM> psiChildren) {
        this.psiChildren = psiChildren;
    }

    @Override
    public String toString() {
        return "PRIDEModPTM{" +
                "uniModRef=" + uniModRef +
                '}';
    }
}
