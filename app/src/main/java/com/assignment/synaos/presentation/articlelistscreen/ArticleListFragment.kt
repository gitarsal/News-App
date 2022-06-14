package com.assignment.synaos.presentation.articlelistscreen

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.assignment.synaos.databinding.FragmentArticleListBinding
import com.assignment.synaos.domain.model.ArticleDomainModel
import com.assignment.synaos.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.DividerItemDecoration
import com.assignment.synaos.common.ExtensionFunctions.gone
import com.assignment.synaos.presentation.articlelistscreen.adapter.FooterAdapter
import com.assignment.synaos.presentation.articlelistscreen.adapter.PagerAdapter

@AndroidEntryPoint
class ArticleListFragment : BaseFragment<FragmentArticleListBinding>() {
    private val articleListViewModel: ArticleListViewModel by viewModels()
    private val articleAdapter = PagerAdapter()
    override fun constructViewBinding(): ViewBinding {
        return FragmentArticleListBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        articleListViewModel.getArticles()
    }

    override fun init(viewBinding: ViewBinding) {
        getViewBinding()?.bindAdapter(articleAdapter = articleAdapter)
        setArticleDataState()
        lifecycleScope.launch {
            articleAdapter.loadStateFlow.collect {
                if (it.refresh is LoadState.Error) {
                    showOrHideErrorView(true)
                } else {
                    showOrHideErrorView(false)
                }
            }
        }

        getViewBinding()?.retryView?.btnRetry?.setOnClickListener {
            articleAdapter.retry()
        }
        articleAdapter.onItemClick = {
            gotoDetailScreen(it)
        }

        getViewBinding()?.swipeRefresh?.setOnRefreshListener {
            articleAdapter.refresh()
            getViewBinding()?.swipeRefresh?.isRefreshing = false
        }
    }


    private fun gotoDetailScreen(articleDomainModel: ArticleDomainModel) {
        val direction =
            ArticleListFragmentDirections.actionArticleListToArticleDetail(articleDomainModel)
        findNavController().navigate(direction)
    }

    private fun showOrHideErrorView(isShow: Boolean) {
        getViewBinding()?.retryView?.btnRetry?.gone(isShow.not())
        getViewBinding()?.retryView?.tvError?.gone(isShow.not())
    }

    private fun onDataLoaded(articleList: PagingData<ArticleDomainModel>) {
        showOrHideErrorView(false)
        lifecycleScope.launch {
            articleAdapter.submitData(articleList)
        }
    }

    private fun setArticleDataState() {
        articleListViewModel.stateLiveData.observe(this) { state ->
            when (state) {
                is ArticleListViewModel.ArticleUIState.Loaded -> onDataLoaded(state.articleList)
            }
        }
    }
}

private fun FragmentArticleListBinding.bindAdapter(articleAdapter: PagerAdapter) {

    articleRv.adapter = articleAdapter.withLoadStateFooter(FooterAdapter(articleAdapter::retry))
    articleRv.layoutManager = LinearLayoutManager(articleRv.context)
    val decoration = DividerItemDecoration(articleRv.context, DividerItemDecoration.VERTICAL)
    articleRv.addItemDecoration(decoration)
}