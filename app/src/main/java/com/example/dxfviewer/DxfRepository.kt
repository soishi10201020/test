package com.example.dxfviewer

class DxfRepository {
    external fun parseDxf(path: String): Array<FloatArray>

    fun loadDxf(path: String): List<LineEntity> {
        System.loadLibrary("dxf")
        val data = parseDxf(path)
        return data.map { coords ->
            LineEntity(coords[0], coords[1], coords[2], coords[3])
        }
    }
}
