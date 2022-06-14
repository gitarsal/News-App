package com.assignment.synaos.presentation.articledetailscreen

import androidx.navigation.fragment.navArgs
import androidx.viewbinding.ViewBinding
import com.assignment.synaos.common.ExtensionFunctions.getFormattedDate
import com.assignment.synaos.databinding.FragmentArticleDetailBinding
import com.assignment.synaos.presentation.base.BaseFragment
import com.bumptech.glide.Glide
import android.graphics.Paint
import com.assignment.synaos.common.ExtensionFunctions.openUrl


class ArticleDetailScreenFragment : BaseFragment<FragmentArticleDetailBinding>() {
    private val args: ArticleDetailScreenFragmentArgs by navArgs()

    override fun constructViewBinding(): ViewBinding {
        return FragmentArticleDetailBinding.inflate(layoutInflater)
    }

    override fun init(viewBinding: ViewBinding) {
        getViewBinding()?.newsImage?.let {
            Glide.with(requireActivity())
                .load(args.articleModel.imageUrl)
                .into(it)
        }
        getViewBinding()?.toolbar?.title = args.articleModel.newsSite
        getViewBinding()?.title?.text = args.articleModel.title
        getViewBinding()?.summary?.text = args.articleModel.summary
        getViewBinding()?.updateDate?.text =  args.articleModel.updatedAt.getFormattedDate()
        getViewBinding()?.link?.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        getViewBinding()?.link?.setOnClickListener {
            requireActivity().openUrl(args.articleModel.url)
        }
    }


}