package uk.ac.ebi.pride.utilities.pridemod.io.unimod.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the uk.ac.ebi.unimod.model package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Url_QNAME = new QName("http://www.unimod.org/xmlns/schema/unimod_2", "url");
    private final static QName _Text_QNAME = new QName("http://www.unimod.org/xmlns/schema/unimod_2", "text");
    private final static QName _MiscNotes_QNAME = new QName("http://www.unimod.org/xmlns/schema/unimod_2", "misc_notes");
    private final static QName _AltName_QNAME = new QName("http://www.unimod.org/xmlns/schema/unimod_2", "alt_name");
    private final static QName _Source_QNAME = new QName("http://www.unimod.org/xmlns/schema/unimod_2", "source");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: uk.ac.ebi.unimod.model
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ElementCollection }
     */
    public ElementCollection createElementCollection() {
        return new ElementCollection();
    }

    /**
     * Create an instance of {@link Reference }
     */
    public Reference createReference() {
        return new Reference();
    }

    /**
     * Create an instance of {@link Ignore }
     */
    public Ignore createIgnore() {
        return new Ignore();
    }

    /**
     * Create an instance of {@link ModBricksCollection }
     */
    public ModBricksCollection createModBricksCollection() {
        return new ModBricksCollection();
    }

    /**
     * Create an instance of {@link PepNeutralLoss }
     */
    public PepNeutralLoss createPepNeutralLoss() {
        return new PepNeutralLoss();
    }

    /**
     * Create an instance of {@link Specificity }
     */
    public Specificity createSpecificity() {
        return new Specificity();
    }

    /**
     * Create an instance of {@link UnimodModification }
     */
    public UnimodModification createUnimodModification() {
        return new UnimodModification();
    }

    /**
     * Create an instance of {@link ModBrick }
     */
    public ModBrick createModBrick() {
        return new ModBrick();
    }

    /**
     * Create an instance of {@link ModificationCollection }
     */
    public ModificationCollection createModificationCollection() {
        return new ModificationCollection();
    }

    /**
     * Create an instance of {@link AminoAcidCollection }
     */
    public AminoAcidCollection createAminoAcidCollection() {
        return new AminoAcidCollection();
    }

    /**
     * Create an instance of {@link Unimod }
     */
    public Unimod createUnimod() {
        return new Unimod();
    }

    /**
     * Create an instance of {@link AtomElement }
     */
    public AtomElement createAtomElement() {
        return new AtomElement();
    }

    /**
     * Create an instance of {@link AtomComposition }
     */
    public AtomComposition createAtomComposition() {
        return new AtomComposition();
    }

    /**
     * Create an instance of {@link NeutralLoss }
     */
    public NeutralLoss createNeutralLoss() {
        return new NeutralLoss();
    }

    /**
     * Create an instance of {@link AminoAcid }
     */
    public AminoAcid createAminoAcid() {
        return new AminoAcid();
    }

    /**
     * Create an instance of {@link Delta }
     */
    public Delta createDelta() {
        return new Delta();
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}}
     */
    @XmlElementDecl(namespace = "http://www.unimod.org/xmlns/schema/unimod_2", name = "url")
    public JAXBElement<String> createUrl(String value) {
        return new JAXBElement<String>(_Url_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}}
     */
    @XmlElementDecl(namespace = "http://www.unimod.org/xmlns/schema/unimod_2", name = "text")
    public JAXBElement<String> createText(String value) {
        return new JAXBElement<String>(_Text_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}}
     */
    @XmlElementDecl(namespace = "http://www.unimod.org/xmlns/schema/unimod_2", name = "misc_notes")
    public JAXBElement<String> createMiscNotes(String value) {
        return new JAXBElement<String>(_MiscNotes_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}}
     */
    @XmlElementDecl(namespace = "http://www.unimod.org/xmlns/schema/unimod_2", name = "alt_name")
    public JAXBElement<String> createAltName(String value) {
        return new JAXBElement<String>(_AltName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}}
     */
    @XmlElementDecl(namespace = "http://www.unimod.org/xmlns/schema/unimod_2", name = "source")
    public JAXBElement<String> createSource(String value) {
        return new JAXBElement<String>(_Source_QNAME, String.class, null, value);
    }

}
