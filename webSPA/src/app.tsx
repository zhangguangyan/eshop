import * as React from 'react';
import * as ReactDOM from 'react-dom';
import { createStore, applyMiddleware, combineReducers } from 'redux';
import thunk from 'redux-thunk';
import { Provider } from "react-redux";
import { logger } from 'redux-logger';

import './app.scss';

import { reducer as catalogReducer } from './catalog';
import { reducer as basketReducer, basket } from './basket';
import { App, reducer as shellReducer } from './shell';
import { reducer as orderingReducer } from './ordering';

const appReducer = combineReducers({
    shell: shellReducer,
    catalog: catalogReducer,
    basket: basketReducer,
    ordering: orderingReducer
});

const store = createStore(appReducer, applyMiddleware(thunk, logger));

ReactDOM.render(
    <Provider store={store}>
        <App />
    </Provider>,
    document.getElementById('main')
);
basket.render(store);
