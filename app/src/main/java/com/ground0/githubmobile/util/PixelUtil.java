package com.ground0.githubmobile.util;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;
import java.util.Random;

/**
 * Created by zer0 on 14/11/16.
 */

public class PixelUtil {

  public static int convertDpToPixel(Context context, int dp) {
    if (context == null) {
      return 1;
    }
    float screen_density = context.getResources().getDisplayMetrics().density;
    return dp <= 0 ? 0 : (int) (screen_density * dp + 0.5f);
  }

  public static float convertPixelsToDp(float px, Context context) {

    float screenDensity = context.getResources().getDisplayMetrics().density;
    float dp = px / (screenDensity / 160f);
    return dp;
  }

  /*
  Function to calculate the scale down factor when loading an image in memory
  @param Bitmapfactor.options : contains the actual height and width of the image
  @param reqWidth and @param reqHeight : the required height and width
  returns the factor by which to scale down the image
   */
  public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth,
      int reqHeight) {
    // Raw height and width of image
    final int height = options.outHeight;
    final int width = options.outWidth;
    int inSampleSize = 1;

    if (height > reqHeight || width > reqWidth) {

      final int halfHeight = height / 2;
      final int halfWidth = width / 2;

      // Calculate the largest inSampleSize value that is a power of 2 and keeps both
      // height and width larger than the requested height and width.
      while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth) {
        inSampleSize *= 2;
      }
    }

    return inSampleSize;
  }

  public static Point getScreenDimensions(Context context) {
    WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    Display display = wm.getDefaultDisplay();
    Point point = new Point();
    display.getSize(point);
    return point;
  }

  public static long getRandomLongInRange(long start, long end) {
    long random = new Random().nextLong();
    long randomInInterval = random % (end - start);
    randomInInterval = Math.abs(randomInInterval);
    return start + randomInInterval;
  }
}
