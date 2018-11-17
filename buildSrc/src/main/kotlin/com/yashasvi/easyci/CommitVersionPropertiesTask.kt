package com.yashasvi.easyci

import org.eclipse.jgit.api.Git
import org.eclipse.jgit.storage.file.FileRepositoryBuilder
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.TaskAction
import java.io.File


open class CommitVersionPropertiesTask : DefaultTask() {

    init {
        group = "Easy CI"
        description = "commits unsaved changes in version.properties"
    }

    @TaskAction
    fun run() {
        val file = File(VERSION_PROPERTIES_PATH)
        if (file.exists()) {
            val versionProps = file.readAsMap()
            val code = versionProps[VERSION_CODE_KEY]
            val git = getGitRepo()
            git.add().addFilepattern(file.path).call()
            git.commit().setMessage("Update version code to $code").call();
            println("version code: $code updated successfully")
        } else {
            throw GradleException("can't find version.properties file")
        }

    }

    private fun getGitRepo(): Git {
        val repositoryBuilder = FileRepositoryBuilder()
        repositoryBuilder.isMustExist = true
        repositoryBuilder.gitDir = File(GIT_DIR_PATH)
        val repository = repositoryBuilder.build();
        val git = Git(repository)
        return git
    }
}