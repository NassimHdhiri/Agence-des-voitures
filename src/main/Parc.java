package main;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Parc {
    
	public void affichage() throws FileNotFoundException {
		
		     
		FileReader  fr=new FileReader("parc.txt");
		Scanner scan=new Scanner(fr);
		String str_rec;
		String[] str_tab;
		
		while(scan.hasNextLine())
		{    str_rec=scan.nextLine();
		     str_tab=str_rec.split(",");
			 System.out.println("  Marque = "+str_tab[0]+"   Matricule = "+
		     str_tab[1]+"   Etat = "+str_tab[2]+
			"   Prix UT = "+str_tab[3]+"   Constructeur = "+str_tab[4]);
		}
		
	}
	
	
	public void ajout_vehicule(Vehicule v) throws IOException {
					
		//l'ajout au ficier		
		String etat = v.getEtat();
		String marque = v.getMarque();
		String mat = v.getNum_mat();
		String cons = v.getConstructeur();
		float prix = v.getPrix_jr();
		 //this block is for writing in file
		
		///////////////////////////////////////////////////
		     
			     if(!trouveV(mat)){
			    	//ouvrir fichier location pour creer
			       	 FileWriter fw=new FileWriter("parc.txt",true);
			       	 PrintWriter pw= new PrintWriter(fw,true);
			       	 pw.println(marque+","+mat+","+etat+","+prix+","+cons);
			       	 pw.close();
			       	 fw.close();
			       	 
			       	System.out.println("\n  VEHICULE AJOUTEE AVEC SUCCES!\n");
			     }else {
			    	 System.out.println("\n  VEHICULE EXISTE !\n");
			     }

	}
		
	public boolean supp_vehicule(String mat) throws IOException {
		  String etat="";
		return this.modif_del(mat,etat,2);
	}
	
	public boolean modif_etat(String mat, String etat) throws IOException {
		   return this.modif_del(mat, etat, 1);
	}
	
	public void vehiculesLoues() throws IOException {
		afficher_LRD(1);				    
	}
	
	public void vehiculesCR() throws IOException {
		 
		afficher_LRD(2);
	}
	
	public void vehiculesDispo() throws IOException {	
		afficher_LRD(3);
	}
	
	public boolean trouveV(String mat) throws IOException {
		boolean trouve=false;
		String[] strS;
         
		 File inputFile = new File("parc.txt");
	     BufferedReader reader = new BufferedReader(new FileReader(inputFile));
	     
	     String lineToRemove = mat;
	     String currentLine;

	     while((currentLine = reader.readLine()) != null&&(!trouve)) {
	         // trim newline when comparing with lineToRemove
	         String trimmedLine = currentLine.trim();
	         strS=trimmedLine.split(",");
	         if(strS[1].equals(lineToRemove)) {
	        	 trouve=true;
	         }   
	     }
	     reader.close(); 	
	     
	     return trouve;
	}
	
	// 1 pour modif
	//2 pour del
	
	public boolean modif_del(String mat,String etat,int choix) throws IOException {
		String[] strS;
		boolean successful = false;
			
		if(!trouveV(mat)) {
			System.out.println("\n  VEHICULE N'EST PAS EXISTE!\n");
		}else {
				 File inputFile = new File("parc.txt");
			     File tempFile = new File("myTempFile.txt");

			     BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			     BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

			     String lineToRemove = mat;
			     String currentLine;

			     while((currentLine = reader.readLine()) != null) {
			         // trim newline when comparing with lineToRemove
			         String trimmedLine = currentLine.trim();
			         strS=trimmedLine.split(",");
			         
			         if(choix==1) {
			        	 if(strS[1].equals(lineToRemove)) {
				        	 writer.write(strS[0]+","+strS[1]+","+etat+","+strS[3]+","+strS[4]+ 
				        			 System.getProperty("line.separator"));
				         }else {
				        	 writer.write(currentLine + System.getProperty("line.separator"));
				         }
			         }else if(choix==2) {
			        	 if(strS[1].equals(lineToRemove)) continue;
				         writer.write(currentLine + System.getProperty("line.separator"));
			         }
			     }
			          writer.close(); 
				      reader.close(); 
				      inputFile.delete();
				      successful = tempFile.renameTo(inputFile);
			     }   
		
		return  successful ;
	}
	
	public void afficher_LRD(int choix) throws IOException {
		
		String[] strS;
		String currentLine;
		
		File inputFile = new File("parc.txt");
		BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		
		switch(choix) {
		case 1: System.out.println("\nLISTE DES VEHICULES LOUE : \n");break;
		case 2: System.out.println("\nLISTE DES VEHICULES EN COURS DE REPARATION : \n");break;
		case 3: System.out.println("\nLISTE DES VEHICULES DISPONIBLE : \n");break;
		}
		

		while((currentLine = reader.readLine()) != null) 
		 {         
		 strS=currentLine.split(",");
		
		switch(choix) 
		 {
		   case 1: 
			if(strS[2].equalsIgnoreCase("loue")) 
			{	        	
			System.out.println("  Marque  = "+strS[0]+"  Matricule  = "+strS[1]+"  Etat  = "
			+strS[2]+"  Prix UT  = "+strS[3]+"  Constructeur  = "+strS[4]);
			} ;
			
		   break;
		
		   case 2: 
			if(strS[2].equalsIgnoreCase("en cours de reparation")) 
		      {
			   System.out.println("  Marque  = "+strS[0]+"  Matricule  = "+strS[1]+"  Etat  = "
			   +strS[2]+"  Prix UT  = "+strS[3]+"  Constructeur  = "+strS[4]);
			  }
				
		   break;
		
		   case 3: 
			if(strS[2].equalsIgnoreCase("disponible")) 
		   {       	
		System.out.println("  Marque  = "+strS[0]+"  Matricule  = "+strS[1]+"  Etat  = "
		+strS[2]+"  Prix UT  = "+strS[3]+"  Constructeur  = "+strS[4]);
		   }
		   break;
		}
		
		 }
	     reader.close(); 	
	     
	}

}
