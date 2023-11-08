package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class LocHistorique extends Location{
	
	
	 private String etat;
	
	
	
      public LocHistorique() 
      {
		super();
		this.etat="";	
	 }

  	public  String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

// verifier si il y retard ou non 
public  boolean v_retard(String removeLine) throws ParseException {
		String[] tab;
		Date date1;
		boolean retard=false;
    	tab=removeLine.split(",");
    	date1=StringToDate(tab[3]);
    	// Annoncer si il y a retard  
    	 if(Periode_btw_D1_D2(date1,dateSystem())>0)
    	 {
    		retard=true;
    	 }
		return retard;
	}
	

//change etat
public  boolean save_Etat_R(String matricule,String etat) throws IOException{
	  	 
	 String chaine;
	 String[] tab1=null;
	 String[] tab2=null;
			 
	 File inputFile = new File("parc.txt");
	 File tempFile = new File("myTempFile.txt");

	 BufferedReader reader = new BufferedReader(new FileReader(inputFile));
	 BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
	          
	         
	  String lineToRemove = matricule;
	  String currentLine;

	  while((currentLine = reader.readLine()) != null) 
	  {
	     // trim newline when comparing with lineToRemove
	       tab2=currentLine.split(",");
	        	 
		 //comparer matricule entrer avec matricule d une ligne parc
		    if(tab2[1].equals(lineToRemove)) {
         
	      //changer l'etat 
	          tab2[2]=etat;
	        		 
	      // convertir tab (@@@@) to String ligne
	           chaine=Arrays.toString(tab2).replaceAll("\\[|\\]", "");
	           chaine=chaine.replaceAll("\\s", "");
	        		 
	       //apres modification ecrire dans le fichier 
	            writer.write(chaine + System.getProperty("line.separator"));	
	            }else {
	                 writer.write(currentLine + System.getProperty("line.separator"));
	             }
	     }
	         writer.close(); 
	         reader.close(); 
	         inputFile.delete();
	         boolean successful = tempFile.renameTo(inputFile);
	         
	        return successful;
	    
	}
	 

// enregistrer les location dans location historique 
public void Print(String removeLine,String etat) throws ParseException, IOException  {
	
	FileWriter fw=new FileWriter("locHistorique.txt",true);
    PrintWriter pw= new PrintWriter(fw,true);
    pw.println(removeLine+","+v_retard(removeLine)+","+etat) ;  		
    pw.close();    	 
  }


// return nombre des location 
public int nbr_location(Date d1, Date d2) throws FileNotFoundException, ParseException {
	String str;
	String[] tab1=null;
	int cpt=0;
	
	File file =new File("locHistorique.txt");
	Scanner scan2 =new Scanner(file);

	while(scan2.hasNextLine()) {
	  str=scan2.nextLine();
	  tab1=str.split(",");	
	  Date d3=super.StringToDate(tab1[2]);
	  Date d4=super.StringToDate(tab1[3]);
	  
	 if((super.Periode_btw_D1_D2(d3,d1)<=0)&&(super.Periode_btw_D1_D2(d4,d2)>=0)) 
	 {cpt+=1;}
	} 
	scan2.close();
	return cpt;
}

//return total des reveuns 
public float total_revenus(Date d1, Date d2) throws FileNotFoundException, ParseException {
	String str;
	String[] tab1=null;
	float sum=0;
	File file =new File("locHistorique.txt");
	Scanner scan2 =new Scanner(file);
	while(scan2.hasNextLine()) {
		str=scan2.nextLine();
		tab1=str.split(",");
	
	  // deux date debut et fin 
	  Date  d3=super.StringToDate(tab1[2]);
	  Date  d4=super.StringToDate(tab1[3]);
		if((super.Periode_btw_D1_D2(d3,d1)<=0)&&(super.Periode_btw_D1_D2(d4,d2)>=0)) 
		{	sum+=Float.valueOf(tab1[4]);}
	} 
	
	scan2.close();
	return sum;
}

public ArrayList  client_risque() throws FileNotFoundException{
	String str;
	String[] tab1=null;
	ArrayList myArray=new ArrayList();
	float sum=0;
	File file =new File("locHistorique.txt");
	Scanner scan2 =new Scanner(file);
	while(scan2.hasNextLine()) {
		str=scan2.nextLine();
		tab1=str.split(",");

	    int t=tab1[6].compareToIgnoreCase("true");
	    int t2=tab1[7].compareToIgnoreCase("en panne");   
		if((t==0)||(t2==0)) {
		   myArray.add(tab1[1]);
		}
	} 
	scan2.close();
	return myArray;
}

public void client_risque2() throws FileNotFoundException {
	    ArrayList myArray=this.client_risque();
	    String str;
		String[] tab1=null;
		File file =new File("client.txt");
		Scanner scan2 =new Scanner(file);
		while(scan2.hasNextLine())
		{
			str=scan2.nextLine();
			tab1=str.split(",");
			
		if(myArray.contains(tab1[0])) {
	  System.out.println(" CIN = "+ tab1[0]+"  NOM = "+ tab1[1]+"  PRENOM = "+ 
		tab1[2] +"  ADDRESS = "+ tab1[3]);
			}
		}
}

}


