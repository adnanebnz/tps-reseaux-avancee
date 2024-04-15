package EXO4;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class A {
    static Integer var = 1;

    public static void main(String args[]) throws IOException {
        Map<String, Integer> map = new HashMap<>();
        try (Scanner a = new Scanner(new File("./EXO4/symptomes.txt"))) {
            while (a.hasNextLine()) {
                traiter(a.nextLine(), map);
            }
        }
    }

    static void traiter(String ligne, @SuppressWarnings("rawtypes") Map hash) {
        StringTokenizer st = new StringTokenizer(ligne);
        // separer les mots de la ligne
        while (st.hasMoreTokens())
            Ajouter(hash, st.nextToken());
    }

    static void Ajouter(Map map, String mot) {
        Object k = map.get(mot); // le mot existe ou pas encore dans le map ?
        if (k == null)
            map.put(mot, var); // insérer le mot pour la première fois
        else { // le mot existe déja
            int nb = ((Integer) k) + 1;
            map.put(mot, nb); // mettre à jour le nombre de fois
        }
    }
}
