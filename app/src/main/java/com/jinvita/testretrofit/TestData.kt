package com.jinvita.testretrofit

import com.google.gson.annotations.SerializedName

class TestData : ArrayList<TestData.TestDataItem>() {
    data class TestDataItem(
        @SerializedName("body")
        val body: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("title")
        val title: String,
        @SerializedName("userId")
        val userId: Int
    )
}