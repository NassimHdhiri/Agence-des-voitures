package main;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Location {
  
	// attributs 
	  
  private String matricule;
  private String cin ;
  private Date début_location;
  private Date fin_location;
  private float prix_total;
  private float avanceC;
  
  
  
//constructor 
  public Location() {
		this.matricule="";
		this.cin="";
		this.début_location = null;
		this.fin_location = null;
		this.prix_total = 0;
		this.avanceC = 0;
	}
  
//getters and setters 
  public String getMatricule() {
	return matricule;
}
  
public void setMatricule(String matricule) {
	this.matricule = matricule;
}

public String getCin() {
	return cin;
}

public void setCin(String cin) {
	this.cin = cin;
}

public Date getDébut_location() {
	return début_location;
}
public void setDébut_location(Date début_location) {
	this.début_location = début_location;
}
public Date getFin_location() {
	return fin_location;
}
public void setFin_location(Date fin_location) {
	this.fin_location = fin_location;
}
public float getPrix_total() {
	return prix_total;
}
public void setPrix_total(float prix_total) {
	this.prix_total = prix_total;
}
public float getAvanceC() {
	return avanceC;
}
public void setAvanceC(float avanceC) {
	this.avanceC = avanceC;
}


// methods 
// ------------------------------------------------------------------------------------
// 1 - Prix total 
	public static float Prix_total(float prix_jr,long nbr_jrs) {
		return prix_jr*nbr_jrs;
	}
	
// 2- search etat prix 
public float Search_Etat_PrixJr(String matricule,int choix) {
	  
    float value = 0;    
	String str,matF;
	String[] tab;
	
	File file=new File("parc.txt");
	Scanner scan=null ;
	try {
		 scan =new Scanner(file);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 while(scan.hasNextLine()) 
	 {
			str=scan.nextLine();
			tab=str.split(",");
			matF=tab[1];
		   if(matF.equalsIgnoreCase(matricule)) 
		    {
			  if((choix==1)&&(tab[2].equalsIgnoreCase("disponible")))
			   {
				  value= 1; // disponible 
			   }else if(choix==2) 
			    {
				  value= Float.valueOf(tab[3]); // prixJR
			    }	 
		    break;
	        }	
     }
	 scan.close();
	return value;
}

    
//prendre avance

public float Avance() {
		Scanner s=new Scanner(System.in);
		String choix="non";
		float avance=0;
		
		do {
			System.out.println("\n   Pour vous assurer que votre réservation est réussie");
			System.out.println("   il est nécessaire de donner une avance.\n");
			System.out.println(" --- TAPEZ VOTRE AVANCE !");
			avance=s.nextLong();
			
			if(avance<=0) {
				System.out.println("   Si ne veux pas entrer un avance ,tapez « oui » ");
			    System.out.println("  -------- Vous perdrez cet location  si « oui » -------- ");
			    choix=s.next();
			}
			    
		}while((avance<=0.0)&&(choix.equalsIgnoreCase("non")));
		
		//System.out.println(avance);s
		
		return avance;
		}
	
	
//changer etat
	 public  boolean modif_etat(String str) throws IOException {
	 String chaine;
	 String[] tab1=null;
	 String[] tab2=null;
	 
	 File inputFile = new File("parc.txt");
     File tempFile = new File("myTempFile.txt");

     BufferedReader reader = new BufferedReader(new FileReader(inputFile));
     BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
      
     
     String lineToRemove = str;
     String currentLine;

     while((currentLine = reader.readLine()) != null) {
    	 
         // trim newline when comparing with lineToRemove
         //String trimmedLine = currentLine.trim();
      
    	 //extract matricule d une ligne de fichier parc
    	 tab2=currentLine.split(",");
    	 
    	 //comparer matricule entrer avec matricule d une ligne parc
         if(tab2[1].equals(lineToRemove)) {
            
        	 // diviser le ligne eli qrineh ou nhotouh f tableau  
        	 tab1=currentLine.split(",");
          
        	 //changer l'etat 
             tab1[2]="loue";
    		 
             // convertir tab (@@@@) to String ligne
             
             chaine=Arrays.toString(tab1).replaceAll("\\[|\\]", "");
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
	 
	// supprimer une ligne 

	 public  boolean delete_Line(String str) throws IOException {
	 File inputFile = new File("location.txt");
	 File tempFile = new File("myTempFile.txt");

	 BufferedReader reader = new BufferedReader(new FileReader(inputFile));
	 BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

	 String lineToRemove = str;
	 String currentLine;

	 while((currentLine = reader.readLine()) != null) {
	 // trim newline when comparing with lineToRemove
	 String trimmedLine = currentLine.trim();
	 if(trimmedLine.equals(lineToRemove)) continue;
	 writer.write(currentLine + System.getProperty("line.separator"));
	 }
	 writer.close(); 
	 reader.close(); 


	 inputFile.delete();

	 boolean successful = tempFile.renameTo(inputFile);

	 return successful;
	 }


//extraire un ligne specifique 

public  String found_Line(String matricule,String cin) throws IOException {
	 String removeLine = null,str;
	 String[] tab=null;

	 File file =new File("location.txt");
	 Scanner scan =new Scanner(file);

	 while(scan.hasNextLine()) {
	 str=scan.nextLine();
	 tab=str.split(",");
	
	 if(tab[0].equals(matricule)&&(tab[1].equalsIgnoreCase(cin)))
	 {
		removeLine=str;
		break;
	 }
} 
      scan.close();
      return removeLine;
}
	
// determiner date system 
public Date dateSystem() {
	 DateFormat format = new SimpleDateFormat("dd/MM/YYYY");
     //obtenir la date courante
     Date date = new Date();
     return date;
}

// check date 
public   boolean check_date(String date)
 {
     // Définir le format date préféré
	 SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
     format.setLenient(false);
     try
     {
        Date d = format.parse(date); 
     }
     // Date invalide
     catch (ParseException e)
     {
      System.out.println(date+" est une date invalide");
      return false;
     }
    // Renvoie true si la date est valide
    return true;
 }
  

//Converting String to Date

public   Date StringToDate(String str) throws ParseException{
     //Instantiating the SimpleDateFormat class
     SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
     //Parsing the given String to Date object
     Date date = formatter.parse(str);

     return date;
  }

//caculate periode btw two dates
public long Periode_btw_D1_D2(Date date1,Date date2) {
	 
	   // Calucalte time difference in milliseconds   
       long time_difference = date2.getTime() - date1.getTime();  
      // Calucalte time difference in days  
       long days_difference = (time_difference / (1000*60*60*24)) % 365;
  
  return days_difference;
}
   

  
}
