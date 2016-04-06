package uk.ac.ebi.pride.utilities.pridemod.io.unimod.model;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for PepNeutralLoss element declaration.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;element name="PepNeutralLoss">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element ref="{http://www.unimod.org/xmlns/schema/unimod_2}element" maxOccurs="unbounded"/>
 *         &lt;/sequence>
 *         &lt;attribute name="avge_mass" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *         &lt;attribute name="composition" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *         &lt;attribute name="mono_mass" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *         &lt;attribute name="required" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;/restriction>
 *     &lt;/complexContent>
 *   &lt;/complexType>
 * &lt;/element>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "element"
})
@XmlRootElement(name = "PepNeutralLoss")
public class PepNeutralLoss implements Serializable, UnimodObject {

    private final static long serialVersionUID = 100L;
    @XmlElement(required = true)
    protected List<AtomComposition> element;
    @XmlAttribute(name = "avge_mass", required = true)
    protected BigDecimal avgeMass;
    @XmlAttribute(required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String composition;
    @XmlAttribute(name = "mono_mass", required = true)
    protected BigDecimal monoMass;
    @XmlAttribute(required = true)
    protected boolean required;

    /**
     * Gets the value of the element property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the element property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getElement().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link AtomComposition }
     */
    public List<AtomComposition> getElement() {
        if (element == null) {
            element = new ArrayList<AtomComposition>();
        }
        return this.element;
    }

    /**
     * Gets the value of the avgeMass property.
     *
     * @return possible object is
     *         {@link java.math.BigDecimal }
     */
    public BigDecimal getAvgeMass() {
        return avgeMass;
    }

    /**
     * Sets the value of the avgeMass property.
     *
     * @param value allowed object is
     *              {@link java.math.BigDecimal }
     */
    public void setAvgeMass(BigDecimal value) {
        this.avgeMass = value;
    }

    /**
     * Gets the value of the composition property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getComposition() {
        return composition;
    }

    /**
     * Sets the value of the composition property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setComposition(String value) {
        this.composition = value;
    }

    /**
     * Gets the value of the monoMass property.
     *
     * @return possible object is
     *         {@link java.math.BigDecimal }
     */
    public BigDecimal getMonoMass() {
        return monoMass;
    }

    /**
     * Sets the value of the monoMass property.
     *
     * @param value allowed object is
     *              {@link java.math.BigDecimal }
     */
    public void setMonoMass(BigDecimal value) {
        this.monoMass = value;
    }

    /**
     * Gets the value of the required property.
     */
    public boolean isRequired() {
        return required;
    }

    /**
     * Sets the value of the required property.
     */
    public void setRequired(boolean value) {
        this.required = value;
    }

}
