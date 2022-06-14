package com.assignment.synaos.presentation.articlelistscreen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.assignment.synaos.R
import com.assignment.synaos.common.ExtensionFunctions.getFormattedDate
import com.assignment.synaos.databinding.ItemArticleBinding
import com.assignment.synaos.domain.model.ArticleDomainModel
import com.bumptech.glide.Glide

class PagerAdapter :
    PagingDataAdapter<ArticleDomainModel, PagerAdapter.ArticleItemViewHolder>(ArticleDiffCallback2()) {
    private lateinit var context: Context
    var onItemClick: ((ArticleDomainModel) -> Unit)? = null

    inner class ArticleItemViewHolder(
        private val binding: ItemArticleBinding

    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(articleDomainModel: ArticleDomainModel?) {
            binding.articleTitle.text = articleDomainModel?.newsSite
            binding.summaryDescription.text = articleDomainModel?.title
            Glide.with(context)
                .load(articleDomainModel?.imageUrl).placeholder(R.drawable.progress_animation)
                .into(binding.image)
            binding.date.text = articleDomainModel?.updatedAt?.getFormattedDate()
            binding.root.setOnClickListener {
                if (articleDomainModel != null)
                    onItemClick?.invoke(articleDomainModel)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleItemViewHolder {
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context
        return ArticleItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}


class ArticleDiffCallback2 : DiffUtil.ItemCallback<ArticleDomainModel>() {

    override fun areItemsTheSame(
        oldItem: ArticleDomainModel,
        newItem: ArticleDomainModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: ArticleDomainModel,
        newItem: ArticleDomainModel
    ): Boolean {
        return oldItem.id == newItem.id && oldItem.id == newItem.id
    }
}


