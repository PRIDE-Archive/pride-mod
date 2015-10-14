package uk.ac.ebi.pride.utilities.pridemod.io.slimmod.model;

import uk.ac.ebi.pride.utilities.pridemod.model.Specificity;

import java.util.*;

/**
 * User: yperez
 */

/**
 * Class that contains a List and methods to retrieve the information from a List
 * of Slim Modifications.
 */
public class SlimModCollection implements List<SlimModification> {

    //modCollection collection of all slim modification.
    private List<SlimModification> modCollection = null;


    /**
     * Constructor that create a new List of Slim modifications
     */
    public SlimModCollection() {
        this.modCollection = new ArrayList<SlimModification>();
    }

    /**
     * Constructor that create an instance of the SlimModCollection class
     * and initializes the collection of slim modification with the parameter.
     *
     * @param modCollection
     */
    public SlimModCollection(List<SlimModification> modCollection) {
        this.modCollection = modCollection;
    }

    /**
     * This method return an object of SlimCollection with a list of the slim modifications
     * by a given mass and small windows or error between the delta mass of the modifications
     * and the parameter deltaMass.
     *
     * @param deltaMass  the value of the delta mass to filter modifications.
     * @param difference an small error allowed between the mass and the param deltaMass.
     * @return an object SlimModCollection with all of the modification that pass the filter.
     */
    public SlimModCollection getbyDelta(double deltaMass, double difference) {
        SlimModCollection resultSlimCollection = new SlimModCollection();
        for (SlimModification aModCollection : modCollection) {
            if (Math.abs(aModCollection.getDeltaMass() - deltaMass) < difference) {
                resultSlimCollection.add(aModCollection);
            }
        }
        return resultSlimCollection;
    }

    /**
     * This method return an SlimModCollection object, a list of the slim modifications that
     * contain an specific specificity in the list of possible specificities.
     *
     * @param specificity the specificity to looking in the List of specificities in each modification
     * @return an object SlimModCollection with all of the modification that pass the filter.
     */
    public SlimModCollection getbySpecificity(Specificity.AminoAcid specificity) {
        SlimModCollection resultSlimCollection = new SlimModCollection();
        for (SlimModification aModCollection : modCollection) {
            if (aModCollection.isSpecificity(specificity)) {
                resultSlimCollection.add(aModCollection);
            }
        }
        return resultSlimCollection;
    }

    /**
     * This method return an SlimModCollection object, a list of the slim modifications that
     * contain an specific specificity in the list of possible specificities. The parameter
     * used is the string representation  in one character of the amino acids (K or R or H..).
     *
     * @param nameSpecificity String one character representation of Amino Acids.
     * @return an object SlimModCollection with all of the modification that pass the filter.
     */
    public SlimModCollection getbySpecificity(String nameSpecificity) {
        Specificity.AminoAcid specificity = Specificity.parseAminoAcid(nameSpecificity);
        return getbySpecificity(specificity);
    }

    /**
     * This method allow users to obtain the slim modification filtering with a delta mass
     * a possible error (difference) and one specificity in the list of the possible
     * specificities for each modification.
     *
     * @param specificity an specificity  in the form of class Specificity
     * @param mass        the delta mass
     * @param difference  the difference or error between the parameter mass and the delta mas
     *                    of each modification.
     * @return an object SlimModCollection with all of the modification that pass the filter.
     */
    public SlimModCollection getbySpecificity(Specificity.AminoAcid specificity, double mass, double difference) {
        SlimModCollection resultSlimCollection = new SlimModCollection();
        for (SlimModification aModCollection : modCollection) {
            if (aModCollection.isSpecificity(specificity, mass, difference)) {
                resultSlimCollection.add(aModCollection);
            }
        }
        return resultSlimCollection;
    }

    /**
     * This method allow users to obtain the slim modification filtering with a delta mass
     * a possible error (difference) and one specificity in the list of the possible
     * specificities for each modification.
     *
     * @param nameSpecificity an specificity  in the form of String.
     * @param mass            the delta mass
     * @param difference      the difference or error between the parameter mass and the delta mas
     *                        of each modification.
     * @return an object SlimModCollection with all of the modification that pass the filter.
     */
    public SlimModCollection getbySpecificity(String nameSpecificity, double mass, double difference) {
        Specificity.AminoAcid specificity = Specificity.parseAminoAcid(nameSpecificity);
        return getbySpecificity(specificity, mass, difference);
    }

    /**
     * Get the list of slim modifications compare only with the default specificity of
     * each modification.
     *
     * @param name the name of the modification.
     * @return an object SlimModification.
     */
    public SlimModification getbyName(String name) {

        for (SlimModification aModCollection : modCollection) {
            if (aModCollection.getShortNamePsiMod().compareToIgnoreCase(name) == 0) {
                return aModCollection;
            }
        }
        return null;
    }

