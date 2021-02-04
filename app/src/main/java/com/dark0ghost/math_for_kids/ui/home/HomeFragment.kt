package com.dark0ghost.math_for_kids.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dark0ghost.math_for_kids.R
import com.dark0ghost.math_for_kids.databinding.FragmentHomeBinding
import com.dark0ghost.math_for_kids.score.Score

class HomeFragment : Fragment() {

    private lateinit var score: Score

    private lateinit var homeViewModel: HomeViewModel

    private lateinit var editText: EditText

    private var _binding: FragmentHomeBinding? = null




    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val scoreTextView: TextView = binding.score
        val textView: TextView = binding.textHome
        editText = binding.lenExample
        val text: String = "${getText(R.string.title_max_score)} ${Score.maxScore}"
        scoreTextView.text = text

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}