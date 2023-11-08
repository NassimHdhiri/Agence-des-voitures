package main;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Scanner;

public class Client {
	
	private String cin;
	private String nom , prenom, adrs ;
	
	public Client(String cin) {
		this.cin =cin;
		this.nom ="";
		this.prenom = "";
		this.adrs = "";
	}
	
	public String getCin() {
		return cin;
	}
	public void setCin(String cin) {
		this.cin = cin;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getAdrs() {
		return adrs;
	}
	public void setAdrs(String adrs) {
		this.adrs = adrs;
	}
	
   
	public void saisi() {
		
		Scanner s1=new Scanner(System.in);
		
		System.out.println(" ");
		System.out.println(" ");
		
		System.out.println("Veuillez entrer Nom de client !");
		this.setNom(s1.next());
		
		System.out.println("Veuillez entrer Prenom de client ");
		this.setPrenom(s1.next());
		
		System.out.println("Veuillez entrer address !");
		this.setAdrs(s1.next());

	}
		
	
	 public  void ajout() throws ParseException, IOException {
	  	     //ouvrir fichier location pour creer
	    	 FileWriter fw=new FileWriter("client.txt",true);
	         PrintWriter pw= new PrintWriter(fw,true);
	    	        	 
	    	 pw.println(this.getCin()+","+this.getNom()+","+this.getPrenom()+","+this.getAdrs()); 	
	    	 pw.close();
	    	 fw.close();
	    }

}
