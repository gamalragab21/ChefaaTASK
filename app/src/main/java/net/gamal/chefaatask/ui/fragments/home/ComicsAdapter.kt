package net.gamal.chefaatask.ui.fragments.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import net.gamal.chefaatask.databinding.LayoutComicsItemBinding
import net.gamal.chefea.festures.commics.domain.models.ComicsItem
import javax.inject.Inject

class ComicsAdapter @Inject constructor(
    private val context: Context
) : RecyclerView.Adapter<ComicsAdapter.ComicsVH>() {

    private var onItemClickListener: ((ComicsItem, View?) -> Unit)? = null
    private var originalComicsItems: List<ComicsItem> = emptyList()

    fun setOnItemClickListener(listener: (ComicsItem, View?) -> Unit) {
        onItemClickListener = listener
    }

    inner class ComicsVH(private val binding: LayoutComicsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(comicsItem: ComicsItem) = binding.apply {
//            cardViewCarColor.setCardBackgroundColor(Color.parseColor(colorImage.colorHex))
            comicsCaptionTv.text = comicsItem.title
            comicImage.setImageBitmap(comicsItem.thumbnail.imageBitmap!!)

            itemView.setOnClickListener {
                onItemClickListener?.invoke(comicsItem, null)
            }
            itemView.setOnLongClickListener {
                onItemClickListener?.invoke(comicsItem, it)
                true
            }
        }
    }

    var comicsItems: List<ComicsItem>
        get() = differ.currentList
        set(value) {
            originalComicsItems = value
            performSearch(searchQuery)
        }



    private val differCallBack = object : DiffUtil.ItemCallback<ComicsItem>() {
        override fun areItemsTheSame(oldItem: ComicsItem, newItem: ComicsItem) =
            oldItem.hashCode() == newItem.hashCode()

        override fun areContentsTheSame(oldItem: ComicsItem, newItem: ComicsItem) =
            oldItem.id == newItem.id
    }

    private val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ComicsVH(
            LayoutComicsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )

    override fun getItemCount() = comicsItems.size

    override fun onBindViewHolder(holder: ComicsVH, position: Int) {
        val comicsItem = comicsItems[position]
        holder.bindData(comicsItem)
    }

    private var searchQuery: String = ""

    fun searchByTitle(query: String) {
        searchQuery = query
        performSearch(query)
    }
    private fun performSearch(query: String) {
        val filteredList = if (query.isEmpty()) {
            originalComicsItems
        } else {
            originalComicsItems.filter { it.title.contains(query, true) }
        }
        differ.submitList(filteredList)
    }
}