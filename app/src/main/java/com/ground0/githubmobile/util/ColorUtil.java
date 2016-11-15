package com.ground0.githubmobile.util;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import com.ground0.githubmobile.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by zer0 on 14/11/16.
 */

public class ColorUtil {

  public static List<Integer> materialColors = new ArrayList<>();

  private static void initSparseArray(Context context) {
    materialColors.add(ContextCompat.getColor(context, R.color.md_amber_500));
    materialColors.add(ContextCompat.getColor(context, R.color.md_blue_500));
    materialColors.add(ContextCompat.getColor(context, R.color.md_cyan_500));
    materialColors.add(ContextCompat.getColor(context, R.color.md_green_500));
    materialColors.add(ContextCompat.getColor(context, R.color.md_indigo_500));
    materialColors.add(ContextCompat.getColor(context, R.color.md_lime_500));
    materialColors.add(ContextCompat.getColor(context, R.color.md_orange_500));
    materialColors.add(ContextCompat.getColor(context, R.color.md_pink_500));
    materialColors.add(ContextCompat.getColor(context, R.color.md_purple_500));
    materialColors.add(ContextCompat.getColor(context, R.color.md_red_500));
    materialColors.add(ContextCompat.getColor(context, R.color.md_teal_500));
  }

  public static Drawable getRandomMaterialDrawable(Context context) {
    initSparseArray(context);
    int index = (new Random().nextInt(materialColors.size()));
    return new ColorDrawable(materialColors.get(index));
  }

  public static Drawable getMaterialDrawable(Context context, String name) {
    initSparseArray(context);
    int index = (new Random(name.hashCode()).nextInt(materialColors.size()));
    return new ColorDrawable(materialColors.get(index));
  }
}