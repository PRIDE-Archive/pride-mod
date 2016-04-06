package uk.ac.ebi.pride.utilities.pridemod.io.unimod.model;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.math.BigInteger;


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
 *         &lt;element ref="{http://www.unimod.org/xmlns/schema/unimod_2}elements"/>
 *         &lt;element ref="{http://www.unimod.org/xmlns/schema/unimod_2}modifications"/>
 *         &lt;element ref="{http://www.unimod.org/xmlns/schema/unimod_2}amino_acids"/>
 *         &lt;element ref="{http://www.unimod.org/xmlns/schema/unimod_2}mod_bricks"/>
 *       &lt;/sequence>
 *       &lt;attribute name="majorVersion" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="minorVersion" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "elements",
        "modifications",
        "aminoAcids",
        "modBricks"
})
@XmlRootElement(name = "unimod")
public class Unimod
        implements Serializable, UnimodObject {

    private final static long serialVersionUID = 100L;
    @XmlElement(required = true)
    protected ElementCollection elements;
    @XmlElement(required = true)
    protected ModificationCollection modifications;
    @XmlElement(name = "amino_acids", required = true)
    protected AminoAcidCollection aminoAcids;
    @XmlElement(name = "mod_bricks", required = true)
    protected ModBricksCollection modBricks;
    @XmlAttribute(required = true)
    protected BigInteger majorVersion;
    @XmlAttribute(required = true)
    protected BigInteger minorVersion;

    /**
     * Gets the value of the elements property.
     *
     * @return possible object is
     *         {@link ElementCollection }
     */
    public ElementCollection getElements() {
        return elements;
    }

    /**
     * Sets the value of the elements property.
     *
     * @param value allowed object is
     *              {@link ElementCollection }
     */
    public void setElements(ElementCollection value) {
        this.elements = value;
    }

    /**
     * Gets the value of the modifications property.
     *
     * @return possible object is
     *         {@link ModificationCollection }
     */
    public ModificationCollection getModifications() {
        return modifications;
    }

    /**
     * Sets the value of the modifications property.
     *
     * @param value allowed object is
     *              {@link ModificationCollection }
     */
    public void setModifications(ModificationCollection value) {
        this.modifications = value;
    }

    /**
     * Gets the value of the aminoAcids property.
     *
     * @return possible object is
     *         {@link AminoAcidCollection }
     */
    public AminoAcidCollection getAminoAcids() {
        return aminoAcids;
    }

    /**
     * Sets the value of the aminoAcids property.
     *
     * @param value allowed object is
     *              {@link AminoAcidCollection }
     */
    public void setAminoAcids(AminoAcidCollection value) {
        this.aminoAcids = value;
    }

    /**
     * Gets the value of the modBricks property.
     *
     * @return possible object is
     *         {@link ModBricksCollection }
     */
    public ModBricksCollection getModBricks() {
        return modBricks;
    }

    /**
     * Sets the value of the modBricks property.
     *
     * @param value allowed object is
     *              {@link ModBricksCollection }
     */
    public void setModBricks(ModBricksCollection value) {
        this.modBricks = value;
    }

    /**
     * Gets the value of the majorVersion property.
     *
     * @return possible object is
     *         {@link java.math.BigInteger }
     */
    public BigInteger getMajorVersion() {
        return majorVersion;
    }

    /**
     * Sets the value of the majorVersion property.
     *
     * @param value allowed object is
     *              {@link java.math.BigInteger }
     */
    public void setMajorVersion(BigInteger value) {
        this.majorVersion = value;
    }

    /**
     * Gets the value of the minorVersion property.
     *
     * @return possible object is
     *         {@link java.math.BigInteger }
     */
    public BigInteger getMinorVersion() {
        return minorVersion;
    }

    /**
     * Sets the value of the minorVersion property.
     *
     * @param value allowed object is
     *              {@link java.math.BigInteger }
     */
    public void setMinorVersion(BigInteger value) {
        this.minorVersion = value;
    }

}
