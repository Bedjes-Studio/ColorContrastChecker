package com.example.colorpicker.ui.image;

import static android.app.Activity.RESULT_OK;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.palette.graphics.Palette;

import com.example.colorpicker.MainActivity;
import com.example.colorpicker.R;
import com.example.colorpicker.databinding.FragmentImageBinding;

import java.io.IOException;
import java.util.List;

public class ImageFragment extends Fragment {

    private FragmentImageBinding binding;

    ImageView imageView;

    Button mainColorButton1;
    Button mainColorButton2;
    Button mainColorButton3;
    Button mainColorButton4;

    private static final int RESULT_LOAD_IMAGE = 1000;

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
                // navigate to settings screen
                Toast.makeText(getContext(), "help image", Toast.LENGTH_SHORT).show();
                AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                alertDialog.setTitle("Image");
                alertDialog.setMessage("Cette section permet de générer un thème à partir d'une image.\n\nCliquez sur l'image pour en choisir une nouvelle. Cliquez sur l'une des quatres boites pour copier sa couleur dans le presse papier.");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Compris !",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        ImageViewModel imageViewModel =
                new ViewModelProvider(this).get(ImageViewModel.class);
        binding = FragmentImageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        imageView = root.findViewById(R.id.image1);
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
            }
        });

        mainColorButton1 = root.findViewById(R.id.color_1_btn);
        mainColorButton2 = root.findViewById(R.id.color_2_btn);
        mainColorButton3 = root.findViewById(R.id.color_3_btn);
        mainColorButton4 = root.findViewById(R.id.color_4_btn);

        View.OnClickListener clipboardCopy = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Color", button.getText().toString().substring(1));
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getContext(), "Couleur copiée dans le presse papier", Toast.LENGTH_SHORT).show();
            }
        };

        mainColorButton1.setOnClickListener(clipboardCopy);
        mainColorButton2.setOnClickListener(clipboardCopy);
        mainColorButton3.setOnClickListener(clipboardCopy);
        mainColorButton4.setOnClickListener(clipboardCopy);

        extractColorsFromBitmap(((BitmapDrawable) imageView.getDrawable()).getBitmap());
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            imageView.setImageURI(imageUri);
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), imageUri);
                extractColorsFromBitmap(bitmap);
            } catch (IOException e) {
                Toast.makeText(getContext(), "Impossible de charger l'image", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }

    private void extractColorsFromBitmap(Bitmap bitmap) {
        Palette.from(bitmap).maximumColorCount(4).resizeBitmapArea(bitmap.getWidth()).generate(palette -> {
            if (palette != null) {
                List<Palette.Swatch> swatches = palette.getSwatches();
                UpdateMainColors(swatches);
            } else {
                Toast.makeText(getContext(), "Impossible de créer une palette à partir de cette image.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void UpdateMainColors(List<Palette.Swatch> swatches) {
        applyMainColor(mainColorButton1, swatches.get(0));
        applyMainColor(mainColorButton2, swatches.get(1));
        applyMainColor(mainColorButton3, swatches.get(2));
        applyMainColor(mainColorButton4, swatches.get(3));
    }

    private void applyMainColor(Button button, Palette.Swatch swatch) {
        button.setBackgroundColor(swatch.getRgb());
        button.setText(String.format("#%06X", (0xFFFFFF & swatch.getRgb())));
        button.setTextColor(swatch.getTitleTextColor());
    }
}