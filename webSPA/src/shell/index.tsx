import * as React from 'react';
import * as ReactDOM from 'react-dom';
import { createStore, applyMiddleware, combineReducers } from 'redux';
import thunk from 'redux-thunk';
import { connect, Provider } from "react-redux";
import { logger } from 'redux-logger';

import { reducer as catalogReducer, Catalog } from '../catalog';
import { reducer as basketReducer, basket } from '../basket';

const appReducer = combineReducers({
    catalog: catalogReducer,
    basket: basketReducer
});

const store = createStore(appReducer, applyMiddleware(thunk, logger));

const App = () => (
    <Catalog />
);

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