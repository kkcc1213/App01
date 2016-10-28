package tlc.kkcc_pk.app01;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.media.VolumeProviderCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

public class Question extends AppCompatActivity {
    private EditText questionEditText, choice1EditText, choice2EditText,
            choice3EditText, choice4EditText;
    private Spinner spinner;
    private Button button;
    private TextView textView;
    private String questionString, choice1String, choice2String,
            choice3String, choice4String, trueAnswerString;
    private String QuestionID;
    private int CorrentIndex;
    private String[] LoginString;
    private String IndexSub;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        LoginString = getIntent().getStringArrayExtra("Login");
        IndexSub = getIntent().getStringExtra("Index");

        questionEditText = (EditText) findViewById(R.id.editText);
        choice1EditText = (EditText) findViewById(R.id.editText2);
        choice2EditText = (EditText) findViewById(R.id.editText4);
        choice3EditText = (EditText) findViewById(R.id.editText5);
        choice4EditText = (EditText) findViewById(R.id.editText6);

        spinner = (Spinner) findViewById(R.id.spinner);
        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView9);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionString = questionEditText.getText().toString();
                choice1String = choice1EditText.getText().toString();
                choice2String = choice2EditText.getText().toString();
                choice3String = choice3EditText.getText().toString();
                choice4String = choice4EditText.getText().toString();

                if (questionString.trim().equals("") || choice1String.trim().equals("") ||
                        choice2String.trim().equals("") || choice3String.trim().equals("") ||
                        choice4String.trim().equals("")) {
                    // Have Space

                } else if (spinner.getSelectedItem().toString().equals(1)) {
                    String strURL = "http://103.13.30.147/add_question.php";
                    AddQuestion addQuestion = new AddQuestion(Question.this);
                    addQuestion.execute(strURL);
                    AddChoice addChoice = new AddChoice(Question.this,);
                } else {
                }
                MyAlert myAlert = new MyAlert(Question.this, "User False",
                        "Spinner Get: "+spinner.getSelectedItem());
                myAlert.myDialog();

            }
        });


        String[] showAnswer = new String[]{"1", "2", "3", "4"};
        ArrayAdapter<String> dd = new ArrayAdapter<String>(Question.this,
                android.R.layout.simple_list_item_1, showAnswer);
        spinner.setAdapter(dd);

    }


    private class AddQuestion extends AsyncTask<String, Void, String> {

        private Context context;
        private String InsertLastID;

        public AddQuestion(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(String... params) {

            try {
                OkHttpClient okHttpClient = new OkHttpClient();
                RequestBody requestBody = new FormEncodingBuilder()
                        .add("Question", questionString)
                        .add("idTeacher", LoginString[0])
                        .add("idSubject", IndexSub)
                        .build();
                Log.d("28octV1", "Question String: " + questionString);
                Log.d("28octV1", "idTeacher String: " + LoginString[0]);
                Log.d("28octV1", "idSubject String: " + IndexSub);
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(params[0]).post(requestBody).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();
            } catch (Exception e) {
                Log.d("26octV1", "e doIn ==> " + e.toString());
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {

                JSONArray jsonArray = new JSONArray(s);
                if(jsonArray.length() > 0) {
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    InsertLastID = jsonObject.getString("LastId");
                    QuestionID = InsertLastID;
                    Log.d("28octV1", "Done Return Question ID: " + QuestionID);
                }
                else {
                    //User False
                    MyAlert myAlert = new MyAlert(Question.this, "User False",
                            "False Return");
                    myAlert.myDialog();
                }


            } catch (Exception e) {
                Log.d("28octV1", "Catch HERE:  " + e.toString());
                e.printStackTrace();
            }
        }
    }

    private class AddChoice extends AsyncTask<String, Void, String> {

        private Context context;
        private String choiceText, correct;

        public AddChoice(Context context, String ChoiceText, String Correct) {
            this.context = context;
            choiceText = ChoiceText;
            correct = Correct;
        }

        @Override
        protected String doInBackground(String... params) {

            try {
                OkHttpClient okHttpClient = new OkHttpClient();
                RequestBody requestBody = new FormEncodingBuilder()
                        .add("idQuestion", QuestionID)
                        .add("Choice", choiceText)
                        .add("Correct", correct)
                        .build();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(params[0]).post(requestBody).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();
            } catch (Exception e) {
                Log.d("28octV1", "e doIn ==> " + e.toString());
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {

                    MyAlert myAlert = new MyAlert(Question.this, "User False",
                            "False Return");
                    myAlert.myDialog();


            } catch (Exception e) {
                Log.d("28octV1", "Catch HERE:  " + e.toString());
                e.printStackTrace();
            }
        }
    }

}
