package com.projects.mycompany.apod_app


import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.projects.mycompany.apod_app.database.ApodDao
import com.projects.mycompany.apod_app.database.ApodDataBase
import com.projects.mycompany.apod_app.database.DataBaseApod
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Before
import java.io.IOException
import org.junit.Assert.assertEquals

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class RoomDatabaseTest {

    private lateinit var apodDao: ApodDao
    private lateinit var db: ApodDataBase

    @Before
    fun createDb(){
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(appContext,ApodDataBase::class.java)
            .allowMainThreadQueries()
            .build()

        apodDao = db.apodDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb(){
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun test() {
        val apod = DataBaseApod("test_title","test_explanation","test_url")
        apodDao.insert(apod)
        val testApod = apodDao.getApodByTitle("test_title")
        assertEquals(testApod.title,"test_title")

    }
}
