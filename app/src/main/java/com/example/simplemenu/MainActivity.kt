package com.example.simplemenu

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.simplemenu.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.mytoolbar.root)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        // Wenn eine ActionViewClass verwendet wird, kommt die Ausführungslogig hier hin
        val search = menu?.findItem(R.id.action_search)
        val searchView  = search?.actionView as SearchView
        searchView.queryHint = "Was soll gesucht werden?"
        // der Listener wird hier als anonyme Klasse deklariert
        searchView.setOnQueryTextListener(object : OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(this@MainActivity, "Suche nach $query", Toast.LENGTH_SHORT).show()
                searchView.setQuery("",false)
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                Toast.makeText(this@MainActivity, "Der Text $p0", Toast.LENGTH_SHORT).show()
                return true
            }

        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.action_settings -> {
                Toast.makeText(this,"Settings wurde getippt",Toast.LENGTH_LONG).show()
                true
            }

            R.id.action_send -> {
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:")
                    putExtra(Intent.EXTRA_EMAIL, arrayOf("empfaenger@email.com"))
                    putExtra(Intent.EXTRA_SUBJECT,"Betreff")
                    putExtra(Intent.EXTRA_TEXT,"Text in der Nachricht")
                    setPackage("com.google.android.gm")
                }
                if(intent.resolveActivity(packageManager) != null){
                    startActivity(intent)
                }else{
                    Toast.makeText(this,"Kein E-Mail Programm vorhanden",Toast.LENGTH_LONG).show()
                }
                true
            }
            R.id.action_sub ->{
                Toast.makeText(this,"Sub wurde getippt",Toast.LENGTH_LONG).show()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }

    }
}

// Erklärung zur anonymen Klasse
class MyListener: OnQueryTextListener{

    override fun onQueryTextSubmit(p0: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
       return true
    }
}