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
 *       &lt;attribute name="accession" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "unimod_mapping")
public class UnimodMapping implements Serializable, PrideModObject {

    private final static long serialVersionUID = 100L;
    @XmlAttribute(required = true)
    protected String accession;
    @XmlAttribute(name = "general_modification", required = true)
    protected BigInteger generalModification;


    /**
     * Gets the value of the accession property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getAccession() {
        return accession;
    }

    /**
     * Sets the value of the accession property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setAccession(String value) {
        this.accession = value;
    }

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

}
