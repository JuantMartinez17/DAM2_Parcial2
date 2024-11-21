package com.example.dam2_parcial2.view.activities

import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dam2_parcial2.R
import com.example.dam2_parcial2.databinding.ActivityMainBinding
import com.example.dam2_parcial2.view.activities.fragments.FavoritesFragment
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Picasso.with(binding.userImage.context)
            .load("https://ui-avatars.com/api/?name=User&background=000&color=fff&size=128")
            .into(binding.userImage)

        binding.svIngredient.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()){
                    val intent = Intent(this@MainActivity, SearchResultsActivity::class.java)
                    intent.putExtra("search_query", query)
                    startActivity(intent)
                }else{
                    Toast.makeText(this@MainActivity, "Por favor, ingrese un texto", Toast.LENGTH_SHORT).show()
                }
                return true
            }
            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })

        binding.btnFavorites.setOnClickListener{
            favoritesFragmentInit()
        }
    }

    private fun favoritesFragmentInit() {
        val favoritesFragment = FavoritesFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, favoritesFragment)
            .addToBackStack(null)
            .commit()
    }
}