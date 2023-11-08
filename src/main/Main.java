package main;

import java.io.*;
import java.text.ParseException;
import java.util.Date;
//import java.text.ParseException;
//import java.util.Date;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws ParseException, IOException {
		
		///////////////////CREATION DU FICHIERS////////////////
	 try {	
		File F = new File("parc.txt");
		if(F.createNewFile()) {
			 System.out.println("File created: " +F.getName());
		}
	 }catch(IOException e) {
		   System.out.println("An error occurred.");
		   e.printStackTrace();
	 }

	 
	 /////////////DECLARATION VARIABLES ET INSTANCES///////////////////
	 
	 try (Scanner s = new Scanner(System.in)) {
		Parc p = new Parc();
		 boolean quitter_mp = false;

			System.out.println(" \n       ------------------    Menu principale   ----------------- \n");
			do {
				 System.out.println("  1 - Gestion du Parc de l'Agence");
				 System.out.println("  2 - Gestion Locations");
				 System.out.println("  3 - Statistiques");
				 System.out.println("  4 - Quitter \n");
				 System.out.println("      TAPEZ VOTRE CHOIX :");
				 int c;
				 	
				 c = s.nextInt();
				 switch(c) {
				 case 1:
					 boolean quitter_gv = false;
					 do {	 
					       System.out.println("\n       ------------------------   Menu    -----------------------\n");	 
					       System.out.println("  1 - Nouvelle acquisition (Ajout d'un vehicule)");
					       System.out.println("  2 - Modification de l'Etat dun Vehicule");
					       System.out.println("  3 - Suppression d'un Vehicule");
					       System.out.println("  4 - Consultation Parc (tous les vehicules)");
					       System.out.println("  5 - Liste des vehicules loues");
					       System.out.println("  6 - Liste des vehicules en cours de reparation");
					       System.out.println("  7 - Liste des vehicules disponibles");
					       System.out.println("  8 - Quitter \n");
					       System.out.println("      TAPEZ VOTRE CHOIX :");
					       int c1 = s.nextInt();
					       switch(c1) {
					       case 1 :
							    String matr,mrq,et="",cons;
							    float prix;
							    int choix;
						    	System.out.println(" --- Matricule: ");
							  	matr = s.next();
							  	System.out.println(" --- Marque: ");
							  	mrq = s.next();
							  	System.out.println(" --- Prix");
							  	prix=s.nextFloat();
							  	System.out.println(" --- Etat :\n ");
							  	System.out.println("  1 - Disponible");
							  	System.out.println("  2 - Loue");
							  	System.out.println("  3 - En cours de reparation");
							  	do {
							  	 choix = s.nextInt();
							  	 if((choix!=1) && (choix!=2) && (choix!=3)) {
									  System.out.println("Choix errone!");
								  }
							  	}while((choix!=1) && (choix!=2) && (choix!=3));
							  	switch(choix) {
							  	case 1 :
							  		et = "Disponible";
							  	break;
							  	case 2 : 
							  		et = "Loue";
							  	break;
							  	case 3 : 
							  		et = "En cours de reparation";
							  	break;
							  	}
							  	System.out.println("Constructeur: ");
							  	cons = s.next();
							  	Vehicule v1 = new Vehicule(mrq,matr,et,prix,cons);
								p.ajout_vehicule(v1);
					       break;
					       case 2 :
								  String matmod,etmod="";
								  int cc;
								  System.out.println(" --- Donner la matricule");
								  matmod = s.next();
								  System.out.println(" --- Le nouveau etat: \n");
								  System.out.println("  1 - Disponible");
								  System.out.println("  2 - Loue"); 
								  System.out.println("  3 - En cours de reparation");
								  	do {
								  	 cc = s.nextInt();
								  	 if((cc!=1) && (cc!=2) && (cc!=3)) {
										  System.out.println("Choix errone!");
									  }
								  	}while((cc!=1) && (cc!=2) && (cc!=3));
								  	switch(cc) {
								  	case 1 :
								  		etmod = "Disponible";
								  	break;
								  	case 2 : 
								  		etmod = "Loue";
								  	break;
								  	case 3 : 
								  		etmod = "En cours de reparation";
								  	break;
								  	}
								  if(p.modif_etat(matmod, etmod)) {
									  System.out.println("\n ETAT EST MODIFIÉ AVEC SUCCÈS!");
								  }else {
									  System.out.println("\n MODIFICATION EST ECHOUE !");
								  }
									  
					       break;
							  case 3:
								  String mat;
								  System.out.println(" --- Tapez la matricule a supprimer:");
								  mat = s.next();
								
								  if(p.supp_vehicule(mat)) {
									  System.out.println("\n VEHICULE EST SUPPRIMEE AVEC SUCCÈS !");
								  }else {
									  System.out.println("\n SUPPRESSION EST ECHOUE !");
								  }
								  
							  break;
							  
							  case 4 : 
								  System.out.println("\n --- Tous les vehicules dans le parc :\n");
								  p.affichage();
							  break;
						
							  case 5:
								  p.vehiculesLoues();
							  break;
							  
							  case 6 :
								  p.vehiculesCR();
							  break;
							  
							  case 7 :
								  p.vehiculesDispo();
							  break;
					       case 8 : 
							 System.out.println("       ------------------    Menu principale   ----------------- \n");
							 quitter_gv = true;
					       break;
					       default: 
						    	System.out.println("choix invalide");
					       }
					  }while(!quitter_gv);
				 break;
				 case 2:
					 boolean quitter_gl = false;
					 do {	 
						  System.out.println("\n       ------------------------   Menu    ----------------------- \n");	 
						  System.out.println("  1 - Nouvelle Location (Ajout d'une location)");
						  System.out.println("  2 - Fin de Location (Suppression)");
						  System.out.println("  3 - Consultation des locations en cours");
						  System.out.println("  4 - Consultation des Vehicules Loues par un client");
						  System.out.println("  5 - Quitter \n");
						  System.out.println("      TAPEZ VOTRE CHOIX :");
						  
					
						  
						  int c2;
						  c2 = s.nextInt();
						 
						  switch(c2) {
						  	case 1 : //create new location 

							LocEnCours L=new LocEnCours();
 
							if(L.ajout_Location())        
							{
								System.out.println("\n   FELICITATION VOTRE RESERVATION EST TERMINÉE AVEC SUCCÈS !");
							}else{
								System.out.println("\n   MALHEUREUSEMENT VOTRE RESERVATION EST ECHOUE");
							}
						                           
							 
							break;
						
						  	case 2 :
						  		LocHistorique LocH=new LocHistorique();
								LocEnCours LC=new LocEnCours();
								
						  		Boolean verif=false;
								String str,removeLine,etat;
								
							
							do {
								System.out.println("  Vous voulez remis la voiture maintenant ? ");
								System.out.println("  tapez << oui >> ou << non >>");		
							    str=s.next();
							}while((!str.equalsIgnoreCase("oui"))&&(!str.equalsIgnoreCase("non")));
								
							
							if(str.equalsIgnoreCase("oui")){
								
								
								do {
									
									System.out.println(" --- Veuillez entrez matricule ");
									LocH.setMatricule(s.next());
									
									//cin
									System.out.println(" --- Veuillez entrez cin ");
									LocH.setCin(s.next());
										
									
									if(LC.found_Line(LocH.getMatricule(),LocH.getCin())==null){
										 System.out.println("Cette location n'existe pas !");
										 verif=true;
									}else {
										verif=false;
									}
									
								}while(verif==true);
								
								
								
								//etat
								
								do {
									
									System.out.println(" --- Veuillez entrez etat de remis");
									System.out.println(" --- tapez << bonne etat >> ou  << en panne >>");
									etat=s.nextLine();
									
								}while((!etat.trim().equalsIgnoreCase("bonne etat"))&&(!etat.trim().equalsIgnoreCase("en panne")));
								
								if(etat.equalsIgnoreCase("bonne etat")) {
									LocH.setEtat("disponible");
								}else
								{
									LocH.setEtat("en cours de reparation");
								}
								
								
								
								// supprimer et retouner le ligne 
								   removeLine=LC.suppr_Loc(LocH.getMatricule(), LocH.getCin());
								
								   //changer l etat dans fichier parc par l etat de remis  
								   LocH.save_Etat_R(LocH.getMatricule(), LocH.getEtat());
								
								   //enregistrer la location dans fichier location historique 
								  
								   LocH.Print(removeLine,etat);
								   
							}else{
								System.out.println("OK au revoir");
							}
								break;
								
						  	case 3 :
						  		 // afficher les location en cours 
								
								FileReader  fr=new FileReader("Location.txt");
								Scanner scan=new Scanner(fr);
								String str_rec;
								String[] str_tab;
								
								while(scan.hasNextLine())
								{    str_rec=scan.nextLine();
								     str_tab=str_rec.split(",");
									 System.out.println("  Matricule = "+str_tab[0]+"   CIN = "+str_tab[1]+"   Date de debut = "+str_tab[2]+
									"   Date de fin = "+str_tab[3]+"   Cout total = "+str_tab[4]+"   Avance = "+str_tab[5]);
								}
								break;
								
						  	case 4 :
						  		System.out.println("Entrez cin ");
								String cin=s.next();
								String[] tab;
								
								FileReader  fr1=new FileReader("Location.txt");
								Scanner scan1=new Scanner(fr1);
								
								
								while(scan1.hasNextLine()) 
								{
									tab=scan1.nextLine().split(",");
									
									if(tab[1].equalsIgnoreCase(cin))
									{
										System.out.println("  Matricule = "+tab[0]);
									}
									 
								}
								
								break;
								
						    case 5:
							  System.out.println("       ------------------    Menu principale   ----------------- \n");
							  quitter_gl = true;
						    break;
						    
						    default: 
						    	System.out.println("choix invalide");
						  }
					 }while(!quitter_gl);
					 
				 break;
				 
				 case 3 :
					 boolean quitter_s = false;
					 do {	 
						  System.out.println("\n       ------------------------   Menu    -----------------------\n");	 
						  System.out.println("   1 - Nombre de locations pour une periode determinee");
						  System.out.println("   2 - Total des revenus pour une periode determinee");
						  System.out.println("   3 - Liste des clients a risque (Retard / mauvais etat de vehicules rendus)");
						  System.out.println("   4 - Quitter \n");
						  System.out.println("TAPEZ VOTRE CHOIX :");
						  int c3 = s.nextInt();
						  switch(c3) {
						     case 1:
						    	   Date d1,d2;
						    	   String str1,str2;
						    	   
						    	   LocHistorique Lh=new LocHistorique() ;
						    	
						    	   System.out.println("    Veuillez entrer 1er date !");
						    	   str1=s.next();
						    	   d1=Lh.StringToDate(str1);
						    	   
						    	   System.out.println("    Veuillez entrer 2e date !");
						    	   str2=s.next();
						    	   d2=Lh.StringToDate(str2);
						    	   
						           
						    	   
						    		System.out.println("    Nombre de locations entre "+str1+" et "+str2+" est "+Lh.nbr_location(d1, d2)+".");
		
						     break ;
						     case 2: 
						       Date d3,d4;
					    	   String str3,str4;
					    	   
					    	   LocHistorique Lh1=new LocHistorique() ;
					    	
					    	   System.out.println("    Veuillez entrer 1er date !");
					    	   str3=s.next();
					    	   d3=Lh1.StringToDate(str3);
					    	   
					    	   System.out.println("    Veuillez entrer 2e date !");
					    	   str4=s.next();
					    	   d4=Lh1.StringToDate(str4);
					    	   
					    	  System.out.println("\n   Total des revenus = "+Lh1.total_revenus(d3, d4));
					    	
						     break;
						     case 3:

						    	  LocHistorique Lh3=new LocHistorique() ;
						    
						    	  Lh3.client_risque2();
						    	  
						     case 4 :
						       System.out.println("       ------------------    Menu principale   ----------------- \n");
							   quitter_s = true;
							   
							 break;
						  }
					 }while(!quitter_s);
				 break;
				 case 4 : 
					 System.out.println("    Au revoir!");
					 quitter_mp = true;
				 break;
				 default: 
					 System.out.println("    choix invalide");
				 }
				
			}while(!quitter_mp);
	}

	 
	
 }

}
