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
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.DefaultHighlighter;

import builder.BuildChecker;
import console.Input;
import console.Output;
import execution.ExecutionManager;
import execution.FunctionTracker;
import scope.LocalScopeCreator;
import semantics.symboltable.SymbolTable;
import statements.StatementControlOverseer;
import ui.fragments.TextEditor;
import utils.notifications.NotificationCenter;
import utils.notifications.Notifications;

public class GUI extends JFrame{
    public static final int WINDOW_WIDTH = 850;
    public static final int WINDOW_HEIGHT = 750;
    
    private	App app;
    private JScrollPane codeSb;
    private JScrollPane inputSb;
    private JScrollPane outputSb;
    private static JTextArea output;
    private static JTextArea input;
    private JButton compile;
    private JTextArea code;
    private static JButton submit;
    private TextLineNumber codeLn;
    private TextLineNumber outputLn;
    private TextLineNumber inputLn;
    private JLabel inputLbl;
    private JLabel outputLbl;
    
    private TextEditor te;
    
    private DefaultHighlighter.DefaultHighlightPainter painter;
    
    
    public GUI() {
    	this.app = new App();
    	te = new TextEditor();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Exit when close button clicked
        setTitle("JABA Program"); // "this" JFrame sets title
        setLayout(null);
        setResizable(false);
        setLocation(500, 0);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT-50);  // or pack() the components
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
		Output.getInstance().clear();
		Input.getInstance().clear();
	}
    
    public void init() {
    	
    	painter = new DefaultHighlighter.DefaultHighlightPainter(Color.RED);
        code = new JTextArea("");
        code.setVisible(true);
        code.setBackground(new java.awt.Color(164, 231, 223));
        code.setBorder(BorderFactory.createLineBorder(Color.black));
        code.setWrapStyleWord(true);
        codeLn = new TextLineNumber(code);
        codeLn.setUpdateFont(true);
        code.setFont(new Font("Consolas", Font.PLAIN,14));
        
        compile = new JButton();
        compile.setText("Compile");
        compile.setVisible(true);
        compile.setForeground(Color.BLACK);
        compile.setBackground(Color.WHITE);
        Border line = new LineBorder(Color.BLACK);
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);
        compile.setBorder(compound);
        compile.setBounds(35,620,374,50);
        compile.setFont(new Font("Consolas", Font.PLAIN,14));
        compile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
        		te.onCreateView(code);
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
        		
            	
                //output.setText(app.output(code.getText(), code.getLineCount()));
            }
        });
        this.add(compile);

        submit = new JButton();
        submit.setText("Submit");
        submit.setVisible(true);
        submit.setForeground(Color.BLACK);
        submit.setBackground(Color.WHITE);
        submit.setBorder(compound);
        submit.setBounds(455,620,384,50);
        submit.setFont(new Font("Consolas", Font.PLAIN,14));
        submit.setEnabled(false);
        submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("BUTTON");
				NotificationCenter.getInstance().postNotification(Notifications.ON_SCAN_DIALOG_DISMISSED, Input.getInstance().getMessage());
			}
		});
        
        this.add(submit);
        
        outputLbl = new JLabel("Output: ");
        outputLbl.setBounds(35, 300, 100, 20);
        outputLbl.setFont(new Font("Consolas", Font.PLAIN,14));
        outputLbl.setVisible(true);
        this.add(outputLbl);
        
        inputLbl = new JLabel("Input: ");
        inputLbl.setBounds(455, 300, 100, 20);
        inputLbl.setFont(new Font("Consolas", Font.PLAIN,14));
        inputLbl.setVisible(true);
        this.add(inputLbl);
        
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
					code.getHighlighter().removeAllHighlights();
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
					arr = code.getText().split("\n");
					
					startIndex= code.getLineStartOffset(lineNumber);
					endIndex = code.getLineEndOffset(lineNumber);
					
					System.out.println(startIndex + " " + endIndex);
					code.setCaretPosition(startIndex);
		        	code.getHighlighter().addHighlight(startIndex, endIndex, painter);
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
        output.setFont(new Font("Consolas", Font.PLAIN,14));
        Output.initialize(output);
        
        input = new JTextArea("");
        input.setBackground(new java.awt.Color(255, 209, 220));
        input.setBorder(BorderFactory.createLineBorder(Color.black));
        input.setVisible(true);
        input.setWrapStyleWord(true);
        input.setFont(new Font("Consolas", Font.PLAIN,14));
        input.setEnabled(false);
        Input.initialize(input, submit);
        
        outputLn = new TextLineNumber(output);
        outputLn.setUpdateFont(true);
        
        inputLn = new TextLineNumber(input);
        inputLn.setUpdateFont(true);
        
        
        codeSb = new JScrollPane(code);
        codeSb.setVisible(true);
        codeSb.setBounds(0,0, 840, 300);
        codeSb.setRowHeaderView(codeLn);
        this.add(codeSb);

        outputSb = new JScrollPane(output);
        outputSb.setVisible(true);
        outputSb.setBounds(0,321,410,300);
        outputSb.setRowHeaderView(outputLn);
        this.add(outputSb);
        
        inputSb = new JScrollPane(input);
        inputSb.setVisible(true);
        inputSb.setBounds(420,321,420,300);
        inputSb.setRowHeaderView(inputLn);
        this.add(inputSb);

    }
}
