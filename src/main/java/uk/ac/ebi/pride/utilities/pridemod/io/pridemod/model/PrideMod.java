package uk.ac.ebi.pride.utilities.pridemod.io.pridemod.model;

import javax.xml.bind.annotation.*;
import java.io.Serializable;


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
 *         &lt;element ref="{}pride_modifications"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "prideModifications"
})
@XmlRootElement(name = "pride_mod")
public class PrideMod
        implements Serializable, PrideModObject {

    private final static long serialVersionUID = 100L;
    @XmlElement(name = "pride_modifications", required = true)
    protected PrideModifications prideModifications;

    /**
     * Gets the value of the prideModifications property.
     *
     * @return possible object is
     *         {@link PrideModifications }
     */
    public PrideModifications getPrideModifications() {
        return prideModifications;
    }

    /**
     * Sets the value of the prideModifications property.
     *
     * @param value allowed object is
     *              {@link PrideModifications }
     */
    public void setPrideModifications(PrideModifications value) {
        this.prideModifications = value;
    }

}
