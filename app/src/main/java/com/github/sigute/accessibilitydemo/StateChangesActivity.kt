package com.github.sigute.accessibilitydemo

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.AccessibilityDelegateCompat
import androidx.core.view.ViewCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
import kotlinx.android.synthetic.main.activity_state_changes.favorite_button_custom
import kotlinx.android.synthetic.main.activity_state_changes.favorite_button_default

class StateChangesActivity : AppCompatActivity(R.layout.activity_state_changes) {

    private var defaultSelected = false
    private var customSelected = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        favorite_button_default.setOnClickListener {
            defaultSelected = !defaultSelected
            setDrawable(favorite_button_default, defaultSelected)
        }

        //initial setup
        setUpCustomButtonAccessibility()
        favorite_button_custom.setOnClickListener {
            customSelected = !customSelected
            setDrawable(favorite_button_custom, customSelected)
            //updated after user action
            setUpCustomButtonAccessibility()
        }
    }

    private fun setUpCustomButtonAccessibility() {
        favorite_button_custom.contentDescription = getString(
                if (customSelected) {
                    R.string.content_description_favorite
                } else {
                    R.string.content_description_not_favorite
                })

        ViewCompat.setAccessibilityDelegate(favorite_button_custom, object : AccessibilityDelegateCompat() {
            override fun onInitializeAccessibilityNodeInfo(v: View, info: AccessibilityNodeInfoCompat) {
                super.onInitializeAccessibilityNodeInfo(v, info)
                val clickAction = AccessibilityNodeInfoCompat.AccessibilityActionCompat(
                        AccessibilityNodeInfoCompat.ACTION_CLICK, getString(
                        if (customSelected) {
                            R.string.content_description_action_remove_from_favorites
                        } else {
                            R.string.content_description_action_favorite
                        }
                ))
                info.addAction(clickAction)
            }
        })
    }

    private fun setDrawable(imageButton: ImageButton, selected: Boolean) {
        imageButton.setImageDrawable(ContextCompat.getDrawable(this,
                if (selected) {
                    R.drawable.ic_favorite_black_24dp
                } else {
                    R.drawable.ic_favorite_border_black_24dp
                }
        )
        )
    }
}
