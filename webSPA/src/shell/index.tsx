import * as React from 'react';
import * as ReactDOM from 'react-dom';
import { createStore, applyMiddleware } from 'redux';
import thunk from 'redux-thunk';
import { connect, Provider } from "react-redux";
import { logger } from 'redux-logger';

import {reducer, Catalog} from '../catalog';
import basket from '../basket';

//configure store
const store = createStore(reducer, applyMiddleware(thunk, logger));

const App = () => (
    <Catalog />
)

function start() {
    ReactDOM.render(
        <Provider store={store}>
            <App />
        </Provider>,
        document.getElementById('main')
    );
    basket.render(store);
}

export default function app() {
    return {
        start: start
    };
}