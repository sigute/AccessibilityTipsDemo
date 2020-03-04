package com.github.sigute.accessibilitydemo

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.AccessibilityDelegateCompat
import androidx.core.view.ViewCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat
import kotlinx.android.synthetic.main.activity_roles_and_actions.default_actions_button
import kotlinx.android.synthetic.main.activity_roles_and_actions.login_text_view_as_button
import kotlinx.android.synthetic.main.activity_roles_and_actions.login_text_view_as_button_with_role
import kotlinx.android.synthetic.main.activity_roles_and_actions.set_actions_button

class RolesAndActionsActivity : AppCompatActivity(R.layout.activity_roles_and_actions) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //click listeners for buttons and views which look like buttons
        login_text_view_as_button.setOnClickListener {
            //no action
        }

        ViewCompat.setAccessibilityDelegate(login_text_view_as_button_with_role,
                object : AccessibilityDelegateCompat() {
                    override fun onInitializeAccessibilityNodeInfo(v: View, info: AccessibilityNodeInfoCompat) {
                        super.onInitializeAccessibilityNodeInfo(v, info)
                        info.roleDescription = getString(R.string.button_role)
                    }
                })
        login_text_view_as_button_with_role.setOnClickListener {
            //no action
        }

        //click listeners and actions for buttons with actions
        default_actions_button.setOnClickListener {
            //no action
        }
        default_actions_button.setOnLongClickListener {
            //no action
            true
        }
        set_actions_button.setOnClickListener {
            //no action
        }
        set_actions_button.setOnLongClickListener {
            //no action
            true
        }
        ViewCompat.setAccessibilityDelegate(set_actions_button,
                object : AccessibilityDelegateCompat() {
                    override fun onInitializeAccessibilityNodeInfo(v: View, info: AccessibilityNodeInfoCompat) {
                        super.onInitializeAccessibilityNodeInfo(v, info)

                        val clickAction = AccessibilityActionCompat(
                                AccessibilityNodeInfoCompat.ACTION_CLICK, getString(R.string.action_edit_note))
                        info.addAction(clickAction)

                        val longClickAction = AccessibilityActionCompat(
                                AccessibilityNodeInfoCompat.ACTION_LONG_CLICK, getString(R.string.action_copy_note))
                        info.addAction(longClickAction)
                    }
                })
    }
}
