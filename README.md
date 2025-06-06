# DXFViewer

This is a minimal Android project demonstrating how to load DXF files using C++
and display them in a simple 2D viewer. Kotlin is used for the UI and MVVM
components while the DXF parser is implemented in C++ via the Android NDK.

The parser is intentionally basic and expects `LINE` entries formatted as:

```
LINE x1 y1 x2 y2
```

Lines are drawn onto a custom `DxfView`.
