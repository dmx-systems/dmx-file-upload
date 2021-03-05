<template>
  <el-dialog custom-class="dmx-create-folder-dialog" :visible="visible2" :title="title" :modal="false" v-draggable
      @opened="focus" @close="close">
    <div class="field-label">Folder Name</div>
    <el-input v-model="model" ref="input"></el-input>
    <div slot="footer">
      <el-button type="primary" @click="createFolder">Create Folder</el-button>
    </div>
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

    createFolder () {
      this.$store.dispatch('createFolder', {repoPath: this.path, folderName: this.model})
    },


    close () {
      this.$store.dispatch('closeCreateFolderDialog')
    }
  }
}
</script>
