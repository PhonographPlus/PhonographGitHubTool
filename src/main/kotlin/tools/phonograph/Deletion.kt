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


fun generateGitTagDeletionScript(releases: List<Release>) {
    val commandsLocal = releases.sortedBy { it.id }.map { release ->
        deleteGitLocalTagCommand(release.tagName)
    }
    val commandsRemote = releases.sortedBy { it.id }.map { release ->
        deleteGitRemoteTagCommand(release.tagName)
    }

    val outputFile = File(artifactDir, "TagDeletionScript.txt").also { it.createNewFile() }

    outputFile.writer().use { writer ->
        commandsLocal.forEach {
            writer.write(it)
            writer.appendLine()
        }
        writer.appendLine()
        commandsRemote.forEach {
            writer.write(it)
            writer.appendLine()
        }
        writer.flush()
    }
}


private fun deleteGitLocalTagCommand(tagName: String) =
    """git tag -d $tagName"""

private fun deleteGitRemoteTagCommand(tagName: String, remote: String = "github") =
    """git push $remote :refs/tags/$tagName"""

