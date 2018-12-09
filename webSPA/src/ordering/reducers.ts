function reducer(state = {}, action) {
    switch (action.type) {
        case 'PLACE_ORDER':
            console.log('Place order');
            return Object.assign({}, state, {
                order: action.payload
            });
        default:
            return state;
    }
}

export default reducer;