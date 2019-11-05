package dcu.ie.sda_a2_2019_iancoady;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ExplicitIntent extends AppCompatActivity {

    private Button saveButton;
    private EditText email;
    private EditText subject;
    private EditText content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.explicit_intent);

        saveButton =    (Button)findViewById(R.id.saveButton);
        email =         (EditText)findViewById(R.id.emailEditTest);
        subject =       (EditText)findViewById(R.id.subjectEditText);
        content =       (EditText)findViewById(R.id.composeMSg);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailText = email.getText().toString();

                if (isEmailValid(emailText)){
                    //Ref: https://www.javatpoint.com/android-explicit-intent-example
                    Intent returnToMain = new Intent(getApplicationContext(), MainActivity.class);
                    returnToMain.putExtra("Email", emailText);
                    returnToMain.putExtra("Subject", subject.getText().toString());
                    returnToMain.putExtra("Content", content.getText().toString());


                    startActivity(returnToMain);
                }

                else{
                    Toast.makeText(getApplicationContext(),"Invalid email, try again!",Toast.LENGTH_SHORT).show();
                    email.      setText(R.string.default_email_text);
                    subject.    setText(R.string.default_subject_text);
                    content.    setText(R.string.default_content_text);
                }


            }
        });
    }

    public boolean isEmailValid(CharSequence email) {
        //Ref: https://stackoverflow.com/questions/9355899/android-email-edittext-validation
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
