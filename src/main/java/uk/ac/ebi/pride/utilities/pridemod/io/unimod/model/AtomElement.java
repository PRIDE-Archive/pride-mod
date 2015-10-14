package uk.ac.ebi.pride.utilities.pridemod.io.unimod.model;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.math.BigDecimal;


/**
 * <p>Java class for elem element declaration.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;element name="elem">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;attribute name="avge_mass" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *         &lt;attribute name="full_name" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *         &lt;attribute name="mono_mass" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *         &lt;attribute name="title" use="required" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" />
 *       &lt;/restriction>
 *     &lt;/complexContent>
 *   &lt;/complexType>
 * &lt;/element>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "elem")
public class AtomElement
        implements Serializable, UnimodObject {

    private final static long serialVersionUID = 100L;
    @XmlAttribute(name = "avge_mass", required = true)
    protected BigDecimal avgeMass;
    @XmlAttribute(name = "full_name", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String fullName;
    @XmlAttribute(name = "mono_mass", required = true)
    protected BigDecimal monoMass;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String title;

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
