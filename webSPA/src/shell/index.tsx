import * as React from 'react';
import * as ReactDOM from 'react-dom';
import { createStore, applyMiddleware } from 'redux';
import thunk from 'redux-thunk';
import { connect, Provider } from "react-redux";
import { logger } from 'redux-logger';

//----actions
const REQUEST_ALL = 'REQUEST_ALL';
const RECEIVE_ALL = 'RECEIVE_ALL';

//async action
const retrieveAll = (url: string) => dispatch => {
    dispatch({ type: REQUEST_ALL });
    return fetch(url)
        .then(res => res.json())
        .then(response => {
            dispatch({
                type: RECEIVE_ALL,
                payload: response
            });
        })
        .catch(error => console.error('Error:', error));
};

//reducers
function reducer(state: { [k: string]: any } = {}, action) {
    switch (action.type) {
        case REQUEST_ALL:
            return Object.assign({}, state, {
                isRequesting: true
            });
        case RECEIVE_ALL:
            return {
                ...state,
                isRequesting: false,
                response: action.payload
            };
        default:
            return state;
    }
}

//configure store
const store = createStore(reducer, applyMiddleware(thunk, logger));

//--- views/component
class Hello extends React.Component<{ [key: string]: any }> {
    constructor(props) {
        super(props)
    }

    componentDidMount() {
        console.log('DidMount');
        this.props.fireRequest();
    }

    render() {
        console.log('render');
        console.log(this.props);
        const loading = (this.props.loading) ? <h2>loading</h2> : '';
        const items = this.props.catalogItems;
        if (items) {
            let itemList = items.map((item) =>
                <li key={item.id}>{item.description}</li>
            );
            return <div>
                <ul>{itemList}</ul>
            </div>;
        } else {
            return <div>
                {loading}
            </div>;
        }
    }
}

function mapStateToProps(state) {
    return {
        loading: state.isRequesting,
        catalogItems: state.response
    };
}

const mapDispatchToProps = (dispatch) => {
    return {
        fireRequest: () => { dispatch(retrieveAll('/api/v1/catalog/items')) }
    }
};
const ConnectedHello = connect(mapStateToProps, mapDispatchToProps)(Hello);

const App = () => (
    <ConnectedHello />
)

function start() {
    ReactDOM.render(
        <Provider store={store}>
            <App />
        </Provider>,
        document.getElementById('main')
    );
}

export default function app() {
    return {
        start: start
    };
}