package com.example.nsntechandroid.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nsntechandroid.R
import com.example.nsntechandroid.auth.RegisterActivity
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_onboarding.*

class OnboardingActivity : AppCompatActivity() {

    private lateinit var onboardingAdapter: OnboardingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        setupOnboarding()

        btnSkip.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
    }

    private fun setupOnboarding() {
        val onboardingData = listOf(
            Onboarding(
                R.drawable.ic_item_1,
                R.string.title_onboarding_1,
                R.string.subtitle_onboarding_1
            ),
            Onboarding(
                R.drawable.ic_item_2,
                R.string.title_onboarding_2,
                R.string.subtitle_onboarding_2
            ),
            Onboarding(
                R.drawable.ic_item_3,
                R.string.title_onboarding_3,
                R.string.subtitle_onboarding_3
            )
        )
        onboardingAdapter = OnboardingAdapter(onboardingData)
        onbordingPager.adapter = onboardingAdapter

        TabLayoutMediator(indicatorView, onbordingPager) { tab, position ->
            //some Implementation, not used for now.
        }.attach()
    }
}