package com.ench_wu.togglebutton;

import android.app.Activity;
import android.os.Bundle;

import com.ench_wu.togglebutton.View.ToggleButton;

public class MainActivity extends Activity {

    private ToggleButton toggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton);

        toggleButton.setSlideBackgroundResourse(R.mipmap.slide_button_background);
        toggleButton.setSwitchBackgroundResourse(R.mipmap.switch_background);
        toggleButton.setToggleState(ToggleButton.ToggleState.close);
    }

    public static void abc(){

    }

}
