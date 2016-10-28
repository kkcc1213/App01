package tlc.kkcc_pk.app01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class HubMenusStudent extends AppCompatActivity implements View.OnClickListener {
    private TextView textView;
    private ImageButton Image1, Image2, Image3, Image4, Image5, Image6;
    private String[] LoginString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub_menus_student);

        LoginString = getIntent().getStringArrayExtra("Login");

        Image1 =  (ImageButton) findViewById(R.id.imageButton);
        Image2 = (ImageButton) findViewById(R.id.imageButton2);
        Image3 = (ImageButton) findViewById(R.id.imageButton3);
        Image4 = (ImageButton) findViewById(R.id.imageButton4);
        Image5 = (ImageButton) findViewById(R.id.imageButton5);
        Image6 = (ImageButton) findViewById(R.id.imageButton6);

        textView = (TextView) findViewById(R.id.textView8);

        LoginString = getIntent().getStringArrayExtra("Login");

        String[] positionStrings = new String[]{"นักเรียน", "อาจารย์"};
        textView.setText(LoginString[4] + " " + positionStrings[Integer.parseInt(LoginString[1])]);

        Image1.setOnClickListener(HubMenusStudent.this);
        Image2.setOnClickListener(HubMenusStudent.this);
        Image3.setOnClickListener(HubMenusStudent.this);
        Image4.setOnClickListener(HubMenusStudent.this);
        Image5.setOnClickListener(HubMenusStudent.this);
        Image6.setOnClickListener(HubMenusStudent.this);
    }

    @Override
    public void onClick(View v) {
        int index = 0;
        if (LoginString[1] == "1") {
            int id = v.getId();
            if (id == R.id.imageButton)
                index = 0;
            else if (id == R.id.imageButton2)
                index = 1;
            else if (id == R.id.imageButton3)
                index = 2;
            else if (id == R.id.imageButton4)
                index = 3;
            else if (id == R.id.imageButton5)
                index = 4;
            else if (id == R.id.imageButton6)
                index = 5;
        }
        Intent intent = new Intent(HubMenusStudent.this, Question.class);
        intent.putExtra("Login", LoginString);
        intent.putExtra("Index", index);
        startActivity(intent);
    }


}
