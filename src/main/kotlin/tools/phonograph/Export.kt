package tools.phonograph

import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import java.io.File

private val parser = Json {
    prettyPrint = true
}


fun export(releases: List<Release>) {
    println("Start Export Metadata...")
    if (exportedMetadataDir.exists()) {
        println("deleting the existed...")
        exportedMetadataDir.deleteRecursively()
    }
    exportedMetadataDir.mkdirs()

    for (release in releases) {
        val target = File(exportedMetadataDir, "release_${release.tagName}.json").also { it.createNewFile() }
        val raw = parser.encodeToString(parser.serializersModule.serializer(), release)
        target.writer().use {
            it.write(raw)
            it.flush()
        }
    }
    println("Export Metadata Completed!")
}