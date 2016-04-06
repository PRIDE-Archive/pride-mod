package uk.ac.ebi.pride.utilities.pridemod.io.unimod.model;

import javax.xml.bind.annotation.*;
import java.io.Serializable;


/**
 * <p>Java class for xref element declaration.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;element name="xref">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element ref="{http://www.unimod.org/xmlns/schema/unimod_2}text"/>
 *           &lt;element ref="{http://www.unimod.org/xmlns/schema/unimod_2}source"/>
 *           &lt;element ref="{http://www.unimod.org/xmlns/schema/unimod_2}url"/>
 *         &lt;/sequence>
 *       &lt;/restriction>
 *     &lt;/complexContent>
 *   &lt;/complexType>
 * &lt;/element>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "text",
        "source",
        "url"
})
@XmlRootElement(name = "xref")
public class Reference
        implements Serializable, UnimodObject {

    private final static long serialVersionUID = 100L;
    @XmlElement(required = true)
    protected String text;
    @XmlElement(required = true)
    protected String source;
    @XmlElement(required = true)
    @XmlSchemaType(name = "anyURI")
    protected String url;

    /**
     * Gets the value of the text property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the value of the text property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setText(String value) {
        this.text = value;
    }

    /**
     * Gets the value of the source property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getSource() {
        return source;
    }

    /**
     * Sets the value of the source property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSource(String value) {
        this.source = value;
    }

    /**
     * Gets the value of the url property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the value of the url property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setUrl(String value) {
        this.url = value;
    }

}
