package uk.ac.ebi.pride.utilities.pridemod.io.pridemod.model;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the uk.ac.ebi.pridemod.model package.
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: uk.ac.ebi.pridemod.model
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PrideMod }
     */
    public PrideMod createPrideMod() {
        return new PrideMod();
    }

    /**
     * Create an instance of {@link PsiModification }
     */
    public PsiModification createPsiModification() {
        return new PsiModification();
    }

    /**
     * Create an instance of {@link UnimodMappings }
     */
    public UnimodMappings createUnimodMappings() {
        return new UnimodMappings();
    }

    /**
     * Create an instance of {@link PrideModifications }
     */
    public PrideModifications createPrideModifications() {
        return new PrideModifications();
    }

    /**
     * Create an instance of {@link PsiModifications }
     */
    public PsiModifications createPsiModifications() {
        return new PsiModifications();
    }

    /**
     * Create an instance of {@link PrideModification }
     */
    public PrideModification createPrideModification() {
        return new PrideModification();
    }

    /**
     * Create an instance of {@link UnimodMapping }
     */
    public UnimodMapping createUnimodMapping() {
        return new UnimodMapping();
    }

}
