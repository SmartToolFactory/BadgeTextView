package com.smarttoolfactory.badgetextview

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.smarttoolfactory.badgetextview.databinding.ActivityMainBinding
import com.smarttoolfactory.badgetextview.ui.main.SectionsPagerAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabLayout: TabLayout = binding.tabs

        tabLayout.setupWithViewPager(viewPager)

        val tabView0 = LayoutInflater.from(this).inflate(R.layout.row_tab, null)
        tabView0.findViewById<TextView>(R.id.tvTabName)?.text = "CHATS"

        tabView0.findViewById<BadgeTextView>(R.id.tvBadge).setBadgeCount(100)

        val tabView1 = LayoutInflater.from(this).inflate(R.layout.row_tab, null)
        tabView1.findViewById<TextView>(R.id.tvTabName)?.text = "STATUS"

        val tabView2 = LayoutInflater.from(this).inflate(R.layout.row_tab, null)
        tabView2.findViewById<TextView>(R.id.tvTabName)?.text = "CALLS"

        tabView2.findViewById<BadgeTextView>(R.id.tvBadge).setBadgeCount(48)

        tabLayout.getTabAt(0)?.customView = tabView0
        tabLayout.getTabAt(1)?.customView = tabView1
        tabLayout.getTabAt(2)?.customView = tabView2
    }
}