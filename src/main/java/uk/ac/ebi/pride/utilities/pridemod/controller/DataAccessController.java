package uk.ac.ebi.pride.utilities.pridemod.controller;

import uk.ac.ebi.pride.utilities.pridemod.model.PTM;
import uk.ac.ebi.pride.utilities.pridemod.model.Specificity;

import java.io.InputStream;
import java.util.List;

/**
 * DataAccessController is an Interface for all the Modification Databases.
 * yperez.
 */
public interface DataAccessController {



    public InputStream getSource();
    /**
     * PTM accession
     * @param accession
     * @return
     */
    public PTM getPTMbyAccession(String accession);

    /**
     * String pattern present in the name.
     * @param namePattern
     * @return
     */
    public List<PTM> getPTMListByPatternName(String namePattern);

    /**
     * Specificity to filter all the identifications in the
     * @param specificity
     * @return
     */
    public List<PTM> getPTMListBySpecificity(Specificity specificity);

    /**
     * Description pattern to found PTMs with the pattern
     * @param descriptionPattern
     * @return
     */
    public List<PTM> getPTMListByPatternDescription(String descriptionPattern);

    /**
     * Return all PTMs with the same name. In case of PSI-Mod modifications different mofifications
     * can have the same name.
     * @param name
     * @return
     */
    public List<PTM> getPTMListByEqualName(String name);

    /**
     * Get List of PTMs by Monoisotopic delta mass
     * @param delta
     * @return
     */
    public List<PTM> getPTMListByMonoDeltaMass(Double delta);

    /**
     * Get List PTMs by Average Delta Mass
     * @param delta
     * @return
     */
    public List<PTM> getPTMListByAvgDeltaMass(Double delta);

}
