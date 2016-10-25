package tlc.kkcc_pk.app01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText UsernameEditText, PasswordEditText;
    private Button LoginButton;
    private CheckBox RememberCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Code Start HERE
        UsernameEditText = (EditText) findViewById(R.id.usernameEditText);
        PasswordEditText = (EditText) findViewById(R.id.passwordEditText);
        LoginButton = (Button) findViewById(R.id.loginButton);
        RememberCheckBox = (CheckBox) findViewById(R.id.remeberCheckBox);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String CurrentUsername = String.valueOf(UsernameEditText.getText());
                UsernameEditText.setText(String.valueOf(UsernameEditText.getText()) + "1");
            }
        });
        //this is Get Values
        String Username = String.valueOf(UsernameEditText.getText());
        String Password = String.valueOf(PasswordEditText.getText());
        //this is Set Values
        UsernameEditText.setText(Username + "11");
    }

    //Shift + Ctrl + Enter
    //auto completed

    //Ctrl + Alt + L
    //Reorganize code

    //Ctrl + space
    //auto Complete name

}
