package uk.ac.ebi.pride.utilities.pridemod.io.pridemod.model;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
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
 *         &lt;element ref="{}unimod_mapping" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "unimodMapping"
})
@XmlRootElement(name = "unimod_mappings")
public class UnimodMappings
        implements Serializable, PrideModObject {

    private final static long serialVersionUID = 100L;
    @XmlElement(name = "unimod_mapping", required = true)
    protected List<UnimodMapping> unimodMapping;

    /**
     * Gets the value of the unimodMapping property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the unimodMapping property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUnimodMapping().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link UnimodMapping }
     */
    public List<UnimodMapping> getUnimodMapping() {
        if (unimodMapping == null) {
            unimodMapping = new ArrayList<UnimodMapping>();
        }
        return this.unimodMapping;
    }

}
