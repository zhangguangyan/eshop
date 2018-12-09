import * as React from 'react';
import { connect } from "react-redux";

import { checkout } from './actions';

var cart = document.getElementById('cart');

function render(store) {
    store.subscribe(() => {
        const basket = store.getState().basket;
        if (basket.items.length > 0) {
            cart.textContent = `Cart: (${basket.total})`
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
        const items = this.props.basket.items;
        let itemList = items.map((item) =>
            <div key={item.id}>
                <article className="esh-basket-items row">

                    <section className="esh-basket-item esh-basket-item--middle col-lg-3 hidden-lg-down">
                        <img className="esh-basket-image" src="" />
                    </section>
                    <section className="esh-basket-item esh-basket-item--middle col-3">{item.name}</section>
                    <section className="esh-basket-item esh-basket-item--middle col-2">{item.price}</section>
                    <section className="esh-basket-item esh-basket-item--middle col-2">
                        <input name="quantity"
                            className="esh-basket-input"
                            type="number"
                            min="1"
                            value={item.quantity}
                            onChange={(e) => { this.props.updateQuantity(e, item.id); }} />
                    </section>
                    <section className="esh-basket-item esh-basket-item--middle esh-basket-item--mark col-2">{item.cost}</section>
                </article>
                <br />
            </div>
        );
        return <div>
            <div className="esh-basket">
                <h4>Back to catalog</h4>

                <div className="container">
                    <article className="esh-basket-titles row">
                        <section className="esh-basket-title col-3">Product</section>
                        <section className="esh-basket-title col-3 hidden-lg-down"></section>
                        <section className="esh-basket-title col-2">Price</section>
                        <section className="esh-basket-title col-2">Quantity</section>
                        <section className="esh-basket-title col-2">Cost</section>
                    </article>

                    <div >
                        {itemList}

                    </div>
                </div>

                <div className="container">
                    <article className="esh-basket-titles esh-basket-titles--clean row">
                        <section className="esh-basket-title col-9"></section>
                        <section className="esh-basket-title col-2">Total</section>
                    </article>

                    <article className="esh-basket-items row">
                        <section className="esh-basket-item col-9"></section>
                        <section className="esh-basket-item esh-basket-item--mark col-2">{this.props.basket.totalCost}</section>
                    </article>

                    <article className="esh-basket-items row">
                        <section className="esh-basket-item col-7"></section>
                        <section className="esh-basket-item col-2">
                            <button className="btn esh-basket-checkout">[ Update ]</button>
                        </section>
                        <section className="esh-basket-item col-3">
                            <button className="btn" onClick={() => this.props.checkout(this.props.basket)}>[ Check out ]</button>
                        </section>
                    </article>
                </div>
            </div>

        </div>
    }
}
function mapStateToProps(state) {
    const basket = state.basket
    console.log(basket);
    return { basket: basket };
}

const mapDispatchToProps = (dispatch) => {
    return {
        checkout: (basket) => dispatch(checkout('/api/v1/basket/checkout', basket)),
        updateQuantity: (e, itemId) => {
            console.log(e);
            dispatch({ type: 'UPDATE_ITEM', payload: { event: e, id: itemId } });
        }
    }
};
export default {
    view: connect(mapStateToProps, mapDispatchToProps)(Basket),
    render: render
}
