package uk.ac.ebi.pride.utilities.pridemod.io.unimod.model;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for mod element declaration.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;element name="mod">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element ref="{http://www.unimod.org/xmlns/schema/unimod_2}specificity" maxOccurs="unbounded"/>
 *           &lt;element ref="{http://www.unimod.org/xmlns/schema/unimod_2}delta"/>
 *           &lt;choice>
 *             &lt;element ref="{http://www.unimod.org/xmlns/schema/unimod_2}alt_name" maxOccurs="unbounded" minOccurs="0"/>
 *             &lt;element ref="{http://www.unimod.org/xmlns/schema/unimod_2}Ignore" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;/choice>
 *           &lt;element ref="{http://www.unimod.org/xmlns/schema/unimod_2}xref" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element ref="{http://www.unimod.org/xmlns/schema/unimod_2}misc_notes" minOccurs="0"/>
 *         &lt;/sequence>
 *         &lt;attribute name="approved" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *         &lt;attribute name="date_time_modified" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *         &lt;attribute name="date_time_posted" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *         &lt;attribute name="full_name" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *         &lt;attribute name="group_of_poster" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *         &lt;attribute name="record_id" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *         &lt;attribute name="title" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *         &lt;attribute name="username_of_poster" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;/restriction>
 *     &lt;/complexContent>
 *   &lt;/complexType>
 * &lt;/element>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "specificity",
        "delta",
        "altNameOrIgnore",
        "xref",
        "miscNotes"
})
@XmlRootElement(name = "mod")
public class UnimodModification
        implements Serializable, UnimodObject {

    private final static long serialVersionUID = 100L;

    @XmlElement(required = true)
    protected List<Specificity> specificity;

    @XmlElement(required = true)
    protected Delta delta;

    @XmlElements({
            @XmlElement(name = "Ignore", type = Ignore.class),
            @XmlElement(name = "alt_name", type = String.class)
    })
    protected List<Serializable> altNameOrIgnore;

    protected List<Reference> xref;

    @XmlElement(name = "misc_notes")
    protected String miscNotes;

    @XmlAttribute(required = true)
    protected BigInteger approved;

    @XmlAttribute(name = "date_time_modified", required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String dateTimeModified;

    @XmlAttribute(name = "date_time_posted", required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String dateTimePosted;

    @XmlAttribute(name = "full_name", required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String fullName;

    @XmlAttribute(name = "group_of_poster", required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String groupOfPoster;

    @XmlAttribute(name = "record_id", required = true)
    protected BigInteger recordId;

    @XmlAttribute(required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String title;

    @XmlAttribute(name = "username_of_poster", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String usernameOfPoster;


    /**
     * Gets the value of the specificity property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the specificity property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSpecificity().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link Specificity }
     */
    public List<Specificity> getSpecificity() {
        if (specificity == null) {
            specificity = new ArrayList<Specificity>();
        }
        return this.specificity;
    }

    /**
     * Gets the value of the delta property.
     *
     * @return possible object is
     *         {@link Delta }
     */
    public Delta getDelta() {
        return delta;
    }

    /**
     * Sets the value of the delta property.
     *
     * @param value allowed object is
     *              {@link Delta }
     */
    public void setDelta(Delta value) {
        this.delta = value;
    }

    /**
     * Gets the value of the altNameOrIgnore property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the altNameOrIgnore property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAltNameOrIgnore().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link Ignore }
     * {@link String }
     */
    public List<Serializable> getAltNameOrIgnore() {
        if (altNameOrIgnore == null) {
            altNameOrIgnore = new ArrayList<Serializable>();
        }
        return this.altNameOrIgnore;
    }

    /**
     * Gets the value of the xref property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the xref property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getXref().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link Reference }
     */
    public List<Reference> getXref() {
        if (xref == null) {
            xref = new ArrayList<Reference>();
        }
        return this.xref;
    }

    /**
     * Gets the value of the miscNotes property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getMiscNotes() {
        return miscNotes;
    }

    /**
     * Sets the value of the miscNotes property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMiscNotes(String value) {
        this.miscNotes = value;
    }

    /**
     * Gets the value of the approved property.
     *
     * @return possible object is
     *         {@link java.math.BigInteger }
     */
    public BigInteger getApproved() {
        return approved;
    }

    /**
     * Sets the value of the approved property.
     *
     * @param value allowed object is
     *              {@link java.math.BigInteger }
     */
    public void setApproved(BigInteger value) {
        this.approved = value;
    }

    /**
     * Gets the value of the dateTimeModified property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getDateTimeModified() {
        return dateTimeModified;
    }

    /**
     * Sets the value of the dateTimeModified property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDateTimeModified(String value) {
        this.dateTimeModified = value;
    }

    /**
     * Gets the value of the dateTimePosted property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getDateTimePosted() {
        return dateTimePosted;
    }

    /**
     * Sets the value of the dateTimePosted property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDateTimePosted(String value) {
        this.dateTimePosted = value;
    }

    /**
     * Gets the value of the fullName property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets the value of the fullName property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFullName(String value) {
        this.fullName = value;
    }

    /**
     * Gets the value of the groupOfPoster property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getGroupOfPoster() {
        return groupOfPoster;
    }

    /**
     * Sets the value of the groupOfPoster property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setGroupOfPoster(String value) {
        this.groupOfPoster = value;
    }

    /**
     * Gets the value of the recordId property.
     *
     * @return possible object is
     *         {@link java.math.BigInteger }
     */
    public BigInteger getRecordId() {
        return recordId;
    }

    /**
     * Sets the value of the recordId property.
     *
     * @param value allowed object is
     *              {@link java.math.BigInteger }
     */
    public void setRecordId(BigInteger value) {
        this.recordId = value;
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

    /**
     * Gets the value of the usernameOfPoster property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getUsernameOfPoster() {
        return usernameOfPoster;
    }

    /**
     * Sets the value of the usernameOfPoster property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setUsernameOfPoster(String value) {
        this.usernameOfPoster = value;
    }

    public boolean compareAvg(double mass) {
        return (this.delta.avgeMass.doubleValue() == mass);
    }

    public boolean compareId(int id) {
        return (this.recordId.intValue() == id);
    }

    public boolean isSpecificity(String specificity) {
        for (Specificity aSpecificity : this.specificity) {
            if (aSpecificity.getPosition().compareToIgnoreCase(specificity) == 0) return true;
        }
        return false;
    }

}
