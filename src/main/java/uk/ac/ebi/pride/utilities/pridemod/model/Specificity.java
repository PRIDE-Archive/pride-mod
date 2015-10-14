package uk.ac.ebi.pride.utilities.pridemod.model;

/**
 * User: yperez
 * Date: 19/07/11
 */

/**
 * Specificity is a class that contains the information of the aminoacid specificity for
 * modifications and also specified the position of this amino acids (N-term, C-Term) or
 * Anywhere. The class contains inside a two classes to define the position and the amino
 * acid specificity.
 */
public class Specificity implements Comparable {

    private AminoAcid name = null;

    private Position position = Position.NONE;    // the positions could be

    /**
     * Constructor to init the Specificity with aminoacid and position
     *
     * @param aminoacid
     * @param position
     */
    public Specificity(AminoAcid aminoacid, Position position) {
        this.name = aminoacid;
        this.position = position;
    }

    /**
     * Constructor to init the Specificity with aminoacid and position in String form
     *
     * @param aminoacid
     * @param position
     */
    public Specificity(String aminoacid, String position) {
        this.name = parseAminoAcid(aminoacid);
        this.position = parsePositon(position);
    }

    /**
     * Get Position
     *
     * @return
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Set position
     *
     * @param position
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Get Amino Acid Position
     *
     * @return
     */
    public AminoAcid getName() {
        return name;
    }

    /**
     * Set Amino Acid Position
     *
     * @param name
     */
    public void setName(AminoAcid name) {
        this.name = name;
    }

    public int compareTo(Object o) {
        if(o != null)
          return this.getName().compareTo(((Specificity) o).getName());  //To change body of implemented methods use File | Settings | File Templates.
        return -1;
    }

    public enum Position {
        CTERM, NTERM, PCTERM, PNTERM, NONE
    }

    /**
     * To use the same notation of PSI-MOD we decide to include the X as aminoacid. PSI-Mod use X for N-Term position.
     */
    public enum AminoAcid {
        A, R, N, D, C, E, Q, G, H, K, I, L, M, F, P, S, T, W, Y, V, X, NONE

    }

    /**
     * Convert an Amino acids in the one chacter representation to the AminoAcid
     * representation.
     *
     * @param s
     * @return
     */
    public static AminoAcid parseAminoAcid(String s) {
        if (s.compareToIgnoreCase("A") == 0) return AminoAcid.A;
        if (s.compareToIgnoreCase("R") == 0) return AminoAcid.R;
        if (s.compareToIgnoreCase("K") == 0) return AminoAcid.K;
        if (s.compareToIgnoreCase("N") == 0) return AminoAcid.N;
        if (s.compareToIgnoreCase("D") == 0) return AminoAcid.D;
        if (s.compareToIgnoreCase("C") == 0) return AminoAcid.C;
        if (s.compareToIgnoreCase("E") == 0) return AminoAcid.E;
        if (s.compareToIgnoreCase("Q") == 0) return AminoAcid.Q;
        if (s.compareToIgnoreCase("G") == 0) return AminoAcid.G;
        if (s.compareToIgnoreCase("H") == 0) return AminoAcid.H;
        if (s.compareToIgnoreCase("I") == 0) return AminoAcid.I;
        if (s.compareToIgnoreCase("L") == 0) return AminoAcid.L;
        if (s.compareToIgnoreCase("M") == 0) return AminoAcid.M;
        if (s.compareToIgnoreCase("F") == 0) return AminoAcid.F;
        if (s.compareToIgnoreCase("P") == 0) return AminoAcid.P;
        if (s.compareToIgnoreCase("S") == 0) return AminoAcid.S;
        if (s.compareToIgnoreCase("T") == 0) return AminoAcid.T;
        if (s.compareToIgnoreCase("W") == 0) return AminoAcid.W;
        if (s.compareToIgnoreCase("Y") == 0) return AminoAcid.Y;
        if (s.compareToIgnoreCase("V") == 0) return AminoAcid.V;
        if (s.compareToIgnoreCase("X") == 0) return AminoAcid.X;
        return AminoAcid.NONE;
    }

    /**
     * Convert a String to a Position object.
     *
     * @param s
     * @return
     */
    public static Position parsePositon(String s) {
        if (s != null) {
            if (s.compareToIgnoreCase("N-Term") == 0) return Position.NTERM;
            if (s.compareToIgnoreCase("C-Term") == 0) return Position.CTERM;
            if (s.compareToIgnoreCase("Protein N-term") == 0) return Position.PNTERM;
            if (s.compareToIgnoreCase("Protein C-Term") == 0) return Position.PCTERM;
        }
        return Position.NONE;
    }
}
