package com.yashasvi.easyci.plugins

import com.yashasvi.easyci.utils.VERSION_CODE_KEY
import com.yashasvi.easyci.utils.VERSION_PROPERTIES_PATH
import com.yashasvi.easyci.utils.readAsMap
import org.gradle.api.GradleException
import org.gradle.api.Project
import java.io.File


open class EasyCIExtension(project: Project) {

    var currentVersionCode: Int = 0;

    init {
        setCurrentVersionCode()
    }

    fun setCurrentVersionCode() {
        with(File(VERSION_PROPERTIES_PATH)) {
            if (exists()) {
                val versionProps = readAsMap()
                val code = versionProps[VERSION_CODE_KEY].toString().toInt()
                currentVersionCode = code
            } else {
                throw GradleException("can't find version.properties file")
            }
        }
    }
}