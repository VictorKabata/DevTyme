package com.vickikbt.devtyme.domain.utils.domain.utils.domain.utils

actual class Platform actual constructor() {
    actual val platform: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}