const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const config = {
    entry: {
        eshop: './src/app.js'
    },
    output: {
        filename: 'js/[name].bundle.[hash].js',
        path: path.resolve('./dist')
    },
    resolve: {
        extensions: ['.ts', '.js', '.tsx']
    },
    module: {
        rules: [
            {
                test: /\.(ts$|tsx$)|\.js$/,
                exclude: /(node_modules|bower_components)/,
                use: 'ts-loader'
            },
            {
                test: /\.scss$/,
                use: ['style-loader','css-loader','sass-loader']
            }
        ]
    },
    plugins: [
        new HtmlWebpackPlugin({
            template: './src/index.html',
            filename: 'index.html'
        })
    ],
    externals: {},
    devServer: {
        contentBase: path.join(__dirname, 'dist'),
        port: 9000,
        overlay: true,
        proxy: {
            '/api': 'http://localhost:8081'
        }
    }
};

module.exports = (env) => {
    if (env && env.prod) {
        config.mode = 'production';
        config.output.filename = 'js/[name].[hash].min.js';
    } else {
        config.mode = 'development';
        config.output.filename = 'js/[name].bundle.[hash].js';
        config.devtool = 'inline-source-map'
    }
    return config;
};
