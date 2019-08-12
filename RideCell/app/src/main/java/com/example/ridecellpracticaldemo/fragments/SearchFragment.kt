package com.example.ridecellpracticaldemo.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.ridecellpracticaldemo.R
import com.example.ridecellpracticaldemo.utils.AppUtility

class SearchFragment : Fragment() {



    var binding: com.example.ridecellpracticaldemo.databinding.FrgSearchTopicBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            com.example.ridecellpracticaldemo.databinding.FrgSearchTopicBinding.inflate(inflater, container, false)
                .also {
                    binding = it
                }.root
        }
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding!!.lifecycleOwner = this

        binding?.let {
            it.btnDone.setOnClickListener {

                val inputMethodManager = context!!.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)

                if (!(binding!!.edtTagText.text.toString().isNullOrEmpty())) {
                    AppUtility.searchString = binding!!.edtTagText.text.toString()
                    NavHostFragment.findNavController(this@SearchFragment).navigate(R.id.actionphotoListFragment)
                } else {
                    Toast.makeText(context, "Enter Text to search!", Toast.LENGTH_SHORT).show()
                }
            }
        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }


}
