#include <jni.h>
#include <vector>
#include <string>
#include <fstream>
#include <android/log.h>

#define LOG_TAG "DXFNative"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)

// Very minimal DXF parser that extracts lines of the form:
// LINE\n x1\n y1\n x2\n y2

extern "C" JNIEXPORT jobjectArray JNICALL
Java_com_example_dxfviewer_DxfRepository_parseDxf(JNIEnv* env, jobject /*this*/, jstring path) {
    const char* cPath = env->GetStringUTFChars(path, nullptr);
    std::ifstream file(cPath);
    env->ReleaseStringUTFChars(path, cPath);

    std::vector<float> coords;
    std::string token;
    while (file >> token) {
        if (token == "LINE") {
            float x1, y1, x2, y2;
            file >> x1 >> y1 >> x2 >> y2;
            coords.push_back(x1);
            coords.push_back(y1);
            coords.push_back(x2);
            coords.push_back(y2);
        }
    }

    size_t count = coords.size() / 4;
    jclass floatArrayClass = env->FindClass("[F");
    jobjectArray outer = env->NewObjectArray(static_cast<jsize>(count), floatArrayClass, nullptr);

    for (size_t i = 0; i < count; ++i) {
        jfloatArray arr = env->NewFloatArray(4);
        env->SetFloatArrayRegion(arr, 0, 4, coords.data() + i * 4);
        env->SetObjectArrayElement(outer, static_cast<jsize>(i), arr);
        env->DeleteLocalRef(arr);
    }
    return outer;
}
