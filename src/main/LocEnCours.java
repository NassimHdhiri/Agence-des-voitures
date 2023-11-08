package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class LocEnCours extends Location{
	
// constructor 	
	
	public LocEnCours() 
	{
		super();
	}
   
   
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
 public  boolean ajout_Location() throws ParseException, IOException {
	
		Scanner s=new Scanner(System.in);
		
		String str_date1,str_date2;
		boolean notValid=false;
		boolean verif_ajout=false;
    	long nbr_jrs;
		
		
		System.out.println("  Veuillez entrez numero de carte d'indentité de locataire .");
	    super.setCin(s.next());
	     
		System.out.println("  Veuillez entrez matricule de voiture que vous pouvez loué  (***TN****) ");
		 super.setMatricule(s.next());
		
		//read two dates and verify if valid or not 
   do {
		System.out.println("  Veuillez entrez date de debut (dd/MM/YYYY) ");
		str_date1=s.next();
		
		if(super.check_date(str_date1)) 
		{       super.setDébut_location(super.StringToDate(str_date1));
		  
		}else {
			notValid=true;
		}
		
		//read two dates and verify if valid or not	
 	     System.out.println("  Veuillez entrez date de fin de location (dd/MM/YYYY) ");
		 str_date2=s.next();
		         
			if(super.check_date(str_date2)) 
			{
			  super.setFin_location(super.StringToDate(str_date2));
			 }else{
				   notValid=true;
				   }
					
					
	    //verifier que date 2 superieur que date 1 , sinon resaisi les deux date 
		if(super.Periode_btw_D1_D2(super.getDébut_location(),super.getFin_location())<0) 
		    {
			  System.out.println("\n   IL FAUT DATE FIN SUPERIEUR QUE CELUI DE DEBUT !");
			  notValid=true;
			}
					
	}while(notValid);
			
    	
    	// befor //////////////////////////////////////////////////: befor ///////////
    	
   //verifier si etat  disponible 
    if(Search_Etat_PrixJr(this.getMatricule(),1)==1.0) {
    	 
    			     
    	//calculer nombre de jour entre les deux date 
    	      nbr_jrs=Periode_btw_D1_D2(this.getDébut_location(),this.getFin_location());
    	   
    	// calculer cout total de cette nouveau location 
    	    // 1 - convert prixJr de string en float avec choix 2 pour prix par jour 
    	      float prix_jr=Search_Etat_PrixJr(this.getMatricule(),2);
    	         
    	     // 2- calculer cout total
    	      this.setPrix_total(Prix_total(prix_jr,nbr_jrs));
    	    
    	//demande de l'avance
    	  
    	       System.out.println("\n  LE PRIX DE LOCATION PAR JOUR   : "+prix_jr); 
    	       System.out.println("  LE COUT TOTAL EST  : "+ this.getPrix_total());  
    	        this.setAvanceC(Avance()); 
                 
    	// si exist avance en ajoute cette location dans fichier 
                 if((this.getAvanceC()>0.0)&&(this.getAvanceC()<=this.getPrix_total())) {
                   
                //creation d un client 
                	 Client c=new Client(this.getCin());
         			 c.saisi();
                	 c.ajout();
                	 
    	           //ouvrir fichier location pour creer
    	        	 FileWriter fw=new FileWriter("location.txt",true);
    	        	 PrintWriter pw= new PrintWriter(fw,true);
    	        	 
    	        	 str_date1=dateToString(this.getDébut_location());
    	        	 str_date2=dateToString(this.getFin_location());
    	        	 
    	        	 pw.println(this.getMatricule()+","+this.getCin()+","+str_date1+","+str_date2+","+this.getPrix_total()+","+this.getAvanceC()); 	
    	        	 pw.close();
    	        	 
    	        	 fw.close();
    	        	 //ajout est valide
        	        
    	        	   verif_ajout=true;
    	         }
      
    	}else {
    		System.out.println("\n DESOLE CETTE VOITURE N'EXISTE PAS !");
    	}
    
    	if(verif_ajout) {
    		 modif_etat(this.getMatricule());
    	}
	
    	return verif_ajout;
    }
    
/////////////////////////////////////////////////////////////////////////////////////////////////////////
    
  // supprimer location   

public  String suppr_Loc(String matricule,String cin) throws ParseException, IOException {
            Date   date1,date2;
            String[] tab = null;
            String removeLine = null;
        
          	 removeLine=found_Line(matricule,cin);
	         System.out.println("this line = "+ removeLine);
	         
	    	 try {
	    		 tab=removeLine.split(",");
	    	 }catch(Exception e ){
	    		 System.out.println("\n   CETTE SITUATION N'EST EXISTE PAS!");
	    		 System.exit(0);
	    	 }
	    	 
	    	 date1=StringToDate(tab[3]);
	    	 date2=dateSystem();
	    	  
	    	 // Annoncer si il y a retard 
	    	 if(Periode_btw_D1_D2(date1,date2)>0) {
	    		 System.out.println("\n IL Y A RETARD DE  "+Periode_btw_D1_D2(date1,date2)+" jours");
	    	 }else {
	    		 System.out.println("\n IL N'Y A PAS DE RETARD ! ");
	    	 }
	    	 
	    	 //delete  location
	    	if( delete_Line(removeLine)) {
	    		System.out.println("\n     SUPPRIMER AVEC SUCCES !");
	    	}else {
	    		System.out.println("\n     SUPPRESSION EST ECHOUE  !");
	    	}
	    return removeLine; 
    }


// convert date to string 
public String dateToString(Date date) {
	
    DateFormat format = new SimpleDateFormat("dd/MM/YYYY");
    
    return format.format(date);
}


	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    
}