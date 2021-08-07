package com.example.slideindicatorkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.LinearLayout
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback

class MainActivity : AppCompatActivity() {

    private lateinit var dotsLayout: LinearLayout
    private lateinit var adapter: SliderAdapter
    private lateinit var pager2: ViewPager2
    private lateinit var list: IntArray
    private lateinit var dots: Array<TextView?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dotsLayout = findViewById(R.id.dots_container)
        pager2 = findViewById(R.id.view_pager2)

        list = IntArray(5)
        list[0] = resources.getColor(R.color.purple_700)
        list[1] = resources.getColor(R.color.colorPrimary)
        list[2] = resources.getColor(R.color.darker_gray)
        list[3] = resources.getColor(R.color.teal_700)
        list[4] = resources.getColor(R.color.green)

        adapter = SliderAdapter(list)
        pager2.adapter = adapter

        dots = arrayOfNulls(5)
        dotsIndicator()

        pager2.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                selectedIndicator(position)
                super.onPageSelected(position)
            }
        })
    }
    private fun selectedIndicator(position: Int) {
        for (i in dots.indices) {
            if (i == position) {
                dots[i]!!.setTextColor(list[position])
            } else {
                dots[i]!!.setTextColor(resources.getColor(R.color.white))
            }
        }
    }

    private fun dotsIndicator() {
        for (i in dots.indices) {
            dots[i] = TextView(this)
            dots[i]!!.text = Html.fromHtml("&#9673;")
            dots[i]!!.textSize = 18f
            dotsLayout.addView(dots[i])
        }
    }
}