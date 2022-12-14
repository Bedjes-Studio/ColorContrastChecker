package com.example.colorpicker.ui.home;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import com.example.colorpicker.databinding.FragmentPaletteBinding;

import yuku.ambilwarna.AmbilWarnaDialog;

public class PaletteFragment extends Fragment {

    private FragmentPaletteBinding binding;

    Button button1, button2, button3, button4;
    ImageButton ibtn1, ibtn2, ibtn3, ibtn4;
    ImageButton cpy1, cpy2, cpy3, cpy4;

    ConstraintLayout constraintLayout;
    int defaultColor;
    TextView colorNumber1, colorNumber2, colorNumber3, colorNumber4;
    String test;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.help, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.help: {
                AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                alertDialog.setTitle("Comment utiliser le mode Palete ?");
                alertDialog.setMessage("Cette section permet de gérer une palette de couleurs.\n\nChoisissez une couleur en cliquant sur l'une des boîtes ou en écrivant le code couleur en héxadécimal (ne pas ajouter le \"#\") puis en cliquant sur le crayon.");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Compris !",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PaletteViewModel paletteViewModel =
                new ViewModelProvider(this).get(PaletteViewModel.class);

        binding = FragmentPaletteBinding.inflate(inflater, container, false);
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

        cpy1 = (ImageButton) root.findViewById(R.id.cpy1);
        cpy2 = (ImageButton) root.findViewById(R.id.cpy2);
        cpy3 = (ImageButton) root.findViewById(R.id.cpy3);
        cpy4 = (ImageButton) root.findViewById(R.id.cpy4);

        System.out.println("test" + colorNumber1.getText().toString());

        constraintLayout = (ConstraintLayout) root.findViewById(R.id.layout);

        defaultColor = ContextCompat.getColor(this.getContext(), R.color.black);
        colorNumber1.setText("CC0000");
        colorNumber2.setText("00CC00");
        colorNumber3.setText("0000DD");
        colorNumber4.setText("000000");

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
                try {
                    String color = colorNumber1.getText().toString();
                    int colorInt = 0;
                    colorInt = Color.parseColor('#' + color.toUpperCase());
                    button1.setBackgroundColor(colorInt);
                } catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "VOTRE COULEUR N'EXISTE PAS", Toast.LENGTH_SHORT).show();
                }
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

        cpy1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String color = colorNumber1.getText().toString();
                    int colorInt = 0;
                    colorInt = Color.parseColor('#' + color.toUpperCase());
                    copyToClipboard(color.toUpperCase());
                } catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "VOTRE COULEUR N'EXISTE PAS", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cpy2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String color = colorNumber2.getText().toString();
                    int colorInt = 0;
                    colorInt = Color.parseColor('#' + color.toUpperCase());
                    copyToClipboard(color.toUpperCase());
                } catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "VOTRE COULEUR N'EXISTE PAS", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cpy3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String color = colorNumber3.getText().toString();
                    int colorInt = 0;
                    colorInt = Color.parseColor('#' + color.toUpperCase());
                    copyToClipboard(color.toUpperCase());
                } catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "VOTRE COULEUR N'EXISTE PAS", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cpy4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String color = colorNumber4.getText().toString();
                    int colorInt = 0;
                    colorInt = Color.parseColor('#' + color.toUpperCase());
                    copyToClipboard(color.toUpperCase());
                } catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "VOTRE COULEUR N'EXISTE PAS", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return root;
    }

    private void copyToClipboard(String string) {
        ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Color", string);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(getContext(), "Couleur copiée dans le presse papier", Toast.LENGTH_SHORT).show();
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