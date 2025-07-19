const pluginUri = 'systems.dmx.file-upload'

const MiniCssExtractPlugin = require('mini-css-extract-plugin')
const { VueLoaderPlugin }  = require('vue-loader')
const path = require('path')

module.exports = {
  entry: './src/main/js/plugin.js',
  output: {
    path: path.join(__dirname, '/target/classes/web'),
    filename: '[chunkhash].plugin.js',
    chunkFilename: '[chunkhash].[name].js',
    publicPath: '/' + pluginUri + '/',
    library: '_' + pluginUri.replace(/[.-]/g, '_'),
    libraryTarget: 'jsonp'
  },
  resolve: {
    extensions: ['.js', '.vue']
  },
  module: {
    rules: [
      {
        test: /\.vue$/,
        use: 'vue-loader'
      },
      {
        test: /\.js$/,
        use: 'babel-loader',
        exclude: /node_modules/
      },
      {
        test: /\.css$/,
        use: [MiniCssExtractPlugin.loader, 'css-loader']
      },
      {
        test: /\.(png|jpg|jpeg|gif|eot|ttf|woff|woff2|svg|svgz)(\?.+)?$/,
        type: 'asset/resource'
      }
    ]
  },
  plugins: [
    new MiniCssExtractPlugin({
      filename: '[contenthash].style.css',
      chunkFilename: '[contenthash].[name].css'
    }),
    new VueLoaderPlugin()
  ],
  stats: {
    assets: false,
    modules: false
  },
  performance: {
    hints: false
  }
}
