package com.dark0ghost.math_for_kids.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dark0ghost.math_for_kids.databinding.FragmentDashboardBinding
import com.dark0ghost.math_for_kids.score.Score
import org.dark0ghost.math_emaple_generator.MathGenerate
import org.dark0ghost.math_emaple_generator.MathOperation
import java.math.BigDecimal


class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    private lateinit var editText: EditText

    private lateinit var scoreView: TextView

    private lateinit var mathExample: Pair<String, BigDecimal>

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
        scoreView = binding.score
        val root: View = binding.root
        val textView: TextView = binding.textDashboard
        val mathOperation = listOf(MathOperation.Division,MathOperation.Multiplication)
        mathExample = mathGen.getData(mathOperation,5,25,1)
        scoreView.text = Score.score.toString()
        editText.setOnEditorActionListener {
            _, _, _ ->
            Log.d("edittext",editText.text.toString())
            if(editText.text.toString() == mathExample.second.toString()){
                Score.updateScore()
                scoreView.text = Score.score.toString()
                mathExample = mathGen.getData(mathOperation,5,25,1)
                textView.text = mathExample.first +" ${mathExample.second}"
                editText.text.clear()
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
        textView.text = mathExample.first +" ${mathExample.second}"
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Score.clearScore()
        _binding = null
    }
}