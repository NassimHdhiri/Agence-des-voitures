package main;

public class Vehicule {
	
	private String marque;
	private String num_mat;
	private String etat;
	private float prix_jr;
	private String constructeur;
	
	
	public Vehicule(String marque , String  num_mat,  String etat,float prix_jr, String constructeur) {
		super();
		this.num_mat = num_mat;
		this.marque = marque;
		this.etat = etat;
		this.constructeur = constructeur;
		this.prix_jr=prix_jr;
	}
	public String getNum_mat() {
		return num_mat;
	}

	public void setNum_mat(String num_mat) {
		this.num_mat = num_mat;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	
	public String getConstructeur() {
		return constructeur;
	}

	public void setConstructeur(String constructeur) {
		this.constructeur = constructeur;
	}
	public float getPrix_jr() {
		return prix_jr;
	}
	public void setPrix_jr(float prix_jr) {
		this.prix_jr = prix_jr;
	}
	
}
