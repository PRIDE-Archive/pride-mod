package uk.ac.ebi.pride.utilities.pridemod.model;

import java.util.List;

/**
 * The UniModPTM contains the information for each modification in Unimod. The current version represent the information
 * and fields on UniMod June 2017.
 *
 * @author ypriverol
 */
public class UniModPTM extends AbstractPTM {

    /**
     * Constructor with all the fields for a general modification
     *
     * @param accession        PTM accession
     * @param name             Name
     * @param description      Description
     * @param monoDeltaMass    MonoIsotopic Delta Mass
     * @param averageDeltaMass Average Delta Mass
     * @param specificityList  List of Specificity
     * @param formula          Chemical Formula
     */

    List<String> classifications;

    public UniModPTM(String accession,
                     String name,
                     String description,
                     Double monoDeltaMass,
                     Double averageDeltaMass,
                     List<Specificity> specificityList,
                     String formula) {
        super(accession, name, description, monoDeltaMass, averageDeltaMass, specificityList, formula);
    }

    public UniModPTM(String accession, String name, String description, Double monoDeltaMass, Double averageDeltaMass, List<Specificity> specificityList, String formula, List<String> classifications) {
        super(accession, name, description, monoDeltaMass, averageDeltaMass, specificityList, formula);
        this.classifications = classifications;
    }

    @Override
    public String getCvLabel() {
        return "UNIMOD";
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return this.accession.equalsIgnoreCase(((UniModPTM)obj).getAccession());
    }

    public List<String> getClassifications() {
        return classifications;
    }
}
