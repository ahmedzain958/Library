package com.zainco.library.singlecheckablerecyclerview


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.zainco.library.R
import com.zainco.library.databinding.ItemPharmacyBinding
import kotlin.properties.Delegates


class PharmaciesAdapter(var pharmaciesList: List<Pharmacy>) :
    RecyclerView.Adapter<PharmaciesAdapter.PharmaciesViewHolder>() {
    // if checkedPosition = -1, there is no default selection
// if checkedPosition = 0, 1st item is selected by default
    private var selectedPosition by
    Delegates.observable(-1) { property, oldPos, newPos ->
        if (newPos in pharmaciesList.indices) {
            notifyItemChanged(oldPos)
            notifyItemChanged(newPos)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PharmaciesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewBinding: ItemPharmacyBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_pharmacy, parent, false)
        return PharmaciesViewHolder(viewBinding)
    }

    override fun getItemCount() = pharmaciesList.size

    override fun onBindViewHolder(holder: PharmaciesViewHolder, position: Int) {
        if (position in pharmaciesList.indices) {
            holder.bind(pharmaciesList[position], position == selectedPosition)
            holder.itemView.setOnClickListener {
                if (selectedPosition == position)
                    selectedPosition = -1
                else
                    selectedPosition = position
            }
        }
    }


    inner class PharmaciesViewHolder(private val itemPharmacyBinding: ItemPharmacyBinding) :
        RecyclerView.ViewHolder(itemPharmacyBinding.root) {

        fun bind(pharmacy: Pharmacy, selected: Boolean) {
            setIsRecyclable(false)
            with(itemPharmacyBinding) {
                imagCheck.visibility = if (selected) View.VISIBLE else View.INVISIBLE
                textviewPharmacy.text = pharmacy.customerName
            }
        }

    }
}
