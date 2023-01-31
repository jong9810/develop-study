const path = require('path');
const webpack = require('webpack');
// const RefreshWebpackPlugin = require('@pmmmwh/react-refresh-webpack-plugin');

module.exports = {
  name: '끝말잇기 설정',
  mode: 'development',
  devtool: 'eval',
  resolve: {
    extensions: ['.jsx', '.js'],
  },

  entry: {
    app: ['./client'],
  },

  module: {
    rules: [
      {
        test: /\.jsx?/,
        loader: 'babel-loader',
        options: {
          presets: [
            [
              '@babel/preset-env',
              {
                targets: {
                  browsers: ['> 1% in KR', 'last 2 chrome versions'],
                },
                debug: true,
              },
            ],
            '@babel/preset-react',
          ],
          // plugins: ['@react-refresh/babel'],
        },
      },
    ],
  },

  plugins: [new webpack.LoaderOptionsPlugin({ debug: true })], // new RefreshWebpackPlugin()],

  output: {
    path: path.join(__dirname, 'dist'),
    filename: 'app.js',
  },

  // devServer: {
  //   devMiddleware: { publicPath: '/dist/' },
  //   static: { directory: path.resolve(__dirname) },
  //   hot: true,
  // },
};
