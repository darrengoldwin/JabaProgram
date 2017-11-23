package ui.fragments;

import javax.swing.JTextArea;

import builder.ParserHandler;
import utils.notifications.NotificationCenter;
import utils.notifications.NotificationListener;
import utils.notifications.Notifications;
import utils.notifications.Parameters;

public class TextEditor implements NotificationListener{
	
	public JTextArea textEditorView;
	public String classNameText;
	public final static String FILE_NAME_KEY = "FILE_NAME_KEY";
	public final static String TEXT_READ_KEY = "TEXT_READ_KEY";
	
	public TextEditor() {
		System.out.println("Shabuya");
		NotificationCenter.getInstance().addObserver(Notifications.ON_BUILD_EVENT, this);
		NotificationCenter.getInstance().addObserver(Notifications.ON_NEW_CLASS_CREATED, this);
		NotificationCenter.getInstance().addObserver(Notifications.ON_CLASS_LOADED, this);
		NotificationCenter.getInstance().addObserver(Notifications.ON_CLASS_SAVE_STARTED, this);
	}
	
	public void onCreateView(JTextArea textEditorView) {
		// Inflate the layout for this fragment
		
		this.classNameText = "JabaProgram";
		this.textEditorView = textEditorView;
		
		//this.assignAutoCompleteFunction();
		
		//return this.parentView;
	}
	
	public void onDestroy() {
		
		NotificationCenter.getInstance().removeObserver(Notifications.ON_BUILD_EVENT, this);
		NotificationCenter.getInstance().removeObserver(Notifications.ON_NEW_CLASS_CREATED, this);
		NotificationCenter.getInstance().removeObserver(Notifications.ON_CLASS_LOADED, this);
		NotificationCenter.getInstance().removeObserver(Notifications.ON_CLASS_SAVE_STARTED, this);
	}

	private void compileAndParseText() {
		String textRead = this.textEditorView.getText().toString();
		System.out.println(textRead);
		ParserHandler.getInstance().parseText(this.classNameText, textRead);
	}
	
	@Override
	public void onNotify(String notificationString, Parameters params) {
		if(notificationString == Notifications.ON_BUILD_EVENT) {
			
			this.compileAndParseText();
		}
		else if(notificationString == Notifications.ON_NEW_CLASS_CREATED) {
//			String fileName = params.getStringExtra(MainActivity.CLASS_FILE_NAME_KEY, "");
//			this.updateTextView(fileName + ".mobi", CodeTemplates.createNewClassTemplate(fileName));
		}
		else if(notificationString == Notifications.ON_CLASS_LOADED) {
			String fileName = params.getStringExtra(FILE_NAME_KEY, "");
			String codeText = params.getStringExtra(TEXT_READ_KEY, "");
			//this.updateTextView(fileName, codeText);
		}
		else if(notificationString == Notifications.ON_CLASS_SAVE_STARTED) {
			//this.saveFile();
		}
	}

}
