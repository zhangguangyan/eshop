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
                f.quantity += 1;
                f.cost = f.price * f.quantity;
                items = state.items;
            } else {
                item.quantity = 1;
                item.cost = item.price;
                items = state.items.concat(item);
            }
            return Object.assign({}, state, {
                items: items,
                total: state.total + 1,
                totalCost: items.reduce((sum, it) => sum + it.cost, 0)
            });
        case 'UPDATE_ITEM':
            const event = action.payload.event;
            const item1 = state.items.find(it => it.id == action.payload.id);
            item1.quantity = parseInt(event.target.value);
            item1.cost = item1.price * item1.quantity;
            return Object.assign({}, state, {
                totalCost: state.items.reduce((sum, it) => sum + it.cost, 0)
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