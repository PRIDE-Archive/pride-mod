package uk.ac.ebi.pride.utilities.pridemod.model;

import java.util.List;

/**
 * Interface for PTMs. The PTMs are described by Accession, Name and Delta Mass.
 * yperez.
 */

public interface PTM {

    /**
     * Return the accession of a PTM. All the PTMs will be described
     * by one unique accession. In case of unimod for example is 31 or 35.
     * @return Accession
     */
    String getAccession();

    /**
     * Get the Name of the PTMs
     * @return Name
     */
    String getName();

    /**
     * Returns the delta MonoIsotopic mass of the modification.
     * @return
     */
    Double getMonoDeltaMass();

    /**
     * Returns the delta Average mass of the modification.
     * @return
     */
    Double getAveDeltaMass();

    /**
     * Returns the description of the Modification.
     * @return Description
     */
    String getDescription();

    /**
     * Return the List of AminoAcid Specificity.
     * @return
     */
    List<Specificity> getSpecificityCollection();

    /**
     * Return the Chemical Formula of the modification
     * @return
     */
    String getFormula();


    String getCvLabel();

}
