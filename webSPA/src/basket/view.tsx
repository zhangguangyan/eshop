var cart = document.getElementById('cart');

function render(store) {
    store.subscribe(() => {
        const state = store.getState().basket;
        if (state.items.length > 0) {
            cart.textContent = `Cart: (${state.total})`
        }
    });
}

export default {
    render: render
}