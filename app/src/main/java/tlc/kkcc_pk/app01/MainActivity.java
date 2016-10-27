package tlc.kkcc_pk.app01;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class MainActivity extends AppCompatActivity {

    private EditText UsernameEditText, PasswordEditText;
    private Button LoginButton;
    private CheckBox RememberCheckBox;
    private String NameString, TypeString, UserString, PassString;

    private String StudentName, StudentAdd, StudentNumber;

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
                if (UserString.equals("") || PassString.equals("")) {
                    MyAlert myAlert = new MyAlert(MainActivity.this, "คำเตือน", "ไม่ได้ใส่ไรเลยโว้ยยยย");
                    myAlert.myDialog();
                } else {
                    String strURL = "http://103.13.30.147/php_get_user2.php";
                    SynUser synUser = new SynUser(MainActivity.this);
                    synUser.execute(strURL);
                    //MyAlert myAlert = new MyAlert(MainActivity.this, "คำคำคำคำ", "ทำดีละlol");
                    //myAlert.myDialog();
                }
            }
        });
        //this is Get Values
        //tring Username = String.valueOf(UsernameEditText.getText());
        //String Password = String.valueOf(PasswordEditText.getText());
        //this is Set Values
        //UsernameEditText.setText(Username + "11");
    }


    private class AddUserStudent extends AsyncTask<String, Void, String> {
        private Context context;

        public AddUserStudent(Context context)
        {
            this.context = context;
        }


        @Override
        protected String doInBackground(String... params) {

            try {

                OkHttpClient okHttpClient = new OkHttpClient();
                RequestBody requestBody = new FormEncodingBuilder()
                        .add("StudentName", StudentName)
                        .add("StudentNum",StudentNumber)
                        .add("StudentAdd", StudentAdd)
                        .build();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(params[0]).post(requestBody).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();

            } catch (Exception e) {
                Log.d("23octV2", "e doInBack ==> " + e.toString());
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("23octV2", "Result ===> " + s);
            String result = null;
            if (Boolean.parseBoolean(s)) {
                result = "Upload Value Finish";
                Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
                finish();
            } else {
                result = "Cannot Upload";
                Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
            }
        }

    }


    //Asnyctask คือการทำงานซ้ำๆ เช่น ต่อกับinternet ซ้ำๆ
    //<2.Void = ไม่รอโหลด หรือ ค้าง , 3.String = ส่งคือมาเป็น String หนึ่งค่าเท่านั้น
    private class SynUser extends AsyncTask<String, Void, String> {

        private Context context;
        private boolean aBoolean = true;
        private String[] userStrings;
        private String truePasswordString;

        public SynUser(Context context) {
            this.context = context;
        }


        @Override
        protected String doInBackground(String... params) {

            try {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(params[0]).build();
                Response response = okHttpClient.newCall(request).execute();
               // Log.d("26octV1", "response.body: " + response.body().string());
                return response.body().string();


            } catch (Exception e) {
                Log.d("26octV1", "e doIn ==> " + e.toString());
                return null;
            }

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("26octV1", "JSON ==> " + s);

            try {

                JSONArray jsonArray = new JSONArray(s);
                userStrings = new String[5];
                Log.d("26octV1", "Pass: Create 1");
                Log.d("26octV1", "JsonArray Length:"+ jsonArray.length());
                for (int i = 0; i < jsonArray.length(); i++) {
                    Log.d("26octV1","No: "+ i);
                    JSONObject jsonObject = jsonArray.getJSONObject(i);


                    if (UserString.equals(jsonObject.getString("Username"))) {
                        //True
                        Log.d("26octV1","In if");
                        aBoolean = false;
                        userStrings[0] = jsonObject.getString("idPerson");
                        userStrings[1] = jsonObject.getString("Username");
                        userStrings[2] = jsonObject.getString("Password");
                        userStrings[3] = jsonObject.getString("Name");
                        userStrings[4] = jsonObject.getString("TypeOfUser");

                    }

                }   // for

                Log.d("26octV1", "passUser==> " + PassString);
                Log.d("26octV1", "passTure==> " + userStrings[2]);


                if (aBoolean) {
                    //User False
                    MyAlert myAlert = new MyAlert(MainActivity.this, "User False",
                            "No This User in my Database");
                    myAlert.myDialog();
                } else if (PassString.equals(userStrings[2])) {
                    //Password True
                    Intent intent = new Intent(MainActivity.this, HubMenusStudent.class);
                    intent.putExtra("Login", userStrings);
                    startActivity(intent);
                    finish();

                    Toast.makeText(MainActivity.this, "Welcome " + userStrings[3],
                            Toast.LENGTH_SHORT).show();
                } else {
                    MyAlert myAlert = new MyAlert(MainActivity.this,
                            "Password Fasle", "Please Try Again Password False");
                    myAlert.myDialog();

                }


            } catch (Exception e) {
                Log.d("26octV1", "Catch HERE:  " + e.toString());
                e.printStackTrace();
            }


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
