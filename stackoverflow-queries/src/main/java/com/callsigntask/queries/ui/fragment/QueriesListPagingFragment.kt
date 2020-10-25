package com.callsigntask.queries.ui.fragment

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.callsigntask.queries.R
import com.callsigntask.queries.data.model.response.QueryViewItem
import com.callsigntask.queries.databinding.FragmentQueriesListBinding
import com.callsigntask.queries.ui.adapter.QueryLoadStateAdapter
import com.callsigntask.queries.ui.adapter.QueryPagingAdapter
import com.callsigntask.queries.viewmodel.QueriesViewModel
import com.recipe.task.interactor.QueryItemClickListener

class QueriesListPagingFragment : Fragment(R.layout.fragment_queries_list), QueryItemClickListener,
    View.OnClickListener {

    private var queryTag: String = "android"
    private var queryScore: Int = 5
    private var _binding: FragmentQueriesListBinding? = null
    private val binding get() = _binding!!
    private var queryAdapter: QueryPagingAdapter? = null
    private val queryViewModel: QueriesViewModel by viewModels()

    companion object {
        fun newInstance(score: String?, tag: String?): QueriesListPagingFragment {
            val fragment = QueriesListPagingFragment()
            val bundle = Bundle()
            bundle.putString("score", score)
            bundle.putString("tag", tag)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.let {
            Toast.makeText(context, "Tag: ${it.getString("tag")} Score: ${it.getInt("score")}", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentQueriesListBinding.bind(view)
        setupUI()
        if(savedInstanceState == null){
            // Api call
            queryViewModel.getPagingList(hashMapOf("score" to queryScore.toString(), "tag" to queryTag))        }
    }

    private fun setupUI() {
        queryScore = (arguments?.getString("score") ?: "5").toInt()
        queryTag = arguments?.getString("tag") ?: "android"
        binding.btTryAgain.setOnClickListener(this)
        queryAdapter = QueryPagingAdapter(this)
        binding.rvQuery.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = queryAdapter?.withLoadStateFooter(
                footer = QueryLoadStateAdapter { queryAdapter?.retry() }
            )
        }
        queryAdapter?.addLoadStateListener { loadState ->

            if (loadState.refresh is LoadState.Loading )
                showHideLoadingBar(true)
            else if (loadState.refresh is LoadState.NotLoading ||
                loadState.append is LoadState.NotLoading)
                showHideLoadingBar(false)

            val errorState = when{
                loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                else  -> null
            }
            errorState?.let {
                binding.tvEmpty.visibility = if (queryAdapter?.itemCount ?: 0 > 0) View.GONE
                else
                    View.VISIBLE
            }
        }
        // Only Debug variant. Espresso waiting lock this should be only inside dev build type
//        EspressoIdlingResource.increment()
    }

    private fun registerApiCallback() {
        queryViewModel.queryPaging?.observe(viewLifecycleOwner, Observer {
            queryAdapter?.submitData(viewLifecycleOwner.lifecycle, it)

        })
    }

    /*private fun setAdapter(data: List<QueryViewItem>?) {
        if (data.isNullOrEmpty()) {
            queryAdapter?.clear()
            binding?.tvEmpty?.visibility = View.VISIBLE
        }
        else
            queryAdapter?.submitData(data)

    }*/

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        registerApiCallback()
    }

    private fun showHideLoadingBar(loading: Boolean) {
        binding.pbLoading.visibility = if (loading)
            View.VISIBLE
        else
            View.GONE
    }

    override fun onQueryListClicked(query: QueryViewItem?) {
        val builder = CustomTabsIntent.Builder()
        builder.setToolbarColor(R.color.purple_500)
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(context!!, Uri.parse(query?.link))
    }

    override fun onClick(view: View?) {
        // Api call
        queryAdapter?.refresh()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("score", queryScore)
        outState.putString("tag", queryTag)
    }


    override fun onDestroyView() {
        super.onDestroyView()
//        _binding = null
        binding.rvQuery.adapter = null
    }
}