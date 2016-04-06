package uk.ac.ebi.pride.utilities.pridemod.io.unimod.model;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.math.BigInteger;


/**
 * <p>Java class for element element declaration.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;element name="element">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;attribute name="number" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *         &lt;attribute name="symbol" use="required" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" />
 *       &lt;/restriction>
 *     &lt;/complexContent>
 *   &lt;/complexType>
 * &lt;/element>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "element")
public class AtomComposition
        implements Serializable, UnimodObject {

    private final static long serialVersionUID = 100L;
    @XmlAttribute(required = true)
    protected BigInteger number;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String symbol;

    /**
     * Gets the value of the number property.
     *
     * @return possible object is
     *         {@link java.math.BigInteger }
     */
    public BigInteger getNumber() {
        return number;
    }

    /**
     * Sets the value of the number property.
     *
     * @param value allowed object is
     *              {@link java.math.BigInteger }
     */
    public void setNumber(BigInteger value) {
        this.number = value;
    }

    /**
     * Gets the value of the symbol property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Sets the value of the symbol property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSymbol(String value) {
        this.symbol = value;
    }

}
