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
 *         &lt;element ref="{}pride_modification" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "prideModification"
})
@XmlRootElement(name = "pride_modifications")
public class PrideModifications
        implements Serializable, PrideModObject {

    private final static long serialVersionUID = 100L;
    @XmlElement(name = "pride_modification", required = true)
    protected List<PrideModification> prideModification;

    /**
     * Gets the value of the prideModification property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the prideModification property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPrideModification().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link PrideModification }
     */
    public List<PrideModification> getPrideModification() {
        if (prideModification == null) {
            prideModification = new ArrayList<PrideModification>();
        }
        return this.prideModification;
    }

    public List<PrideModification> getModbyMonoMass(double mass) {
        List<PrideModification> resultModList = new ArrayList<PrideModification>();
        for (int i = 0; i < this.getPrideModification().size(); i++) {
            if (this.getPrideModification().get(i).compareMono(mass)) {
                resultModList.add(this.getPrideModification().get(i));
            }
        }
        return resultModList;
    }

    public PrideModification getModbyId(int id) {
        for (int i = 0; i < this.getPrideModification().size(); i++) {
            if (this.getPrideModification().get(i).compareId(id)) return this.getPrideModification().get(i);
        }
        return null;
    }

    public List<PrideModification> getModListbySpecificity(String specificity) {
        List<PrideModification> resultModList = new ArrayList<PrideModification>();
        for (int i = 0; i < this.getPrideModification().size(); i++) {
            if (this.prideModification.get(i).isSpecificity(specificity)) {
                resultModList.add(this.prideModification.get(i));
            }
        }
        return resultModList;
    }

    public List<PrideModification> getListbyMassSpecificity(String specificity, double mass) {
        List<PrideModification> resultModList = new ArrayList<PrideModification>();
        for (int i = 0; i < this.getPrideModification().size(); i++) {
            if (this.getPrideModification().get(i).compareMono(mass) && this.getPrideModification().get(i).isSpecificity(specificity)) {
                resultModList.add(this.getPrideModification().get(i));
            }
        }
        return resultModList;
    }
}
