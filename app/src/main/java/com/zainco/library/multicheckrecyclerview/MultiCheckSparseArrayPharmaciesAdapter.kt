package com.zainco.library.multicheckrecyclerview


import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.zainco.library.R
import com.zainco.library.databinding.ItemPharmacyBinding
import com.zainco.library.singlecheckablerecyclerview.Pharmacy


class MultiCheckSparseArrayPharmaciesAdapter(var pharmaciesList: List<Pharmacy>) :
    RecyclerView.Adapter<MultiCheckSparseArrayPharmaciesAdapter.PharmaciesViewHolder>() {
    // if checkedPosition = -1, there is no default selection
// if checkedPosition = 0, 1st item is selected by default
    /*private var selectedPosition by
    Delegates.observable(-1) { property, oldPos, newPos ->
        if (newPos in pharmaciesList.indices) {
            notifyItemChanged(oldPos)
            notifyItemChanged(newPos)
        }
    }*/
    val itemStateArray = SparseBooleanArray()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PharmaciesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewBinding: ItemPharmacyBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_pharmacy, parent, false)
        return PharmaciesViewHolder(viewBinding)
    }

    override fun getItemCount() = pharmaciesList.size

    override fun onBindViewHolder(holder: PharmaciesViewHolder, position: Int) {
        if (position in pharmaciesList.indices) {
            holder.bind(pharmaciesList[position])
        }
    }


    inner class PharmaciesViewHolder constructor(private val itemPharmacyBinding: ItemPharmacyBinding) :
        RecyclerView.ViewHolder(itemPharmacyBinding.root), View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }

        fun bind(pharmacy: Pharmacy) {
            with(itemPharmacyBinding) {
                if (itemStateArray.get(adapterPosition, false)) {
                    imagCheck.visibility = View.VISIBLE
                } else {
                    imagCheck.visibility = View.INVISIBLE
                }
                textviewPharmacy.text = pharmacy.customerName
            }
        }

        override fun onClick(view: View?) {
            if (!itemStateArray.get(adapterPosition, false)) {
                itemStateArray.put(adapterPosition, true)
                itemPharmacyBinding.imagCheck.visibility = View.VISIBLE
            } else {
                itemStateArray.put(adapterPosition, false)
                itemPharmacyBinding.imagCheck.visibility = View.INVISIBLE
            }
        }

    }
}
