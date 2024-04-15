package EXO1;

import java.io.Serializable;

public class Employee implements Serializable {
	public String nom;
	static String prenom;
	transient final String adresse = "CCC";
	transient static String affiliation;
}
