package com.example.colorpicker.ui.notifications;

import static android.app.Activity.RESULT_OK;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
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

import com.example.colorpicker.R;
import com.example.colorpicker.databinding.FragmentNotificationsBinding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    ImageView imageView;

    Button mainColorButton1;
    Button mainColorButton2;
    Button mainColorButton3;
    Button mainColorButton4;

    private static final int RESULT_LOAD_IMAGE = 1000;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
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
                ClipData clip = ClipData.newPlainText("Color", button.getText());
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