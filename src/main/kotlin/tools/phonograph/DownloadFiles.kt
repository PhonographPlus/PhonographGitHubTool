package tools.phonograph

import java.io.File


private val rt = Runtime.getRuntime()

fun downloadFiles(releases: List<Release>) {
    println("Start Download Artifacts...")

    clearFiles(previewReleaseFiles)

    for (release in releases) {
        println(" - download release ${release.name}")
        val assets = release.assets ?: continue
        val dir = File(previewReleaseFiles, release.tagName).also { it.mkdirs() }

        for (asset in assets) {
            if (asset.browserDownloadUrl.isEmpty()) continue
            println("   - download asset ${asset.name}")
            val command =
                """aria2c ${asset.browserDownloadUrl} --all-proxy=http://localhost:1081"""
            val process = rt.exec(command, arrayOf(), dir)
//            process.inputStream.use {
//                println(String(it.readAllBytes()))
//            }
            process.waitFor()
        }
    }

    println("Download Artifacts Completed!")
}

