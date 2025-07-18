export default ({dmx, axios: http}) => {

  return {

    namespaced: true,

    state: {
      visible: false,     // "Upload" dialog visibility
      visible2: false,    // "Create Folder" dialog visibility
      folderName: '',     // Name of selected folder
      path: ''            // Repo path of selected folder
    },

    actions: {

      openUploadDialog ({state, rootState}) {
        state.visible = true
        initFolderState(state, rootState)
      },

      closeUploadDialog ({state}) {
        state.visible = false
      },

      openCreateFolderDialog ({state, rootState}) {
        state.visible2 = true
        initFolderState(state, rootState)
      },

      closeCreateFolderDialog ({state}) {
        state.visible2 = false
      },

      createFolder ({dispatch}, {repoPath, folderName}) {
        http.post(`/upload/${encodeURIComponent(repoPath)}/folder/${folderName}`).then(response => {
          dispatch('revealRelatedTopic', {relTopic: new dmx.RelatedTopic(response.data)}, {root: true})
        })
      }
    }
  }

  // state helper

  function initFolderState (state, rootState) {
    const folder = rootState.object
    state.folderName = folder.children['dmx.files.folder_name'].value
    state.path = folder.children['dmx.files.path'].value
  }
}
