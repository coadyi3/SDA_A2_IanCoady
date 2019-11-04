package dcu.ie.sda_a2_2019_iancoady;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ExplicitIntent extends AppCompatActivity {

    private Button saveButton;
    private EditText email;
    private EditText subject;
    private EditText content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.explicit_intent);

        saveButton = (Button)findViewById(R.id.saveButton);
        email = (EditText)findViewById(R.id.emailEditTest);
        subject = (EditText)findViewById(R.id.subjectEditText);
        content = (EditText) findViewById(R.id.composeMSg);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Ref: https://www.javatpoint.com/android-explicit-intent-example
                Intent returnToMain = new Intent(getApplicationContext(), MainActivity.class);
                returnToMain.putExtra("Email", email.getText().toString());
                returnToMain.putExtra("Subject", subject.getText().toString());
                returnToMain.putExtra("Content", content.getText().toString());

                startActivity(returnToMain);
            }
        });
    }
}
