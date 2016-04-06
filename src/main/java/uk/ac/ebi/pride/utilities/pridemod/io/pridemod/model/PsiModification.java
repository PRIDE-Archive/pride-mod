package uk.ac.ebi.pride.utilities.pridemod.io.pridemod.model;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
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
 *       &lt;attribute name="general_modification" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" />
 *       &lt;attribute name="obsolete" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="origin" use="required" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" />
 *       &lt;attribute name="specific_modification" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="term_spec" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="title" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "psi_modification")
public class PsiModification
        implements Serializable, PrideModObject {

    private final static long serialVersionUID = 100L;
    @XmlAttribute(name = "general_modification", required = true)
    protected BigInteger generalModification;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String id;
    @XmlAttribute(required = true)
    protected BigInteger obsolete;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String origin;
    @XmlAttribute(name = "specific_modification", required = true)
    protected BigInteger specificModification;
    @XmlAttribute(name = "term_spec", required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String termSpec;
    @XmlAttribute(required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String title;

    /**
     * Gets the value of the generalModification property.
     *
     * @return possible object is
     *         {@link java.math.BigInteger }
     */
    public BigInteger getGeneralModification() {
        return generalModification;
    }

    /**
     * Sets the value of the generalModification property.
     *
     * @param value allowed object is
     *              {@link java.math.BigInteger }
     */
    public void setGeneralModification(BigInteger value) {
        this.generalModification = value;
    }

    /**
     * Gets the value of the id property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the obsolete property.
     *
     * @return possible object is
     *         {@link java.math.BigInteger }
     */
    public BigInteger getObsolete() {
        return obsolete;
    }

    /**
     * Sets the value of the obsolete property.
     *
     * @param value allowed object is
     *              {@link java.math.BigInteger }
     */
    public void setObsolete(BigInteger value) {
        this.obsolete = value;
    }

    /**
     * Gets the value of the origin property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Sets the value of the origin property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setOrigin(String value) {
        this.origin = value;
    }

    /**
     * Gets the value of the specificModification property.
     *
     * @return possible object is
     *         {@link java.math.BigInteger }
     */
    public BigInteger getSpecificModification() {
        return specificModification;
    }

    /**
     * Sets the value of the specificModification property.
     *
     * @param value allowed object is
     *              {@link java.math.BigInteger }
     */
    public void setSpecificModification(BigInteger value) {
        this.specificModification = value;
    }

    /**
     * Gets the value of the termSpec property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getTermSpec() {
        return termSpec;
    }

    /**
     * Sets the value of the termSpec property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setTermSpec(String value) {
        this.termSpec = value;
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

}
