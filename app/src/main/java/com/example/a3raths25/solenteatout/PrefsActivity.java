package com.example.a3raths25.solenteatout;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;
import android.os.Bundle;

/**
 * Created by 3RATHS25 on 19/04/2018.
 */

public class PrefsActivity extends PreferenceActivity{
    public void onCreate (Bundle savedInstanceState)
{
    super.onCreate(savedInstanceState);
    addPreferencesFromResource(R.xml.preferences);
}
}
