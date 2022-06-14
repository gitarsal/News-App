package com.assignment.synaos.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticleDomainModel(
    val id: Int,
    val featured: Boolean,
    val title: String,
    val imageUrl: String,
    val summary: String,
    val newsSite: String,
    val updatedAt: String,
    val publishedAt: String,
    val url: String

):Parcelable
