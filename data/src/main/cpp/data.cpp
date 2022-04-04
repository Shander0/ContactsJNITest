#include <jni.h>
#include <iostream>
#include <regex>

extern "C" JNIEXPORT jboolean JNICALL
Java_com_example_data_validate_ContactPhoneValidator_validatePhone(JNIEnv *env, jobject obj, jstring str) {

    static const std::regex pattern("\\+[7][[:digit:]]{10}");

    jboolean isCopy;
    const char *convertedValue = (env)->GetStringUTFChars(str, &isCopy);
    std::string string = convertedValue;

    if (std::regex_match(string, pattern)) {
        env->ReleaseStringUTFChars(str, convertedValue);
        return true;
    }

    env->ReleaseStringUTFChars(str, convertedValue);
    return false;
}