package com.yashasvi.easyci.plugins

import com.yashasvi.easyci.tasks.CommitVersionPropertiesTask
import com.yashasvi.easyci.tasks.UpdateVersionCodeTask
import org.gradle.api.Plugin
import org.gradle.api.Project


class EasyCIPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.tasks.create("commitVersionProperties", CommitVersionPropertiesTask::class.java)
        project.tasks.create("updateVersionCode", UpdateVersionCodeTask::class.java)
                .finalizedBy("commitVersionProperties")
        project.extensions.create("easyci", EasyCIExtension::class.java, project)
    }
}