import * as React from 'react';
import { connect } from "react-redux";

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
        store.dispatch({ type: 'TO', page: 'basket' });
    });
}

class Basket extends React.Component<{ [key: string]: any }> {
    constructor(props) {
        super(props)
    }

    render() {
        return <div>
            <h3>basket</h3>
            <button onClick={() => this.props.checkout(this.props.basket)}>Check out</button>
        </div>
    }
}
function mapStateToProps(state) {
    const basket = state.basket
    console.log(basket);
    return { basket: basket};
}

const mapDispatchToProps = (dispatch) => {
    return {
        checkout: (basket) => { dispatch(checkout('/api/v1/basket/checkout', basket)) },
    }
};
export default {
    view: connect(mapStateToProps, mapDispatchToProps)(Basket),
    render: render
}
