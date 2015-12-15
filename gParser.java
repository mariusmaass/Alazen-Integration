import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class gParser {

	
//Grundlegend wird immer erst das Format getestet und dann erst geparst.	
	
private static boolean noParse=false; 

private static String Source;
private static String ReferenceName;

private static FileReader InputFile;
private static BufferedReader InputReader;

private static long FileLine=0;
private static long currentLine=0;

public static void main(String[] args){//ist nur zu testzwecken gedacht
String f="";	

try {
InputFile=new FileReader("C:\\test.txt");//hier muss das File sein
} catch (FileNotFoundException e) {

	System.out.println("Kein File dieses Namens oder kein File gefunden");
	// TODO Auto-generated catch block
	e.printStackTrace();
} 
InputReader=new BufferedReader(InputFile);

boolean testPart=testFirstPart();
System.out.println(Source+"   "+ReferenceName+"  "+testPart);

testPart=testSecondPart();
System.out.println(testPart);
testPart=testThirdPart();
System.out.println(testPart);



while(f!="EOF"){
f=getInputPhrase();	
System.out.println(f);
}	
	
}

private static void parse(String Inputfiles){

FileLine=0;
	try {
	InputFile=new FileReader(Inputfiles);
	} catch (FileNotFoundException e) {
	
		System.out.println("Kein File dieses Namens oder kein File gefunden");
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	InputReader=new BufferedReader(InputFile);
boolean partTest=testFirstPart();	

	
	
	
}	

private static String getline(){
String resultString="";

try {
	resultString=InputReader.readLine();
	FileLine++;
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}


return resultString;	
}

private static boolean testPhrase(String phrase){
String StringToMatch=getInputPhrase();

if(phrase.equals(StringToMatch)){
	return true;
	}else{
		System.out.println(phrase+" wurde nicht in vorgesehener Zeile "+currentLine+" gefunden stattdessen: "+StringToMatch+" Structure Error no parsing");
	}

noParse=true;
return false;
}

private static String getInputPhrase(){
String resultString="";	
int next=0;

try {
	while(next!=':'&&next!='\n'&&next!='$'){
		next=InputReader.read();
		
		if(next!=-1){
				if(next=='\n')
				{FileLine++;
				return "EOL";}
				if(next!=':'){
				resultString+=(char)next;}else{return resultString;}	
			}else
			{return "EOF";}
		}
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

return resultString;	
}

private static boolean testFirstPart(){
String EndString;
boolean testresult=true;
currentLine=FileLine;

if(testPhrase("Referenzgenomname")){
	ReferenceName=getline();
	currentLine=FileLine;
}else{testresult=false;}
	
if(testPhrase("Quelle")){
	Source=getline();//txt datei mit check einfügen
	currentLine=FileLine;
}else{testresult=false;}	
	
EndString=getline();
if(EndString.equals("$$")){
	return testresult;	
	
}else{return false;}
	
}

private static boolean testSecondPart(){
String testString;
boolean testresult=true;
currentLine=FileLine;

if(testPhrase("SampelID")){
	testString=getline();
	currentLine=FileLine; //einfügen in eine liste
}else{testresult=false;}
	
if(testPhrase("Genkoordinaten")){
	testString=getline();
	currentLine=FileLine; //check format
}else{testresult=false;}	

if(testPhrase("Mutationssequenz")){
	testString=getline();
	currentLine=FileLine; //check sequenz 
}else{testresult=false;}

//hier wiederholung einbauen
testString=getline();
if(testString.equals("$$")){
	return testresult;	
	
}else{}
	
return testresult;	
}

private static boolean testThirdPart(){
String EndString;
boolean testresult=true;
String testString;
currentLine=FileLine;

if(testPhrase("SampelID")){
testString=getline();	
currentLine=FileLine;// check nun ob sampelID vorhanden
}else{testresult=false;}
	
if(testPhrase("Gender")){
testString=getline();	// nur m/f
currentLine=FileLine;
}else{testresult=false;}	

if(testPhrase("Population")){
testString=getline();	//nur 3 char
currentLine=FileLine;
}else{testresult=false;}


if(testPhrase("Download")){
testString=getline();	//check Format
currentLine=FileLine;
}else{testresult=false;}

	
return testresult;	
}

private static void outputTest(String errorMessage){
	
System.out.println(errorMessage);	
	
}

}
