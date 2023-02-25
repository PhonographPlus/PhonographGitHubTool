package tools.phonograph

import java.io.File

private val rt = Runtime.getRuntime()


fun downloadMetadata() {
    println("Start downloading...")

    val outputDir = File(".", "jsons")

    for ((tag, _) in tagsMap) {
        println("Download $tag...")
        val process =
            rt.exec("""gh api -H "Accept: application/vnd.github+json" /repos/$OWNER/$REPO_NAME/releases/tags/$tag""")
        val raw = process.inputStream.readBytes()

        val file = File(outputDir, "$tag.release.json")
        val json = String(raw, Charsets.UTF_8)

        file.outputStream().buffered().writer(Charsets.UTF_8).use {
            it.write(json)
            it.flush()
        }
    }

    println("Download completely")
}