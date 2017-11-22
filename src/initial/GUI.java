package initial;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.DefaultHighlighter;

import builder.BuildChecker;
import execution.ExecutionManager;
import execution.FunctionTracker;
import scope.LocalScopeCreator;
import semantics.symboltable.SymbolTable;
import statements.StatementControlOverseer;
import utils.notifications.NotificationCenter;
import utils.notifications.Notifications;

public class GUI extends JFrame{
    public static final int WINDOW_WIDTH = 850;
    public static final int WINDOW_HEIGHT = 750;
    
    private	App app;
    private JScrollPane inputSb;
    private JScrollPane outputSb;
    private JTextArea output;
    private JPanel topPanel;
    private JTextArea input;
    private JButton compile;
    private JTextArea code;
    private JButton button;
    private TextLineNumber inputLn;
    private TextLineNumber outputLn;
    
    private DefaultHighlighter.DefaultHighlightPainter painter;
    
    public GUI() {
    	this.app = new App();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Exit when close button clicked
        setTitle("JABA Program"); // "this" JFrame sets title
        setLayout(null);
        setResizable(false);
        setLocation(500, 0);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);  // or pack() the components
        init();
        setVisible(true);   // show it

    }

    public static void main(String[] args) {
        //GUISample guiSample = new GUISample();
        
		SymbolTable.initialize();
		BuildChecker.initialize();
		ExecutionManager.initialize();
		LocalScopeCreator.initialize();
		StatementControlOverseer.initialize();
		FunctionTracker.initialize();
        
		GUI g = new GUI();
    }

    public static void performResetComponents() {
		ExecutionManager.reset();
		LocalScopeCreator.reset();
		SymbolTable.reset();
		BuildChecker.reset();
		StatementControlOverseer.reset();
		FunctionTracker.reset();
	}
    
    public void init() {
    	
    	painter = new DefaultHighlighter.DefaultHighlightPainter(Color.RED);
        input = new JTextArea("");
        input.setVisible(true);
        input.setBackground(new java.awt.Color(164, 231, 223));
        input.setBorder(BorderFactory.createLineBorder(Color.black));
        input.setWrapStyleWord(true);
        inputLn = new TextLineNumber(input);
        inputLn.setUpdateFont(true);
        input.setFont(new Font("Consolas", Font.PLAIN,14));
        
        
        compile = new JButton();
        compile.setText("Compile");
        compile.setVisible(true);
        compile.setForeground(Color.BLACK);
        compile.setBackground(Color.WHITE);
        Border line = new LineBorder(Color.BLACK);
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);
        compile.setBorder(compound);
        compile.setBounds(37,600,802,50);
        compile.setFont(new Font("Consolas", Font.PLAIN,14));
        
        compile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	GUI.performResetComponents();
        		NotificationCenter.getInstance().postNotification(Notifications.ON_BUILD_EVENT);
        		
        		if(BuildChecker.getInstance().canExecute()) {
        			System.out.println("Execute");
        			ExecutionManager.getInstance().executeAllActions();
            		//this.mViewPager.setCurrentItem(1);
        		}
        		else {
        			System.out.println("ERROR");
        			//Console.log(LogType.ERROR, "Fix identified errors before executing!");
        		}
            	ExecutionManager.getInstance().executeAllActions();
                //output.setText(app.output(input.getText(), input.getLineCount()));
            }
        });
        this.add(compile);

        output = new JTextArea("");
        output.setBackground(new java.awt.Color(255, 209, 220));
        output.setBorder(BorderFactory.createLineBorder(Color.black));
        output.setEditable(false);
        output.setVisible(true);
        output.setWrapStyleWord(true);
        output.addMouseListener(new MouseListener() {
			
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				try {
					
					String[] arr = output.getText().split("\n");
					input.getHighlighter().removeAllHighlights();
					int startIndex= 0;
					int endIndex = 0;
					int lineNumber =0;
					int index=0;
					for(int i =0; i< arr.length; i++) {
						startIndex= output.getLineStartOffset(i);
						endIndex = output.getLineEndOffset(i);
						
						if(startIndex < output.getCaretPosition() && output.getCaretPosition() < endIndex ) {
							System.out.println(i + " " + startIndex + " " + endIndex);
							String[] temp = arr[i].replaceAll("line ", "").split(":");
							lineNumber = Integer.parseInt(temp[0])-1;
							break;
						}
					}
					arr = input.getText().split("\n");
					
					startIndex= input.getLineStartOffset(lineNumber);
					endIndex = input.getLineEndOffset(lineNumber);
					
					System.out.println(startIndex + " " + endIndex);
					input.setCaretPosition(startIndex);
		        	input.getHighlighter().addHighlight(startIndex, endIndex, painter);
		        }catch(Exception ex) {
		        	
		        }
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
        outputLn = new TextLineNumber(output);
        outputLn.setUpdateFont(true);
        output.setFont(new Font("Consolas", Font.PLAIN,14));
        
        inputSb = new JScrollPane(input);
        inputSb.setVisible(true);
        inputSb.setBounds(0,0, 840, 300);
        inputSb.setRowHeaderView(inputLn);
        this.add(inputSb);
        

        outputSb = new JScrollPane(output);
        outputSb.setVisible(true);
        outputSb.setBounds(0,301,840,300);
        outputSb.setRowHeaderView(outputLn);
        this.add(outputSb);

    }
}
