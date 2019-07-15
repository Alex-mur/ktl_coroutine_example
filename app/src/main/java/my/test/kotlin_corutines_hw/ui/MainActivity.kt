package my.test.kotlin_corutines_hw.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import my.test.kotlin_corutines_hw.R
import my.test.kotlin_corutines_hw.model.repository.IRepo
import my.test.kotlin_corutines_hw.model.repository.Repo
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.main_frame, ListFragment())
            .commit()
    }


}
