package com.example.myapplication.ui.main;

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.controller.Settings

class SettingsFragment : Fragment() {

    //initialize a shared pref object
    val settingsMain = Settings(activity?.applicationContext)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val rootView: View =
            inflater.inflate(R.layout.settings_layout, container, false)


        val locationSetting: EditText = rootView.findViewById(R.id.locationSettingInputBox)
        val saveButton: Button = rootView.findViewById(R.id.saveButton)
        val cancelButton: Button = rootView.findViewById(R.id.cancelButton)

        locationSetting.setText(settingsMain.getSearchLocationSetting())

        saveButton.setOnClickListener{
            settingsMain.setSearchLocationSetting(locationSetting.text.toString())
        }

        cancelButton.setOnClickListener{
            //does nothing!
            //TODO: create cancel function that hides the keyboard
        }

        return rootView
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): SettingsFragment {
            return SettingsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}
