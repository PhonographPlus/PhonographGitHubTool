package tools.phonograph

import java.io.File


fun generateReleaseDeletionScript(releases: List<Release>) {

    val commands = releases.sortedBy { it.id }.map { release ->
        deleteReleaseCommand(release.id.toString())
    }

    val outputFile = File(artifactDir, "ReleaseDeletionScript.txt").also { it.createNewFile() }

    outputFile.writer().use { writer ->
        commands.forEach {
            writer.write(it)
            writer.appendLine()
        }
        writer.flush()
    }
}

private fun deleteReleaseCommand(id: String) =
    """gh api --method DELETE -H "Accept: application/vnd.github+json" -H "X-GitHub-Api-Version: 2022-11-28" /repos/$OWNER/$REPO_NAME/releases/$id"""

