export default ({dmx, axios: http}) => {

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

    closeUploadDialog () {
      state.visible = false
    },

    openCreateFolderDialog ({rootState}) {
      state.visible2 = true
      initFolderState(rootState)
    },

    closeCreateFolderDialog () {
      state.visible2 = false
    },

    createFolder ({dispatch}, {repoPath, folderName}) {
      http.post(`/upload/${encodeURIComponent(repoPath)}/folder/${folderName}`).then(response => {
        dispatch('revealRelatedTopic', {relTopic: new dmx.RelatedTopic(response.data)}, {root: true})
      })
    }
  }

  // state helper

  function initFolderState (rootState) {
    const folder = rootState.object
    state.folderName = folder.children['dmx.files.folder_name'].value
    state.path = folder.children['dmx.files.path'].value
  }

  return {
    namespaced: true,
    state,
    actions
  }
}
