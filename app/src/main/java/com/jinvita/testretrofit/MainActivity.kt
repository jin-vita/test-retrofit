package com.jinvita.testretrofit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.jinvita.testretrofit.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.UnknownHostException

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
            button.setOnClickListener { getPosts() }
            button2.setOnClickListener { getPosts2() }
            button3.setOnClickListener { repeat(3) { getEachPost(it + 1) } }
            button4.setOnClickListener { repeat(3) { getEachPost2(it + 1) } }
        }
    }

    private fun getPosts() {
        val method = object {}.javaClass.enclosingMethod?.name
        TestApiClient.api.getPosts().enqueue(object : Callback<TestData> {
            override fun onResponse(call: Call<TestData>, response: Response<TestData>) {
                if (response.isSuccessful) {
                    response.body()?.apply {
                        App.error(TAG, "$method isSuccessful. size: ${this.size}")
                        App.debug(TAG, "data: $this")
                    }
                } else App.error(TAG, "$method isNotSuccessful. code: ${response.code()}")
            }

            override fun onFailure(call: Call<TestData>, t: Throwable) {
                App.error(TAG, "$method isFail, ${t.message}")
            }
        })
    }

    private fun getPosts2() {
        val method = object {}.javaClass.enclosingMethod?.name
        lifecycleScope.launch {
            try {
                TestApiClient.api.getPosts2()
            } catch (e: UnknownHostException) {
                App.error(TAG, "$method isFail, ${e.message}")
                return@launch
            }.apply {
                if (isSuccessful) {
                    body()?.apply {
                        App.error(TAG, "$method isSuccessful. size: ${this.size}")
                        App.debug(TAG, "data: $this")
                    }
                } else App.error(TAG, "$method isNotSuccessful. code: ${code()}")
            }
        }
    }

    private fun getEachPost(id: Int) {
        val method = object {}.javaClass.enclosingMethod?.name
        TestApiClient.api.getEachPost(id).enqueue(object : Callback<TestData> {
            override fun onResponse(call: Call<TestData>, response: Response<TestData>) {
                if (response.isSuccessful) {
                    response.body()?.apply {
                        App.error(TAG, "$method isSuccessful. size: ${this.size}")
                        App.debug(TAG, "data: $this")
                    }
                } else App.error(TAG, "$method isNotSuccessful. code: ${response.code()}")
            }

            override fun onFailure(call: Call<TestData>, t: Throwable) {
                App.error(TAG, "$method isFail, ${t.message}")
            }
        })
    }

    private fun getEachPost2(id: Int) {
        val method = object {}.javaClass.enclosingMethod?.name
        lifecycleScope.launch {
            try {
                TestApiClient.api.getEachPost2(id)
            } catch (e: UnknownHostException) {
                App.error(TAG, "$method isFail, ${e.message}")
                return@launch
            }.apply {
                if (isSuccessful) {
                    body()?.apply {
                        App.error(TAG, "$method isSuccessful. size: ${this.size}")
                        App.debug(TAG, "data: $this")
                    }
                } else App.error(TAG, "$method isNotSuccessful. code: ${code()}")
            }
        }
    }
}