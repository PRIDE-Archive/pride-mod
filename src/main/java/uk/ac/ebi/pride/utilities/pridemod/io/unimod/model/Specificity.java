package uk.ac.ebi.pride.utilities.pridemod.io.unimod.model;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for specificity element declaration.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;element name="specificity">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element ref="{http://www.unimod.org/xmlns/schema/unimod_2}NeutralLoss" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;choice>
 *             &lt;element ref="{http://www.unimod.org/xmlns/schema/unimod_2}misc_notes"/>
 *             &lt;element ref="{http://www.unimod.org/xmlns/schema/unimod_2}PepNeutralLoss" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;/choice>
 *         &lt;/sequence>
 *         &lt;attribute name="classification" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *         &lt;attribute name="hidden" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *         &lt;attribute name="position" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *         &lt;attribute name="site" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *         &lt;attribute name="spec_group" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;/restriction>
 *     &lt;/complexContent>
 *   &lt;/complexType>
 * &lt;/element>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "neutralLoss",
        "miscNotesOrPepNeutralLoss"
})
@XmlRootElement(name = "specificity")
public class Specificity
        implements Serializable, UnimodObject {

    private final static long serialVersionUID = 100L;
    @XmlElement(name = "NeutralLoss")
    protected List<NeutralLoss> neutralLoss;
    @XmlElements({
            @XmlElement(name = "PepNeutralLoss", type = PepNeutralLoss.class),
            @XmlElement(name = "misc_notes", type = String.class)
    })
    protected List<Serializable> miscNotesOrPepNeutralLoss;
    @XmlAttribute(required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String classification;
    @XmlAttribute(required = true)
    protected BigInteger hidden;
    @XmlAttribute(required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String position;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String site;
    @XmlAttribute(name = "spec_group", required = true)
    protected BigInteger specGroup;

    /**
     * Gets the value of the neutralLoss property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the neutralLoss property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNeutralLoss().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link NeutralLoss }
     */
    public List<NeutralLoss> getNeutralLoss() {
        if (neutralLoss == null) {
            neutralLoss = new ArrayList<NeutralLoss>();
        }
        return this.neutralLoss;
    }

    /**
     * Gets the value of the miscNotesOrPepNeutralLoss property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the miscNotesOrPepNeutralLoss property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMiscNotesOrPepNeutralLoss().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link PepNeutralLoss }
     * {@link String }
     */
    public List<Serializable> getMiscNotesOrPepNeutralLoss() {
        if (miscNotesOrPepNeutralLoss == null) {
            miscNotesOrPepNeutralLoss = new ArrayList<Serializable>();
        }
        return this.miscNotesOrPepNeutralLoss;
    }

    /**
     * Gets the value of the classification property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getClassification() {
        return classification;
    }

    /**
     * Sets the value of the classification property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setClassification(String value) {
        this.classification = value;
    }

    /**
     * Gets the value of the hidden property.
     *
     * @return possible object is
     *         {@link java.math.BigInteger }
     */
    public BigInteger getHidden() {
        return hidden;
    }

    /**
     * Sets the value of the hidden property.
     *
     * @param value allowed object is
     *              {@link java.math.BigInteger }
     */
    public void setHidden(BigInteger value) {
        this.hidden = value;
    }

    /**
     * Gets the value of the position property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getPosition() {
        return position;
    }

    /**
     * Sets the value of the position property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPosition(String value) {
        this.position = value;
    }

    /**
     * Gets the value of the site property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getSite() {
        return site;
    }

    /**
     * Sets the value of the site property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSite(String value) {
        this.site = value;
    }

    /**
     * Gets the value of the specGroup property.
     *
     * @return possible object is
     *         {@link java.math.BigInteger }
     */
    public BigInteger getSpecGroup() {
        return specGroup;
    }

    /**
     * Sets the value of the specGroup property.
     *
     * @param value allowed object is
     *              {@link java.math.BigInteger }
     */
    public void setSpecGroup(BigInteger value) {
        this.specGroup = value;
    }

}
