package systems.dmx.fileupload;

import systems.dmx.files.StoredFile;
import systems.dmx.files.UploadedFile;



public interface FileUploadService {

    // === File Repository ===

    /**
     * Receives an uploaded file, stores it in the file repository, and creates a corresponding File topic.
     *
     * @param   repoPath    The directory where to store the uploaded file.
     *                      The directory must exist.
     *                      <p>
     *                      A repository path. Relative to the repository base path.
     *                      Must begin with slash, no slash at the end.
     *                      <p>
     *                      If per-workspace file repos are active (<code>dmx.filerepo.per_workspace=true</code>)
     *                      the repository path must contain the workspace prefix as the first path segment,
     *                      e.g. <code>"/workspace-1234"</code> where <code>1234</code> is the workspace ID.
     *                      <p>
     *                      However there is one exception to that rule: if and only if <code>"/"</code> is passed
     *                      as the repository path the workspace prefix is determined automatically with the
     *                      semantics of <i>current workspace</i>, based on the request's workspace cookie.
     *                      <p>
     *                      For support with constructing a repository path see the {@link pathPrefix} methods.
     *
     * @return  a StoredFile object which holds 2 information: the name of the uploaded file, and the ID
     *          of the created File topic. ### FIXDOC
     */
    StoredFile storeFile(UploadedFile file, String repoPath);
}
