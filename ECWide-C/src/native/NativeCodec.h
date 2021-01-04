/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class NativeCodec */

#ifndef _Included_NativeCodec
#define _Included_NativeCodec
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     NativeCodec
 * Method:    generateEncodeMatrix
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_NativeCodec_generateEncodeMatrix__
  (JNIEnv *, jobject);

/*
 * Class:     NativeCodec
 * Method:    generateEncodeMatrix
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_NativeCodec_generateEncodeMatrix__I
  (JNIEnv *, jobject, jint);

/*
 * Class:     NativeCodec
 * Method:    initEncodeTable
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_NativeCodec_initEncodeTable
  (JNIEnv *, jobject);

/*
 * Class:     NativeCodec
 * Method:    initDecodeTable
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_NativeCodec_initDecodeTable
  (JNIEnv *, jobject);

/*
 * Class:     NativeCodec
 * Method:    encodeData
 * Signature: ([Ljava/nio/ByteBuffer;[Ljava/nio/ByteBuffer;)V
 */
JNIEXPORT void JNICALL Java_NativeCodec_encodeData
  (JNIEnv *, jobject, jobjectArray, jobjectArray);

/*
 * Class:     NativeCodec
 * Method:    decodeData
 * Signature: ([Ljava/nio/ByteBuffer;Ljava/nio/ByteBuffer;)V
 */
JNIEXPORT void JNICALL Java_NativeCodec_decodeData
  (JNIEnv *, jobject, jobjectArray, jobject);

/*
 * Class:     NativeCodec
 * Method:    xorIntemediate
 * Signature: ([Ljava/nio/ByteBuffer;[Ljava/nio/ByteBuffer;)V
 */
JNIEXPORT void JNICALL Java_NativeCodec_xorIntemediate
  (JNIEnv *, jobject, jobjectArray, jobjectArray);

#ifdef __cplusplus
}
#endif
#endif
