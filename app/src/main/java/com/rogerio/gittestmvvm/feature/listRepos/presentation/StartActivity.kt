package com.rogerio.gittestmvvm.feature.listRepos.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rogerio.gittestmvvm.R
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        setSupportActionBar(toolbar)

    }

}
