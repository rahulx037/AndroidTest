package dragor.international.ui.test.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import dragor.international.R
import dragor.international.databinding.ExampleitemBinding
import dragor.international.ui.base.BaseAdapter
import dragor.international.api.model.Example
import java.util.*

class ExampleRecyclerAdapter(@NonNull private val clickListner: ExampleListClickItem ) : BaseAdapter<ExampleRecyclerAdapter.ItemsViewHolder, Example>(),
    Filterable {
    var mItemsEntities = mutableListOf<Example>()
    var mItemsEntitiesFiltered = mutableListOf<Example>()

    private var layoutInflater: LayoutInflater? = null

    private var mMenuItemsListCallback: ExampleListClickItem? = null
    private lateinit var binding: ExampleitemBinding

    init {
        mItemsEntities = ArrayList()
        mItemsEntitiesFiltered = ArrayList()
        mMenuItemsListCallback=clickListner
    }



    override fun setData(data: List<Example>) {
        mItemsEntities = data.toMutableList()
        mItemsEntitiesFiltered = data.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext())
        }
        binding = DataBindingUtil.inflate(
            layoutInflater!!,
            R.layout.exampleitem,
            parent,
            false
        )
        return ItemsViewHolder(binding, mMenuItemsListCallback)
    }

    override fun getItemCount(): Int {
        return mItemsEntitiesFiltered.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }


    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        holder.onBind(mItemsEntitiesFiltered[position])
    }

    public class ItemsViewHolder(
        val itemsbinding: ExampleitemBinding,
        callback: ExampleListClickItem?
    ) :
        RecyclerView.ViewHolder(itemsbinding.root) {
        fun onBind(itementity: Example?) {
            itemsbinding.example = itementity
            itemsbinding.executePendingBindings()
        }

        init {
            itemsbinding.root.setOnClickListener { view -> callback?.onListItemClick(itemsbinding.example) }
        }
    }

    override fun getFilter(): Filter? {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                mItemsEntitiesFiltered = if (charString.isEmpty()) {
                    mItemsEntities
                } else {
                    val filteredList: MutableList<Example> = ArrayList<Example>()
                    for (row in mItemsEntities) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                       /* if (String.valueOf(row.category) == charString || row.firstName
                                .equals(charString)
                        ) {
                            filteredList.add(row)
                        }*/
                    }
                    filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = mItemsEntitiesFiltered
                return filterResults
            }

            override fun publishResults(
                charSequence: CharSequence,
                filterResults: FilterResults
            ) {
                mItemsEntitiesFiltered = filterResults.values as MutableList<Example>
                notifyDataSetChanged()
            }
        }
    }

}