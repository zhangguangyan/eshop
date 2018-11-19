var cart = document.getElementById('cart');
var total = 0;

function render(store) {
    store.subscribe(()=> {
        const state = store.getState();
        const item = state.item;
        if (item) {
            total +=1;
            cart.textContent = `Cart: (${total})`
        }
    });
}


export default {
    render: render
}