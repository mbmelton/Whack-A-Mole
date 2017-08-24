

package homework;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

public class GridTest extends JFrame implements ActionListener{


	private static final long serialVersionUID = 1L;
	private JButton[] buttons;
	private int[] moles = {(int)(Math.random() * 100), (int)(Math.random() * 100), (int)(Math.random() * 100), (int)(Math.random() * 100), 
			(int)(Math.random() * 100), (int)(Math.random() * 100), (int)(Math.random() * 100), (int)(Math.random() * 100), 
			(int)(Math.random() * 100), (int)(Math.random() * 100)};
	private int guessCount = 50;
	public static int counter = 0;
	private int moleCount = 10;
	private static ImageIcon whackIcon;
	private static ImageIcon whackedMole;
	
	
	public static void main(String[] args){
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                makeGUIVisible();
            }
        });
	}
	
	private static void makeGUIVisible(){
		whackIcon = new ImageIcon("smallSQwhackb.jpg");
		whackedMole = new ImageIcon("whackamole.png");
		GridTest grid = new GridTest(10, 10);
		grid.setLocation(300, 200);
		grid.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		grid.pack();
		grid.setVisible(true);
	}
	
	
	public GridTest(int rows, int cols) {
		buttons = new JButton[100];
	    final Container pane = getContentPane();
	    
	    pane.setLayout(new GridLayout(rows, cols));
	    setTitle("Whack-A-Mole   Guesses Remaining: " + guessCount + "   Moles Remaining: " + moleCount);
	    for (int i = 0; i < 100; i++) {
	    	counter = i;
	    	buttons[i] = new JButton(Integer.toString(i + 1));
	    	buttons[i].addActionListener(this);
	    	pane.add(buttons[i]);
	    }
	   
	  }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		boolean guess = false;
		JButton b = ((JButton) source);
		int num;
		if(!b.equals("W") && !b.getText().equals("M")){
			num = Integer.parseInt(b.getText());
			if (source instanceof JButton) {
				guessCount--;
				setTitle("Whack-A-Mole   Guesses Remaining: " + guessCount + "   Moles Remaining: " + moleCount);
				
				//checks to see if the user has run out of guesses
				if(guessCount == 0){
					//JTextField end = new JTextField(10);
					//JOptionPane.showMessageDialog(null, "Sorry, you have run out of guesses. Please try again.");
					int result = JOptionPane.showConfirmDialog(null,
					        "Sorry, you have run out of guesses. Please try again.",
					        null, JOptionPane.CLOSED_OPTION);
					if(result == JOptionPane.NO_OPTION || result == JOptionPane.CANCEL_OPTION){
						System.exit(0);
					}
					makeGUIVisible();
				}
				
				
				
		    	for(int i = 0; i < moles.length; i++){
		    		if(moles[i] == num && !guess){
		    			guess = true;
		    			moleCount--;
		    			setTitle("Whack-A-Mole   Guesses Remaining: " + guessCount + "   Moles Remaining: " + moleCount);
		    			
		    			if(moleCount == 0){
		    				JTextField win = new JTextField("CONGRATS! You in only " + Integer.toString(50 - guessCount) + "turns left");
		    				win.setEnabled(true);
		    			}
		    			moles[i] = 0;
		    		}
		    	}
		    	if(guess){
		    		((JButton) source).setForeground(Color.RED);
		    		((JButton) source).setText("M");
		    	}else{
		    		try{
		    			((JButton) source).setText("W");
		    		}catch(Exception E){
		    			
		    		}
		    		
		    	}
		    }
		}
	}

}
