export default ({axios: http}) => {

  const state = {
    visible: false,     // "Upload" dialog visibility
    visible2: false,    // "Create Folder" dialog visibility
    folderName: '',     // Name of selected folder
    path: ''            // Repo path of selected folder
  }

  const actions = {

    openUploadDialog ({rootState}) {
      state.visible = true
      initFolderState(rootState)
    },

    openCreateFolderDialog ({rootState}) {
      state.visible2 = true
      initFolderState(rootState)
    },

    createFolder (_, {repoPath, folderName}) {
      http.post(`/upload/${encodeURIComponent(repoPath)}/folder/${folderName}`)
    },

    closeUploadDialog () {
      state.visible = false
    },

    closeCreateFolderDialog () {
      state.visible2 = false
    }
  }

  // state helper

  function initFolderState (rootState) {
    const folder = rootState.object
    state.folderName = folder.children['dmx.files.folder_name'].value
    state.path = folder.children['dmx.files.path'].value
  }

  return {
    state,
    actions
  }
}
