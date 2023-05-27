package com.ifkusyoba.tunetrades.data

import com.ifkusyoba.tunetrades.data.model.FakeDataSource
import com.ifkusyoba.tunetrades.data.model.OrderMusic
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class Repository {
    private val orderMusics = mutableListOf<OrderMusic>()

    // *Inisialisasi data
    init {
        if (orderMusics.isEmpty()) {
            FakeDataSource.dummyMusic.forEach { music ->
                orderMusics.add(OrderMusic(music, 0))
            }
        }
    }

    // *Method getItem
    fun getAllItem(): Flow<List<OrderMusic>> {
        return flowOf(orderMusics)
    }


    // *Method get berdasarkan id
    fun getItemOrderById(musicId: Long): OrderMusic {
        return orderMusics.first {
            it.music.id == musicId
        }
    }

    // *Method update
    fun updateOrderedMusic(musicId: Long, newCountValue: Int): Flow<Boolean> {
        val index = orderMusics.indexOfFirst { it.music.id == musicId }
        val result = if (index >= 0) {
            val orderMusic = orderMusics[index]
            orderMusics[index] =
                orderMusic.copy(music = orderMusic.music, count = newCountValue)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    // *Method getAddedOrderMusics untuk mendapatkan data yang sudah ditambahkan
    fun getOrderedMusic(): Flow<List<OrderMusic>> {
        return getAllItem()
            .map { orderMusics ->
                orderMusics.filter { orderMusic ->
                    orderMusic.count != 0
                }
            }
    }

    // *Method clear cart setelah di checkout
    fun clearCart() {
        orderMusics.forEachIndexed { index, orderMusic ->
            orderMusics[index] = orderMusic.copy(count = 0)
        }
    }

    companion object {
        @Volatile
        private var instance: Repository? = null
        fun getInstance(): Repository =
            instance ?: synchronized(this) {
                Repository().apply {
                    instance = this
                }
            }
    }
}