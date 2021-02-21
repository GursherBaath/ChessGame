//Name: Gursher Baath
//Purpose:To make a functioning playable 2 player chess game
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.Applet;

public class FinalGame extends Applet implements ActionListener
{
	Panel p_card; // to hold all of the screens
	Panel card1, card2, card3, card4, card5; // the two screens
	CardLayout cdLayout = new CardLayout();
	JLabel turnpic; //holds picture that shows who's turn it is
	char turn = 'w';//it is automatically white's turn first
	int last = -1;
	int kingcoverage;//variable holds how many places the king can move
	int kingpositionx;//x position of king
	int kingpositiony;//y position of king
	int checkmatecoverage;//variable holds how many of the king coverage places are in threat from enemy pieces
	
	//castlingcode
	int kingmoved = 0;//variable turns to 1 if the white king moved which is not allowed for castling
	int castle1moved = 0;//variable holds if left white castle moved which is not allowed for castling
	int castle2moved = 0;//variable holds if right white castle moved which is not allowed for castling
	int kingblackmoved = 0;//variable holds if black king moved which is not allowed for castling
	int castle1blackmoved = 0;//variable holds if left black castle moved which is not allowed for castling
	int castle2blackmoved = 0;//variable holds if left black castle moved which is not allowed for castling

	// grid
	int row = 8;
	int col = 8;
	JButton a[] = new JButton[row * col];

