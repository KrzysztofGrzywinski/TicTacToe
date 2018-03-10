import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class TicTacToe{
	
	private JFrame frame = new JFrame("");
	private JButton[][] buttons = new JButton[3][3]; 
	private JButton playButton = new JButton("Play");
	private JLabel statusLabel = new JLabel(" ");
	private JButton clearButton = new JButton("Reset score");
	private boolean turn;
	private int numberOfTurn=1, scorePlayer1=0, scorePlayer2=0;
	private boolean isWin=false;
	private boolean draw=false;
	private JLabel mainTextArea = new JLabel();
	private JTextArea scoreTextArea = new JTextArea();
	private JLabel ScorePlayer1Label = new JLabel("Player 1 score: "+scorePlayer1);
	private JLabel ScorePlayer2Label = new JLabel("Player 2 score: "+scorePlayer2);

	
	
	
	public void run(){
		frame.setTitle("Tic Tac Toe");
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		Font font = new Font("Arial", Font.BOLD,32);
		Font font2 = new Font("Arial", Font.BOLD,20);
		
		GridLayout grid = new GridLayout(3,3);
		grid.setVgap(2);
		grid.setHgap(2);
		JPanel centerPanel = new JPanel(grid);
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				buttons[i][j] = new JButton("");
				buttons[i][j].setFont(font);
				buttons[i][j].addActionListener(new ButtonsListener());
				centerPanel.add(buttons[i][j]);
			}
		}
		frame.add(new BorderLayout().CENTER,centerPanel);
		playButton.addActionListener(new playButtonListener());
		clearButton.addActionListener(new clearButtonListener());
		JPanel southPanel = new JPanel();
		JPanel northPanel = new JPanel();
		
		
		JPanel eastPanel = new JPanel();
		eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));
		
		southPanel.add(playButton);
		southPanel.add(clearButton);
		statusLabel.setFont(font2);
		northPanel.add(statusLabel);
		
		mainTextArea.setText("Results: ");
		mainTextArea.setFont(font2);
		scoreTextArea.setEditable(false);
		
		eastPanel.add(mainTextArea);
		eastPanel.add(ScorePlayer1Label);
		eastPanel.add(ScorePlayer2Label);
		eastPanel.add(Box.createVerticalStrut(400));
		
		frame.add(new BorderLayout().SOUTH,southPanel);
		frame.add(new BorderLayout().NORTH,northPanel);
		frame.add(new BorderLayout().EAST, eastPanel);
		
		
		
		statusLabel.setText("Press play to start");
		setButtonsEnabled(false);
		
		frame.setLocationRelativeTo(null);
	}
	
	class ButtonsListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			for(int i=0; i<3; i++){
				for(int j=0; j<3; j++){
					if(event.getSource()==buttons[i][j]){
						clickButton(i,j);
					}
				}
			}
		}
	}
	
	class clearButtonListener implements ActionListener{
		public void actionPerformed( ActionEvent event){
			scorePlayer1=0;
			scorePlayer2=0;
			ScorePlayer1Label.setText("Player 1 score: "+scorePlayer1);
			ScorePlayer2Label.setText("Player 2 score: "+scorePlayer2);
		}
	}
	
	public void clickButton(int i, int j){
	
		if(checkPlayer()==false){
			buttons[i][j].setText("X");
			statusLabel.setText("Movement of player " + numberOfPlayer());
		}
		else{
			buttons[i][j].setText("O");
			statusLabel.setText("Movement of player " + numberOfPlayer());
		}
		buttons[i][j].setEnabled(false);
		checkForWin();
		numberOfTurn++;
	}
	
	public int numberOfPlayer(){
		int i;
		if(turn==true) i=2;
		else i=1;
		return i;
		
	}
	public boolean checkPlayer(){
		if(numberOfTurn%2==1) turn=true;
		else turn=false;
		return turn;
	}
	
	class playButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			statusLabel.setText("Movement of player 1");
			setButtonsEnabled(true);
			numberOfTurn =1;
			if(playButton.getText().equals("Play")){
				playButton.setText("Reset");
			}
			
			if(playButton.getText().equals("Play again")){
				playButton.setText("Reset");
			}
			
		}
	}
	
	private void setButtonsEnabled(boolean text) {
		  for(int i=0;i<3;i++){
		   for(int j=0;j<3;j++){
		    buttons[i][j].setEnabled(text);
		    if(text==true) buttons[i][j].setText("");
		   }
		  }
		}
	
	public void checkForWin(){
		if(buttons[0][0].getText().equals(buttons[0][1].getText()) && buttons[0][0].getText().equals(buttons[0][2].getText()) && buttons[0][0].getText()!= "") isWin=true;
		else if(buttons[1][0].getText().equals(buttons[1][1].getText()) && buttons[1][0].getText().equals(buttons[1][2].getText()) && buttons[1][0].getText()!= "") isWin=true;
		else if(buttons[2][0].getText().equals(buttons[2][1].getText()) && buttons[2][0].getText().equals(buttons[2][2].getText()) && buttons[2][0].getText()!= "") isWin=true;
		
		else if(buttons[0][0].getText().equals(buttons[1][0].getText()) && buttons[0][0].getText().equals(buttons[2][0].getText()) && buttons[0][0].getText()!= "") isWin=true;
		else if(buttons[0][1].getText().equals(buttons[1][1].getText()) && buttons[0][1].getText().equals(buttons[2][1].getText()) && buttons[0][1].getText()!= "") isWin=true;
		else if(buttons[0][2].getText().equals(buttons[1][2].getText()) && buttons[0][2].getText().equals(buttons[2][2].getText()) && buttons[0][2].getText()!= "") isWin=true;	

		else if(buttons[0][0].getText().equals(buttons[1][1].getText()) && buttons[0][0].getText().equals(buttons[2][2].getText()) && buttons[0][0].getText()!= "") isWin=true;
		else if(buttons[0][2].getText().equals(buttons[1][1].getText()) && buttons[0][2].getText().equals(buttons[2][0].getText()) && buttons[0][2].getText()!= "") isWin=true;
		else if(numberOfTurn==9) draw=true;
		
		if(isWin == true){
			statusLabel.setText("Game finished");
			
			if(numberOfPlayer()==2){
				scorePlayer1++;
				ScorePlayer1Label.setText("Player 1 score: "+scorePlayer1);
			}
			else{

				scorePlayer2++;
				ScorePlayer2Label.setText("Player 2 score: "+scorePlayer2);
			}
			setButtonsEnabled(false);
			playButton.setText("Play again");
			isWin=false;
			
		}
		
		if(draw == true){
			statusLabel.setText("Draw");
			playButton.setText("Play again");
			numberOfTurn=1;
			draw=false;
		}
	}

	public static void main(String[] args) {
		new TicTacToe().run();
	}
}