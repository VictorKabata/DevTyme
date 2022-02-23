package com.vickikbt.devtyme

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}