	//check substitute array where enemy positions are inputed to see if they match position of king or king coverage
	char checkarray[][] = { { 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u' },
			{ 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u' },
			{ 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u' },
			{ 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u' },
			{ 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u' },
			{ 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u' },
			{ 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u' },
			{ 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u' } };
	//Holds tile colour of chess board
	char tile[][] = { { 'b', 'w', 'b', 'w', 'b', 'w', 'b', 'w' },
			{ 'w', 'b', 'w', 'b', 'w', 'b', 'w', 'b' },
			{ 'b', 'w', 'b', 'w', 'b', 'w', 'b', 'w' },
			{ 'w', 'b', 'w', 'b', 'w', 'b', 'w', 'b' },
			{ 'b', 'w', 'b', 'w', 'b', 'w', 'b', 'w' },
			{ 'w', 'b', 'w', 'b', 'w', 'b', 'w', 'b' },
			{ 'b', 'w', 'b', 'w', 'b', 'w', 'b', 'w' },
			{ 'w', 'b', 'w', 'b', 'w', 'b', 'w', 'b' } };
	// colour of the piece
	char colour[][] = { { 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b' },
			{ 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b' },
			{ 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x' },
			{ 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x' },
			{ 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x' },
			{ 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x' },
			{ 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w' },
			{ 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w' } };
	//holds valid movements for the piece you want to move, and also temporarily used in checkmate to put king coverage in here and compare with checkmate coverage
	char select[][] = { { 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u' },
			{ 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u' },
			{ 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u' },
			{ 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u' },
			{ 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u' },
			{ 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u' },
			{ 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u' },
			{ 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u' } };
	//holds what type of piece is on board
	char piece[][] = { { 'c', 'h', 'b', 'k', 'q', 'b', 'n', 'c' },
			{ 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p' },
			{ 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x' },
			{ 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x' },
			{ 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x' },
			{ 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x' },
			{ 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p' },
			{ 'c', 'h', 'b', 'k', 'q', 'b', 'n', 'c' } };
	//copy of start colour array, and used in reset
	char coloursecond[][] = { { 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b' },
			{ 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b' },
			{ 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x' },
			{ 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x' },
			{ 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x' },
			{ 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x' },
			{ 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w' },
			{ 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w' } };
	//copy of start select array, and used in reset
	char selectsecond[][] = { { 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u' },
			{ 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u' },
			{ 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u' },
			{ 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u' },
			{ 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u' },
			{ 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u' },
			{ 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u' },
			{ 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u' } };
	//copy of start piece array, and used in reset
	char piecesecond[][] = { { 'c', 'h', 'b', 'k', 'q', 'b', 'n', 'c' },
			{ 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p' },
			{ 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x' },
			{ 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x' },
			{ 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x' },
			{ 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x' },
			{ 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p' },
			{ 'c', 'h', 'b', 'k', 'q', 'b', 'n', 'c' } };
	//array holds options that user see's to switch pawn when it reaches other end of the board
	String Pawnoptions [] = {"Queen","Rook","Knight","Bishop"};
	String Promotion;

	public void init()
	{
		p_card = new Panel();
		p_card.setLayout(cdLayout);
		screen1();
		screen2();
		screen3();
		resize(630, 680);
		setLayout(new BorderLayout());
		add("Center", p_card);


	}

	// screen 1 is set up, this is the splash screen
	public void screen1()
	{
		card1 = new Panel();
		//splashscreen image in a button so anywhere can be clicked ot move to screen2
		JButton pic1 = new JButton (createImageIcon("splashscreen.PNG"));
		pic1.setActionCommand("s2");
		pic1.addActionListener(this);
		card1.add(pic1);
		p_card.add("1", card1);

	}

	// screen 2 is set up, this is the instructions screen
	public void screen2()
	{
		card2 = new Panel();
		card2.setBackground(new Color(80,0,0));
		JLabel title = new JLabel("Instructions:");
		title.setForeground (Color.white);
		title.setFont (new Font ("Gloucester MT Extra Condensed", Font.PLAIN, 40));
		JButton next = new JButton("Next");
		next.setFont (new Font ("Gloucester MT Extra Condensed", Font.PLAIN, 40));
		next.setActionCommand("s3");
		next.addActionListener(this);
		JLabel instruction = new JLabel(createImageIcon("instructions.PNG"));

		card2.add(title);
		card2.add(instruction);
		card2.add(next);
		p_card.add("2", card2);
	}


	// screen 3 is set up, this is the game screen
	public void screen3()
	{
		card3 = new Panel();
		card3.setBackground(new Color(80,0,0));
		JLabel title = new JLabel("Turn: ");
		title.setFont (new Font ("Gloucester MT Extra Condensed", Font.PLAIN, 30));
		title.setForeground(Color.white);
		JButton next = new JButton("Back to Instructuions");// Will bring user back to screen 2
		next.setFont (new Font ("Gloucester MT Extra Condensed", Font.PLAIN, 20));
		next.setActionCommand("s2");
		next.addActionListener(this);
		JButton reset = new JButton ("reset");// Button to reset the board
		reset.setFont (new Font ("Gloucester MT Extra Condensed", Font.PLAIN, 20));
		reset.setActionCommand ("rc");
		reset.addActionListener(this);

		// Set up grid
		Panel p = new Panel(new GridLayout(row, col));
		int move = 0;
		turnpic = new JLabel (createImageIcon("wpwu.jpg"));   

		for (int i = 0; i < row; i++)
		{
			for (int j = 0; j < col; j++)
			{
				a[move] = new JButton(createImageIcon(colour[i][j] + "" + piece[i][j] + "" + tile[i][j] + "" + select[i][j] + ".jpg"));                
				a[move].setPreferredSize(new Dimension(70, 70));
				a[move].setMargin(new Insets(0, 0, 0, 0));
				a[move].setBorder(null);
				a[move].addActionListener(this);
				a[move].setActionCommand("" + move);
				p.add(a[move]);
				move++;
			}
		}

		card3.add(title);
		card3.add (turnpic);	
		card3.add(p);
		card3.add(reset);
		card3.add(next);


		p_card.add("3", card3);
	}

	protected static ImageIcon createImageIcon(String path)
	{ 
		java.net.URL imgURL = FinalGame.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
	//redraws game screen
	public void redraw()
	{
		int move = 0;
		for (int i = 0; i < row; i++)
		{
			for (int j = 0; j < col; j++)
			{
				a[move].setIcon( createImageIcon(colour[i][j] + "" + piece[i][j] + "" + tile[i][j] + "" + select[i][j] + ".jpg"));
				move++;
			}
		}
	}
	//method called to reset game board
	public void reset ()
	{

		for (int i = 0 ; i < row ; i++)
		{
			for (int j = 0 ; j < col ; j++)
			{
				colour [i][j] = coloursecond [i][j];
				piece [i][j] = piecesecond [i][j];

				select [i][j] = selectsecond [i][j];
			}	

		}


		redraw ();
	}
	//pawn promotion
	public void promote (int x, int y)
	{
		if (x == 0 && piece [x][y] == 'p' && colour[x][y] == 'w')//if the white pawn reached opposite end of the board
		{
			options (x,y);//call method to change pawn to whichever piece the user chooses
		}
		if (x == 7 && piece [x][y] == 'p' && colour[x][y] == 'b')//if the black pawn reached opposite end of the board
		{
			options (x,y);//call method to change pawn to whichever piece the user chooses
		}
	}
	//method that changes pawn to whichever piece the user chooses 
	public void options (int x, int y)
	{
		redraw();
		Promotion = (String) JOptionPane.showInputDialog (null,
				"You can now select a piece to change your pawn to.", "Promotiontion", JOptionPane.INFORMATION_MESSAGE, null,
				Pawnoptions, Pawnoptions [0]);
		System.out.println(Promotion);
		if (Promotion.equals("Queen"))//if user selects queen 
		{
			piece[x][y] = 'q';//change pawn to queen
		}
		else if (Promotion.equals("Rook"))//if user selects rook
		{
			piece[x][y] = 'c';//change pawn to castle
		}
		else if (Promotion.equals("Knight"))//if user selects knight
		{
			piece[x][y] = 'n';//change pawn to knight
		}
		else if (Promotion.equals("Bishop"))//if user selects bishop
		{
			piece[x][y] = 'b';//change pawn to bishop
		}
	}
	//Method of Pawn movement and attack to select the valid areas it can go
	public void selectPawn (int x, int y) {

		if (colour[x][y] == 'w' && x == 6)//if colour is white and pawn is at starting line allow 2 tile forward jump
		{
			if (colour[x-1][y] == 'x') {
				select[x-1][y] = 's';
				if (colour[x-2][y] == 'x')
					select[x-2][y] = 's';}
		}
		else if (colour[x][y] == 'b' && x == 1)//if colour is black and pawn is at starting line allow 2 tile forward jump
		{
			if (colour[x+1][y] == 'x') {
				select[x+1][y] = 's';
				if (colour[x+2][y] == 'x')
					select[x+2][y] = 's';	}
		}
		else if (colour[x][y] == 'w')//if pawn is white
		{	
			if (colour[x-1][y] == 'x')//if one forward is unoccupied
				select[x-1][y] = 's';//allow pawn to be able to move there
		}
		else if (colour[x][y] == 'b')//if pawn is black
		{
			if (colour[x+1][y] == 'x')//if one forward is unoccupied
				select[x+1][y] = 's';//allow pawn to be able to move there
		}
		//kill code
		if (colour[x][y] == 'w')//if pawn is white
		{	if (y-1 >= 0 && x-1 >= 0)//if diagonal up left exists
		{
			if (colour[x-1][y-1] == 'b')//if diagonal up left is a black piece
				select[x-1][y-1] = 's';//allow pawn to be able to move there
		}
		if (y+1 < col && x-1 >= 0) {//if diagonal up right exists
			if (colour[x-1][y+1] == 'b')//if diagonal up left is a black piece
				select[x-1][y+1] = 's';}//allow pawn to be able to move there
		}
		else if (colour[x][y] == 'b')//if pawn is white
		{
			if (y-1 >= 0 && x+1 < row) {//if down left exists
				if (colour[x+1][y-1] == 'w')//if diagonal down left is a white piece
					select[x+1][y-1] = 's';}//allow pawn to be able to move there
			if (y+1 < col && x-1 < row){//if down right exists
				if (colour[x+1][y+1] == 'w')//if diagonal down right is a white piece
					select[x+1][y+1] = 's';}//allow pawn to be able to move there
		}

	}
	//Method of Castle movement and attack to select the valid areas it can go
	public void selectCastle (int x, int y)
	{
		//up
		int cxup = x-1;

		while (cxup >= 0 && colour[cxup][y] == 'x')
		{
			select[cxup][y] = 's';
			cxup--;
		}
		if (cxup >= 0 && colour[cxup][y] != colour[x][y] && colour[cxup][y]!= 'x' )
		{
			select[cxup][y] = 's';
		}
		//down
		int cxdn = x+1;

		while (cxdn < row && colour[cxdn][y] == 'x')
		{
			select[cxdn][y] = 's';
			cxdn++;
		}
		if (cxdn < row && colour[cxdn][y] != colour[x][y] && colour[cxdn][y]!= 'x' )
		{
			select[cxdn][y] = 's';
		}

		//left
		int cylf = y-1;

		while (cylf >= 0 && colour[x][cylf] == 'x')
		{
			select[x][cylf] = 's';
			cylf--;
		}
		if (cylf >= 0 && colour[x][cylf] != colour[x][y] && colour[x][cylf]!= 'x' )
		{
			select[x][cylf] = 's';
		}
		//right
		int cyrt = y+1;

		while (cyrt < col && colour[x][cyrt] == 'x')
		{
			select[x][cyrt] = 's';
			cyrt++;
		}
		if (cyrt < col && colour[x][cyrt] != colour[x][y] && colour[x][cyrt]!= 'x' )
		{
			select[x][cyrt] = 's';
		}
	}
	//Method of Bishop movement and attack to select the valid areas it can go
	public void selectBishop (int x, int y)
	{
		//upleft
		int bxup = x-1;
		int bylf = y-1;

		while (bxup >= 0 && bylf >= 0 && colour[bxup][bylf] == 'x')
		{
			select[bxup][bylf] = 's';
			bxup--;
			bylf--;
		}
		if (bxup >= 0 && bylf >= 0 && colour[bxup][bylf] != colour[x][y] && colour[bxup][bylf]!= 'x' )
		{
			select[bxup][bylf] = 's';
		}
		//upright
		bxup = x-1;
		int byrt = y+1;

		while (bxup >= 0 && byrt < col && colour[bxup][byrt] == 'x')
		{
			select[bxup][byrt] = 's';
			bxup--;
			byrt++;
		}
		if (bxup >= 0 && byrt < col && colour[bxup][byrt] != colour[x][y] && colour[bxup][byrt]!= 'x' )
		{
			select[bxup][byrt] = 's';
		}
		//downleft
		int bxdn = x+1;
		bylf = y-1;

		while (bxdn < row && bylf >= 0 && colour[bxdn][bylf] == 'x')
		{
			select[bxdn][bylf] = 's';
			bxdn++;
			bylf--;
		}
		if (bxdn < row && bylf >= 0 && colour[bxdn][bylf] != colour[x][y] && colour[bxdn][bylf]!= 'x' )
		{
			select[bxdn][bylf] = 's';
		}
		//downright
		bxdn = x+1;
		byrt = y+1;

		while (bxdn < row && byrt < col && colour[bxdn][byrt] == 'x')
		{
			select[bxdn][byrt] = 's';
			bxdn++;
			byrt++;
		}
		if (bxdn < row && byrt < col && colour[bxdn][byrt] != colour[x][y] && colour[bxdn][byrt]!= 'x' )
		{
			select[bxdn][byrt] = 's';
		}
	}
	//Method of Queen movement and attack to select the valid areas it can go
	public void selectQueen (int x, int y)
	{
		//up
		int qxup = x-1;

		while (qxup >= 0 && colour[qxup][y] == 'x')
		{
			select[qxup][y] = 's';
			qxup--;
		}
		if (qxup >= 0 && colour[qxup][y] != colour[x][y] && colour[qxup][y]!= 'x' )
		{
			select[qxup][y] = 's';
		}
		//down
		int qxdn = x+1;

		while (qxdn < row && colour[qxdn][y] == 'x')
		{
			select[qxdn][y] = 's';
			qxdn++;
		}
		if (qxdn < row && colour[qxdn][y] != colour[x][y] && colour[qxdn][y]!= 'x' )
		{
			select[qxdn][y] = 's';
		}

		//left
		int qylf = y-1;

		while (qylf >= 0 && colour[x][qylf] == 'x')
		{
			select[x][qylf] = 's';
			qylf--;
		}
		if (qylf >= 0 && colour[x][qylf] != colour[x][y] && colour[x][qylf]!= 'x' )
		{
			select[x][qylf] = 's';
		}
		//right
		int qyrt = y+1;

		while (qyrt < col && colour[x][qyrt] == 'x')
		{
			select[x][qyrt] = 's';
			qyrt++;
		}
		if (qyrt < col && colour[x][qyrt] != colour[x][y] && colour[x][qyrt]!= 'x' )
		{
			select[x][qyrt] = 's';
		}
		//upleft
		qxup = x-1;
		qylf = y-1;

		while (qxup >= 0 && qylf >= 0 && colour[qxup][qylf] == 'x')
		{
			select[qxup][qylf] = 's';
			qxup--;
			qylf--;
		}
		if (qxup >= 0 && qylf >= 0 && colour[qxup][qylf] != colour[x][y] && colour[qxup][qylf]!= 'x' )
		{
			select[qxup][qylf] = 's';
		}
		//upright
		qxup = x-1;
		qyrt = y+1;

		while (qxup >= 0 && qyrt < col && colour[qxup][qyrt] == 'x')
		{
			select[qxup][qyrt] = 's';
			qxup--;
			qyrt++;
		}
		if (qxup >= 0 && qyrt < col && colour[qxup][qyrt] != colour[x][y] && colour[qxup][qyrt]!= 'x' )
		{
			select[qxup][qyrt] = 's';
		}
		//downleft
		qxdn = x+1;
		qylf = y-1;

		while (qxdn < row && qylf >= 0 && colour[qxdn][qylf] == 'x')
		{
			select[qxdn][qylf] = 's';
			qxdn++;
			qylf--;
		}
		if (qxdn < row && qylf >= 0 && colour[qxdn][qylf] != colour[x][y] && colour[qxdn][qylf]!= 'x' )
		{
			select[qxdn][qylf] = 's';
		}
		//downright
		qxdn = x+1;
		qyrt = y+1;

		while (qxdn < row && qyrt < col && colour[qxdn][qyrt] == 'x')
		{
			select[qxdn][qyrt] = 's';
			qxdn++;
			qyrt++;
		}
		if (qxdn < row && qyrt < col && colour[qxdn][qyrt] != colour[x][y] && colour[qxdn][qyrt]!= 'x' )
		{
			select[qxdn][qyrt] = 's';
		}
	}
	//Method of King movement and attack to select the valid areas it can go
	public void selectKing (int x, int y)
	{
		kingcoverage = 0;//when checking kingcoverage by calling selectKing, kingcoverage needs to start off as 0
		//up
		int kxup = x-1;

		if (kxup >= 0 && colour[kxup][y] != colour[x][y] && checkarray[kxup][y] != 's')
		{
			select[kxup][y] = 's';
			kingcoverage ++;//one more place the king can move
		}
		//down
		int kxdn = x+1;

		if (kxdn < row && colour[kxdn][y] != colour[x][y] && checkarray[kxdn][y] != 's' )
		{
			select[kxdn][y] = 's';
			kingcoverage++;//one more place the king can move
		}

		//left
		int kylf = y-1;


		if (kylf >= 0 && colour[x][kylf] != colour[x][y] && checkarray[x][kylf] != 's')
		{
			select[x][kylf] = 's';
			kingcoverage++;//one more place the king can move
		}
		//right
		int kyrt = y+1;


		if (kyrt < col && colour[x][kyrt] != colour[x][y] && checkarray[x][kyrt] != 's')
		{
			select[x][kyrt] = 's';
			kingcoverage++;//one more place the king can move
		}
		//upleft
		kxup = x-1;
		kylf = y-1;

		if (kxup >= 0 && kylf >= 0 && colour[kxup][kylf] != colour[x][y] && checkarray[kxup][kylf] != 's')
		{
			select[kxup][kylf] = 's';
			kingcoverage++;//one more place the king can move
		}
		//upright
		kxup = x-1;
		kyrt = y+1;

		if (kxup >= 0 && kyrt < col && colour[kxup][kyrt] != colour[x][y] && checkarray[kxup][kyrt] != 's')
		{
			select[kxup][kyrt] = 's';
			kingcoverage++;//one more place the king can move
		}
		//downleft
		kxdn = x+1;
		kylf = y-1;

		if (kxdn < row && kylf >= 0 && colour[kxdn][kylf] != colour[x][y] && checkarray[kxdn][kylf] != 's')
		{
			select[kxdn][kylf] = 's';
			kingcoverage++;
		}
		//downright
		kxdn = x+1;
		kyrt = y+1;

		if (kxdn < row && kyrt < col && colour[kxdn][kyrt] != colour[x][y] && checkarray[kxdn][kyrt] != 's')
		{
			select[kxdn][kyrt] = 's';
			kingcoverage++;//one more place the king can move
		}
		//code to castle for white king
		if (kingmoved == 0 && castle2moved == 0 && piece[7][6] == 'x' && piece[7][5] == 'x' && piece[7][4] == 'x')//if king did not move, castle involved did not move and spaces in between are unoccupied 
		{
			select[x][y+2] = 's';//allow castling to be able to happen
		}
		if (kingmoved == 0 && castle1moved == 0 && piece[7][1] == 'x' && piece[7][2] == 'x')//if king did not move, castle involved did not move and spaces in between are unoccupied
		{
			select[x][y-2] = 's';//allow castling to be able to happen
		}
		//code to castle for black king
		if (kingblackmoved == 0 && castle2blackmoved == 0 && piece[0][6] == 'x' && piece[0][5] == 'x' && piece[0][4] == 'x')//if king did not move, castle involved did not move and spaces in between are unoccupied
		{
			select[x][y+2] = 's';//allow castling to be able to happen
		}
		if (kingblackmoved == 0 && castle1blackmoved == 0 && piece[0][1] == 'x' && piece[0][2] == 'x')//if king did not move, castle involved did not move and spaces in between are unoccupied
		{
			select[x][y-2] = 's';//allow castling to be able to happen
		}

	}
	//Method of Horse movement and attack to select the valid areas it can go
	public void selectHorse (int x, int y)
	{
		//up,up,left
		int hxup = x-2;
		int hylf = y-1;

		if (hxup >= 0 && hylf >= 0)
		{
			if (colour [hxup][hylf] != colour [x][y])
				select [hxup][hylf] = 's';
		}
		//up,left,left
		hxup = x-1;
		hylf = y-2;

		if (hxup >= 0 && hylf >= 0)
		{
			if (colour [hxup][hylf] != colour [x][y])
				select [hxup][hylf] = 's';
		}
		//up,up,right
		hxup = x-2;
		int hyrt = y+1;

		if (hxup >= 0 && hyrt < col)
		{
			if (colour [hxup][hyrt] != colour [x][y])
				select [hxup][hyrt] = 's';
		}
		//right,right,up
		hxup = x-1;
		hyrt = y+2;

		if (hxup >= 0 && hyrt < col)
		{
			if (colour [hxup][hyrt] != colour [x][y])
				select [hxup][hyrt] = 's';
		}
		//down,left,left
		int hxdn = x+1;
		hylf = y-2;

		if (hxdn < row &&  hylf >= 0)
		{
			if (colour [hxdn][hylf] != colour [x][y])
				select [hxdn][hylf] = 's';
		}
		//down,down,left
		hxdn = x+2;
		hylf = y-1;

		if (hxdn < row &&  hylf >= 0)
		{
			if (colour [hxdn][hylf] != colour [x][y])
				select [hxdn][hylf] = 's';
		}
		//down,down,right
		hxdn = x+2;
		hylf = y+1;

		if (hxdn < row &&  hylf < col)
		{
			if (colour [hxdn][hylf] != colour [x][y])
				select [hxdn][hylf] = 's';
		}
		//down,right,right
		hxdn = x+1;
		hylf = y+2;

		if (hxdn < row &&  hylf < col)
		{
			if (colour [hxdn][hylf] != colour [x][y])
				select [hxdn][hylf] = 's';
		}
	}
	//method that looks if king is in check or checkmate
	public void check ()
	{
		for (int i = 0; i < row; i++ )
		{
			for (int j = 0; j < col; j++)
			{
				select[i][j] = 'u';//unselects select array
			}
		}	
		for (int i = 0; i < row; i++ )
		{
			for (int j = 0; j < col; j++)
			{
				if (piece[i][j] == 'k' && colour[i][j] == turn) {//if piece is king and colour of king is same as who's turn it is
					int x = i;
					int y = j;
					selectKing (x, y);//call method to select places where king can go in select array
					kingpositionx = i;//save x position of king
					kingpositiony = j;//save y position of king
				}
			}
		}
		for (int i = 0; i < row; i++ )
		{
			for (int j = 0; j < col; j++)
			{

				if (colour[i][j] != turn) {//if it is an enemy

					if (piece[i][j]=='p')//if piece is pawn
						checkPawn (i,j);//call method which will put the attack positions of this piece into the check array

					else if (piece[i][j]=='c')//if piece is castle
						checkCastle (i,j);//call method which will put the attack positions of this piece into the check array

					else if (piece[i][j]=='b')//if piece is bishop
						checkBishop (i,j);//call method which will put the attack positions of this piece into the check array

					else if (piece[i][j]=='q')//if piece is queen
						checkQueen (i,j);//call method which will put the attack positions of this piece into the check array

					else if (piece[i][j]=='k')//if piece is king
						checkKing (i,j);//call method which will put the attack positions of this piece into the check array

					else if (piece[i][j]=='h')//if piece is left horse
						checkHorse (i,j);//call method which will put the attack positions of this piece into the check array

					else if (piece[i][j]=='n')//if piece is right horse
						checkHorse (i,j);//call method which will put the attack positions of this piece into the check array
				}
			}
		}
		checkmatecoverage = 0;//to check checkmate coverage must erase checkmate coverage from last time check was called
		for (int i = 0; i < row; i++ )
		{
			for (int j = 0; j < col; j++)
			{
				if (select[i][j] == 's' && checkarray[i][j] == 's')//if enemy attack position is equal to king movement position
				{
					checkmatecoverage++;//places that cover where the king can go and progress to checkmate is increased by 1
				}
			}
		}	
		if (kingcoverage == checkmatecoverage && checkarray[kingpositionx][kingpositiony] == 's' )//if places where king can go is equal to places that enemy attacks cover and the king is in check
		{
			JOptionPane.showMessageDialog (null, "alert message", "CHECKMATE", JOptionPane.INFORMATION_MESSAGE);//Tell user Checkmate
		}
		else if (checkarray[kingpositionx][kingpositiony] == 's' )//if king is in check
		{
			JOptionPane.showMessageDialog (null, "Your king is in check.", "Attention", JOptionPane.INFORMATION_MESSAGE);//Tell user Check
		}
		for (int i = 0; i < row; i++ )//cleans select array again to be sure
		{
			for (int j = 0; j < col; j++)
			{
				select[i][j] = 'u';//make unselected
			}
		}
	}
	//Method that puts places where horse can attack into checkarray
	public void checkHorse (int i, int j)
	{
		//up,up,left
		int hiup = i-2;
		int hjlf = j-1;

		if (hiup >= 0 && hjlf >= 0)
		{
			if (colour [hiup][hjlf] != colour [i][j])
				checkarray [hiup][hjlf] = 's';
		}
		//up,left,left
		hiup = i-1;
		hjlf = j-2;

		if (hiup >= 0 && hjlf >= 0)
		{
			if (colour [hiup][hjlf] != colour [i][j])
				checkarray [hiup][hjlf] = 's';
		}
		//up,up,right
		hiup = i-2;
		int hjrt = j+1;

		if (hiup >= 0 && hjrt < col)
		{
			if (colour [hiup][hjrt] != colour [i][j])
				checkarray [hiup][hjrt] = 's';
		}
		//right,right,up
		hiup = i-1;
		hjrt = j+2;

		if (hiup >= 0 && hjrt < col)
		{
			if (colour [hiup][hjrt] != colour [i][j])
				checkarray [hiup][hjrt] = 's';
		}
		//down,left,left
		int hidn = i+1;
		hjlf = j-2;

		if (hidn < row &&  hjlf >= 0)
		{
			if (colour [hidn][hjlf] != colour [i][j])
				checkarray [hidn][hjlf] = 's';
		}
		//down,down,left
		hidn = i+2;
		hjlf = j-1;

		if (hidn < row &&  hjlf >= 0)
		{
			if (colour [hidn][hjlf] != colour [i][j])
				checkarray [hidn][hjlf] = 's';
		}
		//down,down,right
		hidn = i+2;
		hjlf = j+1;

		if (hidn < row &&  hjlf < col)
		{
			if (colour [hidn][hjlf] != colour [i][j])
				checkarray [hidn][hjlf] = 's';
		}
		//down,right,right
		hidn = i+1;
		hjlf = j+2;

		if (hidn < row &&  hjlf < col)
		{
			if (colour [hidn][hjlf] != colour [i][j])
				checkarray [hidn][hjlf] = 's';
		}
	}
	//Method that puts places where king can attack into checkarray
	public void checkKing (int i, int j)
	{
		//up
		int kiup = i-1;

		if (kiup >= 0 && colour[kiup][j] != colour[i][j] )
		{
			checkarray[kiup][j] = 's';
		}
		//down
		int kidn = i+1;

		if (kidn < row && colour[kidn][j] != colour[i][j] )
		{
			checkarray[kidn][j] = 's';
		}

		//left
		int kjlf = j-1;


		if (kjlf >= 0 && colour[i][kjlf] != colour[i][j] )
		{
			checkarray[i][kjlf] = 's';
		}
		//right
		int kjrt = j+1;


		if (kjrt < col && colour[i][kjrt] != colour[i][j] )
		{
			checkarray[i][kjrt] = 's';
		}
		//upleft
		kiup = i-1;
		kjlf = j-1;

		if (kiup >= 0 && kjlf >= 0 && colour[kiup][kjlf] != colour[i][j] )
		{
			checkarray[kiup][kjlf] = 's';
		}
		//upright
		kiup = i-1;
		kjrt = j+1;

		if (kiup >= 0 && kjrt < col && colour[kiup][kjrt] != colour[i][j] )
		{
			checkarray[kiup][kjrt] = 's';
		}
		//downleft
		kidn = i+1;
		kjlf = j-1;

		if (kidn < row && kjlf >= 0 && colour[kidn][kjlf] != colour[i][j] )
		{
			checkarray[kidn][kjlf] = 's';
		}
		//downright
		kidn = i+1;
		kjrt = j+1;

		if (kidn < row && kjrt < col && colour[kidn][kjrt] != colour[i][j] )
		{
			checkarray[kidn][kjrt] = 's';
		}
	}
	//Method that puts places where queen can attack into checkarray
	public void checkQueen (int i, int j)
	{
		//up
		int qiup = i-1;

		while (qiup >= 0 && colour[qiup][j] == 'x')
		{
			checkarray[qiup][j] = 's';
			qiup--;
		}
		if (qiup >= 0 && colour[qiup][j] != colour[i][j] && colour[qiup][j]!= 'x' )
		{
			checkarray[qiup][j] = 's';
		}
		//down
		int qidn = i+1;

		while (qidn < row && colour[qidn][j] == 'x')
		{
			checkarray[qidn][j] = 's';
			qidn++;
		}
		if (qidn < row && colour[qidn][j] != colour[i][j] && colour[qidn][j]!= 'x' )
		{
			checkarray[qidn][j] = 's';
		}

		//left
		int qjlf = j-1;

		while (qjlf >= 0 && colour[i][qjlf] == 'x')
		{
			checkarray[i][qjlf] = 's';
			qjlf--;
		}
		if (qjlf >= 0 && colour[i][qjlf] != colour[i][j] && colour[i][qjlf]!= 'x' )
		{
			checkarray[i][qjlf] = 's';
		}
		//right
		int qjrt = j+1;

		while (qjrt < col && colour[i][qjrt] == 'x')
		{
			checkarray[i][qjrt] = 's';
			qjrt++;
		}
		if (qjrt < col && colour[i][qjrt] != colour[i][j] && colour[i][qjrt]!= 'x' )
		{
			checkarray[i][qjrt] = 's';
		}
		//upleft
		qiup = i-1;
		qjlf = j-1;

		while (qiup >= 0 && qjlf >= 0 && colour[qiup][qjlf] == 'x')
		{
			checkarray[qiup][qjlf] = 's';
			qiup--;
			qjlf--;
		}
		if (qiup >= 0 && qjlf >= 0 && colour[qiup][qjlf] != colour[i][j] && colour[qiup][qjlf]!= 'x' )
		{
			checkarray[qiup][qjlf] = 's';
		}
		//upright
		qiup = i-1;
		qjrt = j+1;

		while (qiup >= 0 && qjrt < col && colour[qiup][qjrt] == 'x')
		{
			checkarray[qiup][qjrt] = 's';
			qiup--;
			qjrt++;
		}
		if (qiup >= 0 && qjrt < col && colour[qiup][qjrt] != colour[i][j] && colour[qiup][qjrt]!= 'x' )
		{
			checkarray[qiup][qjrt] = 's';
		}
		//downleft
		qidn = i+1;
		qjlf = j-1;

		while (qidn < row && qjlf >= 0 && colour[qidn][qjlf] == 'x')
		{
			checkarray[qidn][qjlf] = 's';
			qidn++;
			qjlf--;
		}
		if (qidn < row && qjlf >= 0 && colour[qidn][qjlf] != colour[i][j] && colour[qidn][qjlf]!= 'x' )
		{
			checkarray[qidn][qjlf] = 's';
		}
		//downright
		qidn = i+1;
		qjrt = j+1;

		while (qidn < row && qjrt < col && colour[qidn][qjrt] == 'x')
		{
			checkarray[qidn][qjrt] = 's';
			qidn++;
			qjrt++;
		}
		if (qidn < row && qjrt < col && colour[qidn][qjrt] != colour[i][j] && colour[qidn][qjrt]!= 'x' )
		{
			checkarray[qidn][qjrt] = 's';
		}
	}
	//Method that puts places where bishop can attack into checkarray
	public void checkBishop (int i, int j)
	{
		//upleft
		int biup = i-1;
		int bjlf = j-1;

		while (biup >= 0 && bjlf >= 0 && colour[biup][bjlf] == 'x')
		{
			checkarray[biup][bjlf] = 's';
			biup--;
			bjlf--;
		}
		if (biup >= 0 && bjlf >= 0 && colour[biup][bjlf] != colour[i][j] && colour[biup][bjlf]!= 'x' )
		{
			checkarray[biup][bjlf] = 's';
		}
		//upright
		biup = i-1;
		int bjrt = j+1;

		while (biup >= 0 && bjrt < col && colour[biup][bjrt] == 'x')
		{
			checkarray[biup][bjrt] = 's';
			biup--;
			bjrt++;
		}
		if (biup >= 0 && bjrt < col && colour[biup][bjrt] != colour[i][j] && colour[biup][bjrt]!= 'x' )
		{
			checkarray[biup][bjrt] = 's';
		}
		//downleft
		int bidn = i+1;
		bjlf = j-1;

		while (bidn < row && bjlf >= 0 && colour[bidn][bjlf] == 'x')
		{
			checkarray[bidn][bjlf] = 's';
			bidn++;
			bjlf--;
		}
		if (bidn < row && bjlf >= 0 && colour[bidn][bjlf] != colour[i][j] && colour[bidn][bjlf]!= 'x' )
		{
			checkarray[bidn][bjlf] = 's';
		}
		//downright
		bidn = i+1;
		bjrt = j+1;

		while (bidn < row && bjrt < col && colour[bidn][bjrt] == 'x')
		{
			checkarray[bidn][bjrt] = 's';
			bidn++;
			bjrt++;
		}
		if (bidn < row && bjrt < col && colour[bidn][bjrt] != colour[i][j] && colour[bidn][bjrt]!= 'x' )
		{
			checkarray[bidn][bjrt] = 's';
		}
	}
	//Method that puts places where castle can attack into checkarray
	public void checkCastle (int i, int j)
	{
		//up
		int ciup = i-1;

		while (ciup >= 0 && colour[ciup][j] == 'x')
		{
			checkarray[ciup][j] = 's';
			ciup--;
		}
		if (ciup >= 0 && colour[ciup][j] != colour[i][j] && colour[ciup][j]!= 'x' )
		{
			checkarray[ciup][j] = 's';
		}
		//down
		int cidn = i+1;

		while (cidn < row && colour[cidn][j] == 'x')
		{
			checkarray[cidn][j] = 's';
			cidn++;
		}
		if (cidn < row && colour[cidn][j] != colour[i][j] && colour[cidn][j]!= 'x' )
		{
			checkarray[cidn][j] = 's';
		}

		//left
		int cjlf = j-1;

		while (cjlf >= 0 && colour[i][cjlf] == 'x')
		{
			checkarray[i][cjlf] = 's';
			cjlf--;
		}
		if (cjlf >= 0 && colour[i][cjlf] != colour[i][j] && colour[i][cjlf]!= 'x' )
		{
			checkarray[i][cjlf] = 's';
		}
		//right
		int cjrt = j+1;

		while (cjrt < col && colour[i][cjrt] == 'x')
		{
			checkarray[i][cjrt] = 's';
			cjrt++;
		}
		if (cjrt < col && colour[i][cjrt] != colour[i][j] && colour[i][cjrt]!= 'x' )
		{
			checkarray[i][cjrt] = 's';
		}
	}
	//Method that puts places where pawn can attack into checkarray
	public void checkPawn (int i, int j)
	{
		if (colour[i][j] == 'w')
		{	if (j-1 >= 0 && i-1 >= 0)
		{
			if (colour[i-1][j-1] != 'w')
				checkarray[i-1][j-1] = 's';
		}
		if (j+1 < col && i-1 >= 0) {
			if (colour[i-1][j+1] != 'w')
				checkarray[i-1][j+1] = 's';}
		}
		else if (colour[i][j] == 'b')
		{
			if (j-1 >= 0 && i+1 < row) {
				if (colour[i+1][j-1] != 'b')
					checkarray[i+1][j-1] = 's';}
			if (j+1 < col && i+
					1 < row){
				if (colour[i+1][j+1] != 'b')
					checkarray[i+1][j+1] = 's';}
		}
	}





	public void actionPerformed(ActionEvent e)
	{
		// moves between the screens
		if (e.getActionCommand().equals("s1"))
			cdLayout.show(p_card, "1");
		else if (e.getActionCommand().equals("s2"))
			cdLayout.show(p_card, "2");
		else if (e.getActionCommand().equals("s3"))
			cdLayout.show(p_card, "3");
		else if (e.getActionCommand().equals("s4"))
			cdLayout.show(p_card, "4");
		else if (e.getActionCommand().equals("s5"))
			cdLayout.show(p_card, "5");
		else if (e.getActionCommand().equals("s6"))
			System.exit(0);
		else if (e.getActionCommand().equals("rc"))//if player pressed reset button
			reset();//call reset method
		else {
			// code to handle the game
			int n = Integer.parseInt(e.getActionCommand());
			int x = n / col;
			int y = n % col;
			
			if (turn != colour[x][y] && last == -1)//if player clicks to move when it ia not their turn 
				showStatus ("Not your turn.");//tell them it is not their turn 
			//Click 1 - select
			else if (last == -1 && turn==colour[x][y])//if player clicks to move while it is their turn
			{
				if (piece[x][y]=='p')//if piece is pawn
					selectPawn (x,y);//call method to show where piece can go on grid

				else if (piece[x][y]=='c')//if piece is castle
					selectCastle (x,y);//call method to show where piece can go on grid

				else if (piece[x][y]=='b')//if piece is bishop
					selectBishop (x,y);//call method to show where piece can go on grid

				else if (piece[x][y]=='q')//if piece is queen
					selectQueen (x,y);//call method to show where piece can go on grid

				else if (piece[x][y]=='k')//if piece is king
					selectKing (x,y);//call method to show where piece can go on grid

				else if (piece[x][y]=='h')//if piece is left horse
					selectHorse (x,y);//call method to show where piece can go on grid

				else if (piece[x][y]=='n')//if piece is right knight
					selectHorse (x,y);//call method to show where piece can go on grid

				last = n;
			}
			//Click 2 - select
			else
			{
				int lastx = last/col;
				int lasty = last%col;

				if (select[x][y] == 's' )//if their click is a valid choice
				{//valid choice
					//move piece there and restore its old position
					colour[x][y]=colour[lastx][lasty];
					colour[lastx][lasty]='x';
					piece[x][y]=piece[lastx][lasty];
					piece[lastx][lasty]='x';
					//castle code
					if(piece[x][y] =='k' && lastx == 7 && lasty == 3 && y == 1)//if user selects area that king can only move by using castling
					{//perform castling
						colour[7][2] = 'w';
						colour[7][0] = 'x';
						piece [7][2] = 'c';
						piece [7][0] = 'x';
					}
					if(piece[x][y] =='k' && lastx == 7 && lasty == 3 && y == 5)//if user selects area that king can only move by using castling
					{//perform castling
						colour[7][4] = 'w';
						colour[7][7] = 'x';
						piece [7][4] = 'c';
						piece [7][7] = 'x';
					}
					//black castle code
					if(piece[x][y] =='k' && lastx == 0 && lasty == 3 && y == 1)//if user selects area that king can only move by using castling
					{//perform castling
						colour[0][2] = 'b';
						colour[0][0] = 'x';
						piece [0][2] = 'c';
						piece [0][0] = 'x';
					}
					if(piece[x][y] =='k' && lastx == 0 && lasty == 3 && y == 5)//if user selects area that king can only move by using castling
					{//perform castling
						colour[0][4] = 'b';
						colour[0][7] = 'x';
						piece [0][4] = 'c';
						piece [0][7] = 'x';
					}
					//need to know if any rooks or kings moved for allowing or denying castling
					if (piece[x][y] == 'k' && colour[x][y] == 'w')//if white king moved
					{
						kingmoved = 1;//variable stores something that will make sure the type of castling in which this piece has to stay unmoved is not allowed anymore
					}
					if (piece[x][y] == 'c' && lastx == 7 && lasty == 7)//if white left castle moved
					{
						castle2moved = 1;//variable stores something that will make sure the type of castling in which this piece has to stay unmoved is not allowed
					}
					if (piece[x][y] == 'c' && lastx == 7 && lasty == 0)//if white right castle moved
					{
						castle1moved = 1;//variable stores something that will make sure the type of castling in which this piece has to stay unmoved is not allowed
					}
					//black castle
					if (piece[x][y] == 'k' && colour[x][y] == 'b')// if black king moved
					{
						kingblackmoved = 1;//variable stores something that will make sure the type of castling in which this piece has to stay unmoved is not allowed
					}
					if (piece[x][y] == 'c' && lastx == 0 && lasty == 7)//if black left castle moved
					{
						castle2blackmoved = 1;//variable stores something that will make sure the type of castling in which this piece has to stay unmoved is not allowed
					}
					if (piece[x][y] == 'c' && lastx == 0 && lasty == 0)//if black right castle moved
					{
						castle1blackmoved = 1;//variable stores something that will make sure the type of castling in which this piece has to stay unmoved is not allowed
					}

					promote(x,y);//calls pawn promotion method, placed here so checks at end of every turn if pawn promotion is allowed
					if (turn == 'w'){//if turn is white
						turnpic.setIcon(createImageIcon("bpbu.jpg"));//change turnpic for black turn
						turn = 'b';//turn is black
					}	else {
						turnpic.setIcon(createImageIcon("wpwu.jpg"));//change turnpic for white turn
						turn = 'w';//turn is white
					}
					for (int i = 0; i < row; i++ )
					{
						for (int j = 0; j < col; j++)
						{
							checkarray[i][j] = 'u';//clears check array right before calling check method so check method works
						}
					}
					check();//calls check method to see before player starts making turn decisions if they are in check or checkmate

				} else {//if they don't click on a valid area to move after showing where they can move for that piece

					last =-1;
					for (int i = 0; i < row; i++)
					{
						for (int j = 0; j < col; j++)
						{
							select[i][j]='u';//stop showing where that piece can go
						}
					}	
				}

				for (int i = 0; i < row; i++)
				{
					for (int j = 0; j < col; j++)
					{
						select[i][j]='u';//clear select array after everything
					}
				}	
			}
			redraw();
		}
	}
}
