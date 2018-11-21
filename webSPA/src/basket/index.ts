var cart = document.getElementById('cart');
var total = 0;

function reducer(state: { [key: string]: any } = { items: [] }, action) {
    switch (action.type) {
        case 'ADD_ITEM':
            return Object.assign({}, state, {
                items: state.items.concat(action.payload)
            });
        default:
            return state;
    }
}
function render(store) {
    store.subscribe(() => {
        const state = store.getState().basket;
        const items = state.items;
        if (items.length > 0) {
            total += 1;
            cart.textContent = `Cart: (${total})`
        }
    });
}


export default {
    render: render
}
export { reducer };