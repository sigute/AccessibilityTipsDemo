package com.github.sigute.accessibilitydemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.sigute.accessibilitydemo.recyclerview.ITEMS
import com.github.sigute.accessibilitydemo.recyclerview.ItemsAdapter
import kotlinx.android.synthetic.main.activity_recycler_view_scrolling.non_scrolling_recycler_view
import kotlinx.android.synthetic.main.activity_recycler_view_scrolling.scrolling_recycler_view

class RecyclerViewScrollingActivity : AppCompatActivity(R.layout.activity_recycler_view_scrolling) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        non_scrolling_recycler_view.adapter = ItemsAdapter().apply {
            setItems(ITEMS)
        }
        scrolling_recycler_view.adapter = ItemsAdapter().apply {
            setItems(ITEMS)
        }
    }
}
