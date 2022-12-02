package com.example.colorpicker.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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
    ConstraintLayout constraintLayout;
    int defaultColor;
    TextView colorNumber;

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
        colorNumber = (TextView) root.findViewById(R.id.colorNumber);

        constraintLayout = (ConstraintLayout) root.findViewById(R.id.layout);

        defaultColor = ContextCompat.getColor(this.getContext(), R.color.white);

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
                switch (num_btn){
                    case 1:{
                        button1.setBackgroundColor(defaultColor);
                    }break;
                    case 2:{
                        button2.setBackgroundColor(defaultColor);
                    }break;
                    case 3:{
                        button3.setBackgroundColor(defaultColor);
                    }break;
                    case 4:{
                        button4.setBackgroundColor(defaultColor);
                    }break;
                    default:
                        break;
                };
                colorNumber.setText(String.valueOf(defaultColor)); //color.parseColor
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