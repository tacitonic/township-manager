package com.township.manager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class RegistrationStepTwoActivity extends AppCompatActivity implements RegistrationStepTwoWingDetailsFragment.OnFragmentInteractionListener, RegistrationStepTwoAmenityDetailsFragment.OnFragmentInteractionListener, RegistrationStepTwoLoginDetailsFragment.OnFragmentInteractionListener {

    public Button login;
    private ImageView[] mDots;
    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;
    private TabLayout tabLayout;
    String application_id;

    RegistrationStepTwoWingDetailsFragment wingDetailsFragment;
    RegistrationStepTwoAmenityDetailsFragment amenitiesDetailsFragment;
    RegistrationStepTwoLoginDetailsFragment adminLoginDetailsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_step_two);

        Toolbar toolbar = (Toolbar) findViewById(R.id.registration_step_two_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Society Details");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        wingDetailsFragment = new RegistrationStepTwoWingDetailsFragment();
        amenitiesDetailsFragment = new RegistrationStepTwoAmenityDetailsFragment();
        adminLoginDetailsFragment = new RegistrationStepTwoLoginDetailsFragment();

        SliderAdapter sliderAdapter = new SliderAdapter(getSupportFragmentManager());
        sliderAdapter.addFragment(wingDetailsFragment, "");
        sliderAdapter.addFragment(amenitiesDetailsFragment, "");
        sliderAdapter.addFragment(adminLoginDetailsFragment, "");

        mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        mSlideViewPager.setAdapter(sliderAdapter);
        mSlideViewPager.setOffscreenPageLimit(2);

        mDotLayout = (LinearLayout) findViewById(R.id.dotsLayout);
        addDotsIndicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListener);
        Intent intent = getIntent();
        application_id = intent.getStringExtra("application_id");
        adminLoginDetailsFragment.setApplication_id(application_id);

    }

    public void addDotsIndicator(int position) {
        mDots = new ImageView[3];
        mDotLayout.removeAllViews();

        for (int i = 0; i < mDots.length; i++) {

            mDots[i] = new ImageView(this);
            mDots[i].setImageResource(R.drawable.ic_dot_outlined);
            mDots[i].setPadding(4, 0, 4, 0);
            mDotLayout.addView(mDots[i]);
        }

        if (mDots.length > 0) {
            mDots[position].setImageResource(R.drawable.ic_dot_filled);
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
            try {
                InputMethodManager manager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public ArrayList<Wing> getWingsData() {
        return wingDetailsFragment.getWingsFromAdapter();
    }

    @Override
    public ArrayList<Amenity> getAmenitiesData() {
        return amenitiesDetailsFragment.getAmenityDataFromAdapter();
    }

    @Override
    public Boolean getWingsError() {
        return wingDetailsFragment.getWingsError();
    }

    @Override
    public Boolean getAmenitiesError() {
        return amenitiesDetailsFragment.getAmenitiesError();
    }

}
