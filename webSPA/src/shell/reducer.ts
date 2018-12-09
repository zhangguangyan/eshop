function shellReducer(state = 'catalog', action) {
    switch (action.type) {
        case 'TO':
            if (action.page) {
                return action.page;
            } else {
                return 'ordering';
            }
            break;
        default:
            return state;
    }
}

export { shellReducer };

