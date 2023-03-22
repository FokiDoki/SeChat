package com.seChat.utils;

import org.bson.types.Binary;

import java.util.Base64;

public class EncodeTools {
    public static final String BytesToBase64(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }
    public static final String BinaryToBase64(Binary binary) {
        return BytesToBase64(binary.getData());
    }
}
