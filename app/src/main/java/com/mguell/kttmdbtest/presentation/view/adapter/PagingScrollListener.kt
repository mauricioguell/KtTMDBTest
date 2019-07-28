package com.mguell.kttmdbtest.presentation.view.adapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mguell.kttmdbtest.utils.Constants.QUERY_NUM_RESULTS

class PagingScrollListener(
    private val layoutManager: LinearLayoutManager,
    private val loadMoreItemsListener: LoadMoreItemsListener
) :
    RecyclerView.OnScrollListener() {

    private var isLastPage = false
    private var isLoading = false

    private val isNotFirstPage: Boolean
        get() = layoutManager.findFirstVisibleItemPosition() >= 0 && layoutManager.itemCount >= QUERY_NUM_RESULTS

    private val isNotLoadingInProgress: Boolean
        get() = !isLoading

    private val nextPageNumber: Int
        get() = layoutManager.itemCount / QUERY_NUM_RESULTS + 1

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (shouldLoadNextPage()) {
            loadMoreItemsListener.loadMoreItems(nextPageNumber)
        }
    }

    private fun shouldLoadNextPage(): Boolean {
        return isNotLoadingInProgress && userScrollsToNextPage() && isNotFirstPage && !isLastPage
    }

    private fun userScrollsToNextPage(): Boolean {
        return layoutManager.childCount + layoutManager.findFirstVisibleItemPosition() >= layoutManager.itemCount
    }

    fun markLoading(isLoading: Boolean) {
        this.isLoading = isLoading
    }

    fun markLastPage(isLastPage: Boolean) {
        this.isLastPage = isLastPage
    }

    interface LoadMoreItemsListener {
        fun loadMoreItems(page: Int)
    }
}
