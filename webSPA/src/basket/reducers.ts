import { ADD_ITEM } from './actions';

const initialState = {
    items: [],
    total: 0
};

function reducer(state: { [key: string]: any } = initialState, action) {
    switch (action.type) {
        case ADD_ITEM:
            const item = action.payload;
            const f = state.items.find(it => it.id == item.id);
            let items;
            if (f) {
                f.quantity +=1;
                items = state.items;
            } else {
                item.quantity = 1;
                items = state.items.concat(item);
            }
            return Object.assign({}, state, {
                items: items,
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