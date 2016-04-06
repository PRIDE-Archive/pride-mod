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
 *         &lt;element ref="{}psi_modification" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "psiModification"
})
@XmlRootElement(name = "psi_modifications")
public class PsiModifications
        implements Serializable, PrideModObject {

    private final static long serialVersionUID = 100L;
    @XmlElement(name = "psi_modification", required = true)
    protected List<PsiModification> psiModification;

    /**
     * Gets the value of the psiModification property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the psiModification property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPsiModification().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link PsiModification }
     */
    public List<PsiModification> getPsiModification() {
        if (psiModification == null) {
            psiModification = new ArrayList<PsiModification>();
        }
        return this.psiModification;
    }

}
