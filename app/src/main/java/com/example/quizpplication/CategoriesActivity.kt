package com.example.quizpplication

import android.content.Intent
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_categories.*

class CategoriesActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)


        recyclerView = findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        //Call function for spacing between cards
        recyclerView.addItemDecoration(cardViewItemDecoration(30))

        recyclerView.adapter = CategoriesRecyclerAdapter(this, DataManager.categories)


    }
//Display help icon in Action bar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.appmenu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        // Handle help item selection
        return when (item.itemId)
        {
            R.id.action_help -> {
                showHelp()
                true
            }
            else -> super.onOptionsItemSelected(item) //returns default false value
        }
    }

fun showHelp()
{
    val dialogBuilder = AlertDialog.Builder(this,R.style.AlertDialogTheme)
    dialogBuilder.setTitle("Help Info")
        .setMessage(DataManager.helpMessage)
        .setPositiveButton("OK")
        {
                dialog,which->
            Toast.makeText(this,"You closed help window", Toast.LENGTH_LONG).show()
        }
    val alert = dialogBuilder.create()
    alert.show()
}
    override fun onResume() {
        super.onResume()
        recyclerView.adapter?.notifyDataSetChanged()

    }
    inner class cardViewItemDecoration(private val padding:Int):RecyclerView.ItemDecoration()
    {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            outRect.top = padding
        }
    }
}