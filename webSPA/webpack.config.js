const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const config = {
    entry: {
        eshop: './src/app.ts'
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
                use: ['style-loader','css-loader','postcss-loader', 'sass-loader']
            }
        ]
    },
    plugins: [
        new HtmlWebpackPlugin({
            template: './src/index.html',
            filename: 'index.html',
            favicon: './src/favicon.ico'
        })
    ],
    externals: {},
    devServer: {
        contentBase: path.join(__dirname, 'dist'),
        port: 9000,
        overlay: true,
        proxy: {
//            '/api/v1/basket': 'http://localhost:3001',
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
