export default () => {

  const state = {
    visible: false,     // Upload dialog visibility
    folderName: '',     // Name of folder to upload to
    path: ''            // Repo path to upload to
  }

  const actions = {

    openUploadDialog ({rootState}) {
      state.visible = true
      const folder = rootState.object
      state.folderName = folder.children['dmx.files.folder_name'].value
      state.path = folder.children['dmx.files.path'].value
    },

    closeUploadDialog () {
      state.visible = false
    }
  }

  return {
    state,
    actions
  }
}
