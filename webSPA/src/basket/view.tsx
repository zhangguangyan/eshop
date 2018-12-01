import { checkout } from './actions';

var cart = document.getElementById('cart');



function render(store) {
    store.subscribe(() => {
        const state = store.getState().basket;
        if (state.items.length > 0) {
            cart.textContent = `Cart: (${state.total})`
        }
    });
    cart.addEventListener('click', e => {
        console.log(e);
        store.dispatch(checkout('/api/v1/basket/checkout', store.getState().basket))
    });
}

export default {
    render: render
}