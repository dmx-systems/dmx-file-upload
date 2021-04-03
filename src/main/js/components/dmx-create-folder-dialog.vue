<template>
  <el-dialog custom-class="dmx-create-folder-dialog" :visible="visible2" :title="title" :modal="false" v-draggable
      @opened="focus" @close="close">
    <div class="field-label">Folder Name</div>
    <el-input v-model="model" ref="input" @keyup.native.enter="action"></el-input>
    <el-button slot="footer" type="primary" @click="action">Create Folder</el-button>
  </el-dialog>
</template>

<script>
export default {

  data () {
    return {
      model: ''
    }
  },

  computed: {

    visible2 () {
      return this.$store.state.fileupload.visible2
    },

    folderName () {
      return this.$store.state.fileupload.folderName
    },

    path () {
      return this.$store.state.fileupload.path
    },

    title () {
      return `Create Folder in "${this.folderName}"`
    }
  },

  methods: {

    focus () {
      this.$refs.input.focus()
    },

    action () {
      this.$store.dispatch('fileupload/createFolder', {repoPath: this.path, folderName: this.model})
      this.close()
    },

    close () {
      this.$store.dispatch('fileupload/closeCreateFolderDialog')
    }
  }
}
</script>
