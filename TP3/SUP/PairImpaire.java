package SUP;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PairImpaire implements Serializable {
    List<Integer> pair = new ArrayList<>();
    List<Integer> impaire = new ArrayList<>();

    public PairImpaire(List<Integer> pair, List<Integer> impaire) {
        this.pair = pair;
        this.impaire = impaire;
    }

    @Override
    public String toString() {
        return "pair = " + pair +
                "\nimpaire = " + impaire;
    }
}
