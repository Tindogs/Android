package com.appvengers.tindogs.match

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.appvengers.business.models.Dog
import com.appvengers.tindogs.R
import com.squareup.picasso.Picasso


class DogMatchCardAdapter(context: Context) : ArrayAdapter<Dog>(context, 0) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var contentView = convertView
        val holder: DogMatchCardAdapter.ViewHolder

        if (contentView == null) {
            val inflater = LayoutInflater.from(context)
            contentView = inflater.inflate(R.layout.item_dog_match_card, parent, false)
            holder = DogMatchCardAdapter.ViewHolder(contentView!!)
            contentView!!.tag = holder
        } else {
            holder = contentView.tag as DogMatchCardAdapter.ViewHolder
        }

        val dog = getItem(position)

        holder.name.text = dog!!.name
        holder.age.text = dog.age.toString()
        if(dog.photos.isEmpty()) {
            Picasso.with(context)
                    .load(R.drawable.dog_placeholder)
                    .into(holder.image)
        } else {
            Picasso.with(context)
                    .load(dog.photos.first())
                    .into(holder.image)
        }
        return contentView
    }

    private class ViewHolder(view: View) {
        var name: TextView
        var age: TextView
        var image: ImageView

        init {
            this.name = view.findViewById(R.id.item_dog_match_card_name) as TextView
            this.age = view.findViewById(R.id.item_dog_match_card_location) as TextView
            this.image = view.findViewById(R.id.item_dog_match_card_image) as ImageView
        }
    }


}