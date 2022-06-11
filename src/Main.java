//User instruction
//1. Deposit the amount by clicking on the deposit button, the deposit amount can only be a whole positive number. e.g. 100
//2. After deposited the money, type the odds for the game, the odds can only be a fraction. e.g. 5/2
//3. Then Type the stake you want to bet, the stake can either be a whole positive number or decimal. e/g. 100 or 10.5 
//4. If you have won the game, click on the win button, but if you lost the game click on the lose button. 
//5. The balance will automatically changes based on your operation. 

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Main {
	//initiliase different variables
	JLabel label1;
	JLabel balanceLabel;
	JLabel balance;
	JButton depositButton;
	JButton depositSuccess;
	JTextField amountInput;
	JTextField oddsInput;
	JTextField stakeInput;
	JLabel heading;
	JLabel odds;
	JLabel stake;
	JLabel error1;
	JLabel error2;
	JPanel p,p1,p2,p3,p4,p5;
	JButton win,lose;
	JTable table1;
	JFrame main, depositWindow;
	String [] sportList = {"Football","Basketball","Badminton","American Football","Baseball"};
	
	Main(){
		//setting up the main frame and add the main panel to the frame
		main = new JFrame("Betting Management System");
		main.setSize(350,300);
		main.setLayout(null);
        //setting the bounds for the JFrame
	    main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    mainComponents();
	    main.add(p);
	    main.setVisible(true);
	}
	
	//setting up the deposit window 
	void depositWindow1(){
		depositWindow = new JFrame("Deposit");
		depositWindow.setSize(300,300);
		depositWindow.setLocation(150,150);
		depositWindow.setLayout(new FlowLayout(FlowLayout.CENTER));
		depositWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    depositWindowComp();

	    depositWindow.setVisible(true);
	}
	
	
	void mainComponents() { 
		//declare the comoponets within the main frame
		balanceLabel = new JLabel("Amount : ");
		balance = new JLabel();
		depositButton = new JButton("Deposit");
		odds = new JLabel("Odds: ");
		oddsInput = new JTextField();
		oddsInput.setColumns(3);
		stake = new JLabel("Your bet: ");
		stakeInput = new JTextField();
		stakeInput.setColumns(5);
		win = new JButton("Win");
		lose = new JButton("Lose");
		JComboBox sportSelection = new JComboBox(sportList);
		sportSelection.setSelectedIndex(0);
		p = new JPanel();
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		p4 = new JPanel();
		p5 = new JPanel();
		
		//adding different sections to the main jpanel
		p.add(p1);
		p.add(p2);
		p.add(p3);
		p.add(p4);
		p.add(p5);
		 
		//set the layout of the main jpanel
		p.setLayout(new GridLayout(5,1));
		p1.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		//adding components to different jpanel
		p1.add(depositButton);
	    p1.add(balanceLabel);
	    p1.add(balance);
	    p2.add(sportSelection);
	    p3.add(odds);
	    p3.add(oddsInput);
	    p3.add(stake);
	    p3.add(stakeInput);
	    p4.add(win);
	    p4.add(lose);
	     
	    //set the bounds of the main jpanel
	    p.setBounds(0,0,350,180);
	    
	    //creating eventlistener for depositButton
	    depositButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent actionEvent) 
          {
            depositWindow1();
          }
	    });
	    
	    //creating eventlistener for win button
	    win.addActionListener(new ActionListener() {
	          @Override
	          public void actionPerformed(ActionEvent actionEvent) 
	          {
	        	 //if the stake input is empty, display the error message
	       		 if(stakeInput.getText().isEmpty()) {
	       			 p5.add(error("Stake cannot be empty!"));
	       			 p5.updateUI();
	       		 }
	       		 //convert the string input from stake and balance to double and calculate the profit made, that will automatically adds on the balance
		       	 double stake1 = Double.parseDouble(stakeInput.getText()); 
		         double balanceAmount = Double.parseDouble(balance.getText());
		       //check if there's enough balance in users account
		         if(stake1>balanceAmount) {
		        	 p5.add(error("Not enough balance"));
		        	 p5.updateUI();
		         }
		         else {
		         //if the odds input is empty, display the error message
			         if(oddsInput.getText().isEmpty()) {
			       	 	p5.add(error("Odds cannot be empty!"));
			           	p5.updateUI();
			         }
			         stake1 /= Double.parseDouble(oddsInput.getText().split("/")[1]);
			         stake1 *= Double.parseDouble(oddsInput.getText().split("/")[0]);
			         balanceAmount += stake1;
			         String newBalance = String.valueOf(balanceAmount);
			         balance.setText(newBalance);
		         }
	          }
	     });
	    
	    //creating eventlistener for lose button
	     lose.addActionListener(new ActionListener() {
	           @Override
	           public void actionPerformed(ActionEvent actionEvent) 
	           {
	        	   //if the stake input is empty, display the error message
	        	   if(stakeInput.getText().isEmpty()) {
		        		p5.add(error("Cannot be empty!"));
		        		p5.updateUI();
	        	   }
	        	   //convert the string input from stake and balance to double and calculate the lose, that will automatically take away from the balance
			       double stake1 = Double.parseDouble(stakeInput.getText());
			       double balanceAmount = Double.parseDouble(balance.getText());
			       balanceAmount -= stake1;
			       //if the balance is less than 0, display the error message
			       if(balanceAmount<=0) {
			    	   p5.add(error("Not enough amount"));
			    	   p5.updateUI();
			    	   
			       }else {
				       String newBalance = String.valueOf(balanceAmount);
				       balance.setText(newBalance);
			       }
	           }
	       });
	     
	}
	
	//function to display errors
	public JLabel error(String i) {
		JLabel error2 = new JLabel();
		error2.setForeground(Color.red);
		error2.setText(i);
		return error2;
	}
	
	void depositWindowComp() {
		//declare the comoponets within the depositWindow 
		label1 = new JLabel("Amount");
		amountInput = new JTextField();
		error1 = new JLabel();
		error1.setForeground(Color.red);
		amountInput.setColumns(10);
		depositSuccess = new JButton("Deposit");
		//adding different components onto the depositWindow 
		depositWindow.add(label1);
		depositWindow.add(amountInput);
		depositWindow.add(depositSuccess);
		depositWindow.add(error1);
		
		//creating eventlistener for succeess deposit button
		depositSuccess.addActionListener(new ActionListener() {
	           @Override
	           public void actionPerformed(ActionEvent actionEvent) 
	           {
	        	   //check the deposit amount is correct, if its correct display the amount on the balance label,
	        	   //if not display the error message
		           String amount = amountInput.getText();
		           if(isNumeric(amount)) {
		        	   balance.setText(amount);
		           }
		           else {
		        	   error1.setText("Please enter correct Amount");
		           }
	           }
	       });
	}
	
	//function to check the string is numeric
	public static boolean isNumeric(String string) {
	    int intValue;
			
	    if(string == null || string.equals("")) {
	        return false;
	    }
	    
	    try {
	        intValue = Integer.parseInt(string);
	        return true;
	    } catch (NumberFormatException e) {
	        System.out.println("Input String cannot be parsed to Integer.");
	    }
	    return false;
	}
	
	public static void main(String[] args) {
		Main main = new Main();   
	}
	

}
