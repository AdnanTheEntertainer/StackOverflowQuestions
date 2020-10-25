package com.callsigntask.queries.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.callsigntask.queries.R
import com.callsigntask.queries.databinding.FragmentInputFormBinding
import com.callsigntask.queries.utils.CallSignUtils
import com.callsigntask.queries.utils.FragmentUtils
import com.callsigntask.queries.viewmodel.QueriesViewModel

class InputFormFragment : Fragment() {

    private var binding: FragmentInputFormBinding? = null
    private val queryViewModel: QueriesViewModel by activityViewModels()

    companion object {
        fun newInstance() = InputFormFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInputFormBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI(savedInstanceState)
    }

    private fun setupUI(savedInstanceState: Bundle?) {
        savedInstanceState?.let {
            binding?.etScore?.setText(it.getString("score"))
            binding?.etTag?.setText(it.getString("tag"))
        }

        binding?.btSubmit?.setOnClickListener {
            submitQueriesRequest()
        }
    }

    fun submitQueriesRequest() {
        CallSignUtils.hideSoftKeyboard(activity)
        //Tag format should be android;kotlin
        val fragment = QueriesListPagingFragment.newInstance(
            score = binding?.etScore?.text?.toString(),
            tag = binding?.etTag?.text?.toString(),
        )
//        startActivity(Intent(context, TestActivity::class.java))
        FragmentUtils.addFragmentWithStack(activity, fragment, R.id.container)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("score", binding?.etScore?.text?.toString())
        outState.putString("tag", binding?.etTag?.text?.toString())
    }

}