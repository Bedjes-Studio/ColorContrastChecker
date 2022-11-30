package com.example.colorpicker.ui.dashboard;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.colorpicker.R;
import com.example.colorpicker.databinding.FragmentDashboardBinding;
import com.example.colorpicker.ui.scoreCalculator;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;




    EditText foreground, background, viewTxtForeGround;

    EditText RBackground, GBackground, BBackground;
    EditText RForeground, GForeground, BForeground;



    Color backgroundColor, foregroundColor;
    Button score;
    TextView scoreView, aaalarge, aaanormal, aalarge, aanormal;

    ConstraintLayout laybackground, backgroundColorLayout, foregroundColorLayout;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        foreground = root.findViewById(R.id.Foreground_edittxt);
        background = root.findViewById(R.id.Background_Edittxt);
        viewTxtForeGround = root.findViewById(R.id.foreground_txtEdit_view);
        laybackground = root.findViewById(R.id.Background_layout);

        RBackground = (EditText)root.findViewById(R.id.R_Background_edittxt);
        GBackground = (EditText)root.findViewById(R.id.G_Background_edittxt);
        BBackground = (EditText)root.findViewById(R.id.B_Background_edittxt);

        RForeground = (EditText)root.findViewById(R.id.R_Foreground_edittxt);
        GForeground = (EditText)root.findViewById(R.id.G_Foreground_edittxt);
        BForeground = (EditText)root.findViewById(R.id.B_Foreground_edittxt);


        aaalarge = root.findViewById(R.id.large_AAA_view);
        aalarge = root.findViewById(R.id.large_AA_view);
        aaanormal = root.findViewById(R.id.normal_AAA_view);
        aanormal = root.findViewById(R.id.normal_AA_view);

        backgroundColorLayout = root.findViewById(R.id.layout_background_color);
        foregroundColorLayout = root.findViewById(R.id.layout_foreground_color);

        score = root.findViewById(R.id.score);
        scoreView = root.findViewById(R.id.score_view);

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

        score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (backgroundColor != null && foregroundColor != null) {
                    scoreCalculator calculator = new scoreCalculator();
                    scoreView.setText("Score : " + calculator.getConstrastRatio5DigitRound(foregroundColor, backgroundColor));
                    aaanormal.setVisibility(View.VISIBLE);
                    aanormal.setVisibility(View.VISIBLE);
                    aaalarge.setVisibility(View.VISIBLE);
                    aalarge.setVisibility(View.VISIBLE);

                    if (calculator.getConstrastRatio5DigitRound(foregroundColor, backgroundColor) < 7) {
                        aaanormal.setVisibility(View.INVISIBLE);
                    }
                    if (calculator.getConstrastRatio5DigitRound(foregroundColor, backgroundColor) < 4.5) {
                        aanormal.setVisibility(View.INVISIBLE);
                        aaalarge.setVisibility(View.INVISIBLE);
                        aaanormal.setVisibility(View.INVISIBLE);
                    }
                    if (calculator.getConstrastRatio5DigitRound(foregroundColor, backgroundColor) < 3) {
                        aalarge.setVisibility(View.INVISIBLE);
                        aanormal.setVisibility(View.INVISIBLE);
                        aaalarge.setVisibility(View.INVISIBLE);
                        aaanormal.setVisibility(View.INVISIBLE);
                        Toast.makeText(getActivity(), "Votre association ne respecte aucune norme", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Selectionnez les deux couleurs", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }







    private void changeColorForeGround(String colorString){
        System.out.println(colorString);
        int color = 0;
        try {
            color = Color.parseColor(colorString.toUpperCase());
        } catch (Exception e){
            e.printStackTrace();

            Toast.makeText(getActivity(), "VOTRE COULEUR N'EXISTE PAS", Toast.LENGTH_SHORT).show();
        }
        viewTxtForeGround.setTextColor(color);
        foregroundColorLayout.setBackgroundColor(color);
        loadRGBForegroud(color);

    }

    private void changeColorBackGround(String colorString){
        System.out.println(colorString);
        int color = 0;
        try {
            color = Color.parseColor(colorString.toUpperCase());
        } catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getActivity(), "VOTRE COULEUR N'EXISTE PAS", Toast.LENGTH_SHORT).show();
        }
        laybackground.setBackgroundColor(color);
        backgroundColorLayout.setBackgroundColor(color);
        loadRGBBackgroud(color);
    }



    private void loadRGBBackgroud(int color){
        String red = String.valueOf(Color.red(color));
        String green = String.valueOf(Color.green(color));
        String blue = String.valueOf(Color.blue(color));
        RBackground.setText(red);
        GBackground.setText(green);
        BBackground.setText(blue);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            backgroundColor = Color.valueOf(Color.red(color), Color.green(color), Color.blue(color));
        }
    }

    private void loadRGBForegroud(int color){
        String red = String.valueOf(Color.red(color));
        String green = String.valueOf(Color.green(color));
        String blue = String.valueOf(Color.blue(color));
        RForeground.setText(red);
        GForeground.setText(green);
        BForeground.setText(blue);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            foregroundColor = Color.valueOf(Color.red(color), Color.green(color), Color.blue(color));
        }
    }
}