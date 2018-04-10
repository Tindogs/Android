package com.appvengers.tindogs.userProfile

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.appvengers.business.models.Dog
import com.appvengers.tindogs.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cell_list_dog_user_profile.view.*

class UserDogsAdapter(private val dogs: List<Dog>): RecyclerView.Adapter<UserDogsAdapter.UserDogsViewHolder>()
{
    var listener: OnDogClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserDogsViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_list_dog_user_profile, parent, false)
        return UserDogsViewHolder(view)
    }

    override fun getItemCount(): Int
    {
        return dogs.count()
    }

    override fun onBindViewHolder(holder: UserDogsViewHolder, position: Int)
    {
        holder.bindDog(dogs[position])
    }

    inner class UserDogsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        fun bindDog(dog: Dog)
        {
            itemView.cell_list_dog_user_profile_name.text = dog.name
            if(dog.photos[0] != "") {
                Picasso.with(itemView.context)
                        .load(dog.photos.firstOrNull())
                        .placeholder(R.drawable.dog_placeholder)
                        .into(itemView.cell_list_dog_user_profile_image)

            } else {
                Picasso.with(itemView.context)
                        .load(R.drawable.dog_placeholder)
                        .into(itemView.cell_list_dog_user_profile_image)
            }

            itemView.setOnClickListener { listener?.onDogSelected(dog) }

        }
    }

    interface OnDogClickListener
    {
        fun onDogSelected(dog: Dog)
    }
}