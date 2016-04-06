package uk.ac.ebi.pride.utilities.pridemod.io.pridemod.model;

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
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "unimod_mapping")
public class UnimodMapping
        implements Serializable, PrideModObject {

    private final static long serialVersionUID = 100L;
    @XmlAttribute(required = true)
    protected BigInteger id;

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

}
