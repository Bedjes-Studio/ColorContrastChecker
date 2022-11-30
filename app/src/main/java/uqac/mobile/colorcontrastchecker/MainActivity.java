package uqac.mobile.colorcontrastchecker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText foreground, background;

    EditText RBackground, GBackground, BBackground;
    EditText RForeground, GForeground, BForeground;

    ConstraintLayout layforeground, laybackground;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        foreground = findViewById(R.id.Foreground_edittxt);
        background = findViewById(R.id.Background_Edittxt);
        layforeground = findViewById(R.id.Foreground_layout);
        laybackground = findViewById(R.id.Background_layout);

        RBackground = (EditText) findViewById(R.id.R_Background_edittxt);
        GBackground = (EditText)findViewById(R.id.G_Background_edittxt);
        BBackground = (EditText)findViewById(R.id.B_Background_edittxt);

        RForeground = (EditText)findViewById(R.id.R_Foreground_edittxt);
        GForeground = (EditText)findViewById(R.id.G_Foreground_edittxt);
        BForeground = (EditText)findViewById(R.id.B_Foreground_edittxt);

        foreground.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 5) {
                    String color = "#";
                    color += charSequence;
                    changeColorForeGround(color);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (charSequence.length() > 5) {
                        String color = "#";
                        color += charSequence;
                        changeColorForeGround(color);
                    }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



        background.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 5) {
                    String color = "#";
                    color += charSequence;
                    changeColorBackGround(color);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 5) {
                    String color = "#";
                    color += charSequence;
                    changeColorBackGround(color);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }




    private void changeColorForeGround(String colorString){
        System.out.println(colorString);
        int color = 0;
        try {
            color = Color.parseColor(colorString.toUpperCase());
        } catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "VOTRE COULEUR N'EXISTE PAS", Toast.LENGTH_SHORT).show();
        }
        layforeground.setBackgroundColor(color);
        loadRGBForegroud(color);

    }

    private void changeColorBackGround(String colorString){
        System.out.println(colorString);
        int color = 0;
        try {
            color = Color.parseColor(colorString.toUpperCase());
        } catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "VOTRE COULEUR N'EXISTE PAS", Toast.LENGTH_SHORT).show();
        }
        laybackground.setBackgroundColor(color);
        loadRGBBackgroud(color);
    }



    private void loadRGBBackgroud(int color){
        String red = String.valueOf(Color.red(color));
        String green = String.valueOf(Color.green(color));
        String blue = String.valueOf(Color.blue(color));
        RBackground.setText(red);
        GBackground.setText(green);
        BBackground.setText(blue);
    }

    private void loadRGBForegroud(int color){
        String red = String.valueOf(Color.red(color));
        String green = String.valueOf(Color.green(color));
        String blue = String.valueOf(Color.blue(color));
        RForeground.setText(red);
        GForeground.setText(green);
        BForeground.setText(blue);
    }
}