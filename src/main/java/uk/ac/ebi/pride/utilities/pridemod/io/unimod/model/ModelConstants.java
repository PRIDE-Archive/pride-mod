package uk.ac.ebi.pride.utilities.pridemod.io.unimod.model;

import javax.xml.namespace.QName;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: yperez
 * Date: 18-Julio-2011
 * Time: 14:17:05
 * To change this template use File | Settings | File Templates.
 */
public class ModelConstants {

    public static final String MODEL_PKG = "uk.ac.ebi.pride.utilities.pridemod.io.unimod.model";
    public static final String UNIMOD = "";

    private static Map<Class, QName> modelQNames = new HashMap<Class, QName>();

    static {

        modelQNames.put(AminoAcid.class, new QName(UNIMOD, "aa"));
        modelQNames.put(AminoAcidCollection.class, new QName(UNIMOD, "amino_acids"));
        modelQNames.put(AtomComposition.class, new QName(UNIMOD, "element"));
        modelQNames.put(AtomElement.class, new QName(UNIMOD, "elem"));
        modelQNames.put(Delta.class, new QName(UNIMOD, "delta"));
        modelQNames.put(ElementCollection.class, new QName(UNIMOD, "elements"));
        modelQNames.put(Ignore.class, new QName(UNIMOD, "Ignore"));
        modelQNames.put(ModBrick.class, new QName(UNIMOD, "brick"));
        modelQNames.put(ModBricksCollection.class, new QName(UNIMOD, "mod_bricks"));
        modelQNames.put(ModificationCollection.class, new QName(UNIMOD, "modifications"));
        modelQNames.put(NeutralLoss.class, new QName(UNIMOD, "NeutralLoss"));
        modelQNames.put(PepNeutralLoss.class, new QName(UNIMOD, "PepNeutralLoss"));
        modelQNames.put(Reference.class, new QName(UNIMOD, "xref"));
        modelQNames.put(Specificity.class, new QName(UNIMOD, "specificity"));
        modelQNames.put(UnimodModification.class, new QName(UNIMOD, "mod"));
        modelQNames.put(Unimod.class, new QName(UNIMOD, "unimod"));

        //now make set unmodifiable
        modelQNames = Collections.unmodifiableMap(modelQNames);

    }


    public static boolean isRegisteredClass(Class cls) {
        return modelQNames.containsKey(cls);
    }

    public static QName getQNameForClass(Class cls) {
        if (isRegisteredClass(cls)) {
            return modelQNames.get(cls);
        } else {
            throw new IllegalStateException("No QName registered for class: " + cls);
        }
    }

    public static String getElementNameForClass(Class cls) {
        if (isRegisteredClass(cls)) {
            return modelQNames.get(cls).getLocalPart();
        } else {
            throw new IllegalStateException("No QName registered for class: " + cls);
        }
    }

    public static Class getClassForElementName(String name) {
        for (Map.Entry<Class, QName> entry : modelQNames.entrySet()) {
            if (entry.getValue().getLocalPart().equals(name)) {
                return entry.getKey();
            }
        }
        return null;
    }


}
