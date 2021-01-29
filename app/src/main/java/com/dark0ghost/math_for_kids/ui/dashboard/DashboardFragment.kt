package com.dark0ghost.math_for_kids.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dark0ghost.math_for_kids.databinding.FragmentDashboardBinding
import com.dark0ghost.math_for_kids.math_impl.MathGenerate

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
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
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
       /* dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
        val result = mathGen.getData(listOf("+","-","/","*"),0,20)
        textView.text = result.first +" ${result.second}"
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}