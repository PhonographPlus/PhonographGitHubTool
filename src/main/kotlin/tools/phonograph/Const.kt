package tools.phonograph

import java.io.File

const val OWNER = "chr56"
const val REPO_NAME = "Phonograph_Plus"

val jsonOutputFiles = File(".", "jsons")


val artifactDir = File(".", "export")

val exportedMetadataDir = File(artifactDir, "metadata")
val exportedReleaseNoteDir = File(artifactDir, "releaseNote")