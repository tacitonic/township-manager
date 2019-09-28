package com.township.manager;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class RegistrationSocietyStepTwo extends AppCompatActivity implements RegistrationSocietyStepTwoWingDetailsFragment.OnFragmentInteractionListener, RegistrationSocietyStepTwoAmenitiesDetailsFragment.OnFragmentInteractionListener, RegistrationSocietyStepTwoAdminLoginDetailsFragment.OnFragmentInteractionListener {

    public Button login;
    private ImageView[] mDots;
    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_society_step_two);

        Toolbar toolbar = (Toolbar) findViewById(R.id.registration_step_two_toolbar);
//        toolbar.setTitleTextColor(getColor(R.color.secondaryColor));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Society Details");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SliderAdapter sliderAdapter = new SliderAdapter(getSupportFragmentManager());
        sliderAdapter.addFragment(new RegistrationSocietyStepTwoWingDetailsFragment(), "");
        sliderAdapter.addFragment(new RegistrationSocietyStepTwoAmenitiesDetailsFragment(), "");
        sliderAdapter.addFragment(new RegistrationSocietyStepTwoAdminLoginDetailsFragment(), "");

        mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        mSlideViewPager.setAdapter(sliderAdapter);
        mSlideViewPager.setOffscreenPageLimit(2);

//        tabLayout = findViewById(R.id.registration_step_two_tab_dots);
//        tabLayout.setupWithViewPager(mSlideViewPager, true);

        mDotLayout = (LinearLayout) findViewById(R.id.dotsLayout);
        addDotsIndicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListener);

    }
    public void addDotsIndicator(int position){


        mDots = new ImageView[3];
        mDotLayout.removeAllViews();

        for (int i = 0; i < mDots.length; i++){

            mDots[i] = new ImageView(this);
            mDots[i].setImageResource(R.drawable.ic_dot_outlined);
            mDots[i].setPadding(4,0,4,0);
            mDotLayout.addView(mDots[i]);
        }

        if(mDots.length > 0){
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

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
