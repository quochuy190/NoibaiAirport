package neo.com.noibaiairport.untils;


import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import neo.com.noibaiairport.App;
import neo.com.noibaiairport.Config.Constants;
import neo.com.noibaiairport.Model.Language;
import neo.com.noibaiairport.R;

public class LanguageUtils {

    private static Language sCurrentLanguage = null;

    public static Language getCurrentLanguage() {
        if (sCurrentLanguage == null) {
            sCurrentLanguage = initCurrentLanguage();
        }
        return sCurrentLanguage;
    }

    /**
     * check language exist in SharedPrefs, if not exist then default language is English
     */
    private static Language initCurrentLanguage() {
        Language currentLanguage =
                SharedPrefs.getInstance().get(SharedPrefs.LANGUAGE, Language.class);
        if (currentLanguage != null) {
            return currentLanguage;
        }
        currentLanguage = new Language(Constants.Value.DEFAULT_LANGUAGE_ID,
                App.self().getString(R.string.language_english),
                App.self().getString(R.string.language_english_code));
        SharedPrefs.getInstance().put(SharedPrefs.LANGUAGE, currentLanguage);
        return currentLanguage;
    }

    /**
     * return language list from string.xml
     */
    public static List<Language> getLanguageData() {
        List<Language> languageList = new ArrayList<>();
        List<String> languageNames =
                Arrays.asList(App.self().getResources().getStringArray(R.array.language_names));
        List<String> languageCodes =
                Arrays.asList(App.self().getResources().getStringArray(R.array.language_codes));
        if (languageNames.size() != languageCodes.size()) {
            // error, make sure these arrays are same size
            return languageList;
        }
        for (int i = 0, size = languageNames.size(); i < size; i++) {
            languageList.add(new Language(i, languageNames.get(i), languageCodes.get(i)));
        }
        return languageList;
    }

    /**
     * load current locale and change language
     */
    public static void loadLocale() {
        Log.i("language", "onResume_main");
        changeLanguage(initCurrentLanguage());
    }

    /**
     * change app language
     */


    @SuppressWarnings("deprecation")
    public static void changeLanguage(Language language) {
        Log.i("language", "change_language");
        SharedPrefs.getInstance().put(SharedPrefs.LANGUAGE, language);
        sCurrentLanguage = language;
        // Locale locale = new Locale(language);
        Locale locale = new Locale(language.getCode());
        Resources resources = App.self().getResources();
        Configuration configuration = resources.getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(locale);
            resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        } else {
            Configuration config = new Configuration();
            config.locale = locale;
            resources.updateConfiguration(configuration, resources.getDisplayMetrics());
         /*   getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());*/
        }

    }
}
