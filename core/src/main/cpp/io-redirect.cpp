#include <android/log.h>

#define LOG_TAG "VirtualWorld"

void redirect_io(const char *path) {
    __android_log_print(ANDROID_LOG_INFO, LOG_TAG, "Redirecting IO for %s", path);
}
