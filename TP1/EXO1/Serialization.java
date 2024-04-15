package EXO1;

import java.io.*;

public class Serialization {
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		Employee emp = new Employee();
		emp.nom = "BENMAMMAR";
		emp.prenom = "BADR";
		emp.affiliation = "UABT";
		try {
			FileOutputStream fileOut = new FileOutputStream("./EXO1/employee.txt");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(emp);
			out.close();
			fileOut.close();
			System.out.printf("Serialisation dans ./EXO1/employee.txt");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}
}
