package com.yashasvi.easyci.utils

import com.jcraft.jsch.JSch
import com.jcraft.jsch.Session
import org.eclipse.jgit.transport.JschConfigSessionFactory
import org.eclipse.jgit.transport.OpenSshConfig
import org.eclipse.jgit.util.FS


class CustomConfigSessionFactory : JschConfigSessionFactory() {
    override fun configure(hc: OpenSshConfig.Host?, session: Session?) {
    }

    override fun createDefaultJSch(fs: FS?): JSch {
        val jsch = super.createDefaultJSch(fs)
        jsch.removeAllIdentity()
        val privateKeyPath = System.getenv("PRIVATE_KEY_PATH")
        val passphrase = System.getenv("PASSPHRASE")
        jsch.addIdentity(privateKeyPath, passphrase)
        return jsch
    }
}