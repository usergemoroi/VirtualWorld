#include <android/log.h>

#define LOG_TAG "VirtualWorld"

void install_hook(const char *symbol) {
    __android_log_print(ANDROID_LOG_INFO, LOG_TAG, "Request hook for %s", symbol);
}
