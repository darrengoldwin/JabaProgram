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
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

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
    private JTextPane code;
    private static JButton submit;
    private TextLineNumber codeLn;
    private TextLineNumber outputLn;
    private TextLineNumber inputLn;
    private JLabel inputLbl;
    private JLabel outputLbl;
    
    private TextEditor te;
    
    private DefaultHighlighter.DefaultHighlightPainter painter;
    private boolean quote = false;;
    
    private MutableAttributeSet  attrBlue;
    private MutableAttributeSet  attrDBlue;
    private MutableAttributeSet  attrBlack;
    private MutableAttributeSet  attrGray;
    private MutableAttributeSet  attrOrange;
    private MutableAttributeSet  attrMagenta;
    
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
    
    public void colorChange(DefaultStyledDocument d, String text, int wordL, int wordR) {
    	System.out.println(text.substring(wordL, wordR));
    	
    	if (text.substring(wordL, wordR).matches("(\\W)*(private|public|protected|void|boolean|int|float|double|char|String|final|new)")) {
    		
    		d.setCharacterAttributes(wordL, wordR - wordL, attrMagenta, true);
    	}
    	else if (text.substring(wordL, wordR).matches("[\\{|\\}|\\]|\\[|\\(|\\)|\\*|\\/|\\;].*")) {
    		d.setCharacterAttributes(wordL,1, attrDBlue, true);
    	}
    	else if (text.substring(wordL, wordR).matches("(\\W)*(\\d+(\\.\\d+)?)")) 
            d.setCharacterAttributes(wordL, wordR - wordL, attrOrange, false);
    	else if (text.substring(wordL, wordR).matches("(\\W)*(true|false)")) 
            d.setCharacterAttributes(wordL, wordR - wordL, attrBlue, false);
    	else if (text.substring(wordL, wordR).matches("\\\".*")) {
    		quote = !quote;	
    		d.setCharacterAttributes(wordL, wordR - wordL, attrGray, false);
    	}else if(quote)
    		d.setCharacterAttributes(wordL, wordR - wordL, attrGray, false);            
        else
            d.setCharacterAttributes(wordL, wordR - wordL, attrBlack, false);
    }
    private int findLastNonWordChar (String text, int index) {
        while (--index >= 0) {
            if (String.valueOf(text.charAt(index)).matches("\\W")) {
                break;
            }
        }
        return index;
    }

    private int findFirstNonWordChar (String text, int index) {
        while (index < text.length()) {
            if (String.valueOf(text.charAt(index)).matches("\\W")) {
                break;
            }
            index++;
        }
        return index;
    }
    
    
    public void initStyle() {
    	attrBlue = new SimpleAttributeSet();
    	attrOrange = new SimpleAttributeSet();
    	attrGray = new SimpleAttributeSet();
    	attrBlack = new SimpleAttributeSet();
    	attrDBlue = new SimpleAttributeSet();
    	attrMagenta = new SimpleAttributeSet();
		StyleConstants.setForeground(attrBlue, Color.BLUE);
    	StyleConstants.setForeground(attrOrange, Color.ORANGE);
    	StyleConstants.setForeground(attrGray, Color.GRAY);
    	StyleConstants.setForeground(attrBlack, Color.BLACK);
    	StyleConstants.setForeground(attrDBlue, new Color(0,0,151));
    	StyleConstants.setForeground(attrMagenta, Color.MAGENTA);
    	StyleConstants.setBold(attrDBlue, true);
    	StyleConstants.setBold(attrMagenta, false);
    	StyleConstants.setBold(attrOrange, true);
    	StyleConstants.setBold(attrBlue, true);
    	StyleConstants.setBold(attrBlack, false);
    	StyleConstants.setBold(attrGray, false);
    	StyleConstants.setItalic(attrGray, true);
    }
    public void init() {
    	
    	initStyle();
    	painter = new DefaultHighlighter.DefaultHighlightPainter(Color.RED);
    	
    	DefaultStyledDocument doc = new DefaultStyledDocument() {
            public void insertString (int offset, String str, AttributeSet a) throws BadLocationException {
                super.insertString(offset, str, a);
                code.getHighlighter().removeAllHighlights();
                String text = getText(0, getLength());
                int before = findLastNonWordChar(text, offset);
                if (before < 0) before = 0;
                int after = findFirstNonWordChar(text, offset + str.length());
                int wordL = before;
                int wordR = before;

                while (wordR <= after) {
                    if (wordR == after || String.valueOf(text.charAt(wordR)).matches("\\W")) {
                    	colorChange(this, text, wordL, wordR);
                        wordL = wordR;
                    }
                    wordR++;
                }
            }

            public void remove (int offs, int len) throws BadLocationException {
                super.remove(offs, len);
                code.getHighlighter().removeAllHighlights();
                String text = getText(0, getLength());
                int before = findLastNonWordChar(text, offs);
                if (before < 0) before = 0;
                int after = findFirstNonWordChar(text, offs);

                colorChange(this, text, before, after);
            }
        };
        
//        JTextPane txt = new JTextPane(doc);
//        txt.setText("public class Hi {}");
//        add(new JScrollPane(txt));
//        setVisible(true);
        
        code = new JTextPane(doc);
        code.setVisible(true);
        //code.setBackground(new java.awt.Color(164, 231, 223));
        code.setBackground(Color.WHITE);
        code.setBorder(BorderFactory.createLineBorder(Color.black));
        //code.setWrapStyleWord(true);
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
            	code.getHighlighter().removeAllHighlights();
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
						
							String s = arr[i].replaceAll("line ","aaa");
							System.out.println(s);
							
							
							String[] temp = s.split("aaa");
							String[] t = temp[1].split("\\.");
							s = t[0];
							
							lineNumber = Integer.parseInt(s)-1;
						
							break;
						}
					}
					endIndex =0;
					arr = code.getText().split("\n");
					int i;
					for(i =0; i< lineNumber+1; i++) {
						endIndex += arr[i].length();
					}
					startIndex = endIndex - arr[i-1].length();
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
