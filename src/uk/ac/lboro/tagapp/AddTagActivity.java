package uk.ac.lboro.tagapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class AddTagActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_add_tag);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_add_tag,
					container, false);
			return rootView;
		}
	}

    // do something when addTag button is pressed
    public void newTag(View view) {
    	EditText editText = (EditText) findViewById(R.id.tag_alias);
    	String tag_alias = editText.getText().toString();
    	editText = (EditText) findViewById(R.id.tag_code);
    	String tag_code = editText.getText().toString();
    	// validate user input, store new tag if data is valid
    	SharedPreferences prefs = getSharedPreferences(MainActivity.PREFS_NAME, 0);
    	SharedPreferences.Editor editor = prefs.edit();
    	
    	// get the number of stored tag info
    	int tagc = prefs.getInt(MainActivity.PREFS_KEY_TAGCOUNT, 0);
    	editor.putInt(MainActivity.PREFS_KEY_TAGCOUNT, ++tagc);
    	editor.putString(MainActivity.PREFS_KEY_TAGALIAS + tagc, tag_alias);
    	editor.putString(MainActivity.PREFS_KEY_TAGCODE + tagc, tag_code);
    	editor.commit();
    	finish();
    }
	
}
