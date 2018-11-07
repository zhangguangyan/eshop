import * as React from 'react';
import * as ReactDOM from 'react-dom';
import { createStore, applyMiddleware } from 'redux';
import thunk from 'redux-thunk';

//----actions
const REQUEST_ALL = 'REQUEST_ALL';
const RECEIVE_ALL = 'RECEIVE_ALL';
//async action
function retrieveAll(url) {
    return function (dispatch) {
        dispatch({type:REQUEST_ALL});
        return fetch(url)
            .then(res => res.text())
            .then(response => {
                dispatch({
                    type: RECEIVE_ALL,
                    payload: response
                });
            })
            .catch(error => console.error('Error:', error));
    };
}

//reducers
function reducer(state: {[k: string]: any} = {}, action) {
    switch (action.type) {
        case REQUEST_ALL:
            return Object.assign(state, {}, {
                isRequesting: true
            });
        case RECEIVE_ALL:
            return Object.assign(state, {}, {
                isRequesting: false,
                response: action.payload
            });
        default:
            return state;

    }
}

const Hello = ({text}) => (
    <div>{text}</div>
);

//configure store
const store = createStore(reducer, applyMiddleware(thunk));

store.subscribe(() => {
    const state = store.getState();
    if (state.response) {
        console.log(state);
        const a = state.response;
        ReactDOM.render(
            <Hello text={a}/>,
        document.getElementById('main'));//.innerHTML = state.response;
    }
});

function start() {
    const url = '/api/v1/catalog/items';
    document.addEventListener("DOMContentLoaded", function () {
        const state = store.getState();
        if (state.isRequesting) {
            console.log('in progress.....');
        } else {
            store.dispatch(retrieveAll(url));
        }
    });
}

export default function app() {
    return {
        start: start
    };
}