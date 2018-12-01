export const ADD_ITEM = 'ADD_ITEM';

//async action
export const checkout = (url: string, basket) => dispatch => {
    dispatch({ type: 'CHECK_OUT' });
    console.log(basket);
    return fetch(url, {
            method: 'POST', // or 'PUT'
            body: JSON.stringify(basket), // data can be `string` or {object}!
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(res => res.json())
        .then(response => {
            dispatch({
                type: 'WHAT',
                payload: response
            });
        })
        .catch(error => console.error('Error:', error));
};

