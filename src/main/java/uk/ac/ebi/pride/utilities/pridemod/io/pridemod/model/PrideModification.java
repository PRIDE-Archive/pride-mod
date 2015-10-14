package uk.ac.ebi.pride.utilities.pridemod.io.pridemod.model;

import uk.ac.ebi.pride.utilities.pridemod.model.Specificity;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for anonymous complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}unimod_mappings"/>
 *         &lt;element ref="{}psi_modifications"/>
 *       &lt;/sequence>
 *       &lt;attribute name="biological_significance" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="diff_mono" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="title" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "unimodMappings",
        "psiModifications"
})
@XmlRootElement(name = "pride_modification")
public class PrideModification
        implements Serializable, PrideModObject {

    private final static long serialVersionUID = 100L;
    @XmlElement(name = "unimod_mappings", required = true)
    protected UnimodMappings unimodMappings;
    @XmlElement(name = "psi_modifications", required = true)
    protected PsiModifications psiModifications;
    @XmlAttribute(name = "biological_significance", required = true)
    protected BigInteger biologicalSignificance;
    @XmlAttribute(name = "diff_mono", required = true)
    protected BigDecimal diffMono;
    @XmlAttribute(required = true)
    protected BigInteger id;
    @XmlAttribute(required = true)
    @XmlSchemaType(name = "anyURI")
    protected String title;


    /**
     * Gets the value of the unimodMappings property.
     *
     * @return possible object is
     *         {@link UnimodMappings }
     */
    public UnimodMappings getUnimodMappings() {
        return unimodMappings;
    }

    /**
     * Sets the value of the unimodMappings property.
     *
     * @param value allowed object is
     *              {@link UnimodMappings }
     */
    public void setUnimodMappings(UnimodMappings value) {
        this.unimodMappings = value;
    }

    /**
     * Gets the value of the psiModifications property.
     *
     * @return possible object is
     *         {@link PsiModifications }
     */
    public PsiModifications getPsiModifications() {
        return psiModifications;
    }

    /**
     * Sets the value of the psiModifications property.
     *
     * @param value allowed object is
     *              {@link PsiModifications }
     */
    public void setPsiModifications(PsiModifications value) {
        this.psiModifications = value;
    }

    /**
     * Gets the value of the biologicalSignificance property.
     *
     * @return possible object is
     *         {@link java.math.BigInteger }
     */
    public BigInteger getBiologicalSignificance() {
        return biologicalSignificance;
    }

    /**
     * Sets the value of the biologicalSignificance property.
     *
     * @param value allowed object is
     *              {@link java.math.BigInteger }
     */
    public void setBiologicalSignificance(BigInteger value) {
        this.biologicalSignificance = value;
    }

    /**
     * Gets the value of the diffMono property.
     *
     * @return possible object is
     *         {@link java.math.BigDecimal }
     */
    public BigDecimal getDiffMono() {
        return diffMono;
    }

    /**
     * Sets the value of the diffMono property.
     *
     * @param value allowed object is
     *              {@link java.math.BigDecimal }
     */
    public void setDiffMono(BigDecimal value) {
        this.diffMono = value;
    }

    /**
     * Gets the value of the id property.
     *
     * @return possible object is
     *         {@link java.math.BigInteger }
     */
    public BigInteger getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     * @param value allowed object is
     *              {@link java.math.BigInteger }
     */
    public void setId(BigInteger value) {
        this.id = value;
    }

    /**
     * Gets the value of the title property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setTitle(String value) {
        this.title = value;
    }

    public boolean compareMono(double mass) {
        return (this.diffMono.doubleValue() == mass);
    }


    public boolean compareId(int id) {
        return (this.getId().intValue() == id) ? true : false;
    }


    public boolean isSpecificity(String specificity) {
        for (int i = 0; i < this.getPsiModifications().getPsiModification().size(); i++) {
            if (this.getPsiModifications().getPsiModification().get(i).getOrigin().compareToIgnoreCase(specificity) == 0)
                return true;
        }
        return false;
    }

    public List<Specificity> getSpecificityList() {
        List<Specificity> specificityList = new ArrayList<Specificity>();

        for (PsiModification psiModification : this.getPsiModifications().getPsiModification()) {
            if (psiModification.generalModification.intValue() != 1) {
                Specificity specificity = new Specificity(psiModification.getOrigin(), psiModification.getTermSpec());
                specificityList.add(specificity);
            }

        }
        return specificityList;
    }

    public String getPsiName() {
        for (PsiModification psiModification : this.getPsiModifications().getPsiModification()) {
            if (psiModification.generalModification.intValue() == 1) {
                return psiModification.getTitle();
            }
        }
        return null;
    }

    /**
     * This function returns the id for a given PRIDE Mod
     * @return
     */
    public String getPsiId() {
        for (PsiModification psiModification : this.getPsiModifications().getPsiModification()) {
            if (psiModification.generalModification.intValue() == 1) {
                return psiModification.getId();
            }
        }
        return null;
    }
}
