package com.freshworks.recipefinder.data

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.freshworks.recipefinder.Activity.WebViewActivity
import com.freshworks.recipefinder.R
import com.freshworks.recipefinder.model.Recipe
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_row.view.*


class RecipeListAdapter(
    private var context: Context,
    private var list: ArrayList<Recipe>
) : RecyclerView.Adapter<RecipeListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): RecipeListAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(p0: RecipeListAdapter.ViewHolder, p1: Int) {

        p0.bindView(list[p1])

    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var title = itemView.tv_title
        var ingredients = itemView.tv_ingredients
        var cv_image = itemView.iv_circle_image
        var share = itemView.btn_share
        var more = itemView.btn_more

        fun shareMessage(msg: String) {
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.putExtra(Intent.EXTRA_TEXT, msg)
            shareIntent.type = "text/plain"


            context.startActivity(Intent.createChooser(shareIntent, "send to"))
        }

        fun bindView(recipe: Recipe) {
            title.text = recipe.title
            ingredients.text = recipe.ingredients

            if (recipe.thumbnail!!.length != 0)
                Picasso.get().load(recipe.thumbnail)
                    .into(cv_image);

            share.setOnClickListener {

                shareMessage(msg = "Hi , check this recipe I found at ${recipe.link}")
            }

            more.setOnClickListener {

                val intent = Intent(context, WebViewActivity::class.java)
                intent.putExtra("source", recipe.link)
                context.startActivity(intent)


            }

        }


    }


}