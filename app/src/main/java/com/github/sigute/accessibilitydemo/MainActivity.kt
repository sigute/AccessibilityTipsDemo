package com.github.sigute.accessibilitydemo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        grouping_button.setOnClickListener { startActivity(Intent(this, GroupingActivity::class.java)) }
        roles_and_actions_button.setOnClickListener { startActivity(Intent(this, RolesAndActionsActivity::class.java)) }
        state_changes_button.setOnClickListener { startActivity(Intent(this, StateChangesActivity::class.java)) }
        recycler_view_scrolling_button.setOnClickListener { startActivity(Intent(this, RecyclerViewScrollingActivity::class.java)) }
        recycler_view_focus_button.setOnClickListener { startActivity(Intent(this, RecyclerViewFocusActivity::class.java)) }
    }
}
