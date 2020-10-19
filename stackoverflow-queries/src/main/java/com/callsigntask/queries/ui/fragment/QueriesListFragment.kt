package com.callsigntask.queries.ui.fragment

import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.view.doOnNextLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.callsigntask.queries.R
import com.callsigntask.queries.data.model.response.QueryViewItem
import com.callsigntask.queries.data.network.ResponseState
import com.callsigntask.queries.databinding.FragmentQueriesListBinding
import com.callsigntask.queries.ui.adapter.QueriesAdapter
import com.callsigntask.queries.viewmodel.QueriesViewModel
import com.callsigntask.utils.EspressoIdlingResource
import com.recipe.task.interactor.QueryItemClickListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class QueriesListFragment : Fragment(), QueryItemClickListener, View.OnClickListener {

    private var queryTag: String = "android"
    private var queryScore: Int = 5
    private var binding: FragmentQueriesListBinding? = null
    private var queryAdapter: QueriesAdapter? = null
    private val queryViewModel: QueriesViewModel by viewModels()

    companion object {
        fun newInstance(score: String?, tag: String?): QueriesListFragment {
            val fragment = QueriesListFragment()
            val bundle = Bundle()
            bundle.putString("score", score)
            bundle.putString("tag", tag)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_queries_list, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    override fun onStart() {
        super.onStart()
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)

    }
    private fun setupUI() {
        queryScore = (arguments?.getString("score") ?: "5").toInt()
        queryTag = arguments?.getString("tag") ?: "android"
        binding?.btTryAgain?.setOnClickListener(this)
        queryAdapter = QueriesAdapter(context, this)
        binding?.rvQuery?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = queryAdapter
            setHasFixedSize(true)
        }
        // Only Debug variant. Espresso waiting lock this should be only inside dev build type
        EspressoIdlingResource.increment()

        // Api call
        queryViewModel.getUnAnsweredQueries(score = queryScore, tag = queryTag)
    }

    private fun registerApiCallback() {
        queryViewModel.queriesResponse.observe(viewLifecycleOwner, Observer {
            when (it) {
                is ResponseState.Loading -> {
                    showHideLoadingBar(it.loading)
                    binding?.tvEmpty?.visibility = View.GONE
                    binding?.btTryAgain?.visibility = View.GONE
                }

                is ResponseState.Success -> {
                    showHideLoadingBar(false)
                    setAdapter(it.data)
                    EspressoIdlingResource.decrement()
                }

                is ResponseState.Error -> {
                    showHideLoadingBar(false)
                    binding?.btTryAgain?.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun setAdapter(data: List<QueryViewItem>?) {
        if (data.isNullOrEmpty()) {
            queryAdapter?.clear()
            binding?.tvEmpty?.visibility = View.VISIBLE
        }
        else
            queryAdapter?.setData(data)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        registerApiCallback()
    }
    private fun showHideLoadingBar(loading: Boolean) {
        binding?.pbLoading?.visibility = if (loading)
            View.VISIBLE
        else
            View.GONE
    }
    override fun onQueryListClicked(query: QueryViewItem?) {
        val builder = CustomTabsIntent.Builder()
        builder.setToolbarColor(R.color.purple_500)
        val customTabsIntent = builder.build();
        customTabsIntent.launchUrl(context!!, Uri.parse(query?.link));
    }

    override fun onClick(view: View?) {
        // Api call
        queryViewModel.getUnAnsweredQueries(score = queryScore, tag = queryTag)
    }
}