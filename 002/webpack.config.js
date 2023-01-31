const path = require('path');
const webpack = require('webpack');
// const ReactRefreshWebpackPlugin = require('@pmmmwh/react-refresh-webpack-plugin');

module.exports = {
  name: 'wordrelay-setting',
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
        use: [
          {
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
    ],
  },

  plugins: [new webpack.LoaderOptionsPlugin({ debug: true })], // new ReactRefreshWebpackPlugin()],

  output: {
    path: path.join(__dirname, 'dist'),
    filename: 'app.js',
    publicPath: '/dist',
  },

  // devServer: {
  //   devMiddleware: { publicPath: '/dist' },
  //   static: { directory: path.resolve(__dirname) },
  //   hot: true,
  // },
};
