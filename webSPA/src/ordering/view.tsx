import * as React from 'react';
import { connect } from "react-redux";

class Ordering extends React.Component<{ [key: string]: any }> {
    constructor(props) {
        super(props)
    }

    componentDidMount() {
        console.log('DidMount');
        console.log(this.props.ordering);
    }

    render() {
        const order = this.props.ordering;
        const orderDetails = order.items.map(item =>
            <article key={item.id} className="esh-orders_new-items esh-orders_new-items--border row">
                <section className="esh-orders_new-item col-md-4 hidden-md-down">
                    <img className="esh-orders_new-image" src="" />
                </section>
                <section className="esh-orders_new-item esh-orders_new-item--middle col-4">{item.name}</section>
                <section className="esh-orders_new-item esh-orders_new-item--middle col-1">{item.price}</section>
                <section className="esh-orders_new-item esh-orders_new-item--middle col-1">{item.quantity}</section>
                <section className="esh-orders_new-item esh-orders_new-item--middle col-2">{item.cost}</section>
            </article>
        )

        return <div>
            <h4>ordering</h4>
            <div className="esh-orders_new-header">
                <div className="container">
                    <a className="esh-orders_new-back" >Back to basket</a>
                </div>
            </div>
            <div className="container">
                <form >
                    <section className="esh-orders_new-section">
                        <h4 className="esh-orders_new-title">Shipping Address</h4>
                        <div className="row">
                            <div className="col-md-6">
                                <div className="form-group" >
                                    <label className="esh-orders_new-title">Address</label>
                                    <input className="form-control form-input" type="text" placeholder="Street" />
                                </div>
                            </div>
                            <div className="col-md-6">
                                <div className="form-group" >
                                    <label className="esh-orders_new-title">City</label>
                                    <input className="form-control form-input" type="text" placeholder="City" />
                                </div>
                            </div>
                            <div className="col-md-6">
                                <div className="form-group" >
                                    <label className="esh-orders_new-title">State</label>
                                    <input className="form-control form-input" type="text" placeholder="state" />
                                </div>
                            </div>
                            <div className="col-md-6">
                                <div className="form-group" >
                                    <label className="esh-orders_new-title">Country</label>
                                    <input className="form-control form-input" type="text" placeholder="country" />
                                </div>
                            </div>
                        </div>
                    </section>
                    <section className="esh-orders_new-section">
                        <h4 className="esh-orders_new-title">Payment method</h4>
                        <div className="row">
                            <div className="col-md-6">
                                <div className="form-group" >
                                    <label className="esh-orders_new-title">Card number</label>
                                    <input className="form-control form-input" type="text" placeholder="000000000000000" />
                                </div>
                            </div>
                            <div className="col-md-6">
                                <div className="form-group" >
                                    <label className="esh-orders_new-title">Cardholder name</label>
                                    <input className="form-control form-input" type="text" placeholder="Card holder" />
                                </div>
                            </div>
                            <div className="col-md-6">
                                <div className="form-group" >
                                    <label className="esh-orders_new-title">Expiration date</label>
                                    <input className="form-control form-input form-input-medium" type="text" placeholder="MM/YY" />
                                </div>
                            </div>
                            <div className="col-md-6">
                                <div className="form-group" >
                                    <label className="esh-orders_new-title">Security code</label>
                                    <input className="form-control form-input form-input-small" type="text" placeholder="000" />
                                </div>
                            </div>
                        </div>
                    </section>
                    <section className="esh-orders_new-section">
                        <article className="esh-orders_new-titles row">
                            <section className="esh-orders_new-title col-12">Order details</section>
                        </article>
                        {orderDetails}
                    </section>

                    <section className="esh-orders_new-section esh-orders_new-section--right">
                        <article className="esh-orders_new-titles row">
                            <section className="esh-orders_new-title col-9"></section>
                            <section className="esh-orders_new-title col-2">Total</section>
                        </article>

                        <article className="esh-orders_new-items row">
                            <section className="esh-orders_new-item col-9"></section>
                            <section className="esh-orders_new-item esh-orders_new-item--mark col-2">{this.props.ordering.totalCost}</section>
                        </article>
                    </section>
                    <section className="esh-orders_new-section">
                        <div className="form-group row">
                            <div className="col-md-9">
                            </div>
                            <div className="col-md-2">
                                <button type="submit" className="btn esh-orders_new-placeOrder"
                                    onClick={() => this.props.placeOrder(order)}>[ Place Order ]</button>
                            </div>
                        </div>
                    </section>
                </form>
            </div>
        </div>;
    }
}

function mapStateToProps(state) {
    console.log(state);
    return {
        ordering: Object.assign({}, state.basket)
    };
}

function mapDispatchToProps(dispatch) {
    return {
        placeOrder: (order) => dispatch({ type: 'PLACE_ORDER', payload: order})
    };
}

export default connect(mapStateToProps, mapDispatchToProps)(Ordering);
