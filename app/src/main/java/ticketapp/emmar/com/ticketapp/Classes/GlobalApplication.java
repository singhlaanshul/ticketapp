package ticketapp.emmar.com.ticketapp.Classes;

import android.app.Application;

import ticketapp.emmar.com.ticketapp.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


public class GlobalApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/ClanPro-NarrMedium.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
