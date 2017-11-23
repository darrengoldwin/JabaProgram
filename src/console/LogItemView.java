/**
 * 
 */
package console;

import java.awt.Color;

import javax.naming.Context;
import javax.swing.text.View;

import com.neildg.mobiprog.R;

import android.view.LayoutInflater;
import android.widget.TextView;
import javafx.scene.control.TableRow;

/**
 * Represents a single log item view to be added into the console table
 * @author NeilDG
 *
 */
public class LogItemView {

	public enum LogType {
		VERBOSE,
		DEBUG,
		ERROR,
		ALL
	}
	
	private TableRow assignedRow;
	private TextView messageText;
	private TextView logTypeText;
	private LogType logType;
	
	public LogItemView(Context context) {
		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.assignedRow = (TableRow) layoutInflater.inflate(R.layout.console_log_item, null);
		this.messageText = (TextView) this.assignedRow.findViewById(R.id.log_message_text);
		this.logTypeText = (TextView) this.assignedRow.findViewById(R.id.log_type_text);
	}
	
	public void setText(LogType logType, String text) {
		this.logType = logType;
		this.logTypeText.setText(this.logType.toString());
		this.messageText.setText(text);
		
		this.UpdateTextColor();
	}
	
	private void UpdateTextColor() {
		switch(this.logType) {
			case DEBUG: this.logTypeText.setTextColor(Color.parseColor("#006400"));
						this.messageText.setTextColor(Color.parseColor("#006400"));
						break;
			case ERROR: this.logTypeText.setTextColor(Color.RED);
						this.messageText.setTextColor(Color.RED);
						break;
			default: break;
		}
	}
	
	public TableRow getView() {
		return this.assignedRow;
	}
	
	public void updateVisibility(LogType filterType) {
		if(filterType != LogType.ALL && this.logType != filterType) {
			this.assignedRow.setVisibility(View.GONE);
		}
		else {
			this.assignedRow.setVisibility(View.VISIBLE);
		}
	}
}
