import { ADD_ITEM } from './actions';

const initialState = {
    items: [],
    total: 0
};

function reducer(state: { [key: string]: any } = initialState, action) {
    switch (action.type) {
        case ADD_ITEM:
            return Object.assign({}, state, {
                items: state.items.concat(action.payload),
                total: state.total + 1
            });
        case 'CHECKOUT':
            return state;
        case 'WHAT':
            return state;
        default:
            return state;
    }
}

export { reducer };