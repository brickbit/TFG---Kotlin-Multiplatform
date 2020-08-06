package com.architecture.mpp.view.adapter

import BaseAdapater
import android.view.View
import domain.model.MonumentMainItemDomain
import kotlinx.android.synthetic.main.item_list.view.*

class MonumentAdapter(onItemClick: (MonumentMainItemDomain) -> Unit)
    : BaseAdapater<MonumentMainItemDomain>(onItemClickListener = onItemClick) {

    override val itemLayoutId: Int = com.architecture.mpp.R.layout.item_list

    override fun getItemCount() = items.size

    override fun viewHolder(view: View): BaseViewHolder<MonumentMainItemDomain> = MonumentViewHolder(view)

    class MonumentViewHolder(itemView: View) : BaseViewHolder<MonumentMainItemDomain>(itemView) {
        override fun bind(model: MonumentMainItemDomain) {
            itemView.idMonument.text = model.id.toString()
            itemView.titleMonument.text = model.title
            itemView.coordinatesMonument.text = model.geocoordinates
        }
    }
}