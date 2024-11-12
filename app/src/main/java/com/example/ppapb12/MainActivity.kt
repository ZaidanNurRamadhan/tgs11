package com.example.ppapb12

import ApiAdapter
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ppapb12.databinding.ActivityMainBinding
import com.example.ppapb12.modal.Api
import com.example.ppapb12.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val client = ApiClient.getInstance()
        val responseApi = client.getAllAuthors()
        val libutList = ArrayList<String>()

        responseApi.enqueue(object : Callback<List<Api>> {
            override fun onResponse(p0: Call<List<Api>>, p1: Response<List<Api>>) {
                for (i in p1.body()!!) {
                    libutList.add(i.keterangan)
                    libutList.add(i.tanggal)
                    libutList.add(if (i.cuti) "Libur" else "Tidak Libur")
                }
                // Set up RecyclerView dengan adapter dan layout manager
                val apiAdapter = ApiAdapter(libutList)
                binding.rvApi.layoutManager = GridLayoutManager(this@MainActivity,3)
                binding.rvApi.adapter = apiAdapter
            }

            override fun onFailure(p0: Call<List<Api>>, p1: Throwable) {
                Toast.makeText(this@MainActivity, "hai", Toast.LENGTH_SHORT).show()
            }
        })

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
