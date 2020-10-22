package com.example.quizpplication


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//Adapter holds list of categories
class CategoriesRecyclerAdapter(val context: Context, val categories : List<Category>): RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
    val layoutInflater = LayoutInflater.from(context)

    //Gets the layout of current row and passes to VieHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
    {
        val itemView = layoutInflater.inflate(R.layout.categories_list_view, parent, false)
        return CategoriesViewHolder(itemView)

    }
    //Bind list of items to ViewHolder
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int)
    {
       when(holder)
       {
           is CategoriesViewHolder -> { holder.bind(categories[position]) }
       }
    }

    //Return no. of items to adapter
    override fun getItemCount():Int
    {
        return categories.size
    }
    //create inner class to keep hold of itemView,add reference to itemView
    inner class CategoriesViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView)
    {
     val categoryImage = itemView.findViewById<ImageView>(R.id.categoryListImageView)
     val categoryText = itemView.findViewById<TextView>(R.id.categoryListTextView)
    //Displays the items in View
     fun bind(categoryDisplay: Category)
     {
         categoryText.setText(categoryDisplay.description)
         categoryImage.setImageResource(categoryDisplay.categoryImage)
     }

     init
     {
         categoryImage.setOnClickListener {
             val intent = Intent(context, QuizQuestionActivity::class.java)
             //Pass textview value
             intent.putExtra("categoryname",categoryText.text.toString())
             context.startActivity(intent)
             // to finish activity from RecyclerView Adapter
             (context as CategoriesActivity).finish()
         }
     }
    }
}