package uk.ac.ebi.pride.utilities.pridemod.model;

import java.util.List;

/**
 * yperez
 */
public class PRIDEModPTM extends AbstractPTM{


    private Boolean biologicalSignificance;
    private Comparable uniModRef;


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
     * @param bioSignificance  Biological Significance vs artificial modification
     */
    public PRIDEModPTM(String accession,
                       String name,
                       String description,
                       Double monoDeltaMass,
                       Double averageDeltaMass,
                       List<Specificity> specificityList,
                       Comparable unimodReference,
                       String formula,
                       Boolean bioSignificance) {
        super(accession, name, description, monoDeltaMass, averageDeltaMass, specificityList, formula);
        this.uniModRef = unimodReference;
        this.biologicalSignificance = bioSignificance;

    }

    public Comparable getUniModRef() {
        return uniModRef;
    }

    public void setUniModRef(Comparable uniModRef) {
        this.uniModRef = uniModRef;
    }

    public Boolean isBiologicalSignificance() {
        return biologicalSignificance;
    }

    public void setBiologicalSignificance(Boolean biologicalSignificance) {
        this.biologicalSignificance = biologicalSignificance;
    }

    @Override
    public String toString() {
        return "PRIDEModPTM{" +
                "uniModRef=" + uniModRef +
                '}';
    }
}
