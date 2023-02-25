package tools.phonograph

import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import java.io.File

private val parser = Json {
    prettyPrint = true
}


fun export(releases: List<Release>) {
    println("Start Export Metadata...")
    exportMetadata(releases)
    println("Export Metadata Completed!")
    println("Start Export ReleaseNote...")
    exportReleaseNote(releases)
    println("Export ReleaseNote Completed!")
}


private fun exportMetadata(releases: List<Release>) {

    clearFiles(exportedMetadataDir)

    for (release in releases) {
        val target = File(exportedMetadataDir, "release_${release.tagName}.json").also { it.createNewFile() }
        val raw = parser.encodeToString(parser.serializersModule.serializer(), release)
        target.writer().use {
            it.write(raw)
            it.flush()
        }
    }
}


private fun exportReleaseNote(releases: List<Release>) {

    clearFiles(exportedReleaseNoteDir)

    for (release in releases) {
        val target = File(exportedReleaseNoteDir, "release_${release.tagName}.release_note.md").also { it.createNewFile() }
        val markdown = release.body
        target.writer().use {
            it.write(markdown)
            it.flush()
        }
    }
}