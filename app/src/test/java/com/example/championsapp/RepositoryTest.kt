package com.example.championsapp

import com.example.championsapp.client.RetrofitApiClientImplementation
import com.example.championsapp.db.RoomDataSource
import com.example.championsapp.repository.Repository
import com.example.championsapp.repository.RepositoryImpl
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class RepositoryTest {

    lateinit var retrofitApiClientImplementation: RetrofitApiClientImplementation
    lateinit var roomDataSource: RoomDataSource
    private lateinit var repository: Repository

    private val championResponse = ChampionMock.championListMock

    @Before
    fun setup() = runBlockingTest {
        MockitoAnnotations.initMocks(this)
        retrofitApiClientImplementation = Mockito.mock(RetrofitApiClientImplementation::class.java)
        roomDataSource = Mockito.mock(RoomDataSource::class.java)
        whenever(retrofitApiClientImplementation.getChampionResponse()).thenReturn(this@RepositoryTest.championResponse)
        repository = RepositoryImpl(retrofitApiClientImplementation,roomDataSource)
    }


    @Test
    fun `if RetrofitApiClientImplementation return championList then Repository calls getChampionResponse method returns same championList`() = runBlockingTest {
        val championFromClientImpl = repository.getChampionResponse()
        assertEquals(championFromClientImpl, this@RepositoryTest.championResponse)
        assertEquals(championFromClientImpl[0].image, this@RepositoryTest.championResponse[0].image)
    }
}