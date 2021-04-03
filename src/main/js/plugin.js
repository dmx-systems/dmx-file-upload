export default ({store}) => ({

  storeModule: {
    name: 'fileupload',
    module: require('./file-upload').default
  },

  extraElementUI: true,

  components: [
    {
      comp: require('./components/dmx-upload-dialog').default,
      mount: 'webclient'
    },
    {
      comp: require('./components/dmx-create-folder-dialog').default,
      mount: 'webclient'
    }
  ],

  detailPanelButtons: {
    'dmx.files.folder': [
      {
        label: 'Upload File',
        handler: _ => store.dispatch('fileupload/openUploadDialog')
      },
      {
        label: 'Create Folder',
        handler: _ => store.dispatch('fileupload/openCreateFolderDialog')
      }
    ]
  }
})
