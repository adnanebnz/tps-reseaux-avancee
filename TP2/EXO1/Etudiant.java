import java.io.Serializable;
public class Etudiant implements Serializable{
  String nom;
  String specialite;
  int moy;
  Etudiant (String nom, String specialite, int moy) {
    this.nom = nom; 
    this.specialite = specialite;
    this.moy = moy;
  }
    String getNom() {
    return nom;
  }
  public String toString() {
    return "Etudiant : "+nom+"   "+specialite+" : "+moy;
  } 
}
