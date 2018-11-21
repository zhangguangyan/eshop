import * as React from 'react';
import { connect } from "react-redux";

import { retrieveAll } from  './actions'

//--- views/component
class Catalog extends React.Component<{ [key: string]: any }> {
    constructor(props) {
        super(props)
    }

    componentDidMount() {
        console.log('DidMount');
        this.props.fireRequest();
    }

    render() {
        console.log('render');
        const loading = (this.props.loading) ? <h2>loading</h2> : '';
        const items = this.props.catalogItems;
        if (items) {
            let itemList = items.map((item) =>
                <div key={item.id} className="esh-catalog-item col-md-4">
                    <img className="esh-catalog-thumbnail" src={item.pictureUri} />
                    <button onClick={()=>this.props.addToCart(item)}>
                        [ ADD TO CART ]
                    </button>

                    <div className="esh-catalog-name">
                        <span>{item.name}</span>
                    </div>
                    <div className="esh-catalog-price">
                        <span>{item.price}</span>
                    </div>
                </div>
            );
            return <div className="esh-catalog-items row">
                {itemList}
            </div>;
        } else {
            return <div>
                {loading}
            </div>;
        }
    }
}

function mapStateToProps(state) {
    const cataglog = state.catalog
    return {
        loading: cataglog.isRequesting,
        catalogItems: cataglog.response
    };
}

const mapDispatchToProps = (dispatch) => {
    return {
        fireRequest: () => { dispatch(retrieveAll('/api/v1/catalog/items')) },
        // is it good to use string instead of action creation function as this will decouple catalog feature from basket
        addToCart: (item) => dispatch({type:'ADD_ITEM', payload: item}) 
    }
};
export default connect(mapStateToProps, mapDispatchToProps)(Catalog);
