public class IO
{
    static void printSlow (String s)
    {
	for (int i = 0 ; i < s.length () ; i++)
	{
	    System.out.print ("" + s.charAt (i));
	    //sleep for a bit after printing a letter
	    try
	    {
		Thread.sleep (100);
	    }
	    catch (InterruptedException m)
	    {
		;
	    }
	}
	System.out.println ();
    }


    static double round (double num, int digit)
    {
	double num2 = num * Math.pow (10, digit);
	double num3 = (num2 - ((int) num2)) * 10;
	num2 = ((int) num2);
	if (num3 >= 5)
	    num2++;
	return num2 /= Math.pow (10, digit);
    }


    static void printSlow (String s, int t)
    {
	for (int i = 0 ; i < s.length () ; i++)
	{
	    System.out.print ("" + s.charAt (i));
	    //sleep for a bit after printing a letter
	    try
	    {
		Thread.sleep (t);
	    }
	    catch (InterruptedException m)
	    {
		;
	    }
	}
	System.out.println ();
    }


    static void output (String info)
    {
	System.out.println (info);
    }


    static void output (char info)
    {
	System.out.println (info);
    }


    static void output (byte info)
    {
	System.out.println (info);
    }


    static void output (int info)
    {
	System.out.println (info);
    }


    static void output (long info)
    {
	System.out.println (info);
    }


    static void output (double info)
    {
	System.out.println (info);
    }


    static void output (boolean info)
    {
	System.out.println (info);
    }


    static String input (String prompt)
    {
	String inputLine = "";
	System.out.print (prompt);
	try
	{
	    inputLine = (new java.io.BufferedReader (new java.io.InputStreamReader (System.in))).readLine ();
	}
	catch (Exception e)
	{
	    String err = e.toString ();
	    System.out.println (err);
	    inputLine = "";
	}
	return inputLine;
    }


    static String inputString (String prompt)
    {
	return input (prompt);
    }


    static String input ()
    {
	return input ("");
    }


    static int inputInt ()
    {
	return inputInt ("");
    }


    static double inputDouble ()
    {
	return inputDouble ("");
    }


    static char inputChar (String prompt)
    {
	char result = (char) 0;
	try
	{
	    result = input (prompt).charAt (0);
	}
	catch (Exception e)
	{
	    result = (char) 0;
	}
	return result;
    }


    static byte inputByte (String prompt)
    {
	byte result = 0;
	try
	{
	    result = Byte.valueOf (input (prompt).trim ()).byteValue ();
	}
	catch (Exception e)
	{
	    result = 0;
	}
	return result;
    }


    static int inputInt (String prompt)
    {
	int result = 0;
	try
	{
	    result = Integer.valueOf (input (prompt).trim ()).intValue ();
	}
	catch (Exception e)
	{
	    result = 0;
	}
	return result;
    }


    static long inputLong (String prompt)
    {
	long result = 0;
	try
	{
	    result = Long.valueOf (input (prompt).trim ()).longValue ();
	}
	catch (Exception e)
	{
	    result = 0;
	}
	return result;
    }


    static double inputDouble (String prompt)
    {
	double result = 0;
	try
	{
	    result = Double.valueOf (input (prompt).trim ()).doubleValue ();
	}
	catch (Exception e)
	{
	    result = 0;
	}
	return result;
    }


    static boolean inputBoolean (String prompt)
    {
	boolean result = false;
	try
	{
	    result = Boolean.valueOf (input (prompt).trim ()).booleanValue ();
	}
	catch (Exception e)
	{
	    result = false;
	}
	return result;
    }
}


