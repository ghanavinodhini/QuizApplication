package com.example.quizpplication

import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizpplication.BaseCorotineJob.BaseCoroutineJob
import com.example.quizpplication.roomDB.AppDatabase
import com.example.quizpplication.roomDB.CategoryEntity
import com.example.quizpplication.roomDB.Player_Entity
import kotlinx.android.synthetic.main.activity_categories.*
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class CategoriesActivity : BaseCoroutineJob() {
    private lateinit var db: AppDatabase
    private val cardPadding = 20

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.addItemDecoration(CardViewItemDecoration(cardPadding))

        //Get instance of Database
        db = AppDatabase.getInstance(this)

        Log.d("!!!", "Inside Categories activity")

        val cat1 = CategoryEntity("Fruits")
        Log.d("!!!", "After creating cat1: $cat1")

        val cat2 = CategoryEntity( "Flowers")
        Log.d("!!!", "After creating cat2: $cat2")

        val cat3 = CategoryEntity("Vegetables")
        Log.d("!!!", "After creating cat3: $cat3")

        Log.d("!!!", " calling insert inside Categories first launch coroutine function to delete")

        //Coroutine Scope functions
        launch {
            db.categoryDao.deleteAllCategories()
        }
        launch {
            //delay(500)
            db.categoryDao.insertCategory(cat1, cat2, cat3)
            Log.d("!!!", "After insertCategory DAO Call")

        }

        Log.d("!!!","Before calling async coroutine")
       val deferred =  async {
          delay(1000)
           val categoriesList = db.categoryDao.getAllCategories()
           Log.d("!!!","Categories List: $categoriesList")
            recyclerView.adapter = CategoriesRecyclerAdapter(categoriesList,this@CategoriesActivity)
            Log.d("!!!","After the adapter calling line")

        }
        Log.d("!!!","Deferred coroutine value : $deferred")
    }

    //Implement spacing between cards in recyclerview
    inner class CardViewItemDecoration(private val padding:Int):RecyclerView.ItemDecoration()
    {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State)
        {
            super.getItemOffsets(outRect, view, parent, state)
            outRect.top = padding
        }
    }

    //Display help icon in Action bar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.appmenu, menu)
        return true
    }
    // Implement help icon click
    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        return when (item.itemId)
        {
            R.id.action_help -> {
                showHelp()
                true
            }
            else -> super.onOptionsItemSelected(item) //returns default false value
        }
    }
    //Implement help dialog
    private fun showHelp()
    {
        val dialogBuilder = AlertDialog.Builder(this,R.style.AlertDialogTheme)
        dialogBuilder.setTitle("Help Info")
            .setMessage(DataManager.helpMessage)
            .setPositiveButton("OK")
            {
                    dialog,which->
                Toast.makeText(this,"You closed help window", Toast.LENGTH_SHORT).show()
            }
        val alert = dialogBuilder.create()
        alert.show()
    }
    //Notify adapter that dataset has change
   /* override fun onResume()
    {
        super.onResume()
        recyclerView.adapter?.notifyDataSetChanged()
    }*/
}