package uk.ac.ebi.pride.utilities.pridemod.io.pridemod.model;

import javax.xml.namespace.QName;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * User: yperez
 * Date: 18-Julio-2011
 */
public class ModelConstants {

    public static final String MODEL_PKG = "uk.ac.ebi.pride.utilities.pridemod.io.pridemod.model";
    public static final String PRIDEMOD = "";

    private static Map<Class, QName> modelQNames = new HashMap<Class, QName>();

    static {

        modelQNames.put(UnimodMappings.class, new QName(PRIDEMOD, "unimod_mappings"));
        modelQNames.put(UnimodMapping.class, new QName(PRIDEMOD, "unimod_mapping"));
        modelQNames.put(PsiModifications.class, new QName(PRIDEMOD, "psi_modifications"));
        modelQNames.put(PsiModification.class, new QName(PRIDEMOD, "psi_modification"));
        modelQNames.put(PrideModifications.class, new QName(PRIDEMOD, "pride_modifications"));
        modelQNames.put(PrideModification.class, new QName(PRIDEMOD, "pride_modification"));
        modelQNames.put(PrideMod.class, new QName(PRIDEMOD, "pride_mod"));

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
