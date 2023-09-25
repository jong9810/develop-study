const path = require('path');
const ReactRefreshWebpackPlugin = require('@pmmmwh/react-refresh-webpack-plugin');

module.exports = {
  name: '006',
  mode: 'development', // development production
  devtool: 'eval',
  resolve: {
    extensions: ['.js', '.jsx'],
  },

  entry: {
    app: ['./client'],
  },
  module: {
    rules: [
      {
        test: /\.jsx?/,
        loader: 'babel-loader',
        exclude: path.join(__dirname, 'node_modules'),
        options: {
          presets: [
            ['@babel/preset-env', { targets: { browsers: ['last 2 chrome versions'] } }],
            '@babel/preset-react',
          ],
          plugins: ['react-refresh/babel'],
        },
      },
    ],
  },
  plugins: [new ReactRefreshWebpackPlugin()],
  output: {
    path: path.join(__dirname, 'dist'),
    filename: 'app.js',
    publicPath: '/dist',
  },
  devServer: {
    devMiddleware: { publicPath: '/dist' },
    static: { directory: path.resolve(__dirname) },
    port: 3000,
    hot: true,
  },
};
