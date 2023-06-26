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

@Suppress("SameParameterValue")
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
            button.setOnClickListener { getPostList() }
            button2.setOnClickListener { getPostList2() }
            button3.setOnClickListener { getQueryPost(27) }
            button4.setOnClickListener { getQueryPost2(41) }
            button5.setOnClickListener { getPathPost(63) }
            button6.setOnClickListener { getPathPost2(86) }
        }
    }

    private fun getPostList() {
        val method = object {}.javaClass.enclosingMethod?.name
        TestApiClient.api.getPostList().enqueue(object : Callback<PostsData> {
            override fun onResponse(call: Call<PostsData>, response: Response<PostsData>) {
                val code = response.code()
                if (response.isSuccessful) {
                    response.body()?.apply {
                        App.error(TAG, "$method isSuccessful. size: ${this.size}")
                        App.debug(TAG, "data: $this")
                        this.first().apply {
                            val post = Post(id = id, userId = userId, title = title, body = body)
                            setData(code, post)
                        }
                    }
                } else App.error(TAG, "$method isNotSuccessful. code: $code")
            }

            override fun onFailure(call: Call<PostsData>, t: Throwable) {
                App.error(TAG, "$method isFail, ${t.message}")
            }
        })
    }

    private fun getPostList2() {
        val method = object {}.javaClass.enclosingMethod?.name
        lifecycleScope.launch {
            try {
                TestApiClient.api.getPostList2()
            } catch (e: UnknownHostException) {
                App.error(TAG, "$method isFail, ${e.message}")
                return@launch
            }.apply {
                val code = code()
                if (isSuccessful) {
                    body()?.apply {
                        App.error(TAG, "$method isSuccessful. size: ${this.size}")
                        App.debug(TAG, "data: $this")
                        this.last().apply {
                            val post = Post(id = id, userId = userId, title = title, body = body)
                            setData(code, post)
                        }
                    }
                } else App.error(TAG, "$method isNotSuccessful. code: $code")
            }
        }
    }

    private fun getQueryPost(postId: Int) {
        val method = object {}.javaClass.enclosingMethod?.name
        TestApiClient.api.getPost(postId).enqueue(object : Callback<PostsData> {
            override fun onResponse(call: Call<PostsData>, response: Response<PostsData>) {
                val code = response.code()
                if (response.isSuccessful) {
                    response.body()?.apply {
                        App.error(TAG, "$method isSuccessful. size: ${this.size}")
                        App.debug(TAG, "data: $this")
                        this[0].apply {
                            val post = Post(id = id, userId = userId, title = title, body = body)
                            setData(code, post)
                        }
                    }
                } else App.error(TAG, "$method isNotSuccessful. code: $code")
            }

            override fun onFailure(call: Call<PostsData>, t: Throwable) {
                App.error(TAG, "$method isFail, ${t.message}")
            }
        })
    }

    private fun getQueryPost2(postId: Int) {
        val method = object {}.javaClass.enclosingMethod?.name
        lifecycleScope.launch {
            try {
                TestApiClient.api.getPost2(postId)
            } catch (e: UnknownHostException) {
                App.error(TAG, "$method isFail, ${e.message}")
                return@launch
            }.apply {
                val code = code()
                if (isSuccessful) {
                    body()?.apply {
                        App.error(TAG, "$method isSuccessful. size: ${this.size}")
                        App.debug(TAG, "data: $this")
                        this[0].apply {
                            val post = Post(id = id, userId = userId, title = title, body = body)
                            setData(code, post)
                        }
                    }
                } else App.error(TAG, "$method isNotSuccessful. code: $code")
            }
        }
    }

    private fun getPathPost(postId: Int) {
        val method = object {}.javaClass.enclosingMethod?.name
        TestApiClient.api.getPost3(postId).enqueue(object : Callback<PostData> {
            override fun onResponse(call: Call<PostData>, response: Response<PostData>) {
                val code = response.code()
                if (response.isSuccessful) {
                    response.body()?.apply {
                        App.error(TAG, "$method isSuccessful.")
                        App.debug(TAG, "data: $this")
                        val post = Post(id = id, userId = userId, title = title, body = body)
                        setData(code, post)
                    }
                } else App.error(TAG, "$method isNotSuccessful. code: $code")
            }

            override fun onFailure(call: Call<PostData>, t: Throwable) {
                App.error(TAG, "$method isFail, ${t.message}")
            }
        })
    }

    private fun getPathPost2(postId: Int) {
        val method = object {}.javaClass.enclosingMethod?.name
        lifecycleScope.launch {
            try {
                TestApiClient.api.getPost4(postId)
            } catch (e: UnknownHostException) {
                App.error(TAG, "$method isFail, ${e.message}")
                return@launch
            }.apply {
                val code = code()
                if (isSuccessful) {
                    body()?.apply {
                        App.error(TAG, "$method isSuccessful.")
                        App.debug(TAG, "data: $this")
                        val post = Post(id = id, userId = userId, title = title, body = body)
                        setData(code, post)
                    }
                } else App.error(TAG, "$method isNotSuccessful. code: $code")
            }
        }
    }

    private fun setData(code: Int, post: Post) = with(binding) {
        post.apply {
            StringBuilder()
                .append("CODE: ").append(code)
                .append(", ID: ").append(id)
                .append(", UserID: ").append(userId)
                .apply { textInfo.text = this }
            textTitle.text = title
            textBody.text = body
        }

    }
}