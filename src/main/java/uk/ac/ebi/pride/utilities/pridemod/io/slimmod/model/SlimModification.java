package uk.ac.ebi.pride.utilities.pridemod.io.slimmod.model;


import uk.ac.ebi.pride.utilities.pridemod.model.Specificity;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yperez
 * Date: 19/07/11
 * Time: 17:12
 * To change this template use File | Settings | File Templates.
 */

/**
 * Slim Modification is an object that contains the information for slim modification.
 * Slim modifications are post-translational modifications obtained with the information
 * from PSI-MOD and Unimod database. The list of slim modifications is a short list of more
 * used post-translational modification in the field of proteomics studies. Each modification
 * contain the delta mass of the modification and the specificity sites of the modification.
 */
public class SlimModification {

    // Identificator
    private String idPsiMod = null;

    private double deltaMass = 0.0;

    private int idUnimod = 0;

    private String psiModDesc = null;

    private String shortNamePsiMod = null;

    private List<Specificity> specificityCollection = null;

    /**
     * Constructor of the Slim Modification Class using as a parameters all attributes
     * of the class.
     *
     * @param idPsiMod              PSI-Mod database identifier.
     * @param deltaMass             delta mass of the PSI-MOD modification
     * @param idUnimod              Unimod database identifier.
     * @param psiModDesc            Name in PsiMod
     * @param shortNamePsiMod       short name in PsI Mod.
     * @param specificityCollection a collection of possible specificities of the current modification.
     */
    public SlimModification(String idPsiMod, double deltaMass, int idUnimod, String psiModDesc, String shortNamePsiMod, List<Specificity> specificityCollection) {
        this.idPsiMod = idPsiMod;
        this.deltaMass = deltaMass;
        this.idUnimod = idUnimod;
        this.psiModDesc = psiModDesc;
        this.shortNamePsiMod = shortNamePsiMod;
        this.specificityCollection = specificityCollection;
    }

    /**
     * Get the PSI-Mod database identifier.
     *
     * @return
     */
    public String getIdPsiMod() {
        return idPsiMod;
    }

    /**
     * Set the PSI-Mod database identifier.
     *
     * @param idPsiMod
     */
    public void setIdPsiMod(String idPsiMod) {
        this.idPsiMod = idPsiMod;
    }

    /**
     * Get the delta Mass of the Modification
     *
     * @return
     */
    public double getDeltaMass() {
        return deltaMass;
    }

    /**
     * Set a delta Mass of the Modification
     *
     * @param deltaMass
     */
    public void setDeltaMass(double deltaMass) {
        this.deltaMass = deltaMass;
    }

    /**
     * Get Unimod database identifier.
     *
     * @return
     */
    public int getIdUnimod() {
        return idUnimod;
    }

    /**
     * Set Unimod database identifier.
     *
     * @param idUnimod
     */
    public void setIdUnimod(int idUnimod) {
        this.idUnimod = idUnimod;
    }

    /**
     * Get Name in PSI-Mod database
     *
     * @return
     */
    public String getPsiModDesc() {
        return psiModDesc;
    }

    /**
     * Set Name in PSI-Mod database
     *
     * @param psiModDesc
     */
    public void setPsiModDesc(String psiModDesc) {
        this.psiModDesc = psiModDesc;
    }

    /**
     * Get short name in PSI-Mod database
     *
     * @return
     */
    public String getShortNamePsiMod() {
        return shortNamePsiMod;
    }

    /**
     * Set short name in PSI-Mod database
     *
     * @param shortNamePsiMod
     */
    public void setShortNamePsiMod(String shortNamePsiMod) {
        this.shortNamePsiMod = shortNamePsiMod;
    }

    /**
     * Get a collection of possible specificities of the current modification.
     *
     * @return
     */
    public List<Specificity> getSpecificityCollection() {
        return specificityCollection;
    }

    /**
     * Set a collection of possible specificities of the current modification.
     *
     * @param specificityCollection
     */
    public void setSpecificityCollection(List<Specificity> specificityCollection) {
        this.specificityCollection = specificityCollection;
    }

    /**
     * Check if a given AminoAcid is part of the specificity of this SlimModification.
     *
     * @param specificity the AminoAcid to check the specificity against.
     * @return true if the provided AminoAcid is part of the specificity of this SlimModification
     */
    public boolean isSpecificity(Specificity.AminoAcid specificity) {
        for (Specificity aSpecificityCollection : specificityCollection) {
            if (aSpecificityCollection.getName() == specificity) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if a given AminoAcid is part of the specificity of this SlimModification,
     * but only perform this check if the delta mass of this SlimModification is with
     * the specified difference of the specified mass.
     *
     * @param specificity the AminoAcid to check the specificity against.
     * @param mass the mass to compare against.
     * @param difference the difference between the specified mass and this SliModification's delta mass.
     * @return true if the provided AminoAcid is part of the specificity of this SlimModification and
     *         the delta mass differs less than the provided difference.
     * @see #isSpecificity(uk.ac.ebi.pride.utilities.pridemod.model.Specificity.AminoAcid)
     */
    public boolean isSpecificity(Specificity.AminoAcid specificity, double mass, double difference) {
        if (Math.abs(this.deltaMass - mass) < difference) {
            for (Specificity aSpecificityCollection : specificityCollection) {
                if (this.isSpecificity(specificity)) {
                    return true;
                }
            }
        }
        return false;
    }
}