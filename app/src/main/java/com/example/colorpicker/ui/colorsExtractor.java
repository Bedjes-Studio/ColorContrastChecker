package com.example.colorpicker.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.widget.Toast;

import androidx.palette.graphics.Palette;

import java.util.List;

public class colorsExtractor {

    private Context context;

    public colorsExtractor(Context context) {
        this.context = context;
    }

//    createPaletteAsync((ContextCompat.getDrawable(this,R.drawable.car) as BitmapDrawable).bitmap)

    // Note : this function in async
    public void extractColorsFromBitmap(Bitmap bitmap) {
        Palette.from(bitmap).maximumColorCount(6).resizeBitmapArea(bitmap.getWidth()).generate(palette -> {
            if (palette != null) {
                List<Palette.Swatch> swatches = palette.getSwatches();
                UpdateUI(swatches);
            } else {
                Toast.makeText(context, "Impossible de créer une palette à partir de cette image.", Toast.LENGTH_SHORT).show();
            }
        });
    }


    // TODO : update UI colors based on extracted colors
    private void UpdateUI(List<Palette.Swatch> swatches) {

        for (Palette.Swatch swatch : swatches) {
            // TIPS : this is how you get the color from swatch
            // you can then use color.red(), .green() ...
            Color color = Color.valueOf(swatch.getRgb());
            // TIPS : swatch provide example text color that fits good with swatch color
            Color textColor = Color.valueOf(swatch.getTitleTextColor());

            // TODO : here update UI colors => move this method and return palette ?
        }
    }
}
