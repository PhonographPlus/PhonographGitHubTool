package tools.phonograph

import java.io.File

fun clearFiles(file: File) {
    if (file.exists()) {
        println("deleting the existed file (${file.path})...")
        file.deleteRecursively()
    }
    file.mkdirs()
}