export default ({store}) => ({

  storeModule: {
    name: 'fileupload',
    module: require('./file-upload').default
  },

  components: [
    {
      comp: require('./components/dmx-upload-dialog').default,
      mount: 'webclient'
    }
  ],

  detailPanelButtons: {
    'dmx.files.folder': [
      {
        label: 'Upload File',
        handler: _ => store.dispatch('openUploadDialog')
      }
    ]
  }
})
