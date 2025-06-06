package com.example.dxfviewer

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val viewModel: DxfViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dxfView = findViewById<DxfView>(R.id.dxfView)
        dxfView.bindViewModel(this, viewModel)

        // Example: load DXF file from path (implementation missing)
        // viewModel.loadDxfFile("/sdcard/sample.dxf")
    }
}
