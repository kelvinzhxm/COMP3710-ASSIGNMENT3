package comp3710.XZZ0059.Assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    TextView editNote;
    EditText editPreviousNotes;
    Button btnAddNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);

        editNote = (TextView) findViewById(R.id.editNote);
        btnAddNote = (Button)findViewById(R.id.btnSave);
        editPreviousNotes = (EditText)findViewById(R.id.txtPreviousNotes);
        AddNote();
    }

    public void AddNote(){
        btnAddNote.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean result = myDb.createNote(editNote.getText().toString());
                        if (result)
                            Toast.makeText(MainActivity.this, "Note Created", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Note Not Created", Toast.LENGTH_LONG).show();
                        getNotes();
                        ClearText();
                    }
                }
        );
    }

    public void getNotes(){
        Cursor result = myDb.getAllData();
        StringBuffer buffer = new StringBuffer();
        while(result.moveToNext()){
            buffer.append("Note #" + result.getString(0) + ": ");
            buffer.append(result.getString(1) + "\n\n");
        }

        MainActivity.this.editPreviousNotes.setText(buffer);
    }

    public void ClearText(){
        MainActivity.this.editNote.setText("");
    }
}
