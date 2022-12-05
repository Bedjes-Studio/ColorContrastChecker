package com.example.colorpicker.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.colorpicker.R;
import com.example.colorpicker.databinding.FragmentHomeBinding;

import yuku.ambilwarna.AmbilWarnaDialog;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    Button button1, button2, button3, button4;
    ImageButton ibtn1, ibtn2, ibtn3, ibtn4;
    ConstraintLayout constraintLayout;
    int defaultColor;
    TextView colorNumber1, colorNumber2, colorNumber3, colorNumber4;
    String test;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        button1 = (Button) root.findViewById(R.id.btn1);
        button2 = (Button) root.findViewById(R.id.btn2);
        button3 = (Button) root.findViewById(R.id.btn3);
        button4 = (Button) root.findViewById(R.id.btn4);
        colorNumber1 = (TextView) root.findViewById(R.id.colorNumber1);
        colorNumber2 = (TextView) root.findViewById(R.id.colorNumber2);
        colorNumber3 = (TextView) root.findViewById(R.id.colorNumber3);
        colorNumber4 = (TextView) root.findViewById(R.id.colorNumber4);
        ibtn1 = (ImageButton) root.findViewById(R.id.ibtn1);
        ibtn2 = (ImageButton) root.findViewById(R.id.ibtn2);
        ibtn3 = (ImageButton) root.findViewById(R.id.ibtn3);
        ibtn4 = (ImageButton) root.findViewById(R.id.ibtn4);

        System.out.println("test" + colorNumber1.getText().toString());

        constraintLayout = (ConstraintLayout) root.findViewById(R.id.layout);

        defaultColor = ContextCompat.getColor(this.getContext(), R.color.black);
        colorNumber1.setText(Integer.toHexString(defaultColor).substring(2));
        colorNumber2.setText(Integer.toHexString(defaultColor).substring(2));
        colorNumber3.setText(Integer.toHexString(defaultColor).substring(2));
        colorNumber4.setText(Integer.toHexString(defaultColor).substring(2));

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openColorPicker(1);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openColorPicker(2);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openColorPicker(3);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openColorPicker(4);
            }
        });

        ibtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String color = colorNumber1.getText().toString();
                int colorInt = 0;

                try {
                    colorInt = Color.parseColor('#' + color.toUpperCase());
                } catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "VOTRE COULEUR N'EXISTE PAS", Toast.LENGTH_SHORT).show();
                }
                button1.setBackgroundColor(colorInt);
            }
        });

        ibtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String color = colorNumber2.getText().toString();
                int colorInt = 0;
                try {
                    colorInt = Color.parseColor('#' + color.toUpperCase());
                } catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "VOTRE COULEUR N'EXISTE PAS", Toast.LENGTH_SHORT).show();
                }
                button2.setBackgroundColor(colorInt);
            }
        });

        ibtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String color = colorNumber3.getText().toString();
                int colorInt = 0;
                try {
                    colorInt = Color.parseColor('#' + color.toUpperCase());
                } catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "VOTRE COULEUR N'EXISTE PAS", Toast.LENGTH_SHORT).show();
                }
                button3.setBackgroundColor(colorInt);
            }
        });

        ibtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String color = colorNumber4.getText().toString();
                int colorInt = 0;
                try {
                    colorInt = Color.parseColor('#' + color.toUpperCase());
                } catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "VOTRE COULEUR N'EXISTE PAS", Toast.LENGTH_SHORT).show();
                }
                button4.setBackgroundColor(colorInt);
            }
        });

        return root;
    }

    public void openColorPicker(int num_btn){
        AmbilWarnaDialog ambilWarnaDialog = new AmbilWarnaDialog(this.getContext(), defaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                defaultColor = color;
                String colorH = Integer.toHexString(defaultColor);
                colorH = colorH.substring(2);
                switch (num_btn){
                    case 1:{
                        button1.setBackgroundColor(defaultColor);
                        colorNumber1.setText(colorH);
                        test = colorH;
                    }break;
                    case 2:{
                        button2.setBackgroundColor(defaultColor);
                        colorNumber2.setText(colorH);
                    }break;
                    case 3:{
                        button3.setBackgroundColor(defaultColor);
                        colorNumber3.setText(colorH);
                    }break;
                    case 4:{
                        button4.setBackgroundColor(defaultColor);
                        colorNumber4.setText(colorH);
                    }break;
                    default:
                        break;
                };
            }
        });
        ambilWarnaDialog.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}