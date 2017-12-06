package initial;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
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
import console.Debug;
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
    public static final int WINDOW_HEIGHT = 725;
    
    private JFrame create;
    private JFrame watch;
    private JScrollPane codeSb;
    private JScrollPane inputSb;
    private JScrollPane outputSb;
    private static JTextArea output;
    private static JTextArea input;
    private JButton compile;
    private JTextPane code;
    private static JButton submit;
    private JButton createFunction;
    public JButton resumeButton;
    public JButton watchButton;
    private JButton breakButton;
    private TextLineNumber codeLn;
    private TextLineNumber outputLn;
    private TextLineNumber inputLn;
    private JLabel inputLbl;
    private JLabel outputLbl;
    
    private JLabel lblDataType;
    private JLabel lblfunctionName;
    private JLabel lblparameters;
    private JComboBox<String> datatypes;
    private JPanel parametersPanel;
    private JPanel variablePanel;
    
    private TextEditor te;
    
    private DefaultHighlighter.DefaultHighlightPainter redPainter;
    private DefaultHighlighter.DefaultHighlightPainter bluePainter;
    private boolean quote = false;
    private boolean comment = false;
    private boolean comment2 = false;
    
    private MutableAttributeSet  attrBlue;
    private MutableAttributeSet  attrDBlue;
    private MutableAttributeSet  attrBlack;
    private MutableAttributeSet  attrGray;
    private MutableAttributeSet  attrOrange;
    private MutableAttributeSet  attrMagenta;
    private MutableAttributeSet  attrGreen;
    
    private ArrayList<String> funcs;
    
    public ArrayList<Integer> breakpoint;
    
    private static GUI sharedInstance = null;
	
	public static GUI getInstance() {
		return sharedInstance;
	}
    
    public GUI() {
    	
    	te = new TextEditor();
        setTitle("JABA Program"); // "this" JFrame sets title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Exit when close button clicked
        setLayout(null);
        setResizable(false);
        setLocation(500, 0);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);  // or pack() the components
        init();
        setVisible(true);   // show it

    }

    public static void main(String[] args) {
        //GUISample guiSample = new GUISample();
    	Debug.initialize();
		SymbolTable.initialize();
		BuildChecker.initialize();
		ExecutionManager.initialize();
		LocalScopeCreator.initialize();
		StatementControlOverseer.initialize();
		FunctionTracker.initialize();
        
		GUI g = new GUI();
		sharedInstance = g;
    }

    public static void performResetComponents() {
    	GUI.getInstance().variablePanel.removeAll();
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
    	
    	if(text.substring(wordL, wordR).matches("\\\n")) {
    		comment2 = false;
    	}
    	if (text.substring(wordL, wordR).matches("\\/") || comment2) {
    		if(comment) {
    			d.setCharacterAttributes(wordL-1,wordR - wordL+1, attrGreen, true);
    			comment2 = true;
    		}	
    		comment = true;
    		//d.setCharacterAttributes(wordL,wordR - wordL, attrGreen, true);
    	}
    	else if (text.substring(wordL, wordR).matches("\\\".*")) {
    		comment = false;
    		quote = !quote;	
    		d.setCharacterAttributes(wordL, wordR - wordL, attrGray, true);
    	} 
    	else if(quote)
    		d.setCharacterAttributes(wordL, wordR - wordL, attrGray, true);  
    	else if (text.substring(wordL, wordR).matches("(\\W)*(private|public|protected|void|boolean|int|float|double|char|String|final|new)")) {
    		comment = false;
    		d.setCharacterAttributes(wordL, wordR - wordL, attrMagenta, true);
    	}
    	else if (text.substring(wordL, wordR).matches("[\\{|\\}|\\]|\\[|\\(|\\)|\\*|\\/|\\;].*")) {
    		comment = false;
    		d.setCharacterAttributes(wordL,1, attrDBlue, true);
    	}
    	else if (text.substring(wordL, wordR).matches("(\\W)*(\\d+(\\.\\d+)?)")) {
    		comment = false;
    		d.setCharacterAttributes(wordL, wordR - wordL, attrOrange, true);
    	}
    	else if (text.substring(wordL, wordR).matches("(\\W)*(true|false|return|if|else|for|while|print|scan)")) {
    		comment = false;
    		d.setCharacterAttributes(wordL, wordR - wordL, attrBlue, true);     
    	}
        else {
        	comment = false;
            d.setCharacterAttributes(wordL, wordR - wordL, attrBlack, true);
        }
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
    
    private void saveFuncNames() {
    	String s = code.getText();
    	String[] split = s.split("\n");
    	for(int i =0; i< split.length; i++) {
    		String str = split[i];
    		if(str.contains("public")) {
    			String[] temp = str.split(" ");
    			int index = temp[2].indexOf('(');
    			if(!temp[1].equals("class"))
    				funcs.add(temp[2].substring(0, index));
    		}
    		
    	}
    }
    
    public void initCreate() {
    	
    	lblDataType = new JLabel("Data Type: ");
    	lblDataType.setBounds(10, 10, 100, 20);
    	lblDataType.setVisible(true);
    	create.add(lblDataType);
    	
    	String[] types = {"void", "int", "char", "boolean", "double", "float", "String"};
    	String[] types2 = {"int", "char", "boolean", "double", "float", "String"};
    	datatypes = new JComboBox<String>(types);
    	datatypes.setBounds(120, 10, 150, 20);
    	datatypes.setVisible(true);
    	create.add(datatypes);
    	
    	lblfunctionName = new JLabel("Function Name: ");
    	lblfunctionName.setBounds(10, 40, 100, 20);
    	lblfunctionName.setVisible(true);
    	create.add(lblfunctionName);
    	
    	JTextField functionName = new JTextField();
    	functionName.setBounds(120, 40, 150, 20);
    	functionName.setVisible(true);
    	create.add(functionName);
    	
    	lblparameters = new JLabel("Parameters: ");
    	lblparameters.setBounds(10, 70, 100, 20);
    	lblparameters.setVisible(true);
    	create.add(lblparameters);
    	
    	JButton btnAddParameter = new JButton("Add Parameter");
    	btnAddParameter.setBounds(120, 70, 150, 20);
    	btnAddParameter.setVisible(true);
    	btnAddParameter.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox<String> datatypes = new JComboBox<String>(types2);
		    	datatypes.setBounds(0, 10* parametersPanel.getComponentCount(), 100, 20);
		    	datatypes.setVisible(true);
		    	
		    	
		    	JTextField functionName = new JTextField();
		    	functionName.setBounds(120, 10* parametersPanel.getComponentCount(), 130, 20);
		    	functionName.setVisible(true);
		    	
		    	parametersPanel.add(datatypes);
		    	parametersPanel.add(functionName);
		    	parametersPanel.repaint();
		    	parametersPanel.revalidate();
			}
		});
    	create.add(btnAddParameter);
    	
    	parametersPanel = new JPanel();
    	parametersPanel.setBackground(Color.WHITE);
    	parametersPanel.setLayout(null);
    	parametersPanel.setVisible(true);
    	
    	JScrollPane parametersb = new JScrollPane(parametersPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    	parametersb.setBounds(10, 100, 260, 100);
    	parametersb.setVisible(true);
    	create.add(parametersb);
    	
    	JButton btnCreateFunction = new JButton("Submit");
    	btnCreateFunction.setBounds(160, 230, 100, 20);
    	btnCreateFunction.setVisible(true);
    	btnCreateFunction.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ArrayList<String> params = new ArrayList<String>();
				ArrayList<String> types = new ArrayList<String>();
				
				//System.out.println(code.getText().charAt(code.getCaretPosition()) + " " +code.getCaret().getDot());
				boolean error =  false;
				
				for(String s: funcs) {
					if(s.equals(functionName.getText())) {
						JOptionPane.showMessageDialog(create, "Duplicate Function Name");
						error = true;
					}
						
				}
				if(!error)
					funcs.add(functionName.getText());
				
				types.add((String)datatypes.getSelectedItem());
				Component[] components = parametersPanel.getComponents();
				
				for(Component jc: components) {
					System.out.println(jc.getClass());
					if(jc.getClass().equals(JComboBox.class)) {
						JComboBox<String> j = (JComboBox<String>) jc;
						types.add((String)j.getSelectedItem());
					}
					else {
						JTextField t = (JTextField) jc;
						for(int i =0; i< params.size(); i++) {
							if(params.get(i).equalsIgnoreCase(t.getText())) {
								JOptionPane.showMessageDialog(create, "Duplicate Parameter Name");
								error =true;
							}
						}
						params.add(t.getText());
					}
						
				}
				if(!error) {
					String s = "public ";
					s += types.get(0) + " " + functionName.getText() + "(";
					
					if(types.size() == 1) 
						s+= " ";
					else
						s+= types.get(1) + " " + params.get(0);
					for(int i =2; i< types.size(); i++) {
						s+= ", " + types.get(i) + " " + params.get(i-1);
					}
					s += ") {\n";
					s += "//Put Code Here";
					s += "\n\n\n";
					if(!types.get(0).equals("void"))
						s+= "return // " + types.get(0) + ";\n";
					s += "}\n";
					
					String codeStr;
					try {
						codeStr = code.getDocument().getText(0, code.getDocument().getLength());
						
						String sub = code.getDocument().getText(0, code.getCaretPosition());
						sub += s + codeStr.substring(code.getCaretPosition(), codeStr.length());
						code.setText(sub);
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					create.dispose();
				}
				
				
			}
		});
    	create.add(btnCreateFunction);
    	
    	
    	
    	
    }

    public void initStyle() {
    	attrBlue = new SimpleAttributeSet();
    	attrOrange = new SimpleAttributeSet();
    	attrGray = new SimpleAttributeSet();
    	attrBlack = new SimpleAttributeSet();
    	attrDBlue = new SimpleAttributeSet();
    	attrMagenta = new SimpleAttributeSet();
    	attrGreen = new SimpleAttributeSet();
    	StyleConstants.setForeground(attrGreen, new Color(0,150,0));
		StyleConstants.setForeground(attrBlue, Color.BLUE);
    	StyleConstants.setForeground(attrOrange, Color.ORANGE);
    	StyleConstants.setForeground(attrGray, Color.GRAY);
    	StyleConstants.setForeground(attrBlack, Color.BLACK);
    	StyleConstants.setForeground(attrDBlue, new Color(0,0,151));
    	StyleConstants.setForeground(attrMagenta, Color.MAGENTA);
    	StyleConstants.setBold(attrDBlue, true);
    	StyleConstants.setBold(attrMagenta, false);
    	StyleConstants.setBold(attrOrange, false);
    	StyleConstants.setBold(attrBlue, true);
    	StyleConstants.setBold(attrBlack, false);
    	StyleConstants.setBold(attrGray, false);
    	StyleConstants.setBold(attrGreen, false);
    	StyleConstants.setItalic(attrGreen, false);
    	StyleConstants.setItalic(attrDBlue, false);
    	StyleConstants.setItalic(attrMagenta, false);
    	StyleConstants.setItalic(attrOrange, false);
    	StyleConstants.setItalic(attrDBlue, false);
    	StyleConstants.setItalic(attrBlack, false);
    	StyleConstants.setItalic(attrGray, true);
    }
    
    public void initWatch() {
    	
    	variablePanel = new JPanel();
    	variablePanel.setBackground(Color.WHITE);
    	variablePanel.setLayout(null);
    	variablePanel.setVisible(true);
    	
    	JScrollPane variablesb = new JScrollPane(variablePanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    	variablesb.setBounds(10, 10, 280, 250);
    	variablesb.setVisible(true);
    	watch.add(variablesb);
    }
    
    public void getVar(String data, String name, String value) {
    	JTextField datatype = new JTextField();
    	datatype.setBounds(0, 10* variablePanel.getComponentCount(), 70, 20);
    	datatype.setText(data);
    	datatype.setEditable(false);
    	datatype.setVisible(true);
    	
    	JTextField varName = new JTextField();
    	varName.setBounds(100, 10* variablePanel.getComponentCount(), 70, 20);
    	varName.setText(name);
    	varName.setEditable(false);
    	varName.setVisible(true);
    	
    	JTextField val = new JTextField();
    	val.setBounds(200, 10* variablePanel.getComponentCount(), 70, 20);
    	val.setText(value);
    	val.setEditable(false);
    	val.setVisible(true);
    	
    	variablePanel.add(datatype);
    	variablePanel.add(varName);
    	variablePanel.add(val);
    	variablePanel.repaint();
    	variablePanel.revalidate();
    }
    
    public void init() {
    	
    	initStyle();
    	redPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.RED);
    	bluePainter = new DefaultHighlighter.DefaultHighlightPainter(Color.LIGHT_GRAY);
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
        compile.setBounds(35,645,374,50);
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
        
        createFunction = new JButton();
        createFunction.setText("New Function");
        createFunction.setVisible(true);
        createFunction.setForeground(Color.BLACK);
        createFunction.setBackground(Color.GRAY);
        createFunction.setBorder(compound);
        createFunction.setBounds(35,0,150,25);
        createFunction.setFont(new Font("Consolas", Font.PLAIN,14));
        createFunction.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				funcs = new ArrayList<String>();
				create = new JFrame("Create Function");
				create.setSize(300, 300);
				create.setLayout(null);
				create.setResizable(false);
				create.setLocation(200, 0);
				create.setVisible(true);
				saveFuncNames();
				initCreate();
				
			}
		});
        this.add(createFunction);
        
        breakpoint = new ArrayList<Integer>();
        breakButton = new JButton();
        breakButton.setText("Break Point");
        breakButton.setVisible(true);
        breakButton.setForeground(Color.BLACK);
        breakButton.setBackground(Color.GRAY);
        breakButton.setBorder(compound);
        breakButton.setBounds(205,0,150,25);
        breakButton.setFont(new Font("Consolas", Font.PLAIN,14));
        breakButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				int lineNumber =0;
				int sum =0;
				int position = code.getCaretPosition();
				try {
					String s = code.getDocument().getText(0,code.getDocument().getLength());
				
					String[] lines = s.split("\n");
					
					for(int i =0; i< lines.length; i++) {
			
						sum += lines[i].length()+1;
						if(position > sum)
							lineNumber++;
						else break;
					}
					boolean isEmpty = true;
					
						
					for(int i =0; i< breakpoint.size(); i++) {
						if(lineNumber+1 == breakpoint.get(i)) {
							code.getHighlighter().removeAllHighlights();
							breakpoint.removeAll(breakpoint);
							isEmpty = false;
						}
						
					}
					if(isEmpty) {
						breakpoint.add(lineNumber +1);
						code.getHighlighter().addHighlight(sum - lines[lineNumber].length()-1, sum, bluePainter);
					}
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
        this.add(breakButton);
        
        watch = new JFrame("Create Function");
		watch.setSize(300, 300);
		watch.setLayout(null);
		watch.setResizable(false);
		watch.setVisible(false);
		watch.setLocation(200, 300);
		watch.setDefaultCloseOperation(HIDE_ON_CLOSE);
		initWatch();
		
        watchButton = new JButton();
        watchButton.setText("Watch");
        watchButton.setVisible(true);
        watchButton.setForeground(Color.BLACK);
        watchButton.setBackground(Color.GRAY);
        watchButton.setBorder(compound);
        watchButton.setBounds(375,0,150,25);
        watchButton.setFont(new Font("Consolas", Font.PLAIN,14));
        watchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				watch.setVisible(true);
				
				
			}
		});
        this.add(watchButton);

        resumeButton = new JButton();
        resumeButton.setText("Resume");
        resumeButton.setVisible(false);
        resumeButton.setForeground(Color.BLACK);
        resumeButton.setBackground(Color.GRAY);
        resumeButton.setBorder(compound);
        resumeButton.setBounds(545,0,150,25);
        resumeButton.setFont(new Font("Consolas", Font.PLAIN,14));
        resumeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GUI.getInstance().variablePanel.removeAll();
				ExecutionManager.getInstance().resumeExecution();
				resumeButton.setVisible(false);;
			}
		});
        this.add(resumeButton);
        
        submit = new JButton();
        submit.setText("Submit");
        submit.setVisible(true);
        submit.setForeground(Color.BLACK);
        submit.setBackground(Color.WHITE);
        submit.setBorder(compound);
        submit.setBounds(455,645,384,50);
        submit.setFont(new Font("Consolas", Font.PLAIN,14));
        submit.setEnabled(false);
        submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("BUTTON");
				//ExecutionManager.getInstance().resumeExecution();
				NotificationCenter.getInstance().postNotification(Notifications.ON_SCAN_DIALOG_DISMISSED, Input.getInstance().getMessage());
			}
		});
        
        this.add(submit);
        
        outputLbl = new JLabel("Output: ");
        outputLbl.setBounds(35, 325, 100, 20);
        outputLbl.setFont(new Font("Consolas", Font.PLAIN,14));
        outputLbl.setVisible(true);
        this.add(outputLbl);
        
        inputLbl = new JLabel("Input: ");
        inputLbl.setBounds(455, 325, 100, 20);
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
		        	code.getHighlighter().addHighlight(startIndex, endIndex, redPainter);
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
        codeSb.setBounds(0,25, 840, 300);
        codeSb.setRowHeaderView(codeLn);
        this.add(codeSb);

        outputSb = new JScrollPane(output);
        outputSb.setVisible(true);
        outputSb.setBounds(0,346,410,300);
        outputSb.setRowHeaderView(outputLn);
        this.add(outputSb);
        
        inputSb = new JScrollPane(input);
        inputSb.setVisible(true);
        inputSb.setBounds(420,346,420,300);
        inputSb.setRowHeaderView(inputLn);
        this.add(inputSb);

    }
    
}
