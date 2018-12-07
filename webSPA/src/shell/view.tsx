import * as React from 'react';
import { connect } from "react-redux";

import { Catalog } from '../catalog';
import { basket } from '../basket';

function mapStateToProps(state) {
    return {
        current: state.shell
    };
}
const App = (page: any) => {
    const views = {
        'catalog': Catalog,
        'basket': basket.view

    };
    console.log(page.current);
    const View = views[page.current];
    return <View />;
};

export default connect(mapStateToProps, null)(App);
