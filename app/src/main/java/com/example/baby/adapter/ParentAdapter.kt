package com.example.baby.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.baby.R
import com.example.baby.model.ParentModel
import kotlinx.android.synthetic.main.header.view.*
import kotlinx.android.synthetic.main.parent_item.view.*

class ParentAdapter(private val parents: List<ParentModel>) :
    RecyclerView.Adapter<ParentAdapter.ParentViewHolder>() {

    private val ITEM_VIEW_TYPE_HEADER = 0
    private val ITEM_VIEW_TYPE_LIST = 1

    private val viewPool = RecyclerView.RecycledViewPool()

    // TO DO
    // not sure if I need this
    sealed class DataItem {

        data class ParentItem(val parent : ParentModel) : DataItem() {

        }

        object Header: DataItem() {

        }
    }

    override fun getItemViewType(position: Int): Int {

        return if (position == 0) {
            ITEM_VIEW_TYPE_HEADER
        } else {
            ITEM_VIEW_TYPE_LIST
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> HeaderViewHolder.from(parent)
            ITEM_VIEW_TYPE_LIST -> ParentViewHolder(parent)
            else -> throw ClassCastException("Unknown viewType ${viewType}")
        }
    }

    override fun onBindViewHolder(
        holder: ParentViewHolder,
        position: Int
    ) {
        val parent = parents[position]

        when (holder) {
            is ParentViewHolder -> {
                val childLayoutManager = LinearLayoutManager(
                    holder.recyclerView.context, RecyclerView.HORIZONTAL, false
                )

                childLayoutManager.initialPrefetchItemCount = 4
                holder.recyclerView.apply {
                    layoutManager = childLayoutManager
                    adapter = ChildAdapter(parent.children)
                    setRecycledViewPool(viewPool)
                }
            }
            is HeaderViewHolder -> {
                holder.itemView.header_text
            }
        }
    }

    override fun getItemCount(): Int {
        return parents.size + 1
    }

    // ViewHolder for Header
    class HeaderViewHolder(view: View): RecyclerView.ViewHolder(view) {
        companion object {
            fun from(parent: ViewGroup): HeaderViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.header, parent, false)
                return HeaderViewHolder(view)
            }
        }
    }

    // ViewHolder for Parent RecyclerView
    inner class ParentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recyclerView: RecyclerView = itemView.recycler_view
    }
}