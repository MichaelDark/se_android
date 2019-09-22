package ua.temnokhud.app.util;

import android.app.Activity;
import android.content.Intent;

public class ActivityUtils {

    private ActivityUtils() {
    }

    public static <T extends Activity> void startActivity(Activity activity, Class<T> targetActivityClass) {
        Intent activityIntent = new Intent(activity, targetActivityClass);
        activity.startActivity(activityIntent);
    }

}
