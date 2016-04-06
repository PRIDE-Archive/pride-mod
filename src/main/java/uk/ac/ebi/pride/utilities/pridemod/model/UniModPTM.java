package uk.ac.ebi.pride.utilities.pridemod.model;

import java.util.List;

/**
 * yperez
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
    public UniModPTM(String accession,
                     String name,
                     String description,
                     Double monoDeltaMass,
                     Double averageDeltaMass,
                     List<Specificity> specificityList,
                     String formula) {
        super(accession, name, description, monoDeltaMass, averageDeltaMass, specificityList, formula);
    }
}
