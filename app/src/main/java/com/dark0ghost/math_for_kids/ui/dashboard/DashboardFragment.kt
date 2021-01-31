package com.dark0ghost.math_for_kids.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dark0ghost.math_for_kids.databinding.FragmentDashboardBinding
import org.dark0ghost.math_emaple_generator.MathGenerate
import org.dark0ghost.math_emaple_generator.MathOperation

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var editText: EditText
    private var _binding: FragmentDashboardBinding? = null
    private val mathGen: MathGenerate = MathGenerate()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        dashboardViewModel =
                ViewModelProvider(this).get(DashboardViewModel::class.java)
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        editText = binding.answer
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
       /* dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
        val result = mathGen.getData(listOf(MathOperation.Division,MathOperation.Multiplication),-100,1000,1)
                /*editText.setOnEditorActionListener { v, actionId, event ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEND -> {

                    true
                }
                else -> false
            }
        }*/
        textView.text = result.first +" ${result.second}"
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}