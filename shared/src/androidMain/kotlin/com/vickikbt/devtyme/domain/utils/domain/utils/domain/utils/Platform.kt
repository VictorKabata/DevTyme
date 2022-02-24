package com.vickikbt.devtyme.domain.utils.domain.utils.domain.utils

actual class Platform actual constructor() {
    actual val platform: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}