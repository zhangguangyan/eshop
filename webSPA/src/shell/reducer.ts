function shellReducer(state = 'catalog', action) {
    switch (action.type) {
        case 'TO':
            return action.page;
            break;
        default:
            return state;
    }
}

export { shellReducer };

