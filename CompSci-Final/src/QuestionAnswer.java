public class QuestionAnswer
{
	public static void main (String args[])
	{
		new QuestionAnswer ();
	}
	public QuestionAnswer ()
	{
		String num = IO.inputString ("What is your favourite number? ");
		String breakfast = IO.inputString ("What did you eat for breakfast? ");
		String animal = IO.inputString("What is your favourite animal?");
		String colour = IO.inputString("What is your favouite colour?");

		System.out.println ("Your favourite number is : " + num);
		System.out.println ("");
		System.out.println ("You at this for breakfast: " + breakfast);
		System.out.println ("");
		System.out.println ("Your favourite animal is: " + animal);
		System.out.println ("");
		System.out.println ("Your favourite colour is: " + colour);
		System.out.println ("");
	}
}