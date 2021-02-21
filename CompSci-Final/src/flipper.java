public class flipper {

	public static void main(String[] args) {

		int[][] grid = new int[2][2];
		grid [0][0] = 1;
		grid [0][1] = 2;
		grid [1][0] = 3;
		grid [1][1] = 4;

		String num = IO.inputString ("input change:");
		for (int r = 0; r<num.length(); r++) {
			char newnum = num.charAt(0); 
			if (newnum == 'v') {
				int temp = grid [0][0];
				grid [0][0] = grid [0][1];
				grid [0][1] = temp;

				temp = grid [1][0];
				grid [1][0] = grid [1][1];
				grid [1][1] = temp;

				/*System.out.print(grid[0][0]);
System.out.print(" ");
System.out.println(grid[0][1]);
System.out.print(grid[1][0]);
System.out.print(" ");
System.out.print(grid[1][1]);*/
			}
			else if (newnum == 'h') {

				int temp = grid [0][0];
				grid [0][0] = grid [1][0];
				grid [1][0] = temp;

				temp = grid [0][1];
				grid [0][1] = grid [1][1];
				grid [1][1] = temp;

				/*System.out.print(grid[0][0]);
System.out.print(" ");
System.out.println(grid[0][1]);
System.out.print(grid[1][0]);
System.out.print(" ");
System.out.print(grid[1][1]);*/
			}
			else {

			}
			num = num.substring(1, num.length());
		}

		char newnum =num.charAt(0);
		if (newnum == 'v') {
			int temp = grid [0][0];
			grid [0][0] = grid [0][1];
			grid [0][1] = temp;

			temp = grid [1][0];
			grid [1][0] = grid [1][1];
			grid [1][1] = temp;

			System.out.print(grid[0][0]);
			System.out.print(" ");
			System.out.println(grid[0][1]);
			System.out.print(grid[1][0]);
			System.out.print(" ");
			System.out.print(grid[1][1]);
		}
		else if (newnum == 'h') {

			int temp = grid [0][0];
			grid [0][0] = grid [1][0];
			grid [1][0] = temp;

			temp = grid [0][1];
			grid [0][1] = grid [1][1];
			grid [1][1] = temp;

			System.out.print(grid[0][0]);
			System.out.print(" ");
			System.out.println(grid[0][1]);
			System.out.print(grid[1][0]);
			System.out.print(" ");
			System.out.print(grid[1][1]);
		}
		else {

		}
		/*for(int i = 0; i<2; i++)
{
for(int j = 0; j<2; j++)
{
System.out.print(grid[i][j]);
System.out.print (" ");
}
System.out.println ("");

}*/

	}

}