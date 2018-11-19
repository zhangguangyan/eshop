import { REQUEST_ALL, RECEIVE_ALL } from './actions';

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
        case 'ADD_ITEM':
            return Object.assign({}, state, {
                item: action.payload
            });
        default:
            return state;
    }
}

export default reducer
