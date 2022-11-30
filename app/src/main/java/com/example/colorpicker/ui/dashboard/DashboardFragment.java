package com.example.colorpicker.ui.dashboard;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.colorpicker.R;
import com.example.colorpicker.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;


    EditText foreground, background;

    EditText RBackground, GBackground, BBackground;
    EditText RForeground, GForeground, BForeground;

    ConstraintLayout layforeground, laybackground;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        foreground = root.findViewById(R.id.Foreground_edittxt);
        background = root.findViewById(R.id.Background_Edittxt);
        layforeground = root.findViewById(R.id.Foreground_layout);
        laybackground = root.findViewById(R.id.Background_layout);

        RBackground = (EditText)root.findViewById(R.id.R_Background_edittxt);
        GBackground = (EditText)root.findViewById(R.id.G_Background_edittxt);
        BBackground = (EditText)root.findViewById(R.id.B_Background_edittxt);

        RForeground = (EditText)root.findViewById(R.id.R_Foreground_edittxt);
        GForeground = (EditText)root.findViewById(R.id.G_Foreground_edittxt);
        BForeground = (EditText)root.findViewById(R.id.B_Foreground_edittxt);

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
            Toast.makeText(getActivity(), "VOTRE COULEUR N'EXISTE PAS", Toast.LENGTH_SHORT).show();
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