    /**
     * Add an slim modification to the list of modifications
     *
     * @param mod new modification
     * @return
     */
    public boolean add(SlimModification mod) {
        return this.modCollection.add(mod);
    }

    /**
     * Get the size of the modification List.
     *
     * @return
     */
    public int size() {
        return modCollection.size();
    }

    /**
     * Get if the modification list is Empty.
     *
     * @return
     */
    public boolean isEmpty() {
        return modCollection.isEmpty();
    }

    /**
     * Verified if a Modification exist in the List of the current modifications.
     *
     * @param o generic slim modification object
     * @return
     */
    public boolean contains(Object o) {
        return modCollection.contains(o);
    }

    /**
     * An iterator through the modification List
     *
     * @return
     */
    public Iterator<SlimModification> iterator() {
        return modCollection.iterator();
    }

    /**
     * Convert the current List to an Array of Objects
     *
     * @return
     */
    public Object[] toArray() {
        return modCollection.toArray();
    }

    /**
     * @param a
     * @param <T>
     * @return
     */
    public <T> T[] toArray(T[] a) {
        return modCollection.toArray(a);
    }

    /**
     * Remove an modification passed by parameters from the List of modifications
     *
     * @param o modification
     * @return
     */
    public boolean remove(Object o) {
        return modCollection.remove(o);
    }

    /**
     * Compare if a list of slim modifications are included in the List of
     * modifications
     *
     * @param c a collection of objects
     * @return
     */
    public boolean containsAll(Collection<?> c) {
        return modCollection.containsAll(c);
    }

    /**
     * Add a collection of slim modifications to the current list of slim modifications.
     *
     * @param c collection of slim modifications
     * @return
     */
    public boolean addAll(Collection<? extends SlimModification> c) {
        return modCollection.addAll(c);
    }

    /**
     * Remove a collection of slim modifications from the current List of slim modifications
     *
     * @param c collection of slim modifications
     * @return
     */
    public boolean removeAll(Collection<?> c) {
        return modCollection.removeAll(c);
    }

    /**
     * @param c
     * @return
     */
    public boolean retainAll(Collection<?> c) {
        return modCollection.retainAll(c);
    }

    /**
     * Clear the List of modifications
     */
    public void clear() {
        modCollection.clear();
    }


    /**
     * Add a collection of slim modifications from the index integer
     *
     * @param index an index of the first position to add slim modifications.
     * @param c     a collection of the slim modifications to add to the list.
     * @return
     */
    public boolean addAll(int index, Collection<? extends SlimModification> c) {
        return modCollection.addAll(index, c);
    }

    /**
     * Get a modification in the list.
     *
     * @param index the position of the slim modification to return
     * @return
     */
    public SlimModification get(int index) {
        return modCollection.get(index);
    }

    /**
     * Set slim modification with the value passed by parameter element in the position
     * index of the list.
     *
     * @param index
     * @param element
     * @return
     */
    public SlimModification set(int index, SlimModification element) {
        return modCollection.set(index, element);
    }

    /**
     * Add an Slim Modification in the list in an specific position (index)
     *
     * @param index   position to add the slim modification in the list.
     * @param element an slim modification
     */
    public void add(int index, SlimModification element) {
        modCollection.add(index, element);
    }

    /**
     * Remove an slim object from the list of slim modification by index.
     *
     * @param index
     * @return
     */
    public SlimModification remove(int index) {
        return remove(index);
    }

    /**
     * Index of the Object slim modification in the List of modifications
     *
     * @param o
     * @return
     */
    public int indexOf(Object o) {
        return modCollection.indexOf(o);
    }

    /**
     * Index after the element passed by parameters in the list
     *
     * @param o
     * @return
     */
    public int lastIndexOf(Object o) {
        return modCollection.lastIndexOf(o);
    }

    /**
     * Get List of Iterator of the List of modifications
     *
     * @return
     */
    public ListIterator<SlimModification> listIterator() {
        return modCollection.listIterator();
    }

    /**
     * Get List of Iterator of the List of modifications from specific position
     *
     * @param index
     * @return
     */
    public ListIterator<SlimModification> listIterator(int index) {
        return modCollection.listIterator();
    }

    /**
     * Get a collection of modification between to indexes.
     *
     * @param fromIndex
     * @param toIndex
     * @return
     */
    public List<SlimModification> subList(int fromIndex, int toIndex) {
        List<SlimModification> tmp = modCollection.subList(fromIndex, toIndex);
        return new SlimModCollection(tmp);
    }

}
