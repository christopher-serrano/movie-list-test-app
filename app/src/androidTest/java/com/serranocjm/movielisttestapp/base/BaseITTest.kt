package com.serranocjm.movielisttestapp.base

import androidx.test.platform.app.InstrumentationRegistry
import com.serranocjm.movielisttestapp.utils.readTextAndClose
import java.io.InputStream

abstract class BaseITTest {

    /**
     * Reads input file and converts to json
     */
    fun getJson(path: String): String {
        val ctx = InstrumentationRegistry.getInstrumentation().context
        val manager = ctx.assets
        val input: InputStream = manager.open(path)
        return input.readTextAndClose()
    }
}
