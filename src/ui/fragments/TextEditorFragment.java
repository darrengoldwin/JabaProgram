package ui.fragments;

import com.neildg.mobiprog.MainActivity;
import com.neildg.mobiprog.R;
import com.neildg.mobiprog.generatedexp.JavaReservedWords;
import com.neildg.mobiprog.io.ClassFileSaver;
import com.neildg.mobiprog.templates.CodeTemplates;
import com.neildg.mobiprog.utils.SpaceTokenizer;
import com.sun.glass.ui.View;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import builder.ParserHandler;
import utils.ApplicationCore;
import utils.notifications.NotificationCenter;
import utils.notifications.NotificationListener;
import utils.notifications.Notifications;
import utils.notifications.Parameters;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link TextEditorFragment.OnFragmentInteractionListener} interface to handle
 * interaction events.
 * 
 */
public class TextEditorFragment extends Fragment implements NotificationListener{

	private final static String TAG = "MobiProg_TextEditorFragment";
	
	private View parentView;
	private TextView classNameText;
	private MultiAutoCompleteTextView textEditorView;
	
	public final static String FILE_NAME_KEY = "FILE_NAME_KEY";
	public final static String TEXT_READ_KEY = "TEXT_READ_KEY";
	
	public TextEditorFragment() {
		NotificationCenter.getInstance().addObserver(Notifications.ON_BUILD_EVENT, this);
		NotificationCenter.getInstance().addObserver(Notifications.ON_NEW_CLASS_CREATED, this);
		NotificationCenter.getInstance().addObserver(Notifications.ON_CLASS_LOADED, this);
		NotificationCenter.getInstance().addObserver(Notifications.ON_CLASS_SAVE_STARTED, this);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		NotificationCenter.getInstance().removeObserver(Notifications.ON_BUILD_EVENT, this);
		NotificationCenter.getInstance().removeObserver(Notifications.ON_NEW_CLASS_CREATED, this);
		NotificationCenter.getInstance().removeObserver(Notifications.ON_CLASS_LOADED, this);
		NotificationCenter.getInstance().removeObserver(Notifications.ON_CLASS_SAVE_STARTED, this);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		this.parentView = inflater.inflate(R.layout.fragment_text_editor, container, false);
		
		this.classNameText = (TextView) this.parentView.findViewById(R.id.class_name_text);
		this.textEditorView = (MultiAutoCompleteTextView) this.parentView.findViewById(R.id.editor_text);
		
		Typeface type = Typeface.createFromAsset(ApplicationCore.getInstance().getAssetManager(),"Consolas.ttf"); 
		this.textEditorView.setTypeface(type);
		
		this.assignAutoCompleteFunction();
		
		return this.parentView;
	}
	
	private void assignAutoCompleteFunction() {

		// Create the adapter and set it to the AutoCompleteTextView 
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.parentView.getContext(), android.R.layout.simple_list_item_1, JavaReservedWords.KEYWORDS);
		this.textEditorView.setAdapter(adapter);
		this.textEditorView.setTokenizer(new SpaceTokenizer());
	}
	
	private void compileAndParseText() {
		String textRead = this.textEditorView.getText().toString();
		
		ParserHandler.getInstance().parseText(this.classNameText.getText().toString(), textRead);
	}
	
	private void updateTextView(String fileName, String codeText) {
		this.classNameText.setText(fileName);	
		this.textEditorView.setText(codeText);
	}
	
	private void saveFile() {
		String fileName = this.classNameText.getText().toString();
		String codeText = this.textEditorView.getText().toString();
		
		ClassFileSaver classFileSaver = new ClassFileSaver();
		classFileSaver.saveFileWithExtension(fileName, codeText);
	}
	

	@Override
	public void onNotify(String notificationString, Parameters params) {
		if(notificationString == Notifications.ON_BUILD_EVENT) {
			this.compileAndParseText();
		}
		else if(notificationString == Notifications.ON_NEW_CLASS_CREATED) {
			String fileName = params.getStringExtra(MainActivity.CLASS_FILE_NAME_KEY, "");
			this.updateTextView(fileName + ".mobi", CodeTemplates.createNewClassTemplate(fileName));
		}
		else if(notificationString == Notifications.ON_CLASS_LOADED) {
			String fileName = params.getStringExtra(FILE_NAME_KEY, "");
			String codeText = params.getStringExtra(TEXT_READ_KEY, "");
			this.updateTextView(fileName, codeText);
		}
		else if(notificationString == Notifications.ON_CLASS_SAVE_STARTED) {
			this.saveFile();
		}
	}
	
	
}
