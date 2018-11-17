package com.yashasvi.easyci;

import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.TaskAction
import java.io.File

open class UpdateVersionCodeTask : DefaultTask() {

    init {
        group = "Easy CI"
        description = "updates version code in a separate commit"
    }

    @TaskAction
    fun run() {
        val file = File(VERSION_PROPERTIES_PATH)
        if (file.exists()) {
            val versionProps = file.readAsMap()
            val code = versionProps[VERSION_CODE_KEY].toString().toInt() + 1
            versionProps[VERSION_CODE_KEY] = code.toString()
            logger.lifecycle("updating version code to $code")
            file.writeMap(versionProps)
        } else {
            throw GradleException("can't find version.properties file")
        }
    }
}


