import * as React from 'react';
import { connect } from "react-redux";

class Ordering extends React.Component<{ [key: string]: any }> {
    render() {
        const items = this.props.odering;
        return <div>
            <h4>ordering</h4>
            <button onClick={() => this.props.placeOrder(items)}>[ Place Order ]</button>
        </div>;
    }
}

function mapStateToProps(state) {
    return {
        ordering: state.basket
    }
}

function mapDispatchToProps(dispatch) {
    return {
        placeOrder: (ordering) => dispatch({ type: 'PLACE_ORDER', payload: ordering })
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(Ordering);
