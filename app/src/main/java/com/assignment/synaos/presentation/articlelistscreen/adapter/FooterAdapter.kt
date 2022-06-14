package com.assignment.synaos.presentation.articlelistscreen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.assignment.synaos.common.ExtensionFunctions.gone
import com.assignment.synaos.databinding.ViewRetryBinding

class FooterAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<FooterAdapter.FooterViewHolder>() {
    private lateinit var context: Context
    override fun onBindViewHolder(holder: FooterViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    inner class FooterViewHolder(
        private val binding: ViewRetryBinding

    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState) {


            binding.btnRetry.setOnClickListener { retry.invoke() }
            when (loadState) {
                is LoadState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    footerErrorView(binding,true)
                }
                is LoadState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    footerErrorView(binding, false)
                }
                else -> {
                    binding.progressBar.visibility = View.GONE
                    footerErrorView(binding, true)
                }
            }
        }
    }

    fun footerErrorView(binding: ViewRetryBinding, isHide: Boolean) {
        binding.tvError.gone(isHide)
        binding.btnRetry.gone(isHide)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): FooterViewHolder {
        val binding = ViewRetryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context
        return FooterViewHolder(binding)
    }


}

