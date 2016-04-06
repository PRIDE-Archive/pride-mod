package uk.ac.ebi.pride.utilities.pridemod.io.unimod.model;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for modifications element declaration.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;element name="modifications">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element ref="{http://www.unimod.org/xmlns/schema/unimod_2}mod" maxOccurs="unbounded"/>
 *         &lt;/sequence>
 *       &lt;/restriction>
 *     &lt;/complexContent>
 *   &lt;/complexType>
 * &lt;/element>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "mod"
})
@XmlRootElement(name = "modifications")
public class ModificationCollection
        implements Serializable, UnimodObject {

    private final static long serialVersionUID = 100L;
    @XmlElement(required = true)
    protected List<UnimodModification> mod;

    /**
     * Gets the value of the mod property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mod property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMod().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link UnimodModification }
     */
    public List<UnimodModification> getMod() {
        if (mod == null) {
            mod = new ArrayList<UnimodModification>();
        }
        return this.mod;
    }

    public List<UnimodModification> getModbyAvgMass(double mass) {
        List<UnimodModification> resultModList = new ArrayList<UnimodModification>();
        for (UnimodModification aMod : this.mod) {
            if (aMod.compareAvg(mass)) {
                resultModList.add(aMod);
            }
        }
        return resultModList;
    }

    public UnimodModification getModbyId(int id) {
        for (UnimodModification aMod : this.mod) {
            if (aMod.compareId(id)) return aMod;
        }
        return null;
    }


    public List<UnimodModification> getModListbySpecificity(String specificity) {
        List<UnimodModification> resultModList = new ArrayList<UnimodModification>();
        for (UnimodModification aMod : this.mod) {
            if (aMod.isSpecificity(specificity)) {
                resultModList.add(aMod);
            }
        }
        return resultModList;
    }

    public List<UnimodModification> getListbyMassSpecificity(String specificity, double mass) {
        List<UnimodModification> resultModList = new ArrayList<UnimodModification>();
        for (UnimodModification aMod : this.mod) {
            if (aMod.compareAvg(mass) && aMod.isSpecificity(specificity)) {
                resultModList.add(aMod);
            }
        }
        return resultModList;
    }


}
