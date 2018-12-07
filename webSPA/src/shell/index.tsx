import * as React from 'react';
import * as ReactDOM from 'react-dom';
import { createStore, applyMiddleware, combineReducers } from 'redux';
import thunk from 'redux-thunk';
import { connect, Provider } from "react-redux";
import { logger } from 'redux-logger';

import { reducer as catalogReducer, Catalog } from '../catalog';
import { reducer as basketReducer, basket } from '../basket';

function shellReducer(state = 'catalog', action) {
    switch (action.type) {
        case 'TO':
            return action.page;
            break;
        default:
            return state;
    }

}
const appReducer = combineReducers({
    shell: shellReducer,
    catalog: catalogReducer,
    basket: basketReducer
});

const store = createStore(appReducer, applyMiddleware(thunk, logger));

function mapStateToProps(state) {
    //return state.shell
    return {
        current: state.shell
    };
}

const App = connect(mapStateToProps, null)((page: any) => {
    const views = {
        'catalog': Catalog,
        'basket': basket.view

    };
    console.log(page.current);
    const View = views[page.current];
    return <View />;
});

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