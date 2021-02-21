import java.io.*;
//ECOO - question 1, 2016 - students passing 
public class Question1 {
public static void main(String args[]) {
BufferedReader in;
PrintWriter out;
try {
in=new BufferedReader(new FileReader("DATA11.txt"));
out= new PrintWriter(new FileWriter("question1.txt"));
for(int z =0 ; z<10; z++) {

String n1 ="";

n1 = in.readLine();
int i = 0;
double a[]= new double [4];

while (n1.indexOf(" ")!=-1)
{
	int sp = n1.indexOf(" ");
	String word = n1.substring (0, sp);
	a[i] = Double.parseDouble(word);
	//System.out.println(a[i]);
	i++;
	
	n1= n1.substring(sp+1, n1.length());
}

a[3] = Double.parseDouble(n1);
//System.out.println(a[3]);
int pass = 0;
String n2 = in.readLine();
double students = Double.parseDouble(n2);
int student= (int) Math.round(students);

 String p[]= new String [student];
for (int x = 0; x < students; x++ )
{
	p[x] = in.readLine();

	int j  = 0;
	double b[]= new double [4];

	while (p[x].indexOf(" ")!=-1)
	{
		int sp = p[x].indexOf(" ");
		String word = p[x].substring (0, sp);
		b[j] = Double.parseDouble(word);
		//System.out.println(b[j]);
		j++;
		
		p[x]= p[x].substring(sp+1, p[x].length());
	}
	b[3] = Double.parseDouble(p[x]);
	//System.out.println(b[3]);
double average = ((a[0]/100)*b[0]) + ((a[1]/100)*b[1]) + ((a[2]/100)*b[2]) + ((a[3]/100)*b[3]);
//System.out.println(average);
if (average >= 50)
	pass++;

}
System.out.println(pass);


out.println();


out.close();
}
} catch (IOException e) {
System.out.println("error");
}
}
}