const placeOrder = (url, what) => dispatch => {
    dispatch({ type: '????' });
    return fetch(url)
        .then(res => res.json())
        .then(res => {
            dispatch({
                type: '????',
                payload: res
            });
        })
        .catch(error => console.error('Error:', error));
}
