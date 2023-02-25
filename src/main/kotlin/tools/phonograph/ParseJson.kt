package tools.phonograph

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.File

private val parser = Json { ignoreUnknownKeys = true }

fun parse(): List<Release> {
    println("Start parsing jsons...")
    val jsons = jsonOutputFiles.listFiles() ?: return emptyList()
    val releases = jsons.map(::decode).onEach { it.hash = tagsMap[it.tagName] ?: "" }
    println("Parsing json completed!")
    return releases
}

private fun decode(json: File): Release {
    val text = json.readText()
    return parser.decodeFromString(text)
}
