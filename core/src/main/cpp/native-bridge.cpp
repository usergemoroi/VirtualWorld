#include <jni.h>
#include <android/log.h>

#define LOG_TAG "VirtualWorld"

extern "C" JNIEXPORT void JNICALL
Java_com_virtualworld_core_NativeBridge_notifyHookInstalled(JNIEnv *env, jclass clazz, jstring hookName) {
    const char *name = env->GetStringUTFChars(hookName, nullptr);
    __android_log_print(ANDROID_LOG_INFO, LOG_TAG, "Hook installed: %s", name);
    env->ReleaseStringUTFChars(hookName, name);
}

jint JNI_OnLoad(JavaVM *vm, void *) {
    JNIEnv *env = nullptr;
    if (vm->GetEnv(reinterpret_cast<void **>(&env), JNI_VERSION_1_6) != JNI_OK) {
        return JNI_ERR;
    }
    __android_log_print(ANDROID_LOG_INFO, LOG_TAG, "Native bridge loaded");
    return JNI_VERSION_1_6;
}
