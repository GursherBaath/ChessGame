import javax.swing.*; import java.applet.*; import java.awt.event.*; import java.awt.*;

	public class Desert extends Applet implements ActionListener {
	
	
	int des[] [] = {
	{1,1,5,5,5,5,5,5,5,5,5,5,5,5,5,5},
	{5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5},
	{5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5},
	{5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5},
	{5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5},
	{5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5},
	{5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5},
	{5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5},
	{5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5},
	{5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5},
	{5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5}};
	int rows = 11;
	int cols = 16;
	JLabel pics[] = new JLabel [rows * cols];
	public void init () {
	Panel grid = new Panel (new GridLayout (rows, cols));
	int m = 0;
	for (int i = 0 ; i < rows ; i++) {
	for (int j = 0 ; j < cols ; j++){
	pics [m] = new JLabel (createImageIcon ("g"+des[i][j]+".png"));
	pics [m].setPreferredSize (new Dimension (55, 55));
	grid.add (pics [m]);
	m++;
	}
	}
	add (grid);
	}
	public void actionPerformed (ActionEvent e) {
	}
	protected static ImageIcon createImageIcon (String path) {
	java.net.URL imgURL = Desert.class.getResource (path);
	if (imgURL != null)
	return new ImageIcon (imgURL);
	else
	System.out.print("DID NOT ARRIVE");
	return null;
	}
	}
	
	
	
	
	
	
	
	


