package com.freshworks.recipefinder.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.freshworks.recipefinder.R
import com.freshworks.recipefinder.data.RecipeListAdapter
import com.freshworks.recipefinder.model.Recipe
import kotlinx.android.synthetic.main.activity_recipe_list.*
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception

class RecipeListActivity : AppCompatActivity() {


    var recipeList : ArrayList<Recipe>? = null
    var volleyRequestQueue: RequestQueue? = null
    var layoutManager : LinearLayoutManager? = null
    var recipeListAdapter : RecipeListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)
        volleyRequestQueue = Volley.newRequestQueue(this)
        recipeList = ArrayList()
        layoutManager = LinearLayoutManager(this)
        recipeListAdapter = RecipeListAdapter(this,recipeList!!)


        //setting up rv
        rv_list.adapter = recipeListAdapter
        rv_list.layoutManager = layoutManager



        getRecipeList("http://www.recipepuppy.com/api/?i=onions,garlic&q=omelet&p=3 ")
    }


    fun getRecipeList(url: String) {
        var RecipeRequest = JsonObjectRequest(Request.Method.GET, url, Response.Listener {

                response: JSONObject ->
            try {
                Log.d("Testing",response.toString())
            val resultArray = response.getJSONArray("results")

                for(i in 0..resultArray.length().minus(1))
                {

                    var recipeObj = resultArray.getJSONObject(i)

                    var title = recipeObj.getString("title")
                    var link = recipeObj.getString("href")
                    var thumbnail = recipeObj.getString("thumbnail")
                    var ingredients = recipeObj.getString("ingredients")
                    var recipe = Recipe(title,link,ingredients,thumbnail)
                    recipeList?.add(recipe)




                }

                recipeListAdapter?.notifyDataSetChanged()

            } catch (e: JSONException) {
                e.printStackTrace()
            }

        }, Response.ErrorListener { error: VolleyError? ->
            try {
                Log.d("Error", error.toString())

            } catch (e: JSONException) {
                e.printStackTrace()
            }

        })

        volleyRequestQueue?.add(RecipeRequest)

    }
}
