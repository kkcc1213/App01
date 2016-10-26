package tlc.kkcc_pk.app01;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText UsernameEditText, PasswordEditText;
    private Button LoginButton;
    private CheckBox RememberCheckBox;
    private String UserString, PassString;

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
                UserString = String.valueOf(UsernameEditText.getText()).trim();
                PassString = String.valueOf(PasswordEditText.getText()).trim();
                //if(UserString.length() != 0 && PassString.length() != 0) same same
                if (!UserString.equals("") && !PassString.equals("")) {
                    MyAlert myAlert = new MyAlert(MainActivity.this,"คำเตือน","ไม่ได้ใส่ไรเลยโว้ยยยย");
                    myAlert.myDialog();
                }else
                {
                    String strURL = "http://103.13.30.147/php_get_user2.php";
                    SynUser synUser = new SynUser(MainActivity.this);
                    synUser.execute(strURL);
                    MyAlert myAlert = new MyAlert(MainActivity.this,"คำคำคำคำ","ทำดีละlol");
                    myAlert.myDialog();
                }
            }
        });
        //this is Get Values
        String Username = String.valueOf(UsernameEditText.getText());
        String Password = String.valueOf(PasswordEditText.getText());
        //this is Set Values
        UsernameEditText.setText(Username + "11");
    }

    //Asnyctask คือการทำงานซ้ำๆ เช่น ต่อกับinternet ซ้ำๆ
    //<2.Void = ไม่รอโหลด หรือ ค้าง , 3.String = ส่งคือมาเป็น String หนึ่งค่าเท่านั้น
    private class SynUser extends AsyncTask<String, Void, String> {

        private Context context;

        public SynUser(Context context)
        {
            this.context = context;
        }


        @Override
        protected String doInBackground(String... params) {

            try {

            }catch (Exception e){
                Log.d("26octV1", "e doIn ==> "+e.toString());
            }


            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

    //Shift + Ctrl + Enter
    //auto completed

    //Alt+Enter
    //recommend setting

    //Ctrl + Alt + L
    //Reorganize code

    //Ctrl + space
    //auto Complete name

}
